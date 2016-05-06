package pizzeria_pizzicato.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzeria_pizzicato.model.Kayttaja;
import pizzeria_pizzicato.model.dao.KayttajaDAO;


@WebServlet("/luoTyontekija")
public class luoTyontekija extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public luoTyontekija() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsp = "/view/luo-ttekija.jsp";
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(
				jsp);
		
		dispather.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viesti = null;
		KayttajaDAO tdao = new KayttajaDAO();
		int kID = 0;
		String knimi = request.getParameter("Knimi");
		String enimi = request.getParameter("Enimi");
		String snimi = request.getParameter("Snimi");
		String kos = request.getParameter("KatuOs");
		String pno = request.getParameter("Puhno");
		String spos = request.getParameter("Sposti");
		String sala = request.getParameter("SS");
		int kryh = 2; //Työntekijä.
		
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
			tdao.create(kirjautunut);
			response.sendRedirect("luoTyontekija");
		}
		
	}
	

}
