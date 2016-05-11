package pizzeria_pizzicato.control;
import java.util.Comparator;

import pizzeria_pizzicato.model.Tayte;

public class VertailijaTayte implements Comparator<Tayte> {
	
	@Override
	public int compare(Tayte A, Tayte B){
		int palaute = 0;
		if(A.getTayte_id()<B.getTayte_id()){
			palaute = -1;
		}
		if(A.getTayte_id()>B.getTayte_id()){
			palaute = 1;
		}
		
		return palaute;
		
	}

}
