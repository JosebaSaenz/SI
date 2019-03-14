package Interfaze;

import java.util.ArrayList;


import Proiektua.GomendioSistema;
import Salbuespenak.ErabiltzaileaEzDaExistitzenException;
import Salbuespenak.KargaMotaEzDaExistitzenException;
import Salbuespenak.PelikulaEzDaExistitzenException;
public class Grafiko_Kontrolagailua {

	private static Grafiko_Kontrolagailua nireDatuak = null;
	
	private Grafiko_Kontrolagailua() {}
	
	public static synchronized Grafiko_Kontrolagailua getNireDatuak() {
		if (nireDatuak == null) {
			nireDatuak = new Grafiko_Kontrolagailua();
		}
		return nireDatuak;
	}
	
	public void Datuak_Kargatu() throws ErabiltzaileaEzDaExistitzenException, PelikulaEzDaExistitzenException, KargaMotaEzDaExistitzenException {
		GomendioSistema.getGomendioSistema().datuakKargatu();
	}
	
	public void datuakEzabatu() {
		GomendioSistema.getGomendioSistema().datuakEzabatu();
	}
	
	public ArrayList<String> getPelikulak(){
		return GomendioSistema.getGomendioSistema().pelikulakEskuratu();
	}
	
	public ArrayList<Integer> getErabiltzaileak(){
		return GomendioSistema.getGomendioSistema().erabiltzaileakEskuratu();
	}
	
}
