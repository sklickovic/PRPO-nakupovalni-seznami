package si.fri.prpo.nakupovalniseznami.zrno;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.prpo.nakupovalniseznami.anotacije.BeleziKlice;
import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;

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
public class UporabnikZrno {

    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
    private EntityManager em;
    private final static Logger log = Logger.getLogger(UporabnikZrno.class.getName());

    @PostConstruct
    private void init() {
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        log.info("Inicializacija zrna: " + UporabnikZrno.class.getSimpleName()+ ", uuid = "+ uuid);
    }

    @PreDestroy
    private void destroy() {
        log.info("Uničenje zrna: " + UporabnikZrno.class.getSimpleName());
    }

    public List<Uporabnik> getUporabniki() {
        CriteriaBuilder criteria = em.getCriteriaBuilder();
        CriteriaQuery<Uporabnik> query = criteria.createQuery(Uporabnik.class);
        Root<Uporabnik> root = query.from(Uporabnik.class);
        query.select(root);
        List<Uporabnik> uporabniki = em.createQuery(query).getResultList();
        return uporabniki;
    }

    @Default
    public List<Uporabnik> getUporabniki(QueryParameters query){
        List<Uporabnik> uporabniki = JPAUtils.queryEntities(em, Uporabnik.class, query);
        return uporabniki;
    }

    @Default
    public Long pridobiUporabnikeCount(QueryParameters query){
        Long count = JPAUtils.queryEntitiesCount(em, Uporabnik.class, query);
        return count;
    }

    public Uporabnik pridobiUporabnika(int id) {
        Uporabnik uporabnik = em.find(Uporabnik.class, id);
        if (uporabnik == null){
            log.info("User not found!");
            return null;
        }
        return uporabnik;
    }

    @Transactional
    public Uporabnik dodajUporabnika(Uporabnik uporabnik) {
        if (uporabnik != null) {
            em.persist(uporabnik);
            log.info("User added with id: " + uporabnik.getId());
        }
        return uporabnik;
    }

    @Transactional
    public Uporabnik posodobiUporabnika(Uporabnik uporabnik) {
        Uporabnik user = em.find(Uporabnik.class, uporabnik.getId());
        if(user == null){
            log.info("User not found!");
            return null;
        }
        else {
            user.setIme(uporabnik.getIme());
            user.setPriimek(uporabnik.getPriimek());
            user.setEmail(uporabnik.getEmail());
            user.setNaslov(uporabnik.getNaslov());
            user.setUporabniskoIme(uporabnik.getUporabniskoIme());
            user.setPassword(uporabnik.getPassword());
            user.setWishLists(uporabnik.getWishLists());
            em.merge(user);
            log.info("Updating successfully complete.");
            return user;
        }
    }

    @Transactional
    public void odstraniUporabnika(int id) {
        Uporabnik user = em.find(Uporabnik.class, id);
        if (user != null) {
            em.remove(user);
            log.info("User with id " + id + " successfully deleted.");
        }
        else {
            log.info("User not found!");
        }
    }
}