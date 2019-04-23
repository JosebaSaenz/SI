package Proiektua;


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
		return this.notaAjustatu(emaitza);
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
	
}