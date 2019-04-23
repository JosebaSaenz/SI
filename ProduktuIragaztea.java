package Proiektua;

import java.util.ArrayList;

import Salbuespenak.ErabiltzaileaEzDaExistitzenException;
import Salbuespenak.KargaMotaEzDaExistitzenException;
import Salbuespenak.PelikulaEzDaExistitzenException;

public class ProduktuIragaztea {
	
	public ProduktuIragaztea() {}
	
	public double balorazioEstimazioa(int idUser, int idMovie) {
		Tupla[] peliAntzekoenak = antzekoPeliLortu(idUser, idMovie);	// idMovie-aren antzekoen diren lehenengo 50 pelikula (edo gutxiago)
		double baturaBalAntzek = 0.0;
		double baturaAntzek = 0.0;
		for(int i=0; i<peliAntzekoenak.length; i++) {
			baturaBalAntzek = baturaBalAntzek + BalorazioenMatrizea.getBalorazioenMatrizea().getBalorazioa(idUser,peliAntzekoenak[i].getId())*Math.abs(peliAntzekoenak[i].getBalioa());
			baturaAntzek = baturaAntzek + Math.abs(peliAntzekoenak[i].getBalioa());
		}
		double emaitza = (double)baturaBalAntzek/baturaAntzek;
		return emaitza;
	}
	
	private Tupla[] antzekoPeliLortu(int idUser, int idMovie) {
		ArrayList<Integer> erabiltzaileakBaloratuDituenPelikulak = BalorazioenMatrizea.getBalorazioenMatrizea().erabiltzaileakBaloratuDituenPelikulenZerrenda(idUser);		// idUser baloratu dituen pelikulen id-ak
		Tupla[] antzekoProd = new Tupla[erabiltzaileakBaloratuDituenPelikulak.size()];
		Bektorea bek1 = BalorazioenMatrizea.getBalorazioenMatrizea().getPeliBalorazioNormalizatuak(idMovie);
		Bektorea bek2;
		for(int i=0;i<antzekoProd.length;i++) {
			int unekoPelikula = erabiltzaileakBaloratuDituenPelikulak.get(i);
			bek2 = BalorazioenMatrizea.getBalorazioenMatrizea().getPeliBalorazioNormalizatuak(unekoPelikula);				
			double antzekotasuna = AntzekotasunKalkulua.getAntzekotasunKalkulua().antzekotasunaKalkulatu(bek1, bek2);
			Tupla ta = new Tupla(unekoPelikula,antzekotasuna);
			antzekoProd[i] = ta;
		}
		
		Tupla[] antzekoenak;
		TuplaOrdenazioa.getTuplaAntzekOrdenazioa().handTxikOrdenatu(antzekoProd);
		if(antzekoProd.length <= 20) {
			antzekoenak = antzekoProd;
		}
		else {									
			antzekoenak = new Tupla[20];
			for(int i=0; i<antzekoenak.length; i++) {
				antzekoenak[i] = antzekoProd[i];
			}
		}
		return antzekoenak;
	}
	
	public static void main(String[] args) throws ErabiltzaileaEzDaExistitzenException, PelikulaEzDaExistitzenException, KargaMotaEzDaExistitzenException {
		GomendioSistema.getGomendioSistema().datuakKargatu();
		ProduktuIragaztea pi = new ProduktuIragaztea();
		Tupla[] zer = pi.antzekoPeliLortu(2048, 161);
		for(int i = 0; i<zer.length; i++) {
			System.out.println(zer[i].getId());
		}
			
	}
	
}
