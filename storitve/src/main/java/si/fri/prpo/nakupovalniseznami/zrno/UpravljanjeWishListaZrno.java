package si.fri.prpo.nakupovalniseznami.zrno;

import si.fri.prpo.nakupovalniseznami.dto.WishListDTO;
import si.fri.prpo.nakupovalniseznami.entitete.Izdelki;
import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;
import si.fri.prpo.nakupovalniseznami.entitete.WishList;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class UpravljanjeWishListaZrno {

    private Logger log = Logger.getLogger(UpravljanjeWishListaZrno.class.getName());

    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
    private EntityManager em;

    @PostConstruct
    private void init() {
        log.info("Inicializcaija zrna: " + UpravljanjeWishListaZrno.class.getSimpleName());
    }

    @PreDestroy
    private void destroy() {
        log.info("Uničenje zrna: " + UpravljanjeWishListaZrno.class.getSimpleName());
    }

    @Inject
    private WishListZrno wishListZrno;
    @Inject
    private UporabnikZrno uporabnikZrno;


    public WishList createWishList(WishListDTO wl) {

        Uporabnik u = uporabnikZrno.pridobiUporabnika(wl.getUporabnikId());

        if (u == null) {
            log.info("Uporabnik ne obstaja!");
            return null;
        }

        WishList list = new WishList();
        list.setUporabnik(u);
        list.setList(wl.getIzdelki());
        log.info("Wish list has been successfully created.");
        return wishListZrno.dodajWishList(list);
    }

    public WishList sortByCheapest(WishListDTO wl) {
        WishList w_l = wishListZrno.pridobiWishList(wl.getId());
        List<Izdelki> izdelki = wl.getIzdelki();

        for (int i = 0; i < izdelki.size() - 1; i++) {
            for (int j = 0; j < izdelki.size() - i - 1; j++) {
                Izdelki izdelek1 = izdelki.get(i);
                Izdelki izdelek2 = izdelki.get(j);

                if (Integer.parseInt(izdelek1.getCena()) > Integer.parseInt(izdelek2.getCena())) {
                    Collections.swap(izdelki, i, j);
                }
            }
        }

        w_l.setList(izdelki);
        return wishListZrno.posodobiWishList(wl.getId(), w_l);
    }

    public WishList sortByExpensive(WishListDTO wl) {
        WishList w_l = wishListZrno.pridobiWishList(wl.getId());
        List<Izdelki> izdelki = wl.getIzdelki();

        for (int i = 0; i < izdelki.size() - 1; i++) {
            for (int j = 0; j < izdelki.size() - i - 1; j++) {
                Izdelki izdelek1 = izdelki.get(i);
                Izdelki izdelek2 = izdelki.get(j);

                if (Integer.parseInt(izdelek1.getCena()) < Integer.parseInt(izdelek2.getCena())) {
                    Collections.swap(izdelki, i, j);
                }
            }
        }

        w_l.setList(izdelki);
        return wishListZrno.posodobiWishList(wl.getId(), w_l);
    }
}
