package Proiektua;

import Salbuespenak.PelikulaEzDaExistitzenException;

public class Erabiltzailea {

	//Atributuak
	private PelikulaZerrenda ikusitakoPelikulak;
	private int idUser;
	
	//Metodoak
	public Erabiltzailea(int id){
		this.idUser = id;
		ikusitakoPelikulak = new PelikulaZerrenda();
	}

	public void pelikulaGehitu(int idMovie, Pelikula p){
		this.ikusitakoPelikulak.pelikulaGehitu(idMovie, p);
	}

	public boolean ikusiDu(int idMovie) {
		return ikusitakoPelikulak.badago(idMovie);
	}
	
	public int getId() {
		return idUser;
	}
	
	public int ikusitakoPelikulaKop() {
		return ikusitakoPelikulak.pelikulaKop();
	}
	
	public boolean pasahitzZuzena(int pPasahitza) {
		return this.pasahitza==pPasahitza;
	}

}
