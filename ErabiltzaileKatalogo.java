package Proiektua;

import java.util.HashMap;

public class ErabiltzaileKatalogo {

	private HashMap<Integer,Erabiltzailea> lista;
	private static ErabiltzaileKatalogo nErabiltzaileak = null;
	
	private ErabiltzaileKatalogo() {
		this.lista= new HashMap<Integer,Erabiltzailea>();
	}
	
	public static synchronized ErabiltzaileKatalogo getErabiltzaileKatalogo() {
		if (nErabiltzaileak== null) {
			nErabiltzaileak = new ErabiltzaileKatalogo();
		}
		return nErabiltzaileak;
	}
	
	public int zenbatErabiltzaile() {
		return this.lista.size();
	}
	public boolean ikusiDu(int pErabId, int pPelId) {
		return this.lista.get(pErabId).ikusiDu(pPelId);
	}
}


