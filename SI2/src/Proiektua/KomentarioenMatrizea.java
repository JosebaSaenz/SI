package Proiektua;

import java.util.HashMap;

public class KomentarioenMatrizea {
	private static KomentarioenMatrizea nKomentarioenMatrizea;
	private Bektorea[] mat;
	private Bektorea[] matErabilgarria;
	private HashMap<Integer,Integer> komentarioaZenbatPelikuletan;
	private HashMap<Integer,Integer> pelikulaIdItzultzailea;
	
	private KomentarioenMatrizea() {
		pelikulaIdItzultzailea = new HashMap<Integer,Integer>();
		komentarioaZenbatPelikuletan = new HashMap<Integer,Integer>();
		mat = new Bektorea[PelikulaKatalogo.getPelikulaKatalogo().zenbatPelikula()];
		for(int i=0; i<mat.length; i++) {
			mat[i] = new Bektorea();
		}
		matrizeaBete();
		//matErabilgarria = ErabilgarritasunKalkulua.getErabilgarritasunKalkulua().erabilgarritasunMatrizeaBete(mat);
	}
	
	public static synchronized KomentarioenMatrizea getKomentarioenMatrizea() {
		if (nKomentarioenMatrizea == null) {
			nKomentarioenMatrizea = new KomentarioenMatrizea();
			nKomentarioenMatrizea.matErabilgarria = ErabilgarritasunKalkulua.getErabilgarritasunKalkulua().erabilgarritasunMatrizeaBete(nKomentarioenMatrizea.mat);
		}
		return nKomentarioenMatrizea;
	}
	
	private void matrizeaBete() {
		for(int i=0; i<PelikulaKatalogo.getPelikulaKatalogo().zenbatPelikula(); i++) {
			Pelikula unekoP = PelikulaKatalogo.getPelikulaKatalogo().getPosiziokoPelikula(i);
			pelikulaIdItzultzailea.put(unekoP.getPelikulaId(), i);
			for(int j=0; j<unekoP.zenbatKomentario(); j++) {
				Tag unekoK = unekoP.getKomentarioaPosizioz(j);
				mat[i].gehituElementua(unekoK.getTagId(), (double)unekoK.zenbatAldiz());
				if (komentarioaZenbatPelikuletan.containsKey(unekoK.getTagId())) {
					komentarioaZenbatPelikuletan.put(unekoK.getTagId(), komentarioaZenbatPelikuletan.get(unekoK.getTagId())+1);
				}
				else {
					komentarioaZenbatPelikuletan.put(unekoK.getTagId(), 1);
				}
			}
		}
	}

	public int zenbatPelikuletan(int pKomentarioa) {
		return komentarioaZenbatPelikuletan.get(pKomentarioa);
	}

	public Bektorea getPeliKomentarioak(int idMovie) {
		return mat[pelikulaIdItzultzailea.get(idMovie)];
	}
	
	public Bektorea getPeliKomentarioErabilgarriak(int idMovie) {
		return matErabilgarria[pelikulaIdItzultzailea.get(idMovie)];
	}

	public Bektorea[] getMatrizea() {
		return mat;
	}

}