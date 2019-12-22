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
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
@BeleziKlice
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

    @Default
    public Long pridobiUporabnikeCnt(QueryParameters query) {
        Long cnt = JPAUtils.queryEntitiesCount(em, Uporabnik.class, query);
        return cnt;
    }

    @Default
    public List<Uporabnik> getUporabniki() {
        Query q = em.createNamedQuery("Uporabnik.getAll");
        List<Uporabnik> usrs =(List<Uporabnik>)(q.getResultList());
        return usrs;
    }

    @Default
    public List<Uporabnik> getPetras() {
        List<Uporabnik> petras = em.createNamedQuery("Uporabnik.getPetras").getResultList();
        return petras;
    }

    @Transactional
    public Uporabnik pridobiUporabnika(int id) {
        Uporabnik u = em.find(Uporabnik.class, id);
        if(u == null){
            log.info("User not found!");
            return null;
        }else{
            return u;
        }
    }

    @Transactional
    public Uporabnik dodajUporabnika(Uporabnik u) {
        if (u != null) {
            em.persist(u);
            log.info("User added with id: " + u.getId());
        }

        return u;
    }

    @Transactional
    public Uporabnik posodobiUporabnika(int id, Uporabnik uporabnik) {
        Uporabnik user = em.find(Uporabnik.class, id);
        if(user == null){
            log.info("User not found!");
        }
        else {
            user.setId(user.getId());
            em.merge(uporabnik);
            log.info("Updating successfully.");
        }
        return uporabnik;
    }

    @Transactional
    public int odstraniUporabnika(int id) {
        Uporabnik user = em.find(Uporabnik.class, id);

        if (user != null) {
            em.remove(user);
            log.info("User with id " + id + " successfully deleted.");
        }
        else {
            log.warning("User not found!");
        }

        return id;
    }


}
