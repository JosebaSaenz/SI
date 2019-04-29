package Interfaze;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Proiektua.GomendioSistema;
import Salbuespenak.ErabiltzaileaEzDaExistitzenException;
import Salbuespenak.EremuakHutsikException;
import Salbuespenak.PasahitzaOkerraException;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private static Login nLogin=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Login");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/Fitxategiak/lehioa_icon.png")));
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel phOkerra = new JPanel();
		phOkerra.setBackground(SystemColor.inactiveCaption);
		phOkerra.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		phOkerra.setBounds(169, 139, 232, 142);
		phOkerra.setLayout(null);
		phOkerra.setVisible(false);
		contentPane.add(phOkerra);
		
		JLabel lblPasahitzaOkerraDa = new JLabel("Pasahitza okerra da");
		lblPasahitzaOkerraDa.setForeground(Color.RED);
		lblPasahitzaOkerraDa.setFont(new Font("Segoe UI Semilight", Font.BOLD, 23));
		lblPasahitzaOkerraDa.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasahitzaOkerraDa.setBounds(0, 9, 232, 55);
		phOkerra.add(lblPasahitzaOkerraDa);
		
		JButton btnNewButton = new JButton("Ados");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setIcon(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				phOkerra.setVisible(false);
			}
		});
		btnNewButton.setSelectedIcon(new ImageIcon(Login.class.getResource("/Fitxategiak/back_icon.png")));
		btnNewButton.setBounds(70, 92, 93, 34);
		phOkerra.add(btnNewButton);
		
		JLabel lblSartuErabiltzailearenIda = new JLabel("Saioa hasi");
		lblSartuErabiltzailearenIda.setHorizontalAlignment(SwingConstants.CENTER);
		lblSartuErabiltzailearenIda.setForeground(new Color(204, 0, 51));
		lblSartuErabiltzailearenIda.setBackground(Color.BLACK);
		lblSartuErabiltzailearenIda.setFont(new Font("Nirmala UI", Font.BOLD, 37));
		lblSartuErabiltzailearenIda.setBounds(133, 139, 308, 49);
		contentPane.add(lblSartuErabiltzailearenIda);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(64, 64, 64), 3, true));
		panel.setBackground(Color.GRAY);
		panel.setBounds(97, 208, 378, 200);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblErabiltzaileIda = new JLabel("Erabiltzaile IDa:");
		lblErabiltzaileIda.setBackground(new Color(0, 0, 0));
		lblErabiltzaileIda.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblErabiltzaileIda.setBounds(36, 31, 141, 27);
		panel.add(lblErabiltzaileIda);
		
		JButton btnSartu = new JButton("SARTU");
		btnSartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (textField.getText().isEmpty() || String.valueOf(passwordField.getPassword()).isEmpty()) { //Erabiltzailea edo pasahitza hutsik
						throw new EremuakHutsikException();
					}
					else if (GomendioSistema.getGomendioSistema().getErabiltzailea(Integer.parseInt(textField.getText())).pasahitzZuzena(Integer.parseInt(String.valueOf(passwordField.getPassword())))) { //Sartutako erabiltzailearen pasahitza zuzena bada
						passwordField.setText("");
						setVisible(false);
						Lehio_Nagusia.getNagusia().setErabiltzailea(Integer.parseInt(textField.getText()));
						textField.setText("");
						Lehio_Nagusia.getNagusia().setVisible(true);
					}
					else { //Pasahitza okerra da
						throw new PasahitzaOkerraException();
					}
				} catch (NumberFormatException e1) {
					System.out.println("Idazteko eremuak zenbaki batekin bete behar dira.");
					DialogZenbakiBatSartuBeharDa dz = new DialogZenbakiBatSartuBeharDa();
					dz.setVisible(true);
				} catch (ErabiltzaileaEzDaExistitzenException e1) {
					e1.mezua(Integer.parseInt(textField.getText()));
					DialogErabiltzaileaEzDaExistitzen de = new DialogErabiltzaileaEzDaExistitzen(Integer.parseInt(textField.getText()));
					de.setVisible(true);
				} catch (EremuakHutsikException e1) {
					e1.mezua();
					DialogEremuakHutsik de = new DialogEremuakHutsik();
					de.setVisible(true);
				} catch (PasahitzaOkerraException e1) {
					e1.mezua();
					DialogPasahitzaOkerra dp = new DialogPasahitzaOkerra();
					dp.setVisible(true);
				}
			}
		});
		btnSartu.setForeground(new Color(0, 0, 0));
		btnSartu.setBackground(Color.LIGHT_GRAY);
		btnSartu.setFont(new Font("Corbel", Font.BOLD, 18));
		btnSartu.setBounds(118, 142, 141, 34);
		panel.add(btnSartu);
		
		JLabel lblPasahitza = new JLabel("Pasahitza:");
		lblPasahitza.setBackground(new Color(0, 0, 0));
		lblPasahitza.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblPasahitza.setBounds(36, 80, 141, 27);
		panel.add(lblPasahitza);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(Color.LIGHT_GRAY);
		passwordField.setBounds(201, 80, 149, 27);
		panel.add(passwordField);
		
		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setFont(new Font("Arial", Font.BOLD, 15));
		textField.setBounds(201, 32, 149, 27);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton getPhButton = new JButton("?");
		getPhButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					GomendioSistema.getGomendioSistema().getErabiltzailea(Integer.parseInt(textField.getText())).pasahitzaBete();
				} catch (NumberFormatException e1) {
					System.out.println("Idazteko eremuak zenbaki batekin bete behar dira.");
					DialogZenbakiBatSartuBeharDa dz = new DialogZenbakiBatSartuBeharDa();
					dz.setVisible(true);
				} catch (ErabiltzaileaEzDaExistitzenException e1) {
					e1.mezua(Integer.parseInt(textField.getText()));
					DialogErabiltzaileaEzDaExistitzen de = new DialogErabiltzaileaEzDaExistitzen(Integer.parseInt(textField.getText()));
					de.setVisible(true);
				}
			}
		});
		getPhButton.setBackground(Color.GRAY);
		getPhButton.setFont(new Font("Tahoma", Font.PLAIN, 8));
		getPhButton.setBounds(134, 85, 43, 22);
		panel.add(getPhButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(195, 11, 181, 117);
		contentPane.add(panel_1);
		panel_1.add(new JLabel(new ImageIcon("src/Fitxategiak/erabLogo.png")));
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				textField.setText("");
				passwordField.setText("");
				phOkerra.setVisible(false);
				Hasierako_Lehioa.getHL().setVisible(true);
			}
		});
		button.setBackground(Color.BLACK);
		button.setBounds(15, 16, 56, 48);
		button.setIcon(new ImageIcon(Login.class.getResource("/Fitxategiak/back_icon.png")));
		contentPane.add(button);
	}
	
	public static synchronized Login getLogin() {
		if (nLogin==null) {
			nLogin=new Login();
		}
		return nLogin;
	}
	
	public void setPasahitza(String ph) {
		passwordField.setText(ph);
	}
}