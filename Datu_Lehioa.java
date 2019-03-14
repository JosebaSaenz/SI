package Interfaze;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class Datu_Lehioa extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private Grafiko_Kontrolagailua d;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Datu_Lehioa frame = new Datu_Lehioa(Grafiko_Kontrolagailua.getNireDatuak());
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
	public Datu_Lehioa(Grafiko_Kontrolagailua pD) {
		this.d = pD;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 564, 350);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(Datu_Lehioa.class.getResource("/Interfaze/back_icon.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnNewButton.setBounds(10, 0, 50, 50);
		contentPane.add(btnNewButton);
	}
	public void pelikulakIkusi() {
		this.setTitle("Pelikulak");
		if (d != null) {
		for (int i=0; i<d.getPelikulak().size();i++) {
	   		textArea.append(d.getPelikulak().get(i) + "\n");
	   	}
		}
	}
	
	public void erabiltzaileakIkusi() {
		this.setTitle("Erabiltzaileak");
		if (d != null) {
		for (int i=0; i<d.getErabiltzaileak().size(); i++) {
			textArea.append(d.getErabiltzaileak().get(i) + "\n");
		}
		}
	}
	
}
