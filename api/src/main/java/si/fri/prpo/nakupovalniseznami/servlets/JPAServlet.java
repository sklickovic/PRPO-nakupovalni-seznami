package si.fri.prpo.nakupovalniseznami.servlets;

import si.fri.prpo.nakupovalniseznami.entitete.Izdelki;
import si.fri.prpo.nakupovalniseznami.entitete.Kategorija;
import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;
import si.fri.prpo.nakupovalniseznami.entitete.WishList;
import si.fri.prpo.nakupovalniseznami.zrno.IzdelkiZrno;
import si.fri.prpo.nakupovalniseznami.zrno.KategorijaZrno;
import si.fri.prpo.nakupovalniseznami.zrno.UporabnikZrno;
import si.fri.prpo.nakupovalniseznami.zrno.WishListZrno;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/servlet")
public class JPAServlet extends HttpServlet {

    @Inject
    private UporabnikZrno uporabnikiZrno;

    @Inject
    private IzdelkiZrno izdelkiZrno;

    @Inject
    private KategorijaZrno kategorijaZrno;

    @Inject
    private WishListZrno wishListZrno;

    private final static Logger log = Logger.getLogger(JPAServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Uporabnik> uporabniki = uporabnikiZrno.getUporabniki();
        List<Izdelki> izdelki = izdelkiZrno.getIzdelki();
        List<WishList> wishList = wishListZrno.getWishLists();
        List<Kategorija> kategorije = kategorijaZrno.getKategorije();

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();

        pw.append("Izpis vseh uporabnikov:<br/>");
        uporabniki.forEach(u -> pw.append("<strong>IME:</strong> ").append(u.getIme()).append("  <strong>PRIIMEK:</strong> ").append(u.getPriimek()).append("  <strong>EMAIL:</strong> ").append(u.getEmail()).append("  <strong>NASLOV:</strong> ").append(u.getNaslov()).append("  <strong>UPORABNISKO IME:</strong> ").append(u.getUporabniskoIme()).append("<br/>"));

        /*pw.append("<br/>Izpis vseh izdelkov:<br/>");
        izdelki.forEach(u -> pw.append("<strong>NAZIV IZDELKA:</strong> ").append(u.getNazivIzdelka()).append("  <strong>Zaloga: </strong>").append(u.getZalogaIzdelka()).append("  <strong>CENA: </strong>").append(u.getCena()).append("<br/>"));
*/
        pw.append("<br/>Izpis vseh kategorij:<br/>");
        kategorije.forEach(u -> pw.append("<strong>NAZIV KATEGORIJE: </strong>").append(u.getNazivKategorije()).append("<br/>"));

    }
}
