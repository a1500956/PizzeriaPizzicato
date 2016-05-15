package pizzeria_pizzicato.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

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
import pizzeria_pizzicato.model.dao.TuoteDAO;
import pizzeria_pizzicato.model.Tuote;
import pizzeria_pizzicato.model.TilattuTuote;
import pizzeria_pizzicato.model.Tilaus;

@WebServlet("/vahvistaTilaus")
public class vahvistaTilaus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PizzaDAO pizzadao = new PizzaDAO();
		ArrayList<Pizza> pizzaLista = pizzadao.findAll();
		TilausDAO tdao = new TilausDAO();
		TuoteDAO tuoteDAO = new TuoteDAO();
		
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
		
		if(ostoskori.getTuotteet() != null || ostoskori.getKoko() != 0){ //t‰ss‰ k‰yd‰‰n l‰pi onko tuotteilla lis‰t‰ytteit‰
			Pizza pizza = new Pizza();
			for(int i = 0; i<ostoskori.getTuotteet().size(); i++){
				if(tuoteDAO.pizzaVaiJuoma(ostoskori.getTuotteet().get(i).getTuote().getId()) == true ){ //katsotaan onko ostoskorin tuote pizza vai juoma
					pizza = (Pizza) ostoskori.getTuotteet().get(i).getTuote(); // jos on pizza tehd‰‰n siit‰ pizzo olio 
					PizzaTayteDAO PTdao = new PizzaTayteDAO();
					ArrayList<Tayte> kannanTaytteet = PTdao.haePizzanTaytteet(pizza.getId());
					ArrayList<Tayte> nykyiset = pizza.getTaytteet();
					
					if(tdao.karsiTavallisetTaytteet(kannanTaytteet, nykyiset).isEmpty() == false){
						ostoskori.getTuotteet().get(i).setLisataytteet(tdao.karsiTavallisetTaytteet(kannanTaytteet, nykyiset));
					}
					
				}
			}	
		}

		request.setAttribute("juomat", juomaNakyy);
		request.setAttribute("pizzat", pizzaLista);
		
		String jsp = "/view/vahvista-tilaus.jsp"; 
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
		dispather.forward(request, response);
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean ok = true;
		String virhe = null;
		HttpSession session = request.getSession();
		
		TilausDAO TDAO = new TilausDAO();  //Haetaan formin tiedot
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
		
		
		if(enimi.isEmpty() || snimi.isEmpty()){ //tarkistetaan etunimi
			virhe = "Nimi kent‰t ovat pakollisia!";
			ok = false;
			request.getSession().setAttribute("message4", virhe);
			response.sendRedirect("vahvistaTilaus"); //palataan takaisin virheen kera jos puuttuu
		
		}else if(puhnro.matches("\\w{9,10}") == false && puhnro.isEmpty() && ok == true){ // tarkistetaan ett‰ puhelinnumero on oikeaa muotoa ja ei puutu
			ok = false;
			virhe = "Puhelinnumero on virheellinen!";
			request.getSession().setAttribute("message4", virhe);
			response.sendRedirect("vahvistaTilaus"); //palataan takaisin virheen kera jos puuttuu tai on virheellinen
			
		}else if(osoite.isEmpty() && toimitus.equals("kotiinkuljetus")){ // katsotaan onko kotiinkuljetus jos on tarkistetaan, ett‰ osoite ei puutu
			virhe = "Osoitekentt‰ on pakollinen!";
			ok = false;
			request.getSession().setAttribute("message4", virhe);
			response.sendRedirect("vahvistaTilaus"); //palataan takaisin virheen kera jos puuttuu
			
		}else if(ok == true){ // jos kaikki ok l‰hetet‰‰n tilaus eteenp‰in
			Ostoskori tuotteet = (Ostoskori) request.getSession().getAttribute("ostoskori");
			
			Kayttaja valiaikainen = new Kayttaja();
		if(session.getAttribute("kayttajaID")!=null){ // jos k‰ytt‰j‰ on kirjautunut sis‰‰n haetaan kayttajaID tilaukseen
			valiaikainen.setKayttaja_id((int) session.getAttribute("kayttajaID"));
		}else {
			valiaikainen.setKayttaja_id(404); //jos ei ole kirjautunut sis‰‰n laitetaan vieras tunnukselle
		}
		
		Tilaus T = new Tilaus(); // alustetaan tilaus
		
		T.setKayttaja(valiaikainen); // laitetaan tilaukselle tiedot
		T.setOsoite(osoite);
		T.setPuhnro(puhnro);
		if(toimitus.equals("nouto")){
			T.setStatusID(10);
		}else if(toimitus.equals("kotiinkuljetus")){
			T.setStatusID(1);
		}
		
		try {
			TDAO.addTilaus(T, tuotteet); //l‰hetet‰‰n tilaus DAOon
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		String tilausLapi = "true"; //otetaan tieto tilauksen l‰pimenemisest‰ talteen
		session.removeAttribute("eNimi"); //poistetaan sessiosta formi tiedot
		session.removeAttribute("sNimi");
		session.removeAttribute("osoite");
		session.removeAttribute("puhnro");
		session.removeAttribute("sPosti");
		session.removeAttribute("ostoskori"); // tyhjennet‰‰n ostoskori
		session.setAttribute("tilausLapi", tilausLapi);//vied‰‰n tieto tilauksen l‰pimenemisest‰ sessioon
		System.out.println(toimitus);
		session.setAttribute("toimitustapa", toimitus);//vied‰‰n tieto tilauksen toimitustavasta sessioon
		response.sendRedirect("pizzaMenu"); // l‰hetet‰‰n etusivulle
		
		}else{
			request.getSession().setAttribute("message4", virhe); //jos tarkistuksiisa on tullut virhe vied‰‰n virheviesti sessioon
			response.sendRedirect("vahvistaTilaus"); //l‰hetet‰‰n asiakas takaisin vahvistussivulle
		
	}
		
		}
		
	}
