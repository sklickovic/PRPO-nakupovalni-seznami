package si.fri.prpo.nakupovalniseznami.servlets;

import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;
import si.fri.prpo.nakupovalniseznami.zrno.UporabnikZrno;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/servlet")
public class JPAServlet extends HttpServlet {

    @Inject
    private UporabnikZrno uporabnikiZrno;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Uporabnik> uporabniki = uporabnikiZrno.getUporabniki();

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter pw = resp.getWriter();

        pw.append("Izpis vseh uporabnikov:<br/>");
        uporabnikiZrno.getUporabniki().forEach(u -> pw.append("<strong>IME:</strong> ").append(u.getIme()).append("  <strong>PRIIMEK:</strong> ").append(u.getPriimek()).append("  <strong>EMAIL:</strong> ").append(u.getEmail()).append("  <strong>NASLOV:</strong> ").append(u.getNaslov()).append("  <strong>UPORABNISKO IME:</strong> ").append(u.getUporabniskoIme()).append("<br/>"));


        pw.append("<br/>Izpis vseh podatkov za uporabnike z imenov \"Petra\"<br/>");
        uporabnikiZrno.getPetras().forEach(u -> pw.append("<strong>IME:</strong> ").append(u.getIme()).append("  <strong>PRIIMEK:</strong> ").append(u.getPriimek()).append("  <strong>EMAIL:</strong> ").append(u.getEmail()).append("  <strong>NASLOV:</strong> ").append(u.getNaslov()).append("  <strong>UPORABNISKO IME:</strong> ").append(u.getUporabniskoIme()).append("<br/>"));

    }
}
