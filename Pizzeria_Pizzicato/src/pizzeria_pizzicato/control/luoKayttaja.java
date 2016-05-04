package pizzeria_pizzicato.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pizzeria_pizzicato.model.Kayttaja;
import pizzeria_pizzicato.model.Ostoskori;
import pizzeria_pizzicato.model.dao.KayttajaDAO;


@WebServlet("/rekisteroidy")
public class luoKayttaja extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String jsp = "/view/luo-kayttaja.jsp";
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(
				jsp);
		
		HttpSession session = request.getSession();
		Ostoskori ostoskori = (Ostoskori) session.getAttribute("ostoskori");
		if(ostoskori == null){
			ostoskori = new Ostoskori();
			session.setAttribute("ostoskori", ostoskori);
			session.setMaxInactiveInterval(60*60);
		}
		
		dispather.forward(request, response);
			
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String viesti = null;
		KayttajaDAO kdao = new KayttajaDAO();
		int kID = 0;
		String knimi = request.getParameter("Knimi");
		String enimi = request.getParameter("Enimi");
		String snimi = request.getParameter("Snimi");
		String kos = request.getParameter("KatuOs");
		String pno = request.getParameter("Puhno");
		String spos = request.getParameter("Sposti");
		String sala = request.getParameter("SS");
		int kryh = 3; //Asiakas, ei oikeuksia.
		
		if(enimi.isEmpty() || snimi.isEmpty()){
			viesti = "Nimikentät ovat pakollisia!";

		
		}else if(pno.matches("\\w{9,10}") == false && pno.isEmpty()){
			viesti= "Puhelinnumero on virheellinen!";
			response.sendRedirect("vahvistaTilaus");
			
		}else if(kos.isEmpty()){
			viesti=  "Osoitekenttä on pakollinen!";
			
		}
		
		
		if (viesti!=null){
			request.getSession().setAttribute("message", viesti);
			response.sendRedirect("rekisteroidy");

		}else{
			Kayttaja kirjautunut = new Kayttaja(kID, knimi, enimi, snimi, sala, pno, kos, spos, kryh);
			kdao.create(kirjautunut);
			response.sendRedirect("pizzaMenu");
		}
		
	}
	

}
