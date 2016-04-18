package pizzeria_pizzicato.model;

public class Tuote {
	private int id;
	private String nimi;
	private double hinta;
	

	public Tuote(int id, String nimi, double hinta) {
		super();
		this.id = id;
		this.nimi = nimi;
		this.hinta = hinta;
	}
	
	public Tuote(int id, int hinta) {
		super();
		this.id = id;
		this.hinta = hinta;
	}
	
	public Tuote() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getHinta() {
		return hinta;
	}

	public void setHinta(double hinta) {
		this.hinta = hinta;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	@Override
	public String toString() {
		return "Tuote [id=" + id + ", hinta=" + hinta + "]";
	}
	
}

