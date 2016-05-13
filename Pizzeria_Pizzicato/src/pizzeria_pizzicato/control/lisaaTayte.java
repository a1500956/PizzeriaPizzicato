package pizzeria_pizzicato.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pizzeria_pizzicato.model.Lukija;
import pizzeria_pizzicato.model.Tayte;
import pizzeria_pizzicato.model.Tuote;
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
		
		String nimiStr = request.getParameter("nimi");
		String tayte_nimi = new String(nimiStr);

		TayteDAO TDAO = new TayteDAO();
		ArrayList<Tayte> tayteLista = TDAO.findAll();
		String viesti = null;
		String viesti3 = null;
		String nimiDB = null;
		
		
		boolean loytyy = false;
		
		for(int j = 0; j < tayteLista.size(); j++){
			
			if(tayte_nimi.equalsIgnoreCase(tayteLista.get(j).getTayte_nimi())){
				loytyy = true;
				nimiDB = tayteLista.get(j).getTayte_nimi();	
				viesti3 = nimiDB+ " niminen täyte on jo tietokannassa!";
	
		
				}
			}
			
	if(loytyy == false){
			

		try {
			int tayte_id = 0;

			
			String tayte_nimi_en = new String(request.getParameter("nimi_en"));

			String hintaStr = request.getParameter("hinta");
			double tayte_hinta = lukija.lueDesimaaliluku(hintaStr);

			Tayte tayte = new Tayte(tayte_id, tayte_nimi, tayte_nimi_en, tayte_hinta);
			TayteDAO taytedao = new TayteDAO();

			taytedao.addTayte(tayte);
			viesti = "Täytteen tallennus onnistui!";

		} catch (SQLException e) {

			System.out.println("Sovelluksessa tapahtui virhe " + e.getMessage());
		}

		if (viesti!=null){
			request.getSession().setAttribute("message", viesti);
			response.sendRedirect("listaa-taytteet");

			}else{

		response.sendRedirect("listaa-taytteet");

	}
		
	}
	
	
	if (viesti3!=null){
	request.getSession().setAttribute("message", viesti3);
	response.sendRedirect("listaa-taytteet");

	}else{

		response.sendRedirect("listaa-taytteet");
}
}
}
