package si.fri.prpo.nakupovalniseznami.zrno;

<<<<<<< HEAD
import si.fri.prpo.nakupovalniseznami.entitete.Izdelki;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;
=======
import javax.enterprise.context.ApplicationScoped;
>>>>>>> 4c9d90f1c5e832930c6f44b8c1c28ed2f8cce9ab

@ApplicationScoped
public class IzdelkiZrno {

<<<<<<< HEAD
    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")

    private EntityManager em;

    private Logger log = Logger.getLogger(IzdelkiZrno.class.getName());

    @PostConstruct
    private void init() {
        log.info("Inicializacija zrna: " + IzdelkiZrno.class.getSimpleName());
    }

    @PreDestroy
    private void destroy() {
        log.info("Uničenje zrna: " + IzdelkiZrno.class.getSimpleName());
    }

    public List<Izdelki> getIzdelki() {

        List<Izdelki> izdelki = em.createNamedQuery("Izdelki.getAll").getResultList();

        return izdelki;
    }

    @Transactional
    public Izdelki pridobiIzdelek(int id) {
        Izdelki i = em.find(Izdelki.class, id);

        return i;
    }

    @Transactional
    public Izdelki dodajIzdelek(Izdelki i) {
        if (i != null) {
            em.persist(i);
        }

        return i;
    }

    @Transactional
    public void posodobiIzdelek(int id, Izdelki i) {
        Izdelki izdelek = em.find(Izdelki.class, id);

        i.setIdIzdelka(izdelek.getIdIzdelka());
        em.merge(i);
    }

    @Transactional
    public int odstraniIzdelek(int id) {

        Izdelki izdelek = em.find(Izdelki.class, id);

        if (izdelek != null) {
            em.remove(izdelek);
        }

        return id;
    }
}
