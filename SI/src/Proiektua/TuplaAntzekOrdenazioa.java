package Proiektua;

public class TuplaAntzekOrdenazioa {
	private static TuplaAntzekOrdenazioa nTuplaAntzekOrdenazioa;
	private OrdenazioAlgoritmoa algoritmoa;
	
	private TuplaAntzekOrdenazioa() {
		algoritmoa = new MergeSort();
	}
	
	public static synchronized TuplaAntzekOrdenazioa getTuplaAntzekOrdenazioa() {
		if (nTuplaAntzekOrdenazioa == null){
			nTuplaAntzekOrdenazioa = new TuplaAntzekOrdenazioa();
		}
		return nTuplaAntzekOrdenazioa;
	}
	
	public void handTxikOrdenatu(TuplaAntzekotasuna[] pZerrenda) {
		algoritmoa.handTxikOrdenatu(pZerrenda);
	}
	
	public void algoritmoaAldatu(OrdenazioAlgoritmoa pAlgoritmoa) {
		algoritmoa = pAlgoritmoa;
	}
}
