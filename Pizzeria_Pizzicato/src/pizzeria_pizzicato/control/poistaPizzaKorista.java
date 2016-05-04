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
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession(); //haetaan session
		Ostoskori ostoskori = (Ostoskori) session.getAttribute("ostoskori"); // haetaan ostoskori sessionista
		
		if(action.equalsIgnoreCase("poista")){ //jos napin action tieto oli poista
			int sArvo = Integer.parseInt((String) request.getParameter("paikkaID")); //Haetaan ensin pizzaID
			ostoskori.removeTuote(sArvo); //poistetaan pizza ostoskorista
			session.setAttribute("ostoskori", ostoskori); // vied‰‰n ostoskorin muutokset sessioniin
			
			response.sendRedirect("vahvistaTilaus"); // l‰hetet‰‰n takaisin vahvistussivulle
		}else if(action.equalsIgnoreCase("lisaa")){ // jos napin action tieto oli lisaa
			int sArvo = Integer.parseInt((String) request.getParameter("paikkaID")); //Haetaan ensin pizzaID
			
			Pizza pizza = new Pizza(); //Luodaan pizza olio
			pizza = (Pizza) ostoskori.getTuote(sArvo).getTuote(); //haetaan pizza oliolle tietoa tulleen paikkaID perusteella
			ostoskori.addPizza(pizza, ostoskori.getTuote(sArvo).getOregano(), ostoskori.getTuote(sArvo).getvSipuli(), 1); //Lis‰t‰‰n haetun tiedon perusteella yksi kappale koriin
			session.setAttribute("ostoskori", ostoskori); // vied‰‰n ostoskorin muutokset sessioniin
			
			response.sendRedirect("vahvistaTilaus"); // l‰hetet‰‰n takaisin vahvistussivulle
		}else if(action.equalsIgnoreCase("poistaKori")){
			session.removeAttribute("ostoskori");
			ostoskori = new Ostoskori();
			session.setAttribute("ostoskori", ostoskori);
			response.sendRedirect("pizzaMenu");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
