package pizzeria_pizzicato.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import pizzeria_pizzicato.model.Pizza;
import pizzeria_pizzicato.model.Tayte;
import pizzeria_pizzicato.model.dao.PizzaDAO;
import pizzeria_pizzicato.model.dao.PizzaTayteDAO;
import pizzeria_pizzicato.model.dao.TayteDAO;

@WebServlet("/muokkaa-pizza")
public class muokkaaPizza extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public muokkaaPizza() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PizzaDAO Pdao = new PizzaDAO();
		PizzaTayteDAO PTdao = new PizzaTayteDAO();
		TayteDAO Tdao = new TayteDAO();

		int iidee = Integer.parseInt(request.getParameter("id"));

		Pizza kyseessa = Pdao.getPizza(iidee);
		request.setAttribute("valittuID", iidee);
		request.setAttribute("valittuN", kyseessa.getNimi());
		request.setAttribute("valittuH", Double.toString(kyseessa.getHinta()));
		request.setAttribute("nakyykovaiei", kyseessa.getNakyy());

		ArrayList<Tayte> pizzanTaytteittenIDt = new ArrayList<Tayte>();
		pizzanTaytteittenIDt = PTdao.haePizzanTaytteet(iidee);
		ArrayList<Tayte> tietokannanTaytteet = new ArrayList<Tayte>();
		tietokannanTaytteet = Tdao.findAll();
		ArrayList<String> taytteittenEdelleenlahetettavaNimilista = new ArrayList<String>();

		for (int i = 0; i < pizzanTaytteittenIDt.size(); i++) {
			for (int j = 0; j < tietokannanTaytteet.size(); j++) {
				if ((pizzanTaytteittenIDt.get(i).getTayte_id()) == tietokannanTaytteet.get(j).getTayte_id()) {
					taytteittenEdelleenlahetettavaNimilista.add(tietokannanTaytteet.get(j).getTayte_nimi());
				}
			}
		}

		request.setAttribute("taytteet",
				taytteittenEdelleenlahetettavaNimilista);
		String jsp = "/view/muokkaa-pizza.jsp";
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(
				jsp);
		dispather.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String viesti = null;

		try {

			int id = Integer.parseInt(request.getParameter("id"));
			String nimi = request.getParameter("nimi");

			String eka = nimi.substring(0, 1).toUpperCase();
			String loput = nimi.substring(1).toLowerCase();
			nimi = eka + loput;

			String StrHinta = request.getParameter("hinta");
			StrHinta = StrHinta.replace(",", ".");
			double hinta = Double.parseDouble(StrHinta);
			String StrNakyy = request.getParameter("nakyy");
			int nakyy = new Integer(StrNakyy);

			Pizza pizza = new Pizza(id, nimi, hinta, nakyy);
			PizzaDAO pizzadao = new PizzaDAO();
			PizzaTayteDAO PTdao = new PizzaTayteDAO();

			// Pizzan tiedot päivitetään
			pizzadao.updatePizza(pizza);

			// Pizzan täytteet hävitetään warppiin PizzaTäyte-taulukosta,
			// toistaiseksi voimassaolevuudesta huolimatta
			PTdao.poistaPizzaTaytelistalta(id);

			// Valitut täytteet lisätään
			String[] taytteetStr = request.getParameterValues("tayte");
			for (int i = 0; i < taytteetStr.length; i++) {
				PTdao.addTayteToPizza(Integer.parseInt(taytteetStr[i]), id);
			}
			viesti = "y";

		} catch (SQLException e) {

			System.out
					.println("Sovelluksessa tapahtui virhe " + e.getMessage());
		}

		HttpSession session = request.getSession();

		session.setAttribute("viesti", viesti);

		response.sendRedirect("listaaPizzat");

	}
}
