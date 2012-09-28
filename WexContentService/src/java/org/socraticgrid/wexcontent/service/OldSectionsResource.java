/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.wexcontent.service;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author Jerry Goodnough
 */

@Path("/sections")
public class OldSectionsResource {
    @Context
    private UriInfo context;

    /** Creates a new instance of OldSectionsResource */
    public OldSectionsResource() {
    }


    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public SectionResource getSectionsResource(@PathParam("id")
    String id) {
        return SectionResource.getInstance(id);
    }
}
