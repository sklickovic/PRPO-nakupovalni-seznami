package si.fri.prpo.nakupovalniseznami.api.v1.viri;


import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;
import si.fri.prpo.nakupovalniseznami.zrno.UporabnikZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("uporabniki")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UporabnikiVir extends Application {


    @Inject
    private UporabnikZrno uporabnikZrno;

    @GET
    public Response pridobiUporabnike() {
        return Response.ok(uporabnikZrno.getUporabniki()).build();
    }

    @GET
    @Path("{id}")
    public Response pridobiUporabnika(@PathParam("id") Integer id) {
        Uporabnik uporabnik = uporabnikZrno.pridobiUporabnika(id);

        if (uporabnik != null) {
            return Response.ok(uporabnik).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response dodajUporabnika(Uporabnik uporabnik) {

        return Response.status(Response.Status.CREATED).entity(uporabnikZrno.dodajUporabnika(uporabnik)).build();
    }

    @PUT
    @Path("{id}")
    public Response posodobiUporabnika(@PathParam("id") Integer id, Uporabnik uporabnik) {

        return Response.status(Response.Status.CREATED).entity(uporabnikZrno.posodobiUporabnika(id, uporabnik)).build();
    }

    @DELETE
    @Path("{id}")
    public Response odstraniUporabnika(@PathParam("id") Integer id) {

        return Response.status(Response.Status.OK).entity(uporabnikZrno.odstraniUporabnika(id)).build();
    }
}
