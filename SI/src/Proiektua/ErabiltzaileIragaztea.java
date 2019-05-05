package Proiektua;


import java.util.ArrayList;

public class ErabiltzaileIragaztea {
	
	public ErabiltzaileIragaztea() {}
	
	public double balorazioEstimazioa(int idUser, int idMovie) {
		Tupla[] erabAntzekoenak = antzekoErabLortu(idUser, idMovie);	// idUser-aren antzekoen diren lehenengo 30 erabiltzaileak (edo gutxiago)
		double baturaBalAntzek = 0.0;	// formularen goiko batukaria
		double baturaAntzek = 0.0;		// formularen beheko batukaria
		for(int i=0; i<erabAntzekoenak.length; i++) {
			baturaBalAntzek = baturaBalAntzek + BalorazioenMatrizea.getBalorazioenMatrizea().getBalorazioa(erabAntzekoenak[i].getId(),idMovie)*Math.abs(erabAntzekoenak[i].getBalioa());
			baturaAntzek = baturaAntzek + Math.abs(erabAntzekoenak[i].getBalioa());
		}
		double emaitza = (double)baturaBalAntzek/baturaAntzek;		// formula: (goiko batukaria)/(beheko batukaria)
		return emaitza;
	}

	private Tupla[] antzekoErabLortu(int idUser, int idMovie) {
		
		ArrayList<Integer> pelikulaBaloratuDutenErabiltzaileak = BalorazioenMatrizea.getBalorazioenMatrizea().pelikulaBaloratuDutenErabiltzaileenZerrenda(idMovie);		// idMovie baloratu duten erabiltzaileen id-a (idUser-a izan ezik)
		Tupla[] antzekoErab = new Tupla[pelikulaBaloratuDutenErabiltzaileak.size()];
		Bektorea bek1 = BalorazioenMatrizea.getBalorazioenMatrizea().getErabBalorazioNormalizatuak(idUser);			// idUser-aren balorazio guztiak lortu
		for(int i=0; i<antzekoErab.length; i++) {
			int unekoErab = pelikulaBaloratuDutenErabiltzaileak.get(i);
			Bektorea bek2 = BalorazioenMatrizea.getBalorazioenMatrizea().getErabBalorazioNormalizatuak(unekoErab);				// uneko erabiltzailearen balorazio guztiak lortu
			double antzekotasuna = AntzekotasunKalkulua.getAntzekotasunKalkulua().antzekotasunaKalkulatu(bek1, bek2);		// idUser eta unekoaren antzekotasuna kalkulatu
			antzekoErab[i] = new Tupla(unekoErab, antzekotasuna);
		}
		TuplaOrdenazioa.getTuplaAntzekOrdenazioa().handTxikOrdenatu(antzekoErab);	// merge sort egiten da antzekotasun handienetik txikienera ordenatzeko
		Tupla[] emaitza = null;
		if(antzekoErab.length <= 30) {					// antzeko erabiltzaileen zerrendaren luzera 30 edo txikiagoa bada, hori da emaitza
			emaitza = antzekoErab;
		}
		else {											// antzeko erabiltzaileen zerrendaren luzera 30 baino handiago bada, soilik lehenengo 30 hartuko dira kontuan
			emaitza = new Tupla[30];
			for(int i=0; i<emaitza.length; i++) {
				emaitza[i] = antzekoErab[i];
			}
		}
		return emaitza;
	}
	
}
