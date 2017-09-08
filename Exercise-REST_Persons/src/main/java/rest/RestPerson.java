/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import converter.JSONConverter;
import entity.Person;
import facade.Facade;
import facade.IPersonFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Alexander
 */
@Path("person")
public class RestPerson {
    IPersonFacade f = new Facade();

    @Context
    private UriInfo context;

    public RestPerson() {
    }
    
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonById(@PathParam("id") int id) {
        Person p = f.getPerson(id);
        String js = JSONConverter.getJSONFromPerson(p);
        return js;
    }
    
    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersons() {
        List<Person> persons = f.getPersons();
        String js = JSONConverter.getJSONFromPerson(persons);
        return js;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    public String postPerson(String content){
        Person p = JSONConverter.getPersonFromJson(content);
        f.addPerson(p);
        String js = JSONConverter.getJSONFromPerson(p);
        return js;
    }
    @Path("{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String putPerson(String content, @PathParam("id") int id) {
        Person p = JSONConverter.getPersonFromJson(content);
        f.editPerson(p);
        String js = JSONConverter.getJSONFromPerson(p);
        return js;
    }
    
    @Path("{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public String deletePerson(@PathParam("id") int id){
        f.deletePerson(id);
        return "Person #" + id + " have been deleted";
    }
}
