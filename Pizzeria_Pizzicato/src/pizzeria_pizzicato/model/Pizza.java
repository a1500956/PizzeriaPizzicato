package pizzeria_pizzicato.model;


import java.util.ArrayList;

public class Pizza extends Tuote {

	private int id;
	private String nimi;
	private double hinta;
	int nakyy;
	private ArrayList<Tayte> taytteet;

	public Pizza() {
		super();
		this.taytteet = new ArrayList<Tayte>(); //tyhj‰n listan luonti
	}

	public Pizza(int id, String nimi, double hinta, int nakyy) {
		super();
		this.id = id;
		this.nimi = nimi;
		this.hinta = hinta;
		this.nakyy = nakyy;
		this.taytteet = new ArrayList<Tayte>(); //tyhj‰n listan luonti
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
	
	public ArrayList<Tayte> getTaytteet() {
		return taytteet;
	}
	
	public ArrayList<Integer> getIDT() { // T‰ll‰ haetaan lista pizzan taytteiden id:st‰!
		ArrayList<Integer> tayteIdT = new ArrayList<>(); // Tarvitaan pizzan lis‰‰misess‰ ostoskoriin!
		for(int i = 0; i<this.taytteet.size(); i++){
			tayteIdT.add(this.getTayte(i).getTayte_id());
		}
		return tayteIdT;
	}

	@Override
	public String toString() {
		return "Pizza [id=" + id + ", nimi=" + nimi + ", hinta=" + hinta
				+ ", nakyy=" + nakyy + ", taytteet=" + taytteet + "]";
	}

	public Tayte getTayte(int index) {
		Tayte tayte = null;
		if(index>=0 && index < taytteet.size() ){
		tayte = taytteet.get(index);
		}
		return tayte;
	}
	
	public void addTayte(Tayte tayte){
		if(tayte != null){
			this.taytteet.add(tayte);	
		}
		
	}

	public void addTaytteita(ArrayList<Tayte> taytteita) {
		for (int i = 0; i < taytteita.size(); i++) {
			addTayte(taytteita.get(i));
			
		}
		
	}

}
