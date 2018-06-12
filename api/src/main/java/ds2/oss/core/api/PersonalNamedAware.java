package ds2.oss.core.api;

public interface PersonalNamedAware {
    String getTitle();

    String getFirstName();

    String getMiddleName();

    String getSureName();

    /**
     * Like Jr., Sr. etc.
     *
     * @return
     */
    String getNameAppendix();
}
