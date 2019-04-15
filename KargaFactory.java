package Proiektua;

import Salbuespenak.KargaMotaEzDaExistitzenException;

public class KargaFactory {

	private static KargaFactory nKargaFactory;
	
	private KargaFactory() {}
	
	public static synchronized KargaFactory getKargaFactory() {
		if(nKargaFactory == null) {
			nKargaFactory = new KargaFactory();
		}
		return nKargaFactory;
	}
	
	public DatuenKarga createKarga(String kargaMota) throws KargaMotaEzDaExistitzenException {
		DatuenKarga nireDatuenKarga = null;
		if(kargaMota.equals(".csv mota1")) {
			nireDatuenKarga = new CSVKargaMota1();
		}
		else if(kargaMota.equals(".csv mota2")) {
			nireDatuenKarga = new CSVKargaMota2();
		}
		else if(kargaMota.equals("datu basea")) {
			//nireDatuenKarga = new DatuBaseKarga();
		}
		else {
			throw new KargaMotaEzDaExistitzenException(kargaMota);
		}
		return nireDatuenKarga;
	}
	
}
