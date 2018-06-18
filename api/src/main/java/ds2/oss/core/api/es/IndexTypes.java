/*
 * Copyright 2018 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.api.es;

/**
 * The index types.
 *
 * @author dstrauss
 * @version 0.2
 */
public enum IndexTypes {
    /**
     * Perform a full analyze on field value. Given field is broken into tokens and analyzed.
     */
    ANALYZED("analyzed"),
    /**
     * Do not analyze this field. The field will not be searchable.
     */
    NO("no"),
    /**
     * Use full match index for the field values. Field is searchable but not tokenized.
     */
    NOT_ANALYZED("not_analyzed");

    /**
     * The type name.
     */
    private String typeName;

    /**
     * Inits the enum value.
     *
     * @param s
     *            the ES string for this index type
     */
    private IndexTypes(final String s) {
        typeName = s;
    }

    /**
     * Returns the ES index type name.
     *
     * @return the index type name from ES
     */
    public String getTypeName() {
        return typeName;
    }
}
