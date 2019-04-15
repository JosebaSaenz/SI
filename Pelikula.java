package Proiektua;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.String;

public class Pelikula {
	
	//Atributuak
	private Balorazioa[] balorazioak;
	private ArrayList<Tag> komentarioak;
	private int idMovie;
	private String titulua;
	private ArrayList<String> generoa;
	
	//Metodoak
	public Pelikula(int id, String titulua, ArrayList<String> generoa) {
		this.idMovie = id;
		this.titulua = titulua;
		this.generoa = generoa;
		balorazioak = new Balorazioa[11];
		double bal = 0.0;
		for(int i=0; i<balorazioak.length; i++) {
			balorazioak[i] = new Balorazioa(idMovie,bal);
			bal = bal + 0.5;
		}
		komentarioak = new ArrayList<Tag>();
	}
	
	public Pelikula(int id, String titulua) {
		this.idMovie = id;
		this.titulua = titulua;
		balorazioak = new Balorazioa[11];
		double bal = 0.0;
		for(int i=0; i<balorazioak.length; i++) {
			balorazioak[i] = new Balorazioa(idMovie,bal);
			bal = bal + 0.5;
		}
		komentarioak = new ArrayList<Tag>();
	}
	
	public void balorazioaGehitu(int idUser, double rating){
		balorazioak[(int) (rating*2)].gehituErabiltzailea(idUser);
	}
	
	public void komentarioaGehitu(int idUser, String k, int idTag){
		Tag komentarioa = bilatuKomentarioa(k);
		if(komentarioa == null) {
			komentarioa = new Tag(idMovie, k, idTag);
			this.komentarioak.add(komentarioa);
		}
		else {
			komentarioa.kopuruaInkrementatu();
		}
		komentarioa.gehituErabiltzailea(idUser);
	}
	
	public void komentarioaErabiltzaileaGabeGehitu(String tag, int idTag) {
		Tag komentarioa = bilatuKomentarioa(tag);
		if(komentarioa == null) {
			komentarioa = new Tag(idMovie, tag, idTag);
			this.komentarioak.add(komentarioa);
		}
		else {
			komentarioa.kopuruaInkrementatu();
		}
	}

	public Tag bilatuKomentarioa(String k) {
		Tag komentarioa = null;
		Iterator<Tag> itr = komentarioak.iterator();
		boolean topatuta = false;
		while(itr.hasNext() && !topatuta) {
			Tag lag = itr.next();
			if (lag.berdinaDa(k)) {
				komentarioa = lag;
				topatuta = true;
			}
		}
		return komentarioa;
	}
	
	public boolean baloratuDu(int idErab) {
		boolean topatuta = false;
		int i=0;
		while(!topatuta && i<balorazioak.length) {
			topatuta = balorazioak[i].baloratuDu(idErab);
			i++;
		}
		return topatuta;
	}
	
	public boolean komentatuDu(int idErab) {
		boolean topatuta = false;
		int i=0;
		while(!topatuta && i<komentarioak.size()) {
			topatuta = komentarioak.get(i).komentatuDu(idErab);
			i++;
		}
		return topatuta;
	}

	public String getTitulua() {
		return titulua;
	}
	
	public ArrayList<String> getGeneroa(){
		return generoa;
	}
	
	public Balorazioa getBalorazioa(double rating) {
		return balorazioak[(int) (rating*2)];
	}
	
	public Tag getKomentarioa(String k) {
		Tag koment = null;
		boolean topatuta = false;
		int i=0;
		while(i<komentarioak.size() && !topatuta) {
			Tag lag = komentarioak.get(i);
			if(lag.getKomentarioa().equals(k)) {
				koment = lag;
				topatuta = true;
			}
			i++;
		}
		return koment;
	}

	public int getPelikulaId() {
		return idMovie;
	}

	public int zenbatKomentario() {
		return komentarioak.size();
	}

	public Tag getKomentarioaPosizioz(int pPos) {
		return komentarioak.get(pPos);
	}
	
	public ArrayList<Tag> komentarioak() {
		return komentarioak;
	}
	
}