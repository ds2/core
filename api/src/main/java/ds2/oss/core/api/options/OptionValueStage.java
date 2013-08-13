package ds2.oss.core.api.options;

/**
 * The several stages of an option value.
 * 
 * @author dstrauss
 * @version 0.3
 */
public enum OptionValueStage {
    /**
     * The value is approved and may switch to LIVE state.
     */
    Approved(2),
    /**
     * The value is deleted and cannot be used anymore.
     */
    Deleted(4),
    /**
     * The value is live and can be used.
     */
    Live(3),
    /**
     * The value is new and prepared and needs an approver to get approved.
     */
    Prepared(1);
    /**
     * The numerical value of the stage.
     */
    private int stageId;
    
    /**
     * Creates the option value stage.
     * 
     * @param stgId
     *            the id of this stage
     */
    private OptionValueStage(final int stgId) {
        stageId = stgId;
    }
    
    /**
     * Returns the numerical id of this stage.
     * 
     * @return the numerical id of this stage
     */
    public int getStageId() {
        return stageId;
    }
}
