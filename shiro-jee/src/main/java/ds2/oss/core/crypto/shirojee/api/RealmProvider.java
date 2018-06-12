package ds2.oss.core.crypto.shirojee.api;

import org.apache.shiro.realm.Realm;

import java.util.List;

public interface RealmProvider {
    List<Realm> getRealms();
}
