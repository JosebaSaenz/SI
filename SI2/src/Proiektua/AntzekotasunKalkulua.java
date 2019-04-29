package Proiektua;

public class AntzekotasunKalkulua {
	
	private static AntzekotasunKalkulua nAntzekotasunKalkulua;
	private AntzekotasunNeurria neurriMota;
	
	private AntzekotasunKalkulua() {
		neurriMota = new Kosinua();
	}
	
	public static synchronized AntzekotasunKalkulua getAntzekotasunKalkulua() {
		if (nAntzekotasunKalkulua == null){
			nAntzekotasunKalkulua = new AntzekotasunKalkulua();
		}
		return nAntzekotasunKalkulua;
	}
	
	public double antzekotasunaKalkulatu(Bektorea bek1, Bektorea bek2) {
		return neurriMota.antzekotasunaKalkulatu(bek1, bek2);
	}
	
	public void neurriaAldatu(AntzekotasunNeurria pNeurria) {
		neurriMota = pNeurria;
	}
	
}
