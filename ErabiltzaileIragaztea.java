package Proiektua;

import java.util.ArrayList;

import Interfaze.Lehio_Nagusia;
import Salbuespenak.ErabiltzaileaEzDaExistitzenException;
import Salbuespenak.KargaMotaEzDaExistitzenException;
import Salbuespenak.PelikulaEzDaExistitzenException;

public class ErabiltzaileIragaztea {
	
	public ErabiltzaileIragaztea() {}
	
	public double balorazioEstimazioa(int idUser, int idMovie) {
		TuplaAntzekotasuna[] erabAntzekoenak = antzekoErabLortu(idUser, idMovie);	// idUser-aren antzekoen diren lehenengo 30 erabiltzaileak (edo gutxiago)
		double baturaBalAntzek = 0.0;	// formularen goiko batukaria
		double baturaAntzek = 0.0;		// formularen beheko batukaria
		for(int i=0; i<erabAntzekoenak.length; i++) {
			baturaBalAntzek = baturaBalAntzek + BalorazioenMatrizea.getBalorazioenMatrizea().getBalorazioa(erabAntzekoenak[i].getId(),idMovie)*Math.abs(erabAntzekoenak[i].getAntzekotasuna());
			baturaAntzek = baturaAntzek + Math.abs(erabAntzekoenak[i].getAntzekotasuna());
		}
		double emaitza = (double)baturaBalAntzek/baturaAntzek;		// formula: (goiko batukaria)/(beheko batukaria)
		return emaitza;
	}

	private TuplaAntzekotasuna[] antzekoErabLortu(int idUser, int idMovie) {
		
		ArrayList<Integer> pelikulaBaloratuDutenErabiltzaileak = BalorazioenMatrizea.getBalorazioenMatrizea().pelikulaBaloratuDutenErabiltzaileenZerrenda(idMovie);		// idMovie baloratu duten erabiltzaileen id-a (idUser-a izan ezik)
		TuplaAntzekotasuna[] antzekoErab = new TuplaAntzekotasuna[pelikulaBaloratuDutenErabiltzaileak.size()];
		Bektorea bek1 = BalorazioenMatrizea.getBalorazioenMatrizea().getErabBalorazioNormalizatuak(idUser);			// idUser-aren balorazio guztiak lortu
		for(int i=0; i<antzekoErab.length; i++) {
			int unekoErab = pelikulaBaloratuDutenErabiltzaileak.get(i);
			Bektorea bek2 = BalorazioenMatrizea.getBalorazioenMatrizea().getErabBalorazioNormalizatuak(unekoErab);				// uneko erabiltzailearen balorazio guztiak lortu
			double antzekotasuna = AntzekotasunKalkulua.getAntzekotasunKalkulua().antzekotasunaKalkulatu(bek1, bek2);		// idUser eta unekoaren antzekotasuna kalkulatu
			antzekoErab[i] = new TuplaAntzekotasuna(unekoErab, antzekotasuna);
		}
		TuplaAntzekOrdenazioa.getTuplaAntzekOrdenazioa().handTxikOrdenatu(antzekoErab);	// merge sort egiten da antzekotasun handienetik txikienera ordenatzeko
		TuplaAntzekotasuna[] emaitza = null;
		if(antzekoErab.length <= 30) {		// antzeko erabiltzaileen zerrendaren luzera 30 edo txikiagoa bada, hori da emaitza
			emaitza = antzekoErab;
		}
		else {											// antzeko erabiltzaileen zerrendaren luzera 30 baino handiago bada, soilik lehenengo 30 hartuko dira kontuan
			emaitza = new TuplaAntzekotasuna[30];
			for(int i=0; i<emaitza.length; i++) {
				emaitza[i] = antzekoErab[i];
			}
		}
		return emaitza;
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
				balorazioak.gehituElementua(pelikulenIdak.get(i), this.balorazioEstimazioa(idUser, pelikulenIdak.get(i)));
			}
		}
		return balorazioak;
	}
	
	public static void main(String[] args) throws ErabiltzaileaEzDaExistitzenException, PelikulaEzDaExistitzenException, KargaMotaEzDaExistitzenException {
		GomendioSistema.getGomendioSistema().datuakKargatu();
		ErabiltzaileIragaztea ei = new ErabiltzaileIragaztea();
		System.out.println(ei.balorazioEstimazioa(1, 1));
	}
	
}
