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

	public void addPizza(Pizza pizza, int oregano, int VSipuli, int lkm){ // Lis�t��n ostoskoriin Pizza
		TilattuTuote tuote = new TilattuTuote(); //Luodaan tilattuTuote
		
		if(pizza != null){ //Jos tullut pizza ei ole tyhj� luodaan liis� TilattuTuote olio
			tuote.setTuote(pizza);
			tuote.setOregano(oregano);
			tuote.setvSipuli(VSipuli);
			tuote.setHinta(tuote.getTuote().getHinta());
			tuote.setLkm(lkm);
		}
		
		boolean loytyy = false;
		if(this.ostoskori == null){ //Katsotaan onko ostoskori tyhj�, jos on lis�t��n TilattuTuote ostoskoriin
			this.ostoskori = (ArrayList<TilattuTuote>) new ArrayList<TilattuTuote>();
			this.ostoskori.add(tuote);
			this.koko++; // lis�t��n korin kokoa
			loytyy = true;
			return;
		}else if(this.ostoskori != null){ //Jos ostoskori ei ole tyhj� katsotaan l�ytyyk� sielt� jo vastaava TilattuTuote olio
			ArrayList<TilattuTuote> kori = this.ostoskori;
			for(int i = 0; i < kori.size();i++){
				if(kori.get(i).getTuote() instanceof Pizza){
					Pizza p = (Pizza) kori.get(i).getTuote(); // haetaan korista tuote pizzaolioksi
					Pizza tp = (Pizza) tuote.getTuote(); // tehd��n tulleesta tuotteesta pizza olio
					System.out.println("tp " + tp);
					if(kori.get(i).getTuote().getId() == tuote.getTuote().getId() // katsotaan tuoteid, oregano ja vSipuli
					   && kori.get(i).getOregano() == tuote.getOregano() 
					   && kori.get(i).getvSipuli() == tuote.getvSipuli()
					   && p.getIDT().equals(tp.getIDT()) == true){ // katsotaan ovatko tuotteet samat
					System.out.println("on jo ");
					int luku = kori.get(i).getLkm();
					luku += tuote.getLkm(); // nostetaan lukum��r� tulleella kpl m��r�ll�
					kori.get(i).setLkm(luku); // ostoskorin lukum��r�ksi asetetaan vanha lkm+tullut lkm
					this.ostoskori = kori; //tuotteisiin vied��n muokattu lista tuotteita
					loytyy = true; // vaihdetaan loytyy arvo trueksi
					break; //ja lopetetaan for loop
				}else{
					loytyy = false; //jos tuotetta ei l�ytynyt pidet��n loytyy falsena
				}
				}
			}
		}
		if(loytyy == false){ // jos vastaavaa TilattuTuote oliota ei l�ydy lis�t��n ostoskoriin uusi TilattuTuote
			this.ostoskori.add(tuote);
			this.koko++;
		}
		
	}
	
	public void removeTuote(int paikka){
		int p = paikka;
		
		if(p >= 0){ //Jos tullut id ei ole alle 0 menn��n eteenp�in
			if(this.ostoskori != null){ //Jos ostoskori ei ole tyhj� katsotaan l�ytyyk� sielt� jo vastaava TilattuTuote olio
				ArrayList<TilattuTuote> kori = this.ostoskori;
				int luku = kori.get(p).getLkm();
				if(luku > 1) { // katsotaan tuotteen lkm
					luku -= 1;	// jos tuotetta on enemm�n kuin 1 korissa v�hennet��n lukum��r��
					kori.get(p).setLkm(luku);
				}else{	// jos tuotteita on 1 poistetaan tuote korista
					kori.remove(p);
					this.koko --;
				}
					this.ostoskori = kori; //vied��n muutettu tieto koriin
			}
		}
		if(this.ostoskori.isEmpty()){
			this.ostoskori = null;
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
		if(this.ostoskori == null){ //Katsotaan onko ostoskori tyhj�, jos on lis�t��n TilattuTuote ostoskoriin
			this.ostoskori = (ArrayList<TilattuTuote>) new ArrayList<TilattuTuote>();
			this.ostoskori.add(tuote);
			this.koko++; // lis�t��n korin kokoa
			loytyy = true;
			return;
		}else if(this.ostoskori != null){ //Jos ostoskori ei ole tyhj� katsotaan l�ytyyk� sielt� jo vastaava TilattuTuote olio
			ArrayList<TilattuTuote> kori = this.ostoskori;
			for(int i = 0; i < kori.size();i++){
				if(kori.get(i).getTuote().getId() == tuote.getTuote().getId()){ // katsotaan
					int luku = kori.get(i).getLkm();
						luku += tuote.getLkm();	// nostetaan lukum��r� tulleella kpl m��r�ll�
						kori.get(i).setLkm(luku);
					this.ostoskori = kori; //vied��n muutettu tieto koriin
					loytyy = true;
					break; //ja lopetetaan for loop
				}else{
					loytyy = false; //jos tuotetta ei l�ytynyt pidet��n loytyy falsena
				}
			}
		}
		if(loytyy == false){ // jos vastaavaa TilattuTuote oliota ei l�ydy lis�t��n ostoskoriin uusi TilattuTuote
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
