package pizzeria_pizzicato.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzeria_pizzicato.model.Kayttaja;


@WebServlet("/luo-kayttaja")
public class luoKayttaja extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String jsp = "/view/luo-kayttaja.jsp";
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(
				jsp);
		dispather.forward(request, response);
			
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int kID = 0;
		String knimi = request.getParameter("Knimi");
		String enimi = request.getParameter("Enimi");
		String snimi = request.getParameter("Snimi");
		String kos = request.getParameter("KatuOs");
		String pno = request.getParameter("Puhno");
		String spos = request.getParameter("Sposti");
		String sala = request.getParameter("SS");
		int kryh = 3; //Asiakas, ei oikeuksia.
		
		Kayttaja kirjautunut = new Kayttaja(kID, knimi, enimi, snimi, sala, pno, kos, spos, kryh);
		
	}
	

}
