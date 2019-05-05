package Interfaze;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class DialogEremuakHutsik extends JDialog {
	
	private JLabel jLabel1 = new JLabel();
	private JButton jButton1 = new JButton();
	
	public DialogEremuakHutsik() {
		getContentPane().setBackground(Color.DARK_GRAY);
		setTitle("Arazoa gertatu da");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DialogEremuakHutsik.class.getResource("/Fitxategiak/dialog_icon.png")));
		this.setModal(true);
		this.setLocation(500,300);
		this.setSize(new Dimension(450, 160));
		this.getContentPane().setLayout(null);
		jLabel1.setForeground(Color.WHITE);
		jLabel1.setFont(new Font("Tahoma", Font.BOLD, 16));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel1.setText("Eremu guztiak bete behar dira.");
		jLabel1.setBounds(new Rectangle(0, 20, 428, 19));
		jButton1.setForeground(Color.BLACK);
		jButton1.setFont(new Font("Tahoma", Font.BOLD, 16));
		jButton1.setBackground(Color.RED);
		jButton1.setText("Itxi");
		jButton1.setBounds(new Rectangle(180, 55, 70, 30));
		this.getContentPane().add(jButton1,null);
		this.getContentPane().add(jLabel1,null);
		
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}

}
