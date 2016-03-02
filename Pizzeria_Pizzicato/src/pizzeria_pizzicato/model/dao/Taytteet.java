package pizzeria_pizzicato.model.dao;

import java.util.ArrayList;

import pizzeria_pizzicato.model.PizzaTayte;
import pizzeria_pizzicato.model.Tayte;

public class Taytteet extends PizzaTayte {
	
	private ArrayList<String> taytteet = new ArrayList<String>();
	
	

	public Taytteet(ArrayList<String> taytteet) {
		super();
		this.taytteet = taytteet;
	}

	public Taytteet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Taytteet(int pId, int tId) {
		super(pId, tId);
		// TODO Auto-generated constructor stub
	}

	public ArrayList<String> getTaytteet() {
		return taytteet;
	}

	public void setTaytteet(ArrayList<String> taytteet) {
		this.taytteet = taytteet;
	}

	@Override
	public String toString() {
		return "Taytteet [taytteet=" + taytteet + ", toString()="
				+ super.toString() + "]";
	}
	
}
