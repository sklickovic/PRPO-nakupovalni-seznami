package si.fri.prpo.nakupovalniseznami.zrno;


import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

@ApplicationScoped
public class BelezenjeKlicevZrno {

    private Logger log = Logger.getLogger(BelezenjeKlicevZrno.class.getName());
    private int stKlicev = 0;

    public void klic() {
        stKlicev++;
        log.info("Stevilo klicev: " + stKlicev);
    }

}
