package ds2.oss.core.base.impl;

import ds2.oss.core.api.Version;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The implementation for a semantic version.
 *
 * @author dstrauss
 * @version 0.3
 */
public class SemanticVersion implements Version {
  /**
   * Numeric pattern.
   */
  private static final Pattern NUMPATTERN = Pattern.compile("[0-9]+");
  private static final Pattern TRIPLE = Pattern.compile(NUMPATTERN.pattern() + "(\\." + NUMPATTERN.pattern() + "){2}");
  private static final Pattern PATTERN_PREREL = Pattern.compile("\\-[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*");
  private static final Pattern PATTERN_BUILD = Pattern.compile("\\+[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*");
  private static final Pattern PATTERN_SEMVER = Pattern.compile(TRIPLE.pattern() + "(" + PATTERN_PREREL.pattern() + ")?(" + PATTERN_BUILD + ")?");
  private int major;
  private int minor;
  private int patch;
  private List<String> preReleaseData;
  private List<String> buildData;

  private SemanticVersion() {

  }

  private SemanticVersion(long major, long minor, long patch, List<String> preRelData, List<String> buildData) {
    this();
  }

  @Override
  public int compareTo(Version o) {
    return 0;
  }

  public static SemanticVersion parse(String s) {
    if (s == null) {
      throw new IllegalArgumentException("No version given to parse!");
    }
    Matcher m = PATTERN_SEMVER.matcher(s);
    if (!m.find()) {
      throw new IllegalArgumentException("Given string is not considered a semantic version!");
    }
    SemanticVersion rc = new SemanticVersion();
    return rc;
  }

}
