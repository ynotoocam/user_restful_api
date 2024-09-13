/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restful.resources;

import com.restful.model.Users;
import com.restful.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import static jakarta.ws.rs.core.Response.status;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 *
 * @author macoo
 */
@Path("/users")
public class UserResource {

    private UserService dao = UserService.getInstance(); // Get the singleton instance of UserDAO

    /**
     * Handles HTTP GET requests to list all users.
     *
     * @param limit
     * @param offset
     * @return a list of all users in JSON format.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Get All Users",
            description = "Retrieves all users from database",
            parameters = {
                @Parameter(name = "limit", description = "This is the number of records that will be retrieved", required = false, example = "0"),
                @Parameter(name = "offset", description = "This will be the starting point of records", required = false, example = "0")
            }
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successfully retrieved list"),
        @ApiResponse(responseCode = "404", description = "unsuccessfully retrieved list")
    })
    public Response list(
            @QueryParam("limit") @DefaultValue("10") int limit,
            @QueryParam("offset") @DefaultValue("0") int offset) {
        List<Users> users = dao.listAll(limit, offset);
        return Response.ok(users).build();
    }

    /**
     * Handles HTTP GET requests to retrieve a specific user by ID.
     *
     * @param id the ID of the user to retrieve.
     * @return the user object in JSON format if found, or a 404 Not Found
     * response if not.
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Get a single user by id",
            description = "Retrieves one user from database using their id",
            parameters = {
                @Parameter(name = "id", description = "this is the id number of the user", required = true, example = "12"),}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successfully retrieved user"),
        @ApiResponse(responseCode = "404", description = "unsuccessfully retrieved user")
    })
    public Response get(@PathParam("id") int id) {
        Users user = dao.get(id); // Retrieve the user by ID
        if (user != null) {
            return Response.ok(user, MediaType.APPLICATION_JSON).build(); // Return the user if found
        } else {
            return Response.status(Response.Status.NOT_FOUND).build(); // Return 404 if the user is not found
        }
    }

    /**
     * Handles HTTP POST requests to add a new user.
     *
     * @param user the user object to add.
     * @return a 201 Created response with the URI of the new user.
     * @throws URISyntaxException if the URI syntax is incorrect.
     */
    // Adds a new comment
    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Adds a single Comment by id",
            description = "Adda a new Comment to the database using their id",
            parameters = {
                @Parameter(name = "id", description = "this is the id number of the Comment", required = true, example = "12"),}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comment successfully added "),
        @ApiResponse(responseCode = "404", description = "Comment was not unsuccessfully added")
    })
    public Response add(Users user) throws URISyntaxException {
        int newCommentId = dao.add(user);
        URI uri = new URI("/comments/" + newCommentId);
        return Response.created(uri).build();
    }

    /**
     * Handles HTTP PUT requests to update an existing user.
     *
     * @param id the ID of the user to update.
     * @param user the user object with updated information.
     * @return a 200 OK response if the user was updated, or a 304 Not Modified
     * response if not.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{id}")
    @Operation(
            summary = "Updates a single user by id",
            description = "Updates a new user to the database using their id",
            parameters = {
                @Parameter(name = "id", description = "this is the id number of the user", required = true, example = "12"),}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User successfully Updated "),
        @ApiResponse(responseCode = "404", description = "User was not unsuccessfully Updated")
    })
    public Response update(@PathParam("id") int id, Users user) {
        user.setId(id); // Set the ID to the user object
        if (dao.update(user)) {
            return Response.ok().build(); // Return 200 OK if the update was successful
        } else {
            return Response.notModified().build(); // Return 304 Not Modified if the update failed
        }
    }

    /**
     * Handles HTTP DELETE requests to remove a user by ID.
     *
     * @param id the ID of the user to delete.
     * @return a 204 No Content response if the user was deleted, or a 304 Not
     * Modified response if not.
     */
    @DELETE
    @Path("/delete/{id}")
    @Operation(
            summary = "Deletes a single user by id",
            description = "Deletes a  user from the database using their id",
            parameters = {
                @Parameter(name = "id", description = "this is the id number of the user", required = true, example = "12"),}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User Deleted successfully  "),
        @ApiResponse(responseCode = "404", description = "User was not unsuccessfully deleted")
    })
    public Response delete(@PathParam("id") int id) {
        if (dao.delete(id)) {
            return Response.noContent().build(); // Return 204 No Content if the deletion was successful
        } else {
            return Response.notModified().build(); // Return 304 Not Modified if the deletion failed
        }
    }
}
