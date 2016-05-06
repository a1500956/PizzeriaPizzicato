package pizzeria_pizzicato.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzeria_pizzicato.model.Kayttaja;
import pizzeria_pizzicato.model.dao.KayttajaDAO;


@WebServlet("/listaaTyontekijat")
public class listaaTyontekijat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public listaaTyontekijat() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		KayttajaDAO tyontekijadao = new KayttajaDAO();
		ArrayList<Kayttaja> tyontekijaLista = tyontekijadao.findAll();

		request.setAttribute("tyontekijat", tyontekijaLista);
		String jsp = "/view/listaa-tyontekijat.jsp";
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(
				jsp);
		dispather.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
