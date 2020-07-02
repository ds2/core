/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
