package Interfaze;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import Salbuespenak.EremuakHutsikException;
import Salbuespenak.PasahitzaOkerraException;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Aukerak extends JFrame {

	private JPanel contentPane;
	private static Aukerak nAukerak=null;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aukerak frame = new Aukerak();
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
	public Aukerak() {
		setTitle("Aukerak");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Aukerak.class.getResource("/Fitxategiak/aukerak_icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 384, 518);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				passwordField.setText("");
				Hasierako_Lehioa.getHL().setVisible(true);
			}
		});
		button.setIcon(new ImageIcon(Aukerak.class.getResource("/Fitxategiak/back_icon.png")));
		button.setBackground(Color.DARK_GRAY);
		button.setBounds(21, 11, 50, 50);
		contentPane.add(button);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(64, 64, 64), 3, true));
		panel.setBackground(Color.GRAY);
		panel.setBounds(21, 181, 322, 200);
		contentPane.add(panel);
		
		JButton button_1 = new JButton("SARTU");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String pasahitza = String.valueOf(passwordField.getPassword());
					if(String.valueOf(passwordField.getPassword()).isEmpty()) {
						throw new EremuakHutsikException();
					}
					else if(pasahitza.equals("1234")) {
						setVisible(false);
						passwordField.setText("");
						IragazteaAukeratu.getIragaztea().setVisible(true);
					}
					else {
						throw new PasahitzaOkerraException();
					}
				} catch (PasahitzaOkerraException e) {
					e.mezua();
					DialogPasahitzaOkerra dp = new DialogPasahitzaOkerra();
					dp.setVisible(true);
				} catch (EremuakHutsikException e) {
					e.mezua();
					DialogEremuakHutsik de = new DialogEremuakHutsik();
					de.setVisible(true);
				}
			}
		});
		button_1.setForeground(Color.BLACK);
		button_1.setFont(new Font("Corbel", Font.BOLD, 18));
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setBounds(83, 134, 149, 34);
		panel.add(button_1);
		
		JLabel lblAdministratzailearenPasahitzaSartu = new JLabel("Administratzailearen pasahitza sartu:");
		lblAdministratzailearenPasahitzaSartu.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblAdministratzailearenPasahitzaSartu.setBackground(Color.BLACK);
		lblAdministratzailearenPasahitzaSartu.setBounds(10, 24, 309, 27);
		panel.add(lblAdministratzailearenPasahitzaSartu);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(Color.LIGHT_GRAY);
		passwordField.setBounds(83, 73, 149, 27);
		panel.add(passwordField);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(64, 64, 64), 3, true));
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(46, 89, 273, 50);
		contentPane.add(panel_1);
		
		JLabel lblAdministratzaileAukerak = new JLabel("ADMINISTRATZAILE AUKERAK");
		lblAdministratzaileAukerak.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblAdministratzaileAukerak.setBackground(Color.BLACK);
		lblAdministratzaileAukerak.setBounds(25, 11, 238, 27);
		panel_1.add(lblAdministratzaileAukerak);
	}
	
	public static synchronized Aukerak getAukerak() {
		if (nAukerak==null) {
			nAukerak=new Aukerak();
		}
		return nAukerak;
	}
	
}
