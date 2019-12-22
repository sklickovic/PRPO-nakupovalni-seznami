package si.fri.prpo.nakupovalniseznami.zrno;

import si.fri.prpo.nakupovalniseznami.anotacije.BeleziKlice;
import si.fri.prpo.nakupovalniseznami.entitete.Izdelki;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
@BeleziKlice
public class IzdelkiZrno {

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
        if(i == null){
            log.info("Article not found!");
            return null;
        }else {
            return i;
        }
    }

    @Transactional
    public Izdelki dodajIzdelek(Izdelki i) {
        if (i != null) {
            em.persist(i);
            log.info("Article added with id: " + i.getIdIzdelka());
        }

        return i;
    }

    @Transactional
    public Izdelki posodobiIzdelek(int id, Izdelki i) {
        Izdelki izdelek = em.find(Izdelki.class, id);
        if(izdelek == null){
            log.info("Article not found!");
        } else {
            i.setIdIzdelka(izdelek.getIdIzdelka());
            em.merge(i);
            log.info("Updating successfully.");
        }
        return i;
    }

    @Transactional
    public int odstraniIzdelek(int id) {

        Izdelki izdelek = em.find(Izdelki.class, id);

        if (izdelek != null) {
            em.remove(izdelek);
            log.info("Article successfully removed.");
        }
        else {
            log.info("Article not found!");
        }

        return id;
    }
}
