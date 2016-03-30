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


@WebServlet("/pizzaMenu")
public class pizzaMenu extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
       

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
			PizzaDAO pizzadao = new PizzaDAO();
			ArrayList<Pizza> pizzaLista = pizzadao.findAll();
			ArrayList<Pizza> pizzaNakyy = new ArrayList<Pizza>();
			
			for(int i = 0; i<pizzaLista.size(); i++){
				System.out.println(pizzaLista.get(i));
			}
			
			for(int i=0;i<pizzaLista.size();i++){
				
				if(pizzaLista.get(i).getNakyy()==1){
					Pizza pizza = new Pizza();
	        	
					pizza = pizzaLista.get(i);
					pizzaNakyy.add(pizza);
				}
			}
			
			request.setAttribute("pizzat", pizzaNakyy);
			
			
			String jsp = "/view/etusivu.jsp"; 
			RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
		dispather.forward(request, response);

	}

}