package ds2.oss.core.jee.rest;

import javax.ws.rs.core.Response;

/**
 * Created by dstrauss on 24.03.17.
 */
public interface ExceptionTransformer {
    Response transform(Exception e);
}
