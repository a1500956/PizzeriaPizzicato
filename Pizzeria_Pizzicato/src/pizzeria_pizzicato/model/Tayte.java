package pizzeria_pizzicato.model;

public class Tayte {

	private int tayteId;
	private String tayteNimi;

	public Tayte() {
		super();
	}

	public Tayte(int tayte_id, String tayte_nimi) {
		super();
		this.tayteId = tayte_id;
		this.tayteNimi = tayte_nimi;
	}

	public int getTayte_id() {
		return tayteId;
	}

	public void setTayte_id(int tayte_id) {
		this.tayteId = tayte_id;
	}

	public String getTayte_nimi() {
		return tayteNimi;
	}

	public void setTayte_nimi(String tayte_nimi) {
		this.tayteNimi = tayte_nimi;
	}

	/*
	 * public void add(Tayte sArvo) { this.tayte_id = sArvo.getTayte_id();
	 * this.tayte_nimi = sArvo.getTayte_nimi();
	 * 
	 * }
	 */

	@Override
	public String toString() {
		return "Tayte [tayte_id=" + tayteId + ", tayte_nimi=" + tayteNimi
				+ "]";
	}

}
