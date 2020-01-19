package si.fri.prpo.nakupovalniseznami.api.v1;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@SecurityScheme(name = "openid-connect", type = SecuritySchemeType.OPENIDCONNECT,
        openIdConnectUrl = "http://auth-server-url/.well-known/openid-configuration")
@OpenAPIDefinition(
        info = @Info(
                title = "Rest API za nakupovalni seznam.",
                version = "v1",
                contact = @Contact(),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"
                ),
                description = "API, ki omogoca enostavno upravljanje z nakupovalnimi seznami."),
        servers = @Server(url ="http://localhost:8080/v1"),
        security = @SecurityRequirement(name = "openid-connect")
)
@ApplicationPath("/v1")
public class NakupovalniSeznamiApplication extends Application {

}
