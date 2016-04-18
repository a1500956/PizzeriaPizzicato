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
			Ostoskori kori = new Ostoskori();
			Pizza pizzaTuote = new Pizza();
			
			for(int i=0;i<pizzaLista.size();i++){
				
				if(pizzaLista.get(i).getNakyy()==1){
					Pizza pizza = new Pizza();
	        	
					pizza = pizzaLista.get(i);
					pizzaNakyy.add(pizza);
				}
			}
			
			pizzaTuote = pizzaLista.get(2);
			kori.addPizza(pizzaTuote, 0, 0);
			
			System.out.println("yksi tuote korissa " + kori);
			
			pizzaTuote = pizzaLista.get(2);
			kori.addPizza(pizzaTuote, 0, 0);
			
			System.out.println("kaksi tuotetta korissa toisella mausteet " + kori);
			
			pizzaTuote = pizzaLista.get(2);
			kori.addPizza(pizzaTuote, 0, 0);
			
			System.out.println("lisättiin ensimmäiseen +1 lkm " + kori);
			
			
			
			
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

}