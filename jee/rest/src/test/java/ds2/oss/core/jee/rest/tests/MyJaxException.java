package ds2.oss.core.jee.rest.tests;

import ds2.oss.core.api.IErrorData;
import ds2.oss.core.api.JaxRsClientException;

/**
 * Created by dstrauss on 21.02.17.
 */
public class MyJaxException extends JaxRsClientException {
    private int errorCode;

    public MyJaxException(int ec) {
        super(new IErrorData() {
            @Override
            public int getId() {
                return ec;
            }
        });
        errorCode = ec;
    }

    public MyJaxException(int d, String msg) {
        super(new IErrorData() {
            @Override
            public int getId() {
                return d;
            }
        }, msg);
        errorCode = d;
    }

    public MyJaxException(int d, Throwable t) {
        super(new IErrorData() {
            @Override
            public int getId() {
                return d;
            }
        }, t);
        errorCode = d;
    }
}
