package Proiektua;

public class MergeSort implements OrdenazioAlgoritmoa {

	public MergeSort() {}

	public void handTxikOrdenatu(Tupla[] pZerrenda) {
		mergeSort(pZerrenda, 0, pZerrenda.length-1);
	}
	
	private void mergeSort(Tupla[] pZerrenda, int pHasiera, int pBukaera) {
		if(pHasiera < pBukaera) {
			mergeSort(pZerrenda, pHasiera, (pHasiera + pBukaera) / 2);
			mergeSort(pZerrenda, ((pHasiera + pBukaera) / 2) + 1, pBukaera);
			bateratze(pZerrenda, pHasiera, (pHasiera + pBukaera) / 2, pBukaera);
		}
	}
	
	private void bateratze(Tupla[] pZerrenda, int i, int erdikoa, int f) {
		Tupla[] bateratua = new Tupla[f-i+1];
		int ezker = i;
		int eskuin = erdikoa + 1;
		int k = 0; 	// bateratua taula betetzeko indizea
		while(ezker <= erdikoa && eskuin <= f) {
			if(pZerrenda[ezker].getBalioa() >= pZerrenda[eskuin].getBalioa()) {
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
