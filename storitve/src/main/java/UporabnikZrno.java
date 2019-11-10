import si.fri.prpo.nakupovalniseznmi.entitete.Uporabnik;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import java.util.List;

@ApplicationScoped
public class UporabnikZrno {
    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
    private EntityManager em;

    public List<Uporabnik> getUporabniki() {

        // implementacija

        return null;
    }
}
