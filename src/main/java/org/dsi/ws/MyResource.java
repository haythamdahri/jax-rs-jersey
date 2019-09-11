package org.dsi.ws;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.dsi.ws.entities.Message;
import org.dsi.ws.entities.User;
import org.dsi.ws.repositories.UserRepository;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {
	
	/*
	 * Inject user reposiotry instance 
	 */
	private UserRepository userRepository = new UserRepository();

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    /*
     * GET endpoint to ping the server 
     */
    @GET
    @Path("/ping")
    public String ping() {
    	return "The server is up!";
    }
    
    /*
     * POST endpoint to send test data 
     */
    @POST
    @Path("/send-data")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Map<String, String> sendName(Message message) {
    	// Data container
    	Map<String, String> data = new HashMap<>();
    	// Put data
    	data.put("name", message.getName());
    	data.put("message", message.getMessage());
    	// Return data
    	return data;
    }
    
    /*
     * GET endpoint to get all users 
     */
    @GET
    @Path("/users")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<User> getUsers() {
    	return this.userRepository.findAll();
    }
    
    /*
     * GET endpoint to get a specific user by id 
     */
    @GET
    @Path("/users/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public User getUser(@PathParam("id") Long id) {
    	return this.userRepository.findById(id);
    }
    
    /*
     * POST endpoint to save or update a user 
     */
    @POST
    @Path("/users")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public User saveUser(User user) {
    	return this.userRepository.save(user);
    }
    
    /*
     * PUT endpoint to save or update a user 
     */
    @PUT
    @Path("/users")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public User saveOrUpdateUser(User user) {
    	return this.userRepository.save(user);
    }
    
    @DELETE
    @Path("/users/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteUser(@PathParam("id") Long id) {
    	return String.valueOf(this.userRepository.deleteById(id));
    }
    
    @GET
    @Path("/send-email")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Map<String, String> sendEmail(@QueryParam("email") String email) {
    	// Data container
    	Map<String, String> data = new HashMap<>();
    	// Set data
    	data.put("status", "OK");
    	data.put("email", email);
    	data.put("message", "An email has been sent to the specified email!");
    	// Return data
    	return data;
    }
       
    
    
    
    
    
    
    
    
}
