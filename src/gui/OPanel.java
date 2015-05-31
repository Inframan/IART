package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import neural.Network;

public class OPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	Network neuronal;
	private Graphics2D g2d;
	private JButton btnFileLoad;
	private JButton Start;
	
	File file;
	JLabel input = new JLabel("Input Number", JLabel.CENTER);
	JLabel output = new JLabel("Output Number", JLabel.CENTER);
	JLabel learning = new JLabel("Learning Rate Number", JLabel.CENTER);
	JLabel hidden = new JLabel("Hidden Layers Number", JLabel.CENTER);
	JLabel empty = new JLabel("",JLabel.CENTER);
	JLabel empty2 = new JLabel("",JLabel.CENTER);
	JSpinner inputNumber;
	JSpinner outputNumber;
	JSpinner learningRate;
	JSpinner hiddenLayersNumber;
	
	
	private NFrame sF;


	public OPanel(NFrame sF) throws IOException {
		this.sF = sF;
		btnFileLoad = new JButton("Load dataset");
		Start = new JButton("Start");
		
		inputNumber = new JSpinner(new SpinnerNumberModel(68,1,200,1));
		JFormattedTextField tf = ((JSpinner.DefaultEditor)inputNumber.getEditor()).getTextField();
		tf.setHorizontalAlignment(JFormattedTextField.CENTER);
		
		outputNumber = new JSpinner(new SpinnerNumberModel(33,1,200,1));
		tf = ((JSpinner.DefaultEditor)outputNumber.getEditor()).getTextField();
		tf.setHorizontalAlignment(JFormattedTextField.CENTER);
		
		hiddenLayersNumber = new JSpinner(new SpinnerNumberModel(1,1,64,1));
		tf = ((JSpinner.DefaultEditor)hiddenLayersNumber.getEditor()).getTextField();
		tf.setHorizontalAlignment(JFormattedTextField.CENTER);
		
		learningRate= new JSpinner( new SpinnerNumberModel(0.5, 0.0, 1.0,0.1));
		tf = ((JSpinner.DefaultEditor)learningRate.getEditor()).getTextField();
		tf.setHorizontalAlignment(JFormattedTextField.CENTER);
		setFocusable(true);
		requestFocus();

		addComponents();
		setComponents();
		addButtons();
		setUpButtons();
	}

	public void init()
	{		
		sF.frmNetwork.setResizable(false);
		setVisible(true);
		repaint();
		requestFocus();
	}	


	private void setUpButtons() {


		btnFileLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				JFileChooser fc = new JFileChooser();  
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);  

				fc.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("txt","txt");
				fc.setFileFilter(filter);  

				int returnVal = fc.showOpenDialog(new JFrame("Load"));


				if (returnVal == JFileChooser.APPROVE_OPTION) {  
						file= fc.getSelectedFile();  
						sF.filename = file.getName();
				}
			}
		});
		
		Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				sF.hiddenLayersNumber= (int) hiddenLayersNumber.getValue();
				sF.inputNumber = (int) inputNumber.getValue();
				sF.outputNumber= (int) outputNumber.getValue();
				sF.learningRate = (double) learningRate.getValue();
				
				
				setVisible(false);
				try {
					sF.frmNetwork.setSize(new Dimension(1023, 680));
					sF.startNetworkPanel();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		
	}


	private void addButtons() {

		add(btnFileLoad);
		btnFileLoad.setBounds(500, 150, 200, 50);
		add(Start);
		
	}
	
	private void setComponents()
	{


		inputNumber.setBounds(100, 150, 125, 30);
		outputNumber.setBounds(200, 150, 125, 30);
		hiddenLayersNumber.setBounds(300,150 , 150, 30);
		learningRate.setBounds(400, 150 , 150, 30);
	}

	private void addComponents()
	{
		add(input);
		add(output);
		add(inputNumber);
		add(outputNumber);
		add(hidden);
		add(learning);
		add(hiddenLayersNumber);
		add(learningRate);
		add(empty);
		add(empty2);
		
		hidden.setVisible(false);
		hiddenLayersNumber.setVisible(false);
	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g2d = (Graphics2D) g;

	}
}

