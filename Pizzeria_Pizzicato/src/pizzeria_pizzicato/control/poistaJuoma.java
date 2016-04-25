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


@WebServlet("/poista-juoma")
public class poistaJuoma extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public poistaJuoma() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strID = request.getParameter("id");
		String strNimi = request.getParameter("id2");
		request.setAttribute("valittuId", strID);
		request.setAttribute("valittuNimi", strNimi);
		
		String jsp = "/view/poista-juoma.jsp"; 
		
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
		dispather.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strID = request.getParameter("id");
		
		
		
		try{

			int id = new Integer(strID);
			
			
			
			Juoma juoma = new Juoma (id, null, 0, 0, 0);
			JuomaDAO jumanjidao = new JuomaDAO();
						
			jumanjidao.deleteJuoma(juoma);
			
						
			} catch (SQLException e) {
					
			System.out.println("Sovelluksessa tapahtui virhe "+ e.getMessage());
			}

		response.sendRedirect("listaaJuomat");
	}

}
