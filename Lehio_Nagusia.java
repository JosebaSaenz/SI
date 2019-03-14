package Interfaze;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Salbuespenak.ErabiltzaileaEzDaExistitzenException;
import Salbuespenak.KargaMotaEzDaExistitzenException;
import Salbuespenak.PelikulaEzDaExistitzenException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;

public class Lehio_Nagusia extends JFrame {

	private JPanel contentPane;
	private Datu_Lehioa datuak;
	private Grafiko_Kontrolagailua d;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lehio_Nagusia frame = new Lehio_Nagusia();
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
	public Lehio_Nagusia() {
		setTitle("Datuen karga");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null); 
		
		d = Grafiko_Kontrolagailua.getNireDatuak();
		
		JButton btnDatuakKargatu = new JButton("Datuak Kargatu");
		btnDatuakKargatu.setBackground(Color.BLACK);
		btnDatuakKargatu.setForeground(Color.BLACK);
		btnDatuakKargatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					d.Datuak_Kargatu();
				} catch (ErabiltzaileaEzDaExistitzenException | PelikulaEzDaExistitzenException
						| KargaMotaEzDaExistitzenException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDatuakKargatu.setBounds(194, 100, 209, 23);
		contentPane.add(btnDatuakKargatu);
		
		JButton btnNewButton = new JButton("Datuak Ezabatu");
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d.datuakEzabatu();
			}
		});
		btnNewButton.setBounds(194, 210, 209, 23);
		contentPane.add(btnNewButton);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(275, 320, 33, 21);
		contentPane.add(menuBar);
		
		JMenu mnIkusi = new JMenu("Ikusi");
		mnIkusi.setBackground(Color.BLACK);
		mnIkusi.setBounds(194, 219, 209, 22);
		
		JMenuItem mntmPelikulak = new JMenuItem("Pelikulak");
		mntmPelikulak.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				datuak = new Datu_Lehioa(d);
				datuak.setVisible(true);
				datuak.pelikulakIkusi();
			}
		});
		mntmPelikulak.setBounds(29, 277, 129, 22);
		
		JMenuItem mntmErabiltzaileak = new JMenuItem("Erabiltzaileak");
		mntmErabiltzaileak.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				datuak = new Datu_Lehioa(d);
				datuak.setVisible(true);
				datuak.erabiltzaileakIkusi();
			}
		});
		mntmErabiltzaileak.setBounds(0, 315, 129, 22);
		
		mnIkusi.add(mntmPelikulak);
		mnIkusi.add(mntmErabiltzaileak);
		
		menuBar.add(mnIkusi);
		
		

	}
}
