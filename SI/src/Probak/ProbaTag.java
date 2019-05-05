package Probak;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Proiektua.Erabiltzailea;
import Proiektua.Tag;

public class ProbaTag {
	
	static Tag t1;
	static Tag t2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		t1 = new Tag("Pelikula ona",1);
		t2 = null;
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
	public void testTag() {
		assertNotNull(t1);
		assertNull(t2);
	}

	@Test
	public void testGehituErabiltzailea() {
		Erabiltzailea e1 = new Erabiltzailea(1);
		Erabiltzailea e2 = new Erabiltzailea(2);
		
		t1.gehituErabiltzailea(e1.getId());
		assertTrue(t1.komentatuDu(e1.getId()));
		assertFalse(t1.komentatuDu(e2.getId()));
	}

	@Test
	public void testBerdinaDa() {
		assertTrue(t1.berdinaDa("Pelikula ona"));
		assertFalse(t1.berdinaDa("Aspergarria"));
	}
	
	@Test
	public void testKomentatuDu() {
		Erabiltzailea e3 = new Erabiltzailea(3);
		Erabiltzailea e4 = new Erabiltzailea(4);
		
		t1.gehituErabiltzailea(e3.getId());
		assertTrue(t1.komentatuDu(e3.getId()));
		assertFalse(t1.komentatuDu(e4.getId()));
	}

}
