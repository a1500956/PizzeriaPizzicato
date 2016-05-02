package pizzeria_pizzicato.model;

public class Lukija {

	public double lueDesimaaliluku(String sArvo) {

		String x = sArvo.replace(",", ".");
		return Double.parseDouble(x);
	}

}