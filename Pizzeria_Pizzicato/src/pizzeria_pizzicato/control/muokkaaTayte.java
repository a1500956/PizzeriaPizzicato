package pizzeria_pizzicato.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzeria_pizzicato.model.Tayte;
import pizzeria_pizzicato.model.dao.TayteDAO;


@WebServlet("/muokkaa-tayte")
public class muokkaaTayte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public muokkaaTayte() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String strID = request.getParameter("id");
		String strNimi = request.getParameter("id2");
		String strHinta = request.getParameter("id3");
						
		
		request.setAttribute("valittu", strID);
		request.setAttribute("valittuN", strNimi);
		request.setAttribute("valittuH", strHinta);
		String jsp = "/view/muokkaa-tayte.jsp"; 
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
		dispather.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String strNimi = request.getParameter("id2");
		
		
		try{

			int tayte_id = Integer.parseInt(request.getParameter("id"));
			String tayte_nimi = new String(strNimi);
			
			String StrHinta = request.getParameter("hinta");
			StrHinta = StrHinta.replace(",", ".");
			double tayte_hinta = Double.parseDouble(StrHinta);
			
			
			Tayte tayte = new Tayte(tayte_id, tayte_nimi, tayte_hinta);
			TayteDAO taytedao = new TayteDAO();
						
			taytedao.updateTayte(tayte);
			
						
			} catch (SQLException e) {
					
			System.out.println("Sovelluksessa tapahtui virhe "+ e.getMessage());
			}

		response.sendRedirect("listaa-taytteet");
					
	}

		
}


