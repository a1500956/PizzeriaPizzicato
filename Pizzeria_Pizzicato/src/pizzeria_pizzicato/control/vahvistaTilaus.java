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
import javax.websocket.Session;

import pizzeria_pizzicato.model.Pizza;
import pizzeria_pizzicato.model.Tayte;
import pizzeria_pizzicato.model.dao.PizzaDAO;
import pizzeria_pizzicato.model.dao.PizzaTayteDAO;
import pizzeria_pizzicato.model.dao.TayteDAO;
import pizzeria_pizzicato.model.dao.TilausDAO;
import pizzeria_pizzicato.model.Tuote;
import pizzeria_pizzicato.model.Tilaus;

@WebServlet("/vahvistaTilaus")
public class vahvistaTilaus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		TilausDAO TDAO = new TilausDAO();
		String osoite = request.getParameter("osoite");
		String puhnro = request.getParameter("puhnro");
		ArrayList<Tuote> tuotteet = (ArrayList) request.getSession().getAttribute("tilauslista");
		request.getSession().removeAttribute("tilauslista");
		
		Tilaus T = new Tilaus();
		
		T.setKayttajaID(404);
		T.setOsoite(osoite);
		T.setPuhnro(puhnro);
		
		try {
			TDAO.addTilaus(T, tuotteet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("etusivu.jsp");
		
		
		
	}

}
