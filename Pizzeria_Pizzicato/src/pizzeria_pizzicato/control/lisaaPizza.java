package pizzeria_pizzicato.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pizzeria_pizzicato.model.Lukija;
import pizzeria_pizzicato.model.Pizza;
import pizzeria_pizzicato.model.dao.PizzaDAO;
import pizzeria_pizzicato.model.dao.PizzaTayteDAO;

@WebServlet("/lisaa-pizza")
public class lisaaPizza extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Lukija lukija = new Lukija();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		
		String jsp = "/view/lisaa-pizza.jsp"; 
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
		dispather.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try{
			int id = 0;  
			
			String nimiStr = request.getParameter("nimi");
			String nimi = new String(nimiStr);
			
			String eka = nimi.substring(0,1).toUpperCase();
			String loput = nimi.substring(1).toLowerCase();
			nimi = eka+loput;
			
			String hintaStr = request.getParameter("hinta");
			double hinta = lukija.lueDesimaaliluku(hintaStr);
			String nakyyStr = request.getParameter("nakyy");
			int nakyy = new Integer(nakyyStr);
			
			String[] taytteetStr = request.getParameterValues("tayte");
			//int[] taytteet = new int[taytteetStr.length];
			
			//for (int i = 0; i < taytteetStr.length; i++) {
			//	taytteet[i] = Integer.parseInt(taytteetStr[i]);
			//}
			
			//System.out.println(taytteet.toString());
						
						Pizza pizza = new Pizza(id, nimi, hinta, nakyy);
						PizzaDAO pizzadao = new PizzaDAO();					
						PizzaTayteDAO pizzaTaytedao = new PizzaTayteDAO();
						
						pizzadao.addPizza(pizza);
						
						int pId = pizzadao.getPizzaId(nimiStr);
						
						for (int i = 0; i < taytteetStr.length; i++) {
							pizzaTaytedao.addTayteToPizza(Integer.parseInt(taytteetStr[i]),pId);
						}
						
						
					} catch (SQLException e) {
						
						System.out.println("Sovelluksessa tapahtui virhe "+ e.getMessage());
					}
				
					response.sendRedirect("listaaPizzat");
					
	}
}
					