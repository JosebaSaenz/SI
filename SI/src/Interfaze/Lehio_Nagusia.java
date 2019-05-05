package Interfaze;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Salbuespenak.ErabiltzaileaEzDaExistitzenException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Lehio_Nagusia extends JFrame {

	private JPanel contentPane;
	private static Lehio_Nagusia nNagusi=null;
	private int idErabiltzailea;
	private JLabel lblIdErab;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lehio_Nagusia frame = Lehio_Nagusia.getNagusia();
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
	private Lehio_Nagusia() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Lehio_Nagusia.class.getResource("/Fitxategiak/lehioa_icon.png")));
		setTitle("Lehio nagusia");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 564);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null); 
		
		lblIdErab = new JLabel("");
		lblIdErab.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblIdErab.setBackground(Color.BLACK);
		lblIdErab.setForeground(Color.LIGHT_GRAY);
		lblIdErab.setBounds(488, 35, 93, 28);
		contentPane.add(lblIdErab);
		
		JButton button = new JButton("Logout");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				lblIdErab.setText("");
				Login.getLogin().setVisible(true);
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Rockwell", Font.BOLD, 20));
		button.setBackground(new Color(178, 34, 34));
		button.setBounds(596, 33, 127, 38);
		contentPane.add(button);
		
		JLabel lblErabiltzailea = new JLabel("Erabiltzailea:");
		lblErabiltzailea.setForeground(Color.LIGHT_GRAY);
		lblErabiltzailea.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblErabiltzailea.setBackground(Color.BLACK);
		lblErabiltzailea.setBounds(328, 34, 145, 31);
		contentPane.add(lblErabiltzailea);
		
		JButton btnPelikulakGomendatu = new JButton("PELIKULAK GOMENDATU");
		btnPelikulakGomendatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Datu_Lehioa.getDatuLehioa().mezuaAldatu(2);
				setVisible(false);
				Datu_Lehioa.getDatuLehioa().setVisible(true);
				Datu_Lehioa.getDatuLehioa().pelikulakGomendatu(idErabiltzailea);
				Datu_Lehioa.getDatuLehioa().mezuaAldatu(2);
			}
		});
		btnPelikulakGomendatu.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnPelikulakGomendatu.setBackground(new Color(153, 0, 0));
		btnPelikulakGomendatu.setForeground(Color.LIGHT_GRAY);
		btnPelikulakGomendatu.setBounds(168, 186, 437, 60);
		contentPane.add(btnPelikulakGomendatu);
		
		JButton btnIkusitakoPelikulak = new JButton("IKUSITAKO PELIKULAK");
		btnIkusitakoPelikulak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Datu_Lehioa.getDatuLehioa().mezuaAldatu(1);
				setVisible(false);
				Datu_Lehioa.getDatuLehioa().setVisible(true);
				Datu_Lehioa.getDatuLehioa().ikusitakoPelikulakErakutsi(idErabiltzailea);
				Datu_Lehioa.getDatuLehioa().mezuaAldatu(1);
			}
		});
		btnIkusitakoPelikulak.setForeground(Color.LIGHT_GRAY);
		btnIkusitakoPelikulak.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnIkusitakoPelikulak.setBackground(new Color(153, 0, 0));
		btnIkusitakoPelikulak.setBounds(168, 284, 437, 60);
		contentPane.add(btnIkusitakoPelikulak);
		
		JButton btnPelikulaGuztiak = new JButton("PELIKULA GUZTIAK");
		btnPelikulaGuztiak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Datu_Lehioa.getDatuLehioa().mezuaAldatu(0);
				setVisible(false);
				Datu_Lehioa.getDatuLehioa().setVisible(true);
				Datu_Lehioa.getDatuLehioa().pelikulakErakutsi();
				Datu_Lehioa.getDatuLehioa().mezuaAldatu(0);
			}
		});
		btnPelikulaGuztiak.setForeground(Color.LIGHT_GRAY);
		btnPelikulaGuztiak.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnPelikulaGuztiak.setBackground(new Color(153, 0, 0));
		btnPelikulaGuztiak.setBounds(168, 379, 437, 60);
		contentPane.add(btnPelikulaGuztiak);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(47, 35, 200, 75);
		ImageIcon logoIcon = new ImageIcon("src/Fitxategiak/logo.png");
		Image logoIrudi = logoIcon.getImage();
		logoIrudi = logoIrudi.getScaledInstance(200, 75, Image.SCALE_DEFAULT);
		logoIcon = new ImageIcon(logoIrudi);
		panel.add(new JLabel(logoIcon));
		contentPane.add(panel);

	}
	public static synchronized Lehio_Nagusia getNagusia() {
		if (nNagusi==null) {
			nNagusi=new Lehio_Nagusia();
		}
		return nNagusi;
	}
	
	public void setErabiltzailea(int id) {
		idErabiltzailea = id;
		lblIdErab.setText(Integer.toString(id));
	}
}
