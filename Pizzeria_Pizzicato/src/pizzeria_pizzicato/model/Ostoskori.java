package pizzeria_pizzicato.model;

import java.util.ArrayList;
import java.util.Collections;

import pizzeria_pizzicato.control.VertailijaTayte;
import pizzeria_pizzicato.control.VertailijaTuote;

public class Ostoskori {
	
	ArrayList<TilattuTuote> tuotteet;
	int koko = 0;
	
	public Ostoskori(ArrayList<TilattuTuote> ostoskori, int koko) {
		super();
		this.tuotteet = ostoskori;
		this.koko =  koko;
	}

	public Ostoskori() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void addPizza(Pizza pizza, int oregano, int VSipuli, int lkm){ // Lis�t��n ostoskoriin Pizza
		TilattuTuote tuote = new TilattuTuote(); //Luodaan tilattuTuote
		
		if(pizza != null){ //Jos tullut pizza ei ole tyhj� luodaan liis� TilattuTuote olio ja suoritetaan seuraavat
			tuote.setTuote(pizza);
			tuote.setOregano(oregano);
			tuote.setvSipuli(VSipuli);
			tuote.setHinta(tuote.getTuote().getHinta());
			tuote.setLkm(lkm);
		
		
		boolean loytyy = false;
		if(this.tuotteet == null){ //Katsotaan onko ostoskori tyhj�, jos on lis�t��n TilattuTuote ostoskoriin
			this.tuotteet = (ArrayList<TilattuTuote>) new ArrayList<TilattuTuote>();
			this.tuotteet.add(tuote);
			this.koko++; // lis�t��n korin kokoa
			loytyy = true;
			return;
		}else if(this.tuotteet != null){ //Jos ostoskori ei ole tyhj� katsotaan l�ytyyk� sielt� jo vastaava TilattuTuote olio
			ArrayList<TilattuTuote> kori = this.tuotteet;
			for(int i = 0; i < kori.size();i++){
				if(kori.get(i).getTuote() instanceof Pizza){
					Pizza p = (Pizza) kori.get(i).getTuote(); // haetaan korista tuote pizzaolioksi
					Pizza tp = (Pizza) tuote.getTuote(); // tehd��n tulleesta tuotteesta pizza olio
					Collections.sort(tp.getTaytteet(), new VertailijaTayte());
					if(kori.get(i).getTuote().getId() == tuote.getTuote().getId() // katsotaan tuoteid, oregano ja vSipuli
					   && kori.get(i).getOregano() == tuote.getOregano() 
					   && kori.get(i).getvSipuli() == tuote.getvSipuli()
					   && p.getIDT().equals(tp.getIDT()) == true){ // katsotaan ovatko tuotteet samat
					int luku = kori.get(i).getLkm();
					luku += tuote.getLkm(); // nostetaan lukum��r� tulleella kpl m��r�ll�
					kori.get(i).setLkm(luku); // ostoskorin lukum��r�ksi asetetaan vanha lkm+tullut lkm
					this.tuotteet = kori; //tuotteisiin vied��n muokattu lista tuotteita
					loytyy = true; // vaihdetaan loytyy arvo trueksi
					break; //ja lopetetaan for loop
				}else{
					loytyy = false; //jos tuotetta ei l�ytynyt pidet��n loytyy falsena
				}
				}
			}
		}
		if(loytyy == false){ // jos vastaavaa TilattuTuote oliota ei l�ydy lis�t��n ostoskoriin uusi TilattuTuote
			this.tuotteet.add(tuote);
			this.koko++;
		}
		}
		
	}
	
	public void removeTuote(int paikka){
		int p = paikka;
		
		if(p >= 0){ //Jos tullut id ei ole alle 0 menn��n eteenp�in
			if(this.tuotteet != null){ //Jos ostoskori ei ole tyhj� katsotaan l�ytyyk� sielt� jo vastaava TilattuTuote olio
				ArrayList<TilattuTuote> kori = this.tuotteet;
				int luku = kori.get(p).getLkm();
				if(luku > 1) { // katsotaan tuotteen lkm
					luku -= 1;	// jos tuotetta on enemm�n kuin 1 korissa v�hennet��n lukum��r��
					kori.get(p).setLkm(luku);
				}else{	// jos tuotteita on 1 poistetaan tuote korista
					kori.remove(p);
					this.koko --;
				}
					this.tuotteet = kori; //vied��n muutettu tieto koriin
			}
		}
		if(this.tuotteet.isEmpty()){
			this.tuotteet = null;
		}
		
	}
	
	public void addJuoma(Juoma juoma, int lkm){
		TilattuTuote tuote = new TilattuTuote(); //Luodaan tilattuTuote
		boolean loytyy = false;
		
		if(juoma != null){ //Jos tullut juoma ei ole tyhj� luodaan TilattuTuote olio
			tuote.setTuote(juoma);
			tuote.setHinta(tuote.getTuote().getHinta());
			tuote.setLkm(lkm);
		}
		if(this.tuotteet == null){ //Katsotaan onko ostoskori tyhj�, jos on lis�t��n TilattuTuote ostoskoriin
			this.tuotteet = (ArrayList<TilattuTuote>) new ArrayList<TilattuTuote>();
			this.tuotteet.add(tuote);
			this.koko++; // lis�t��n korin kokoa
			loytyy = true;
			return;
		}else if(this.tuotteet != null){ //Jos ostoskori ei ole tyhj� katsotaan l�ytyyk� sielt� jo vastaava TilattuTuote olio
			ArrayList<TilattuTuote> kori = this.tuotteet;
			for(int i = 0; i < kori.size();i++){
				if(kori.get(i).getTuote().getId() == tuote.getTuote().getId()){ // katsotaan
					int luku = kori.get(i).getLkm();
						luku += tuote.getLkm();	// nostetaan lukum��r� tulleella kpl m��r�ll�
						kori.get(i).setLkm(luku);
					this.tuotteet = kori; //vied��n muutettu tieto koriin
					loytyy = true;
					break; //ja lopetetaan for loop
				}else{
					loytyy = false; //jos tuotetta ei l�ytynyt pidet��n loytyy falsena
				}
			}
		}
		if(loytyy == false){ // jos vastaavaa TilattuTuote oliota ei l�ydy lis�t��n ostoskoriin uusi TilattuTuote
			this.tuotteet.add(tuote);
			this.koko++;
		}
	}
	
	public TilattuTuote getTuote(int index) {
		TilattuTuote tuote = null;
		if(index>=0 && index < tuotteet.size() ){
		tuote = tuotteet.get(index);
		}
		return tuote;
	}
	
	public ArrayList<TilattuTuote> getTuotteet() {
		return tuotteet;
	}

	public void setTuotteet(ArrayList<TilattuTuote> ostoskori) {
		this.tuotteet = ostoskori;
	}
	
	public int getMaara() {
		int lkm = 0;
		for(int i = 0; i<this.koko; i++){
			lkm += this.tuotteet.get(i).getLkm();
		}
		return lkm;
	}

	public int getKoko() {
		return koko;
	}

	public void setKoko(int koko) {
		this.koko = koko;
	}


	@Override
	public String toString() {
		return "Ostoskori [koko=" + koko + " tuotteet=" + tuotteet + "]";
	}
	
	
	
}
