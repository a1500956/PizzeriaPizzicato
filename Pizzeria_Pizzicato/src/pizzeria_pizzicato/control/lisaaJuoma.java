package pizzeria_pizzicato.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzeria_pizzicato.model.Juoma;
import pizzeria_pizzicato.model.Lukija;
import pizzeria_pizzicato.model.dao.JuomaDAO;


/**
 * Servlet implementation class lisaaJuoma
 */
@WebServlet("/lisaa-juoma")
public class lisaaJuoma extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Lukija lukija = new Lukija();
	
    public lisaaJuoma() {
        super();
        
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsp = "/view/lisaa-juoma.jsp";
		
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(
				jsp);
		dispather.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			
			String LKStr = request.getParameter("litrakoko");
			double litrakoko = lukija.lueDesimaaliluku(LKStr);
			
			if(hinta<1){String viesti2 = "Juoman hinnan täytyy olla vähintään euron!";
			request.getSession().setAttribute("message2", viesti2);
			response.sendRedirect("lisaa-juoma");
			
			
			}else{
			
			String nakyyStr = request.getParameter("nakyy");
			int nakyy = new Integer(nakyyStr);


			Juoma juoma = new Juoma(id, nimi, hinta, litrakoko, nakyy);
			JuomaDAO juomadao = new JuomaDAO();
			
			juomadao.addJuoma(juoma);
			viesti = "Pizzan tallennus onnistui!";


			}

		} catch (SQLException e) {

			System.out.println("Sovelluksessa tapahtui virhe " + e.getMessage());
		}

		if (viesti!=null){
		request.getSession().setAttribute("message", viesti);
		response.sendRedirect("listaaJuomat");

		

	}
	}

}
