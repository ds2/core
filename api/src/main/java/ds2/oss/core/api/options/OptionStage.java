/*
 * Copyright 2019 DS/2 <dstrauss@ds-2.de>
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
}
