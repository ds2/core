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
package ds2.oss.core.options.impl.encryption;

import java.nio.charset.Charset;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import ds2.oss.core.api.AppServerSecurityBaseDataService;
import ds2.oss.core.api.crypto.Ciphers;
import ds2.oss.core.api.crypto.EncodedContent;
import ds2.oss.core.api.crypto.EncryptionService;
import ds2.oss.core.api.options.ForValueType;
import ds2.oss.core.api.options.ValueType;
import ds2.oss.core.options.api.OptionValueEncrypter;

/**
 * The string option value encrypter.
 * 
 * @author dstrauss
 * @version 0.3
 *
 */
@ForValueType(ValueType.STRING)
@ApplicationScoped
@Alternative
@Priority(100)
public class StringOptionValueEncrypter implements OptionValueEncrypter<String> {
    /**
     * The utf8 charset.
     */
    private static final Charset UTF8 = Charset.forName("utf-8");
    /**
     * The encryption service.
     */
    @Inject
    private EncryptionService encSvc;
    /**
     * The sec data service.
     */
    @Inject
    private AppServerSecurityBaseDataService secSvc;
    
    @Override
    public EncodedContent encrypt(final String s) {
        return encSvc.encode(secSvc.getAppserverSecretKey(), Ciphers.AES, s.getBytes(UTF8));
    }
    
    @Override
    public String decrypt(final EncodedContent s) {
        final byte[] bytes = encSvc.decode(secSvc.getAppserverSecretKey(), Ciphers.AES, s);
        final String rc = new String(bytes, UTF8);
        return rc;
    }
    
}
