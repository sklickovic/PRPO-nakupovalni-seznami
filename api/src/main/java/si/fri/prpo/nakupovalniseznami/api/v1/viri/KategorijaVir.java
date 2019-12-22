package si.fri.prpo.nakupovalniseznami.api.v1.viri;

import si.fri.prpo.nakupovalniseznami.entitete.Kategorija;
import si.fri.prpo.nakupovalniseznami.zrno.KategorijaZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("kategorije")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class KategorijaVir extends Application {

    @Inject
    private KategorijaZrno kategorijaZrno;

    @GET
    public Response pridobiKategorije() {
        return Response.ok(kategorijaZrno.getKategorije()).build();
    }

    @PathParam("{id}")
    @GET
    public Response pridobiKategorijo(@PathParam("id") Integer id) {
        Kategorija kategorija = kategorijaZrno.pridobiKategorijo(id);

        if (kategorija != null) {
            return Response.ok(kategorija).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response dodajKategorijo(Kategorija kat) {
        return Response.status(Response.Status.CREATED).entity(kategorijaZrno.dodajKategorijo(kat)).build();
    }

    @PUT
    @PathParam("{id}")
    public Response posodobiKategorijo(@PathParam("id") Integer id, Kategorija kategorija) {
        return Response.status(Response.Status.CREATED).entity(kategorijaZrno.posodobiKategorijo(id, kategorija)).build();
    }

    @DELETE
    @PathParam("{id}")
    public Response odstraniKategorijo(@PathParam("id") Integer id) {
        return Response.status(Response.Status.OK).entity(kategorijaZrno.odstraniKategorijo(id)).build();
    }
}
