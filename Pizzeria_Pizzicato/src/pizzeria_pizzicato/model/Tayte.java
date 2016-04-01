package pizzeria_pizzicato.model;

public class Tayte {
	
	private int tayte_id;
	private String tayte_nimi;
	private String tayte_nimi_en;
	private Double tayte_hinta;
	
	
	
	public Tayte() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tayte(int tayte_id, String tayte_nimi, String tayte_nimi_en, double tayte_hinta) {
		super();
		this.tayte_id = tayte_id;
		this.tayte_nimi = tayte_nimi;
		this.tayte_nimi_en = tayte_nimi_en;
		this.tayte_hinta = tayte_hinta;
	}
	
	
	
	public Double getTayte_hinta() {
		return tayte_hinta;
	}

	public void setTayte_hinta(Double tayte_hinta) {
		this.tayte_hinta = tayte_hinta;
	}

	public int getTayte_id() {
		return tayte_id;
	}

	public void setTayte_id(int tayte_id) {
		this.tayte_id = tayte_id;
	}

	public String getTayte_nimi() {
		return tayte_nimi;
	}

	public void setTayte_nimi(String tayte_nimi) {
		this.tayte_nimi = tayte_nimi;
	}
	
	public String getTayte_nimi_en() {
		return tayte_nimi_en;
	}

	public void setTayte_nimi_en(String tayte_nimi_en) {
		this.tayte_nimi_en = tayte_nimi_en;
	}

	@Override
	public String toString() {
		return "Tayte [tayte_id=" + tayte_id + ", tayte_nimi=" + tayte_nimi
				+ ", tayte_nimi_en=" + tayte_nimi_en + ", tayte_hinta="
				+ tayte_hinta + "]";
	}


	
		

}
