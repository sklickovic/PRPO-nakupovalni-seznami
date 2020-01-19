package si.fri.prpo.nakupovalniseznami.zrno;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import org.eclipse.persistence.internal.identitymaps.WeakIdentityMap;
import si.fri.prpo.nakupovalniseznami.anotacije.BeleziKlice;
import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;
import si.fri.prpo.nakupovalniseznami.entitete.WishList;

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
public class WishListZrno {

    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
    private EntityManager em;
    private final static Logger log = Logger.getLogger(WishListZrno.class.getName());

    @PostConstruct
    private void init() {
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        log.info("Inicializacija zrna: " + WishListZrno.class.getSimpleName()+ ", uuid = "+ uuid);
    }

    @PreDestroy
    private void destroy() {
        log.info("Uničenje zrna: " + WishListZrno.class.getSimpleName());
    }

    public List<WishList> getWishLists() {
        CriteriaBuilder criteria = em.getCriteriaBuilder();
        CriteriaQuery<WishList> query = criteria.createQuery(WishList.class);
        Root<WishList> root = query.from(WishList.class);
        query.select(root);
        List<WishList> wishlist = em.createQuery(query).getResultList();
        return wishlist;
    }

    @Default
    public List<WishList> getWishLists(QueryParameters query) {
        List<WishList> wishlist = JPAUtils.queryEntities(em, WishList.class, query);
        return wishlist;
    }

    @Default
    public Long pridobiWishListCount(QueryParameters query){
        Long count = JPAUtils.queryEntitiesCount(em, WishList.class, query);
        return count;
    }

    public WishList pridobiWishList(int id) {
        WishList w = em.find(WishList.class, id);
        if(w == null){
            log.info("Wish list not found!");
            return null;
        }
        return w;
    }

    @Transactional
    public WishList dodajWishList(WishList wl) {
        if (wl != null) {
            em.persist(wl);
            log.info("Wish list added with id: " + wl.getIdWishListe());
        }
        return wl;
    }

    @Transactional
    public WishList posodobiWishList(WishList wl) {
        WishList wl2 = em.find(WishList.class, wl.getIdWishListe());
        if(wl2 == null){
            log.info("Wish list not found!");
            return null;
        }
        else {
            wl2.setIzdelkiList(wl.getIzdelkiList());
            wl2.setUser(wl.getUser());
            em.merge(wl2);
            log.info("Updating successfully complete");
            return wl2;
        }
    }

    @Transactional
    public void odstraniWishList(int id) {
        WishList wl = em.find(WishList.class, id);
        if (wl != null) {
            em.remove(wl);
            log.info("Wish list with id " + id + " successfully deleted.");
        }
        else {
            log.info("Wish list not found!");
        }
    }
}
