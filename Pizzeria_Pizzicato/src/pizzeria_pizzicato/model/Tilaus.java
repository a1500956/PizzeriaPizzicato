package pizzeria_pizzicato.model;

import java.sql.Timestamp;

public class Tilaus {
	private int id;
	private Timestamp aika;
	private String osoite;
	private String puhnro;
	private int statusID;
	private int kayttajaID;
	
	public Tilaus(int id, Timestamp aika, String osoite, String puhnro, int statusID,
			int kayttajaID) {
		super();
		this.id = id;
		this.aika = aika;
		this.osoite = osoite;
		this.puhnro = puhnro;
		this.statusID = statusID;
		this.kayttajaID = kayttajaID;
	}
	
	public Tilaus() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getAika() {
		return aika;
	}

	public void setAika(Timestamp aika) {
		this.aika = aika;
	}

	public String getOsoite() {
		return osoite;
	}

	public void setOsoite(String osoite) {
		this.osoite = osoite;
	}
	
	

	public String getPuhnro() {
		return puhnro;
	}

	public void setPuhnro(String puhnro) {
		this.puhnro = puhnro;
	}

	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}

	public int getKayttajaID() {
		return kayttajaID;
	}

	public void setKayttajaID(int kayttajaID) {
		this.kayttajaID = kayttajaID;
	}

	@Override
	public String toString() {
		return "Tilaus [id=" + id + ", aika=" + aika + ", osoite=" + osoite
				+ ", statusID=" + statusID + ", kayttajaID=" + kayttajaID + "]";
	}
	
	
}


