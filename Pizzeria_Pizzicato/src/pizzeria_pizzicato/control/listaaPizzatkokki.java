package pizzeria_pizzicato.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pizzeria_pizzicato.model.Kayttaja;
import pizzeria_pizzicato.model.Pizza;
import pizzeria_pizzicato.model.Tayte;
import pizzeria_pizzicato.model.Tilaus;
import pizzeria_pizzicato.model.dao.KayttajaDAO;
import pizzeria_pizzicato.model.dao.PizzaDAO;
import pizzeria_pizzicato.model.dao.PizzaTayteDAO;
import pizzeria_pizzicato.model.dao.TayteDAO;
import pizzeria_pizzicato.model.dao.TilausDAO;

@WebServlet("/listaaPizzatkokki")
public class listaaPizzatkokki extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PizzaDAO pizzadao = new PizzaDAO();
		ArrayList<Pizza> pizzaLista = pizzadao.findAll();
		
		TilausDAO tilausdao = new TilausDAO();
		ArrayList<Tilaus> tilaukset = new ArrayList<Tilaus>(tilausdao.haeAktiivisetTilaukset());
		request.setAttribute("tilaukset", tilaukset);
		

		request.setAttribute("pizzat", pizzaLista);
		
		String jsp = "/view/listaa-kokki.jsp";
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(
				jsp);
		dispather.forward(request, response);

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String viesti = null;
		
		
		try {
		
		
		int id = Integer.parseInt(request.getParameter("id"));
		String aika = request.getParameter("aika");
		String statusNimi=request.getParameter("statusNimi");
		String tuoteNimi=request.getParameter("tuoteNimi");
		int lukumaara = Integer.parseInt(request.getParameter("lukumaara"));
		String ktunnus=request.getParameter(ktunnus);
		
	
		
		Tilaus tilaus= new Tilaus(id, null, aika, statusNimi, lukumaara, lukumaara, tuoteNimi,ktunnus, lukumaara,ktunnus);
		TilausDAO tilausdao = new TilausDAO();

		
		
		
		// Pizzan tiedot p‰ivitet‰‰n
		tilausdao.updateTilaus(ktunnus);

	

							
				} catch (SQLException e) {
						
				System.out.println("Sovelluksessa tapahtui virhe "+ e.getMessage());
				}

			response.sendRedirect("listaaPizzat");
						
		}
		


}
