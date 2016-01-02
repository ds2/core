package ds2.oss.core.statics;

import java.util.Collection;

/**
 * Created by deindesign on 21.12.15.
 */
public interface Tools2 {
    public static <E,V> E getNullOrElementFromCollection(Collection<E> c, E def){
        E rc=def;
        if(c!=null){
            rc=c.iterator().next();
        }
        return rc;
    }
}
