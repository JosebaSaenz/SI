package Proiektua;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

import Salbuespenak.ErabiltzaileaEzDaExistitzenException;
import Salbuespenak.PelikulaEzDaExistitzenException;

public class CSVKargaMota2 extends DatuenKarga {

	public CSVKargaMota2() {
		super();
	}
	
	public void datuakKargatu() {
		pelikulakIrakurri();
		balorazioakIrakurri();
		komentarioakIrakurri();
	}
	
	private void pelikulakIrakurri() {
		String helbidea = "Fitxategiak/movietitles.csv";
		try {
			InputStream fitx = this.getClass().getClassLoader().getResourceAsStream(helbidea);
			InputStreamReader in= new InputStreamReader(fitx);
			
			Scanner sc = new Scanner(in);
			while (sc.hasNextLine()) {
				String lerroa = sc.nextLine();			// uneko lerroa irakurtzen da
				String[] zatiak = lerroa.split(";");	// ';' id-ak eta titulua banatzen ditu
				int id = Integer.parseInt(zatiak[0]);
				String titulua = zatiak[1];
				Pelikula berria = new Pelikula(id,titulua);
				PelikulaKatalogo.getPelikulaKatalogo().gehituPelikula(id, berria);	// pelikulen katalogora gehitu
			}
			sc.close();
		}catch(NullPointerException e){
			System.out.println("'" + helbidea + "' fitxategia ez da existitzen.");
		}
		
	}
	
	private void balorazioakIrakurri() {
		String helbidea = "Fitxategiak/movieratings.csv";
		try {
			InputStream fitx = this.getClass().getClassLoader().getResourceAsStream(helbidea);
			InputStreamReader in= new InputStreamReader(fitx);
			
			Scanner sc = new Scanner(in);
			while (sc.hasNextLine()) {
				String lerroa = sc.nextLine();				// uneko lerroa irakurtzen da
				String[] zatiak = lerroa.split(",");		// lerroa ',' bakoitzeko zatitzen da, elementuak array batean sartuz
				int idUser = Integer.parseInt(zatiak[0]);
				int idMovie = Integer.parseInt(zatiak[1]);
				double rating = Double.parseDouble(zatiak[2]);
				Erabiltzailea erab = null;
				try {
					if (!GomendioSistema.getGomendioSistema().erregistratutaDago(idUser)) {
						/* erabiltzailea oraindik ez badago erregistratuta, sortzen da eta zerrendara gehitzen da */
						Random pasahitza = new Random();
						erab = new Erabiltzailea(idUser,pasahitza.nextInt(99999999));
						GomendioSistema.getGomendioSistema().gehituErabiltzailea(idUser, erab);
					}
					else {
						/* erabiltzailea jadanik erregistratuta badago, eskuratzen dugu, ondorengo ariketak egiteko */
						erab = GomendioSistema.getGomendioSistema().getErabiltzailea(idUser);
					}
					Pelikula p = PelikulaKatalogo.getPelikulaKatalogo().getPelikula(idMovie);		// balorazioari dagokio pelikula eskuratzen da,aurreko metodoan katalogoan sartzen dira
					p.balorazioaGehitu(idUser, rating);		//  pelikulari dagokion balorazioan erabiltzailearen id-a gehitu
					erab.pelikulaGehitu(idMovie, p);		// dagokion erabiltzaileari baloratutako pelikula gehitzen diogu
				} catch (PelikulaEzDaExistitzenException e) {
					e.mezua(idMovie);
				} catch (ErabiltzaileaEzDaExistitzenException e) {
					e.mezua(idUser);
				}
			}
			sc.close();
		}catch(NullPointerException e){
			System.out.println("'" + helbidea + "' fitxategia ez da existitzen.");
		}
		
	}
	
	private void komentarioakIrakurri() {
		String helbidea = "Fitxategiak/movietags.csv";
		try {
			InputStream fitx = this.getClass().getClassLoader().getResourceAsStream(helbidea);
			InputStreamReader in= new InputStreamReader(fitx);
			
			Scanner sc = new Scanner(in);
			int tagIdKont = 0;					// komentarioei id bat ezartzeko erabiliko dugun kontagailua
			while (sc.hasNextLine()) {
				String lerroa = sc.nextLine();			// uneko lerroa irakurtzen da
				String[] zatiak = lerroa.split(";");	// lerroa ',' bakoitzeko zatitzen da, elementuak array batean sartuz
				int idMovie = Integer.parseInt(zatiak[0]);
				String tag = zatiak[1];
				int tagId = tagIdKont;
				if(GomendioSistema.getGomendioSistema().komentarioaGordetaDago(tag)) {			// komentarioa jadanik beste pelikula batzuetan gorde bada
					tagId = GomendioSistema.getGomendioSistema().getKomentarioarenId(tag);		// komentarioari ezarritako IDa lortzen dugu
				}
				else {																			// bestela
					GomendioSistema.getGomendioSistema().komentarioaGorde(tag,tagId);			// komentarioari ID berria ezartzen diogu
					tagIdKont ++;
				}
				try {
					Pelikula p = PelikulaKatalogo.getPelikulaKatalogo().getPelikula(idMovie);		// komentarioari dagokio pelikula eskuratzen da
					p.komentarioaErabiltzaileaGabeGehitu(tag, tagId);			//  pelikulari komentarioa gehitzen zaio
				}catch(PelikulaEzDaExistitzenException e) {
					e.mezua(idMovie);
				}
			}
			sc.close();
		}catch(NullPointerException e){
			System.out.println("'" + helbidea + "' fitxategia ez da existitzen.");
		}
		
	}
	
}