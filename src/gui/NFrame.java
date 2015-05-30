package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;



import javax.swing.JFrame;

import java.awt.Toolkit;


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