package si.fri.prpo.nakupovalniseznami.servlet;

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

        pw.append("Uporabniki:<br/>");
        uporabnikiZrno.getUporabniki().stream().forEach(u -> pw.append("<strong>IME:</strong> " + u.getIme() + " <strong>PRIIMEK:</strong> " + u.getPriimek()
                + " <strong>EMAIL:</strong> " + u.getEmail() + " <strong>NASLOV:</strong> " + u.getNaslov() + " <strong>UPORABNISKO IME:</strong> " + u.getUporabniskoIme() +  "<br/>"));


        pw.append("<br/>");
        uporabnikiZrno.getPetras().stream().forEach(u -> pw.append("<strong>IME:</strong> " + u.getIme() + " <strong>PRIIMEK:</strong> " + u.getPriimek() + "<br/>"));

    }
}
