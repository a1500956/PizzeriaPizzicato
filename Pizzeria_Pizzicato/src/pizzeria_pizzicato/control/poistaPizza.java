package pizzeria_pizzicato.control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pizzeria_pizzicato.model.Pizza;
import pizzeria_pizzicato.model.dao.PizzaDAO;




@WebServlet("/poista-pizza")
public class poistaPizza extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strID = request.getParameter("id");
						
		
		request.setAttribute("valittu", strID);
		String jsp = "/view/poista-pizza.jsp"; 
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
		dispather.forward(request, response);
}
		
		protected void doPost(HttpServletRequest request,
    			HttpServletResponse response) throws ServletException, IOException {
				
			String strID = request.getParameter("id");	
			
			try{

				int id = new Integer(strID);
				
				
				
				Pizza pizza = new Pizza (id, null, 0, 0);
				PizzaDAO pizzadao = new PizzaDAO();
							
				pizzadao.deletePizza(pizza);
				
							
				} catch (SQLException e) {
						
				System.out.println("Sovelluksessa tapahtui virhe "+ e.getMessage());
				}

			response.sendRedirect("listaaPizzat");
						
		}
		}
