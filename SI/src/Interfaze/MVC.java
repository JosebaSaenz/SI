package Interfaze;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;

import Proiektua.GomendioSistema;
import Salbuespenak.AukeraBatEginBeharDaException;
import Salbuespenak.ErabiltzaileaEzDaExistitzenException;
import Salbuespenak.EremuakHutsikException;
import Salbuespenak.KargaMotaEzDaExistitzenException;
import Salbuespenak.PelikulaEzDaExistitzenException;
import Salbuespenak.PelikulaJadanikIkusiDuException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class IragazteaAukeratu extends JFrame {

	private JPanel contentPane;
	private JTextField txtIderabiltzailea;
	private JTextField txtIdpelikula;
	private static IragazteaAukeratu nIragaztea=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IragazteaAukeratu frame = new IragazteaAukeratu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws KargaMotaEzDaExistitzenException 
	 * @throws PelikulaEzDaExistitzenException 
	 * @throws ErabiltzaileaEzDaExistitzenException 
	 */
	private IragazteaAukeratu() {
		setTitle("Balorazioen estimazioa");
		setIconImage(Toolkit.getDefaultToolkit().getImage(IragazteaAukeratu.class.getResource("/Fitxategiak/aukerak_icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(Datu_Lehioa.class.getResource("/Fitxategiak/back_icon.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Hasierako_Lehioa.getHL().setVisible(true);
			}
		});
		btnNewButton.setBounds(15, 16, 50, 50);
		contentPane.add(btnNewButton);
				
		JRadioButton ErabIr = new JRadioButton("Erabiltzaileetan oinarritutako iragazte kolaboratiboa");
		ErabIr.setBounds(95, 132, 409, 23);
		contentPane.add(ErabIr);
		
		JRadioButton ProdIr = new JRadioButton("Produktuetan oinarritutako iragazte kolaboratiboa");
		ProdIr.setBounds(95, 167, 409, 23);
		contentPane.add(ProdIr);
		
		JRadioButton EzaIr = new JRadioButton("Ezaugarrietan oinarritutako iragaztea");
		EzaIr.setBounds(95, 202, 409, 23);
		contentPane.add(EzaIr);
		
		ButtonGroup taldea = new ButtonGroup();
			taldea.add(ErabIr);
			taldea.add(ProdIr);
			taldea.add(EzaIr);
			
		JLabel ErabID = new JLabel("Erabiltzailearen IDa sartu:");
		ErabID.setBounds(79, 26, 184, 14);
		contentPane.add(ErabID);
			
		JLabel ProdID = new JLabel("Pelikularen IDa sartu:");
		ProdID.setBounds(79, 51, 184, 14);
		contentPane.add(ProdID);
			
		txtIderabiltzailea = new JTextField();
		txtIderabiltzailea.setBounds(278, 26, 120, 20);
		contentPane.add(txtIderabiltzailea);
		txtIderabiltzailea.setColumns(10);
			
		txtIdpelikula = new JTextField();
		txtIdpelikula.setBounds(278, 51, 120, 20);
		contentPane.add(txtIdpelikula);
		txtIdpelikula.setColumns(10);
			
		JLabel lblIragazteMotaAukeratu = new JLabel("Iragazte mota aukeratu:");
		lblIragazteMotaAukeratu.setBounds(79, 102, 204, 18);
		contentPane.add(lblIragazteMotaAukeratu);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(278, 277, 120, 22);
		contentPane.add(textArea);
		
		JButton btnBalorazioaEstimatu = new JButton("Balorazioa estimatu");
		btnBalorazioaEstimatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try {
					if(txtIderabiltzailea.getText().isEmpty() || txtIdpelikula.getText().isEmpty()) {
						throw new EremuakHutsikException();
					}
					else {
						int eID = Integer.parseInt(txtIderabiltzailea.getText());
						int pID = Integer.parseInt(txtIdpelikula.getText());
						Double b = null;
						boolean ikusiDu = MVC.getMVC().pelikulaIkusiDu(eID,pID);
						if (ErabIr.isSelected() && !ikusiDu) {
							try {
								b = MVC.getMVC().erabiltzaileaBalorazioaEstimazioa(eID, pID);
								String emaitza = b.toString();
								textArea.setText(emaitza);
							} catch (ErabiltzaileaEzDaExistitzenException e1) {
								e1.mezua(eID);
								DialogErabiltzaileaEzDaExistitzen de = new DialogErabiltzaileaEzDaExistitzen(eID);
								de.setVisible(true);
							} catch (PelikulaEzDaExistitzenException e1) {
								e1.mezua(pID);
								DialogPelikulaEzDaExistitzen dp = new DialogPelikulaEzDaExistitzen(pID);
								dp.setVisible(true);
							}
						}
						else if (ProdIr.isSelected() && !ikusiDu) {
							try {
								b = MVC.getMVC().produktuaBalorazioaEstimazioa(eID, pID);
								String emaitza = b.toString();
								textArea.setText(emaitza);
							} catch (ErabiltzaileaEzDaExistitzenException e1) {
								e1.mezua(eID);
								DialogErabiltzaileaEzDaExistitzen de = new DialogErabiltzaileaEzDaExistitzen(eID);
								de.setVisible(true);
							} catch (PelikulaEzDaExistitzenException e1) {
								e1.mezua(pID);
								DialogPelikulaEzDaExistitzen dp = new DialogPelikulaEzDaExistitzen(pID);
								dp.setVisible(true);
							}
						}
						else if (EzaIr.isSelected() && !ikusiDu) {
							try {
								b = MVC.getMVC().ezaugarriaBalorazioaEstimazioa(eID, pID);
								String emaitza = b.toString();
								textArea.setText(emaitza);
							} catch (ErabiltzaileaEzDaExistitzenException e1) {
								e1.mezua(eID);
								DialogErabiltzaileaEzDaExistitzen de = new DialogErabiltzaileaEzDaExistitzen(eID);
								de.setVisible(true);
							} catch (PelikulaEzDaExistitzenException e1) {
								e1.mezua(pID);
								DialogPelikulaEzDaExistitzen dp = new DialogPelikulaEzDaExistitzen(pID);
								dp.setVisible(true);
							}					
						}
						else if (ikusiDu) {
							try {
								throw new PelikulaJadanikIkusiDuException();
							} catch (PelikulaJadanikIkusiDuException e1) {
								e1.mezua(eID, pID);
								DialogPelikulaJadanikIkusiDu da = new DialogPelikulaJadanikIkusiDu(eID,pID);
								da.setVisible(true);
							}
						}
						else {
							throw new AukeraBatEginBeharDaException();
						}
					}
				} catch(NumberFormatException excep) {
					System.out.println("Idazteko eremuak zenbaki batekin bete behar dira.");
					DialogZenbakiBatSartuBeharDa dz = new DialogZenbakiBatSartuBeharDa();
					dz.setVisible(true);
				} catch (AukeraBatEginBeharDaException e1) {
					e1.mezua();
					DialogAukeraBatEginBeharDa da = new DialogAukeraBatEginBeharDa();
					da.setVisible(true);
				} catch (EremuakHutsikException e1) {
					e1.mezua();
					DialogEremuakHutsik de = new DialogEremuakHutsik();
					de.setVisible(true);
				}
			}
		});
		btnBalorazioaEstimatu.setBounds(161, 238, 177, 23);
		contentPane.add(btnBalorazioaEstimatu);
		
		
		JLabel lblBalorazioEstimatua = new JLabel("Balorazio estimatua:");
		lblBalorazioEstimatua.setBounds(79, 280, 184, 14);
		contentPane.add(lblBalorazioEstimatua);
		
	}
	
	public static synchronized IragazteaAukeratu getIragaztea() {
		if (nIragaztea==null) {
			nIragaztea= new IragazteaAukeratu();
		}
		return nIragaztea;
		
	}
}
