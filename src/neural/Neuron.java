package neural;

import java.util.ArrayList;

public class Neuron {

	private double value;
	private ArrayList<Double> weights;
	
	public Neuron(int weightsSize) {
		// TODO Auto-generated constructor stub
		
		value = 0;
		//Currents weights for all inputs
		weights = new ArrayList<Double>();
		for(int i = 0; i < weightsSize;i++)
			weights.add(1.0);
	}
	
	

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public ArrayList<Double> getWeights() {
		return weights;
	}

	public void setWeights(ArrayList<Double> weights) {
		this.weights = weights;
	}



	public void addValue(double d) {
		// TODO Auto-generated method stub
		value += d;		
	}
	
	
	
}
