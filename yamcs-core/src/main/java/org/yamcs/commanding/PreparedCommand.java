package org.yamcs.commanding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.yamcs.StandardTupleDefinitions;
import org.yamcs.cmdhistory.protobuf.Cmdhistory.Assignment;
import org.yamcs.cmdhistory.protobuf.Cmdhistory.AssignmentInfo;
import org.yamcs.parameter.Value;
import org.yamcs.protobuf.Commanding.CommandHistoryAttribute;
import org.yamcs.protobuf.Commanding.CommandHistoryEntry;
import org.yamcs.protobuf.Commanding.CommandId;
import org.yamcs.protobuf.Commanding.VerifierConfig;
import org.yamcs.protobuf.Yamcs.Value.Type;
import org.yamcs.utils.StringConverter;
import org.yamcs.utils.ValueHelper;
import org.yamcs.utils.ValueUtility;
import org.yamcs.xtce.Argument;
import org.yamcs.xtce.MetaCommand;
import org.yamcs.xtce.XtceDb;
import org.yamcs.yarch.ColumnDefinition;
import org.yamcs.yarch.DataType;
import org.yamcs.yarch.Tuple;
import org.yamcs.yarch.TupleDefinition;

/**
 * This class is to keep track of a command binary and source included
 * 
 * @author nm
 *
 */
public class PreparedCommand {
    private byte[] binary;
    private CommandId id;
    private MetaCommand metaCommand;
    private final UUID uuid; // Used in REST API as an easier single-field ID. Not persisted.

    // used when a command has a transmissionConstraint with timeout
    // when the command is ready to go, but is waiting for a transmission constraint, this is set to true
    private boolean pendingTransmissionConstraint;

    // this is the time when the clock starts ticking for fullfilling the transmission constraint
    // -1 means it has not been set yet
    private long transmissionContraintCheckStart = -1;

    // if true, the transmission constraints (if existing) will not be checked
    boolean disableTransmissionConstraints = false;

    boolean disableCommandVerifiers = false;

    List<CommandHistoryAttribute> attributes = new ArrayList<>();
    private Map<Argument, Value> argAssignment;
    private Set<String> userAssignedArgumentNames;

    // Verifier-specific configuration options (that override the MDB verifier settings)
    private Map<String, VerifierConfig> verifierConfig = new HashMap<>();

    // column names to use when converting to tuple
    public final static String CNAME_GENTIME = StandardTupleDefinitions.GENTIME_COLUMN;
    public final static String CNAME_SEQNUM = StandardTupleDefinitions.SEQNUM_COLUMN;
    public final static String CNAME_ORIGIN = StandardTupleDefinitions.TC_ORIGIN_COLUMN;
    public final static String CNAME_USERNAME = "username";
    public final static String CNAME_BINARY = "binary";
    public final static String CNAME_CMDNAME = "cmdName";
    public final static String CNAME_SOURCE = "source";
    public final static String CNAME_ASSIGNMENTS = "assignments";
    public final static String CNAME_COMMENT = "comment";

    private static Set<String> reservedNames = new HashSet<>();
    static {
        reservedNames.add(CNAME_GENTIME);
        reservedNames.add(CNAME_SEQNUM);
        reservedNames.add(CNAME_ORIGIN);
        reservedNames.add(CNAME_USERNAME);
        reservedNames.add(CNAME_BINARY);
        reservedNames.add(CNAME_CMDNAME);
        reservedNames.add(CNAME_SOURCE);
        reservedNames.add(CNAME_ASSIGNMENTS);
    }

    public PreparedCommand(CommandId id) {
        this.id = id;
        uuid = UUID.randomUUID();
    }

    /**
     * Used for testing the uplinkers
     * 
     * @param binary
     */
    public PreparedCommand(byte[] binary) {
        this.setBinary(binary);
        uuid = UUID.randomUUID();
    }

    public long getGenerationTime() {
        return id.getGenerationTime();
    }

    public void setSource(String source) {
        setStringAttribute(CNAME_SOURCE, source);
    }

    public String getSource() {
        return getStringAttribute(CNAME_SOURCE);
    }

    public void setComment(String comment) {
        setStringAttribute(CNAME_COMMENT, comment);
    }

    public String getComment() {
        return getStringAttribute(CNAME_COMMENT);
    }

    public String getCmdName() {
        return id.getCommandName();
    }

    public String getStringAttribute(String attrname) {
        CommandHistoryAttribute a = getAttribute(attrname);
        if (a != null) {
            Value v = ValueUtility.fromGpb(a.getValue());
            if (v.getType() == Type.STRING) {
                return v.getStringValue();
            }
        }
        return null;
    }

    public CommandHistoryAttribute getAttribute(String name) {
        for (CommandHistoryAttribute a : attributes) {
            if (name.equals(a.getName())) {
                return a;
            }
        }
        return null;
    }

    public CommandId getCommandId() {
        return id;
    }

    public UUID getUUID() {
        return uuid;
    }

    static public CommandId getCommandId(Tuple t) {
        CommandId cmdId = CommandId.newBuilder().setGenerationTime((Long) t.getColumn(CNAME_GENTIME))
                .setOrigin((String) t.getColumn(CNAME_ORIGIN)).setSequenceNumber((Integer) t.getColumn(CNAME_SEQNUM))
                .setCommandName((String) t.getColumn(CNAME_CMDNAME)).build();
        return cmdId;
    }

    public Tuple toTuple() {
        TupleDefinition td = StandardTupleDefinitions.TC.copy();
        ArrayList<Object> al = new ArrayList<>();
        al.add(id.getGenerationTime());
        al.add(id.getOrigin());
        al.add(id.getSequenceNumber());
        al.add(id.getCommandName());

        if (getBinary() != null) {
            td.addColumn(CNAME_BINARY, DataType.BINARY);
            al.add(getBinary());
        }

        for (CommandHistoryAttribute a : attributes) {
            td.addColumn(a.getName(), ValueUtility.getYarchType(a.getValue().getType()));
            al.add(ValueUtility.getYarchValue(a.getValue()));
        }

        AssignmentInfo.Builder assignmentb = AssignmentInfo.newBuilder();
        if (getArgAssignment() != null) {
            for (Entry<Argument, Value> entry : getArgAssignment().entrySet()) {
                assignmentb.addAssignment(Assignment.newBuilder()
                        .setName(entry.getKey().getName())
                        .setValue(ValueUtility.toGbp(entry.getValue()))
                        .setUserInput(userAssignedArgumentNames.contains(entry.getKey().getName()))
                        .build());
            }
        }
        td.addColumn(CNAME_ASSIGNMENTS, DataType.protobuf("org.yamcs.cmdhistory.protobuf.Cmdhistory$AssignmentInfo"));
        al.add(assignmentb.build());

        return new Tuple(td, al.toArray());
    }

    public void setBinary(byte[] b) {
        this.binary = b;
    }

    public String getUsername() {
        CommandHistoryAttribute cha = getAttribute(CNAME_USERNAME);
        if (cha == null) {
            return null;
        }

        return cha.getValue().getStringValue();
    }

    public List<CommandHistoryAttribute> getAttributes() {
        return attributes;
    }

    public static PreparedCommand fromTuple(Tuple t, XtceDb xtcedb) {
        CommandId cmdId = getCommandId(t);
        PreparedCommand pc = new PreparedCommand(cmdId);
        pc.setMetaCommand(xtcedb.getMetaCommand(cmdId.getCommandName()));
        for (int i = 0; i < t.size(); i++) {
            ColumnDefinition cd = t.getColumnDefinition(i);
            String name = cd.getName();
            if (CNAME_GENTIME.equals(name) || CNAME_ORIGIN.equals(name) || CNAME_SEQNUM.equals(name)
                    || CNAME_ASSIGNMENTS.equals(name) || CNAME_COMMENT.equals(name)) {
                continue;
            }
            Value v = ValueUtility.getColumnValue(cd, t.getColumn(i));
            CommandHistoryAttribute a = CommandHistoryAttribute.newBuilder().setName(name)
                    .setValue(ValueUtility.toGbp(v)).build();
            pc.attributes.add(a);
        }
        pc.setBinary((byte[]) t.getColumn(CNAME_BINARY));
        if (t.hasColumn(CNAME_COMMENT)) {
            String comment = (String) t.getColumn(CNAME_COMMENT);
            pc.setComment(comment);
        }

        AssignmentInfo assignments = (AssignmentInfo) t.getColumn(CNAME_ASSIGNMENTS);
        if (assignments != null) {
            pc.argAssignment = new HashMap<>();
            for (Assignment assignment : assignments.getAssignmentList()) {
                Argument arg = findArgument(pc.getMetaCommand(), assignment.getName());
                Value v = ValueUtility.fromGpb(assignment.getValue());
                pc.argAssignment.put(arg, v);
            }
        }
        return pc;
    }

    private static Argument findArgument(MetaCommand mc, String name) {
        Argument arg = mc.getArgument(name);
        if (arg == null && mc.getBaseMetaCommand() != null) {
            arg = findArgument(mc.getBaseMetaCommand(), name);
        }
        return arg;
    }

    public static PreparedCommand fromCommandHistoryEntry(CommandHistoryEntry che) {
        CommandId cmdId = che.getCommandId();
        PreparedCommand pc = new PreparedCommand(cmdId);

        pc.attributes = che.getAttrList();

        return pc;
    }

    public CommandHistoryEntry toCommandHistoryEntry() {
        CommandHistoryEntry.Builder cheb = CommandHistoryEntry.newBuilder().setCommandId(id);
        cheb.addAllAttr(attributes);
        return cheb.build();
    }

    public void setStringAttribute(String name, String value) {
        int i;
        for (i = 0; i < attributes.size(); i++) {
            CommandHistoryAttribute a = attributes.get(i);
            if (name.equals(a.getName())) {
                break;
            }
        }
        CommandHistoryAttribute a = CommandHistoryAttribute.newBuilder().setName(name)
                .setValue(ValueHelper.newValue(value)).build();
        if (i < attributes.size()) {
            attributes.set(i, a);
        } else {
            attributes.add(a);
        }
    }

    public void addStringAttribute(String name, String value) {
        CommandHistoryAttribute a = CommandHistoryAttribute.newBuilder().setName(name)
                .setValue(ValueHelper.newValue(value)).build();
        attributes.add(a);
    }

    public void addAttribute(CommandHistoryAttribute cha) {
        String name = cha.getName();
        if (CNAME_GENTIME.equals(name) || CNAME_ORIGIN.equals(name) || CNAME_SEQNUM.equals(name)
                || CNAME_ASSIGNMENTS.equals(name)) {
            throw new IllegalArgumentException("Cannot use '" + name + "' as a command attribute");
        }
        attributes.add(cha);
    }

    public byte[] getBinary() {
        return binary;
    }

    public void setUsername(String username) {
        setStringAttribute(CNAME_USERNAME, username);
    }

    public MetaCommand getMetaCommand() {
        return metaCommand;
    }

    public void setMetaCommand(MetaCommand cmd) {
        this.metaCommand = cmd;
    }

    public boolean isPendingTransmissionConstraints() {
        return pendingTransmissionConstraint;
    }

    public void setPendingTransmissionConstraints(boolean b) {
        this.pendingTransmissionConstraint = b;
    }

    public long getTransmissionContraintCheckStart() {
        return transmissionContraintCheckStart;
    }

    public void setTransmissionContraintCheckStart(long transmissionContraintCheckStart) {
        this.transmissionContraintCheckStart = transmissionContraintCheckStart;
    }

    public void setArgAssignment(Map<Argument, Value> argAssignment, Set<String> userAssignedArgumentNames) {
        this.argAssignment = argAssignment;
        this.userAssignedArgumentNames = userAssignedArgumentNames;
    }

    public Map<Argument, Value> getArgAssignment() {
        return argAssignment;
    }

    @Override
    public String toString() {
        return "PreparedCommand(" + uuid + ", " + StringConverter.toString(id) + ")";
    }

    public void disableTransmissionContraints(boolean b) {
        disableTransmissionConstraints = b;
    }

    /**
     * 
     * @return true if the transmission constraints have to be disabled for this command
     */
    public boolean disableTransmissionContraints() {
        return disableTransmissionConstraints;
    }

    /**
     * 
     * @return true if the command verifiers have to be disabled for this command
     */
    public boolean disableCommandVerifiers() {
        return disableCommandVerifiers;
    }

    public void disableCommandVerifiers(boolean b) {
        disableCommandVerifiers = b;
    }

    public void addVerifierConfig(String name, VerifierConfig verifierConfig) {
        this.verifierConfig.put(name, verifierConfig);
    }

    /**
     * @return a list of command verifiers options overriding MDB settings.
     */
    public Map<String, VerifierConfig> getVerifierOverride() {
        return verifierConfig;
    }
}
