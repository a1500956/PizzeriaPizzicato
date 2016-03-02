package pizzeria_pizzicato.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzeria_pizzicato.model.Pizza;

import pizzeria_pizzicato.model.dao.PizzaDAO;



@WebServlet("/listaaPizzat")
public class listaaPizzat extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
       

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				
			PizzaDAO pizzadao = new PizzaDAO();
			ArrayList<Pizza> pizzat = pizzadao.findAll();
			
			
			
			request.setAttribute("pizzat", pizzat);
			
			
			String jsp = "/view/listaa-pizzat.jsp"; 
			RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
			dispather.forward(request, response);
		
		}

}