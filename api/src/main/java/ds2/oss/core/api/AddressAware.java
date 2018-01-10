package ds2.oss.core.api;

public interface AddressAware<T> extends IdAware<T>, PersonalNamedAware {

    String getStreetName();

    String getStreetNumber();

    String getStreetPostfix();

    String getCityZipcode();

    String getCityName();

    T getFederalStateId();

    T getCountryId();
}
