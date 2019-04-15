package Proiektua;

import java.util.ArrayList;
import java.util.HashMap;

public class BektoreTag {
	
	private HashMap<String,Double> zerrenda;
	private ArrayList<String> idItzultzailea;
	
	/*
		Zerrendaren balioak HashMap batean gordetzen dira.
		Zerrenda errekorritzeko ArrayList<String> erabiltzen dugu.
	*/
	
	public BektoreTag() {
		zerrenda = new HashMap<String,Double>();
		idItzultzailea = new ArrayList<String>();
	}

	public void gehituElementua(String idErreala, double balioa) {
		idItzultzailea.add(idErreala);
		zerrenda.put(idErreala, balioa);
	}

	public boolean bektoreanDago(String idErreala) {
		return zerrenda.containsKey(idErreala);
	}

	public double getBalioa(String idErreala) throws NullPointerException{
		return zerrenda.get(idErreala);
	}

	public int luzera() {
		return zerrenda.size();
	}

	public String getElementuarenIdErreala(int pPos) {
		return idItzultzailea.get(pPos);
	}

	public double getPosiziokoBalioa(int pPos) {
		return zerrenda.get(idItzultzailea.get(pPos));
	}

	public void gehituBalioa(String idErreala, double pBalioa) {
		zerrenda.put(idErreala, zerrenda.get(idErreala)+pBalioa);
	}
	
}
