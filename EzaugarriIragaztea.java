package Proiektua;

import java.util.ArrayList;

import Salbuespenak.ErabiltzaileaEzDaExistitzenException;
import Salbuespenak.KargaMotaEzDaExistitzenException;
import Salbuespenak.PelikulaEzDaExistitzenException;

public class EzaugarriIragaztea {

	public EzaugarriIragaztea() {}
	
	public double balorazioEstimazioa(int idUser, int idMovie) {
		Bektorea pertsonaEredua = new Bektorea();
		Bektorea idUserBalorazioak = BalorazioenMatrizea.getBalorazioenMatrizea().getErabBalorazioak(idUser);
		for(int i=0; i<idUserBalorazioak.luzera(); i++) {
			if(idUserBalorazioak.getPosiziokoBalioa(i) >= 3.5) {
				bektoreBatura(pertsonaEredua,KomentarioenMatrizea.getKomentarioenMatrizea().getPeliKomentarioErabilgarriak(idUserBalorazioak.getElementuarenIdErreala(i)));
			}
		}
		double emaitza = AntzekotasunKalkulua.getAntzekotasunKalkulua().antzekotasunaKalkulatu(pertsonaEredua, KomentarioenMatrizea.getKomentarioenMatrizea().getPeliKomentarioErabilgarriak(idMovie));
		return emaitza;
	}

	private void bektoreBatura(Bektorea pertsonaEredua, Bektorea produkEredua) {
		for(int i=0; i<produkEredua.luzera(); i++) {
			if(pertsonaEredua.bektoreanDago(produkEredua.getElementuarenIdErreala(i))) {
				pertsonaEredua.gehituBalioa(produkEredua.getElementuarenIdErreala(i),produkEredua.getPosiziokoBalioa(i));
			}
			else {
				pertsonaEredua.gehituElementua(produkEredua.getElementuarenIdErreala(i), produkEredua.getPosiziokoBalioa(i));
			}
		}
	}
	
	private double notaAjustatu(double nota) {
		return (nota+1)*2.5;
	}
	
	public Bektorea balorazioEstimazioak(int idUser){
		Bektorea balorazioak = new Bektorea();
		ArrayList<Integer> pelikulenIdak = new ArrayList<Integer>();
		pelikulenIdak = PelikulaKatalogo.getPelikulaKatalogo().idGuztiak();
		Erabiltzailea erab = null;
		try {
			erab = GomendioSistema.getGomendioSistema().getErabiltzailea(idUser);
		} catch (ErabiltzaileaEzDaExistitzenException e) {
			e.printStackTrace();
		}
		for (int i=0; i<pelikulenIdak.size();i++) {
			if (!erab.ikusiDu(pelikulenIdak.get(i))) {
				balorazioak.gehituElementua(pelikulenIdak.get(i), this.notaAjustatu(this.balorazioEstimazioa(idUser, pelikulenIdak.get(i))));
			}
		}
		return balorazioak;
	}


	public static void main(String[] args) throws ErabiltzaileaEzDaExistitzenException, PelikulaEzDaExistitzenException, KargaMotaEzDaExistitzenException {
		GomendioSistema.getGomendioSistema().datuakKargatu();
		EzaugarriIragaztea ei = new EzaugarriIragaztea();
		System.out.println(ei.balorazioEstimazioa(1, 1));
	}
	
}