package si.fri.prpo.nakupovalniseznami.api.v1.viri;


import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.security.annotations.Secure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import si.fri.prpo.nakupovalniseznami.entitete.Kategorija;
import si.fri.prpo.nakupovalniseznami.zrno.KategorijaZrno;

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
@Path("kategorije")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class KategorijaVir {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private KategorijaZrno kategorijaZrno;

    @PermitAll
    @Schema(description = "Returns all categories")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Get categories", tags = {"Category"}, description = "Returns all categories.", responses = {
            @ApiResponse(description = "List of all categories", responseCode = "200", content = @Content(schema = @Schema(implementation =
                    Kategorija.class)))
    })
    @GET
    public Response pridobiKategorije() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Kategorija> kategorija = kategorijaZrno.getKategorije(query);
        Long count = kategorijaZrno.pridobiKategorijeCount(query);
        return Response.ok(kategorija).header("X-Total-Count", count).build();
    }

    @PermitAll
    @Schema(description = "Returns all categories")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Get categories", tags = {"Category"}, description = "Returns all categories.", responses = {
            @ApiResponse(description = "List of all categories", responseCode = "200", content = @Content(schema = @Schema(implementation =
                    Kategorija.class)))
    })
    @GET
    @Path("{id}")
    public Response pridobiKategorijo(@PathParam("id") Integer id) {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        Kategorija kategorija = kategorijaZrno.pridobiKategorijo(id);
        Long count = kategorijaZrno.pridobiKategorijeCount(query);
        return Response.ok(kategorija).header("X-Total-Count", count).build();
    }

    @PermitAll
    @Schema(description = "Add a category")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Add a category", tags = {"Category"}, description = "Adds a category.", responses = {
            @ApiResponse(description = "Add category", responseCode = "201", content = @Content(schema = @Schema(implementation =
                    Kategorija.class)))
    })
    @POST
    public Response dodajKategorijo(Kategorija kat) {
        return Response.status(Response.Status.CREATED).entity(kategorijaZrno.dodajKategorijo(kat)).build();
    }

    @RolesAllowed({"user","admin"})
    @Schema(description = "Update a category")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Update a category", tags = {"Category"}, description = "Update a category by id.", responses = {
            @ApiResponse(description = "Update category by id", responseCode = "200", content = @Content(schema = @Schema(implementation =
                    Kategorija.class)))
    })
    @PUT
    @Path("{id}")
    public Response posodobiKategorijo(@PathParam("id") Integer id, Kategorija kategorija) {
        return Response.status(Response.Status.CREATED).entity(kategorijaZrno.posodobiKategorijo(id, kategorija)).build();
    }

    @RolesAllowed("admin")
    @Schema(description = "Deletes a category")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Delete category", tags = {"Category"}, description = "Deletes a category by id.", responses = {
            @ApiResponse(description = "Delete category", responseCode = "204", content = @Content(schema = @Schema(implementation =
                    Kategorija.class)))
    })
    @DELETE
    @Path("{id}")
    public Response odstraniKategorijo(@PathParam("id") Integer id) {
        return Response.status(Response.Status.OK).entity(kategorijaZrno.odstraniKategorijo(id)).build();
    }
}
