package Proiektua;

import java.util.ArrayList;
import java.util.HashMap;

public class Bektorea {
	
	private HashMap<Integer,Double> zerrenda;
	private ArrayList<Integer> idItzultzailea;
	
	/*
		Zerrendaren balioak HashMap batean gordetzen dira.
		Zerrenda errekorritzeko ArrayList<Integer> erabiltzen dugu.
	*/
	
	public Bektorea() {
		zerrenda = new HashMap<Integer,Double>();
		idItzultzailea = new ArrayList<Integer>();
	}

	public void gehituElementua(Integer idErreala, double balioa) {
		idItzultzailea.add(idErreala);
		zerrenda.put(idErreala, balioa);
	}

	public boolean bektoreanDago(int idErreala) {
		return zerrenda.containsKey(idErreala);
	}

	public double getBalioa(int idErreala) throws NullPointerException{
		return zerrenda.get(idErreala);
	}

	public int luzera() {
		return zerrenda.size();
	}

	public int getElementuarenIdErreala(int pPos) {
		return idItzultzailea.get(pPos);
	}

	public double getPosiziokoBalioa(int pPos) {
		return zerrenda.get(idItzultzailea.get(pPos));
	}

	public double bektorearenBatezbestekoa() {		// zerrendaren balioen batezbestekoa kalkulatu
		double emaitza = 0.0;
		for(int i=0; i<zerrenda.size(); i++) {
			emaitza = emaitza + zerrenda.get(idItzultzailea.get(i));
		}
		emaitza = (double)emaitza/zerrenda.size();
		return emaitza;
	}

	public double bektorearenDesbiderapenEstandarra() {			// zerrendaren balioen desbiderapen estandarra kalkulatu
		double emaitza = 0.0;
		double batezbestekoa = bektorearenBatezbestekoa();
		for(int i=0; i<zerrenda.size(); i++) {
			emaitza = emaitza + Math.pow(zerrenda.get(idItzultzailea.get(i)) - batezbestekoa, 2);
		}
		emaitza = Math.sqrt((double)emaitza/zerrenda.size());
		return emaitza;
	}
	
}
