/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restful.resources;

import com.restful.service.CommentService;
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
import net.codejava.ws.Comment;

/**
 *
 * @author macoo
 */
@Path("/comments")
public class CommentResource {

    private CommentService dao = CommentService.getInstance();

    // Retrieves the list of all comments
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Get All Comments",
            description = "Retrieves all Comments from database",
            parameters = {
                @Parameter(name = "limit", description = "This is the number of records that will be retrieved", required = false, example = "0"),
                @Parameter(name = "offset", description = "This will be the starting point of records", required = false, example = "0")
            }
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successfully retrieved Comments list"),
        @ApiResponse(responseCode = "404", description = "unsuccessfully retrieved Comments list")
    })
    public Response list(
            @QueryParam("limit") @DefaultValue("10") int limit,
            @QueryParam("offset") @DefaultValue("0") int offset) {
        List<Comment> comments = dao.listAll(limit, offset);
        return Response.ok(comments).build();
    }

    // Retrieves a specific comment by ID
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Get a single Comment by id",
            description = "Retrieves one Comment from database using their id",
            parameters = {
                @Parameter(name = "id", description = "this is the id number of the Comment", required = true, example = "12"),}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successfully retrieved Comment"),
        @ApiResponse(responseCode = "404", description = "unsuccessfully retrieved Comment")
    })
    public Response get(@PathParam("id") int id) {
        Comment comment = dao.get(id);
        if (comment != null) {
            return Response.ok(comment, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

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
    public Response add(Comment comment) throws URISyntaxException {
        int newCommentId = dao.add(comment);
        URI uri = new URI("/comments/" + newCommentId);
        return Response.created(uri).build();
    }

    // Updates an existing comment by ID
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{id}")
    @Operation(
            summary = "Updates a single Comment by id",
            description = "Updates a new Comment to the database using their id",
            parameters = {
                @Parameter(name = "id", description = "this is the id number of the Comment", required = true, example = "12"),}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comment successfully Updated "),
        @ApiResponse(responseCode = "404", description = "Comment was not unsuccessfully Updated")
    })
    public Response update(@PathParam("id") int id, Comment comment) {
        comment.setId(id);
        if (dao.update(comment)) {
            return Response.ok().build();
        } else {
            return Response.notModified().build();
        }
    }

    // Deletes a comment by ID
    @DELETE
    @Path("/delete/{id}")
    @Operation(
            summary = "Deletes a single Comment by id",
            description = "Deletes a  Comment from the database using their id",
            parameters = {
                @Parameter(name = "id", description = "this is the id number of the Comment", required = true, example = "12"),}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comment Deleted successfully  "),
        @ApiResponse(responseCode = "404", description = "Comment was not unsuccessfully deleted")
    })
    public Response delete(@PathParam("id") int id) {
        if (dao.delete(id)) {
            return Response.noContent().build();
        } else {
            return Response.notModified().build();
        }
    }

}
