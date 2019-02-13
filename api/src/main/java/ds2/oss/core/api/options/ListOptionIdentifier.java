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

import java.util.List;

/**
 * A list option identifier.
 *
 * @author dstrauss
 * @param <E>
 *            the list content type
 * @version 0.3
 *
 */
public interface ListOptionIdentifier<E> extends OptionIdentifier<List<E>> {
    /**
     * The content type class.
     *
     * @return the content type class
     */
    Class<E> getContentTypeClass();
}
