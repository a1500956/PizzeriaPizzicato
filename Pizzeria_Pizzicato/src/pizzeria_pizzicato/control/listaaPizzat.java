package pizzeria_pizzicato.control;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzeria_pizzicato.model.Pizza;
import pizzeria_pizzicato.model.PizzaTayte;
import pizzeria_pizzicato.model.Tayte;
import pizzeria_pizzicato.model.Taytteet;
import pizzeria_pizzicato.model.dao.PizzaDAO;
import pizzeria_pizzicato.model.dao.PizzaTayteDAO;
import pizzeria_pizzicato.model.dao.TayteDAO;



@WebServlet("/listaaPizzat")
public class listaaPizzat extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		PizzaDAO pizzadao = new PizzaDAO();
		ArrayList<Pizza> pizzaLista = pizzadao.findAll();
		
		TayteDAO taytedao = new TayteDAO();
		ArrayList<Tayte> tayteLista = taytedao.findAll();
		
		PizzaTayteDAO pTaytedao = new PizzaTayteDAO();
		ArrayList<PizzaTayte> pTaytteet = pTaytedao.findAll();
		ArrayList<Taytteet> pizzat = new ArrayList<Taytteet>();
		ArrayList<Pizza> listaaPizzat = new ArrayList<Pizza>();
		
		
		
		boolean loytyy = false;
		
		for(int i = 0; i < pTaytteet.size(); i++){
			Taytteet taytteet = new Taytteet();				
			taytteet.setpId(pTaytteet.get(i).getpId());
			
			for(int j = 0; j < pizzat.size(); j++){
				if(pizzat.get(j).getpId() == taytteet.getpId()){
					loytyy = true;
				}else{
					loytyy = false;
				}
			}
			
			if(loytyy == false){
				pizzat.add(taytteet);	
			}
		}
		
		for(int i = 0; i < pizzat.size(); i++){
			
			for(int j = 0; j < pTaytteet.size(); j++){
				if(pizzat.get(i).getpId() == pTaytteet.get(j).getpId()){
					pizzat.get(i).getTaytteet().add(Integer.toString(pTaytteet.get(j).gettId()));
				}
			}
			
		}
		
		for(int i=0; i < pizzat.size(); i++){
			listaaPizzat.add(new Pizza());
			listaaPizzat.get(i).setId(pizzat.get(i).getpId()); // PizzaMenulle pizzanID
			
			for(int j = 0; j<pizzaLista.size();j++){ //PizzaMenulle pizzan nimi ID mukaan
				if(pizzat.get(i).getpId() == pizzaLista.get(j).getId()){
					listaaPizzat.get(i).setNimi(pizzaLista.get(j).getNimi());
					listaaPizzat.get(i).setNakyy(pizzaLista.get(j).getNakyy());
					listaaPizzat.get(i).setHinta(pizzaLista.get(j).getHinta());
				}
			}
			
			for(int h = 0; h<pizzat.get(i).getTaytteet().size(); h++){
				for(int k = 0; k<tayteLista.size(); k++){
					if(pizzat.get(i).getTaytteet().get(h).equals(Integer.toString(tayteLista.get(k).getTayte_id()))){
						Tayte sArvo = new Tayte();
						sArvo.setTayte_id(tayteLista.get(k).getTayte_id());
						sArvo.setTayte_nimi(tayteLista.get(k).getTayte_nimi());
						listaaPizzat.get(i).getTaytteet().add(sArvo);
					}
				}
				
			}
		}
		
			request.setAttribute("pizzat", listaaPizzat);
				
			String jsp = "/view/listaa-pizzat.jsp"; 
			RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
			dispather.forward(request, response);
		
		}
}

			
