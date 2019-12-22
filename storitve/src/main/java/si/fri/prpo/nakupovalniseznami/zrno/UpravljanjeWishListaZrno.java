package si.fri.prpo.nakupovalniseznami.zrno;

import si.fri.prpo.nakupovalniseznami.dto.WishListDTO;
import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;
import si.fri.prpo.nakupovalniseznami.entitete.WishList;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class UpravljanjeWishListaZrno {

    private Logger log = Logger.getLogger(UpravljanjeWishListaZrno.class.getName());

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
        }

        WishList list = new WishList();
        list.setUporabnik(u);
        list.setList(wl.getIzdelki());
        log.info("Wish list has been successfully created.");
        return wishListZrno.dodajWishList(list);
    }
}
