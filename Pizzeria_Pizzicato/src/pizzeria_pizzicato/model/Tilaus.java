package pizzeria_pizzicato.model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Tilaus {
	private int id;
	private Timestamp aika;
	private String osoite;
	private String puhnro;
	private int statusID;
	private String statusNimi;
	private Kayttaja kayttaja;
	private ArrayList<TilattuTuote> tilatutTuotteet;
	
	
	
	public Tilaus(int id, Timestamp aika, String osoite, String puhnro, int statusID, String statusNimi, Kayttaja kayttaja, ArrayList<TilattuTuote> tilatutTuotteet) {
		super();
		this.id = id;
		this.aika = aika;
		this.osoite = osoite;
		this.puhnro = puhnro;
		this.statusID = statusID;
		this.statusNimi = statusNimi;
		this.kayttaja = kayttaja;
		this.tilatutTuotteet = tilatutTuotteet;
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

	public String getStatusNimi() {
		return statusNimi;
	}

	public void setStatusNimi(String statusNimi) {
		this.statusNimi = statusNimi;
	}

	public Kayttaja getKayttaja() {
		return kayttaja;
	}

	public void setKayttaja(Kayttaja kayttaja) {
		this.kayttaja = kayttaja;
	}

	public ArrayList<TilattuTuote> getTilatutTuotteet() {
		return tilatutTuotteet;
	}
	
	public TilattuTuote getTilattuTuote(int index) {
		TilattuTuote tilattuTuote = null;
		if(index>=0 && index < tilatutTuotteet.size() ){
		tilattuTuote = tilatutTuotteet.get(index);
		}
		return tilattuTuote;
	}
	
	public void addTilattuTuote(TilattuTuote tilattuTuote){
		if(tilattuTuote != null){
			this.tilatutTuotteet.add(tilattuTuote);	
		}
		
	}

	
	@Override
	public String toString() {
		return "Tilaus [id=" + id + ", aika=" + aika + ", osoite=" + osoite
				+ ", puhnro=" + puhnro + ", statusID=" + statusID
				+ ", statusNimi=" + statusNimi + ", kayttaja=" + kayttaja
				+ ", tilatutTuotteet=" + tilatutTuotteet + "]";
	}
	
	
}


