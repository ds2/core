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
package ds2.oss.core.interceptors.test;

import javax.ejb.Stateless;

/**
 * A simple test bean.
 * 
 * @author dstrauss
 * @version 0.1
 */
@Stateless
public class SimpleBean {
    
    /**
     * Inits the bean.
     */
    public SimpleBean() {
        // nothing special to do
    }
    
    /**
     * Performs a simple string concat.
     * 
     * @param a
     *            the first number
     * @param b
     *            the second number
     * @return the string concat.
     */
    public String getSomething(final int a, final int b) {
        return "Hi, " + a + "+" + b;
    }
    
}
