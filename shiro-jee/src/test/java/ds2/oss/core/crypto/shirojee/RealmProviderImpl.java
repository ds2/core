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
package ds2.oss.core.crypto.shirojee;

import ds2.oss.core.crypto.shirojee.api.RealmProvider;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.PropertiesRealm;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class RealmProviderImpl implements RealmProvider {
    @Override
    public List<Realm> getRealms() {

        PropertiesRealm realm = new PropertiesRealm();
        realm.setResourcePath("classpath:myUsers.properties");
        realm.onInit();
        MyRealm testRealm = new MyRealm();
        return Arrays.asList(testRealm, realm);
    }
}
