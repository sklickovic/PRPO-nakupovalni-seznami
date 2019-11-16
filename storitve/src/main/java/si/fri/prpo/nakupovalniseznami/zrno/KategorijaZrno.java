package si.fri.prpo.nakupovalniseznami.zrno;


import si.fri.prpo.nakupovalniseznami.entitete.Kategorija;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

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

        return kat;
    }

    @Transactional
    public Kategorija dodajKategorijo(Kategorija kat) {
        if (kat != null) {
            em.persist(kat);
        }

        return kat;
    }

    @Transactional
    public void posodobiKategorijo(int id, Kategorija kat) {
        Kategorija kat1 = em.find(Kategorija.class, id);

        kat.setIdKategorije(kat1.getIdKategorije());
        em.merge(kat);
    }

    @Transactional
    public int odstraniKategorijo(int id) {
        Kategorija kat = em.find(Kategorija.class, id);

        if (kat != null) {
            em.remove(kat);
        }

        return id;
    }
}
