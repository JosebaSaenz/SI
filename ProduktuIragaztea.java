package Proiektua;

import java.util.ArrayList;

import Salbuespenak.ErabiltzaileaEzDaExistitzenException;
import Salbuespenak.KargaMotaEzDaExistitzenException;
import Salbuespenak.PelikulaEzDaExistitzenException;

public class ProduktuIragaztea {
	
	public ProduktuIragaztea() {}
	
	public double balorazioEstimazioa(int idUser, int idMovie) {
		TuplaAntzekotasuna[] peliAntzekoenak = antzekoPeliLortu(idUser, idMovie);	// idMovie-aren antzekoen diren lehenengo 50 pelikula (edo gutxiago)
		double baturaBalAntzek = 0.0;
		double baturaAntzek = 0.0;
		for(int i=0; i<peliAntzekoenak.length; i++) {
			baturaBalAntzek = baturaBalAntzek + BalorazioenMatrizea.getBalorazioenMatrizea().getBalorazioa(idUser,peliAntzekoenak[i].getId())*Math.abs(peliAntzekoenak[i].getAntzekotasuna());
			baturaAntzek = baturaAntzek + Math.abs(peliAntzekoenak[i].getAntzekotasuna());
		}
		double emaitza = (double)baturaBalAntzek/baturaAntzek;
		return emaitza;
	}
	
	private TuplaAntzekotasuna[] antzekoPeliLortu(int idUser, int idMovie) {
		ArrayList<Integer> erabiltzaileakBaloratuDituenPelikulak = BalorazioenMatrizea.getBalorazioenMatrizea().erabiltzaileakBaloratuDituenPelikulenZerrenda(idUser);		// idMovie baloratu duten erabiltzaileen id-a (idUser-a izan ezik)
		TuplaAntzekotasuna[] antzekoProd = new TuplaAntzekotasuna[erabiltzaileakBaloratuDituenPelikulak.size()];
		Bektorea bek1 = BalorazioenMatrizea.getBalorazioenMatrizea().getPeliBalorazioNormalizatuak(idMovie);
		Bektorea bek2;
		for(int i=0;i<antzekoProd.length;i++) {
			int unekoPelikula = erabiltzaileakBaloratuDituenPelikulak.get(i);
			bek2 = BalorazioenMatrizea.getBalorazioenMatrizea().getPeliBalorazioNormalizatuak(unekoPelikula);				
			double antzekotasuna = AntzekotasunKalkulua.getAntzekotasunKalkulua().antzekotasunaKalkulatu(bek1, bek2);
			TuplaAntzekotasuna ta = new TuplaAntzekotasuna(unekoPelikula,antzekotasuna);
			antzekoProd[i] = ta;
		}
		
		TuplaAntzekotasuna[] antzekoenak;
		TuplaAntzekOrdenazioa.getTuplaAntzekOrdenazioa().handTxikOrdenatu(antzekoProd);
		if(antzekoProd.length <= 20) {
			antzekoenak = antzekoProd;
		}
		else {									
			antzekoenak = new TuplaAntzekotasuna[20];
			for(int i=0; i<antzekoenak.length; i++) {
				antzekoenak[i] = antzekoProd[i];
			}
		}
		return antzekoenak;
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
				balorazioak.gehituElementua(pelikulenIdak.get(i),this.balorazioEstimazioa(idUser, pelikulenIdak.get(i)));
			}
		}
		return balorazioak;
	}
	
	public static void main(String[] args) throws ErabiltzaileaEzDaExistitzenException, PelikulaEzDaExistitzenException, KargaMotaEzDaExistitzenException {
		GomendioSistema.getGomendioSistema().datuakKargatu();
		ProduktuIragaztea pi = new ProduktuIragaztea();
		TuplaAntzekotasuna[] zer = pi.antzekoPeliLortu(2048, 161);
		for(int i = 0; i<zer.length; i++) {
			System.out.println(zer[i].getId());
		}
			
	}
	
}
