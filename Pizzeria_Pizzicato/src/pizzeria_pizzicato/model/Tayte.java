package pizzeria_pizzicato.model;

public class Tayte {

	private int tayte_id;
	private String tayte_nimi;

	public Tayte() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tayte(int tayte_id, String tayte_nimi) {
		super();
		this.tayte_id = tayte_id;
		this.tayte_nimi = tayte_nimi;
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

	/*
	 * public void add(Tayte sArvo) { this.tayte_id = sArvo.getTayte_id();
	 * this.tayte_nimi = sArvo.getTayte_nimi();
	 * 
	 * }
	 */

	@Override
	public String toString() {
		return "Tayte [tayte_id=" + tayte_id + ", tayte_nimi=" + tayte_nimi
				+ "]";
	}

}
