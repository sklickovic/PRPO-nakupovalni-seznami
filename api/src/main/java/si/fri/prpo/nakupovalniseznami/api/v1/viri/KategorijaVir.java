package si.fri.prpo.nakupovalniseznami.api.v1.viri;


import com.kumuluz.ee.rest.beans.QueryParameters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import si.fri.prpo.nakupovalniseznami.entitete.Kategorija;
import si.fri.prpo.nakupovalniseznami.zrno.KategorijaZrno;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
@Path("kategorije")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class KategorijaVir {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private KategorijaZrno kategorijaZrno;

    @Schema(description = "Vrne kategorije.")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Pridobi listo kategorij.", tags = {"kategorije"}, description = "Vrne listo vseh kategorij, ki so shranjene v bazi.", responses = {
            @ApiResponse(
                    description = "Lista vseh kategorij.",
                    responseCode = "200",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Kategorija.class)
                            )),
                    headers = {
                            @Header(name = "X-Total-Count", description = "Stevilo kategorij.")
                    }
            ),
    })
    @GET
    public Response pridobiKategorije() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Kategorija> kategorija = kategorijaZrno.getKategorije(query);
        Long count = kategorijaZrno.pridobiKategorijeCount(query);
        return Response.ok(kategorija).header("X-Total-Count", count).build();
    }

    @Schema(description = "Vrne kategorijo.")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Vrne kategorijo.", tags = {"kategorije"}, description = "Vrne vse podatke dolocene kategorije.", responses = {
            @ApiResponse(description = "Podatki o kategoriji.", responseCode = "200", content = @Content(schema = @Schema(implementation =
                    Kategorija.class))),
            @ApiResponse(description = "Kategorija ne obstaja!", responseCode = "404")
    })
    @GET
    @Path("{id}")
    public Response pridobiKategorijo(@Parameter(description = "Identifikator kategorije.", required = true) @PathParam("id") Integer id) {
        Kategorija kategorija = kategorijaZrno.pridobiKategorijo(id);
        if (kategorija == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(kategorija).build();
    }

    @Schema(description = "Doda kategorijo.")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Dodajanje kategorije.", tags = {"kategorije"}, description = "Ustvari novo kategorijo s podanimi podatki.", responses = {
            @ApiResponse(description = "Podatki o kategoriji.", responseCode = "201", content = @Content(
                    schema = @Schema(implementation = Kategorija.class),
                    mediaType = "application/json"
            )),
            @ApiResponse(responseCode = "400", description = "Uneseni podatki niso pravilni!")
    })
    @POST
    public Response dodajKategorijo(@RequestBody(description = "Objekt kategorije z vsemi pripadajocimi podatki.", required = true, content = @Content(schema = @Schema(implementation = Kategorija.class))) Kategorija kat) {
        kategorijaZrno.dodajKategorijo(kat);
        return Response.status(Response.Status.CREATED).build();
    }

    @RolesAllowed({"user", "admin"})
    @Schema(description = "Posodobi kategorijo.")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Posodobitev podatkov kategorije.", tags = {"kategorije"}, description = "Posodobitev podatkov dolocene kategorije", responses = {
            @ApiResponse(description = "Posodobljena kategorija.", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Kategorija.class)))
    })
    @PUT
    @Path("{id}")
    public Response posodobiKategorijo(@RequestBody(description = "Posodobljeni objekt kategorije z vsemi pripadajocimi podatki.", required = true, content = @Content(schema = @Schema(implementation = Kategorija.class))) Kategorija kategorija) {
        kategorijaZrno.posodobiKategorijo(kategorija);
        return Response.status(Response.Status.OK).build();
    }

    @RolesAllowed("admin")
    @Schema(description = "Izbrise kategorijo")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Brisanje kategorije", tags = {"kategorije"}, description = "Iz baze izbrise podatke dolocene kategorije", responses = {
            @ApiResponse(description = "Kategorija izbrisana", responseCode = "204", content = @Content(mediaType = "text/plain"))
    })
    @DELETE
    @Path("{id}")
    public Response odstraniKategorijo(@Parameter(description = "Identifikator kategorije.", required = true) @PathParam("id") Integer id) {
        kategorijaZrno.odstraniKategorijo(id);
        return Response.status(Response.Status.GONE).build();
    }
}
