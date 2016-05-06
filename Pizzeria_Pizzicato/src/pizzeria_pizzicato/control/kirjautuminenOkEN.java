package pizzeria_pizzicato.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;

import pizzeria_pizzicato.model.Juoma;
import pizzeria_pizzicato.model.Ostoskori;
import pizzeria_pizzicato.model.Pizza;
import pizzeria_pizzicato.model.dao.PizzaDAO;


@WebServlet("/kirjautuminenOkEN")
public class kirjautuminenOkEN extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

       

    
    public kirjautuminenOkEN() {
        super();
        
    }


	
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
			
			
			String jsp = "/view/kirjautuminen-ok-en.jsp"; 
			RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
		dispather.forward(request, response);

}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}
}
