/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.api;

/**
 *
 * @author dstrauss
 */
public enum CoreErrors implements IErrorData {

    /**
     * If json encoding failed.
     */
    JSON_ENCODING_FAILED(1), JSON_DECODING_FAILED(2);
    /**
     * The error code.
     */
    private final int code;

    private CoreErrors(int i) {
        code = i;
    }

    @Override
    public int getId() {
        return code;
    }

}
