package Probak;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Proiektua.BalorazioenMatrizea;
import Proiektua.Bektorea;
import Proiektua.Erabiltzailea;
import Proiektua.GomendioSistema;
import Proiektua.Pelikula;
import Proiektua.PelikulaKatalogo;

public class ProbaBalorazioenMatrizea {

	static Erabiltzailea e1;
	static Erabiltzailea e2;
	static Erabiltzailea e3;
	static Erabiltzailea e4;
	
	static Pelikula p1;
	static Pelikula p2;
	static Pelikula p3;
	static Pelikula p4;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		e1 = new Erabiltzailea(1);
		e2 = new Erabiltzailea(2);
		e3 = new Erabiltzailea(3);
		e4 = new Erabiltzailea(4);
		
		p1 = new Pelikula(100,"Avatar");
		p2 = new Pelikula(200,"ToyStory");
		p3 = new Pelikula(300,"Titanic");
		p4 = new Pelikula(400,"Superman");
		
		e1.pelikulaGehitu(p1.getPelikulaId(), p1);
		e1.pelikulaGehitu(p2.getPelikulaId(), p2);
		e1.pelikulaGehitu(p4.getPelikulaId(), p4);
		e2.pelikulaGehitu(p2.getPelikulaId(), p2);
		e2.pelikulaGehitu(p3.getPelikulaId(), p3);
		e3.pelikulaGehitu(p1.getPelikulaId(), p1);
		e3.pelikulaGehitu(p2.getPelikulaId(), p2);
		e3.pelikulaGehitu(p3.getPelikulaId(), p3);
		e3.pelikulaGehitu(p4.getPelikulaId(), p4);
		e4.pelikulaGehitu(p2.getPelikulaId(), p2);
		
		p1.balorazioaGehitu(e1.getId(), 4);
		p2.balorazioaGehitu(e1.getId(), 3);
		p4.balorazioaGehitu(e1.getId(), 3.5);
		p2.balorazioaGehitu(e2.getId(), 2);
		p3.balorazioaGehitu(e2.getId(), 1);
		p1.balorazioaGehitu(e3.getId(), 0.5);
		p2.balorazioaGehitu(e3.getId(), 3.5);
		p3.balorazioaGehitu(e3.getId(), 4);
		p4.balorazioaGehitu(e3.getId(), 5);
		p2.balorazioaGehitu(e4.getId(), 3.5);
		
		GomendioSistema.getGomendioSistema().gehituErabiltzailea(e1.getId(), e1);
		GomendioSistema.getGomendioSistema().gehituErabiltzailea(e2.getId(), e2);
		GomendioSistema.getGomendioSistema().gehituErabiltzailea(e3.getId(), e3);
		GomendioSistema.getGomendioSistema().gehituErabiltzailea(e4.getId(), e4);
		
		PelikulaKatalogo.getPelikulaKatalogo().gehituPelikula(p1.getPelikulaId(), p1);
		PelikulaKatalogo.getPelikulaKatalogo().gehituPelikula(p2.getPelikulaId(), p2);
		PelikulaKatalogo.getPelikulaKatalogo().gehituPelikula(p3.getPelikulaId(), p3);
		PelikulaKatalogo.getPelikulaKatalogo().gehituPelikula(p4.getPelikulaId(), p4);
		
		/* 
		 	Honako BalorazioenMatrizea sortuko dugu probak egiteko:
		
		 			p1		p2		p3		p4
		 	e1		4		3				3.5
		 	e2				2		1
		 	e3		0.5		3.5		4		5
		 	e4				3.5
		 	
		 	
		 	Honako BalorazioenMatrizea normalizatua (Batezbestekoa erabiliz) izango dugu:
		
		 			p1		p2		p3		p4
		 	e1		0.5		-0.5			0
		 	e2				0.5		-0.5
		 	e3		-2.75	0.25	0.75	1.75
		 	e4				0
		 	
		 	Honako BalorazioenMatrizea normalizatua (Zscore erabiliz) izango dugu:
		
		 			p1		p2		p3		p4
		 	e1		3		-3				0
		 	e2				2		-2
		 	e3		-0.98	0.09	0.27	1.78
		 	e4				0
		 	
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
	public void testGetBalorazioenMatrizea() {
		assertNotNull(BalorazioenMatrizea.getBalorazioenMatrizea());
	}

	@Test
	public void testPelikulaBaloratuDutenErabiltzaileenZerrenda() {
		
		// p1 pelikula baloratu duten erabiltzaileen ID-ak itzuli
		ArrayList<Integer> zer = BalorazioenMatrizea.getBalorazioenMatrizea().pelikulaBaloratuDutenErabiltzaileenZerrenda(p1.getPelikulaId());
		assertTrue(zer.size() == 2);
		assertTrue(zer.contains(e1.getId()));
		assertTrue(zer.contains(e3.getId()));
		assertFalse(zer.contains(e2.getId()));
		
		// p2 pelikula baloratu duten erabiltzaileen ID-ak itzuli
		zer = BalorazioenMatrizea.getBalorazioenMatrizea().pelikulaBaloratuDutenErabiltzaileenZerrenda(p2.getPelikulaId());
		assertTrue(zer.size() == 4);
		assertTrue(zer.contains(e1.getId()));
		assertTrue(zer.contains(e2.getId()));
		assertTrue(zer.contains(e3.getId()));
		assertTrue(zer.contains(e4.getId()));
		
		// p3 pelikula baloratu duten erabiltzaileen ID-ak itzuli
		zer = BalorazioenMatrizea.getBalorazioenMatrizea().pelikulaBaloratuDutenErabiltzaileenZerrenda(p3.getPelikulaId());
		assertTrue(zer.size() == 2);
		assertTrue(zer.contains(e2.getId()));
		assertTrue(zer.contains(e3.getId()));
		assertFalse(zer.contains(e1.getId()));
		
		// p4 pelikula baloratu duten erabiltzaileen ID-ak itzuli
		zer = BalorazioenMatrizea.getBalorazioenMatrizea().pelikulaBaloratuDutenErabiltzaileenZerrenda(p4.getPelikulaId());
		assertTrue(zer.size() == 2);
		assertTrue(zer.contains(e1.getId()));
		assertTrue(zer.contains(e3.getId()));
		assertFalse(zer.contains(e4.getId()));
		
	}

	@Test
	public void testErabiltzaileakBaloratuDituenPelikulenZerrenda() {
		
		// e1 erabiltzaileak baloratu dituen pelikulen ID-ak itzuli
		ArrayList<Integer> zer = BalorazioenMatrizea.getBalorazioenMatrizea().erabiltzaileakBaloratuDituenPelikulenZerrenda(e1.getId());
		assertTrue(zer.size() == 3);
		assertTrue(zer.contains(p1.getPelikulaId()));
		assertTrue(zer.contains(p2.getPelikulaId()));
		assertTrue(zer.contains(p4.getPelikulaId()));
		assertFalse(zer.contains(p3.getPelikulaId()));
		
		// e2 erabiltzaileak baloratu dituen pelikulen ID-ak itzuli
		zer = BalorazioenMatrizea.getBalorazioenMatrizea().erabiltzaileakBaloratuDituenPelikulenZerrenda(e2.getId());
		assertTrue(zer.size() == 2);
		assertTrue(zer.contains(p2.getPelikulaId()));
		assertTrue(zer.contains(p3.getPelikulaId()));
		assertFalse(zer.contains(p1.getPelikulaId()));
		
		// e3 erabiltzaileak baloratu dituen pelikulen ID-ak itzuli
		zer = BalorazioenMatrizea.getBalorazioenMatrizea().erabiltzaileakBaloratuDituenPelikulenZerrenda(e3.getId());
		assertTrue(zer.size() == 4);
		assertTrue(zer.contains(p1.getPelikulaId()));
		assertTrue(zer.contains(p2.getPelikulaId()));
		assertTrue(zer.contains(p3.getPelikulaId()));
		assertTrue(zer.contains(p4.getPelikulaId()));
		
		// e2 erabiltzaileak baloratu dituen pelikulen ID-ak itzuli
		zer = BalorazioenMatrizea.getBalorazioenMatrizea().erabiltzaileakBaloratuDituenPelikulenZerrenda(e4.getId());
		assertTrue(zer.size() == 1);
		assertTrue(zer.contains(p2.getPelikulaId()));
		assertFalse(zer.contains(p1.getPelikulaId()));
		
	}

	@Test
	public void testGetErabBalorazioak() {
		
		// e1 erabiltzaileak baloratu dituen pelikulen ID-ak eta balorazioak itzuli
		Bektorea bek = BalorazioenMatrizea.getBalorazioenMatrizea().getErabBalorazioak(e1.getId());
		assertTrue(bek.luzera() == 3);
		assertTrue(bek.getBalioa(p1.getPelikulaId()) == 4);
		assertTrue(bek.getBalioa(p2.getPelikulaId()) == 3);
		assertTrue(bek.getBalioa(p4.getPelikulaId()) == 3.5);
		assertFalse(bek.bektoreanDago(p3.getPelikulaId()));
		
		// e2 erabiltzaileak baloratu dituen pelikulen ID-ak eta balorazioak itzuli
		bek = BalorazioenMatrizea.getBalorazioenMatrizea().getErabBalorazioak(e2.getId());
		assertTrue(bek.luzera() == 2);
		assertTrue(bek.getBalioa(p2.getPelikulaId()) == 2);
		assertTrue(bek.getBalioa(p3.getPelikulaId()) == 1);
		assertFalse(bek.bektoreanDago(p1.getPelikulaId()));
		
		// e3 erabiltzaileak baloratu dituen pelikulen ID-ak eta balorazioak itzuli
		bek = BalorazioenMatrizea.getBalorazioenMatrizea().getErabBalorazioak(e3.getId());
		assertTrue(bek.luzera() == 4);
		assertTrue(bek.getBalioa(p1.getPelikulaId()) == 0.5);
		assertTrue(bek.getBalioa(p2.getPelikulaId()) == 3.5);
		assertTrue(bek.getBalioa(p3.getPelikulaId()) == 4);
		assertTrue(bek.getBalioa(p4.getPelikulaId()) == 5);
		
		// e4 erabiltzaileak baloratu dituen pelikulen ID-ak eta balorazioak itzuli
		bek = BalorazioenMatrizea.getBalorazioenMatrizea().getErabBalorazioak(e4.getId());
		assertTrue(bek.luzera() == 1);
		assertTrue(bek.getBalioa(p2.getPelikulaId()) == 3.5);
		assertFalse(bek.bektoreanDago(p1.getPelikulaId()));
		
	}

	@Test
	public void testGetErabBalorazioNormalizatuak() {

		// Normalizazioa Batezbestekoa erabiliz egiten dugu
		
		// e1 erabiltzaileak baloratu dituen pelikulen ID-ak eta balorazio normalizatuak (Batezbestekoa erabiliz) itzuli
		Bektorea bek = BalorazioenMatrizea.getBalorazioenMatrizea().getErabBalorazioNormalizatuak(e1.getId());
		assertTrue(bek.luzera() == 3);
		assertTrue(bek.getBalioa(p1.getPelikulaId()) == 0.5);
		assertTrue(bek.getBalioa(p2.getPelikulaId()) == -0.5);
		assertTrue(bek.getBalioa(p4.getPelikulaId()) == 0);
		assertFalse(bek.bektoreanDago(p3.getPelikulaId()));
		
		// e2 erabiltzaileak baloratu dituen pelikulen ID-ak eta balorazio normalizatuak (Batezbestekoa erabiliz) itzuli
		bek = BalorazioenMatrizea.getBalorazioenMatrizea().getErabBalorazioNormalizatuak(e2.getId());
		assertTrue(bek.luzera() == 2);
		assertTrue(bek.getBalioa(p2.getPelikulaId()) == 0.5);
		assertTrue(bek.getBalioa(p3.getPelikulaId()) == -0.5);
		assertFalse(bek.bektoreanDago(p1.getPelikulaId()));
		
		// e3 erabiltzaileak baloratu dituen pelikulen ID-ak eta balorazio normalizatuak (Batezbestekoa erabiliz) itzuli
		bek = BalorazioenMatrizea.getBalorazioenMatrizea().getErabBalorazioNormalizatuak(e3.getId());
		assertTrue(bek.luzera() == 4);
		assertTrue(bek.getBalioa(p1.getPelikulaId()) == -2.75);
		assertTrue(bek.getBalioa(p2.getPelikulaId()) == 0.25);
		assertTrue(bek.getBalioa(p3.getPelikulaId()) == 0.75);
		assertTrue(bek.getBalioa(p4.getPelikulaId()) == 1.75);
		
		// e4 erabiltzaileak baloratu dituen pelikulen ID-ak eta balorazio normalizatuak (Batezbestekoa erabiliz) itzuli
		bek = BalorazioenMatrizea.getBalorazioenMatrizea().getErabBalorazioNormalizatuak(e4.getId());
		assertTrue(bek.luzera() == 1);
		assertTrue(bek.getBalioa(p2.getPelikulaId()) == 0);
		assertFalse(bek.bektoreanDago(p1.getPelikulaId()));
		
		
		/*
		// Normalizazioa Zsore erabiliz egiten dugu
		
		DecimalFormat df = new DecimalFormat("#.0000");
		
		// e1 erabiltzaileak baloratu dituen pelikulen ID-ak eta balorazio normalizatuak (Zscore erabiliz) itzuli
		Bektorea bek = BalorazioenMatrizea.getBalorazioenMatrizea().getErabBalorazioNormalizatuak(e1.getId());
		assertTrue(bek.luzera() == 3);
		assertTrue(df.format(bek.getBalioa(p1.getPelikulaId())).equals("1,2247"));
		assertTrue(df.format(bek.getBalioa(p2.getPelikulaId())).equals("-1,2247"));
		assertTrue(bek.getBalioa(p4.getPelikulaId()) == 0);
		assertFalse(bek.bektoreanDago(p3.getPelikulaId()));
		
		// e2 erabiltzaileak baloratu dituen pelikulen ID-ak eta balorazio normalizatuak (Zscore erabiliz) itzuli
		bek = BalorazioenMatrizea.getBalorazioenMatrizea().getErabBalorazioNormalizatuak(e2.getId());
		assertTrue(bek.luzera() == 2);
		assertTrue(bek.getBalioa(p2.getPelikulaId()) == 1);
		assertTrue(bek.getBalioa(p3.getPelikulaId()) == -1);
		assertFalse(bek.bektoreanDago(p1.getPelikulaId()));
		
		// e3 erabiltzaileak baloratu dituen pelikulen ID-ak eta balorazio normalizatuak (Zscore erabiliz) itzuli
		bek = BalorazioenMatrizea.getBalorazioenMatrizea().getErabBalorazioNormalizatuak(e3.getId());
		assertTrue(bek.luzera() == 4);
		assertTrue(df.format(bek.getBalioa(p1.getPelikulaId())).equals("-1,6398"));
		assertTrue(df.format(bek.getBalioa(p2.getPelikulaId())).equals(",1491"));
		assertTrue(df.format(bek.getBalioa(p3.getPelikulaId())).equals(",4472"));
		assertTrue(df.format(bek.getBalioa(p4.getPelikulaId())).equals("1,0435"));
		
		// e4 erabiltzaileak baloratu dituen pelikulen ID-ak eta balorazio normalizatuak (Zscore erabiliz) itzuli
		bek = BalorazioenMatrizea.getBalorazioenMatrizea().getErabBalorazioNormalizatuak(e4.getId());
		assertTrue(bek.luzera() == 1);
		assertTrue(bek.getBalioa(p2.getPelikulaId()) == 0);
		assertFalse(bek.bektoreanDago(p1.getPelikulaId()));
		*/
	}

	@Test
	public void testGetPeliBalorazioak() {
		
		// p1 pelikula baloratu duten erabiltzaileen ID-ak eta balorazioak itzuli
		Bektorea bek = BalorazioenMatrizea.getBalorazioenMatrizea().getPeliBalorazioak(p1.getPelikulaId());
		assertTrue(bek.luzera() == 2);
		assertTrue(bek.getBalioa(e1.getId()) == 4);
		assertTrue(bek.getBalioa(e3.getId()) == 0.5);
		assertFalse(bek.bektoreanDago(e2.getId()));
		
		// p2 pelikula baloratu duten erabiltzaileen ID-ak eta balorazioak itzuli
		bek = BalorazioenMatrizea.getBalorazioenMatrizea().getPeliBalorazioak(p2.getPelikulaId());
		assertTrue(bek.luzera() == 4);
		assertTrue(bek.getBalioa(e1.getId()) == 3);
		assertTrue(bek.getBalioa(e2.getId()) == 2);
		assertTrue(bek.getBalioa(e3.getId()) == 3.5);
		assertTrue(bek.getBalioa(e4.getId()) == 3.5);
		
		// p3 pelikula baloratu duten erabiltzaileen ID-ak eta balorazioak itzuli
		bek = BalorazioenMatrizea.getBalorazioenMatrizea().getPeliBalorazioak(p3.getPelikulaId());
		assertTrue(bek.luzera() == 2);
		assertTrue(bek.getBalioa(e2.getId()) == 1);
		assertTrue(bek.getBalioa(e3.getId()) == 4);
		assertFalse(bek.bektoreanDago(e1.getId()));
		
		// p4 pelikula baloratu duten erabiltzaileen ID-ak eta balorazioak itzuli
		bek = BalorazioenMatrizea.getBalorazioenMatrizea().getPeliBalorazioak(p4.getPelikulaId());
		assertTrue(bek.luzera() == 2);
		assertTrue(bek.getBalioa(e1.getId()) == 3.5);
		assertTrue(bek.getBalioa(e3.getId()) == 5);
		assertFalse(bek.bektoreanDago(e2.getId()));

	}

	@Test
	public void testGetPeliBalorazioNormalizatuak() {
		
		// Normalizazioa Batezbestekoa erabiliz egiten dugu
				
		// p1 pelikula baloratu duten erabiltzaileen ID-ak eta balorazio normalizautak (Baztezbestekoa erabiliz) itzuli
		Bektorea bek = BalorazioenMatrizea.getBalorazioenMatrizea().getPeliBalorazioNormalizatuak(p1.getPelikulaId());
		assertTrue(bek.luzera() == 2);
		assertTrue(bek.getBalioa(e1.getId()) == 0.5);
		assertTrue(bek.getBalioa(e3.getId()) == -2.75);
		assertFalse(bek.bektoreanDago(e2.getId()));
		
		// p2 pelikula baloratu duten erabiltzaileen ID-ak eta balorazio normalizautak (Baztezbestekoa erabiliz) itzuli
		bek = BalorazioenMatrizea.getBalorazioenMatrizea().getPeliBalorazioNormalizatuak(p2.getPelikulaId());
		assertTrue(bek.luzera() == 4);
		assertTrue(bek.getBalioa(e1.getId()) == -0.5);
		assertTrue(bek.getBalioa(e2.getId()) == 0.5);
		assertTrue(bek.getBalioa(e3.getId()) == 0.25);
		assertTrue(bek.getBalioa(e4.getId()) == 0);
		
		// p3 pelikula baloratu duten erabiltzaileen ID-ak eta balorazio normalizautak (Baztezbestekoa erabiliz) itzuli
		bek = BalorazioenMatrizea.getBalorazioenMatrizea().getPeliBalorazioNormalizatuak(p3.getPelikulaId());
		assertTrue(bek.luzera() == 2);
		assertTrue(bek.getBalioa(e2.getId()) == -0.5);
		assertTrue(bek.getBalioa(e3.getId()) == 0.75);
		assertFalse(bek.bektoreanDago(e1.getId()));
		
		// p4 pelikula baloratu duten erabiltzaileen ID-ak eta balorazio normalizautak (Baztezbestekoa erabiliz) itzuli
		bek = BalorazioenMatrizea.getBalorazioenMatrizea().getPeliBalorazioNormalizatuak(p4.getPelikulaId());
		assertTrue(bek.luzera() == 2);
		assertTrue(bek.getBalioa(e1.getId()) == 0);
		assertTrue(bek.getBalioa(e3.getId()) == 1.75);
		assertFalse(bek.bektoreanDago(e2.getId()));
		
		
		
		/*
		// Normalizazioa Zsore erabiliz egiten dugu
		
		DecimalFormat df = new DecimalFormat("#.0000");
		
		// p1 pelikula baloratu duten erabiltzaileen ID-ak eta balorazio normalizautak (Zscore erabiliz) itzuli
		Bektorea bek = BalorazioenMatrizea.getBalorazioenMatrizea().getPeliBalorazioNormalizatuak(p1.getPelikulaId());
		assertTrue(bek.luzera() == 2);
		assertTrue(df.format(bek.getBalioa(e1.getId())).equals("1,2247"));
		assertTrue(df.format(bek.getBalioa(e3.getId())).equals("-1,6398"));
		assertFalse(bek.bektoreanDago(e2.getId()));
		
		// p2 pelikula baloratu duten erabiltzaileen ID-ak eta balorazio normalizautak (Zscore erabiliz) itzuli
		bek = BalorazioenMatrizea.getBalorazioenMatrizea().getPeliBalorazioNormalizatuak(p2.getPelikulaId());
		assertTrue(bek.luzera() == 4);
		assertTrue(df.format(bek.getBalioa(e1.getId())).equals("-1,2247"));
		assertTrue(bek.getBalioa(e2.getId()) == 1);
		assertTrue(df.format(bek.getBalioa(e3.getId())).equals(",1491"));
		assertTrue(bek.getBalioa(e4.getId()) == 0);
		
		// p3 pelikula baloratu duten erabiltzaileen ID-ak eta balorazio normalizautak (Zscore erabiliz) itzuli
		bek = BalorazioenMatrizea.getBalorazioenMatrizea().getPeliBalorazioNormalizatuak(p3.getPelikulaId());
		assertTrue(bek.luzera() == 2);
		assertTrue(bek.getBalioa(e2.getId()) == -1);
		assertTrue(df.format(bek.getBalioa(e3.getId())).equals(",4472"));
		assertFalse(bek.bektoreanDago(e1.getId()));
		
		// p4 pelikula baloratu duten erabiltzaileen ID-ak eta balorazio normalizautak (Zscore erabiliz) itzuli
		bek = BalorazioenMatrizea.getBalorazioenMatrizea().getPeliBalorazioNormalizatuak(p4.getPelikulaId());
		assertTrue(bek.luzera() == 2);
		assertTrue(bek.getBalioa(e1.getId()) == 0);
		assertTrue(df.format(bek.getBalioa(e3.getId())).equals("1,0435"));
		assertFalse(bek.bektoreanDago(e2.getId()));
		*/
		
	}

	@Test
	public void testGetBalorazioa() {
		assertTrue(BalorazioenMatrizea.getBalorazioenMatrizea().getBalorazioa(e1.getId(), p1.getPelikulaId()) == 4);
		assertTrue(BalorazioenMatrizea.getBalorazioenMatrizea().getBalorazioa(e1.getId(), p2.getPelikulaId()) == 3);
		assertTrue(BalorazioenMatrizea.getBalorazioenMatrizea().getBalorazioa(e1.getId(), p4.getPelikulaId()) == 3.5);
		assertTrue(BalorazioenMatrizea.getBalorazioenMatrizea().getBalorazioa(e2.getId(), p2.getPelikulaId()) == 2);
		assertTrue(BalorazioenMatrizea.getBalorazioenMatrizea().getBalorazioa(e2.getId(), p3.getPelikulaId()) == 1);
		assertTrue(BalorazioenMatrizea.getBalorazioenMatrizea().getBalorazioa(e3.getId(), p1.getPelikulaId()) == 0.5);
		assertTrue(BalorazioenMatrizea.getBalorazioenMatrizea().getBalorazioa(e3.getId(), p2.getPelikulaId()) == 3.5);
		assertTrue(BalorazioenMatrizea.getBalorazioenMatrizea().getBalorazioa(e3.getId(), p3.getPelikulaId()) == 4);
		assertTrue(BalorazioenMatrizea.getBalorazioenMatrizea().getBalorazioa(e3.getId(), p4.getPelikulaId()) == 5);
		assertTrue(BalorazioenMatrizea.getBalorazioenMatrizea().getBalorazioa(e4.getId(), p2.getPelikulaId()) == 3.5);
		
		// asmatutako balioak
		assertFalse(BalorazioenMatrizea.getBalorazioenMatrizea().getBalorazioa(e1.getId(), p1.getPelikulaId()) == 5);
		assertFalse(BalorazioenMatrizea.getBalorazioenMatrizea().getBalorazioa(e1.getId(), p2.getPelikulaId()) == 2.5);
		assertFalse(BalorazioenMatrizea.getBalorazioenMatrizea().getBalorazioa(e1.getId(), p4.getPelikulaId()) == 3);
		assertFalse(BalorazioenMatrizea.getBalorazioenMatrizea().getBalorazioa(e2.getId(), p2.getPelikulaId()) == 1);

	}

}
