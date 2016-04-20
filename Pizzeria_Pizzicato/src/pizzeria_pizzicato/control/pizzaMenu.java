package pizzeria_pizzicato.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;

import pizzeria_pizzicato.model.Ostoskori;
import pizzeria_pizzicato.model.Pizza;
import pizzeria_pizzicato.model.TilattuTuote;
import pizzeria_pizzicato.model.dao.PizzaDAO;


@WebServlet("/pizzaMenu")
public class pizzaMenu extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
       

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
			PizzaDAO pizzadao = new PizzaDAO();
			ArrayList<Pizza> pizzaLista = pizzadao.findAll();
			ArrayList<Pizza> pizzaNakyy = new ArrayList<Pizza>();
			
			for(int i=0;i<pizzaLista.size();i++){
				
				if(pizzaLista.get(i).getNakyy()==1){
					Pizza pizza = new Pizza();
	        	
					pizza = pizzaLista.get(i);
					pizzaNakyy.add(pizza);
				}
			}
			
			HttpSession session = request.getSession();
			Ostoskori ostoskori = (Ostoskori) session.getAttribute("ostoskori");
			if(ostoskori == null){
				ostoskori = new Ostoskori();
				session.setAttribute("ostoskori", ostoskori);
				session.setMaxInactiveInterval(60*60);
			}
			
			System.out.println("Ostoskori! " + ostoskori);
			
			/*HttpSession sessionTilaus = request.getSession();
			sessionTilaus.setAttribute("testi", "testitietoa");
			sessionTilaus.setMaxInactiveInterval(30*60);
			Cookie testi = new Cookie("kayttaja", "testitietoa");
			testi.setMaxAge(30*60);
			response.addCookie(testi);*/
			
			
			
			request.setAttribute("pizzat", pizzaNakyy);
			
			
			String jsp = "/view/etusivu.jsp"; 
			RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
			dispather.forward(request, response);

	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Pizza> pizzaLista = (ArrayList<Pizza>) request.getAttribute("pizzat"); // haetaan pizzat

		
		//k‰sitell‰‰n formista tullut data
		System.out.println("pizzaID " + request.getAttribute("pizzaID"));
		System.out.println("oregano checkbox " + request.getAttribute("oregano"));
		System.out.println("vSipuli checkbox " + request.getAttribute("vSipuli"));
		
		int sArvo = Integer.parseInt((String) request.getAttribute("pizzaID")); //Haetaan ensin pizzaID
		int oregano = 0, vSipuli = 0;
		Pizza pizza = new Pizza();
		pizza = pizzaLista.get(sArvo); //luodaan pizzaID mukaan 
		
		HttpSession session = request.getSession(); //haetaan session
		Ostoskori ostoskori = (Ostoskori) session.getAttribute("ostoskori"); // haetaan ostoskori sessionista
		ostoskori.addPizza(pizza, oregano, vSipuli); //lis‰t‰‰n ostoskoriin pizza
		if(ostoskori != null){ //jos ostoskori ei ole tyhj‰ vied‰‰n ostoskori muutoksineen sessioniin
			session.setAttribute("ostoskori", ostoskori);
		}
		
		System.out.println("Ostoskoriin lis‰tty tuote! " + ostoskori);
		
		doGet(request, response);
		
	}
	
	
}