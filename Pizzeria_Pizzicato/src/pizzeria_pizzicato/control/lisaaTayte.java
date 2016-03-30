package pizzeria_pizzicato.control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pizzeria_pizzicato.model.Lukija;
import pizzeria_pizzicato.model.Tayte;
import pizzeria_pizzicato.model.dao.TayteDAO;

@WebServlet("/lisaa-tayte")
public class lisaaTayte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Lukija lukija = new Lukija();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String jsp = "/view/lisaa-tayte.jsp";
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(
				jsp);
		dispather.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String viesti = null;

		try {
			int tayte_id = 0;

			String nimiStr = request.getParameter("nimi");
			String tayte_nimi = new String(nimiStr);

			String hintaStr = request.getParameter("hinta");
			double tayte_hinta = lukija.lueDesimaaliluku(hintaStr);

			Tayte tayte = new Tayte(tayte_id, tayte_nimi, tayte_hinta);
			TayteDAO taytedao = new TayteDAO();

			taytedao.addTayte(tayte);
			viesti = "y";

		} catch (SQLException e) {

			System.out
					.println("Sovelluksessa tapahtui virhe " + e.getMessage());
		}

		HttpSession session = request.getSession();

		session.setAttribute("viesti", viesti);

		response.sendRedirect("lisaa-tayte");

	}
}
