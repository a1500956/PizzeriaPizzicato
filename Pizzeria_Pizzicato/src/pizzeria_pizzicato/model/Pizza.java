package pizzeria_pizzicato.model;

import java.util.ArrayList;

public class Pizza {
	
	private int id;
	private String nimi;
	private double hinta;
	
	
	public Pizza() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pizza(int id, String nimi, double hinta) {
		super();
		this.id = id;
		this.nimi = nimi;
		
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

	
	@Override
	public String toString() {
		return "Pizza [id=" + id + ", nimi=" + nimi + ", hinta=" + hinta + "]";
	}

	public double getHinta() {
		return hinta;
	}

	public void setHinta(double hinta) {
		this.hinta = hinta;
	}
	
	

}
