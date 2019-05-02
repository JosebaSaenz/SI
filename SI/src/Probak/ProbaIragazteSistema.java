package Probak;

import static org.junit.Assert.assertEquals;
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
		 * falta de poner un poco explicado esto
		 */
		GomendioSistema.getGomendioSistema().datuakKargatu();
		ArrayList<String> gomendioa = new ArrayList<String>();
		gomendioa  = IragazteSistema.getIragazteSistema().gomendatu(4);
	
		assertEquals(gomendioa.get(0),"Finding Nemo (2003)");
		assertEquals(gomendioa.get(1),"Forrest Gump (1994)");
	}
}

