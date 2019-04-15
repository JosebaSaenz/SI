package Proiektua;

import java.util.ArrayList;

import Salbuespenak.ErabiltzaileaEzDaExistitzenException;
import Salbuespenak.PelikulaEzDaExistitzenException;

public class IragazteSistema {
	private ErabiltzaileIragaztea erab;
	private ProduktuIragaztea produk;
	private EzaugarriIragaztea ezau;
	private static IragazteSistema nIragazteSistema = null;
	
	private IragazteSistema() {
		erab = new ErabiltzaileIragaztea();
		produk = new ProduktuIragaztea();
		ezau = new EzaugarriIragaztea();
	}
	
	public static synchronized IragazteSistema getIragazteSistema() {
		if(nIragazteSistema==null) {
			nIragazteSistema = new IragazteSistema();
		}
		return nIragazteSistema;
	}
	
	public ArrayList<Integer> gomendatu(int idUser){  
		ArrayList<Integer> gomendioak = new ArrayList<Integer>();
		return gomendioak;
	}
	/*
	public double erabiltzaileaBalorazioaEstimazioa(int idUser, int idMovie) throws ErabiltzaileaEzDaExistitzenException, PelikulaEzDaExistitzenException {
		if (!GomendioSistema.getGomendioSistema().erregistratutaDago(idUser)) {
			throw new ErabiltzaileaEzDaExistitzenException(idUser);
		}
		else if(!PelikulaKatalogo.getPelikulaKatalogo().erregistratutaDago(idMovie)) {
			throw new PelikulaEzDaExistitzenException(idMovie);
		}
		return erab.balorazioEstimazioa(idUser, idMovie);
	}
	
	public double produktuaBalorazioaEstimazioa(int idUser, int idMovie) throws ErabiltzaileaEzDaExistitzenException, PelikulaEzDaExistitzenException {
		if (!GomendioSistema.getGomendioSistema().erregistratutaDago(idUser)) {
			throw new ErabiltzaileaEzDaExistitzenException(idUser);
		}
		else if(!PelikulaKatalogo.getPelikulaKatalogo().erregistratutaDago(idMovie)) {
			throw new PelikulaEzDaExistitzenException(idMovie);
		}
		return produk.balorazioEstimazioa(idUser,idMovie);
	}
	
	public double ezaugarriaBalorazioaEstimazioa(int idUser, int idMovie) throws ErabiltzaileaEzDaExistitzenException, PelikulaEzDaExistitzenException {
		if (!GomendioSistema.getGomendioSistema().erregistratutaDago(idUser)) {
			throw new ErabiltzaileaEzDaExistitzenException(idUser);
		}
		else if(!PelikulaKatalogo.getPelikulaKatalogo().erregistratutaDago(idMovie)) {
			throw new PelikulaEzDaExistitzenException(idMovie);
		}
		return ezau.balorazioEstimazioa(idUser, idMovie);
	}
	*/
	public Bektorea erabiltzaileBalorazioEstimazioak(int idUser) {
		return erab.balorazioEstimazioak(idUser);
	}
	
	public Bektorea produktuBalorazioEstimazioak(int idUser) {
		return produk.balorazioEstimazioak(idUser);
	}
	
	public Bektorea ezaugarriBalorazioEstimazioak(int idUser) {
		return ezau.balorazioEstimazioak(idUser);
	}
}
