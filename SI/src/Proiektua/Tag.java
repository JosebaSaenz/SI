package Proiektua;

import java.util.ArrayList;

import java.lang.String;

public class Tag {

	//Atributuak
	private int idTag;
	private ArrayList<Integer> idUser;
	private String komentarioa;
	private int zenbatAldiz;
	
	//Metodoak
	public Tag(String k, int idTag) {
		this.idTag = idTag;
		this.komentarioa = k;
		idUser = new ArrayList<Integer>();
		zenbatAldiz = 1;
	}
	
	public void gehituErabiltzailea(int id) {
		this.idUser.add(id);
	}

	public boolean berdinaDa(String k) {
		return komentarioa.equals(k);
	}

	public boolean komentatuDu(int idErab) {
		boolean topatuta = false;
		int i=0;
		while(i<idUser.size() && !topatuta) {
			int erab = idUser.get(i);
			if(erab == idErab) {
				topatuta = true;
			}
			i++;
		}
		return topatuta;
	}

	public String getKomentarioa() {
		return komentarioa;
	}

	public int zenbatAldiz() {
		return zenbatAldiz;
	}

	public void kopuruaInkrementatu() {
		zenbatAldiz++;
	}

	public int getTagId() {
		return idTag;
	}
	
}