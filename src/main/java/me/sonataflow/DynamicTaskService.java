package me.sonataflow;

import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/executeFunction")
public class DynamicTaskService {
	
    @Path("/{name}")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String executeFunction(@PathParam("name") String name, String parameters) {
        System.out.println("Execution function " + name + " with parameters " + parameters);
        // TODO use dynamic task
        return "Shipment DHL-0032984 was delayed due to a strike at the shipment center.  Expected 2 days of delay for all packages.";
    }

}