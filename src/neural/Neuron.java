package neural;

import java.util.ArrayList;
import java.util.Random;

public class Neuron {

	private double value;
	private double delta;
	private double errorFactor;
	private double bias;



	private ArrayList<Double> weights;
	
	public Neuron(int weightsSize) {
		// TODO Auto-generated constructor stub
		
		value = 0;
		delta = 0;
		errorFactor = 0;
		//Currents weights for all inputs
		weights = new ArrayList<Double>();
		for(int i = 0; i < weightsSize;i++)
			weights.add(1.0);
		
		Random randomBias = new Random();
		bias = randomBias.nextDouble();
	}
	
	
	public void resetError()
	{
		value = 0;
		delta = 0;
		errorFactor = 0;
		
	}
	
	public double getBias() {
		return bias;
	}
	
	public void updateBias(double learningRate){
		bias += learningRate*delta;
	}
	
	
	public void setBias(double bias) {
		this.bias = bias;
	}

	public double getErrorFactor() {
		return errorFactor;
	}



	public void setErrorFactor(double errorFactor) {
		this.errorFactor = errorFactor;
	}

	public void addErrorFactor(double errorFactor){
		this.errorFactor += errorFactor;
	}


	public double getDelta() {
		return delta;
	}



	public void setDelta(double delta) {
		this.delta = delta;
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
