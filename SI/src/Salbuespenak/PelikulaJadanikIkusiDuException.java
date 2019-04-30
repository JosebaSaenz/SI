package Salbuespenak;

public class PelikulaJadanikIkusiDuException extends Exception{
	public PelikulaJadanikIkusiDuException() {
		super();
	}
	
	public void mezua(int idUser, int idMovie) {
		System.out.println("'" + idUser +"' erabiltzaileak '" + idMovie + "' pelikula jadanik ikusi du.");
	}
}
