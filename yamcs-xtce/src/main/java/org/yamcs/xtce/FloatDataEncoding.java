package org.yamcs.xtce;

import java.nio.ByteOrder;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * For common encodings of floating point data
 * @author nm
 *
 */
public class FloatDataEncoding extends DataEncoding implements NumericDataEncoding {
    private static final long serialVersionUID = 3L;

    public enum Encoding {
        IEEE754_1985, STRING
    }; // DIFFERS_FROM_XTCE

    Calibrator defaultCalibrator = null;
    private List<ContextCalibrator> contextCalibratorList = null;
    
    private Encoding encoding;

    StringDataEncoding stringEncoding = null;

    /**
     * FloadDataEncoding of type {@link FloatDataEncoding.Encoding#IEEE754_1985}
     * 
     * @param sizeInBits
     */
    public FloatDataEncoding(int sizeInBits) {
        this(sizeInBits, ByteOrder.BIG_ENDIAN);
    }

    public FloatDataEncoding(int sizeInBits, ByteOrder byteOrder) {
        super(sizeInBits, byteOrder);
        setEncoding(Encoding.IEEE754_1985);
    }

    /**
     * Float data encoded as a string.
     * 
     * @param sde
     *            describes how the string is encoded
     */
    public FloatDataEncoding(StringDataEncoding sde) {
        super(sde.getSizeInBits());
        setEncoding(Encoding.STRING);
        stringEncoding = sde;
    }

    public Encoding getEncoding() {
        return encoding;
    }

    public StringDataEncoding getStringDataEncoding() {
        return stringEncoding;
    }

    public Calibrator getDefaultCalibrator() {
        return defaultCalibrator;
    }

    public void setDefaultCalibrator(Calibrator calibrator) {
        this.defaultCalibrator = calibrator;
    }

    @Override
    public String toString() {
        switch (getEncoding()) {
        case IEEE754_1985:
            return "FloatDataEncoding(sizeInBits=" + sizeInBits + ""
                    + (defaultCalibrator == null ? "" : (", defaultCalibrator:" + defaultCalibrator))
                    + ")";
        case STRING:
            return "FloatDataEncoding(StringEncoding: " + stringEncoding
                    + (defaultCalibrator == null ? "" : (", defaultCalibrator:" + defaultCalibrator))
                    + ")";
        default:
            return "UnknownFloatEncoding(" + getEncoding() + ")";
        }

    }

    @Override
    public Object parseString(String stringValue) {
        switch (getEncoding()) {
        case IEEE754_1985:
            if (sizeInBits == 32) {
                return Float.parseFloat(stringValue);
            } else {
                return Double.parseDouble(stringValue);
            }
        case STRING:
            return stringValue;
        default:
            throw new IllegalStateException("Unknown encoding " + getEncoding());
        }
    }

    public void setEncoding(Encoding encoding) {
        this.encoding = encoding;
    }

    public List<ContextCalibrator> getContextCalibratorList() {
        return contextCalibratorList;
    }

    public void setContextCalibratorList(List<ContextCalibrator> contextCalibratorList) {
        this.contextCalibratorList = contextCalibratorList;
    }
    
    @Override
    public Set<Parameter> getDependentParameters() {
        if(contextCalibratorList!=null) {
            Set<Parameter> r = new HashSet<>();
            for(ContextCalibrator cc: contextCalibratorList) {
                r.addAll(cc.getContextMatch().getDependentParameters());
            }
            return r;
        } else {
            return Collections.emptySet();
        }
    }
}
