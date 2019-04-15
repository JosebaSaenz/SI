package Proiektua;

import java.util.ArrayList;
import java.util.HashMap;

import Salbuespenak.ErabiltzaileaEzDaExistitzenException;
import Salbuespenak.KargaMotaEzDaExistitzenException;
import Salbuespenak.PelikulaEzDaExistitzenException;

public class BalorazioenMatrizea {
	
	private static BalorazioenMatrizea nBalorazioenMatrizea;
	private Bektorea[] matErabPeli;
	private Bektorea[] matPeliErab;
	private HashMap<Integer,Integer> peliItzultzailea;
	private HashMap<Integer,Integer> erabItzultzailea;
	private Bektorea[] matErabPeliNormalizatua;
	private Bektorea[] matPeliErabNormalizatua;
	
	/*  
	 	La clave es el id real de la pelicula y el valor es nuestro id del 1 hasta el kopuru maximo.
	 	Es necesario para que cuando en un metodo te pasen de parametro el idMovie real podamos hacer el cambio a nuestro id y podamos buscar la pelicula en esa matriz.
	 	Lo rellenas mientras vas creando la matriz.
	*/
	
	private BalorazioenMatrizea() {		
		
		this.peliItzultzailea= new HashMap<Integer,Integer>();
		this.erabItzultzailea= new HashMap<Integer,Integer>();
		this.matErabPeli = new Bektorea[GomendioSistema.getGomendioSistema().zenbatErabiltzaile()];
		this.matPeliErab = new Bektorea[PelikulaKatalogo.getPelikulaKatalogo().zenbatPelikula()];
		this.matErabPeliNormalizatua = new Bektorea[GomendioSistema.getGomendioSistema().zenbatErabiltzaile()];
		for(int i=0; i<matErabPeli.length; i++) {
			matErabPeli[i] = new Bektorea();
		}
		for(int i=0; i<matPeliErab.length; i++) {
			matPeliErab[i] = new Bektorea();
		}
		this.matrizeakBete();
		this.matErabPeliNormalizatua = NormalizazioKalkulua.getNormalizazioKalkulua().matrizeaNormalizatu(matErabPeli);
		this.matPeliErabNormalizatua = NormalizazioKalkulua.getNormalizazioKalkulua().matrizeaNormalizatu(matPeliErab);
	}

	public static synchronized BalorazioenMatrizea getBalorazioenMatrizea() {
		if(nBalorazioenMatrizea == null) {
			nBalorazioenMatrizea = new BalorazioenMatrizea();
		}
		return nBalorazioenMatrizea;
	}
	
	private void matrizeakBete() {
		int kontErab = 0;
		int zenbatPelikula = PelikulaKatalogo.getPelikulaKatalogo().zenbatPelikula();
		for (int i=0; i<zenbatPelikula; i++) {		// pelikula bakoitzeko
			Pelikula oraingoPelikula=PelikulaKatalogo.getPelikulaKatalogo().getPosiziokoPelikula(i);
			peliItzultzailea.put(oraingoPelikula.getPelikulaId(), i);
			for(double nota=0; nota<=5;nota=nota+0.5) {
				Balorazioa oraingoBalorazioa = oraingoPelikula.getBalorazioa(nota);
				int balorazioTamaina = oraingoBalorazioa.zenbatErabiltzailea();
				for(int u=0;u<balorazioTamaina;u++) {
					int oraingoErabiltzailea = oraingoBalorazioa.getErabiltzaileId(u);
					if(!erabItzultzailea.containsKey(oraingoErabiltzailea)) {
						this.erabItzultzailea.put(oraingoErabiltzailea, kontErab);
						kontErab ++;
					}
					this.matErabPeli[erabItzultzailea.get(oraingoErabiltzailea)].gehituElementua(oraingoPelikula.getPelikulaId(), nota);
					this.matPeliErab[i].gehituElementua(oraingoBalorazioa.getErabiltzaileId(u), nota);
				}
			}
		}
	}

	private int pelikularenPosizioa(int pMovId) {
		return this.peliItzultzailea.get(pMovId);		
	}
	
	private int erabiltzailearenPosizioa(int pUserId) {
		return this.erabItzultzailea.get(pUserId);		
	}
	
	public ArrayList<Integer> pelikulaBaloratuDutenErabiltzaileenZerrenda(int idMovie) {
		ArrayList<Integer> idLista = new ArrayList<Integer>();
		int pelikulaPos = this.pelikularenPosizioa(idMovie);
		Bektorea bektorea = this.matPeliErab[pelikulaPos];
		int luzera = bektorea.luzera();
		for (int i=0; i<luzera;i++) {
			idLista.add(bektorea.getElementuarenIdErreala(i));
		}
		return idLista;
	}
	
	public ArrayList<Integer> erabiltzaileakBaloratuDituenPelikulenZerrenda(int idUser) {
		ArrayList<Integer> idLista = new ArrayList<Integer>();
		int erabPos = this.erabiltzailearenPosizioa(idUser);
		Bektorea bektorea = this.matErabPeli[erabPos];
		int luzera = bektorea.luzera();
		for (int i=0; i<luzera;i++) {
			idLista.add(bektorea.getElementuarenIdErreala(i));
		}
		return idLista;
	}

	public Bektorea getErabBalorazioak(int idUser) {
		return matErabPeli[erabItzultzailea.get(idUser)];
	}
	
	public Bektorea getErabBalorazioNormalizatuak(int idUser) {
		return matErabPeliNormalizatua[erabItzultzailea.get(idUser)];
	}
	
	public Bektorea getPeliBalorazioak(int idMovie) {
		return matPeliErab[peliItzultzailea.get(idMovie)];
	}
	
	public Bektorea getPeliBalorazioNormalizatuak(int idMovie) {
		return matPeliErabNormalizatua[peliItzultzailea.get(idMovie)];
	}

	public double getBalorazioa(int idUser, int idMovie) {		
		return matErabPeli[erabItzultzailea.get(idUser)].getBalioa(idMovie);
	}
	
	public static void main(String[] args) throws ErabiltzaileaEzDaExistitzenException, PelikulaEzDaExistitzenException, KargaMotaEzDaExistitzenException {
		GomendioSistema.getGomendioSistema().datuakKargatu();
		Bektorea bek = BalorazioenMatrizea.getBalorazioenMatrizea().getErabBalorazioNormalizatuak(17);
		for(int i=0; i<bek.luzera(); i++) {
			System.out.println(bek.getPosiziokoBalioa(i));
		}
	}
	
}
