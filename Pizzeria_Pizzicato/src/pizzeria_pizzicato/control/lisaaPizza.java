package pizzeria_pizzicato.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pizzeria_pizzicato.model.Kayttaja;
import pizzeria_pizzicato.model.Lukija;
import pizzeria_pizzicato.model.Pizza;
import pizzeria_pizzicato.model.dao.KayttajaDAO;
import pizzeria_pizzicato.model.dao.PizzaDAO;
import pizzeria_pizzicato.model.dao.PizzaTayteDAO;

@WebServlet("/lisaa-pizza")
public class lisaaPizza extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Lukija lukija = new Lukija();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		

		String jsp = "/view/lisaa-pizza.jsp";
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(
				jsp);
		dispather.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		String viesti = null;

		try {
			int id = 0;

			String nimiStr = request.getParameter("nimi");
			String nimi = new String(nimiStr);

			String eka = nimi.substring(0, 1).toUpperCase();
			String loput = nimi.substring(1).toLowerCase();
			nimi = eka + loput;

			String hintaStr = request.getParameter("hinta");
			double hinta = lukija.lueDesimaaliluku(hintaStr);
			String nakyyStr = request.getParameter("nakyy");
			int nakyy = new Integer(nakyyStr);

			String[] taytteetStr = request.getParameterValues("tayte");

			Pizza pizza = new Pizza(id, nimi, hinta, nakyy);
			PizzaDAO pizzadao = new PizzaDAO();
			PizzaTayteDAO pizzaTaytedao = new PizzaTayteDAO();

			pizzadao.addPizza(pizza);
			viesti = "Pizzan tallennus onnistui!";

			int pId = pizzadao.getPizzaId(nimiStr);

			for (int i = 0; i < taytteetStr.length; i++) {
				pizzaTaytedao.addTayteToPizza(Integer.parseInt(taytteetStr[i]),
						pId);

			}

		} catch (SQLException e) {

			System.out
					.println("Sovelluksessa tapahtui virhe " + e.getMessage());
		}

		if (viesti!=null){
		request.getSession().setAttribute("message", viesti);
		response.sendRedirect("listaaPizzat");

		}else{

		response.sendRedirect("listaaPizzat");

	}
}
}
