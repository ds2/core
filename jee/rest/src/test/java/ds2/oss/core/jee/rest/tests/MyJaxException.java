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
package ds2.oss.core.jee.rest.tests;

import ds2.oss.core.api.IErrorData;
import ds2.oss.core.api.JaxRsClientException;

/**
 * Created by dstrauss on 21.02.17.
 */
public class MyJaxException extends JaxRsClientException {
    private int errorCode;

    public MyJaxException(int ec) {
        super(new IErrorData() {
            @Override
            public int getId() {
                return ec;
            }
        });
        errorCode = ec;
    }

    public MyJaxException(int d, String msg) {
        super(new IErrorData() {
            @Override
            public int getId() {
                return d;
            }
        }, msg);
        errorCode = d;
    }

    public MyJaxException(int d, Throwable t) {
        super(new IErrorData() {
            @Override
            public int getId() {
                return d;
            }
        }, t);
        errorCode = d;
    }
}
