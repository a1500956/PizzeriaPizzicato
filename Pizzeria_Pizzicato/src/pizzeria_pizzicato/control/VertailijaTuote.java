package pizzeria_pizzicato.control;
import java.util.Comparator;

import pizzeria_pizzicato.model.Tuote;

public class VertailijaTuote implements Comparator<Tuote> {
	
	@Override
	public int compare(Tuote A, Tuote B){
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
