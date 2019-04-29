package Probak;

import static org.junit.Assert.*;

import java.text.DecimalFormat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Proiektua.Bektorea;
import Proiektua.Kosinua;

public class ProbaKosinua {

	static Kosinua k1;
	static Kosinua k2;
	static Kosinua k3;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		k1 = new Kosinua();
		k2 = new Kosinua();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testKosinua() {
		assertNotNull(k1);
		assertNotNull(k2);
		assertNull(k3);
	}

	@Test
	public void testAntzekotasunaKalkulatu() {
		
		// Proba1
		
		Bektorea bek1 = new Bektorea();
		Bektorea bek2 = new Bektorea();
		bek1.gehituElementua(1, 2.5);
		bek1.gehituElementua(2, 5);
		bek1.gehituElementua(4, 1.5);
		bek2.gehituElementua(1, 3);
		bek2.gehituElementua(3, 4.5);
		bek2.gehituElementua(4, 2);
		
		DecimalFormat df = new DecimalFormat("#.0000");
		
		assertTrue(df.format(k1.antzekotasunaKalkulatu(bek1, bek2)).equals(",3146"));
		assertFalse(df.format(k1.antzekotasunaKalkulatu(bek1, bek2)).equals(",5000"));		// asmatutako balioa
		
		
		// Proba2
		
		Bektorea bek3 = new Bektorea();
		Bektorea bek4 = new Bektorea();
		bek3.gehituElementua(100, 0.5);
		bek3.gehituElementua(200, 3);
		bek3.gehituElementua(300, 1.5);
		bek4.gehituElementua(300, 2);
		bek4.gehituElementua(400, 4);
		bek4.gehituElementua(500, 3.5);
				
		assertTrue(df.format(k2.antzekotasunaKalkulatu(bek3, bek4)).equals(",1558"));
		assertFalse(df.format(k2.antzekotasunaKalkulatu(bek3, bek4)).equals(",5000"));		// asmatutako balioa
	
		// Proba3
		
				Bektorea bek5 = new Bektorea();
				Bektorea bek6 = new Bektorea();
						
				assertTrue(k2.antzekotasunaKalkulatu(bek5, bek6) == 0);
				assertFalse(k2.antzekotasunaKalkulatu(bek5, bek6) == 1);	// asmatutako balioa
		
	}

}
