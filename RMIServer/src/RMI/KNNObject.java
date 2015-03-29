package RMI;

import java.io.Serializable;

		//Serializable OBJEMİZ
public class KNNObject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public double sepalL;
	public double sepalW;
	public double petalL;
	public double petalW;
	public String sinifEtiketi;
	
	
	
	public KNNObject(double sepalL, double sepalW, double petalL, double petalW) {
		super();
		this.sepalL = sepalL;
		this.sepalW = sepalW;
		this.petalL = petalL;
		this.petalW = petalW;
		this.sinifEtiketi = "";
	}
	
	public double getSepalL() {
		return sepalL;
	}
	public void setSepalL(double sepalL) {
		this.sepalL = sepalL;
	}
	public double getSepalW() {
		return sepalW;
	}
	public void setSepalW(double sepalW) {
		this.sepalW = sepalW;
	}
	public double getPetalL() {
		return petalL;
	}
	public void setPetalL(double petalL) {
		this.petalL = petalL;
	}
	public double getPetalW() {
		return petalW;
	}
	public void setPetalW(double petalW) {
		this.petalW = petalW;
	}
	public String getSinifEtiketi() {
		return sinifEtiketi;
	}
	public void setSinifEtiketi(String sinifEtiketi) {
		this.sinifEtiketi = sinifEtiketi;
	}
	
	

}
