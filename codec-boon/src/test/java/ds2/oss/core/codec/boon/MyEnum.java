/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.codec.boon;

/**
 *
 * @author dstrauss
 */
public enum MyEnum {

    VAL1(1), VAL2(2);
    private int id;

    private MyEnum(int i) {
        id = i;
    }

    public int getId() {
        return id;
    }
}
