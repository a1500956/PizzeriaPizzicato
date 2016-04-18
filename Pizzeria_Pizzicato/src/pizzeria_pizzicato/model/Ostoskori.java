package pizzeria_pizzicato.model;

import java.util.ArrayList;

public class Ostoskori {
	
	ArrayList<TilattuTuote> ostoskori;
	
	public Ostoskori(ArrayList<TilattuTuote> ostoskori) {
		super();
		this.ostoskori = ostoskori;
	}

	public Ostoskori() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void addPizza(Pizza pizza, int oregano, int VSipuli){ // Lis�t��n ostoskoriin Pizza
		TilattuTuote tuote = new TilattuTuote(); //Luodaan tilattuTuote
		Tuote t = new Tuote(); //Luodaan tuote
		
		if(pizza != null){ //Jos tullut pizza ei ole tyhj� luodaan liis� TilattuTuote olio
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
		System.out.println("t�ss� ostoskori " + this.ostoskori);
		if(ostoskori == null){ //Katsotaan onko ostoskori tyhj�, jos on lis�t��n TilattuTuote ostoskoriin
			this.ostoskori = (ArrayList<TilattuTuote>) new ArrayList<TilattuTuote>();
			System.out.println("MOI!");
			this.ostoskori.add(tuote);
			loytyy = true;
			return;
		}else if(this.ostoskori != null){ //Jos ostoskori ei ole tyhj� katsotaan l�ytyyk� sielt� jo vastaava TilattuTuote olio
			ArrayList<TilattuTuote> kori = this.ostoskori;
			for(int i = 0; i < kori.size();i++){
				TilattuTuote sArvo = vertaa(kori.get(i));
				if(kori.get(i).getTuote().equals(tuote.getTuote())){
					System.out.println("Hei!");
					int luku = kori.get(i).getLkm();
					luku += 1;
					kori.get(i).setLkm(luku);
					this.ostoskori = kori; //Jos l�ytyy lis�t��n lukum��r��
					loytyy = true; // vaihdetaan loytyy arvo trueksi
					break; //ja lopetetaan for loop
				}else{
					loytyy = false;
				}
			}
		}
		if(loytyy == false){ // jos vastaavaa TilattuTuote oliota ei l�ydy lis�t��n ostoskoriin uusi TilattuTuote
			System.out.println("MOIKKA!");
			this.ostoskori.add(tuote);
		}
		
	}

	public TilattuTuote vertaa(TilattuTuote vertaa){
		TilattuTuote verrattu = new TilattuTuote();
		
		return verrattu;
	}


	@Override
	public String toString() {
		return "Ostoskori [ostoskori=" + ostoskori + "]";
	}
	
	
	
}
