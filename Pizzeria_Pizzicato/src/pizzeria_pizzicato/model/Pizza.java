package pizzeria_pizzicato.model;


import java.util.ArrayList;

public class Pizza extends Tayte {
	
	private int id;
	private String nimi;
	private double hinta;
	int nakyy;
	private ArrayList<Tayte> taytteet = new ArrayList<Tayte>();
	
	
	
	public Pizza() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pizza(int id, String nimi, double hinta, int nakyy) {
		super();
		this.id = id;
		this.nimi = nimi;
		this.hinta = hinta;
		this.nakyy = nakyy;
		
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}


	public double getHinta() {
		
				
		return hinta;
	}

	public void setHinta(double hinta) {
		this.hinta = hinta;
	}

	public int getNakyy() {
		return nakyy;
	}

	public void setNakyy(int nakyy) {
		this.nakyy = nakyy;
	}

	@Override
	public String toString() {
		return "Pizza [id=" + id + ", nimi=" + nimi + ", hinta=" + hinta
				+ ", nakyy=" + nakyy + ", taytteet=" + taytteet + "]";
	}

	public ArrayList<Tayte> getTaytteet() {
		return taytteet;
	}

	public void setTaytteet(ArrayList<Tayte> taytteet) {
		this.taytteet = taytteet;
	}
	
	

}
