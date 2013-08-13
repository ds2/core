package ds2.oss.core.api.options;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * Any stage an option can have.
 * 
 * @author dstrauss
 * @version 0.3
 */
@XmlEnum
@XmlType(name = "optionStageType")
public enum OptionStage {
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
    
    /**
     * Returns the stage id for this stage.
     * 
     * @return the stage id
     */
    public int getStageId() {
        return stageId;
    }
}
