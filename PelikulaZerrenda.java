package Proiektua;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

public class PelikulaZerrenda {
	
	//Atributuak
	private HashMap<Integer,Pelikula> zerrenda;
	private ArrayList<Integer> idPelikulak;
	
	//Metodoak
	public PelikulaZerrenda() {
		zerrenda = new HashMap<Integer,Pelikula>();
		idPelikulak = new ArrayList<Integer>();
	}
	
	public void pelikulaGehitu(int id, Pelikula p) {
		if(!zerrenda.containsKey(id)) {
			idPelikulak.add(id);
			this.zerrenda.put(id, p);
		}
	}
	
	public Pelikula getPelikula(int id) {
		return zerrenda.get(id);
	}
	
	public Pelikula getPosiziokoPelikula(int pPos) {
		return zerrenda.get(idPelikulak.get(pPos));
	}

	public ArrayList<String> pelikulakEskuratu() {
		ArrayList<String> tituluak = new ArrayList<String>();
		Iterator<Integer> itr = idPelikulak.iterator();
		while(itr.hasNext()) {
			tituluak.add(zerrenda.get(itr.next()).getTitulua());
		}
		return tituluak;
	}

	public HashMap<Integer, Pelikula> getPelikulak() {
		return zerrenda;
	}
	
	public ArrayList<Integer> getIdPelikulak() {
		return idPelikulak;
	}
	
	public void datuakEzabatu() {
		zerrenda.clear();
		idPelikulak.clear();
	}

	public boolean badago(int idMovie) {
		return zerrenda.containsKey(idMovie);
	}

	public int pelikulaKop() {
		return idPelikulak.size();
	}

	public boolean erregistratutaDago(int idMovie) {
		return zerrenda.containsKey(idMovie);
	}
	
	public ArrayList<Integer> idGuztiak(){
		return this.idPelikulak;
	}

	public ArrayList<String> tituluakEtaBalorazioakLortu(int idUser) {
		ArrayList<String> emaitza = new ArrayList<String>();
		for(int i=0; i<zerrenda.size(); i++) {
			String unekoTitulua = zerrenda.get(idPelikulak.get(i)).getTitulua();
			double unekoBalorazioaLag = zerrenda.get(idPelikulak.get(i)).erabiltzaileBalorazioa(idUser);
			String unekoBalorazioa = Double.toString(unekoBalorazioaLag);
			emaitza.add("Titulua: " + unekoTitulua + "; Balorazioa: " + unekoBalorazioa);
		}
		return emaitza;
	}
}