package si.fri.prpo.nakupovalniseznami.zrno;


import si.fri.prpo.nakupovalniseznami.anotacije.BeleziKlice;
import si.fri.prpo.nakupovalniseznami.entitete.Kategorija;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
@BeleziKlice
public class KategorijaZrno {

    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")

    private EntityManager em;

    private Logger log = Logger.getLogger(KategorijaZrno.class.getName());

    @PostConstruct
    private void init() {
        log.info("Inicializacija zrna: " + Kategorija.class.getSimpleName());
    }

    @PreDestroy
    private void destroy() {
        log.info("Uničenje zrna: " + Kategorija.class.getSimpleName());
    }

    public List<Kategorija> getKategorije() {
        List<Kategorija> kategorije = em.createNamedQuery("Kategorija.getAll").getResultList();
        return kategorije;
    }

    @Transactional
    public Kategorija pridobiKategorijo(int id) {
        Kategorija kat = em.find(Kategorija.class, id);
        if (kat == null) {
            log.info("Category not found!");
            return null;
        } else {
            return kat;
        }
    }

    @Transactional
    public Kategorija dodajKategorijo(Kategorija kat) {
        if (kat != null) {
            em.persist(kat);
            log.info("Category added with id: " + kat.getIdKategorije());
        }
        return kat;
    }

    @Transactional
    public Kategorija posodobiKategorijo(int id, Kategorija kat) {
        Kategorija kat1 = em.find(Kategorija.class, id);
        if (kat1 == null) {
            log.info("Category not found!");
        } else {
            kat.setIdKategorije(kat1.getIdKategorije());
            em.merge(kat);
            log.info("Updating successfully.");
        }
        return kat;
    }

    @Transactional
    public int odstraniKategorijo(int id) {
        Kategorija kat = em.find(Kategorija.class, id);

        if (kat != null) {
            em.remove(kat);
            log.info("Category successfully removed.");
        } else {
            log.info("Category not found!");
        }

        return id;
    }
}
