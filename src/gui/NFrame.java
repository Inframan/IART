package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;



import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.filechooser.FileNameExtensionFilter;

// TODO: Auto-generated Javadoc
/**
 * The Class NFrame.
 */
public class NFrame {
	
	/** The frm Dbz Battle. */
	JFrame frmNetwork = new JFrame();
	
	/** The game panel. */
	NPanel networkPanel;
	
	public NFrame() throws IOException {
		
		frmNetwork.setTitle("Neuronal Network");
		frmNetwork.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		networkPanel = new NPanel(this);
		
		frmNetwork.getContentPane().add(networkPanel, BorderLayout.CENTER);
		networkPanel.init();
	}		  

	/**
	 * Starts the frame
	 * 
	 */
	public void start() {


		frmNetwork.setSize(new Dimension(1023, 680));
		frmNetwork.setResizable(false);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmNetwork.setLocation(
				dim.width / 2 - frmNetwork.getSize().width / 2,
				dim.height / 2 - frmNetwork.getSize().height / 2);

		frmNetwork.setVisible(true);
	}

}