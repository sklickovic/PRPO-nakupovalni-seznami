package si.fri.prpo.nakupovalniseznami.api.v1.viri;


import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.security.annotations.Secure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import si.fri.prpo.nakupovalniseznami.entitete.WishList;
import si.fri.prpo.nakupovalniseznami.zrno.UpravljanjeWishListaZrno;
import si.fri.prpo.nakupovalniseznami.zrno.WishListZrno;

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
@Path("wishlist")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WishListVir{

    @Context
    protected UriInfo uriInfo;

    @Inject
    private WishListZrno wishListZrno;
    private UpravljanjeWishListaZrno upravljanjeWLZrno;

    @PermitAll
    @Schema(description = "Returns all wish lists")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Get wish lists", tags = {"WishList"}, description = "Returns all wish lists.", responses = {
            @ApiResponse(description = "List of all wish lists", responseCode = "200", content = @Content(schema = @Schema(implementation =
                    WishList.class)))
    })
    @GET
    public Response pridobiWishLists() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<WishList> wishlist = wishListZrno.getWishLists(query);
        Long count = wishListZrno.pridobiWishListCount(query);
        return Response.ok(wishlist).header("X-Total-Count", count).build();
    }


    @PermitAll
    @Schema(description = "Returns all wish lists")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Get wish lists", tags = {"WishList"}, description = "Returns all wish lists.", responses = {
            @ApiResponse(description = "List of all wish lists", responseCode = "200", content = @Content(schema = @Schema(implementation =
                    WishList.class)))
    })
    @GET
    @Path("{id}")
    public Response pridobiWishList(@PathParam("id") Integer id) {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        WishList wl = wishListZrno.pridobiWishList(id);
        Long count = wishListZrno.pridobiWishListCount(query);
        return Response.ok(wl).header("X-Total-Count", count).build();
    }

    @PermitAll
    @Schema(description = "Add a wish list")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Add a wish list", tags = {"WishList"}, description = "Adds a wish list.", responses = {
            @ApiResponse(description = "Add wish list", responseCode = "201", content = @Content(schema = @Schema(implementation =
                    WishList.class)))
    })
    @POST
    public Response dodajWishList(WishList wl) {
        return Response.status(Response.Status.CREATED).entity(wishListZrno.dodajWishList(wl)).build();
    }

    @RolesAllowed({"user","admin"})
    @Schema(description = "Update a wish list")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Update a wish list", tags = {"WishList"}, description = "Update a wish list by id.", responses = {
            @ApiResponse(description = "Update wish list by id", responseCode = "200", content = @Content(schema = @Schema(implementation =
                    WishList.class)))
    })
    @PUT
    @Path("{id}")
    public Response posodobiWishList(@PathParam("id") Integer id, WishList wl) {
        return Response.status(Response.Status.CREATED).entity(wishListZrno.posodobiWishList(id, wl)).build();
    }


    @RolesAllowed("admin")
    @Schema(description = "Deletes a wish list")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Delete wish list", tags = {"WishList"}, description = "Deletes a wish list by id.", responses = {
            @ApiResponse(description = "Delete wish list", responseCode = "204", content = @Content(schema = @Schema(implementation =
                    WishList.class)))
    })
    @DELETE
    @Path("{id}")
    public Response odstraniWishList(@PathParam("id") Integer id) {
        return Response.status(Response.Status.OK).entity(wishListZrno.odstraniWishList(id)).build();
    }

    /*
    @PUT
    @Path("{id}")

    @Operation(
            summary = "Sort items on wishlist - cheapest first",
            tags = "Sorting wishlist items",
            responses = {
                    @ApiResponse(
                            description = "Sorting wishlist",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = WishList.class)
                            )
                    ),
                    @Apiresponse(responseCode = "400", description = "An error has occured! Please check if you have any items on your wishlist!")

            },
            description = "Sort items on given wishlist - cheapest first"
    )
     */

}
