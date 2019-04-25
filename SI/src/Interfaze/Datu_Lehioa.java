package Interfaze;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;

import Salbuespenak.ErabiltzaileaEzDaExistitzenException;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;

public class Datu_Lehioa extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private static Datu_Lehioa nDatu = null;
	private JLabel lblMezua;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Datu_Lehioa frame = Datu_Lehioa.getDatuLehioa();
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
	private Datu_Lehioa() {
		setTitle("Pelikulak");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Datu_Lehioa.class.getResource("/Fitxategiak/lehioa_icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 580);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setVisible(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(73, 113, 564, 350);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setIcon(new ImageIcon(Datu_Lehioa.class.getResource("/Fitxategiak/back_icon.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				try {
					datuakEzabatu();
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
				Lehio_Nagusia.getNagusia().setVisible(true);
			}
		});
		btnNewButton.setBounds(15, 16, 50, 50);
		contentPane.add(btnNewButton);
		
		lblMezua = new JLabel("");
		lblMezua.setForeground(new Color(153, 0, 0));
		lblMezua.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMezua.setBackground(Color.BLACK);
		lblMezua.setBounds(73, 69, 279, 31);
		contentPane.add(lblMezua);
	}
	
	private void datuakEzabatu() throws BadLocationException {
		this.textArea.getDocument().remove(0,textArea.getDocument().getLength());
	}
	
	public static synchronized Datu_Lehioa getDatuLehioa() {
		if (nDatu==null) {
			nDatu= new Datu_Lehioa();
		}
		return nDatu;
	}
	
	public void mezuaAldatu(int m) {
		if (m==0) {
			lblMezua.setText("Pelikula guztiak:");
		}
		else if (m==1) {
			lblMezua.setText("Ikusitako pelikulak:");
		}
		else if (m==2) {
			lblMezua.setText("Gomendatutako pelikulak:");
		}
	}

	public void pelikulakGomendatu(int idErabiltzailea) {
		this.setTitle("Gomendatutako pelikulak");
		ArrayList<String> gomendioak = MVC.getMVC().gomendatu(idErabiltzailea);
		for (int i=0; i<gomendioak.size(); i++) {
			textArea.append((i+1) + ".- " + gomendioak.get(i) + "\n");
		}
	}

	public void ikusitakoPelikulakErakutsi(int idErabiltzailea) {
		this.setTitle("Ikusitako pelikulak");
		ArrayList<String> ikusitakoak = MVC.getMVC().ikusitakoPelikulakLortu(idErabiltzailea);
		for (int i=0; i<ikusitakoak.size(); i++) {
			textArea.append("- " + ikusitakoak.get(i) + "\n");
		}
	}
	
	public void pelikulakErakutsi() {
		this.setTitle("Pelikulak");
		ArrayList<String> pelikulak = MVC.getMVC().getPelikulak();
		for (int i=0; i<pelikulak.size();i++) {
			textArea.append(pelikulak.get(i) + "\n");
		}
	}
}