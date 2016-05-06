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
import pizzeria_pizzicato.model.Kayttaja;
import pizzeria_pizzicato.model.Pizza;
import pizzeria_pizzicato.model.dao.JuomaDAO;
import pizzeria_pizzicato.model.dao.KayttajaDAO;
import pizzeria_pizzicato.model.dao.PizzaDAO;


@WebServlet("/poistaTyontekija")
public class poistaTyontekija extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public poistaTyontekija() {
        super();
    }

	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String strID = request.getParameter("id");
		String strNimi = request.getParameter("id2");
		request.setAttribute("valittuId", strID);
		request.setAttribute("valittuNimi", strNimi);
		
		String jsp = "/view/poista-tyontekija.jsp"; 
		
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
		dispather.forward(request, response);
}
	
	
		protected void doPost(HttpServletRequest request,
    			HttpServletResponse response) throws ServletException, IOException {
				
			String strID = request.getParameter("id");
			
			
			
			int id = new Integer(strID);
			
			
			
			Kayttaja tyontekija = new Kayttaja (id, null, null, null, null, null, null, null, id);
			KayttajaDAO tyontekijadao = new KayttajaDAO();
						
			tyontekijadao.delete(tyontekija);

			response.sendRedirect("listaaTyontekijat");
						
		}
		}

