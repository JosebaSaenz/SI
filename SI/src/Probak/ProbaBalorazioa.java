package Probak;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Proiektua.Balorazioa;
import Proiektua.Erabiltzailea;

public class ProbaBalorazioa {
	
	Balorazioa b1;
	Balorazioa b2;
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
		b1 = new Balorazioa(2, 2.5);
		e1 = new Erabiltzailea(1);
		e2 = new Erabiltzailea(2);
	}

	@After
	public void tearDown() throws Exception {
		b1 = null;
		e1 = null;
		e2 = null;
	}

	@Test
	public void testBalorazioa() {
		assertNotNull(b1);
		assertNull(b2);
	}

	@Test
	public void testGehituErabiltzailea() {
		b1.gehituErabiltzailea(e1.getId());
		assertTrue(b1.baloratuDu(e1.getId()));
		assertFalse(b1.baloratuDu(e2.getId()));
	}
	
	@Test
	public void testBaloratuDu() {
		b1.gehituErabiltzailea(e1.getId());
		assertTrue(b1.baloratuDu(e1.getId()));
		assertFalse(b1.baloratuDu(e2.getId()));
	}
	
	@Test
	public void testGetErabiltzaileId() {
		b1.gehituErabiltzailea(e1.getId());
		b1.gehituErabiltzailea(e2.getId());
		assertTrue(b1.getErabiltzaileId(0) == 1);
		assertTrue(b1.getErabiltzaileId(1) == 2);
		assertFalse(b1.getErabiltzaileId(1) == 1);
		assertFalse(b1.getErabiltzaileId(0) == 2);
	}

}
