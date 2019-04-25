package Probak;

import static org.junit.Assert.*;

import java.text.DecimalFormat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Proiektua.Bektorea;
import Proiektua.KomentarioenMatrizea;
import Proiektua.Pelikula;
import Proiektua.PelikulaKatalogo;

public class ProbaKomentarioenMatrizea {

	static Pelikula p1;
	static Pelikula p2;
	static Pelikula p3;
	static Pelikula p4;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		p1 = new Pelikula(100,"Avatar");
		p2 = new Pelikula(200,"ToyStory");
		p3 = new Pelikula(300,"Titanic");
		p4 = new Pelikula(400,"Superman");
		
		p1.komentarioaErabiltzaileaGabeGehitu("interesgarria", 1);
		p2.komentarioaErabiltzaileaGabeGehitu("interesgarria", 1);
		p2.komentarioaErabiltzaileaGabeGehitu("interesgarria", 1);
		p2.komentarioaErabiltzaileaGabeGehitu("aspergarria", 2);
		p2.komentarioaErabiltzaileaGabeGehitu("luzea", 3);
		p2.komentarioaErabiltzaileaGabeGehitu("luzea", 3);
		p2.komentarioaErabiltzaileaGabeGehitu("luzea", 3);
		p3.komentarioaErabiltzaileaGabeGehitu("komedia", 4);
		p3.komentarioaErabiltzaileaGabeGehitu("komedia", 4);
		p4.komentarioaErabiltzaileaGabeGehitu("aspergarria", 2);
		p4.komentarioaErabiltzaileaGabeGehitu("luzea", 3);
		
		PelikulaKatalogo.getPelikulaKatalogo().gehituPelikula(p1.getPelikulaId(), p1);
		PelikulaKatalogo.getPelikulaKatalogo().gehituPelikula(p2.getPelikulaId(), p2);
		PelikulaKatalogo.getPelikulaKatalogo().gehituPelikula(p3.getPelikulaId(), p3);
		PelikulaKatalogo.getPelikulaKatalogo().gehituPelikula(p4.getPelikulaId(), p4);
		
		/* 
		
	 	Honako KomentarioenMatrizea sortuko dugu probak egiteko:
	
	 			t1		t2		t3		t4
	 	p1		1						
	 	p2		2		1		3
	 	p3								2
	 	p4				1		1
	 	
	 	
	 	Honako KomentarioenMatrizea erabilgarria (Tf-Idf eta bektore unitarioa) izango dugu:
	
	 			t1		t2		t3		t4
	 	p1		1						
	 	p2		0.53	0.27	0.80
	 	p3								1
	 	p4				0.71	0.71
	 	*/
		
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
	public void testGetKomentarioenMatrizea() {
		assertNotNull(KomentarioenMatrizea.getKomentarioenMatrizea());
	}

	@Test
	public void testZenbatPelikuletan() {
		assertTrue(KomentarioenMatrizea.getKomentarioenMatrizea().zenbatPelikuletan(1) == 2);
		assertTrue(KomentarioenMatrizea.getKomentarioenMatrizea().zenbatPelikuletan(2) == 2);
		assertTrue(KomentarioenMatrizea.getKomentarioenMatrizea().zenbatPelikuletan(3) == 2);
		assertTrue(KomentarioenMatrizea.getKomentarioenMatrizea().zenbatPelikuletan(4) == 1);
		
		// asmatutako balioak
		assertFalse(KomentarioenMatrizea.getKomentarioenMatrizea().zenbatPelikuletan(1) == 3);
		assertFalse(KomentarioenMatrizea.getKomentarioenMatrizea().zenbatPelikuletan(2) == 1);
		assertFalse(KomentarioenMatrizea.getKomentarioenMatrizea().zenbatPelikuletan(3) == 4);
		assertFalse(KomentarioenMatrizea.getKomentarioenMatrizea().zenbatPelikuletan(4) == 2);

	}

	@Test
	public void testGetPeliKomentarioak() {
		
		// p1 pelikulan dagoen komentario bakoitzaren IDa eta  errepikatzen den aldi kopurua itzuli
		Bektorea bek = KomentarioenMatrizea.getKomentarioenMatrizea().getPeliKomentarioak(p1.getPelikulaId());
		assertTrue(bek.luzera() == 1);
		assertTrue(bek.getBalioa(1) == 1);
		assertFalse(bek.bektoreanDago(2));
		
		// p2 pelikulan dagoen komentario bakoitzaren IDa eta  errepikatzen den aldi kopurua itzuli
		bek = KomentarioenMatrizea.getKomentarioenMatrizea().getPeliKomentarioak(p2.getPelikulaId());
		assertTrue(bek.luzera() == 3);
		assertTrue(bek.getBalioa(1) == 2);
		assertTrue(bek.getBalioa(2) == 1);
		assertTrue(bek.getBalioa(3) == 3);
		assertFalse(bek.bektoreanDago(4));
		
		// p3 pelikulan dagoen komentario bakoitzaren IDa eta  errepikatzen den aldi kopurua itzuli
		bek = KomentarioenMatrizea.getKomentarioenMatrizea().getPeliKomentarioak(p3.getPelikulaId());
		assertTrue(bek.luzera() == 1);
		assertTrue(bek.getBalioa(4) == 2);
		assertFalse(bek.bektoreanDago(2));
		
		// p4 pelikulan dagoen komentario bakoitzaren IDa eta  errepikatzen den aldi kopurua itzuli
		bek = KomentarioenMatrizea.getKomentarioenMatrizea().getPeliKomentarioak(p4.getPelikulaId());
		assertTrue(bek.luzera() == 2);
		assertTrue(bek.getBalioa(2) == 1);
		assertTrue(bek.getBalioa(3) == 1);
		assertFalse(bek.bektoreanDago(4));
	}

	@Test
	public void testGetPeliKomentarioErabilgarriak() {
		
		// Balio erabilgarria Tf-Idf tekina erabiliz eta ondoren bektore unitarioa bihurtuz lortzen da
		
		DecimalFormat df = new DecimalFormat("#.0000");
		
		// p1 pelikulan dagoen komentario bakoitzaren IDa eta  bere balio erabilgarria itzuli
		Bektorea bek = KomentarioenMatrizea.getKomentarioenMatrizea().getPeliKomentarioErabilgarriak(p1.getPelikulaId());
		assertTrue(bek.luzera() == 1);
		assertTrue(bek.getBalioa(1) == 1);
		assertFalse(bek.bektoreanDago(2));
		
		// p2 pelikulan dagoen komentario bakoitzaren IDa eta  bere balio erabilgarria itzuli
		bek = KomentarioenMatrizea.getKomentarioenMatrizea().getPeliKomentarioErabilgarriak(p2.getPelikulaId());
		assertTrue(bek.luzera() == 3);
		assertTrue(df.format(bek.getBalioa(1)).equals(",5345"));
		assertTrue(df.format(bek.getBalioa(2)).equals(",2673"));
		assertTrue(df.format(bek.getBalioa(3)).equals(",8018"));
		assertFalse(bek.bektoreanDago(4));
		
		// p3 pelikulan dagoen komentario bakoitzaren IDa eta  bere balio erabilgarria itzuli
		bek = KomentarioenMatrizea.getKomentarioenMatrizea().getPeliKomentarioErabilgarriak(p3.getPelikulaId());
		assertTrue(bek.luzera() == 1);
		assertTrue(bek.getBalioa(4) == 1);
		assertFalse(bek.bektoreanDago(2));
		
		// p4 pelikulan dagoen komentario bakoitzaren IDa eta  bere balio erabilgarria itzuli
		bek = KomentarioenMatrizea.getKomentarioenMatrizea().getPeliKomentarioErabilgarriak(p4.getPelikulaId());
		assertTrue(bek.luzera() == 2);
		assertTrue(df.format(bek.getBalioa(2)).equals(",7071"));
		assertTrue(df.format(bek.getBalioa(3)).equals(",7071"));
		assertFalse(bek.bektoreanDago(4));
		
	}

}
