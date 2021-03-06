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
package ds2.oss.core.api.options;

/**
 * A journal service to tell when something has changed.
 *
 * @author dstrauss
 * @version 0.3
 */
public interface OptionServiceJournal {
    /**
     * Adds an entry.
     *
     * @param invoker
     *            the invoker username, if available. Otherwise set to null.
     * @param action
     *            the action
     * @param affectedId
     *            the affected id
     * @param oldVal
     *            the old value
     * @param newVal
     *            the new value
     */
    <D, K> void addEntry(String invoker, JournalAction action, K affectedId, D oldVal, D newVal);

    /**
     * Convenient method to add a journal entry on creating an option.
     *
     * @param option
     *            the option that has been created
     */
    void createdOption(Option<?, ?> option);
}
