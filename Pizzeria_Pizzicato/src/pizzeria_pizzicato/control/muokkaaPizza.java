package pizzeria_pizzicato.control;

import java.sql.SQLException;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import pizzeria_pizzicato.model.Pizza;
import pizzeria_pizzicato.model.dao.PizzaDAO;

 
@WebServlet("/muokkaaPizza")
public class muokkaaPizza extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
        public muokkaaPizza() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String strNimi = request.getParameter("id");
						
		
		request.setAttribute("valittu", strNimi);
		String jsp = "/view/muokkaa-pizza.jsp"; 
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
		dispather.forward(request, response);
}
protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
	
			
	
	try{

		
		int id = 0;
		String nimi = request.getParameter("nimi"); 
		String StrHinta = request.getParameter("hinta");
		double hinta = new Double (StrHinta);
		String StrNakyy = request.getParameter("nakyy");
		int nakyy = new Integer(StrNakyy);
		
		Pizza pizza= new Pizza (id, nimi, hinta, nakyy);
		PizzaDAO pizzadao = new PizzaDAO();
					
		pizzadao.updatePizza(pizza);
		
					
		} catch (SQLException e) {
				
		System.out.println("Sovelluksessa tapahtui virhe "+ e.getMessage());
		}

	response.sendRedirect("listaaPizzat");
				
}
}


