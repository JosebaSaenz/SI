package Interfaze;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Hasierako_Lehioa extends JFrame {

	private JPanel contentPane;
	private static Hasierako_Lehioa nHL=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Hasierako_Lehioa frame = new Hasierako_Lehioa();
				frame.setVisible(true);
				MVC.getMVC().Datuak_Kargatu();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Hasierako_Lehioa() {
		setTitle("Hasierako lehioa");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Hasierako_Lehioa.class.getResource("/Fitxategiak/lehioa_icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 944, 622);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(165, 200, 600, 165);
		ImageIcon logoIcon = new ImageIcon("src/Fitxategiak/logo.png");
		Image logoIrudi = logoIcon.getImage();
		logoIrudi = logoIrudi.getScaledInstance(600, 165, Image.SCALE_DEFAULT);
		logoIcon = new ImageIcon(logoIrudi);
		panel.add(new JLabel(logoIcon));
		contentPane.add(panel);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Login.getLogin().setVisible(true);
			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Rockwell", Font.BOLD, 20));
		btnLogin.setBackground(new Color(178, 34, 34));
		btnLogin.setBounds(697, 52, 127, 38);
		contentPane.add(btnLogin);
		
		JLabel lblEgileakIkerValdelvira = new JLabel("Egileak: Iker Valdelvira, Xabi Dermit eta Joseba Saenz");
		lblEgileakIkerValdelvira.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEgileakIkerValdelvira.setForeground(Color.GRAY);
		lblEgileakIkerValdelvira.setBounds(372, 506, 506, 29);
		contentPane.add(lblEgileakIkerValdelvira);
		
		JButton aukerakButton = new JButton("");
		aukerakButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Aukerak.getAukerak().setVisible(true);
			}
		});
		aukerakButton.setIcon(new ImageIcon(Lehio_Nagusia.class.getResource("/Fitxategiak/aukerak_icon.png")));
		aukerakButton.setBackground(Color.BLACK);
		aukerakButton.setBounds(15, 500, 50, 50);
		contentPane.add(aukerakButton);
	}
	
	public static synchronized Hasierako_Lehioa getHL() {
		if (nHL==null) {
			nHL=new Hasierako_Lehioa();
		}
		return nHL;
	}
}