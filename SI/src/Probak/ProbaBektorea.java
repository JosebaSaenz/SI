package Probak;

import static org.junit.Assert.*;

import java.text.DecimalFormat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Proiektua.Bektorea;

public class ProbaBektorea {

	Bektorea bek1;
	Bektorea bek2;
	Bektorea bek3;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		bek1 = new Bektorea();
		bek2 = new Bektorea();
	}

	@After
	public void tearDown() throws Exception {
		bek1 = null;
		bek2 = null;
	}

	@Test
	public void testBektorea() {
		assertNotNull(bek1);
		assertNotNull(bek2);
		assertNull(bek3);
	}

	@Test
	public void testGehituElementua() {
		bek1.gehituElementua(100, 2.5);
		bek1.gehituElementua(200, 5);
		bek1.gehituElementua(300, 4.5);
		
		bek2.gehituElementua(100, 1.5);
		bek2.gehituElementua(200, 4);
		bek2.gehituElementua(300, 3);
		
		assertTrue(bek1.getBalioa(100) == 2.5);
		assertTrue(bek1.getBalioa(200) == 5);
		assertTrue(bek1.getBalioa(300) == 4.5);
		assertFalse(bek1.getBalioa(100) == 1);
		assertFalse(bek1.getBalioa(100) == 2);
		assertFalse(bek1.getBalioa(300) == 2);
		
		assertTrue(bek2.getBalioa(100) == 1.5);
		assertTrue(bek2.getBalioa(200) == 4);
		assertTrue(bek2.getBalioa(300) == 3);
		assertFalse(bek2.getBalioa(100) == 5);
		assertFalse(bek2.getBalioa(200) == 3);
		assertFalse(bek2.getBalioa(300) == 4);
	}

	@Test
	public void testBektoreanDago() {
		bek1.gehituElementua(100, 2.5);
		bek1.gehituElementua(200, 5);
		bek1.gehituElementua(300, 4.5);
		
		bek2.gehituElementua(300, 1.5);
		bek2.gehituElementua(400, 4);
		bek2.gehituElementua(500, 3);
		
		assertTrue(bek1.bektoreanDago(100));
		assertTrue(bek1.bektoreanDago(200));
		assertTrue(bek1.bektoreanDago(300));
		assertFalse(bek1.bektoreanDago(400));
		assertFalse(bek1.bektoreanDago(500));
		assertFalse(bek1.bektoreanDago(600));
		
		assertTrue(bek2.bektoreanDago(300));
		assertTrue(bek2.bektoreanDago(400));
		assertTrue(bek2.bektoreanDago(500));
		assertFalse(bek2.bektoreanDago(100));
		assertFalse(bek2.bektoreanDago(200));
		assertFalse(bek2.bektoreanDago(600));
	}

	@Test
	public void testLuzera() {
		bek1.gehituElementua(100, 2.5);
		bek1.gehituElementua(200, 5);
		bek1.gehituElementua(300, 4.5);
		bek1.gehituElementua(400, 1.5);
		bek1.gehituElementua(500, 4);
		
		bek2.gehituElementua(500, 3);
		
		assertTrue(bek1.luzera() == 5);				// asmatutako balioa
		assertFalse(bek1.luzera() == 1);
		
		assertTrue(bek2.luzera() == 1);
		assertFalse(bek2.luzera() == 5);			// asmatutako balioa
	}

	@Test
	public void testGetElementuarenIdErreala() {
		bek1.gehituElementua(100, 2.5);
		bek1.gehituElementua(200, 5);
		bek1.gehituElementua(300, 4.5);
		
		bek2.gehituElementua(300, 1.5);
		bek2.gehituElementua(400, 4);
		bek2.gehituElementua(500, 3);
		
		assertTrue(bek1.getElementuarenIdErreala(0) == 100);
		assertTrue(bek1.getElementuarenIdErreala(1) == 200);
		assertTrue(bek1.getElementuarenIdErreala(2) == 300);
		assertFalse(bek1.getElementuarenIdErreala(0) == 300);
		assertFalse(bek1.getElementuarenIdErreala(1) == 100);
		assertFalse(bek1.getElementuarenIdErreala(2) == 200);
		
		assertTrue(bek2.getElementuarenIdErreala(0) == 300);
		assertTrue(bek2.getElementuarenIdErreala(1) == 400);
		assertTrue(bek2.getElementuarenIdErreala(2) == 500);
		assertFalse(bek2.getElementuarenIdErreala(0) == 500);
		assertFalse(bek2.getElementuarenIdErreala(1) == 300);
		assertFalse(bek2.getElementuarenIdErreala(2) == 400);
	}

	@Test
	public void testGetPosiziokoBalioa() {
		bek1.gehituElementua(100, 2.5);
		bek1.gehituElementua(200, 5);
		bek1.gehituElementua(300, 4.5);
		
		bek2.gehituElementua(300, 1.5);
		bek2.gehituElementua(400, 4);
		bek2.gehituElementua(500, 3);
		
		assertTrue(bek1.getPosiziokoBalioa(0) == 2.5);
		assertTrue(bek1.getPosiziokoBalioa(1) == 5);
		assertTrue(bek1.getPosiziokoBalioa(2) == 4.5);
		assertFalse(bek1.getPosiziokoBalioa(0) == 1);
		assertFalse(bek1.getPosiziokoBalioa(1) == 2);
		assertFalse(bek1.getPosiziokoBalioa(2) == 3);
	
		assertTrue(bek2.getPosiziokoBalioa(0) == 1.5);
		assertTrue(bek2.getPosiziokoBalioa(1) == 4);
		assertTrue(bek2.getPosiziokoBalioa(2) == 3);
		assertFalse(bek2.getPosiziokoBalioa(0) == 1);
		assertFalse(bek2.getPosiziokoBalioa(1) == 2);
		assertFalse(bek2.getPosiziokoBalioa(2) == 4);
	}

	@Test
	public void testGehituBalioa() {
		bek1.gehituElementua(100, 2.5);
		bek2.gehituElementua(300, 1.5);
		
		bek1.gehituBalioa(100, 1);
		bek2.gehituBalioa(300, 3.5);
		
		assertTrue(bek1.getBalioa(100) == 3.5);
		assertFalse(bek1.getBalioa(100) == 2.5);
		
		assertTrue(bek2.getBalioa(300) == 5);
		assertFalse(bek2.getBalioa(300) == 1.5);
	}

	@Test
	public void testAldatuPosiziokoBalioa() {
		bek1.gehituElementua(100, 2.5);
		bek2.gehituElementua(300, 1.5);
		
		bek1.aldatuPosiziokoBalioa(0, 1);
		bek2.aldatuPosiziokoBalioa(0, 3.5);
		
		assertTrue(bek1.getBalioa(100) == 1);
		assertFalse(bek1.getBalioa(100) == 2.5);
		
		assertTrue(bek2.getBalioa(300) == 3.5);
		assertFalse(bek2.getBalioa(300) == 1.5);
	}

	@Test
	public void testBektorearenBatezbestekoa() {
		bek1.gehituElementua(100, 2.5);
		bek1.gehituElementua(200, 5);
		bek1.gehituElementua(300, 4.5);
		
		bek2.gehituElementua(300, 1.5);
		
		assertTrue(bek1.bektorearenBatezbestekoa() == 4);
		assertFalse(bek1.bektorearenBatezbestekoa() == 3.56);			// asmatutako balioa
		
		assertTrue(bek2.bektorearenBatezbestekoa() == 1.5);
		assertFalse(bek2.bektorearenBatezbestekoa() == 3.56);			// asmatutako balioa
		
		Bektorea bek3 = new Bektorea();
		assertTrue(bek3.bektorearenBatezbestekoa() == 0);
	}

	@Test
	public void testBektorearenDesbiderapenEstandarra() {
		bek1.gehituElementua(100, 2.5);
		bek1.gehituElementua(200, 5);
		bek1.gehituElementua(300, 4.5);
		
		bek2.gehituElementua(300, 1.5);
		
		DecimalFormat df = new DecimalFormat("#.0000");
				
		assertTrue(df.format(bek1.bektorearenDesbiderapenEstandarra()).equals("1,0801"));
		assertFalse(df.format(bek1.bektorearenDesbiderapenEstandarra()).equals("2,5555"));			// asmatutako balioa
		
		assertTrue(df.format(bek2.bektorearenDesbiderapenEstandarra()).equals(",0000"));
		assertFalse(df.format(bek2.bektorearenDesbiderapenEstandarra()).equals("2,5555"));			// asmatutako balioa
		
		Bektorea bek3 = new Bektorea();
		assertTrue(df.format(bek3.bektorearenDesbiderapenEstandarra()).equals(",0000"));
	}

	@Test
	public void testBektorearenModulua() {
		bek1.gehituElementua(100, 2.5);
		bek1.gehituElementua(200, 5);
		bek1.gehituElementua(300, 4.5);
		
		bek2.gehituElementua(300, 1.5);
		
		DecimalFormat df = new DecimalFormat("#.0000");
				
		assertTrue(df.format(bek1.bektorearenModulua()).equals("7,1764"));
		assertFalse(df.format(bek1.bektorearenModulua()).equals("2,5555"));			// asmatutako balioa
		
		assertTrue(df.format(bek2.bektorearenModulua()).equals("1,5000"));
		assertFalse(df.format(bek2.bektorearenModulua()).equals("2,5555"));			// asmatutako balioa
		
		Bektorea bek3 = new Bektorea();
		assertTrue(df.format(bek3.bektorearenModulua()).equals(",0000"));
	}

}
