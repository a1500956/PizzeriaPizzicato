package pizzeria_pizzicato.model;

import java.util.ArrayList;

public class TilattuTuote {
	
	private int tilausRivi, lkm, oregano, vSipuli;
	private Tuote tuote;
	private double hinta;
	private ArrayList<Tayte> lisataytteet;
	
	
	
	public TilattuTuote() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TilattuTuote(int tilausRivi, int lkm, int oregano, int vSipuli,
			Tuote tuote, double hinta, ArrayList<Tayte> lisataytteet) {
		super();
		this.tilausRivi = tilausRivi;
		this.lkm = lkm;
		this.oregano = oregano;
		this.vSipuli = vSipuli;
		this.tuote = tuote;
		this.hinta = hinta;
		this.lisataytteet=lisataytteet;
	}


	public int getTilausRivi() {
		return tilausRivi;
	}


	public void setTilausRivi(int tilausRivi) {
		this.tilausRivi = tilausRivi;
	}


	public int getLkm() {
		return lkm;
	}


	public void setLkm(int lkm) {
		this.lkm = lkm;
	}


	public int getOregano() {
		return oregano;
	}


	public void setOregano(int oregano) {
		this.oregano = oregano;
	}


	public int getvSipuli() {
		return vSipuli;
	}


	public void setvSipuli(int vSipuli) {
		this.vSipuli = vSipuli;
	}


	public Tuote getTuote() {
		return tuote;
	}


	public void setTuote(Tuote tuote) {
		this.tuote = tuote;
	}


	public double getHinta() {
		return hinta;
	}


	public void setHinta(double hinta) {
		this.hinta = hinta;
	}

	

	public ArrayList<Tayte> getLisataytteet() {
		return lisataytteet;
	}
	
	public Tayte getLisatayte(int x) {
		return this.lisataytteet.get(x);
	}


	public void setLisataytteet(ArrayList<Tayte> lisatayte) {
		this.lisataytteet = lisatayte;
	}
	
	


	@Override
	public String toString() {
		return "TilattuTuote [tilausRivi=" + tilausRivi + ", lkm=" + lkm
				+ ", oregano=" + oregano + ", vSipuli=" + vSipuli + ", tuote="
				+ tuote + ", hinta=" + hinta + "]";
	}

}
