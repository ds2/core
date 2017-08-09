package ds2.oss.core.api;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dstrauss on 17.07.17.
 */
public enum Mimetypes {
    ApplicationPdf("application/pdf", "pdf");

    private String mimetype;
    private Set<String> extensions;

    Mimetypes(String mimetype, String... exts) {
        this.mimetype = mimetype;
        extensions = new HashSet<>(exts.length);
        for (String e : exts) {
            extensions.add(e.toLowerCase());
        }
    }

    public String getMimetype() {
        return mimetype;
    }

    public Set<String> getExtensions() {
        return Collections.unmodifiableSet(extensions);
    }

    public static Mimetypes getByExtension(String extension) {
        for (Mimetypes mimetypes : values()) {
            if (mimetypes.getExtensions().contains(extension)) {
                return mimetypes;
            }
        }
        return null;
    }
}
