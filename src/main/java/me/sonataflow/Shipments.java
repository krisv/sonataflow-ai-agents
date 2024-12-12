package me.sonataflow;

import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;

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
    public String getShipment(@PathParam("identifier") String identifier) {
        String shipment = shipments.get(identifier);
        return shipment;
    }

    @Path("/shipments")
    @GET
    public String getShipment() {
        String result = "";
        for (String shipment: shipments.values()) {
            result = result + shipment + "\n";
        }
        return result;
    }

    @Path("/shipment/{identifier}")
    @POST
    public void setShipment(@PathParam("identifier") String identifier, String shipment) {
        shipments.put(identifier, shipment);
    }

}