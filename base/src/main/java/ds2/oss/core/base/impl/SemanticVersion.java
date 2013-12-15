package ds2.oss.core.base.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ds2.oss.core.api.ISemanticVersion;
import ds2.oss.core.api.Version;

/**
 * The implementation for a semantic version.
 * 
 * @author dstrauss
 * @version 0.3
 */
public final class SemanticVersion implements ISemanticVersion {
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 6788282684302911892L;
    /**
     * Numeric pattern.
     */
    private static final Pattern NUMPATTERN = Pattern.compile("[0-9]+");
    /**
     * A sequence of three numbers, separated by a dot.
     */
    private static final Pattern TRIPLE = Pattern
        .compile(NUMPATTERN.pattern() + "(\\." + NUMPATTERN.pattern() + "){2}");
    /**
     * An alphanumeric sequence.
     */
    private static final Pattern ALPHANUMERIC = Pattern.compile("[A-Za-z0-9\\-]+");
    /**
     * Extension for a prerelease header.
     */
    private static final Pattern EXT_PREREL = Pattern.compile("\\-" + ALPHANUMERIC.pattern() + "(\\."
        + ALPHANUMERIC.pattern() + ")*");
    /**
     * Extension for a build header.
     */
    private static final Pattern EXT_BUILD = Pattern.compile("\\+[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*");
    /**
     * Regular pattern for a semantic version.
     */
    private static final Pattern PATTERN_SEMVER = Pattern.compile(TRIPLE.pattern() + "(" + EXT_PREREL.pattern() + ")?("
        + EXT_BUILD + ")?");
    /**
     * The major number.
     */
    private int majorNumber;
    /**
     * The minor number.
     */
    private int minorNumber;
    /**
     * The patch number.
     */
    private int patchNumber;
    /**
     * The prerelease data.
     */
    private List<String> preReleaseData;
    /**
     * The builds data.
     */
    private List<String> buildData;
    
    /**
     * Inits the object.
     */
    private SemanticVersion() {
        preReleaseData = new ArrayList<>();
        buildData = new ArrayList<>();
    }
    
    /**
     * Internal initialisation for the semantic version.
     * 
     * @param major
     *            the major number
     * @param minor
     *            the minor number
     * @param patch
     *            the patch number
     * @param preRelData
     *            the prerelease data
     * @param buildDataList
     *            the build data
     */
    private SemanticVersion(final long major, final long minor, final long patch, final List<String> preRelData,
        final List<String> buildDataList) {
        this();
        majorNumber = (int) major;
        minorNumber = (int) minor;
        patchNumber = (int) patch;
        if (preRelData != null) {
            preReleaseData.addAll(preRelData);
        }
        if (buildDataList != null) {
            buildData.addAll(buildDataList);
        }
    }
    
    @Override
    public int compareTo(final Version o) {
        int rc = 0;
        if (!(o instanceof ISemanticVersion)) {
            throw new IllegalArgumentException("Given version to compare with is not a semantic version!");
        }
        final ISemanticVersion v2 = (SemanticVersion) o;
        rc =
            compareThree(majorNumber, v2.getMajorNumber(), minorNumber, v2.getMinorNumber(), patchNumber,
                v2.getPatchNumber());
        if (rc == 0) {
            rc = compareLists(buildData, v2.getBuildDataList());
        }
        return rc;
    }
    
    /**
     * Compares two lists.
     * 
     * @param list1
     *            the first list
     * @param list2
     *            the second list
     * @return the result
     */
    private static int compareLists(final List<String> list1, final List<String> list2) {
        if ((list1 == null) && (list2 == null)) {
            return 0;
        }
        if (list1 == null) {
            return -1;
        }
        if (list2 == null) {
            return 1;
        }
        final int maxItems = Math.min(list1.size(), list2.size());
        int rc = 0;
        for (int i = 0; i < maxItems; i++) {
            final String item1 = list1.get(i);
            final String item2 = list2.get(i);
            int diff = 0;
            if (isNumeric(item1) && isNumeric(item2)) {
                final int item1L = Tools.toInt(item1, 0);
                final int item2L = Tools.toInt(item2, 0);
                diff = item1L - item2L;
            } else {
                diff = item1.compareTo(item2);
            }
            if (diff != 0) {
                rc = diff;
                break;
            }
        }
        return rc;
    }
    
    private static int compareInt(final int i1, final int i2) {
        return i1 - i2;
    }
    
    private static int compareThree(final int m1, final int m2, final int min1, final int min2, final int p1,
        final int p2) {
        int rc = 0;
        final int a1 = compareInt(m1, m2);
        if (a1 > 0) {
            rc = 0x2;
        } else if (a1 == 0) {
            rc = 0x0;
        } else if (a1 < 0) {
            rc = 0x1;
        }
        return rc;
    }
    
    private static boolean isNumeric(final String s) {
        if ((s != null) && (s.length() > 0)) {
            try {
                Long.parseLong(s);
            } catch (final NumberFormatException e) {
                return false;
            }
            return true;
        }
        return false;
    }
    
    /**
     * Parses a given string into a semantic version object.
     * 
     * @param s
     *            the string to parse
     * @return the version object
     */
    public static SemanticVersion parse(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("No version given to parse!");
        }
        final Matcher m = PATTERN_SEMVER.matcher(s);
        if (!m.find()) {
            throw new IllegalArgumentException("Given string is not considered a semantic version!");
        }
        final String foundPart = m.group();
        final SemanticVersion rc = new SemanticVersion();
        final String mainNumPart = parseFromString(TRIPLE, foundPart);
        final Matcher mainNumMatcher = NUMPATTERN.matcher(mainNumPart);
        mainNumMatcher.find();
        rc.majorNumber = Integer.parseInt(mainNumMatcher.group());
        mainNumMatcher.find();
        rc.minorNumber = Integer.parseInt(mainNumMatcher.group());
        mainNumMatcher.find();
        rc.patchNumber = Integer.parseInt(mainNumMatcher.group());
        final Matcher prereleaseMatcher = EXT_PREREL.matcher(foundPart);
        if (prereleaseMatcher.find()) {
            final String[] preData = prereleaseMatcher.group().substring(1).split("\\.");
            if (preData != null) {
                for (String preDataItem : preData) {
                    rc.preReleaseData.add(preDataItem);
                }
            }
        }
        return rc;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(majorNumber).append(".").append(minorNumber).append(".").append(patchNumber);
        if (!preReleaseData.isEmpty()) {
            sb.append("-");
            boolean isFirst = true;
            for (String preItem : preReleaseData) {
                if (!isFirst) {
                    sb.append(".");
                }
                sb.append(preItem);
                isFirst = false;
            }
        }
        return sb.toString();
    }
    
    private static String parseFromString(final Pattern triple2, final String foundPart) {
        final Matcher m = triple2.matcher(foundPart);
        if (m.find()) {
            return m.group();
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((buildData == null) ? 0 : buildData.hashCode());
        result = (prime * result) + majorNumber;
        result = (prime * result) + minorNumber;
        result = (prime * result) + patchNumber;
        result = (prime * result) + ((preReleaseData == null) ? 0 : preReleaseData.hashCode());
        return result;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof SemanticVersion)) {
            return false;
        }
        final SemanticVersion other = (SemanticVersion) obj;
        if (buildData == null) {
            if (other.buildData != null) {
                return false;
            }
        } else if (!buildData.equals(other.buildData)) {
            return false;
        }
        if (majorNumber != other.majorNumber) {
            return false;
        }
        if (minorNumber != other.minorNumber) {
            return false;
        }
        if (patchNumber != other.patchNumber) {
            return false;
        }
        if (preReleaseData == null) {
            if (other.preReleaseData != null) {
                return false;
            }
        } else if (!preReleaseData.equals(other.preReleaseData)) {
            return false;
        }
        return true;
    }
    
    /**
     * Returns the major version number.
     * 
     * @return major version number
     */
    @Override
    public int getMajorNumber() {
        return majorNumber;
    }
    
    /**
     * Returns the minor version number.
     * 
     * @return the minorNumber
     */
    @Override
    public int getMinorNumber() {
        return minorNumber;
    }
    
    /**
     * Returns the patch version number.
     * 
     * @return the patchNumber
     */
    @Override
    public int getPatchNumber() {
        return patchNumber;
    }
    
    @Override
    public void incrementMajorNumber() {
        majorNumber++;
        minorNumber = 0;
        patchNumber = 0;
    }
    
    @Override
    public void incrementMinorNumber() {
        minorNumber++;
        patchNumber = 0;
    }
    
    @Override
    public void incrementPatchNumber() {
        patchNumber++;
    }
    
    @Override
    public List<String> getPreReleaseDataList() {
        return Collections.unmodifiableList(preReleaseData);
    }
    
    @Override
    public List<String> getBuildDataList() {
        return Collections.unmodifiableList(buildData);
    }
    
}
