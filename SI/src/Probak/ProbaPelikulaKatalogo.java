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
import Proiektua.Tupla;
import Salbuespenak.ErabiltzaileaEzDaExistitzenException;
import Salbuespenak.KargaMotaEzDaExistitzenException;
import Salbuespenak.PelikulaEzDaExistitzenException;

public class ProbaPelikulaKatalogo {

	static Pelikula p1;
	static Pelikula p2;
	static Erabiltzailea e1;
	static Erabiltzailea e2;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {		
		ArrayList<String> generoak1 = new ArrayList<String>();
		generoak1.add("Romance");
		generoak1.add("Disaster");
		p1 = new Pelikula(1000,"Titanic",generoak1);
		ArrayList<String> generoak2 = new ArrayList<String>();
		generoak2.add("Science Fiction");
		p2 = new Pelikula(2000,"Avatar",generoak2);
		
		e1 = new Erabiltzailea(1);
		e2 = new Erabiltzailea(2);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		p1 = null;
		p2 = null;
		
		e1 = null;
		e2 = null;
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		PelikulaKatalogo.getPelikulaKatalogo().erreseteatu();
		GomendioSistema.getGomendioSistema().erreseteatu();
	}

	@Test
	public void testGetPelikulaKatalogo() {
		assertNotNull(PelikulaKatalogo.getPelikulaKatalogo());
	}
	
	@Test
	public void testGehituPelikula() throws PelikulaEzDaExistitzenException {
		PelikulaKatalogo.getPelikulaKatalogo().gehituPelikula(p1.getPelikulaId(), p1);
		PelikulaKatalogo.getPelikulaKatalogo().gehituPelikula(p2.getPelikulaId(), p2);
		assertEquals(PelikulaKatalogo.getPelikulaKatalogo().getPelikula(p1.getPelikulaId()),p1);
		assertEquals(PelikulaKatalogo.getPelikulaKatalogo().getPelikula(p2.getPelikulaId()),p2);
		assertNotEquals(PelikulaKatalogo.getPelikulaKatalogo().getPelikula(p1.getPelikulaId()),p2);
	}

	@Test
	public void testGetPelikula() throws PelikulaEzDaExistitzenException {
		PelikulaKatalogo.getPelikulaKatalogo().gehituPelikula(p1.getPelikulaId(), p1);
		PelikulaKatalogo.getPelikulaKatalogo().gehituPelikula(p2.getPelikulaId(), p2);
		assertEquals(PelikulaKatalogo.getPelikulaKatalogo().getPelikula(p1.getPelikulaId()),p1);
		assertEquals(PelikulaKatalogo.getPelikulaKatalogo().getPelikula(p2.getPelikulaId()),p2);
		assertNotEquals(PelikulaKatalogo.getPelikulaKatalogo().getPelikula(p1.getPelikulaId()),p2);
		//PelikulaKatalogo.getPelikulaKatalogo().getPelikula(3000);		// idMovie = 3000 duen pelikula ez da existitzen eta salbuespena emango da
	}

	@Test
	public void testPelikulakEskuratu() {
		
		// CSVKargaMota1 fitxategieko datuekin egindako probak dira
		GomendioSistema.getGomendioSistema().datuakKargatu(".csv mota1");
		
		ArrayList<String> tituluak = PelikulaKatalogo.getPelikulaKatalogo().pelikulakEskuratu();
		assertEquals(tituluak.size(), 9742); 							// 9742 pelikula desberdin irakurtzen dira
		assertNotEquals(tituluak.size(), 100);
		String lehena = tituluak.get(0);
		String azkena = tituluak.get(9741);
		assertEquals(lehena,"Toy Story (1995)");						// Eskuratutako lehen pelikularen titulua da
		assertEquals(azkena,"Andrew Dice Clay: Dice Rules (1991)");		// Eskuratutako azken pelikularen titulua da
		assertNotEquals(azkena,"Toy Story (1995)");
		assertNotEquals(lehena,"Andrew Dice Clay: Dice Rules (1991)");
	}

	@Test
	public void testDatuakEzabatu() {
		GomendioSistema.getGomendioSistema().datuakKargatu(".csv mota1");
		
		assertNotEquals(PelikulaKatalogo.getPelikulaKatalogo().getPelikulak().size(),0);
		PelikulaKatalogo.getPelikulaKatalogo().datuakEzabatu();
		assertEquals(PelikulaKatalogo.getPelikulaKatalogo().getPelikulak().size(),0);
	}
	
	@Test
	public void testGetPosiziokoPelikula() {
		PelikulaKatalogo.getPelikulaKatalogo().gehituPelikula(p1.getPelikulaId(), p1);
		PelikulaKatalogo.getPelikulaKatalogo().gehituPelikula(p2.getPelikulaId(), p2);
		
		assertEquals(PelikulaKatalogo.getPelikulaKatalogo().getPosiziokoPelikula(0),p1);
		assertEquals(PelikulaKatalogo.getPelikulaKatalogo().getPosiziokoPelikula(1),p2);
		assertNotEquals(PelikulaKatalogo.getPelikulaKatalogo().getPosiziokoPelikula(0),p2);
		assertNotEquals(PelikulaKatalogo.getPelikulaKatalogo().getPosiziokoPelikula(1),p1);
	}
	
	@Test
	public void testErregistratutaDago() {
		PelikulaKatalogo.getPelikulaKatalogo().gehituPelikula(p1.getPelikulaId(), p1);
		PelikulaKatalogo.getPelikulaKatalogo().gehituPelikula(p2.getPelikulaId(), p2);
		
		Pelikula p3 = new Pelikula(3000,"Shrek");
		
		assertTrue(PelikulaKatalogo.getPelikulaKatalogo().erregistratutaDago(p1.getPelikulaId()));
		assertTrue(PelikulaKatalogo.getPelikulaKatalogo().erregistratutaDago(p2.getPelikulaId()));
		assertFalse(PelikulaKatalogo.getPelikulaKatalogo().erregistratutaDago(p3.getPelikulaId()));
	}
	
	@Test
	public void testIdGuztiak() {
		PelikulaKatalogo.getPelikulaKatalogo().gehituPelikula(p1.getPelikulaId(), p1);
		PelikulaKatalogo.getPelikulaKatalogo().gehituPelikula(p2.getPelikulaId(), p2);	
		
		assertEquals(PelikulaKatalogo.getPelikulaKatalogo().idGuztiak().size(),2);
		assertNotEquals(PelikulaKatalogo.getPelikulaKatalogo().idGuztiak().size(),0);
		assertTrue(PelikulaKatalogo.getPelikulaKatalogo().idGuztiak().get(0) == p1.getPelikulaId());
		assertTrue(PelikulaKatalogo.getPelikulaKatalogo().idGuztiak().get(1) == p2.getPelikulaId());
		assertFalse(PelikulaKatalogo.getPelikulaKatalogo().idGuztiak().get(0) == p2.getPelikulaId());
		assertFalse(PelikulaKatalogo.getPelikulaKatalogo().idGuztiak().get(1) == p1.getPelikulaId());
	}
	
	@Test
	public void testTuplatikIzenakLortu() {
		PelikulaKatalogo.getPelikulaKatalogo().gehituPelikula(p1.getPelikulaId(), p1);
		PelikulaKatalogo.getPelikulaKatalogo().gehituPelikula(p2.getPelikulaId(), p2);
		
		Tupla t1 = new Tupla(p1.getPelikulaId(),2.5);
		Tupla t2 = new Tupla(p2.getPelikulaId(),5);
		Tupla[] zer = new Tupla[2];
		zer[0] = t1;
		zer[1] = t2;
		
		ArrayList<String> tituluak = PelikulaKatalogo.getPelikulaKatalogo().tuplatikIzenakLortu(zer);
		
		assertEquals(tituluak.get(0),p1.getTitulua());
		assertEquals(tituluak.get(1),p2.getTitulua());
		assertNotEquals(tituluak.get(0),p2.getTitulua());
		assertNotEquals(tituluak.get(1),p1.getTitulua());
	}

}
