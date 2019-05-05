package Salbuespenak;


public class ErabiltzaileaEzDaExistitzenException extends Exception {
	
	public ErabiltzaileaEzDaExistitzenException() {
		super();
	}
	
	public void mezua(int idUser) {
		System.out.println("'" + idUser + "' erabiltzailea ez da existitzen.");
	}

	
}
