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
		
		
		TayteDAO TDAO = new TayteDAO();
		Tayte kasittelyssa = TDAO.getTayte(Integer.parseInt(request.getParameter("id")));
						
		
		request.setAttribute("valittu", request.getParameter("id"));
		request.setAttribute("valittuN", kasittelyssa.getTayte_nimi());
		request.setAttribute("valittuN_en", kasittelyssa.getTayte_nimi_en());
		request.setAttribute("valittuH", Double.toString(kasittelyssa.getTayte_hinta()));
		String jsp = "/view/muokkaa-tayte.jsp"; 
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
		dispather.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String viesti = null;
		
		try{

			int tayte_id = Integer.parseInt(request.getParameter("id"));
			String tayte_nimi = new String(request.getParameter("nimi"));
			String tayte_nimi_en = new String(request.getParameter("nimi_en"));
			
			String StrHinta = request.getParameter("hinta");
			StrHinta = StrHinta.replace(",", ".");
			double tayte_hinta = Double.parseDouble(StrHinta);
			
			
			Tayte tayte = new Tayte(tayte_id, tayte_nimi, tayte_nimi_en, tayte_hinta);
			
			TayteDAO taytedao = new TayteDAO();
						
			taytedao.updateTayte(tayte);
			viesti="Täytteen muokkaaminen onnistui!";
			
						
			} catch (SQLException e) {
					
			System.out.println("Sovelluksessa tapahtui virhe "+ e.getMessage());
			}
		
		if (viesti!=null){
			request.getSession().setAttribute("message", viesti);
			response.sendRedirect("listaa-taytteet");

			}else{

		response.sendRedirect("listaa-taytteet");
					
	}

		
}
}


