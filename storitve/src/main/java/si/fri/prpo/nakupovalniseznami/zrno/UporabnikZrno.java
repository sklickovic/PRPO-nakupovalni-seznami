package si.fri.prpo.nakupovalniseznami.zrno;

import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class UporabnikZrno {

    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")

    private EntityManager em;

    private Logger log = Logger.getLogger(UporabnikZrno.class.getName());

    @PostConstruct
    private void init() {
        log.info("Inicializacija zrna: " + UporabnikZrno.class.getSimpleName());
    }

    @PreDestroy
    private void destroy() {
        log.info("Uničenje zrna: " + UporabnikZrno.class.getSimpleName());
    }

    public List<Uporabnik> getUporabniki() {

        List<Uporabnik> uporabniki = em.createNamedQuery("Uporabnik.getAll").getResultList();

        return uporabniki;
    }

    public List<Uporabnik> getPetras() {
        List<Uporabnik> petras = em.createNamedQuery("Uporabnik.getPetras").getResultList();

        return petras;
    }

    @Transactional
    public Uporabnik pridobiUporabnika(int id) {
        Uporabnik user = em.find(Uporabnik.class, id);

        return user;
    }

    @Transactional
    public Uporabnik dodajUporabnika(Uporabnik u) {
        if (u != null) {
            em.persist(u);
        }

        return u;
    }

    @Transactional
    public Uporabnik posodobiUporabnika(int id, Uporabnik uporabnik) {
        Uporabnik user = em.find(Uporabnik.class, id);

        user.setId(user.getId());
        em.merge(uporabnik);

        return uporabnik;
    }

    @Transactional
    public int odstraniUporabnika(int id) {
        Uporabnik user = em.find(Uporabnik.class, id);

        if (user != null) {
            em.remove(user);
        }

        return id;
    }


}
