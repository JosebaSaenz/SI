package Probak;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Proiektua.Erabiltzailea;
import Proiektua.Pelikula;
import Proiektua.PelikulaKatalogo;
import Proiektua.PelikulaZerrenda;

public class ProbaPelikulaZerrenda {
	
	PelikulaZerrenda z1;
	PelikulaZerrenda z2;
	
	Pelikula p1;
	Pelikula p2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ArrayList<String> generoak1 = new ArrayList<String>();
		ArrayList<String> generoak2 = new ArrayList<String>();
		generoak1.add("Romance");
		generoak1.add("Disaster");
		generoak2.add("Science fiction");
		
		p1 = new Pelikula(1000,"Titanic",generoak1);
		p2 = new Pelikula(2000,"Avatar",generoak2);
		
		z1 = new PelikulaZerrenda();
	}

	@After
	public void tearDown() throws Exception {
		p1 = null;
		p2 = null;
		
		z1 = null;

	}

	@Test
	public void testPelikulaZerrenda() {
		assertNotNull(z1);
		assertNull(z2);
	}

	@Test
	public void testPelikulaGehitu() {		
		z1.pelikulaGehitu(p1.getPelikulaId(), p1);
		
		assertTrue(z1.badago(p1.getPelikulaId()));
		assertFalse(z1.badago(p2.getPelikulaId()));
	}

	@Test
	public void testPelikulakEskuratu() {		
		z1.pelikulaGehitu(p1.getPelikulaId(), p1);
		z1.pelikulaGehitu(p2.getPelikulaId(), p2);
		
		assertTrue(z1.pelikulakEskuratu().get(0).equals(p1.getTitulua()));
		assertFalse(z1.pelikulakEskuratu().get(1).equals(p1.getTitulua()));	}

	@Test
	public void testDatuakEzabatu() {		
		z1.pelikulaGehitu(p1.getPelikulaId(), p1);
		z1.pelikulaGehitu(p2.getPelikulaId(), p2);
		
		assertFalse(z1.getPelikulak().isEmpty());
		assertFalse(z1.getIdPelikulak().isEmpty());
		
		z1.datuakEzabatu();
		
		assertTrue(z1.getPelikulak().isEmpty());
		assertTrue(z1.getIdPelikulak().isEmpty());
	}
		
	@Test
	public void testBadago() {		
		z1.pelikulaGehitu(p1.getPelikulaId(), p1);
		
		assertTrue(z1.badago(p1.getPelikulaId()));
		assertFalse(z1.badago(p2.getPelikulaId()));
	}
	
	@Test
	public void testGetPosiziokoPelikula() {
		z1.pelikulaGehitu(p1.getPelikulaId(), p1);
		z1.pelikulaGehitu(p2.getPelikulaId(), p2);
		
		assertEquals(z1.getPosiziokoPelikula(0),p1);
		assertEquals(z1.getPosiziokoPelikula(1),p2);
		assertNotEquals(z1.getPosiziokoPelikula(0),p2);
		assertNotEquals(z1.getPosiziokoPelikula(1),p1);
	}
	
	@Test
	public void testErregistratutaDago() {
		z1.pelikulaGehitu(p1.getPelikulaId(), p1);
		z1.pelikulaGehitu(p2.getPelikulaId(), p2);
		
		Pelikula p3 = new Pelikula(3000,"Shrek");
		
		assertTrue(z1.erregistratutaDago(p1.getPelikulaId()));
		assertTrue(z1.erregistratutaDago(p1.getPelikulaId()));
		assertFalse(z1.erregistratutaDago(p3.getPelikulaId()));

	}
	
	@Test
	public void testIdGuztiak() {
		z1.pelikulaGehitu(p1.getPelikulaId(), p1);
		z1.pelikulaGehitu(p2.getPelikulaId(), p2);
		
		assertEquals(z1.idGuztiak().size(),2);
		assertNotEquals(z1.idGuztiak().size(),0);
		assertTrue(z1.idGuztiak().get(0) == p1.getPelikulaId());
		assertTrue(z1.idGuztiak().get(1) == p2.getPelikulaId());
		assertFalse(z1.idGuztiak().get(0) == p2.getPelikulaId());
		assertFalse(z1.idGuztiak().get(1) == p1.getPelikulaId());
	}
	
	@Test
	public void testTituluakEtaBalorazioakLortu() {
		Erabiltzailea e1 = new Erabiltzailea(1);
		
		p1.balorazioaGehitu(e1.getId(), 3.5);
		p2.balorazioaGehitu(e1.getId(), 2);
		
		z1.pelikulaGehitu(p1.getPelikulaId(), p1);
		z1.pelikulaGehitu(p2.getPelikulaId(), p2);
		
		ArrayList<String> ikusitakoPelikulakEtaBalorazioak = z1.tituluakEtaBalorazioakLortu(e1.getId());
		
		assertTrue(ikusitakoPelikulakEtaBalorazioak.size() == 2);
		assertTrue(ikusitakoPelikulakEtaBalorazioak.get(0).equals("Titulua: Titanic; Balorazioa: 3.5"));
		assertTrue(ikusitakoPelikulakEtaBalorazioak.get(1).equals("Titulua: Avatar; Balorazioa: 2.0"));
		assertFalse(ikusitakoPelikulakEtaBalorazioak.get(0).equals("Titulua: Titanic; Balorazioa: 5.0"));		// asmatutako balioak
		assertFalse(ikusitakoPelikulakEtaBalorazioak.get(1).equals("Titulua: Toy Story; Balorazioa: 2.0"));		// asmatutako balioak
	}

}
