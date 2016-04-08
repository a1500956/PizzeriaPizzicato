package pizzeria_pizzicato.control;
import java.util.Comparator;

import pizzeria_pizzicato.model.Pizza;

public class Vertailija implements Comparator<Pizza> {
	
	@Override
	public int compare(Pizza A, Pizza B){
		int palaute = 0;
		if(A.getHinta()<B.getHinta()){
			palaute = -1;
		}
		if(A.getHinta()>B.getHinta()){
			palaute = 1;
		}
		
		return palaute;
		
	}

}
