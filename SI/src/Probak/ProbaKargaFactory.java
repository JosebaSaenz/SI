package Probak;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Proiektua.CSVKargaMota1;
import Proiektua.CSVKargaMota2;
import Proiektua.DatuenKarga;
import Proiektua.KargaFactory;
import Salbuespenak.KargaMotaEzDaExistitzenException;

public class ProbaKargaFactory {

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
	public void testGetKargaFactory() {
		assertNotNull(KargaFactory.getKargaFactory());
	}

	@Test
	public void testCreateKarga() throws KargaMotaEzDaExistitzenException {
		DatuenKarga dk1 = KargaFactory.getKargaFactory().createKarga(".csv mota1");
		DatuenKarga dk2 = KargaFactory.getKargaFactory().createKarga(".csv mota2");
		assertTrue(dk1 instanceof CSVKargaMota1);
		assertTrue(dk2 instanceof CSVKargaMota2);
		assertFalse(dk1 instanceof CSVKargaMota2);
		assertFalse(dk2 instanceof CSVKargaMota1);
		//DatuenKarga dk3 = KargaFactory.getKargaFactory().createKarga("ezDaExistitzen");	// "ezDaExistitzen" karga mota ez da existitzen eta salbuespena emango da
	}

}
