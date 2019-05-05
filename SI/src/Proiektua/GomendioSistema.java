package Proiektua;
import java.util.ArrayList;
import java.util.HashMap;

import Salbuespenak.ErabiltzaileaEzDaExistitzenException;
import Salbuespenak.KargaMotaEzDaExistitzenException;

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
	
	public void datuakKargatu(String pKargaMota) {
		try {
			DatuenKarga nireDatuenKarga = KargaFactory.getKargaFactory().createKarga(pKargaMota);
			nireDatuenKarga.datuakKargatu();
		} catch (KargaMotaEzDaExistitzenException e) {
			e.mezua(pKargaMota);
		}
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
	
	public Erabiltzailea getErabiltzailea(int idUser) throws ErabiltzaileaEzDaExistitzenException {
		Erabiltzailea erab = erabiltzaileak.get(idUser);
		if(erab == null) {
			throw new ErabiltzaileaEzDaExistitzenException();
		}
		return erab;
	}
	
	public void datuakEzabatu() {
		erabiltzaileak.clear();
	}
	
	public HashMap<Integer,Erabiltzailea> getErabiltzaileak() {
		return erabiltzaileak;
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

	public ArrayList<String> ikusitakoPelikulakLortu(int idUser) {
		ArrayList<String> emaitza = new ArrayList<String>();
		try {
			Erabiltzailea erab = this.getErabiltzailea(idUser);
			emaitza = erab.ikusitakoPelikulakLortu();
		} catch(ErabiltzaileaEzDaExistitzenException e) {
			e.mezua(idUser);
		}
		return emaitza;
	}

	public boolean pelikulaIkusiDu(int idUser, int idMovie) {
		boolean emaitza = false;
		try {
			emaitza = GomendioSistema.getGomendioSistema().getErabiltzailea(idUser).ikusiDu(idMovie);
		} catch (ErabiltzaileaEzDaExistitzenException e) {
			e.mezua(idUser);
		}
		return emaitza;
	}

	public int zenbatPelikulaIkusiDitu(int idUser) {
		int emaitza = 0;
		try {
			emaitza = GomendioSistema.getGomendioSistema().getErabiltzailea(idUser).ikusitakoPelikulaKop();
		} catch (ErabiltzaileaEzDaExistitzenException e) {
			e.mezua(idUser);
		}
		return emaitza;
	}

	public boolean gomendioaEginda(int idUser) {
		boolean emaitza = false;
		try {
			emaitza = GomendioSistema.getGomendioSistema().getErabiltzailea(idUser).gomendioaEginda();
		} catch (ErabiltzaileaEzDaExistitzenException e) {
			e.mezua(idUser);
		}
		return emaitza;
	}

	public void gomendioakGehitu(int idUser, ArrayList<String> gomendioak) {
		try {
			GomendioSistema.getGomendioSistema().getErabiltzailea(idUser).gomendioakGehitu(gomendioak);;
		} catch (ErabiltzaileaEzDaExistitzenException e) {
			e.mezua(idUser);
		}
	}

	public ArrayList<String> getGomendioak(int idUser) {
		ArrayList<String> emaitza = null;
		try {
			emaitza = GomendioSistema.getGomendioSistema().getErabiltzailea(idUser).getGomendioak();
		} catch (ErabiltzaileaEzDaExistitzenException e) {
			e.mezua(idUser);
		}
		return emaitza;
	}
	
	public void erreseteatu() {
		GomendioSistema.nGomendioSistema = null;
	}

}
