package pizzeria_pizzicato.model;

public class Kayttaja {
	
	private int kayttaja_id;
	private String kayttaja_nimi;
	private String kayttaja_enimi;
	private String kayttaja_snimi;
	private String kayttaja_salasana;
	private int kayttajaryhma_id;
	private int puhnro;
	private String osoite;
	
	
	
	public Kayttaja(int kayttaja_id, String kayttaja_nimi, String kayttaja_enimi, String kayttaja_snimi, String kayttaja_salasana, int kayttajaryhma_id, int puhnro, String osoite) {
		
		super();
		this.kayttaja_id = kayttaja_id;
		this.kayttaja_nimi = kayttaja_nimi;
		this.kayttaja_enimi = kayttaja_enimi;
		this.kayttaja_snimi = kayttaja_snimi;
		this.kayttaja_salasana = kayttaja_salasana;
		this.kayttajaryhma_id = kayttajaryhma_id;
		this.puhnro = puhnro;
		this.osoite = osoite;
		
	}
	
	
	public Kayttaja() {
		super();
		
	}



	public int getKayttaja_id() {
		return kayttaja_id;
	}
	public void setKayttaja_id(int kayttaja_id) {
		this.kayttaja_id = kayttaja_id;
	}
	public String getKayttaja_nimi() {
		return kayttaja_nimi;
	}
	
	
	
	public String getKayttaja_enimi() {
		return kayttaja_enimi;
	}


	public void setKayttaja_enimi(String kayttaja_enimi) {
		this.kayttaja_enimi = kayttaja_enimi;
	}


	public String getKayttaja_snimi() {
		return kayttaja_snimi;
	}


	public void setKayttaja_snimi(String kayttaja_snimi) {
		this.kayttaja_snimi = kayttaja_snimi;
	}


	public void setKayttaja_nimi(String kayttaja_nimi) {
		this.kayttaja_nimi = kayttaja_nimi;
	}
	public String getKayttaja_salasana() {
		return kayttaja_salasana;
	}
	public void setKayttaja_salasana(String kayttaja_salasana) {
		this.kayttaja_salasana = kayttaja_salasana;
	}
	

	public int getKayttajaryhma_id() {
		return kayttajaryhma_id;
	}

	public void setKayttajaryhma_id(int kayttajaryhma_id) {
		this.kayttajaryhma_id = kayttajaryhma_id;
	}
	
	

	public int getPuhnro() {
		return puhnro;
	}


	public void setPuhnro(int puhnro) {
		this.puhnro = puhnro;
	}


	public String getOsoite() {
		return osoite;
	}


	public void setOsoite(String osoite) {
		this.osoite = osoite;
	}


	@Override
	public String toString() {
		return "Kayttaja [kayttaja_id=" + kayttaja_id + ", kayttaja_nimi="
				+ kayttaja_nimi + ", kayttaja_enimi=" + kayttaja_enimi
				+ ", kayttaja_snimi=" + kayttaja_snimi + ", kayttaja_salasana="
				+ kayttaja_salasana + ", kayttajaryhma_id=" + kayttajaryhma_id
				+ ", puhnro=" + puhnro + ", osoite=" + osoite + "]";
	}


}
