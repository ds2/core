/*
 * Copyright 2012-2015 Dirk Strauss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ds2.oss.core.api.options;

/**
 * The several stages of an option value.
 *
 * @author dstrauss
 * @version 0.3
 */
public enum OptionValueStage {
    /**
     * The value is approved and may switch to LIVE state depending on its lifecycle.
     */
    Approved(2),
    /**
     * The value is deleted and cannot be used anymore.
     */
    Deleted(5),
    /**
     * The value has expired.
     */
    Expired(4),
    /**
     * The value is live and can be used.
     */
    Live(3),
    /**
     * The value is new and prepared and needs an approver to get approved.
     */
    Prepared(1);
    public static OptionValueStage getById(final int i) {
        for (OptionValueStage s : values()) {
            if (s.stageId == i) {
                return s;
            }
        }
        return null;
    }

    /**
     * The numerical value of the stage.
     */
    private final int stageId;

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
