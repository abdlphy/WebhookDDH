package org.github.webhook;


import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;


import java.util.List;

import org.github.webhook.model.User;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @GET
    public List<User> getAllUsers() {
        return User.listAll();
    }

    @GET
    @Path("/{id}")
    public User getUser(@PathParam("id") Long id) {
        return User.findById(id);
    }

    @POST
    @Transactional
    public User createUser(User user) {
        user.persist();
        return user;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public User updateUser(@PathParam("id") Long id, User userData) {
        User user = User.findById(id);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        user.name = userData.name;
        user.email = userData.email;
        return user;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deleteUser(@PathParam("id") Long id) {
        User user = User.findById(id);
        if (user != null) {
            user.delete();
        }
    }
}
