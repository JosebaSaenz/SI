package Proiektua;

public class TuplaOrdenazioa {
	private static TuplaOrdenazioa nTuplaAntzekOrdenazioa;
	private OrdenazioAlgoritmoa algoritmoa;
	
	private TuplaOrdenazioa() {
		algoritmoa = new MergeSort();
	}
	
	public static synchronized TuplaOrdenazioa getTuplaAntzekOrdenazioa() {
		if (nTuplaAntzekOrdenazioa == null){
			nTuplaAntzekOrdenazioa = new TuplaOrdenazioa();
		}
		return nTuplaAntzekOrdenazioa;
	}
	
	public void handTxikOrdenatu(Tupla[] pZerrenda) {
		algoritmoa.handTxikOrdenatu(pZerrenda);
	}
	
	public void algoritmoaAldatu(OrdenazioAlgoritmoa pAlgoritmoa) {
		algoritmoa = pAlgoritmoa;
	}
}
