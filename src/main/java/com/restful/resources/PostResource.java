/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restful.resources;

import com.restful.service.PostService;
import com.restful.model.Post;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 *
 * @author macoo
 */
@Path("/posts")
public class PostResource {

    private PostService dao = PostService.getInstance();

    // Retrieves the list of all posts
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Get All Posts",
            description = "Retrieves all Posts from database",
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
        List<Post> posts = dao.listAll(limit, offset);
        return Response.ok(posts).build();
    }

    // Retrieves a specific post by ID
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Gets a single Post by id",
            description = "Retrieves one Post from database using their id",
            parameters = {
                @Parameter(name = "id", description = "this is the id number of the Post", required = true, example = "12"),}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successfully retrieved Post"),
        @ApiResponse(responseCode = "404", description = "unsuccessfully retrieved Post")
    })
    public Response get(@PathParam("id") int id) {
        Post post = dao.get(id);
        if (post != null) {
            return Response.ok(post, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Adds a new post
    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Adds a single Post by id",
            description = "Adds a new Post to the database using their id",
            parameters = {
                @Parameter(name = "id", description = "this is the id number of the Post", required = true, example = "12"),}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Post successfully added "),
        @ApiResponse(responseCode = "404", description = "Post was not unsuccessfully added")
    })
    public Response add(Post post) throws URISyntaxException {
        int newPostId = dao.add(post);
        URI uri = new URI("/posts/" + newPostId);
        return Response.created(uri).build();
    }

    // Updates an existing post by ID
    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Updates a single Post by id",
            description = "Updates a new Post to the database using their id",
            parameters = {
                @Parameter(name = "id", description = "this is the id number of the Post", required = true, example = "12"),}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Post successfully Updated "),
        @ApiResponse(responseCode = "404", description = "Post was not unsuccessfully Updated")
    })
    public Response update(@PathParam("id") int id, Post post) {
        post.setId(id);
        if (dao.update(post)) {
            return Response.ok().build();
        } else {
            return Response.notModified().build();
        }
    }

    // Deletes a post by ID
    @DELETE
    @Path("/delete/{id}")
    @Operation(
            summary = "Deletes a single Post by id",
            description = "Deletes a Post from the database using their id",
            parameters = {
                @Parameter(name = "id", description = "this is the id number of the Post", required = true, example = "12"),}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Post Deleted successfully  "),
        @ApiResponse(responseCode = "404", description = "Post was not unsuccessfully deleted")
    })
    public Response delete(@PathParam("id") int id) {
        if (dao.delete(id)) {
            return Response.noContent().build();
        } else {
            return Response.notModified().build();
        }
    }

}
