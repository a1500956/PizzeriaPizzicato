package pizzeria_pizzicato.model;

import java.util.ArrayList;

public class Pizza {
	
	private int id;
	private String nimi, koko;
	private ArrayList<Tayte> taytteet = new ArrayList<Tayte>();
	
	public Pizza() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pizza(int id, String nimi, String koko, ArrayList<Tayte> taytteet) {
		super();
		this.id = id;
		this.nimi = nimi;
		this.koko = koko;
		this.taytteet = taytteet;
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

	public String getKoko() {
		return koko;
	}

	public void setKoko(String koko) {
		this.koko = koko;
	}

	public ArrayList<Tayte> getTaytteet() {
		return taytteet;
	}

	public void setTaytteet(ArrayList<Tayte> taytteet) {
		this.taytteet = taytteet;
	}

	@Override
	public String toString() {
		return "Pizza [id=" + id + ", nimi=" + nimi + ", koko=" + koko
				+ ", taytteet=" + taytteet + "]";
	}
	
	

}
