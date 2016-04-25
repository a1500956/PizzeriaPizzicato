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

import pizzeria_pizzicato.model.Kayttaja;
import pizzeria_pizzicato.model.Ostoskori;
import pizzeria_pizzicato.model.Pizza;
import pizzeria_pizzicato.model.Tayte;
import pizzeria_pizzicato.model.dao.PizzaDAO;
import pizzeria_pizzicato.model.dao.PizzaTayteDAO;
import pizzeria_pizzicato.model.dao.TayteDAO;
import pizzeria_pizzicato.model.dao.TilausDAO;
import pizzeria_pizzicato.model.Tuote;
import pizzeria_pizzicato.model.TilattuTuote;
import pizzeria_pizzicato.model.Tilaus;

@WebServlet("/vahvistaTilaus")
public class vahvistaTilaus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doget");
		PizzaDAO pizzadao = new PizzaDAO();
		ArrayList<Pizza> pizzaLista = pizzadao.findAll();
		
	
		
		
		
		HttpSession session = request.getSession();
		Ostoskori ostoskori = (Ostoskori) session.getAttribute("ostoskori");
		if(ostoskori == null){
			ostoskori = new Ostoskori();
			session.setAttribute("ostoskori", ostoskori);
			session.setMaxInactiveInterval(60*60);
		}
		
		String jsp = "/view/vahvista-tilaus.jsp"; 
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
		dispather.forward(request, response);
		
		
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean ok = true;
		String virhe = null;
		
		
		TilausDAO TDAO = new TilausDAO();
		String enimi = request.getParameter("enimi");
		String snimi = request.getParameter("snimi");
		String osoite = request.getParameter("osoite");
		String puhnro = request.getParameter("puhnro");
		
		if(enimi.isEmpty() || snimi.isEmpty()){
			virhe = "Nimi kentät ovat pakollisia!";
			ok = false;
			request.getSession().setAttribute("message4", virhe);
			response.sendRedirect("vahvistaTilaus");
		
		
		}else if(osoite.isEmpty()){
		virhe = "Osoite kenttä on pakollinen!";
		ok = false;
		request.getSession().setAttribute("message4", virhe);
		response.sendRedirect("vahvistaTilaus");
		}else if(puhnro.matches("\\w{9,10}") == false && puhnro.isEmpty() && ok == true){
			ok = false;
			virhe = "Puhelinnumero on virheellinen!";
			request.getSession().setAttribute("message4", virhe);
			response.sendRedirect("vahvistaTilaus");
		}else if(ok == true){
			Ostoskori tuotteet = (Ostoskori) request.getSession().getAttribute("ostoskori");
		
		Kayttaja valiaikainen = new Kayttaja();
		valiaikainen.setKayttaja_id(404);
		Tilaus T = new Tilaus();
		
		T.setKayttaja(valiaikainen);
		
		T.setOsoite(osoite);
		T.setPuhnro(puhnro);
		
		try {
			TDAO.addTilaus(T, tuotteet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
		response.sendRedirect("pizzaMenu");
		
		}else{
		
		request.getSession().setAttribute("message4", virhe);
		response.sendRedirect("vahvistaTilaus");
		
	}
		
		}
		
	}











