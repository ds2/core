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
        return Arrays.asList(realm);
    }
}
