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
/**
 * 
 */
package ds2.oss.core.elasticsearch.api;

import ds2.oss.core.api.IErrorData;

/**
 * @author dstrauss
 *
 */
public enum ElasticSearchErrors implements IErrorData {
    /**
     * If the put operation failed.
     */
    PutFailed(IErrorData.SERVICE_ERROR + 1);
    /**
     * the error id.
     */
    private int id;
    
    /**
     * 
     * Inits the error with the given error id.
     * 
     * @param id
     *            the error id
     */
    private ElasticSearchErrors(int id) {
        this.id = id;
    }
    
    @Override
    public int getId() {
        return id;
    }
    
}
