package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;






import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.Toolkit;


// TODO: Auto-generated Javadoc
/**
 * The Class NFrame.
 */
public class NFrame {
	
	JFrame frmNetwork = new JFrame();
	NPanel networkPanel;
	
	OPanel optionsPanel;

	int inputNumber;
	int outputNumber;
	int hiddenLayersNumber;
	double learningRate = 0.5;
	String filename;
	
	public NFrame() throws IOException {
		
		frmNetwork.setTitle("Neuronal Network");
		frmNetwork.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		optionsPanel = new OPanel(this);
		optionsPanel.setLayout(new GridLayout(1,0,0,0));
		frmNetwork.add(optionsPanel);
		
		
	}		  
	
	public void startNetworkPanel() throws IOException{
		networkPanel = new NPanel(this, inputNumber, outputNumber , hiddenLayersNumber, learningRate, filename);
		networkPanel.setPreferredSize(new Dimension( 2000,3200));
		JScrollPane scrollFrame = new JScrollPane(networkPanel);
		networkPanel.setAutoscrolls(true);
		scrollFrame.setPreferredSize(new Dimension( 400,150));
		frmNetwork.add(scrollFrame);
		//frmNetwork.getContentPane().add(networkPanel, BorderLayout.CENTER);
		networkPanel.init();
	}

	/**
	 * Starts the frame
	 * 
	 */
	public void start() {


		frmNetwork.setSize(new Dimension(700, 100));
		frmNetwork.setResizable(false);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmNetwork.setLocation(
				dim.width / 2 - frmNetwork.getSize().width / 2,
				dim.height / 2 - frmNetwork.getSize().height / 2);

		frmNetwork.setVisible(true);
	}

}