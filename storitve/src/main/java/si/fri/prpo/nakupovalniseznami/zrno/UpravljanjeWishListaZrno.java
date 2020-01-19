package si.fri.prpo.nakupovalniseznami.zrno;

import si.fri.prpo.nakupovalniseznami.dto.WishListDTO;
import si.fri.prpo.nakupovalniseznami.entitete.Izdelki;
import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;
import si.fri.prpo.nakupovalniseznami.entitete.WishList;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RequestScoped
public class UpravljanjeWishListaZrno {

    @Inject
    private WishListZrno wishListZrno;
    @Inject
    private UporabnikZrno uporabnikZrno;
    @Inject
    private IzdelkiZrno izdelkiZrno;

    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
    private EntityManager em;
    private final static Logger log = Logger.getLogger(UpravljanjeWishListaZrno.class.getName());

    @PostConstruct
    private void init() {
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        log.info("Inicializacija zrna: " + UpravljanjeWishListaZrno.class.getSimpleName()+ ", uuid = "+ uuid);;
    }

    @PreDestroy
    private void destroy() {
        log.info("Uničenje zrna: " + UpravljanjeWishListaZrno.class.getSimpleName());
    }


    public WishList createWishList(WishListDTO wl) {
        Uporabnik u = uporabnikZrno.pridobiUporabnika(wl.getIdWishListe());

        if (u == null) {
            log.info("Uporabnik ne obstaja! Nakupovalni seznam ne bo dodan!");
            return null;
        }
        WishList list = new WishList();
        list.setUser(u);
        list.setIzdelkiList(wl.getIzdelkiList());
        log.info("Wish list has been successfully created.");
        return wishListZrno.dodajWishList(list);
    }

    public WishList sortByCheapest(WishListDTO wl) {
        WishList w_l = wishListZrno.pridobiWishList(wl.getIdWishListe());
        List<Izdelki> izdelki = wl.getIzdelkiList();

        for (int i = 0; i < izdelki.size() - 1; i++) {
            for (int j = 0; j < izdelki.size() - i - 1; j++) {
                Izdelki izdelek1 = izdelki.get(i);
                Izdelki izdelek2 = izdelki.get(j);

                if (Integer.parseInt(izdelek1.getCena()) > Integer.parseInt(izdelek2.getCena())) {
                    Collections.swap(izdelki, i, j);
                }
            }
        }

        w_l.setIzdelkiList(izdelki);
        return wishListZrno.posodobiWishList(w_l);
    }

    public WishList sortByExpensive(WishListDTO wl) {
        WishList w_l = wishListZrno.pridobiWishList(wl.getIdWishListe());
        List<Izdelki> izdelki = wl.getIzdelkiList();

        for (int i = 0; i < izdelki.size() - 1; i++) {
            for (int j = 0; j < izdelki.size() - i - 1; j++) {
                Izdelki izdelek1 = izdelki.get(i);
                Izdelki izdelek2 = izdelki.get(j);

                if (Integer.parseInt(izdelek1.getCena()) < Integer.parseInt(izdelek2.getCena())) {
                    Collections.swap(izdelki, i, j);
                }
            }
        }

        w_l.setIzdelkiList(izdelki);
        return wishListZrno.posodobiWishList(w_l);
    }
}
