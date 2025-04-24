package com.socialnetwork.rest;

import com.socialnetwork.ejb.interfaces.UserService;
import com.socialnetwork.model.User;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserResource {

    @EJB
    private UserService userService;

    @POST
    @Path("/register")
    @Consumes("application/json")
    public Response registerUser(User user) {
        User registeredUser = userService.registerUser(user.getName(), user.getEmail(), user.getPassword());
        return Response.status(Response.Status.CREATED).entity(registeredUser).build();
    }

    @POST
    @Path("/login")
    @Consumes("application/json")
    @Produces("application/json")
    public Response loginUser(User user) {
        User loggedInUser = userService.loginUser(user.getEmail(), user.getPassword());
        return Response.ok(loggedInUser).build();
    }

    @PUT
    @Path("/{id}/profile")
    @Consumes("application/json")
    public Response updateProfile(@PathParam("id") Long userId, User user) {
        userService.updateProfile(userId, user.getName(), user.getBio(), user.getPassword());
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getUser(@PathParam("id") Long userId) {
        User user = userService.getUserById(userId);
        return Response.ok(user).build();
    }
}
