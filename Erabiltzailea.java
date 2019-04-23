package Proiektua;

import java.util.ArrayList;

import Interfaze.Login;

public class Erabiltzailea {

	//Atributuak
	private PelikulaZerrenda ikusitakoPelikulak;
	private int idUser;
	private int pasahitza;
	
	//Metodoak
	public Erabiltzailea(int id, int ph){
		this.idUser = id;
		this.pasahitza = ph;
		ikusitakoPelikulak = new PelikulaZerrenda();
	}
	
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
	
	public void pasahitzaBete() {
		Login.getLogin().setPasahitza(Integer.toString(pasahitza));
	}

	public ArrayList<String> ikusitakoPelikulakLortu() {
		return ikusitakoPelikulak.tituluakEtaBalorazioakLortu(idUser);
	}

}