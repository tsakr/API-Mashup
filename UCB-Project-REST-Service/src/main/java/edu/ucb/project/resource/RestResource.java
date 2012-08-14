package edu.ucb.project.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * @author Tamer Sakr (tamer.sakr@ucsf.edu)
 * 
 */
@Path("/QueryService")
@Produces("application/xml")
public class RestResource {

	/**
	 * matches request of this form [../UCB/Berkeley.CA]
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("{query}")
	public Response processQuery(@PathParam("query") String query) throws Exception {
		return Response.status(200).entity("query sent for processing...").build();
	}

	/**
	 * matches request of this form [../UCB/city/Berkeley/state/CA]
	 * 
	 * @param city
	 * @param state
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/city/{city}/state/{state}")
	public Response getCityState(@PathParam("city") String city, @PathParam("state") String state) throws Exception {
		return Response.status(200).entity("query sent for processing...").build();
	}

}
