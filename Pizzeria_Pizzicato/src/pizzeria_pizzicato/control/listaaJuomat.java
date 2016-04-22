package pizzeria_pizzicato.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzeria_pizzicato.model.Juoma;
import pizzeria_pizzicato.model.dao.JuomaDAO;

/**
 * Servlet implementation class listaaJuomat
 */
@WebServlet("/listaaJuomat")
public class listaaJuomat extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public listaaJuomat() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JuomaDAO juomadao = new JuomaDAO();
		ArrayList<Juoma> juomaLista = juomadao.findAll();

		request.setAttribute("juomat", juomaLista);
		String jsp = "/view/listaa-juomat.jsp";
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(
				jsp);
		dispather.forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
