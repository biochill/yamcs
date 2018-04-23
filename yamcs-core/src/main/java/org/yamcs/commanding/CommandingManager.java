package org.yamcs.commanding;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yamcs.ErrorInCommand;
import org.yamcs.NoPermissionException;
import org.yamcs.Processor;
import org.yamcs.YamcsException;
import org.yamcs.management.ManagementService;
import org.yamcs.protobuf.Commanding.CommandId;
import org.yamcs.security.AuthenticationToken;
import org.yamcs.security.InvalidAuthenticationToken;
import org.yamcs.security.Privilege;
import org.yamcs.security.PrivilegeType;
import org.yamcs.security.SystemPrivilege;
import org.yamcs.xtce.ArgumentAssignment;
import org.yamcs.xtce.MetaCommand;
import org.yamcs.xtceproc.MetaCommandProcessor;
import org.yamcs.xtceproc.MetaCommandProcessor.CommandBuildResult;

import com.google.common.util.concurrent.AbstractService;

/**
 * Responsible for parsing and tc packet composition.
 * 
 * @author nm
 *
 */
public class CommandingManager extends AbstractService {
    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private Processor processor;
    private CommandQueueManager commandQueueManager;
    final MetaCommandProcessor metaCommandProcessor;

    /**
     * Keeps a reference to the channel and creates the queue manager
     * 
     * @param proc
     */
    public CommandingManager(Processor proc) {
        this.processor = proc;
        this.commandQueueManager = new CommandQueueManager(this);
        ManagementService.getInstance().registerCommandQueueManager(proc.getInstance(), proc.getName(),
                commandQueueManager);
        metaCommandProcessor = new MetaCommandProcessor(proc.getProcessorData());
    }

    public CommandQueueManager getCommandQueueManager() {
        return commandQueueManager;
    }

    /**
     * pc is a command whose source is included. parse the source populate the binary part and the definition.
     */
    public PreparedCommand buildCommand(MetaCommand mc, List<ArgumentAssignment> argAssignmentList, String origin,
            int seq, AuthenticationToken authToken) throws ErrorInCommand, NoPermissionException, YamcsException {
        log.debug("building command {} with arguments {}", mc.getName(), argAssignmentList);

        if (!Privilege.getInstance().hasPrivilege1(authToken, PrivilegeType.TC, mc.getName())) {
            throw new NoPermissionException("User has no privilege on command " + mc.getName());
        }
        if (origin == null)
            origin = "anonymous";

        CommandBuildResult cbr = metaCommandProcessor.buildCommand(mc, argAssignmentList);

        CommandId cmdId = CommandId.newBuilder().setCommandName(mc.getQualifiedName()).setOrigin(origin)
                .setSequenceNumber(seq).setGenerationTime(processor.getCurrentTime()).build();
        PreparedCommand pc = new PreparedCommand(cmdId);
        pc.setMetaCommand(mc);
        pc.setBinary(cbr.getCmdPacket());
        pc.setArgAssignment(cbr.getArgs());

        String username;
        if (authToken != null && authToken.getPrincipal() != null) {
            username = authToken.getPrincipal().toString();
        } else {
            username = Privilege.getInstance().getDefaultUser();
        }
        pc.setUsername(username);

        return pc;
    }

    /**
     * @return the queue that the command was sent to
     * @throws InvalidAuthenticationToken
     */
    public CommandQueue sendCommand(AuthenticationToken authToken, PreparedCommand pc)
            throws InvalidAuthenticationToken {
        log.debug("sendCommand commandSource={}", pc.getSource());
        return commandQueueManager.addCommand(authToken, pc);
    }

    public void addToCommandHistory(CommandId commandId, String key, String value, AuthenticationToken authToken)
            throws NoPermissionException {
        if (!Privilege.getInstance().hasPrivilege1(authToken, PrivilegeType.SYSTEM,
                SystemPrivilege.MayModifyCommandHistory.name())) {
            log.warn("Throwing InsufficientPrivileges for lack of COMMANDING privilege for user {}", authToken);
            throw new NoPermissionException("User has no privilege to update command history ");
        }

        commandQueueManager.addToCommandHistory(commandId, key, value);
    }

    public Processor getChannel() {
        return processor;
    }

    @Override
    protected void doStart() {
        commandQueueManager.startAsync();
        commandQueueManager.awaitRunning();
        notifyStarted();
    }

    @Override
    protected void doStop() {
        ManagementService.getInstance().unregisterCommandQueueManager(processor.getInstance(), processor.getName(),
                commandQueueManager);
        commandQueueManager.stopAsync();
        notifyStopped();
    }
}
