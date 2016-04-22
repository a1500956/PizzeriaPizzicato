package pizzeria_pizzicato.model;

public class Juoma extends Tuote{
	private int id;
	private String nimi;
	private double litrakoko;
	private double hinta;
	private int nakyy;
	
	
	public Juoma(int id, String nimi, double hinta, double litrakoko, int nakyy) {
		super();
		this.id = id;
		this.nimi = nimi;
		this.litrakoko = litrakoko;
		this.hinta = hinta;
		this.nakyy = nakyy;
	}

	


	public Juoma() {
		super();
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


	public double getLitrakoko() {
		return litrakoko;
	}


	public void setLitrakoko(double litrakoko) {
		this.litrakoko = litrakoko;
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
		return "Juoma [nimi=" + nimi + ", litrakoko=" + litrakoko + ", hinta="
				+ hinta + ", nakyy=" + nakyy + "]";
	}
	
	
}
