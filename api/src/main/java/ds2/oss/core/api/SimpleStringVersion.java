package ds2.oss.core.api;

/**
 * Created by dstrauss on 27.03.17.
 */
public class SimpleStringVersion implements Version {
    private String versionString = "";

    public SimpleStringVersion() {

    }

    public SimpleStringVersion(String s) {
        this();
        versionString = s;
    }

    @Override
    public int getMajorNumber() {
        return 0;
    }

    @Override
    public int getMinorNumber() {
        return 0;
    }

    @Override
    public int getPatchNumber() {
        return 0;
    }

    @Override
    public int compareTo(Version o) {
        return versionString.compareTo(o.toString());
    }

    @Override
    public String toString() {
        return versionString;
    }

    public String getVersionString() {
        return versionString;
    }

    public void setVersionString(String versionString) {
        this.versionString = versionString;
    }
}
