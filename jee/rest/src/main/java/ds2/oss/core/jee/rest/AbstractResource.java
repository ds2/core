package ds2.oss.core.jee.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Produces("application/json")
@Consumes("application/json")
public class AbstractResource<DTO, PKTYPE> {
    @Context
    private UriInfo context;

    @GET
    @Path("/by-id/{id}")
    public DTO getById(@PathParam("id") PKTYPE pktype) {

    }
}
