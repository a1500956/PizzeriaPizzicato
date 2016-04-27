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

import pizzeria_pizzicato.model.Pizza;
import pizzeria_pizzicato.model.Tilaus;
import pizzeria_pizzicato.model.dao.PizzaDAO;
import pizzeria_pizzicato.model.dao.TilausDAO;


@WebServlet("/listaaPizzatkuski")
public class listaaPizzatkuski extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public listaaPizzatkuski() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TilausDAO tilausdao = new TilausDAO();
		ArrayList<Tilaus> tilaukset = new ArrayList<Tilaus>(tilausdao.haeAktiivisetTilaukset());
		PizzaDAO pizzadao = new PizzaDAO();
		ArrayList<Pizza> pizzaLista = pizzadao.findAll();

		request.setAttribute("pizzat", pizzaLista);
		request.setAttribute("tilaukset", tilaukset);
		
		String jsp = "/view/listaa-kuski.jsp"; 
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
		dispather.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {
			
			String riviStr = request.getParameter("toimitettu");			
			
			if(riviStr!=null){
				
			int tilaus_id = Integer.parseInt(riviStr);			
						
			TilausDAO tilausdao = new TilausDAO();
						
			tilausdao.updateTilausOk(tilaus_id, 5);
		
			
			}

				
				} catch (SQLException e) {
						
				System.out.println("Sovelluksessa tapahtui virhe "+ e.getMessage());
				}

			response.sendRedirect("listaaPizzatkuski");
						
		}
	}