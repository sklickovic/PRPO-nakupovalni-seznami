package si.fri.prpo.nakupovalniseznami.api.v1.viri;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@ApplicationScoped
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse (NotFoundException e){
        Error error = new Error();
        error.setStatus(404);
        error.setMessage(e.getMessage());
        Jsonb jsonb = JsonbBuilder.create();
        String result = jsonb.toJson(error);
        return Response.status(Response.Status.NOT_FOUND).entity(result).build();
    }
}
