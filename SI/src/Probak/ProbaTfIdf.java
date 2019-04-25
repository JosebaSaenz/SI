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
import Proiektua.TfIdf;


public class ProbaTfIdf {

	static Pelikula p1;
	static Pelikula p2;
	static Pelikula p3;
	static Pelikula p4;
	
	static TfIdf t1;
	static TfIdf t2;
	static TfIdf t3;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		t1 = new TfIdf();
		t2 = new TfIdf();
		
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
	 	
	 	*/
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testTfIdf() {
		assertNotNull(t1);
		assertNotNull(t2);
		assertNull(t3);
	}

	@Test
	public void testErabilgarritasunMatrizeaBete() {

		Bektorea[] matErabilgarria = t1.erabilgarritasunMatrizeaBete(KomentarioenMatrizea.getKomentarioenMatrizea().getMatrizea());
				
		DecimalFormat df = new DecimalFormat("#.0000");
				
		assertTrue(df.format(matErabilgarria[0].getBalioa(1)).equals("1,0000"));
		assertTrue(df.format(matErabilgarria[1].getBalioa(1)).equals(",5345"));
		assertTrue(df.format(matErabilgarria[1].getBalioa(2)).equals(",2673"));
		assertTrue(df.format(matErabilgarria[1].getBalioa(3)).equals(",8018"));
		assertTrue(df.format(matErabilgarria[2].getBalioa(4)).equals("1,0000"));
		assertTrue(df.format(matErabilgarria[3].getBalioa(2)).equals(",7071"));
		assertTrue(df.format(matErabilgarria[3].getBalioa(3)).equals(",7071"));

	}
}
