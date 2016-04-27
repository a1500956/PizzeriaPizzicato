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
import pizzeria_pizzicato.model.Tayte;
import pizzeria_pizzicato.model.TilattuTuote;
import pizzeria_pizzicato.model.dao.PizzaDAO;
import pizzeria_pizzicato.model.dao.JuomaDAO;
import pizzeria_pizzicato.model.Juoma;


@WebServlet("/pizzaMenu")
public class pizzaMenu extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
       

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
			PizzaDAO pizzadao = new PizzaDAO();
			ArrayList<Pizza> pizzaLista = pizzadao.findAll();
			ArrayList<Pizza> pizzaNakyy = new ArrayList<Pizza>();
			ArrayList<Pizza> pizzaFantasia = new ArrayList<Pizza>();
			
			
			for(int i=0;i<pizzaLista.size();i++){
				
				if(pizzaLista.get(i).getNakyy()==1 && !pizzaLista.get(i).getNimi().contains("Fantasia")){
					Pizza pizza = new Pizza();
	        	
					pizza = pizzaLista.get(i);
					pizzaNakyy.add(pizza);
				}if(pizzaLista.get(i).getNakyy()==1 && pizzaLista.get(i).getNimi().contains("Fantasia")){
					Pizza pizza = new Pizza();
	        	
					pizza = pizzaLista.get(i);
					pizzaFantasia.add(pizza);
				}
			}
			
			JuomaDAO juomadao = new JuomaDAO();
			ArrayList<Juoma> JuomaLista = juomadao.findAll();
			ArrayList<Juoma> juomaNakyy = new ArrayList<Juoma>();
			
			for(int i=0;i<JuomaLista.size();i++){
				
				if(JuomaLista.get(i).getNakyy()==1){
					Juoma juoma = new Juoma();
	        	
					juoma = JuomaLista.get(i);
					juomaNakyy.add(juoma);
				}
			}
			
			HttpSession session = request.getSession();
			Ostoskori ostoskori = (Ostoskori) session.getAttribute("ostoskori");
			if(ostoskori == null){
				ostoskori = new Ostoskori();
				session.setAttribute("ostoskori", ostoskori);
				session.setMaxInactiveInterval(60*60);
			}
			
			request.setAttribute("pizzat", pizzaNakyy);
			request.setAttribute("pizzaFantasia", pizzaFantasia);
			request.setAttribute("juomat", juomaNakyy);
			
			String jsp = "/view/etusivu.jsp"; 
			RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
			dispather.forward(request, response);

	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		PizzaDAO pizzadao = new PizzaDAO();
		ArrayList<Pizza> pizzaLista = pizzadao.findAll();// haetaan pizzat

		
		//k‰sitell‰‰n formista tullut data
		
		int sArvo = Integer.parseInt((String) request.getParameter("pizzaID")); //Haetaan ensin pizzaID
		int oregano = 0, vSipuli = 0;
		if(request.getParameter("oregano") != null){ //haetaan oregano jos null j‰tet‰‰n 0
			oregano = 1;
		}
		if(request.getParameter("vSipuli") != null){ //haetaan vSipuli jos null j‰tet‰‰n 0
			vSipuli = 1;
		}
		
		Pizza pizza = new Pizza();
		for(int i=0; i<pizzaLista.size();i++){ //luodaan pizzaID mukaan pizza
			if(pizzaLista.get(i).getId() == sArvo){
				pizza = pizzaLista.get(i);
			}
		}
		
		HttpSession session = request.getSession(); //haetaan session
		Ostoskori ostoskori = (Ostoskori) session.getAttribute("ostoskori"); // haetaan ostoskori sessionista
		ostoskori.addPizza(pizza, oregano, vSipuli); //lis‰t‰‰n ostoskoriin pizza
		if(ostoskori != null){ //jos ostoskori ei ole tyhj‰ vied‰‰n ostoskori muutoksineen sessioniin
			session.setAttribute("ostoskori", ostoskori);
		}
		
		doGet(request, response); // palaa takaisin servletin alkuun
		
	}
	
	
}