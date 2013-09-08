package ds2.oss.core.api.options;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

import ds2.oss.core.api.NumericEnumValue;

/**
 * Any stage an option can have.
 * 
 * @author dstrauss
 * @version 0.3
 */
@XmlEnum
@XmlType(name = "optionStageType")
public enum OptionStage implements NumericEnumValue {
    /**
     * The option is deleted.
     */
    Deleted(2),
    /**
     * The option is online and can be used.
     */
    Online(1);
    /**
     * The stage id.
     */
    private int stageId;
    
    /**
     * Creates the stage with the given id.
     * 
     * @param i
     *            the numeric id of the stage.
     */
    private OptionStage(final int i) {
        stageId = i;
    }
    
    @Override
    public int getNumericalValue() {
        return stageId;
    }
    
    /**
     * Id lookup.
     * 
     * @param id
     *            the id of the stage
     * @return the stage, or null
     */
    public static OptionStage getById(final int id) {
        for (OptionStage s : values()) {
            if (id == s.stageId) {
                return s;
            }
        }
        return null;
    }
}
