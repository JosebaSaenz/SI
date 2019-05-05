package Interfaze;

import java.util.ArrayList;

import Proiektua.GomendioSistema;
import Proiektua.IragazteSistema;
import Proiektua.PelikulaKatalogo;
import Salbuespenak.ErabiltzaileaEzDaExistitzenException;
import Salbuespenak.PelikulaEzDaExistitzenException;

public class MVC {

	private static MVC nireDatuak = null;
	
	private MVC() {}
	
	public static synchronized MVC getMVC() {
		if (nireDatuak == null) {
			nireDatuak = new MVC();
		}
		return nireDatuak;
	}
	
	public void Datuak_Kargatu() {
		GomendioSistema.getGomendioSistema().datuakKargatu(".csv mota2");
	}
	
	public void datuakEzabatu() {
		PelikulaKatalogo.getPelikulaKatalogo().datuakEzabatu();
		GomendioSistema.getGomendioSistema().datuakEzabatu();
	}
	
	public ArrayList<String> getPelikulak(){
		return PelikulaKatalogo.getPelikulaKatalogo().pelikulakEskuratu();
	}
	
	public double erabiltzaileaBalorazioaEstimazioa(int idUser, int idMovie) throws ErabiltzaileaEzDaExistitzenException, PelikulaEzDaExistitzenException {
		double estimazioa = 0;
		estimazioa = IragazteSistema.getIragazteSistema().erabiltzaileaBalorazioaEstimazioa(idUser, idMovie);
		return estimazioa;
	}
	
	public double produktuaBalorazioaEstimazioa(int idUser, int idMovie) throws ErabiltzaileaEzDaExistitzenException, PelikulaEzDaExistitzenException {
		double estimazioa = 0;
		estimazioa = IragazteSistema.getIragazteSistema().produktuaBalorazioaEstimazioa(idUser, idMovie);
		return estimazioa;
	}
	
	public double ezaugarriaBalorazioaEstimazioa(int idUser, int idMovie) throws ErabiltzaileaEzDaExistitzenException, PelikulaEzDaExistitzenException {
		double estimazioa = 0;
		estimazioa = IragazteSistema.getIragazteSistema().ezaugarriaBalorazioaEstimazioa(idUser, idMovie);
		return estimazioa;
	}

	public ArrayList<String> gomendatu(int idUser) {
		return IragazteSistema.getIragazteSistema().gomendatu(idUser);
	}

	public ArrayList<String> ikusitakoPelikulakLortu(int idUser) {
		return GomendioSistema.getGomendioSistema().ikusitakoPelikulakLortu(idUser);
	}

	public void setPasahitza(String pPasahitza) {
		Login.getLogin().setPasahitza((pPasahitza));		
	}

	public boolean pelikulaIkusiDu(int idUser, int idMovie) {
		return GomendioSistema.getGomendioSistema().pelikulaIkusiDu(idUser,idMovie);
	}
	
}