package pizzeria_pizzicato.model;

public class Tuote {
	private int id;
	private double hinta;
	
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

	public void setHinta(int hinta) {
		this.hinta = hinta;
	}

	@Override
	public String toString() {
		return "Tuote [id=" + id + ", hinta=" + hinta + "]";
	}
	
	
}

