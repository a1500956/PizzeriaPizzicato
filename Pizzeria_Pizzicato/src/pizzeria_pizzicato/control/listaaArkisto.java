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
import pizzeria_pizzicato.model.TilattuTuote;
import pizzeria_pizzicato.model.Tilaus;
import pizzeria_pizzicato.model.dao.PizzaDAO;
import pizzeria_pizzicato.model.dao.TilausDAO;


@WebServlet("/listaaArkisto")
public class listaaArkisto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public listaaArkisto() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		TilausDAO tilausdao = new TilausDAO();
		ArrayList<Tilaus> tilaukset = new ArrayList<Tilaus>(tilausdao.haeAktiivisetTilaukset());
		PizzaDAO pizzadao = new PizzaDAO();
		ArrayList<Pizza> pizzaLista = pizzadao.findAll();

		request.setAttribute("pizzat", pizzaLista);
		request.setAttribute("tilaukset", tilaukset);

		ArrayList<TilattuTuote> tlista = tilausdao.haeTilastot();
		
		request.setAttribute("tilasto", tlista);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		String jsp = "/view/listaa-arkisto.jsp"; 
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
		dispather.forward(request, response);
	}


}
