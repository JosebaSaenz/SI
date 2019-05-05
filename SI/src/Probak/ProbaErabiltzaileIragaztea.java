package Probak;

import static org.junit.Assert.*;

import java.text.DecimalFormat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Proiektua.ErabiltzaileIragaztea;
import Proiektua.Erabiltzailea;
import Proiektua.GomendioSistema;
import Proiektua.Pelikula;
import Proiektua.PelikulaKatalogo;
import Salbuespenak.ErabiltzaileaEzDaExistitzenException;
import Salbuespenak.KargaMotaEzDaExistitzenException;
import Salbuespenak.PelikulaEzDaExistitzenException;

public class ProbaErabiltzaileIragaztea {

	static ErabiltzaileIragaztea ei1;
	static ErabiltzaileIragaztea ei2;
	static ErabiltzaileIragaztea ei3;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		ei1 = new ErabiltzaileIragaztea();
		ei2 = new ErabiltzaileIragaztea();
		
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
	public void testErabiltzaileIragaztea() {
		assertNotNull(ei1);
		assertNotNull(ei2);
		assertNull(ei3);
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
			
			p3 pelikula erabiltzaile hauek ikusi dute: e2, e3, e5, e6, e8, e10
			
			Antzekotasuna(e4,e2) = 0.2887
			Antzekotasuna(e4,e3) = 0.5
			Antzekotasuna(e4,e5) = 0
			Antzekotasuna(e4,e6) = 0
			Antzekotasuna(e4,e8) = 0
			Antzekotasuna(e4,e10) = -0.4456
			
			Estimazioaren formula aplikatuz, honako balorazioa lortzen dugu: 3.5
			
		*/
		
		String balE4P13 = df.format(ei1.balorazioEstimazioa(4, 13));
		assertTrue(balE4P13.equals("3,4") || balE4P13.equals("3,5") || balE4P13.equals("3,6"));		// onarpen tarte bat uzten dugu dezimalak direla eta
		assertFalse(balE4P13.equals("1,5"));														// asmatutako balioa
		
		/*	PROBA 2:
		 
			e7 erabiltzaileak p5 pelikulari egingo liokeen balorazioaren estimazioa probatuko dugu:
		
			p3 pelikula erabiltzaile hauek ikusi dute: e2, e4, e6, e8, e9, e10
		
			Antzekotasuna(e7,e2) = 0
			Antzekotasuna(e7,e4) = -0.5
			Antzekotasuna(e7,e6) = 0
			Antzekotasuna(e7,e8) = 0.3162
			Antzekotasuna(e7,e9) = 0.4160
			Antzekotasuna(e7,e10) = 0.3961
		
			Estimazioaren formula aplikatuz, honako balorazioa lortzen dugu: 2.7864
		
	*/
		
		String balE7P22 = df.format(ei1.balorazioEstimazioa(7, 22));
		assertTrue(balE7P22.equals("2,6") || balE7P22.equals("2,7") || balE7P22.equals("2,8"));		// onarpen tarte bat uzten dugu dezimalak direla eta
		assertFalse(balE7P22.equals("4,5"));														// asmatutako balioa
		
	}

}
