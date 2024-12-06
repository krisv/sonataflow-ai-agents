package me.sonataflow;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/shipments")
public class Shipments {
	
    private static Map<String, String> shipments;

    static {
        shipments = new HashMap<String, String>();
        shipments.put("FEDEX-002934", "Shipment FEDEX-002934 from Eurodisney to Keerbergen, passing through Fedex hubs in Paris and Liege");
        // shipments.put("FEDEX-003359", "Shipment FEDEX-003359 from Newcastle to Antwerp, passing through Fedex hubs in Stansted and Liege");
        shipments.put("DHL-0032984", "Shipment DHL-0032984 from Amsterdam to Keerbergen, passing through DHL Hub in Antwerp.");
    }

    @Path("/shipment/{identifier}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getShipment(@PathParam("identifier") String identifier) {
        String shipment = shipments.get(identifier);
        return shipment;
    }

    @Path("/shipments")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getShipment() {
        String result = "";
        for (String shipment: shipments.values()) {
            result = result + shipment + "\n";
        }
        return result;
    }

    @Path("/shipment/{identifier}")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void setShipment(@PathParam("identifier") String identifier, String shipment) {
        shipments.put(identifier, shipment);
    }

}