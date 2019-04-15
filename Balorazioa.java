package Proiektua;
import java.util.ArrayList;

public class Balorazioa {
	//Atributuak
	private int idMovie;
	private ArrayList<Integer> idUser;
	private double balorazioa;
	private int userKop;
	
	//Metodoak
	public Balorazioa(int idMovie, double b) {
		this.idMovie = idMovie;
		this.balorazioa = b;
		idUser = new ArrayList<Integer>();
		userKop = 0;
	}
	
	public void gehituErabiltzailea(int id) {
		this.idUser.add(id);
		userKop++;
	}

	public boolean baloratuDu(int idErab) {
		return this.idUser.contains(idErab);
	}

	public int zenbatErabiltzailea() {
		return idUser.size();
	}

	public int getErabiltzaileId(int pPos) {
		return idUser.get(pPos);
	}

}