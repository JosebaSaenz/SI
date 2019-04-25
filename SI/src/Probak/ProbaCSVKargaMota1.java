package Probak;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Proiektua.CSVKargaMota1;
import Proiektua.Erabiltzailea;
import Proiektua.GomendioSistema;
import Proiektua.Pelikula;
import Proiektua.PelikulaKatalogo;
import Proiektua.PelikulaZerrenda;
import Salbuespenak.ErabiltzaileaEzDaExistitzenException;
import Salbuespenak.KargaMotaEzDaExistitzenException;
import Salbuespenak.PelikulaEzDaExistitzenException;

public class ProbaCSVKargaMota1 {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCSVKargaMota1() {
		CSVKargaMota1 k1 = new CSVKargaMota1();
		CSVKargaMota1 k2 = new CSVKargaMota1();
		CSVKargaMota1 k3 = null;
		assertNotNull(k1);
		assertNotNull(k2);
		assertNull(k3);
	}
	
	@Test
	public void testDatuakKargatu() throws PelikulaEzDaExistitzenException, ErabiltzaileaEzDaExistitzenException {
		GomendioSistema.getGomendioSistema().datuakKargatu();

		// datuakKargatu() metodoak hiru metodoei dei egiten die:
		
		// Lehenengo metodoaren probak (pelikulakIraukurri()):
		Pelikula p1 = PelikulaKatalogo.getPelikulaKatalogo().getPelikula(1);
		Pelikula p2 = PelikulaKatalogo.getPelikulaKatalogo().getPelikula(193609);
		ArrayList<String> generoak1 = new ArrayList<String>();						// p1 pelikularen generoak
		generoak1.add("Adventure");
		generoak1.add("Animation");
		generoak1.add("Children");
		generoak1.add("Comedy");
		generoak1.add("Fantasy");
		ArrayList<String> generoak2 = new ArrayList<String>();						// p2 pelikularen generoak
		generoak2.add("Comedy");
		assertEquals(p1.getPelikulaId(),1);
		assertEquals(p2.getPelikulaId(),193609);
		assertEquals(p1.getTitulua(),"Toy Story (1995)");							// p1 pelikularen titulua da
		assertEquals(p2.getTitulua(),"Andrew Dice Clay: Dice Rules (1991)");		// p2 pelikularen titulua da
		assertNotEquals(p2.getTitulua(),"Toy Story (1995)");
		assertNotEquals(p1.getTitulua(),"Andrew Dice Clay: Dice Rules (1991)");	
		assertEquals(p1.getGeneroa(),generoak1);
		assertEquals(p2.getGeneroa(),generoak2);
		assertNotEquals(p1.getGeneroa(),generoak2);
		assertNotEquals(p2.getGeneroa(),generoak1);
		
		// Bigarren metodoaren probak (balorazioakIrakurri()):
		Erabiltzailea e1 = GomendioSistema.getGomendioSistema().getErabiltzailea(1);
		Erabiltzailea e2 = GomendioSistema.getGomendioSistema().getErabiltzailea(610);
		assertEquals(e1.getId(),1);
		assertEquals(e2.getId(),610);
		assertTrue(e1.ikusiDu(101));												// e1 erabiltzaileak 101 id-a duen pelikula ikusi du
		assertFalse(e1.ikusiDu(48738));												// e1 erabiltzaileak ez du 48738 id-a duen pelikula
		assertTrue(e2.ikusiDu(50));													// e2 erabiltzaileak 50 id-a duen pelikula ikusi du
		assertFalse(e2.ikusiDu(333));												// e2 erabiltzaileak ez du 333 id-a duen pelikula
		assertTrue(PelikulaKatalogo.getPelikulaKatalogo().getPelikula(3).baloratuDu(e1.getId()));			// e1 erabiltzaileak 3 id-a duen pelikula baloratu du
		assertFalse(PelikulaKatalogo.getPelikulaKatalogo().getPelikula(2).baloratuDu(e1.getId()));		// e1 erabiltzaileak ez du 2 id-a duen pelikula baloratu
		assertTrue(PelikulaKatalogo.getPelikulaKatalogo().getPelikula(6).baloratuDu(e2.getId()));			// e2 erabiltzaileak 6 id-a duen pelikula baloratu du
		assertFalse(PelikulaKatalogo.getPelikulaKatalogo().getPelikula(4).baloratuDu(e2.getId()));		// e2 erabiltzaileak ez du 4 id-a duen pelikula baloratu
		
		// Hirugarren metodoaren probak (komentarioakIrakurri()):
		Erabiltzailea e3 = GomendioSistema.getGomendioSistema().getErabiltzailea(2);
		Erabiltzailea e4 = GomendioSistema.getGomendioSistema().getErabiltzailea(606);
		assertEquals(e3.getId(),2);
		assertEquals(e4.getId(),606);
		assertTrue(e3.ikusiDu(71535));												// e3 erabiltzaileak 71535 id-a duen pelikula ikusi du
		assertFalse(e3.ikusiDu(5));													// e3 erabiltzaileak ez du 5 id-a duen pelikula
		assertTrue(e4.ikusiDu(11));													// e4 erabiltzaileak 11 id-a duen pelikula ikusi du
		assertFalse(e4.ikusiDu(6));													// e4 erabiltzaileak ez du 6 id-a duen pelikula
		assertTrue(PelikulaKatalogo.getPelikulaKatalogo().getPelikula(60756).komentatuDu(e3.getId()));	// e3 erabiltzaileak 60756 id-a duen pelikulan komentatu du
		assertFalse(PelikulaKatalogo.getPelikulaKatalogo().getPelikula(100).komentatuDu(e3.getId()));		// e3 erabiltzaileak ez du 100 id-a duen pelikulan komentatu
		assertTrue(PelikulaKatalogo.getPelikulaKatalogo().getPelikula(7936).komentatuDu(e4.getId()));		// e4 erabiltzaileak 7936 id-a duen pelikulan komentatu du
		assertFalse(PelikulaKatalogo.getPelikulaKatalogo().getPelikula(7000).komentatuDu(e4.getId()));	// e4 erabiltzaileak ez du 7000 id-a duen pelikulan komentatu
	
	}

}
