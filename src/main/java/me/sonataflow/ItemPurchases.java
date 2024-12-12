package me.sonataflow;

import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/purchases")
public class ItemPurchases {
	
    private static Map<String, String> purchases;
    // private static Map<String, String> purchaseHistory;

    static {
        purchases = new HashMap<String, String>();
        purchases.put("ITEM-0001", "Item purchase by John Mayor for a lightbulb for 3.99 EUR. Fully paid. Expected delivery date Nov 25, 2024. Shipment via Fedex with identifier FEDEX-002934.");
        purchases.put("ITEM-0002", "Item purchase by Frank Defour for a TV for 299 EUR. Payment pending.");
        purchases.put("ITEM-0003", "Item purchase by Elisa Li for an automatic garden robot for 499 EUR. Fully paid. Expected delivery date Nov 19, 2024. Shipment via DHL with identifier DHL-0032984.");
        // purchaseHistory = new HashMap<String, String>();
        // purchaseHistory.put("Kris Verlaenen", 
        //     "Item purchase for a lightbulb for 3.99 EUR. Delivered on time on Oct 14, 2024, 2 days late.\n" +
        //     "Item purchase for a lightbulb for 3.99 EUR. Delivered on time on Sep 14, 2024, 3 days late\n" +
        //     "Item purchase for a lightbulb for 3.99 EUR. Delivered on time on Aug 14, 2024, on time.\n" +
        //     "Item purchase for a lightbulb for 3.99 EUR. Delivered on time on Jul 14, 2024, on time.\n" +
        //     "Item purchase for a lightbulb for 3.99 EUR. Delivered on time on Jun 14, 2024, on time.\n" +
        //     "Item purchase for a lightbulb for 3.99 EUR. Delivered on time on May 14, 2024, on time.\n" +
        //     "Item purchase for a lightbulb for 3.99 EUR. Delivered on time on Apr 14, 2024, on time.\n" +
        //     "Item purchase for a lightbulb for 3.99 EUR. Delivered on time on Mar 14, 2024, 2 days late.\n" +
        //     "Item purchase for a lightbulb for 3.99 EUR. Delivered on time on Feb 14, 2024, 3 days late.\n" +
        //     "Item purchase for a lightbulb for 3.99 EUR. Delivered on time on Jan 14, 2024, 5 days late.\n" +
        //     "Item purchase for a lightbulb for 3.99 EUR. Delivered on time on Dec 14, 2024, 4 days late."
        //     );
    }

    @Path("/purchase/{identifier}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getPurchase(@PathParam("identifier") String identifier) {
        String purchase = purchases.get(identifier);
        return purchase;
    }

    @Path("/purchase/{identifier}")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void setPurchase(@PathParam("identifier") String identifier, String purchase) {
        purchases.put(identifier, purchase);
    }

    @Path("/purchases")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getPurchases() {
        String result = "";
        for (String purchase: purchases.values()) {
            result = result + purchase + "\n";
        }
        return result;
    }

    // @Path("/customer/{identifier}")
    // @GET
    // @Produces(MediaType.TEXT_PLAIN)
    // public String getPurchaseHistory(@PathParam("identifier") String identifier) {
    //     return purchaseHistory.get(identifier);
    // }

}