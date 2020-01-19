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
import si.fri.prpo.nakupovalniseznami.entitete.WishList;
//import si.fri.prpo.nakupovalniseznami.zrno.UpravljanjeWishListaZrno;
import si.fri.prpo.nakupovalniseznami.zrno.WishListZrno;

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
@Path("wishlist")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WishListVir{

    @Context
    protected UriInfo uriInfo;

    @Inject
    private WishListZrno wishListZrno;

    //private UpravljanjeWishListaZrno upravljanjeWLZrno;

    @Schema(description = "Vrne seznam zelja.")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Pridobi sezname zelja.", tags = {"wishlist"}, description = "Vrne listo vseh seznamov zelja, ki so shranjeni v bazi.", responses = {
            @ApiResponse(
                    description = "Lista vseh seznamov zelja.",
                    responseCode = "200",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = WishList.class)
                            )),
                    headers = {
                            @Header(name = "X-Total-Count", description = "Stevilo seznamov zelja.")
                    }
            ),
    })
    @GET
    public Response pridobiWishLists() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<WishList> wishlist = wishListZrno.getWishLists(query);
        Long count = wishListZrno.pridobiWishListCount(query);
        return Response.ok(wishlist).header("X-Total-Count", count).build();
    }


    @Schema(description = "Vrne seznam zelja.")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Vrne seznam zelja.", tags = {"wishlist"}, description = "Vrne vse podatke dolocenega seznama zelja.", responses = {
            @ApiResponse(description = "Podatki o seznamu zelja.", responseCode = "200", content = @Content(schema = @Schema(implementation =
                    WishList.class))),
            @ApiResponse(description = "Seznam zelja ne obstaja!", responseCode = "404")
    })
    @GET
    @Path("{id}")
    public Response pridobiWishList(@Parameter(description = "Identifikator seznama zelja.", required = true) @PathParam("id") Integer id) {
        WishList wishList = wishListZrno.pridobiWishList(id);
        if (wishList == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(wishList).build();
    }

    @Schema(description = "Doda seznam zelja.")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Dodajanje seznama zelja.", tags = {"wishlist"}, description = "Ustvari nov seznam zelja s podanimi podatki.", responses = {
            @ApiResponse(description = "Podatki o seznamu zelja.", responseCode = "201", content = @Content(
                    schema = @Schema(implementation = WishList.class),
                    mediaType = "application/json"
            )),
            @ApiResponse(responseCode = "400", description = "Uneseni podatki niso pravilni!")
    })
    @POST
    public Response dodajWishList(@RequestBody(description = "Objekt seznama zelja z vsemi pripadajocimi podatki.", required = true, content = @Content(schema = @Schema(implementation = WishList.class))) WishList wl) {
        wishListZrno.dodajWishList(wl);
        return Response.status(Response.Status.CREATED).build();
    }

    @RolesAllowed({"user", "admin"})
    @Schema(description = "Posodobi seznam zelja.")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Posodobitev podatkov seznama zelja.", tags = {"wishlist"}, description = "Posodobitev podatkov dolocenega seznama zelja", responses = {
            @ApiResponse(description = "Posodobljen seznam zelja.", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = WishList.class)))
    })
    @PUT
    @Path("{id}")
    public Response posodobiWishList(@RequestBody(description = "Posodobljeni objekt seznama zelja z vsemi pripadajocimi podatki.", required = true, content = @Content(schema = @Schema(implementation = WishList.class))) WishList wl) {
        wishListZrno.posodobiWishList(wl);
        return Response.status(Response.Status.OK).build();
    }


    @RolesAllowed("admin")
    @Schema(description = "Izbrise seznam zelja")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Brisanje seznama zelja", tags = {"wishlist"}, description = "Iz baze izbrise podatke dolocenega seznama zelja", responses = {
            @ApiResponse(description = "Seznam zelja izbrisan", responseCode = "204", content = @Content(mediaType = "text/plain"))
    })
    @DELETE
    @Path("{id}")
    public Response odstraniWishList(@Parameter(description = "Identifikator seznama zelja.", required = true) @PathParam("id") Integer id) {
        wishListZrno.odstraniWishList(id);
        return Response.status(Response.Status.GONE).build();
    }
}
