package Interfaze;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class DialogEremuakHutsik extends JDialog {
	
	private JLabel jLabel1 = new JLabel();
	private JButton jButton1 = new JButton();
	
	public DialogEremuakHutsik() {
		this.setModal(true);
		this.setLocation(500,300);
		this.setSize(new Dimension(400,135));
		this.getContentPane().setLayout(null);
		jLabel1.setText("Eremu guztiak bete behar dira.");
		jLabel1.setBounds(new Rectangle(105,20,300,15));
		jButton1.setText("Itxi");
		jButton1.setBounds(new Rectangle(150,55,70,30));
		this.getContentPane().add(jButton1,null);
		this.getContentPane().add(jLabel1,null);
		
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}

}
