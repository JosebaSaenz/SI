package Probak;

import static org.junit.Assert.*;


import Proiektua.Batezbestekoa;
import Proiektua.Bektorea;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProbaBatezbestekoa {

	static Batezbestekoa bb1;
	static Batezbestekoa bb2;
	static Batezbestekoa bb3;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bb1 = new Batezbestekoa();
		bb2= new Batezbestekoa();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testBatezbestekoa() {
		assertNotNull(bb1);
		assertNotNull(bb2);
		assertNull(bb3);
	}

	@Test
	public void testMatrizeaNormalizatu() {
		
		/* matrize bat sortzen dugu 4 errenkadekin (erabiltzaileak) eta 4 zutabeekin (pelikulak) 
		
		 			p1		p2		p3		p4
		 	e1				3		3.5		
		 	e2		4.5						5
		 	e3				1.5				
		 	e4				2		0.5		5
		 	
		 */
		
		Bektorea[] matErabPeli = new Bektorea[4];
		Bektorea b1 = new Bektorea();
		b1.gehituElementua(2, 3);
		b1.gehituElementua(3, 3.5);
		matErabPeli[0] = b1;
		Bektorea b2 = new Bektorea();
		b2.gehituElementua(1, 4.5);
		b2.gehituElementua(4, 5);
		matErabPeli[1] = b2;
		Bektorea b3 = new Bektorea();
		b3.gehituElementua(2, 1.5);
		matErabPeli[2] = b3;
		Bektorea b4 = new Bektorea();
		b4.gehituElementua(2, 2);
		b4.gehituElementua(3, 0.5);
		b4.gehituElementua(4, 5);
		matErabPeli[3] = b4;
		
		Bektorea[] matErabPeliNormalizatua = bb1.matrizeaNormalizatu(matErabPeli);
		
		assertTrue(matErabPeliNormalizatua[0].getBalioa(2) == -0.25);
		assertTrue(matErabPeliNormalizatua[0].getBalioa(3) == 0.25);
		assertTrue(matErabPeliNormalizatua[1].getBalioa(1) == -0.25);
		assertTrue(matErabPeliNormalizatua[1].getBalioa(4) == 0.25);
		assertTrue(matErabPeliNormalizatua[2].getBalioa(2) == 0);
		assertTrue(matErabPeliNormalizatua[3].getBalioa(2) == -0.5);
		assertTrue(matErabPeliNormalizatua[3].getBalioa(3) == -2);
		assertTrue(matErabPeliNormalizatua[3].getBalioa(4) == 2.5);
		
		assertFalse(matErabPeliNormalizatua[0].getBalioa(2) == 3);
		assertFalse(matErabPeliNormalizatua[0].getBalioa(3) == 3.5);
		assertFalse(matErabPeliNormalizatua[1].getBalioa(1) == 4.5);
		assertFalse(matErabPeliNormalizatua[1].getBalioa(4) == 5);
		assertFalse(matErabPeliNormalizatua[2].getBalioa(2) == 1.5);
		assertFalse(matErabPeliNormalizatua[3].getBalioa(2) == 2);
		assertFalse(matErabPeliNormalizatua[3].getBalioa(3) == 0.5);
		assertFalse(matErabPeliNormalizatua[3].getBalioa(4) == 5);

	}
}
