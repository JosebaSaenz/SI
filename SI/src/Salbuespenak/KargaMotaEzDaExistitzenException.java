package Salbuespenak;


public class KargaMotaEzDaExistitzenException extends Exception {
	
	public KargaMotaEzDaExistitzenException() {
		super();
	}

	public void mezua(String kargaMota) {
		System.out.println("'" + kargaMota + "' datuak kargatzeko era ez da existitzen.");
	}
}
