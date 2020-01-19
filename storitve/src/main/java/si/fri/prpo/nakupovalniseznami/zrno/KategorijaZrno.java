package si.fri.prpo.nakupovalniseznami.zrno;


import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.prpo.nakupovalniseznami.anotacije.BeleziKlice;
import si.fri.prpo.nakupovalniseznami.entitete.Kategorija;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
@BeleziKlice
public class KategorijaZrno {

    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
    private EntityManager em;
    private final static Logger log = Logger.getLogger(KategorijaZrno.class.getName());

    @PostConstruct
    private void init() {
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        log.info("Inicializacija zrna: " + KategorijaZrno.class.getSimpleName()+ ", uuid = "+ uuid);
    }

    @PreDestroy
    private void destroy() {
        log.info("Uničenje zrna: " + Kategorija.class.getSimpleName());
    }

    public List<Kategorija> getKategorije() {
        CriteriaBuilder criteria = em.getCriteriaBuilder();
        CriteriaQuery<Kategorija> query = criteria.createQuery(Kategorija.class);
        Root<Kategorija> root = query.from(Kategorija.class);
        query.select(root);
        List<Kategorija> kategorije = em.createQuery(query).getResultList();
        return kategorije;
    }

    @Default
    public List<Kategorija> getKategorije(QueryParameters query) {
        List<Kategorija> kategorije = JPAUtils.queryEntities(em, Kategorija.class, query);
        return kategorije;
    }

    @Default
    public Long pridobiKategorijeCount(QueryParameters query){
        Long count = JPAUtils.queryEntitiesCount(em, Kategorija.class, query);
        return count;
    }

    @Transactional
    public Kategorija pridobiKategorijo(int id) {
        Kategorija kat = em.find(Kategorija.class, id);
        if (kat == null) {
            log.info("Category not found!");
            return null;
        }
        return kat;
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
    public Kategorija posodobiKategorijo(Kategorija kat) {
        Kategorija kat1 = em.find(Kategorija.class, kat.getIdKategorije());
        if (kat1 == null) {
            log.warning("Category not found!");
            return null;
        }
        else {
            kat1.setNazivKategorije(kat.getNazivKategorije());
            kat1.setIzdelkiList(kat.getIzdelkiList());
            em.merge(kat1);
            log.info("Updating successfully.");
            return kat1;
        }
    }

    @Transactional
    public void odstraniKategorijo(int id) {
        Kategorija kat = em.find(Kategorija.class, id);
        if (kat != null) {
            em.remove(kat);
            log.info("Category with id " + id + " successfully deleted.");
        } else {
            log.info("Category not found!");
        }
    }
}