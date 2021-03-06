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
package ds2.oss.core.elasticsearch.tests;

import ds2.core.testonly.utils.AbstractInjectionEnvironment;
import ds2.oss.core.elasticsearch.api.ElasticSearchNode;
import org.testng.annotations.BeforeClass;

/**
 * The integration test.
 *
 * @author dstrauss
 * @version 0.2
 */
public class EsIT extends AbstractInjectionEnvironment {
    /**
     * The node.
     */
    private ElasticSearchNode esNode;

    @BeforeClass
    public void onClass() {
        esNode = getInstance(ElasticSearchNode.class);
    }

}
