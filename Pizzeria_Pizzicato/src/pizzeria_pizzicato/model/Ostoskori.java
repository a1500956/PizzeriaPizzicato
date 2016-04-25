package pizzeria_pizzicato.model;

import java.util.ArrayList;

public class Ostoskori {
	
	ArrayList<TilattuTuote> ostoskori;
	int koko = 0;
	
	public Ostoskori(ArrayList<TilattuTuote> ostoskori, int koko) {
		super();
		this.ostoskori = ostoskori;
		this.koko =  koko;
	}

	public Ostoskori() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void addPizza(Pizza pizza, int oregano, int VSipuli){ // Lisätään ostoskoriin Pizza
		TilattuTuote tuote = new TilattuTuote(); //Luodaan tilattuTuote
		Tuote t = new Tuote(); //Luodaan tuote
		
		if(pizza != null){ //Jos tullut pizza ei ole tyhjä luodaan liisä TilattuTuote olio
			t.setId(pizza.getId());
			t.setNimi(pizza.getNimi());
			t.setHinta(pizza.getHinta());
			tuote.setTuote(t);
			tuote.setOregano(oregano);
			tuote.setvSipuli(VSipuli);
			tuote.setHinta(tuote.getTuote().getHinta());
			tuote.setLkm(1);
		}
		
		boolean loytyy = false;
		if(this.ostoskori == null){ //Katsotaan onko ostoskori tyhjä, jos on lisätään TilattuTuote ostoskoriin
			this.ostoskori = (ArrayList<TilattuTuote>) new ArrayList<TilattuTuote>();
			this.ostoskori.add(tuote);
			this.koko++; // lisätään korin kokoa
			loytyy = true;
			return;
		}else if(this.ostoskori != null){ //Jos ostoskori ei ole tyhjä katsotaan löytyykö sieltä jo vastaava TilattuTuote olio
			ArrayList<TilattuTuote> kori = this.ostoskori;
			for(int i = 0; i < kori.size();i++){
				if(kori.get(i).getTuote().getId() == tuote.getTuote().getId() // katsotaan tuoteid, oregano ja vSipuli
				   && kori.get(i).getOregano() == tuote.getOregano() 
				   && kori.get(i).getvSipuli() == tuote.getvSipuli()){
					int luku = kori.get(i).getLkm();
					luku += 1;
					kori.get(i).setLkm(luku);
					this.ostoskori = kori; //Jos löytyy lisätään lukumäärää
					loytyy = true; // vaihdetaan loytyy arvo trueksi
					break; //ja lopetetaan for loop
				}else{
					loytyy = false; //jos tuotetta ei löytynyt pidetään loytyy falsena
				}
			}
		}
		if(loytyy == false){ // jos vastaavaa TilattuTuote oliota ei löydy lisätään ostoskoriin uusi TilattuTuote
			this.ostoskori.add(tuote);
			this.koko++;
		}
		
	}
	
	public void removeTuote(int id, int oregano, int VSipuli){
		TilattuTuote tuote = new TilattuTuote(); //Luodaan tilattuTuote
		Tuote t = new Tuote(); //Luodaan tuote
		
		if(id > 0){ //Jos tullut pizza ei ole tyhjä luodaan liisä TilattuTuote olio
			t.setId(id);
			tuote.setTuote(t);
			tuote.setOregano(oregano);
			tuote.setvSipuli(VSipuli);
			tuote.setHinta(tuote.getTuote().getHinta());
			tuote.setLkm(1);
		}
		if(this.ostoskori != null){ //Jos ostoskori ei ole tyhjä katsotaan löytyykö sieltä jo vastaava TilattuTuote olio
			ArrayList<TilattuTuote> kori = this.ostoskori;
			for(int i = 0; i < kori.size();i++){
				if(kori.get(i).getTuote().getId() == tuote.getTuote().getId() // katsotaan tuoteid, oregano ja vSipuli
				   && kori.get(i).getOregano() == tuote.getOregano() 
				   && kori.get(i).getvSipuli() == tuote.getvSipuli()){
					int luku = kori.get(i).getLkm();
					if(luku > 1) { // katsotaan tuotteen lkm
						luku -= 1;	// jos tuotetta on enemmän kuin 1 korissa vähennetään lukumäärää
						kori.get(i).setLkm(luku);
					}else{	// jos tuotteita on 1 poistetaan tuote korista
						kori.remove(i);
						this.koko --;
					}
					this.ostoskori = kori; //viedään muutettu tieto koriin
					break; //ja lopetetaan for loop
				}
			}
		}
		if(this.ostoskori.isEmpty()){
			this.ostoskori = null;
		}
		
	}
	
	public void addJuoma(Juoma juoma){
		TilattuTuote tuote = new TilattuTuote(); //Luodaan tilattuTuote
		Tuote t = new Tuote(); //Luodaan tuote
		boolean loytyy = false;
		
		if(juoma != null){ //Jos tullut juoma ei ole tyhjä luodaan TilattuTuote olio
			t.setId(juoma.getId());
			t.setNimi(juoma.getNimi());
			t.setHinta(juoma.getHinta());
			tuote.setTuote(t);
			tuote.setHinta(tuote.getTuote().getHinta());
			tuote.setLkm(1);
		}
		if(this.ostoskori == null){ //Katsotaan onko ostoskori tyhjä, jos on lisätään TilattuTuote ostoskoriin
			this.ostoskori = (ArrayList<TilattuTuote>) new ArrayList<TilattuTuote>();
			this.ostoskori.add(tuote);
			this.koko++; // lisätään korin kokoa
			loytyy = true;
			return;
		}else if(this.ostoskori != null){ //Jos ostoskori ei ole tyhjä katsotaan löytyykö sieltä jo vastaava TilattuTuote olio
			ArrayList<TilattuTuote> kori = this.ostoskori;
			for(int i = 0; i < kori.size();i++){
				if(kori.get(i).getTuote().getId() == tuote.getTuote().getId()){ // katsotaan
					int luku = kori.get(i).getLkm();
						luku += 1;	// nostetaan lukumäärä yhdellä
						kori.get(i).setLkm(luku);
					this.ostoskori = kori; //viedään muutettu tieto koriin
					loytyy = true;
					break; //ja lopetetaan for loop
				}else{
					loytyy = false; //jos tuotetta ei löytynyt pidetään loytyy falsena
				}
			}
		}
		if(loytyy == false){ // jos vastaavaa TilattuTuote oliota ei löydy lisätään ostoskoriin uusi TilattuTuote
			this.ostoskori.add(tuote);
			this.koko++;
		}
	}
	
	public TilattuTuote getTuote(int index) {
		TilattuTuote tuote = null;
		if(index>=0 && index < ostoskori.size() ){
		tuote = ostoskori.get(index);
		}
		return tuote;
	}
	
	public ArrayList<TilattuTuote> getOstoskori() {
		return ostoskori;
	}

	public void setOstoskori(ArrayList<TilattuTuote> ostoskori) {
		this.ostoskori = ostoskori;
	}
	
	public int getMaara() {
		int lkm = 0;
		for(int i = 0; i<this.koko; i++){
			lkm += this.ostoskori.get(i).getLkm();
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
		return "Ostoskori [koko=" + koko + " ostoskori=" + ostoskori + "]";
	}
	
	
	
}
