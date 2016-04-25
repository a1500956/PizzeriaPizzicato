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
import pizzeria_pizzicato.model.dao.JuomaDAO;



@WebServlet("/muokkaa-juoma")
public class muokkaaJuoma extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public muokkaaJuoma() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		JuomaDAO juomadao = new JuomaDAO();
		int iidee = Integer.parseInt(request.getParameter("id"));
		
		Juoma kyseessa = juomadao.getJuoma(iidee);
		
		request.setAttribute("valittuID", iidee);
		request.setAttribute("valittuN", kyseessa.getNimi());
		request.setAttribute("valittuH", Double.toString(kyseessa.getHinta()));
		request.setAttribute("litrakoko", Double.toString(kyseessa.getLitrakoko()));
		request.setAttribute("nakyykovaiei", kyseessa.getNakyy());
		
		String jsp = "/view/muokkaa-juoma.jsp";
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(
				jsp);
		dispather.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String viesti = null;
		int id = Integer.parseInt(request.getParameter("id"));
		String nimi = request.getParameter("nimi");

		String eka = nimi.substring(0, 1).toUpperCase();
		String loput = nimi.substring(1).toLowerCase();
		nimi = eka + loput;

		String StrHinta = request.getParameter("hinta");
		StrHinta = StrHinta.replace(",", ".");
		double hinta = Double.parseDouble(StrHinta);
		String Stringlitrakoko = request.getParameter("litrakoko");
		Stringlitrakoko = Stringlitrakoko.replace(",", ".");
		double litrakoko = Double.parseDouble(Stringlitrakoko);
		String StrNakyy = request.getParameter("nakyy");
		int nakyy = new Integer(StrNakyy);

		Juoma juoma = new Juoma(id, nimi, hinta, litrakoko, nakyy);
		JuomaDAO juomadao = new JuomaDAO();

		// Juoman tiedot p‰ivitet‰‰n
		try {
			juomadao.updateJuoma(juoma);
			viesti="Juoman muokkaaminen onnistui!";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (viesti!=null){
			request.getSession().setAttribute("message", viesti);
			response.sendRedirect("listaaJuomat");

			}else{
				
		response.sendRedirect("listaaJuomat");
		

			}
	}
}
