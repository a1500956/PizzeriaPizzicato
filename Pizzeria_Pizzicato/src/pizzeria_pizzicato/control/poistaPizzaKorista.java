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

		PizzaDAO pizzadao = new PizzaDAO();
		ArrayList<Pizza> pizzaLista = pizzadao.findAll();// haetaan pizzat
		
		int sArvo = Integer.parseInt((String) request.getParameter("pizzaID")); //Haetaan ensin pizzaID
		int oregano, vSipuli;
		oregano = Integer.parseInt(request.getParameter("oregano"));
		vSipuli = Integer.parseInt(request.getParameter("vSipuli"));
		
		Pizza pizza = new Pizza();
		for(int i=0; i<pizzaLista.size();i++){ //luodaan pizzaID mukaan pizza
			if(pizzaLista.get(i).getId() == sArvo){
				pizza = pizzaLista.get(i);
			}
		}
		
		HttpSession session = request.getSession(); //haetaan session
		Ostoskori ostoskori = (Ostoskori) session.getAttribute("ostoskori"); // haetaan ostoskori sessionista
		ostoskori.removePizza(pizza, oregano, vSipuli); //poistetaan pizza ostoskorista

		session.setAttribute("ostoskori", ostoskori); // viedään ostoskorin muutokset sessioniin
		response.sendRedirect("vahvistaTilaus");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
