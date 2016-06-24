package ds2.oss.core.api;

/**
 * Known values for the dateTime converter. Use {@link #name()}.toLowerCase().
 */
public enum TimeDateStyle {
    DEFAULT, SHORT, MEDIUM, LONG, FULL;

    public String getLowerCase(){
        return name().toLowerCase();
    }
}
