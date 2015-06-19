package ds2.oss.core.api.dto.impl;

import ds2.oss.core.api.EntryState;

/**
 * Created by dstrauss on 19.06.15.
 */
public class EntryStateDto implements EntryState {
    private int numericalValue;
    private String entryStateName;

    public EntryStateDto() {
    }

    public EntryStateDto(int numericalValue, String entryStateName) {
        this.numericalValue = numericalValue;
        this.entryStateName = entryStateName;
    }

    public void setNumericalValue(int numericalValue) {
        this.numericalValue = numericalValue;
    }

    public void setEntryStateName(String entryStateName) {
        this.entryStateName = entryStateName;
    }

    @Override
    public String getEntryStateName() {
        return entryStateName;
    }

    @Override
    public int getNumericalValue() {
        return numericalValue;
    }
}
