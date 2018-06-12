package ds2.oss.core.api;

/**
 * Basic contract to convert enum values to and from a transport description.
 *
 * @author dstrauss
 * @version 0.3
 * @param <A>
 *            the enum type
 * @param <B>
 *            the streaming type
 */
public interface EnumConverter<A extends Enum<A>, B> extends Codec<A, B>{

}
