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

    @Default
    public Long pridobiIzdelkeCount(QueryParameters query){
        Long count = JPAUtils.queryEntitiesCount(em, Izdelki.class, query);
        return count;
    }

    @Default
    public List<Izdelki> getIzdelki() {

        List<Izdelki> izdelki = em.createNamedQuery("Izdelki.getAll").getResultList();
        return izdelki;
    }

    @Default
    public List<Izdelki> getArtikli(QueryParameters query) {
        List<Izdelki> artikli = JPAUtils.queryEntities(em, Izdelki.class, query);
        return artikli;
    }

    @Transactional
    public List<Izdelki> pridobiIzdelek(int id) {
        List<Izdelki> i = (List<Izdelki>) em.createNamedQuery("Izdelki.getAll");
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
