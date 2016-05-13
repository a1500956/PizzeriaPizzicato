package pizzeria_pizzicato.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;

import pizzeria_pizzicato.model.Ostoskori;
import pizzeria_pizzicato.model.Pizza;
import pizzeria_pizzicato.model.Tayte;
import pizzeria_pizzicato.model.TilattuTuote;
import pizzeria_pizzicato.model.dao.PizzaDAO;
import pizzeria_pizzicato.model.dao.JuomaDAO;
import pizzeria_pizzicato.model.dao.TayteDAO;
import pizzeria_pizzicato.model.Juoma;


@WebServlet("/pizzaMenuEn")
public class pizzaMenuEng extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
       

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PizzaDAO pizzadao = new PizzaDAO();
		TayteDAO Taytedao = new TayteDAO();
		ArrayList<Pizza> pizzaLista = pizzadao.findAll();
		ArrayList<Pizza> pizzaNakyy = new ArrayList<Pizza>();
		ArrayList<Pizza> pizzaFantasia = new ArrayList<Pizza>();
		ArrayList<Tayte> kaikkiTaytteet= Taytedao.findAll();
		ArrayList<Tayte> fantasiaTayteValintaLista= Taytedao.karsiFantasianPerustaytteet(kaikkiTaytteet, pizzadao.getPizzaId("Fantasia 2"));
		request.setAttribute("kaikkitaytteet", kaikkiTaytteet);
		request.setAttribute("fantasiaTayteValintaLista", fantasiaTayteValintaLista);
		
		for(int i=0;i<pizzaLista.size();i++){
			
			if(pizzaLista.get(i).getNakyy()==1 && !pizzaLista.get(i).getNimi().contains("Fantasia")){
				Pizza pizza = new Pizza();
        	
				pizza = pizzaLista.get(i);
				pizzaNakyy.add(pizza);
			}if(pizzaLista.get(i).getNakyy()==1 && pizzaLista.get(i).getNimi().contains("Fantasia")){
				Pizza pizza = new Pizza();
        	
				pizza = pizzaLista.get(i);
				pizzaFantasia.add(pizza);
			}
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
		
		
		HttpSession session = request.getSession();
		Ostoskori ostoskori = (Ostoskori) session.getAttribute("ostoskori");
		if(ostoskori == null){
			ostoskori = new Ostoskori();
			session.setAttribute("ostoskori", ostoskori);
			session.setMaxInactiveInterval(60*60);
		}
		
		session.removeAttribute("message4");
		
		request.setAttribute("pizzat", pizzaNakyy);
		request.setAttribute("pizzaFantasia", pizzaFantasia);
		request.setAttribute("juomat", juomaNakyy);
		
		String jsp = "/view/frontpage.jsp"; 
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
		dispather.forward(request, response);

}

protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
	
	
	PizzaDAO pizzadao = new PizzaDAO();
	TayteDAO Taytedao = new TayteDAO();
	JuomaDAO juomadao = new JuomaDAO();
	String[] lisatayte;
	HttpSession session = request.getSession(); //haetaan session
	Ostoskori ostoskori = (Ostoskori) session.getAttribute("ostoskori"); // haetaan ostoskori sessionista
	int kpl = 0;
	kpl = Integer.parseInt(request.getParameter("maara"));

	
	//k‰sitell‰‰n formista tullut data
	
	if(request.getParameter("pizzaID") != null){
		ArrayList<Pizza> pizzaLista = pizzadao.findAll();// haetaan pizzat
		int sArvo = Integer.parseInt((String) request.getParameter("pizzaID")); //Haetaan ensin pizzaID
		int oregano = 0, vSipuli = 0;
		if(request.getParameter("oregano") != null){ //haetaan oregano jos null j‰tet‰‰n 0
			oregano = 1;
		}
		if(request.getParameter("vSipuli") != null){ //haetaan vSipuli jos null j‰tet‰‰n 0
			vSipuli = 1;
		}		
		lisatayte = request.getParameterValues("lisatayte");
		//if(lisatayte != null){
		//	for(int i=0; i<lisatayte.length;i++){
		//		System.out.println("lis‰t‰yte " + lisatayte.toString());
		//	}
		//}
		
		Pizza pizza = new Pizza();
		for(int i=0; i<pizzaLista.size();i++){ //luodaan pizzaID mukaan pizza
			if(pizzaLista.get(i).getId() == sArvo){
				pizza = pizzaLista.get(i);
			}
		}
		
		if(lisatayte!=null){
			for(String s:lisatayte){
				pizza.addTayte(Taytedao.getTayte(Integer.parseInt(s)));
			}
		}
		System.out.println("pizza " + pizza);
		ostoskori.addPizza(pizza, oregano, vSipuli, kpl); //lis‰t‰‰n ostoskoriin pizza
		
	}else if(request.getParameter("juomaID") != null){
		ArrayList<Juoma> juomaLista = juomadao.findAll(); //haetaan kaikki juomat
		int sArvo = Integer.parseInt(request.getParameter("juomaID"));
		
		Juoma juoma = new Juoma();
		for(int i = 0; i<juomaLista.size();i++){
			if(juomaLista.get(i).getId() == sArvo){
				juoma = juomaLista.get(i);
			}
		}
		ostoskori.addJuoma(juoma, kpl);
	}
	
	if(ostoskori != null){ //jos ostoskori ei ole tyhj‰ vied‰‰n ostoskori muutoksineen sessioniin
		session.setAttribute("ostoskori", ostoskori);
	}
	
	doGet(request, response); // palaa takaisin servletin alkuun
}

}
