package pizzeria_pizzicato.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pizzeria_pizzicato.model.Kayttaja;
import pizzeria_pizzicato.model.dao.KayttajaDAO;


@WebServlet("/kirjautuminenEN")
public class kirjautuminenEN extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String kayttaja_ktunnus = request.getParameter("kayttaja");
		String kayttaja_salasana = request.getParameter("salasana");

	
		
		KayttajaDAO kayttajadao = new KayttajaDAO();
		Kayttaja kirjautuja = new Kayttaja();
		kirjautuja = kayttajadao.login(kayttaja_ktunnus, kayttaja_salasana);
		
		if(kirjautuja != null && kirjautuja.getRyhma_id() == 1 ){
			HttpSession session = request.getSession();
			session.setAttribute("ryhma", kirjautuja.getRyhma_id());
			session.setAttribute("kayttaja", kirjautuja.getKayttaja_enimi());
			Cookie userName = new Cookie("kayttaja", kirjautuja.getKayttaja_enimi());
			session.setMaxInactiveInterval(30*60);
			response.addCookie(userName);
			String encodedURL = response.encodeRedirectURL("listaaPizzat");
            response.sendRedirect(encodedURL);
			
		}else if(kirjautuja != null && kirjautuja.getRyhma_id() == 2 ){
			HttpSession session = request.getSession();
			session.setAttribute("ryhma", kirjautuja.getRyhma_id());
			session.setAttribute("kayttaja", kirjautuja.getKayttaja_enimi());
			Cookie userName = new Cookie("kayttaja", kirjautuja.getKayttaja_enimi());
			session.setMaxInactiveInterval(30*60);
			response.addCookie(userName);
			String encodedURL = response.encodeRedirectURL("listaaPizzatkokki");
            response.sendRedirect(encodedURL);
			
		}else if(kirjautuja != null && kirjautuja.getRyhma_id() == 4 ){
			HttpSession session = request.getSession();
			session.setAttribute("ryhma", kirjautuja.getRyhma_id());
			session.setAttribute("kayttaja", kirjautuja.getKayttaja_enimi());
			Cookie userName = new Cookie("kayttaja", kirjautuja.getKayttaja_enimi());
			session.setMaxInactiveInterval(30*60);
			response.addCookie(userName);
			String encodedURL = response.encodeRedirectURL("listaaPizzatkuski");
            response.sendRedirect(encodedURL);
            
		}else if(kirjautuja != null && kirjautuja.getRyhma_id() == 5 ){
			HttpSession session = request.getSession();
			session.setAttribute("ryhma", kirjautuja.getRyhma_id());
			session.setAttribute("kayttaja", kirjautuja.getKayttaja_enimi());
			Cookie userName = new Cookie("kayttaja", kirjautuja.getKayttaja_enimi());
			session.setMaxInactiveInterval(30*60);
			response.addCookie(userName);
			String encodedURL = response.encodeRedirectURL("listaaTilauksettarjoilija");
            response.sendRedirect(encodedURL);
			
		}else if(kirjautuja != null){
			HttpSession session = request.getSession();
			session.setAttribute("ryhma", kirjautuja.getRyhma_id());
			session.setAttribute("kayttaja", kirjautuja.getKayttaja_enimi());
			session.setAttribute("eNimi", kirjautuja.getKayttaja_enimi());
			session.setAttribute("sNimi", kirjautuja.getKayttaja_snimi());
			session.setAttribute("osoite", kirjautuja.getKayttaja_osoite());
			session.setAttribute("puhnro", kirjautuja.getKayttaja_puhnro());
			session.setAttribute("kayttajaID", kirjautuja.getKayttaja_id());
			Cookie userName = new Cookie("kayttaja", kirjautuja.getKayttaja_enimi());
			session.setMaxInactiveInterval(30*60);
			response.addCookie(userName);
			String encodedURL = response.encodeRedirectURL("kirjautuminenOkEN");
            response.sendRedirect(encodedURL);
			


		}else{
			
			String viesti = "Username and/or password is incorrect!";
			request.getSession().setAttribute("message3", viesti);
			response.sendRedirect("pizzaMenuEn");
			
		
 
		}
		
	}

	}
