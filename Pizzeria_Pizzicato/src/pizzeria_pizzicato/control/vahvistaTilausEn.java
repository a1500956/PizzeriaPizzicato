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

import pizzeria_pizzicato.model.Juoma;
import pizzeria_pizzicato.model.Kayttaja;
import pizzeria_pizzicato.model.Ostoskori;
import pizzeria_pizzicato.model.Pizza;
import pizzeria_pizzicato.model.Tayte;
import pizzeria_pizzicato.model.dao.JuomaDAO;
import pizzeria_pizzicato.model.dao.PizzaDAO;
import pizzeria_pizzicato.model.dao.PizzaTayteDAO;
import pizzeria_pizzicato.model.dao.TayteDAO;
import pizzeria_pizzicato.model.dao.TilausDAO;
import pizzeria_pizzicato.model.Tuote;
import pizzeria_pizzicato.model.TilattuTuote;
import pizzeria_pizzicato.model.Tilaus;

@WebServlet("/vahvistaTilausEn")
public class vahvistaTilausEn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PizzaDAO pizzadao = new PizzaDAO();
		ArrayList<Pizza> pizzaLista = pizzadao.findAll();
		
		HttpSession session = request.getSession();
		Ostoskori ostoskori = (Ostoskori) session.getAttribute("ostoskori");
		if(ostoskori == null){
			ostoskori = new Ostoskori();
			session.setAttribute("ostoskori", ostoskori);
			session.setMaxInactiveInterval(60*60);
		}
		
		JuomaDAO juomadao = new JuomaDAO();
		ArrayList<Juoma> JuomaLista = juomadao.findAll();
		ArrayList<Juoma> juomaNakyy = new ArrayList<Juoma>();
		
		for(int i=0;i<JuomaLista.size();i++){
			
			if(JuomaLista.get(i).getNakyy()==1){
				Juoma juoma = new Juoma();
        	
				juoma = JuomaLista.get(i);
				juomaNakyy.add(juoma);
			}
		}
		request.setAttribute("juomat", juomaNakyy);
		request.setAttribute("pizzat", pizzaLista);
		
		String jsp = "/view/vahvista-tilaus-en.jsp"; 
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
		dispather.forward(request, response);
		
		
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean ok = true;
		String virhe = null;
		HttpSession session = request.getSession();
		
		TilausDAO TDAO = new TilausDAO();
		String enimi = request.getParameter("enimi");
		session.setAttribute("eNimi", enimi);
		String snimi = request.getParameter("snimi");
		session.setAttribute("sNimi", snimi);
		String osoite = request.getParameter("osoite");
		String puhnro = request.getParameter("puhnro");
		String toimitus = request.getParameter("toimitustapa");
		if(toimitus.equals("nouto")){
			osoite = toimitus;
		}
		
		
		if(enimi.isEmpty() || snimi.isEmpty()){
			virhe = "Name fields are required";
			ok = false;
			request.getSession().setAttribute("message4", virhe);
			response.sendRedirect("vahvistaTilausEn");
		
		}else if(puhnro.matches("\\w{9,10}") == false && puhnro.isEmpty() && ok == true){
			ok = false;
			virhe = "Your phone number is incorrect";
			request.getSession().setAttribute("message4", virhe);
			response.sendRedirect("vahvistaTilausEn");
			
		}else if(osoite.isEmpty() && toimitus.equals("kotiinkuljetus")){
			virhe = "Fill in your address";
			ok = false;
			request.getSession().setAttribute("message4", virhe);
			response.sendRedirect("vahvistaTilausEn");
			
		}else if(ok == true){
			Ostoskori tuotteet = (Ostoskori) request.getSession().getAttribute("ostoskori");
		
		Kayttaja valiaikainen = new Kayttaja();
		
		if(session.getAttribute("kayttajaID")!=null){
			valiaikainen.setKayttaja_id((int) session.getAttribute("kayttajaID"));
			}
			else {
				valiaikainen.setKayttaja_id(404);
			}
		
		Tilaus T = new Tilaus();
		
		T.setKayttaja(valiaikainen);
		
		T.setOsoite(osoite);
		T.setPuhnro(puhnro);
		if(toimitus.equals("nouto")){
			T.setStatusID(10);
		}else if(toimitus.equals("kotiinkuljetus")){
			T.setStatusID(1);
		}
		
		try {
			TDAO.addTilaus(T, tuotteet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		session.removeAttribute("eNimi");
		session.removeAttribute("sNimi");
		session.removeAttribute("ostoskori");
		response.sendRedirect("pizzaMenuEn");
		
		}else{
		
		request.getSession().setAttribute("message4", virhe);
		response.sendRedirect("vahvistaTilausEn");
		
	}
		
		}
		
	}