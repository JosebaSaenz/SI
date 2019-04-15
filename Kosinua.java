package Proiektua;

public class Kosinua implements AntzekotasunNeurria {
	
	public Kosinua() {}
	
	public double antzekotasunaKalkulatu(Bektorea bek1, Bektorea bek2) {
		double biderkaduraEskalarra = biderkaduraEskalarra(bek1,bek2);
		double moduluaBek1 = bek1.bektorearenModulua();
		double moduluaBek2 = bek2.bektorearenModulua();
		double emaitza = 0;
		if(biderkaduraEskalarra != 0) {
			emaitza = (double) biderkaduraEskalarra/(moduluaBek1*moduluaBek2);
		}
		return emaitza;
	}
	
	private double biderkaduraEskalarra(Bektorea bek1, Bektorea bek2) {		// bi bektoreen biderkadura eskalarra kalkulatu
		
		double emaitza = 0.0;
		for (int i=0; i<bek1.luzera(); i++) {
			int unekoElementua = bek1.getElementuarenIdErreala(i);
			if(bek2.bektoreanDago(unekoElementua)) {
				emaitza = emaitza + bek1.getBalioa(unekoElementua)*bek2.getBalioa(unekoElementua);
			}
		}
		return emaitza;
	}
	
}
