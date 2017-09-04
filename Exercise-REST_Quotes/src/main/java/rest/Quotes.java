/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.Quote;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("quote")
public class Quotes {

    // When it's static you don't need to create an instance of the class to access it
    private static Map<Integer, String> quotes = new HashMap() {
        {
            put(1, "Friends are kisses blown to us by angels");
            put(2, "Do not take life too seriously. You will never get out of it alive");
            put(3, "Behind every great man, is a woman rolling her eyes");
        }
    };

    @Context
    private UriInfo context;

    public Quotes() {
    }
    
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getQuoteByID(@PathParam("id") int id) { 
       return quotes.get(id);
    }
    
    @Path("random")
    @GET
    @Produces (MediaType.APPLICATION_JSON)
    public String getQuoteRandom(){
        Random r = new Random();
        int id = r.nextInt(quotes.size()) + 1;
        return quotes.get(id);
    }
    
    @POST
    @Consumes (MediaType.APPLICATION_JSON)
    public String postQuote(String content){
        int id = quotes.size() + 1;
        Quote q = new Gson().fromJson(content, Quote.class);
        System.out.println("q: " + q);
        quotes.put(id, q.getQuote());
        return new Gson().toJson(q);
    }
    
    @Path("{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String putQuote(String content, @PathParam("id") int id) {
        Quote q = new Gson().fromJson(content, Quote.class);
        System.out.println("q: " + q);
        quotes.put(id, q.getQuote());
        return new Gson().toJson(q);
    }
    
    @Path("{id}")
    @DELETE
    @Consumes (MediaType.APPLICATION_JSON)
    public String deleteQuote(@PathParam("id") int id){
        String json = "{\"quotes\":\"" + quotes.get(id) + "\"}";
        System.out.println("Quote  #" + id + " have been reomeved");
        quotes.remove(id);
        return json;
    }
}
