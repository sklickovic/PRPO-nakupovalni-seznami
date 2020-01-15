package si.fri.prpo.nakupovalniseznami.api.v1.viri;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.QueryStringDefaults;
import com.kumuluz.ee.security.annotations.Secure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import si.fri.prpo.nakupovalniseznami.entitete.Izdelki;
import si.fri.prpo.nakupovalniseznami.zrno.IzdelkiZrno;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Secure
@ApplicationScoped
@Path("izdelki")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IzdelkiVir {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private IzdelkiZrno izdelkiZrno;

    @Default
    private QueryStringDefaults qsd;

    @PermitAll
    @Schema(description = "Returns all articles")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Get articles list", tags = {"article"}, description = "Returns a list of articles.", responses = {
            @ApiResponse(description = "List of articles", responseCode = "200", content = @Content(schema = @Schema(implementation =
                    Izdelki.class)))
    })
    @GET
    public Response vrniIzdelke(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Izdelki> artikli = izdelkiZrno.getArtikli(query);
        Long count = izdelkiZrno.pridobiIzdelkeCount(query);
        return Response.ok(artikli).header("X-Total-Count", count).build();
    }


    @PermitAll
    @Schema(description = "Returns a articel by id")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Get articles list", tags = {"article"}, description = "Returns a list of articles.", responses = {
            @ApiResponse(description = "List of articles", responseCode = "200", content = @Content(schema = @Schema(implementation =
                    Izdelki.class)))
    })
    @GET
    @Path("{id}")
    public Response pridobiIzdelek(@PathParam("id") Integer id) {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        Izdelki izdelek = (Izdelki) izdelkiZrno.pridobiIzdelek(id);
        Long count = izdelkiZrno.pridobiIzdelkeCount(query);
        return Response.ok(izdelek).header("X-Total-Count", count).build();
    }


    @PermitAll
    @Schema(description = "Add a articles")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Add article", tags = {"article"}, description = "Adds an article.", responses = {
            @ApiResponse(description = "Add article", responseCode = "201", content = @Content(schema = @Schema(implementation =
                    Izdelki.class)))
    })
    @POST
    public Response dodajIzdelek(Izdelki izdelek) {
        return Response.status(Response.Status.CREATED).entity(izdelkiZrno.dodajIzdelek(izdelek)).build();
    }

    @RolesAllowed({"user","admin"})
    @Schema(description = "Update an article")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Update an article", tags = {"article"}, description = "Update an article by id.", responses = {
            @ApiResponse(description = "Update an article", responseCode = "200", content = @Content(schema = @Schema(implementation =
                    Izdelki.class)))
    })
    @PUT
    @Path("{id}")
    public Response posodobiIzdelek(@PathParam("id") Integer id, Izdelki izdelek) {
        return Response.status(Response.Status.CREATED).entity(izdelkiZrno.posodobiIzdelek(id, izdelek)).build();
    }


    @RolesAllowed("admin")
    @Schema(description = "Deletes an article")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Delete article", tags = {"article"}, description = "Deletes an article by id.", responses = {
            @ApiResponse(description = "Delete article", responseCode = "204", content = @Content(schema = @Schema(implementation =
                    Izdelki.class)))
    })
    @DELETE
    @Path("{id}")
    public Response odstraniIzdelek(@PathParam("id") Integer id) {
        return Response.status(Response.Status.OK).entity(izdelkiZrno.odstraniIzdelek(id)).build();
    }
}
