package pizzeria_pizzicato.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
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
import pizzeria_pizzicato.model.Pizza;
import pizzeria_pizzicato.model.Tayte;
import pizzeria_pizzicato.model.TilattuTuote;
import pizzeria_pizzicato.model.Tilaus;
import pizzeria_pizzicato.model.Tuote;
import pizzeria_pizzicato.model.dao.KayttajaDAO;
import pizzeria_pizzicato.model.dao.PizzaDAO;
import pizzeria_pizzicato.model.dao.PizzaTayteDAO;
import pizzeria_pizzicato.model.dao.TayteDAO;
import pizzeria_pizzicato.model.dao.TilausDAO;
import pizzeria_pizzicato.model.dao.TuoteDAO;

@WebServlet("/listaaPizzatkokki")
public class listaaPizzatkokki extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		
			TilausDAO tilausdao = new TilausDAO();
			ArrayList<Tilaus> tilaukset = new ArrayList<Tilaus>(tilausdao.haeAktiivisetTilaukset());
			PizzaDAO pizzadao = new PizzaDAO();
			ArrayList<Pizza> pizzaLista = pizzadao.findAll();
			ArrayList<TilattuTuote> tilatutTuotteet = new ArrayList<TilattuTuote>();
			

			request.setAttribute("pizzat", pizzaLista);
			request.setAttribute("tilaukset", tilaukset);
			request.setAttribute("tilatutTuotteet", tilatutTuotteet);
			
			String jsp = "/view/listaa-kokki.jsp"; 
			RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
			dispather.forward(request, response);
			
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
		
		
		try {
			
			String riviStr = request.getParameter("valmis");
			String riviStr2 = request.getParameter("valmis2");
			String riviStr3 = request.getParameter("valmis3");
		
			
			if(riviStr!=null){
				
			int tilausId = Integer.parseInt(riviStr);
			int tilausRivi = Integer.parseInt(riviStr2);
			int tuoteId = Integer.parseInt(riviStr3);
			int status = 0;
						
			TilausDAO tilausdao = new TilausDAO();
						
			tilausdao.updateTilattuTuote(tilausRivi, tuoteId, tilausId, status);
			tilausdao.TilauksenStatusUpdate(tilausRivi, tuoteId, tilausId);
		
			
			}

				
				} catch (SQLException e) {
						
				System.out.println("Sovelluksessa tapahtui virhe "+ e.getMessage());
				}

			response.sendRedirect("listaaPizzatkokki");
						
		}
		

}
