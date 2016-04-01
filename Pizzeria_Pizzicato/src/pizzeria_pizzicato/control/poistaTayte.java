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




@WebServlet("/poista-tayte")
public class poistaTayte extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String strID = request.getParameter("id");
		String strNimi = request.getParameter("id2");
		request.setAttribute("valittuId", strID);
		request.setAttribute("valittuNimi", strNimi);
		
		String jsp = "/view/poista-tayte.jsp"; 
		
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
		dispather.forward(request, response);
}
		
		protected void doPost(HttpServletRequest request,
    			HttpServletResponse response) throws ServletException, IOException {
				
			String strID = request.getParameter("id");
			
			
			
			try{

				int tayte_id = new Integer(strID);
				
				
				
				Tayte tayte = new Tayte (tayte_id, null, null, 0);
				TayteDAO taytedao = new TayteDAO();
							
				taytedao.deleteTayte(tayte);
				
							
				} catch (SQLException e) {
						
				System.out.println("Sovelluksessa tapahtui virhe "+ e.getMessage());
				}

			response.sendRedirect("listaa-taytteet");
						
		}
		}
