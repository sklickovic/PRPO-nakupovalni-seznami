package si.fri.prpo.nakupovalniseznami.zrno;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.prpo.nakupovalniseznami.anotacije.BeleziKlice;
import si.fri.prpo.nakupovalniseznami.entitete.Izdelki;

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
public class IzdelkiZrno {

    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
    private EntityManager em;
    private final static Logger log = Logger.getLogger(IzdelkiZrno.class.getName());

    @PostConstruct
    private void init() {
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        log.info("Inicializacija zrna: " + IzdelkiZrno.class.getSimpleName()+ ", uuid = "+ uuid);
    }

    @PreDestroy
    private void destroy() {
        log.info("Uničenje zrna: " + IzdelkiZrno.class.getSimpleName());
    }

    public List<Izdelki> getIzdelki() {
        CriteriaBuilder criteria = em.getCriteriaBuilder();
        CriteriaQuery<Izdelki> query = criteria.createQuery(Izdelki.class);
        Root<Izdelki> root = query.from(Izdelki.class);
        query.select(root);
        List<Izdelki> izdelki = em.createQuery(query).getResultList();
        return izdelki;
    }

    @Default
    public List<Izdelki> getIzdelki(QueryParameters query) {
        List<Izdelki> izdelki = JPAUtils.queryEntities(em, Izdelki.class, query);
        return izdelki;
    }

    @Default
    public Long pridobiIzdelkeCount(QueryParameters query){
        Long count = JPAUtils.queryEntitiesCount(em, Izdelki.class, query);
        return count;
    }

    public Izdelki pridobiIzdelek(int id) {
        Izdelki i = em.find(Izdelki.class, id);
        if(i == null){
            log.info("Article not found!");
            return null;
        }
        return i;
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
    public Izdelki posodobiIzdelek(Izdelki i) {
        Izdelki izdelek = em.find(Izdelki.class, i.getIdIzdelka());
        if(izdelek == null){
            log.info("Article not found!");
            return null;
        }
        else {
            izdelek.setCena(i.getCena());
            izdelek.setNazivIzdelka(i.getNazivIzdelka());
            izdelek.setZalogaIzdelka(i.getZalogaIzdelka());
            izdelek.setKategorije(i.getKategorije());
            izdelek.setList(i.getList());
            em.merge(izdelek);
            log.info("Updating successfully complete");
            return izdelek;
        }
    }

    @Transactional
    public void odstraniIzdelek(int id) {
        Izdelki izdelek = em.find(Izdelki.class, id);
        if (izdelek != null) {
            em.remove(izdelek);
            log.info("Article with id " + id + " successfully deleted.");
        }
        else {
            log.info("Article not found!");
        }
    }
}
