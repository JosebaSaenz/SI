package Proiektua;

import java.util.ArrayList;
import java.util.HashMap;

import Salbuespenak.ErabiltzaileaEzDaExistitzenException;
import Salbuespenak.KargaMotaEzDaExistitzenException;
import Salbuespenak.PelikulaEzDaExistitzenException;

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
	
	public static void main(String[] args) throws ErabiltzaileaEzDaExistitzenException, PelikulaEzDaExistitzenException, KargaMotaEzDaExistitzenException {
		GomendioSistema.getGomendioSistema().datuakKargatu();
		System.out.println(KomentarioenMatrizea.getKomentarioenMatrizea().getPeliKomentarioak(13).luzera());
	}

}

/*public class KomentarioenMatrizea {
	private static KomentarioenMatrizea nKomentarioenMatrizea;
	private BektoreTag[] mat;
	private BektoreTag[] matErabilgarria;
	private HashMap<String,Integer> komentarioaZenbatPelikuletan;
	private HashMap<Integer,Integer> pelikulaIdItzultzailea;
	
	private KomentarioenMatrizea() {
		pelikulaIdItzultzailea = new HashMap<Integer,Integer>();
		komentarioaZenbatPelikuletan = new HashMap<String,Integer>();
		mat = new BektoreTag[PelikulaKatalogo.getPelikulaKatalogo().zenbatPelikula()];
		for(int i=0; i<mat.length; i++) {
			mat[i] = new BektoreTag();
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
				mat[i].gehituElementua(unekoK.getKomentarioa(), (double)unekoK.zenbatAldiz());
				if (komentarioaZenbatPelikuletan.containsKey(unekoK.getKomentarioa())) {
					komentarioaZenbatPelikuletan.put(unekoK.getKomentarioa(), komentarioaZenbatPelikuletan.get(unekoK.getKomentarioa())+1);
				}
				else {
					komentarioaZenbatPelikuletan.put(unekoK.getKomentarioa(), 1);
				}
			}
		}
	}

	public int zenbatPelikuletan(String pKomentarioa) {
		return komentarioaZenbatPelikuletan.get(pKomentarioa);
	}

	public BektoreTag getPeliKomentarioak(int idMovie) {
		return mat[pelikulaIdItzultzailea.get(idMovie)];
	}
	
	public BektoreTag getPeliKomentarioErabilgarriak(int idMovie) {
		return matErabilgarria[pelikulaIdItzultzailea.get(idMovie)];
	}

	public BektoreTag[] getMatrizea() {
		return mat;
	}
	
	public static void main(String[] args) throws ErabiltzaileaEzDaExistitzenException, PelikulaEzDaExistitzenException, KargaMotaEzDaExistitzenException {
		GomendioSistema.getGomendioSistema().datuakKargatu();
		System.out.println(KomentarioenMatrizea.getKomentarioenMatrizea().getPeliKomentarioak(13).luzera());
	}

}*/
