package si.fri.prpo.nakupovalniseznami.api.v1;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;

@DeclareRoles({"user", "admin"})
@OpenAPIDefinition(info = @Info(title = "Rest API", version = "v1",
        contact = @Contact(), license = @License(),
        description = "JavaSI API for managing conference."),
        security = @SecurityRequirement(name = "openid-connect"),
        servers = @Server(url ="http://localhost:8080/v1"))
@CrossOrigin
@ApplicationPath("/v1")
public class NakupovalniSeznamiApplication extends javax.ws.rs.core.Application{

}
