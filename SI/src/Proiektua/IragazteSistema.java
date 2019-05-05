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
	
	public ArrayList<String> gomendatu(int idUser) {  
		ArrayList<String> gomendioak = new ArrayList<String>();
		if (!GomendioSistema.getGomendioSistema().gomendioaEginda(idUser)) {
			Tupla[] balorazioak = this.estimazioak(idUser);
			TuplaOrdenazioa.getTuplaAntzekOrdenazioa().handTxikOrdenatu(balorazioak);
			Tupla[] balorazioFinala;
			if(balorazioak.length > 10) {
				balorazioFinala = new Tupla[10];
				for (int i=0;i<10;i++) {
					balorazioFinala[i]= balorazioak[i];
				}
			}
			else {
				balorazioFinala = balorazioak;
			}
			gomendioak = PelikulaKatalogo.getPelikulaKatalogo().tuplatikIzenakLortu(balorazioFinala);
			GomendioSistema.getGomendioSistema().gomendioakGehitu(idUser,gomendioak);
		}
		else {
			gomendioak = GomendioSistema.getGomendioSistema().getGomendioak(idUser);
		}	
		return gomendioak;
	}
	
	public double erabiltzaileaBalorazioaEstimazioa(int idUser, int idMovie) throws ErabiltzaileaEzDaExistitzenException, PelikulaEzDaExistitzenException {
		if (!GomendioSistema.getGomendioSistema().erregistratutaDago(idUser)) {
			throw new ErabiltzaileaEzDaExistitzenException();
		}
		else if(!PelikulaKatalogo.getPelikulaKatalogo().erregistratutaDago(idMovie)) {
			throw new PelikulaEzDaExistitzenException();
		}
		return erab.balorazioEstimazioa(idUser, idMovie);
	}
	
	public double produktuaBalorazioaEstimazioa(int idUser, int idMovie) throws ErabiltzaileaEzDaExistitzenException, PelikulaEzDaExistitzenException {
		if (!GomendioSistema.getGomendioSistema().erregistratutaDago(idUser)) {
			throw new ErabiltzaileaEzDaExistitzenException();
		}
		else if(!PelikulaKatalogo.getPelikulaKatalogo().erregistratutaDago(idMovie)) {
			throw new PelikulaEzDaExistitzenException();
		}
		return produk.balorazioEstimazioa(idUser,idMovie);
	}
	
	public double ezaugarriaBalorazioaEstimazioa(int idUser, int idMovie) throws ErabiltzaileaEzDaExistitzenException, PelikulaEzDaExistitzenException {
		if (!GomendioSistema.getGomendioSistema().erregistratutaDago(idUser)) {
			throw new ErabiltzaileaEzDaExistitzenException();
		}
		else if(!PelikulaKatalogo.getPelikulaKatalogo().erregistratutaDago(idMovie)) {
			throw new PelikulaEzDaExistitzenException();
		}
		return ezau.balorazioEstimazioa(idUser, idMovie);
	}
	
	private Tupla[] estimazioak(int idUser) {
		ArrayList<Integer> pelikulenIdak = new ArrayList<Integer>();
		pelikulenIdak = PelikulaKatalogo.getPelikulaKatalogo().idGuztiak();
		Tupla[] balorazioak = new Tupla[pelikulenIdak.size()-GomendioSistema.getGomendioSistema().zenbatPelikulaIkusiDitu(idUser)];
		int kont = 0;
		for (int i=0; i<pelikulenIdak.size();i++) {
			if (!GomendioSistema.getGomendioSistema().pelikulaIkusiDu(idUser, pelikulenIdak.get(i))) {
				int idMovie = pelikulenIdak.get(i);
				double notaerab = erab.balorazioEstimazioa(idUser, idMovie);
				double notaezaug = ezau.balorazioEstimazioa(idUser, idMovie);
				double notaproduk = produk.balorazioEstimazioa(idUser, idMovie);
				double batazBeste= this.notaMediaLortu(notaerab, notaezaug, notaproduk);
				balorazioak[kont] = new Tupla(idMovie,batazBeste);
				kont ++;
			}
		}
		return balorazioak;
	}
	
	private double notaMediaLortu(double erab, double ezaug, double produk) {
		return (0.25*erab)+(ezaug*0.5)+(produk*0.25);
	}
	
}