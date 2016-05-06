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

	public void addPizza(Pizza pizza, int oregano, int VSipuli, int lkm){ // Lisätään ostoskoriin Pizza
		TilattuTuote tuote = new TilattuTuote(); //Luodaan tilattuTuote
		
		if(pizza != null){ //Jos tullut pizza ei ole tyhjä luodaan liisä TilattuTuote olio
			tuote.setTuote(pizza);
			tuote.setOregano(oregano);
			tuote.setvSipuli(VSipuli);
			tuote.setHinta(tuote.getTuote().getHinta());
			tuote.setLkm(lkm);
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
				if(kori.get(i).getTuote() instanceof Pizza){
					Pizza p = (Pizza) kori.get(i).getTuote(); // haetaan korista tuote pizzaolioksi
					Pizza tp = (Pizza) tuote.getTuote(); // tehdään tulleesta tuotteesta pizza olio
					System.out.println("tp " + tp);
					if(kori.get(i).getTuote().getId() == tuote.getTuote().getId() // katsotaan tuoteid, oregano ja vSipuli
					   && kori.get(i).getOregano() == tuote.getOregano() 
					   && kori.get(i).getvSipuli() == tuote.getvSipuli()
					   && p.getIDT().equals(tp.getIDT()) == true){ // katsotaan ovatko tuotteet samat
					System.out.println("on jo ");
					int luku = kori.get(i).getLkm();
					luku += tuote.getLkm(); // nostetaan lukumäärä tulleella kpl määrällä
					kori.get(i).setLkm(luku); // ostoskorin lukumääräksi asetetaan vanha lkm+tullut lkm
					this.ostoskori = kori; //tuotteisiin viedään muokattu lista tuotteita
					loytyy = true; // vaihdetaan loytyy arvo trueksi
					break; //ja lopetetaan for loop
				}else{
					loytyy = false; //jos tuotetta ei löytynyt pidetään loytyy falsena
				}
				}
			}
		}
		if(loytyy == false){ // jos vastaavaa TilattuTuote oliota ei löydy lisätään ostoskoriin uusi TilattuTuote
			this.ostoskori.add(tuote);
			this.koko++;
		}
		
	}
	
	public void removeTuote(int paikka){
		int p = paikka;
		
		if(p >= 0){ //Jos tullut id ei ole alle 0 mennään eteenpäin
			if(this.ostoskori != null){ //Jos ostoskori ei ole tyhjä katsotaan löytyykö sieltä jo vastaava TilattuTuote olio
				ArrayList<TilattuTuote> kori = this.ostoskori;
				int luku = kori.get(p).getLkm();
				if(luku > 1) { // katsotaan tuotteen lkm
					luku -= 1;	// jos tuotetta on enemmän kuin 1 korissa vähennetään lukumäärää
					kori.get(p).setLkm(luku);
				}else{	// jos tuotteita on 1 poistetaan tuote korista
					kori.remove(p);
					this.koko --;
				}
					this.ostoskori = kori; //viedään muutettu tieto koriin
			}
		}
		if(this.ostoskori.isEmpty()){
			this.ostoskori = null;
		}
		
	}
	
	public void addJuoma(Juoma juoma, int lkm){
		TilattuTuote tuote = new TilattuTuote(); //Luodaan tilattuTuote
		boolean loytyy = false;
		
		if(juoma != null){ //Jos tullut juoma ei ole tyhjä luodaan TilattuTuote olio
			tuote.setTuote(juoma);
			tuote.setHinta(tuote.getTuote().getHinta());
			tuote.setLkm(lkm);
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
						luku += tuote.getLkm();	// nostetaan lukumäärä tulleella kpl määrällä
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
