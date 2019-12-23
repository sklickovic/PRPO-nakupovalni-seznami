package si.fri.prpo.nakupovalniseznami.api.v1.viri;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.security.annotations.Secure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;
import si.fri.prpo.nakupovalniseznami.zrno.UporabnikZrno;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Secure
@ApplicationScoped
@Path("uporabniki")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UporabnikiVir{

    @Context
    protected UriInfo uriInfo;

    @Inject
    private UporabnikZrno uporabnikZrno;

    @PermitAll
    @Schema(description = "Returns all users")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Get customers list", tags = {"customers"}, description = "Returns a list of users.", responses = {
            @ApiResponse(description = "List of users", responseCode = "200", content = @Content(schema = @Schema(implementation =
                    Uporabnik.class)))
    })
    @GET
    public Response pridobiUporabnike() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Uporabnik> uporabniki = uporabnikZrno.pridobiUporabnike(query);
        Long count = uporabnikZrno.pridobiUporabnikeCount(query);
        return Response.ok(uporabniki).header("X-Total-Count", count).build();
    }

    @PermitAll
    @Schema(description = "Returns a user")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Get a customer", tags = {"customers"}, description = "Returns a user.", responses = {
            @ApiResponse(description = "User", responseCode = "200", content = @Content(schema = @Schema(implementation =
                    Uporabnik.class)))
    })
    @GET
    @Path("{id}")
    public Response pridobiUporabnika(@PathParam("id") Integer id) {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        Uporabnik uporabnik = uporabnikZrno.pridobiUporabnika(id);
        Long count = uporabnikZrno.pridobiUporabnikeCount(query);
        return Response.ok(uporabnik).header("X-Total-Count", count).build();
    }

    @PermitAll
    @Schema(description = "Add a user")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Add user", tags = {"customers"}, description = "Adds a user.", responses = {
            @ApiResponse(description = "Add user", responseCode = "201", content = @Content(schema = @Schema(implementation =
                    Uporabnik.class)))
    })
    @POST
    public Response dodajUporabnika(Uporabnik uporabnik) {

        return Response.status(Response.Status.CREATED).entity(uporabnikZrno.dodajUporabnika(uporabnik)).build();
    }

    @RolesAllowed({"user","admin"})
    @Schema(description = "Update a user")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Update a user", tags = {"customers"}, description = "Update a user by id.", responses = {
            @ApiResponse(description = "Update a users", responseCode = "200", content = @Content(schema = @Schema(implementation =
                    Uporabnik.class)))
    })
    @PUT
    @Path("{id}")
    public Response posodobiUporabnika(@PathParam("id") Integer id, Uporabnik uporabnik) {

        return Response.status(Response.Status.CREATED).entity(uporabnikZrno.posodobiUporabnika(id, uporabnik)).build();
    }

    @RolesAllowed("admin")
    @Schema(description = "Deletes a user")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Delete user", tags = {"customers"}, description = "Deletes a user by id.", responses = {
            @ApiResponse(description = "Delete user", responseCode = "204", content = @Content(schema = @Schema(implementation =
                    Uporabnik.class)))
    })
    @DELETE
    @Path("{id}")
    public Response odstraniUporabnika(@PathParam("id") Integer id) {

        return Response.status(Response.Status.OK).entity(uporabnikZrno.odstraniUporabnika(id)).build();
    }
}
