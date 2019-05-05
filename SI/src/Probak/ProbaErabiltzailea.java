package Probak;

import static org.junit.Assert.*;


import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Proiektua.Erabiltzailea;
import Proiektua.GomendioSistema;
import Proiektua.Pelikula;
import Proiektua.PelikulaKatalogo;
import Salbuespenak.PelikulaEzDaExistitzenException;

public class ProbaErabiltzailea {
	
	Erabiltzailea e1;
	Erabiltzailea e2;
	
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
		
		e1 = new Erabiltzailea(1);

	}

	@After
	public void tearDown() throws Exception {
		e1 = null;
		
		p1 = null;
		p2 = null;
	}

	@Test
	public void testErabiltzailea() {
		assertNotNull(e1);
		assertNull(e2);
	}

	@Test
	public void testPelikulaGehitu(){
		
		e1.pelikulaGehitu(p1.getPelikulaId(),p1); 
		assertTrue(e1.ikusiDu(p1.getPelikulaId()));
		assertFalse(e1.ikusiDu(p2.getPelikulaId())); 
	}
	
	@Test
	public void testIkusiDu(){
		
		e1.pelikulaGehitu(p1.getPelikulaId(),p1);
		assertTrue(e1.ikusiDu(p1.getPelikulaId()));
		assertFalse(e1.ikusiDu(p2.getPelikulaId()));
	}

	@Test
	public void testIkusitakoPelikulaKop(){
		
		
		e1.pelikulaGehitu(p1.getPelikulaId(),p1);
		e1.pelikulaGehitu(p2.getPelikulaId(),p2);
		
		assertEquals(e1.ikusitakoPelikulaKop(),2);
		assertNotEquals(e1.ikusitakoPelikulaKop(),0);
		assertNotEquals(e1.ikusitakoPelikulaKop(),1);
	}
	
	@Test
	public void testIkusitakoPelikulakLortu(){
		
		e1.pelikulaGehitu(p1.getPelikulaId(), p1);
		e1.pelikulaGehitu(p2.getPelikulaId(), p2);
		p1.balorazioaGehitu(e1.getId(), 3.5);
		p2.balorazioaGehitu(e1.getId(), 2);
		
		ArrayList<String> ikusitakoPelikulakEtaBalorazioak = e1.ikusitakoPelikulakLortu();
		
		assertTrue(ikusitakoPelikulakEtaBalorazioak.size() == 2);
		assertTrue(ikusitakoPelikulakEtaBalorazioak.get(0).equals("Titulua: Titanic; Balorazioa: 3.5"));
		assertTrue(ikusitakoPelikulakEtaBalorazioak.get(1).equals("Titulua: Avatar; Balorazioa: 2.0"));
		assertFalse(ikusitakoPelikulakEtaBalorazioak.get(0).equals("Titulua: Titanic; Balorazioa: 5.0"));		// asmatutako balioak
		assertFalse(ikusitakoPelikulakEtaBalorazioak.get(1).equals("Titulua: Toy Story; Balorazioa: 2.0"));		// asmatutako balioak
	}
	
}
