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
import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;
import si.fri.prpo.nakupovalniseznami.zrno.UporabnikZrno;

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
@Path("uporabniki")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UporabnikiVir {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private UporabnikZrno uporabnikZrno;

    @Schema(description = "Vrne uporabnike.")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Pridobi listo uporabnikov.", tags = {"uporabniki"}, description = "Vrne listo vseh uporabnikov, ki so shranjeni v bazi.", responses = {
            @ApiResponse(
                    description = "Lista vseh uprabnikov.",
                    responseCode = "200",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Uporabnik.class)
                            )),
                    headers = {
                            @Header(name = "X-Total-Count", description = "Stevilo uporabnikov.")
                    }
            ),
    })
    @GET
    public Response pridobiUporabnike() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Uporabnik> uporabniki = uporabnikZrno.getUporabniki(query);
        Long count = uporabnikZrno.pridobiUporabnikeCount(query);
        return Response.ok(uporabniki).header("X-Total-Count", count).build();
    }

    @Schema(description = "Vrne uporabnika.")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Vrne uporabnika.", tags = {"uporabniki"}, description = "Vrne vse podatke dolocenega uporabnika.", responses = {
            @ApiResponse(description = "Podatki uporabnika.", responseCode = "200", content = @Content(schema = @Schema(implementation =
                    Uporabnik.class))),
            @ApiResponse(description = "Uporabnik ne obstaja!", responseCode = "404")
    })
    @GET
    @Path("{id}")
    public Response pridobiUporabnika(@Parameter(description = "Identifikator uporabnika.", required = true) @PathParam("id") Integer id) {
        Uporabnik uporabnik = uporabnikZrno.pridobiUporabnika(id);
        if (uporabnik == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(uporabnik).build();
    }

    @Schema(description = "Doda uporabnika.")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Dodajanje uporabnika.", tags = {"uporabniki"}, description = "Ustvari novega uporabnika s podanimi podatki.", responses = {
            @ApiResponse(description = "Podatki uporabnika.", responseCode = "201", content = @Content(
                    schema = @Schema(implementation = Uporabnik.class),
                    mediaType = "application/json"
            )),
            @ApiResponse(responseCode = "400", description = "Uneseni podatki niso pravilni!")
    })
    @POST
    public Response dodajUporabnika(@RequestBody(description = "Objekt uporabnika z vsemi pripadajocimi podatki.", required = true, content = @Content(schema = @Schema(implementation = Uporabnik.class))) Uporabnik uporabnik) {
        uporabnikZrno.dodajUporabnika(uporabnik);
        return Response.status(Response.Status.CREATED).build();
    }

    @RolesAllowed({"user", "admin"})
    @Schema(description = "Posodobi uporabnika.")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Posodobitev podatkov uporabnika.", tags = {"uporabniki"}, description = "Posodobitev podatkov dolocenega uporabnika", responses = {
            @ApiResponse(description = "Posodobljen uporabnik.", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Uporabnik.class)))
    })
    @PUT
    @Path("{id}")
    public Response posodobiUporabnika(@RequestBody(description = "Posodobljeni objekt uporabnika z vsemi pripadajocimi podatki.", required = true, content = @Content(schema = @Schema(implementation = Uporabnik.class))) Uporabnik uporabnik) {
        uporabnikZrno.posodobiUporabnika(uporabnik);
        return Response.status(Response.Status.OK).build();
    }

    @RolesAllowed("admin")
    @Schema(description = "Izbrise uporabnika")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Brisanje uporabnika", tags = {"uporabniki"}, description = "Iz baze izbrise podatke dolocenega uporabnika", responses = {
            @ApiResponse(description = "Uporabnik izbrisan", responseCode = "204", content = @Content(mediaType = "text/plain"))
    })
    @DELETE
    @Path("{id}")
    public Response odstraniUporabnika(@Parameter(description = "Identifikator uporabnika.", required = true) @PathParam("id") Integer id) {
        uporabnikZrno.odstraniUporabnika(id);
        return Response.status(Response.Status.GONE).build();
    }
}
