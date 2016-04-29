package pizzeria_pizzicato.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pizzeria_pizzicato.model.Ostoskori;
import pizzeria_pizzicato.model.Pizza;
import pizzeria_pizzicato.model.dao.PizzaDAO;

@WebServlet("/poistaPizzaKorista")
public class poistaPizzaKorista extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public poistaPizzaKorista() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int sArvo = Integer.parseInt((String) request.getParameter("paikkaID")); //Haetaan ensin pizzaID
		
		HttpSession session = request.getSession(); //haetaan session
		Ostoskori ostoskori = (Ostoskori) session.getAttribute("ostoskori"); // haetaan ostoskori sessionista
		ostoskori.removeTuote(sArvo); //poistetaan pizza ostoskorista

		session.setAttribute("ostoskori", ostoskori); // vied‰‰n ostoskorin muutokset sessioniin
		response.sendRedirect("vahvistaTilaus");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
