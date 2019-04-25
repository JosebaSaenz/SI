package Proiektua;

public class TfIdf implements ErabilgarritasunMetodoa {

	public TfIdf() {}
	
	public Bektorea[] erabilgarritasunMatrizeaBete(Bektorea[] mat) {
		Bektorea[] matBerria = new Bektorea[mat.length];
		int N = mat.length;
		for(int i=0; i<matBerria.length; i++) {
			matBerria[i] = new Bektorea();
			for(int j=0; j<mat[i].luzera(); j++) {
				double unekoTf = mat[i].getPosiziokoBalioa(j);
				int Nt = KomentarioenMatrizea.getKomentarioenMatrizea().zenbatPelikuletan(mat[i].getElementuarenIdErreala(j));
				double tfidf = unekoTf*Math.log10((double)N/Nt);
				matBerria[i].gehituElementua(mat[i].getElementuarenIdErreala(j), tfidf);
			}
		}
		bektoreUnitarioarekin(matBerria);
		return matBerria;
	}

	private void bektoreUnitarioarekin(Bektorea[] matErabilgarria) {
		for(int i=0; i<matErabilgarria.length; i++) {
			double bekModulua = matErabilgarria[i].bektorearenModulua();
			for(int j=0; j<matErabilgarria[i].luzera(); j++) {
				matErabilgarria[i].aldatuPosiziokoBalioa(j,(double)matErabilgarria[i].getPosiziokoBalioa(j)/bekModulua);
			}
		}
	}

}
