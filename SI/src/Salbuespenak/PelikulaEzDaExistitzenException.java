package Salbuespenak;

public class PelikulaEzDaExistitzenException extends Exception {
	
	public PelikulaEzDaExistitzenException() {
		super();
	}
	
	public void mezua(int idMovie) {
		System.out.println("'" + idMovie + "' pelikula ez da existitzen.");
	}

	
}
