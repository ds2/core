/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.codec.boon;

import java.net.URL;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author dstrauss
 */
public class Complex1 {

    private int number;
    private String msg;
    private URL homepage;
    private Date created;
    private Set<Complex1> childs;
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
