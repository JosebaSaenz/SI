package Proiektua;

import Salbuespenak.ErabiltzaileaEzDaExistitzenException;
import Salbuespenak.PelikulaEzDaExistitzenException;

public abstract class DatuenKarga {
	
	public DatuenKarga() {}
	
	public abstract void datuakKargatu() throws ErabiltzaileaEzDaExistitzenException, PelikulaEzDaExistitzenException;
	
}
