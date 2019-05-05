package Probak;

import static org.junit.Assert.*;

import java.text.DecimalFormat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Proiektua.ErabiltzaileIragaztea;
import Proiektua.GomendioSistema;
import Proiektua.ProduktuIragaztea;
import Salbuespenak.ErabiltzaileaEzDaExistitzenException;
import Salbuespenak.KargaMotaEzDaExistitzenException;
import Salbuespenak.PelikulaEzDaExistitzenException;

public class ProbaProduktuIragaztea {

	static ProduktuIragaztea pi1;
	static ProduktuIragaztea pi2;
	static ProduktuIragaztea pi3;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		pi1 = new ProduktuIragaztea();
		pi2 = new ProduktuIragaztea();
		
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
	public void testProduktuIragaztea() {
		
		assertNotNull(pi1);
		assertNotNull(pi2);
		assertNull(pi3);

	}

	@Test
	public void testBalorazioEstimazioa() {
		GomendioSistema.getGomendioSistema().datuakKargatu(".csv proba");
		
		DecimalFormat df = new DecimalFormat("#.0");
		
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
	 	
	 	
	 	Honako BalorazioenMatrizea normalizatua (Batezbestekoa erabiliz) sortzen da:
	
	 				p1(11)		p2(12)		p3(13)		p4(14)		p5(22)
	 	e1(1)		-0.5		0.5						0
	 	e2(2)		0.5						0.5						-1
	 	e3(3)		1			0			-1
	 	e4(4)		0.5									-0.5		0
	 	e5(5)					-0.5		0.5
	 	e6(6)		0						0						0
	 	e7(7)					-1						1
	 	e8(8)					-0.5		-0.5		0			1
	 	e9(9)		0.5									1.5			-2
	 	e10(10)					0.125		0.125		1.125		-1.375
	 	
	 	*/
		
		/*	PROBA 1:
		 
		e4 erabiltzaileak p3 pelikulari egingo liokeen balorazioaren estimazioa probatuko dugu:
		
		e4 erabiltzaileak pelikula hauek ikusi ditu: p1, p4, p5
		
		Antzekotasuna(p3,p1) = -0.3991
		Antzekotasuna(p3,p4) = 0.0485
		Antzekotasuna(p3,p5) = -0.3140
		
		Estimazioaren formula aplikatuz, honako balorazioa lortzen dugu: 3.2
		
		 */
	
		String balE4P13 = df.format(pi1.balorazioEstimazioa(4, 13));
		assertTrue(balE4P13.equals("3,1") || balE4P13.equals("3,2") || balE4P13.equals("3,3"));		// onarpen tarte bat uzten dugu dezimalak direla eta
		assertFalse(balE4P13.equals("1,5"));														// asmatutako balioa
	
		/*	PROBA 2:
	 
		e7 erabiltzaileak p5 pelikulari egingo liokeen balorazioaren estimazioa probatuko dugu:
	
		e7 erabiltzaileak pelikula hauek ikusi ditu: p2, p4
	
		Antzekotasuna(p5,p2) = -0.1800
		Antzekotasuna(p5,p4) = -0.7415
	
		Estimazioaren formula aplikatuz, honako balorazioa lortzen dugu: 4.6
	
		 */
	
		String balE7P22 = df.format(pi1.balorazioEstimazioa(7, 22));
		assertTrue(balE7P22.equals("4,5") || balE7P22.equals("4,6") || balE7P22.equals("4,7"));		// onarpen tarte bat uzten dugu dezimalak direla eta
		assertFalse(balE7P22.equals("2,5"));														// asmatutako balioa
		
	}

}
