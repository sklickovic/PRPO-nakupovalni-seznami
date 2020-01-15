package si.fri.prpo.nakupovalniseznami.api.v1.viri;

//import io.swagger.v3.oas.annotations.Operation;
import si.fri.prpo.nakupovalniseznami.entitete.WishList;
import si.fri.prpo.nakupovalniseznami.zrno.UpravljanjeWishListaZrno;
import si.fri.prpo.nakupovalniseznami.zrno.WishListZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("wishlist")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WishListVir{

    @Inject
    private WishListZrno wishListZrno;
    private UpravljanjeWishListaZrno upravljanjeWLZrno;

    @GET
    public Response pridobiWishLists() {
        return Response.ok(wishListZrno.getWishLists()).build();
    }

    @GET
    public Response pridobiWishListUporabnike() {
        return Response.ok(wishListZrno.getUserList()).build();
    }

    @GET
    @Path("{id}")
    public Response pridobiWishList(@PathParam("id") Integer id) {
        WishList wl = wishListZrno.pridobiWishList(id);

        if (wl != null) {
            return Response.ok(wl).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response dodajWishList(WishList wl) {
        return Response.status(Response.Status.CREATED).entity(wishListZrno.dodajWishList(wl)).build();
    }

    @PUT
    @Path("{id}")
    public Response posodobiWishList(@PathParam("id") Integer id, WishList wl) {
        return Response.status(Response.Status.CREATED).entity(wishListZrno.posodobiWishList(id, wl)).build();
    }

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
