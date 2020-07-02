/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
