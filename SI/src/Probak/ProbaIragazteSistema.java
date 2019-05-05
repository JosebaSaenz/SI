package Probak;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Proiektua.GomendioSistema;
import Proiektua.IragazteSistema;

public class ProbaIragazteSistema {

	
	@Test
	public void testGetIragazteSistema() {
		assertNotNull(IragazteSistema.getIragazteSistema());
	}

	@Test
	public void testGomendatu() {
		
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
		
		
		/*	PROBA 1
		 	
		 	e4 erabiltzaileari egiten zaion gomendioa frogatu nahi dugu
		 	
		 	e4 erabiltzaileak ez ditu p2 eta p3 pelikulak ikusi; beraz, gomendioa horien gainean egingo da
		 	
		 	Lehenengo e4 erabiltzaileak p2 pelikulari egingo liokeen balorazioa estimatuko dugu (hiru iragazte teknikak konbinatuz):
		 		
		 		ERABILTZAILE IRAGAZTEA:
		 		
		 		e4 erabiltzaileak p2 pelikulari egingo liokeen balorazioaren estimazioa egingo dugu:
			
				p2 pelikula erabiltzaile hauek ikusi dute: e1, e3, e5, e7, e8, e10
			
				Antzekotasuna(e4,e1) = -0.5
				Antzekotasuna(e4,e3) = 0.5
				Antzekotasuna(e4,e5) = 0
				Antzekotasuna(e4,e7) = -0.5
				Antzekotasuna(e4,e8) = 0
				Antzekotasuna(e4,e10) = -0.4456
			
				Estimazioaren formula aplikatuz, honako balorazioa lortzen dugu: 3.8715
				
				
				PRODUKTU IRAGAZTEA:
				
				e4 erabiltzaileak p2 pelikulari egingo liokeen balorazioaren estimazioa egingo dugu:
		
				e4 erabiltzaileak pelikula hauek ikusi ditu: p1, p4, p5
		
				Antzekotasuna(p2,p1) = -0.1330
				Antzekotasuna(p2,p4) = -0.2962
				Antzekotasuna(p2,p5) = -0.1800
		
				Estimazioaren formula aplikatuz, honako balorazioa lortzen dugu: 2.8661
				
				
				EZAUGARRI IRAGAZTEA:
				
				e4 erabiltzaileak p2 pelikulari egingo liokeen balorazioaren estimazioa egingo dugu:
			
				e4-k ikusi dituen pelikulak (balorazioa >=3.5 izanda): p1
			
				Pertsona Eredua:
							t1		t2		t3		t4		t5		t6		t7		t8
							0.3494	0.1747	0.9206
						
				Produktu Eredua:
							t1		t2		t3		t4		t5		t6		t7		t8
							0.2713					0.1356	0.9529
																
				Antzekotasuna(pertsonaEredua,produktuEredua) = 0.0947868
			
				Estimazioa (nota ajustatu ondoren) = 2.7370
				
				
				HIRU IRAGAZTE TEKNIKAK KONBINATUZ:
				
				Estimazioa = erab * 0.25 + prod * 0.25 + ezau * 0.5 = 3.8715 * 0.25 + 2.8661 * 0.25 + 2.7370 * 0.5 = 3.0529
				
				
			Bigarrenik, e4 erabiltzaileak p3 pelikulari egingo liokeen balorazioa estimatuko dugu (hiru iragazte teknikak konbinatuz):
			
				ERABILTZAILE IRAGAZTEA:
				
				e4 erabiltzaileak p3 pelikulari egingo liokeen balorazioaren estimazioa egingo dugu:
			
				p3 pelikula erabiltzaile hauek ikusi dute: e2, e3, e5, e6, e8, e10
			
				Antzekotasuna(e4,e2) = 0.2887
				Antzekotasuna(e4,e3) = 0.5
				Antzekotasuna(e4,e5) = 0
				Antzekotasuna(e4,e6) = 0
				Antzekotasuna(e4,e8) = 0
				Antzekotasuna(e4,e10) = -0.4456
			
				Estimazioaren formula aplikatuz, honako balorazioa lortzen dugu: 3.5
				
				
				PRODUKTU IRAGAZTEA:
				
				e4 erabiltzaileak p3 pelikulari egingo liokeen balorazioaren estimazioa egingo dugu:
		
				e4 erabiltzaileak pelikula hauek ikusi ditu: p1, p4, p5
		
				Antzekotasuna(p3,p1) = -0.3991
				Antzekotasuna(p3,p4) = 0.0485
				Antzekotasuna(p3,p5) = -0.3140
		
				Estimazioaren formula aplikatuz, honako balorazioa lortzen dugu: 3.2
				
				
				EZAUGARRI IRAGAZTEA:
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
				HIRU IRAGAZTE TEKNIKAK KONBINATUZ:
				
				Estimazioa = erab * 0.25 + prod * 0.25 + ezau * 0.5 = 3.5 * 0.25 + 3.2 * 0.25 + 2.7370 * 2.5 = 2.9250
				
				
			e4 erabiltzaileak p2 pelikulari 3.0529 emango lioke
			e4 erabiltzaileak p3 pelikulari 2.9250 emango lioke
			
			Beraz, gomendioan pelikulak orden honetan agertuko dira:
				1. p2 pelikula -> Finding Nemo (2003)
				2. p3 pelikula -> Forrest Gump (1994)
		 
		*/
		
		GomendioSistema.getGomendioSistema().datuakKargatu(".csv proba");
		ArrayList<String> gomendioa = new ArrayList<String>();
		gomendioa  = IragazteSistema.getIragazteSistema().gomendatu(4);
	
		assertEquals(gomendioa.get(0),"Finding Nemo (2003)");
		assertEquals(gomendioa.get(1),"Forrest Gump (1994)");
		
		assertNotEquals(gomendioa.get(0),"Forrest Gump (1994)");
		assertNotEquals(gomendioa.get(1),"Finding Nemo (2003)");
	}
}