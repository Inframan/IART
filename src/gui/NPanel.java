package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import neural.Network;
import neural.Neuron;

public class NPanel  extends JPanel{

	private static final long serialVersionUID = 1L;
	Network neuronal;
	private Graphics2D g2d;
	
	private ArrayList<Node> inputLayer, outputLayer;
	private ArrayList<ArrayList<Node>> hiddenLayers;

	private NFrame nF;


	public NPanel(NFrame nF) throws IOException {
		this.nF = nF;
		inputLayer = new ArrayList<Node>();
		outputLayer = new ArrayList<Node>();
		hiddenLayers = new ArrayList<ArrayList<Node>>();
		neuronal = new Network(70, 33, 0.5, 1);
		loadNeuronToNode();
		neuronal.Run();
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
	
	
		public void loadImage() throws IOException {
			/*
		wallIMG = ImageIO.read(new File("res/wall.png"));
		pathIMG = ImageIO.read(new File("res/path.png"));
		exitIMG = ImageIO.read(new File("res/exit.png"));
		heroIMG = ImageIO.read(new File("res/hero.png"));
		swordIMG = ImageIO.read(new File("res/sword.png"));
		dragonIMG = ImageIO.read(new File("res/dragon.png"));
		DragonSwordIMG = ImageIO.read(new File("res/dragonSword.png"));
		heroSwordIMG = ImageIO.read(new File("res/HeroSword.png"));
		sleepDragonIMG = ImageIO.read(new File("res/SleepDragon.png"));
		eagleIMG = ImageIO.read(new File("res/eagle.png"));
			 */
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			g2d = (Graphics2D) g;
			g2d.draw3DRect(20, 20, 50, 50, false);;

		}
	}
