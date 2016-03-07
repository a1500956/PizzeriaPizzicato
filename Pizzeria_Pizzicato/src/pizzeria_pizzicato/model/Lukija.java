package pizzeria_pizzicato.model;

import java.util.Scanner;

public class Lukija {
	private Scanner input = new Scanner(System.in);
	
	public int lueKokonaisluku(String ilmo){
		String sArvo;
		int paluuArvo=0;
		boolean kelvollinen=false;
		do{
			System.out.print(ilmo);
			sArvo=input.nextLine();
			try {
				paluuArvo = Integer.parseInt(sArvo);
				kelvollinen=true;
			} catch (Exception e) {
				System.out.println("Antamasi arvo ei ole kokonaisluku!");
			}			
		}while(kelvollinen==false);
		return paluuArvo;
	}
	
	public double lueDesimaaliluku(String sArvo){
		
		String x = sArvo.replace(",", ".");
		return Double.parseDouble(x);
	}
	
	public String lueTeksti(String ilmo){
		System.out.print(ilmo);
		return input.nextLine();
	}
}