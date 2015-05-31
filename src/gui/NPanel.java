package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import neural.Network;
import neural.Neuron;

public class NPanel  extends JPanel{

	private static final long serialVersionUID = 1L;
	Network neuronal;
	private Graphics2D g2d;

	private ArrayList<Node> inputLayer, outputLayer;
	private ArrayList<ArrayList<Node>> hiddenLayers;

	private NFrame nF;

	public NPanel(NFrame nF, int inputNumber, int outputNumber, int hiddenLayersNumber, double learningRate, String filename) throws IOException {
		this.nF = nF;
		inputLayer = new ArrayList<Node>();
		outputLayer = new ArrayList<Node>();
		hiddenLayers = new ArrayList<ArrayList<Node>>();

		neuronal = new Network(inputNumber, outputNumber, learningRate,hiddenLayersNumber,filename);
		loadNeuronToNode();
		neuronal.Run();
		loadLabelNumber();
		setFocusable(true);
		requestFocus();

		//loadImage();
	}

	public void init()
	{		
		nF.frmNetwork.setResizable(true);
		setVisible(true);
		repaint();
		requestFocus();
	}

	public void loadNeuronToNode(){

		for(int i = 0 ; i < neuronal.getInputLayer().size(); i++){
			inputLayer.add(new Node(neuronal.getInputLayer().listIterator(i).next()));
		}

		for(int j = 0 ; j < neuronal.getHiddenLayersNumber(); j++){
			ArrayList<Node> tempHidden = new ArrayList<Node>();
			for( int k = 0 ; k < neuronal.getHiddenLayers().listIterator(j).next().size(); k++){
				tempHidden.add(new Node(neuronal.getHiddenLayers().listIterator(j).next().listIterator(k).next()));
			}
			hiddenLayers.add(tempHidden);
		}

		for (int l = 0 ; l < neuronal.getOutputLayer().size(); l++){
			outputLayer.add(new Node(neuronal.getOutputLayer().listIterator(l).next()));
		}
	}


	public void loadLabelNumber(){

		for(int i = 0 ; i < inputLayer.size(); i++){

			inputLayer.listIterator(i).next().setNodeLbl(new JLabel("Value:" + inputLayer.listIterator(i).next().getNeuron().getValue(), JLabel.CENTER));

		}

		int j = 0;
		for( ; j < hiddenLayers.size(); j++){
			for( int k = 0 ; k < hiddenLayers.listIterator(j).next().size(); k++){
				hiddenLayers.listIterator(j).next().listIterator(k).next().setNodeLbl(new JLabel("Value:" + hiddenLayers.listIterator(j).next().listIterator(k).next().getNeuron().getValue(), JLabel.CENTER));
		
			}
		}

		j++;
		for (int l = 0 ; l < outputLayer.size(); l++){
			outputLayer.listIterator(l).next().setNodeLbl(new JLabel("Value:" + outputLayer.listIterator(l).next().getNeuron().getValue(), JLabel.CENTER));
		}

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g2d = (Graphics2D) g;
		for(int i = 0 ; i < inputLayer.size(); i++){

			g2d.setColor(new Color(0,0,0));
			g2d.drawRect(15, 45*i, 30, 30);
			g2d.fillRect(15, 45*i, 30, 30);
			g2d.drawString(inputLayer.listIterator(i).next().getNodeLbl().getText(),55, 45* i + 20);

		}

		int j = 0;
		for( ; j < hiddenLayers.size(); j++){
			for( int k = 0 ; k < hiddenLayers.listIterator(j).next().size(); k++){
				g2d.setColor(new Color(0,0,255));
				g2d.drawRect(45*j+ 300, 45*k , 30, 30);
				g2d.fillRect(45*j+ 300, 45*k , 30, 30);
				g2d.drawString(hiddenLayers.listIterator(j).next().listIterator(k).next().getNodeLbl().getText(),55*j + 350, 45*k + 20);
			}
		}

		j++;
		for (int l = 0 ; l < outputLayer.size(); l++){
			if(outputLayer.listIterator(l).next().getNeuron().getValue() < 0.3)
				g2d.setColor(new Color(255,0,0));
			else
				g2d.setColor(new Color(0,255,0));
			g2d.drawRect(45*j + 550, 45*l , 30, 30);
			g2d.fillRect(45*j + 550, 45*l , 30, 30);
			g2d.drawString(outputLayer.listIterator(l).next().getNodeLbl().getText(),55*j + 615, 45* l + 20);
		}


	}
}
