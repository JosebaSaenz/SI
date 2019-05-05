package Probak;

import static org.junit.Assert.*;

import java.text.DecimalFormat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Proiektua.EzaugarriIragaztea;
import Proiektua.GomendioSistema;
import Salbuespenak.ErabiltzaileaEzDaExistitzenException;
import Salbuespenak.KargaMotaEzDaExistitzenException;
import Salbuespenak.PelikulaEzDaExistitzenException;

public class ProbaEzaugarriIragaztea {

	static EzaugarriIragaztea ezi1;
	static EzaugarriIragaztea ezi2;
	static EzaugarriIragaztea ezi3;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ezi1 = new EzaugarriIragaztea();
		ezi2 = new EzaugarriIragaztea();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testEzaugarriIragaztea() {
		assertNotNull(ezi1);
		assertNotNull(ezi2);
		assertNull(ezi3);
	}

	@Test
	public void testBalorazioEstimazioa() {
		GomendioSistema.getGomendioSistema().datuakKargatu(".csv proba");
		
		DecimalFormat df = new DecimalFormat("#.00");
		
		/* 
		
			Probak egiteko "titles-proba.csv", "ratings-proba" eta "tags-proba" fitxategiak erabiliko ditugu.
		
	 		Honako BalorazioenMatrizea sortzen da:
	
	 					p1(11)		p2(12)		p3(13)		p4(14)		p5(22)
	 		e1(1)		4			5						4.5
	 		e2(2)		4.5						4.5						3
	 		e3(3)		4.5			3.5			2.5
	 		e4(4)		3.5									2.5			3
	 		e5(5)					3			4
	 		e6(6)		4						4						4
	 		e7(7)					3						5
	 		e8(8)					3			3			3.5			4.5
	 		e9(9)		4									5			1.5
	 		e10(10)					4			4			5			2.5
	 		
	 		Eta honako KomentarioeMatrizea sortzen da:
	 		
	 					t1(interesgarria)	t2(luzea)	t3(guda)	t4(aspergarria)	t5(disney)	t6(komedia)	t7(amaiera ona)	t8(abentura)
	 		p1(11)		2					1			3
	 		p2(12)		2											1				4
	 		p3(13)																				3			3
	 		p4(14)							2						1							1			2
	 		p5(22)																				1							4
	 	
	 		Eta honako KomentarioeMatrizea (Erabilgarria -> Tf-Idf + bektore unitarioa) sortzen da:
	 		
	 					t1(interesgarria)	t2(luzea)	t3(guda)	t4(aspergarria)	t5(disney)	t6(komedia)	t7(amaiera ona)	t8(abentura)
	 		p1(11)		0.3494				0.1747		0.9206
	 		p2(12)		0.2713										0.1356			0.9529
	 		p3(13)																				0.4870		0.8734
	 		p4(14)							0.6554					0.3277						0.1827		0.6554
	 		p5(22)																				0.0791						0.9969
	 	*/
		
		
		/*	PROBA 1:
		 
			e4 erabiltzaileak p3 pelikulari egingo liokeen balorazioaren estimazioa probatuko dugu:
			
			e4-k ikusi dituen pelikulak (balorazioa >=3.5 izanda): p1
			
			Pertsona Eredua:
						t1		t2		t3		t4		t5		t6		t7		t8
						0.3494	0.1747	0.9206
						
			Produktu Eredua:
						t1		t2		t3		t4		t5		t6		t7		t8
																0.4872	0.8734
																
			Antzekotasuna(pertsonaEredua,produktuEredua) = 0
			
			Estimazioa (nota ajustatu ondoren) = 2.5
			
		*/
		
		double balE4P13 = ezi1.balorazioEstimazioa(4, 13);
		assertTrue(balE4P13 == 2.5);
		assertFalse(balE4P13 == 5);		// asmatutako balioa
	 	
		
		/*	PROBA 2:
		 
			e7 erabiltzaileak p5 pelikulari egingo liokeen balorazioaren estimazioa probatuko dugu:
		
			e7-k ikusi dituen pelikulak (balorazioa >=3.5 izanda): p4
		
			Pertsona Eredua:
					t1		t2		t3		t4		t5		t6		t7		t8
							0.6554			0.3277			0.1827	0.6554
					
			Produktu Eredua:
					t1		t2		t3		t4		t5		t6		t7		t8
															0.0791			0.9969
															
			Antzekotasuna(pertsonaEredua,produktuEredua) = 0.0145
		
			Estimazioa (nota ajustatu ondoren) = 2.5361
		
		 */
	
		String balE7P22 = df.format(ezi1.balorazioEstimazioa(7, 22));
		assertTrue(balE7P22.equals("2,52") || balE7P22.equals("2,53") || balE7P22.equals("2,54"));	// onarpen tarte bat uzten dugu dezimalak direla eta
		assertFalse(balE7P22.equals("1,5"));														// asmatutako balioa
		
		
		/*	PROBA 3:
		 
			e10 erabiltzaileak p1 pelikulari egingo liokeen balorazioaren estimazioa probatuko dugu:
	
			e10-k ikusi dituen pelikulak (balorazioa >=3.5 izanda): p2,p3,p4
	
			Pertsona Eredua:
						t1		t2		t3		t4		t5		t6		t7		t8
			p2 ->		0.2713					0.1356	0.9529
			p3 ->												0.4870	0.8734
			p4 ->				0.6554			0.3277			0.1827	0.6554
			
			Guztira ->	0.2713	0.6554			0.4633	0.9529	0.6697	1.5288
				
			Produktu Eredua:
						t1		t2		t3		t4		t5		t6		t7		t8
						0.3494	0.1747	0.9206
														
			Antzekotasuna(pertsonaEredua,produktuEredua) = 0.0996
	
			Estimazioa (nota ajustatu ondoren) = 2.7491
	
		 */

		String balE10P11 = df.format(ezi1.balorazioEstimazioa(10,11));
		assertTrue(balE10P11.equals("2,73") || balE10P11.equals("2,74") || balE10P11.equals("2,75"));	// onarpen tarte bat uzten dugu dezimalak direla eta
		assertFalse(balE10P11.equals("1,5"));															// asmatutako balioa
		
	}

}
