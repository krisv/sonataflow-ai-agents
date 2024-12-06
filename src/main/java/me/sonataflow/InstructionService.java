package me.sonataflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/instruction")
public class InstructionService {
	
    private static Map<String, String> responses;
    private static Map<String, List<String>> feedbacks;

    static {
        responses = new HashMap<String, String>();
        responses.put("Check Late Delivery", 
            "When checking for late delivery, always do the following:\n" +
            "1. Check if payment has already been done, if not recommend user to check payment.\n" +
            "2. Check if the expected delivery date has already passed, if not recommend user to be patient.");
        feedbacks = new HashMap<String, List<String>>();
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getInstruction(String topic) {
        return responses.get(topic);
    }

    @Path("/{topic}")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void setInstruction(@PathParam("topic") String topic, String instruction) {
        responses.put(topic, instruction);
    }

    @Path("/{topic}/feedback")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void setFeedback(@PathParam("topic") String topic, String feedback) {
        System.out.println("Adding feedback for " + topic + ": " + feedback);
        List<String> feedbackList = feedbacks.get(topic);
        if (feedbackList == null) {
            feedbackList = new ArrayList<String>();
            feedbacks.put(topic, feedbackList);
        }
        feedbackList.add(feedback);
    }

    @Path("/{topic}/feedback")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getFeedback(@PathParam("topic") String topic) {
        String result = "";
        List<String> feedbackList = feedbacks.get(topic);
        if (feedbackList == null) {
            return result;
        }
        for (String feedback: feedbackList)  {
            result = result + feedback + "\n";
        }
        feedbacks.put(topic, null);
        return result;
    }

}