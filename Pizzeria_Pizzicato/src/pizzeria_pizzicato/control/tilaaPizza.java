
	package pizzeria_pizzicato.control;

	import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

	import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;

import pizzeria_pizzicato.model.Pizza;
import pizzeria_pizzicato.model.Tuote;
import pizzeria_pizzicato.model.dao.PizzaDAO;


	@WebServlet("/tilaaPizza")
	public class tilaaPizza extends HttpServlet {
		
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
				
				request.setAttribute("pizzat", pizzaNakyy);
				
				String jsp = "/view/tilaa-pizza.jsp"; 
				RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
				dispather.forward(request, response);
				

		}
		
		protected void doPost(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			
			PizzaDAO pizzadao = new PizzaDAO();
			ArrayList<Pizza> pizzaLista = pizzadao.findAll();
			
			Pizza haettu = new Pizza();
			ArrayList<Tuote> Ttuotteet = new ArrayList<Tuote>();
			
			int lkm = 0;

			for (int i = 0; i < pizzaLista.size(); i++) {
				haettu = pizzaLista.get(i);
				lkm = Integer.parseInt(request.getParameter(Integer.toString(haettu.getId())));
				if(lkm != (int)lkm){
				doGet(request, response);
				}else if(lkm>0){
						Tuote tuote = new Tuote();
						tuote.setId(haettu.getId());
						tuote.setId(lkm);
						tuote.setHinta(haettu.getHinta());
						Ttuotteet.add(tuote);
					
					}
				
			}
			
			ArrayList<Pizza> plista = pizzadao.findAll();
			/*Pizza P1;
			Tuote T2;
			
			for (int i = 0; i < plista.size(); i++) {
				P1 = plista.get(i);
				for (int j = 0; j < tuotteet.size(); j++) {
					T2 = tuotteet.get(j);
					if(P1.getId()!=T2.getId()){
						T2.setNimi(P1.getNimi());
						tuotteet.set(j, T2);
					}
				}
			}*/
			
			
			for(int i = 0; i<Ttuotteet.size(); i++){ // haetaan tilattujen pizzojen nimet
				int sArvo = Ttuotteet.get(i).getId();
				for(int j = 0; j<plista.size(); j++){
					if(sArvo == plista.get(j).getId()){
						Ttuotteet.get(i).setNimi(plista.get(j).getNimi());
					}
				}
				
			}	
			if(Ttuotteet.isEmpty()){
			 doGet(request, response);
			}else{
				String jsp = "/view/vahvista-tilaus.jsp"; 
				request.setAttribute("tilauslista", Ttuotteet);
				RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
				dispather.forward(request, response);
			}
			
		}

	
	
	}
	

