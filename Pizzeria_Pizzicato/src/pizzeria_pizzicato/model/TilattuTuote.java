package pizzeria_pizzicato.model;

import java.util.ArrayList;

public class TilattuTuote {
	
	private int tuoteId;
	private int tilausId;
	private int tilausRivi, lkm, oregano, vSipuli;
	private Tuote tuote;
	private double hinta;
	private int status;
	private ArrayList<Tayte> lisataytteet;
	
	
	
	public TilattuTuote() {
		super();
		// TODO Auto-generated constructor stub
	}



	public TilattuTuote(int tuoteId, int tilausId, int tilausRivi, int lkm,
			int oregano, int vSipuli, Tuote tuote, double hinta, int status,
			ArrayList<Tayte> lisataytteet) {
		super();
		this.tuoteId = tuoteId;
		this.tilausId = tilausId;
		this.tilausRivi = tilausRivi;
		this.lkm = lkm;
		this.oregano = oregano;
		this.vSipuli = vSipuli;
		this.tuote = tuote;
		this.hinta = hinta;
		this.status = status;
		this.lisataytteet = lisataytteet;
	}


	

	public int getTuoteId() {
		return tuoteId;
	}



	public void setTuoteId(int tuoteId) {
		this.tuoteId = tuoteId;
	}



	public int getTilausId() {
		return tilausId;
	}



	public void setTilausId(int tilausId) {
		this.tilausId = tilausId;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
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
		return "TilattuTuote [tuoteId=" + tuoteId + ", tilausId=" + tilausId
				+ ", tilausRivi=" + tilausRivi + ", lkm=" + lkm + ", oregano="
				+ oregano + ", vSipuli=" + vSipuli + ", tuote=" + tuote
				+ ", hinta=" + hinta + ", status=" + status + ", lisataytteet="
				+ lisataytteet + "]";
	}
	
	


	

}
