/**
 * 
 */
package ds2.oss.core.api.options;

import java.util.List;

/**
 * A list option identifier.
 * 
 * @author dstrauss
 * @param <E>
 *            the list content type
 * @version 0.3
 * 
 */
public interface ListOptionIdentifier<E> extends OptionIdentifier<List<E>> {
    /**
     * The content type class.
     * 
     * @return the content type class
     */
    Class<E> getContentTypeClass();
}
