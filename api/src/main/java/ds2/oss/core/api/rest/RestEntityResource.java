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
package ds2.oss.core.api.rest;

import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.SearchCriteria;
import ds2.oss.core.api.dto.impl.PagedResult;

import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;

/**
 * Sample contract for a rest entity resource. Saying, to reflect database records.
 *
 * @param <DTO>    the dto to send and receive
 * @param <IDTYPE> the type of id to identify a single entity on this server
 */
public interface RestEntityResource<DTO, IDTYPE> {
    @GET
    @Path("/by-id/{id}")
    DTO getyId(@PathParam("id") IDTYPE id, @Context HttpHeaders headers, @Context SecurityContext sc) throws CoreException;

    @PUT
    @Path("/")
    DTO persist(DTO dto, @Context HttpHeaders headers, @Context SecurityContext sc) throws CoreException;

    @POST
    @Path("/by-id/{id}")
    DTO update(@PathParam("id") IDTYPE id, DTO dto, @Context HttpHeaders headers, @Context SecurityContext sc) throws CoreException;

    @DELETE
    @Path("/by-id/{id}")
    void delete(@PathParam("id") IDTYPE idtype, @Context HttpHeaders headers, @Context SecurityContext sc) throws CoreException;

    @Path("/search")
    @POST
    PagedResult<DTO> search(@QueryParam("start") @Min(0) int offset, @QueryParam("size") @Min(1) int size, SearchCriteria searchCriteria) throws CoreException;
}
