package si.fri.prpo.nakupovalniseznami.api.v1.viri;

import si.fri.prpo.nakupovalniseznami.entitete.Izdelki;
import si.fri.prpo.nakupovalniseznami.zrno.IzdelkiZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("izdelki")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IzdelkiVir extends Application {


    @Inject
    private IzdelkiZrno izdelkiZrno;

    @GET
    @Path("{id}")
    public Response pridobiIzdelek(@PathParam("id") Integer id) {
        return Response.ok(izdelkiZrno.pridobiIzdelek(id)).build();
    }

    @POST
    public Response dodajIzdelek(Izdelki izdelek) {
        return Response.status(Response.Status.CREATED).entity(izdelkiZrno.dodajIzdelek(izdelek)).build();
    }

    @PUT
    @Path("{id}")
    public Response posodobiIzdelek(@PathParam("id") Integer id, Izdelki izdelek) {
        return Response.status(Response.Status.CREATED).entity(izdelkiZrno.posodobiIzdelek(id, izdelek)).build();
    }

    @DELETE
    @Path("{id}")
    public Response odstraniIzdelek(@PathParam("id") Integer id) {
        return Response.status(Response.Status.OK).entity(izdelkiZrno.odstraniIzdelek(id)).build();
    }
}
