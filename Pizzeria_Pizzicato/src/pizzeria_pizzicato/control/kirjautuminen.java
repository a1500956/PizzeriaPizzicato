package pizzeria_pizzicato.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import pizzeria_pizzicato.model.Kayttaja;
import pizzeria_pizzicato.model.dao.KayttajaDAO;

@WebServlet("/kirjautuminen")

public class kirjautuminen extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(kirjautuminen.class);
    private RequestDispatcher jsp;
    
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        jsp = context.getRequestDispatcher("login.jsp");
     }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
    throws ServletException, IOException {
       logger.debug("doGet()");
       jsp.forward(req, resp);
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
    throws ServletException, IOException {
       logger.debug("doPost()");

       String kayttaja_tunnus = req.getParameter("kayttajatunnus");
       Kayttaja kayttaja = new KayttajaDAO().findByKayttajaTunnus(kayttaja_tunnus);
       if (kayttaja == null)
       {
          logger.debug("authentication failed: bad username");
          req.setAttribute("message", "Authentication failed.");
          jsp.forward(req, resp);
          return;
       }
       
       String kayttaja_salasana = req.getParameter("salasana");
       if (kayttaja_salasana == null || !kayttaja.getKayttaja_salasana().equals(kayttaja_salasana))
       {
          logger.debug("authentication failed: bad password");
          req.setAttribute("message", "Authentication failed.");
          jsp.forward(req, resp);
          return;
       }

       HttpSession session = req.getSession();
       int kayttaja_id = kayttaja.getKayttaja_id();
       session.setAttribute("kayttaja_id", kayttaja_id);
       logger.debug("authenticated");
       String url = "pizzaMenu";
       resp.sendRedirect(url);
    }  
}