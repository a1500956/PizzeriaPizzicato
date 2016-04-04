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


@WebServlet("/kirjautuminen")
public class kirjautuminen extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String kayttaja_ktunnus = request.getParameter("kayttaja");
		String kayttaja_salasana = request.getParameter("salasana");
		System.out.println(kayttaja_ktunnus);
		
		System.out.println(kayttaja_ktunnus);
		
		
		
		KayttajaDAO kayttajadao = new KayttajaDAO();
		Kayttaja kirjautuja = new Kayttaja();
		kirjautuja = kayttajadao.login(kayttaja_ktunnus, kayttaja_salasana);
		
		
		if(kirjautuja != null){
		HttpSession session = request.getSession();
		session.setAttribute("kayttaja", kirjautuja.getKayttaja_enimi());
		session.setMaxInactiveInterval(30*60);
		Cookie userName = new Cookie("kayttaja", kirjautuja.getKayttaja_enimi());
		userName.setMaxAge(30*60);
		response.addCookie(userName);

		response.sendRedirect("kirjautuminenOk");
		
		}else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/pizzaMenu.java");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(request, response);

		
 
		}
		
	}

}
