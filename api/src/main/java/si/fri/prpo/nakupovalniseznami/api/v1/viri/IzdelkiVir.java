package si.fri.prpo.nakupovalniseznami.api.v1.viri;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.QueryStringDefaults;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import si.fri.prpo.nakupovalniseznami.entitete.Izdelki;
import si.fri.prpo.nakupovalniseznami.zrno.IzdelkiZrno;

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

    @Schema(description = "Vrne izdelke.")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Pridobi listo izdelkov.", tags = {"izdelki"}, description = "Vrne listo vseh izdelkov, ki so shranjeni v bazi.", responses = {
            @ApiResponse(
                    description = "Lista vseh izdelkov.",
                    responseCode = "200",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Izdelki.class)
                            )),
                    headers = {
                            @Header(name = "X-Total-Count", description = "Stevilo izdelkov.")
                    }
            ),
    })
    @GET
    public Response vrniIzdelke(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Izdelki> izdelki = izdelkiZrno.getIzdelki(query);
        Long count = izdelkiZrno.pridobiIzdelkeCount(query);
        return Response.ok(izdelki).header("X-Total-Count", count).build();
    }


    @Schema(description = "Vrne izdelek.")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Vrne izdelek.", tags = {"izdelki"}, description = "Vrne vse podatke dolocenega izdelka.", responses = {
            @ApiResponse(description = "Podatki o izdelku.", responseCode = "200", content = @Content(schema = @Schema(implementation =
                    Izdelki.class))),
            @ApiResponse(description = "Izdelek ne obstaja!", responseCode = "404")
    })
    @GET
    @Path("{id}")
    public Response pridobiIzdelek(@Parameter(description = "Identifikator izdelka.", required = true) @PathParam("id") Integer id) {
        Izdelki izdelek = izdelkiZrno.pridobiIzdelek(id);
        if (izdelek == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(izdelek).build();
    }


    @Schema(description = "Doda izdelek.")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Dodajanje izdelka.", tags = {"izdelki"}, description = "Ustvari nov izdelek s podanimi podatki.", responses = {
            @ApiResponse(description = "Podatki o izdelku.", responseCode = "201", content = @Content(
                    schema = @Schema(implementation = Izdelki.class),
                    mediaType = "application/json"
            )),
            @ApiResponse(responseCode = "400", description = "Uneseni podatki niso pravilni!")
    })
    @POST
    public Response dodajIzdelek(@RequestBody(description = "Objekt izdelka z vsemi pripadajocimi podatki.", required = true, content = @Content(schema = @Schema(implementation = Izdelki.class))) Izdelki izdelek) {
        izdelkiZrno.dodajIzdelek(izdelek);
        return Response.status(Response.Status.CREATED).build();
    }

    @Schema(description = "Posodobi izdelek.")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Posodobitev podatkov izdelka.", tags = {"izdelki"}, description = "Posodobitev podatkov dolocenega izdelka", responses = {
            @ApiResponse(description = "Posodobljen izdelek.", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Izdelki.class)))
    })
    @PUT
    @Path("{id}")
    public Response posodobiIzdelek(@RequestBody(description = "Posodobljeni objekt izdelka z vsemi pripadajocimi podatki.", required = true, content = @Content(schema = @Schema(implementation = Izdelki.class))) Izdelki izdelek) {
        izdelkiZrno.posodobiIzdelek(izdelek);
        return Response.status(Response.Status.OK).build();
    }


    @Schema(description = "Izbrise izdelek")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Brisanje izdelka", tags = {"izdelki"}, description = "Iz baze izbrise podatke dolocenega izdelka", responses = {
            @ApiResponse(description = "Izdelek izbrisan", responseCode = "204", content = @Content(schema = @Schema(implementation = Izdelki.class)))
    })
    @DELETE
    @Path("{id}")
    public Response odstraniIzdelek(@Parameter(description = "Identifikator izdelka.", required = true) @PathParam("id") Integer id) {
        izdelkiZrno.odstraniIzdelek(id);
        return Response.status(Response.Status.GONE).build();
    }
}
