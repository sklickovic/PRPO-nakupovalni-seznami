package si.fri.prpo.nakupovalniseznami.api.v1.viri;

import si.fri.prpo.nakupovalniseznami.entitete.WishList;
import si.fri.prpo.nakupovalniseznami.zrno.WishListZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
@Path("wishlist")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WishListVir{

    @Context
    protected UriInfo uriInfo;

    @Inject
    private WishListZrno wishListZrno;

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
}
