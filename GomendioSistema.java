package Proiektua;
import java.util.ArrayList;
import java.util.HashMap;

import Interfaze.Lehio_Nagusia;
import Salbuespenak.ErabiltzaileaEzDaExistitzenException;
import Salbuespenak.KargaMotaEzDaExistitzenException;
import Salbuespenak.PelikulaEzDaExistitzenException;

public class GomendioSistema {

	private static GomendioSistema nGomendioSistema;
	private HashMap<Integer,Erabiltzailea> erabiltzaileak;
	private ArrayList<Integer> idErabiltzaileak;
	private HashMap<String,Integer> tagIDak;
	
	private GomendioSistema() {
		erabiltzaileak = new HashMap<Integer,Erabiltzailea>();
		idErabiltzaileak = new ArrayList<Integer>();
		tagIDak = new HashMap<String,Integer>();
	}
	
	public static synchronized GomendioSistema getGomendioSistema() {
		if (nGomendioSistema == null) {
			nGomendioSistema = new GomendioSistema();
		}
		return nGomendioSistema;
	}
	
	public void datuakKargatu() throws ErabiltzaileaEzDaExistitzenException, PelikulaEzDaExistitzenException, KargaMotaEzDaExistitzenException {
		DatuenKarga nireDatuenKarga = KargaFactory.getKargaFactory().createKarga(".csv proba");
		nireDatuenKarga.datuakKargatu();
	}
	
	public void gehituErabiltzailea(Integer id, Erabiltzailea e) {
		if(!erabiltzaileak.containsKey(id)) {
			idErabiltzaileak.add(id);
			this.erabiltzaileak.put(id, e);
		}
	}
	
	public boolean erregistratutaDago(int idUser) {
		return erabiltzaileak.containsKey(idUser);
	}
	
	public Erabiltzailea getErabiltzailea(int idUser) throws ErabiltzaileaEzDaExistitzenException  {
		Erabiltzailea erab = erabiltzaileak.get(idUser);
		if(erab == null) {
			throw new ErabiltzaileaEzDaExistitzenException(idUser);
		}
		return erab;
	}
	
	public ArrayList<String> erabiltzaileakEskuratu() throws ErabiltzaileaEzDaExistitzenException{
		ArrayList<String> idUser = new ArrayList<String>();
		for(int i=0; i<erabiltzaileak.size(); i++) {
			Integer erab = erabiltzaileak.get(idErabiltzaileak.get(i)).getId();
			int pelikulaKop = GomendioSistema.getGomendioSistema().getErabiltzailea(erab).ikusitakoPelikulaKop();
			idUser.add("'" + erab.toString() + "' erabiltzaileak " + pelikulaKop + " pelikula ikusi ditu.");
		}
		return idUser;
	}
	
	public void datuakEzabatu() {
		erabiltzaileak.clear();
	}
	
	public HashMap<Integer,Erabiltzailea> getErabiltzaileak() {
		return erabiltzaileak;
	}
	
	public void erreseteatu() {
		GomendioSistema.nGomendioSistema = null;
	}

	public int zenbatErabiltzaile() {
		return erabiltzaileak.size();
	}
	
	public boolean komentarioaGordetaDago(String pTag) {
		return tagIDak.containsKey(pTag);
	}
	
	public int getKomentarioarenId(String pTag) {
		return tagIDak.get(pTag);
	}
	
	public void komentarioaGorde(String pTag, int pTagId) {
		tagIDak.put(pTag, pTagId);
	}

	public ArrayList<String> ikusitakoPelikulakLortu(int idUser) throws ErabiltzaileaEzDaExistitzenException {
		Erabiltzailea erab = this.getErabiltzailea(idUser);
		return erab.ikusitakoPelikulakLortu();
	}
	
	public static void main(String[] args) throws ErabiltzaileaEzDaExistitzenException, PelikulaEzDaExistitzenException, KargaMotaEzDaExistitzenException {

	}

}
