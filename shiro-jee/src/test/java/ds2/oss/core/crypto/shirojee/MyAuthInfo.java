package ds2.oss.core.crypto.shirojee;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

@Setter
@Getter
public class MyAuthInfo implements AuthenticationInfo {
    @Override
    public PrincipalCollection getPrincipals() {
        PrincipalCollection collection=new SimplePrincipalCollection();
        collection.
        return collection;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
