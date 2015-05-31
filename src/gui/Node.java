package gui;

import java.awt.Graphics2D;

import javax.swing.JLabel;

import neural.Neuron;

public class Node {
	
	Neuron neuron;
	JLabel nodeLbl;
	
	public JLabel getNodeLbl() {
		return nodeLbl;
	}
	public void setNodeLbl(JLabel nodeLbl) {
		this.nodeLbl = nodeLbl;
	}
	public Neuron getNeuron() {
		return neuron;
	}
	public void setNeuron(Neuron neuron) {
		this.neuron = neuron;
	}
	public Node (Neuron n){
		
		neuron = n;
	}

}
