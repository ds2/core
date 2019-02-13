/*
 * Copyright 2019 DS/2 <dstrauss@ds-2.de>
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
