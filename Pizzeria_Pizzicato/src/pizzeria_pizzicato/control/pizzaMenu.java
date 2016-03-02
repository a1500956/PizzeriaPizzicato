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
import pizzeria_pizzicato.model.PizzaTayte;
import pizzeria_pizzicato.model.Tayte;
import pizzeria_pizzicato.model.dao.PizzaDAO;
import pizzeria_pizzicato.model.dao.PizzaTayteDAO;
import pizzeria_pizzicato.model.dao.TayteDAO;
import pizzeria_pizzicato.model.dao.Taytteet;


@WebServlet("/pizzaMenu")
public class pizzaMenu extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
       

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				
			// PizzaDAO pizzadao = new PizzaDAO();
			// ArrayList<Pizza> pizzat = pizzadao.findAll();
			
			// TayteDAO taytedao = new TayteDAO();
			// ArrayList<Tayte> taytteet = taytedao.findAll();
			
			PizzaTayteDAO pTaytedao = new PizzaTayteDAO();
			ArrayList<PizzaTayte> pTaytteet = pTaytedao.findAll();
			ArrayList<Taytteet> pizzat = new ArrayList<Taytteet>();
			ArrayList<String> sArvo = new ArrayList<String>();
			
			for(int i = 0; i < pTaytteet.size(); i++){
				System.out.println(i);
				System.out.print(" " + pTaytteet.get(i).getpId());
				System.out.println(" " + pTaytteet.get(i).gettId());
			}
			
			for(int i = 0; i < pTaytteet.size(); i++){
				Taytteet taytteet = new Taytteet();
				String tArvo = new String();
				tArvo = Integer.toString(pTaytteet.get(i).gettId());
				
				taytteet.setpId(pTaytteet.get(i).getpId());
				taytteet.getTaytteet().add(tArvo);
				
				pizzat.add(taytteet);
			}
			
			for(int i = 0; i < pizzat.size(); i++){
			System.out.println(pizzat.get(i).getpId());
			}
			
			// request.setAttribute("pizzat", pizzat);
			// request.setAttribute("taytteet", taytteet);
			request.setAttribute("pTaytteet", pTaytteet);
			
			String jsp = "/view/listaa-pizzat.jsp"; 
			RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
			dispather.forward(request, response);
		
		}

}