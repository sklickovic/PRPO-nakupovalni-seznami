package si.fri.prpo.nakupovalniseznami.zrno;

import si.fri.prpo.nakupovalniseznami.anotacije.BeleziKlice;
import si.fri.prpo.nakupovalniseznami.entitete.WishList;

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
public class WishListZrno {

    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")

    private EntityManager em;

    private Logger log = Logger.getLogger(WishListZrno.class.getName());

    @PostConstruct
    private void init() {
        log.info("Inicializacija zrna: " + WishListZrno.class.getSimpleName());
    }

    @PreDestroy
    private void destroy() {
        log.info("Uničenje zrna: " + WishListZrno.class.getSimpleName());
    }

    public List<WishList> getWishLists() {
        List<WishList> wishlist = em.createNamedQuery("WishList.getAll").getResultList();

        return wishlist;
    }

    public List<WishList> getUserList() {
        List<WishList> wishlist = em.createNamedQuery("WishList.getSeznamUporabnikov").getResultList();

        return wishlist;
    }

    @Transactional
    public WishList pridobiWishList(int id) {
        WishList w = em.find(WishList.class, id);
        if(w == null){
            log.info("Wish list not found!");
            return null;
        }else {
            w.setIdWishListe(w.getIdWishListe());
            em.merge(w);
            return w;
        }
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
    public WishList posodobiWishList(int id, WishList wl) {
        WishList wl2 = em.find(WishList.class, id);
        if(wl2 == null){
            log.info("Wish list not found!");
        }else {
            wl.setIdWishListe(wl2.getIdWishListe());
            em.merge(wl);
            log.info("Updating successfully.");
        }
        return wl;
    }

    @Transactional
    public int odstraniWishList(int id) {
        WishList wl = em.find(WishList.class, id);

        if (wl != null) {
            em.remove(wl);
            log.info("Wish list successfully removed.");
        }
        else {
            log.info("Wish list not found!");
        }

        return id;
    }
}
