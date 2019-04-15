package Proiektua;

public class MergeSort implements OrdenazioAlgoritmoa {

	public MergeSort() {}

	public void handTxikOrdenatu(TuplaAntzekotasuna[] pZerrenda) {
		mergeSort(pZerrenda, 0, pZerrenda.length-1);
	}
	
	private void mergeSort(TuplaAntzekotasuna[] pZerrenda, int pHasiera, int pBukaera) {
		if(pHasiera < pBukaera) {
			mergeSort(pZerrenda, pHasiera, (pHasiera + pBukaera) / 2);
			mergeSort(pZerrenda, ((pHasiera + pBukaera) / 2) + 1, pBukaera);
			bateratze(pZerrenda, pHasiera, (pHasiera + pBukaera) / 2, pBukaera);
		}
	}
	
	private void bateratze(TuplaAntzekotasuna[] pZerrenda, int i, int erdikoa, int f) {
		TuplaAntzekotasuna[] bateratua = new TuplaAntzekotasuna[f-i+1];
		int ezker = i;
		int eskuin = erdikoa + 1;
		int k = 0; 	// bateratua taula betetzeko indizea
		while(ezker <= erdikoa && eskuin <= f) {
			if(pZerrenda[ezker].getAntzekotasuna() >= pZerrenda[eskuin].getAntzekotasuna()) {
				bateratua[k] = pZerrenda[ezker];
				k ++;
				ezker ++;
			}
			else {
				bateratua[k] = pZerrenda[eskuin];
				k ++;
				eskuin ++;
			}
		}
		if(ezker > erdikoa) {
			while(eskuin <= f) {
				bateratua[k] = pZerrenda[eskuin];
				k ++;
				eskuin ++;
			}
		}
		else {
			while(ezker <= erdikoa) {
				bateratua[k] = pZerrenda[ezker];
				k ++;
				ezker ++;
			}
		}
		for(int j = i; j <= f; j++) {
			pZerrenda[j] = bateratua[j-i];
		}
	}
	
	
}
