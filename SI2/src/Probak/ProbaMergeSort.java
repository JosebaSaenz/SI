package Probak;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Proiektua.MergeSort;
import Proiektua.Tupla;

public class ProbaMergeSort {

	static MergeSort ms1;
	static MergeSort ms2;
	static MergeSort ms3;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ms1 = new MergeSort();
		ms2 = new MergeSort();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testMergeSort() {
		assertNotNull(ms1);
		assertNotNull(ms2);
		assertNull(ms3);
	}

	@Test
	public void testHandTxikOrdenatu() {
		Tupla[] zer1 = new Tupla[5];
		zer1[0] = new Tupla(100,5.6);
		zer1[1] = new Tupla(200,3);
		zer1[2] = new Tupla(300,10.64);
		zer1[3] = new Tupla(400,1);
		zer1[4] = new Tupla(500,7.8);
		
		ms1.handTxikOrdenatu(zer1);
				
		assertTrue(zer1[0].getBalioa() == 10.64);
		assertTrue(zer1[1].getBalioa() == 7.8);
		assertTrue(zer1[2].getBalioa() == 5.6);
		assertTrue(zer1[3].getBalioa() == 3);
		assertTrue(zer1[4].getBalioa() == 1);
		
		assertFalse(zer1[0].getBalioa() == 5.6);
		assertFalse(zer1[1].getBalioa() == 3);
		assertFalse(zer1[2].getBalioa() == 10.64);
		assertFalse(zer1[3].getBalioa() == 1);
		assertFalse(zer1[4].getBalioa() == 7.8);
	}

}
