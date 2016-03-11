package pizzeria_pizzicato.model;

public class PizzaTayte {

	private int pId, tId;


	public PizzaTayte() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PizzaTayte(int pId, int tId) {
		super();
		this.pId = pId;
		this.tId = tId;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public int gettId() {
		return tId;
	}

	public void settId(int tId) {
		this.tId = tId;
	}

	@Override
	public String toString() {
		return "PizzaTayte [pId=" + pId + ", tId=" + tId + "]";
	}	
	
}
