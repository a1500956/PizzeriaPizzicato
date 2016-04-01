package pizzeria_pizzicato.model;

public class Kayttaja {
	
	private int kayttaja_id;
	private String kayttaja_ktunnus;
	private String kayttaja_enimi;
	private String kayttaja_snimi;
	private String kayttaja_salasana;
	private String kayttaja_puhnro;
	private String kayttaja_osoite;
	private String kayttaja_sposti;
	private int ryhma_id;
	
	
	
	
	public Kayttaja() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Kayttaja(int kayttaja_id, String kayttaja_ktunnus,
			String kayttaja_enimi, String kayttaja_snimi,
			String kayttaja_salasana, String kayttaja_puhnro,
			String kayttaja_osoite, String kayttaja_sposti, int ryhma_id) {
		
		super();
		this.kayttaja_id = kayttaja_id;
		this.kayttaja_ktunnus = kayttaja_ktunnus;
		this.kayttaja_enimi = kayttaja_enimi;
		this.kayttaja_snimi = kayttaja_snimi;
		this.kayttaja_salasana = kayttaja_salasana;
		this.kayttaja_puhnro = kayttaja_puhnro;
		this.kayttaja_osoite = kayttaja_osoite;
		this.kayttaja_sposti = kayttaja_sposti;
		this.ryhma_id = ryhma_id;
	}


	public int getKayttaja_id() {
		return kayttaja_id;
	}


	public void setKayttaja_id(int kayttaja_id) {
		this.kayttaja_id = kayttaja_id;
	}


	public String getKayttaja_ktunnus() {
		return kayttaja_ktunnus;
	}


	public void setKayttaja_ktunnus(String kayttaja_ktunnus) {
		this.kayttaja_ktunnus = kayttaja_ktunnus;
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


	public String getKayttaja_salasana() {
		return kayttaja_salasana;
	}


	public void setKayttaja_salasana(String kayttaja_salasana) {
		this.kayttaja_salasana = kayttaja_salasana;
	}


	public String getKayttaja_puhnro() {
		return kayttaja_puhnro;
	}


	public void setKayttaja_puhnro(String kayttaja_puhnro) {
		this.kayttaja_puhnro = kayttaja_puhnro;
	}


	public String getKayttaja_osoite() {
		return kayttaja_osoite;
	}


	public void setKayttaja_osoite(String kayttaja_osoite) {
		this.kayttaja_osoite = kayttaja_osoite;
	}


	public String getKayttaja_sposti() {
		return kayttaja_sposti;
	}


	public void setKayttaja_sposti(String kayttaja_sposti) {
		this.kayttaja_sposti = kayttaja_sposti;
	}


	public int getRyhma_id() {
		return ryhma_id;
	}


	public void setRyhma_id(int ryhma_id) {
		this.ryhma_id = ryhma_id;
	}


	@Override
	public String toString() {
		return "Kayttaja [kayttaja_id=" + kayttaja_id + ", kayttaja_ktunnus="
				+ kayttaja_ktunnus + ", kayttaja_enimi=" + kayttaja_enimi
				+ ", kayttaja_snimi=" + kayttaja_snimi + ", kayttaja_salasana="
				+ kayttaja_salasana + ", kayttaja_puhnro=" + kayttaja_puhnro
				+ ", kayttaja_osoite=" + kayttaja_osoite + ", kayttaja_sposti="
				+ kayttaja_sposti + ", ryhma_id=" + ryhma_id + "]";
	}
	
	
	
}