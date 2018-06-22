/*
 * Copyright 2018 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.codec.jackson2;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URL;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author dstrauss
 */
public class Complex1 {
    @JsonProperty("num")
    private int number;
    private String msg;
    private URL homepage;
    private Date created;
    private Set<Complex1> childs;
    @JsonProperty("this_state_is")
    private MyEnum state;

    public MyEnum getState() {
        return state;
    }

    public void setState(MyEnum state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Complex1{number=").append(number).append(", msg=").append(msg).append(", homepage=").append(homepage).append(", created=").append(created).append(", childs=").append(childs).append('}').toString();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public URL getHomepage() {
        return homepage;
    }

    public void setHomepage(URL homepage) {
        this.homepage = homepage;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Set<Complex1> getChilds() {
        return childs;
    }

    public void setChilds(Set<Complex1> childs) {
        this.childs = childs;
    }
}
