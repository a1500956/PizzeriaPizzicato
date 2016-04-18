package pizzeria_pizzicato.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzeria_pizzicato.model.dao.TilausDAO;
import pizzeria_pizzicato.model.Tilaus;

@WebServlet("/listaaAktiivisetTilaukset")
public class listaaAktiivisetTilaukset extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TilausDAO tilausdao = new TilausDAO();
		ArrayList<Tilaus> tilaukset = new ArrayList<Tilaus>(tilausdao.haeAktiivisetTilaukset());
		request.setAttribute("tilaukset", tilaukset);
		
		String jsp = "/view/listaa-aktiivisetTilaukset.jsp"; 
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
		dispather.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
