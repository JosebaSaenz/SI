package Probak;


import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Proiektua.Balorazioa;
import Proiektua.Erabiltzailea;
import Proiektua.Pelikula;

public class ProbaPelikula {
	
	Pelikula p1;
	Pelikula p2;
	Erabiltzailea e1;
	Erabiltzailea e2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ArrayList<String> generoak1 = new ArrayList<String>();
		generoak1.add("Romance");
		generoak1.add("Disaster");
		
		p1 = new Pelikula(1000,"Titanic",generoak1);
		
		e1 = new Erabiltzailea(1);
		e2 = new Erabiltzailea(2);
	}

	@After
	public void tearDown() throws Exception {
		e1 = null;
		e2 = null;
		
		p1 = null;
	}

	@Test
	public void testPelikula() {
		assertNotNull(p1);
		assertNull(p2);
	}

	@Test
	public void testBalorazioaGehitu() {
		p1.balorazioaGehitu(e1.getId(), 3.0);
		
		assertTrue(p1.getBalorazioa(3.0).baloratuDu(e1.getId()));
		assertFalse(p1.getBalorazioa(2.0).baloratuDu(e1.getId()));
		assertFalse(p1.getBalorazioa(3.0).baloratuDu(e2.getId()));
	}

	@Test
	public void testKometarioaGehitu() {		
		Erabiltzailea e3 = new Erabiltzailea(3);

		p1.komentarioaGehitu(e1.getId(), "Aspergarria",1);
		p1.komentarioaGehitu(e2.getId(), "Pelikula ona",2);
		p1.komentarioaGehitu(e3.getId(), "Aspergarria",1);
		
		assertTrue(p1.getKomentarioa("Aspergarria").komentatuDu(e1.getId()));
		assertTrue(p1.getKomentarioa("Pelikula ona").komentatuDu(e2.getId()));
		assertFalse(p1.getKomentarioa("Aspergarria").komentatuDu(e2.getId()));
		assertFalse(p1.getKomentarioa("Pelikula ona").komentatuDu(e1.getId()));
		assertTrue(p1.getKomentarioa("Aspergarria").komentatuDu(e3.getId()));
	}

	@Test
	public void testKometarioaErabiltzaileGabeGehitu() {
		p1.komentarioaErabiltzaileaGabeGehitu( "Aspergarria", 1);
		p1.komentarioaErabiltzaileaGabeGehitu( "Pelikula ona", 2);
		
		assertNotNull(p1.bilatuKomentarioa("Aspergarria"));
		assertNotNull(p1.bilatuKomentarioa("Pelikula ona"));
		assertNull(p1.bilatuKomentarioa("Dibertigarria"));

	}
	
	@Test
	public void testBilatuKomentarioa() {
		p1.komentarioaGehitu(e1.getId(), "Aspergarria",1);
		p1.komentarioaGehitu(e2.getId(), "Pelikula ona",2);
		
		assertNotNull(p1.bilatuKomentarioa("Aspergarria"));
		assertNotNull(p1.bilatuKomentarioa("Pelikula ona"));
		assertNull(p1.bilatuKomentarioa("Dibertigarria"));
	}
	
	@Test
	public void testBaloratuDu() {
		p1.balorazioaGehitu(e1.getId(), 2.5);
		
		assertTrue(p1.baloratuDu(e1.getId()));
		assertFalse(p1.baloratuDu(e2.getId()));
		
	}
	
	@Test
	public void testKomentatuDu() {
		p1.komentarioaGehitu(e1.getId(), "Dibertigarria",3);
		
		assertTrue(p1.komentatuDu(e1.getId()));
		assertFalse(p1.komentatuDu(e2.getId()));
	}
	
	@Test
	public void testErabiltzaileBalorazioa() {
		p1.balorazioaGehitu(e1.getId(), 2.5);
		p1.balorazioaGehitu(e2.getId(), 5);
		
		assertTrue(p1.erabiltzaileBalorazioa(e1.getId()) == 2.5);
		assertTrue(p1.erabiltzaileBalorazioa(e2.getId()) == 5);
		assertFalse(p1.erabiltzaileBalorazioa(e1.getId()) == 5);		// asmatutako balioa
		assertFalse(p1.erabiltzaileBalorazioa(e2.getId()) == 2.5);		// asmatutako balioa
	}

}
