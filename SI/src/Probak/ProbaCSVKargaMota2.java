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
import Salbuespenak.ErabiltzaileaEzDaExistitzenException;
import Salbuespenak.KargaMotaEzDaExistitzenException;
import Salbuespenak.PelikulaEzDaExistitzenException;

public class ProbaCSVKargaMota2 {

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
	public void testDatuakKargatu() throws ErabiltzaileaEzDaExistitzenException, PelikulaEzDaExistitzenException, KargaMotaEzDaExistitzenException {
		
		// .CSV MOTA2 FITXATEGIAK (movietitles.csv, movierating.csv eta movietags.csv) ERABILTZEN DIRA
		
		GomendioSistema.getGomendioSistema().datuakKargatu(".csv mota2");

		// datuakKargatu() metodoak hiru metodoei dei egiten die:
		
		// Lehenengo metodoaren probak (pelikulakIraukurri()):
		Pelikula p1 = PelikulaKatalogo.getPelikulaKatalogo().getPelikula(11);
		Pelikula p2 = PelikulaKatalogo.getPelikulaKatalogo().getPelikula(36955);
		assertEquals(p1.getPelikulaId(),11);
		assertEquals(p2.getPelikulaId(),36955);
		assertEquals(p1.getTitulua(),"Star Wars: Episode IV - A New Hope (1977)");	// p1 pelikularen titulua da
		assertEquals(p2.getTitulua(),"True Lies (1994)");							// p2 pelikularen titulua da
		assertNotEquals(p1.getTitulua(),"True Lies (1994)");
		assertNotEquals(p2.getTitulua(),"Star Wars: Episode IV - A New Hope (1977)");	
		
		// Bigarren metodoaren probak (balorazioakIrakurri()):
		Erabiltzailea e1 = GomendioSistema.getGomendioSistema().getErabiltzailea(1);
		Erabiltzailea e2 = GomendioSistema.getGomendioSistema().getErabiltzailea(5572);
		assertEquals(e1.getId(),1);
		assertEquals(e2.getId(),5572);
		assertTrue(e1.ikusiDu(664));												// e1 erabiltzaileak 664 id-a duen pelikula ikusi du
		assertFalse(e1.ikusiDu(1894));												// e1 erabiltzaileak ez du 1894 id-a duen pelikula
		assertTrue(e2.ikusiDu(607));												// e2 erabiltzaileak 607 id-a duen pelikula ikusi du
		assertFalse(e2.ikusiDu(12));												// e2 erabiltzaileak ez du 12 id-a duen pelikula
		assertTrue(PelikulaKatalogo.getPelikulaKatalogo().getPelikula(664).baloratuDu(e1.getId()));			// e1 erabiltzaileak 664 id-a duen pelikula baloratu du
		assertFalse(PelikulaKatalogo.getPelikulaKatalogo().getPelikula(1894).baloratuDu(e1.getId()));		// e1 erabiltzaileak ez du 1894 id-a duen pelikula baloratu
		assertTrue(PelikulaKatalogo.getPelikulaKatalogo().getPelikula(607).baloratuDu(e2.getId()));			// e2 erabiltzaileak 607 id-a duen pelikula baloratu du
		assertFalse(PelikulaKatalogo.getPelikulaKatalogo().getPelikula(12).baloratuDu(e2.getId()));			// e2 erabiltzaileak ez du 12 id-a duen pelikula baloratu
		
		// Hirugarren metodoaren probak (komentarioakIrakurri()):
		Pelikula p3 = PelikulaKatalogo.getPelikulaKatalogo().getPelikula(114);
		Pelikula p4 = PelikulaKatalogo.getPelikulaKatalogo().getPelikula(581);
		assertNotNull(p3.getKomentarioa("classic"));								// p3 pelikulan "classic" komentarioa dago
		assertNull(p3.getKomentarioa("boring"));									// p3 pelikulan ez dago "boring" komentarioa
		assertNotNull(p4.getKomentarioa("war"));									// p4 pelikulan "war" komentarioa dago
		assertNull(p4.getKomentarioa("interesting"));								// p4 pelikulan ez dago "interesting" komentarioa
	}

}
