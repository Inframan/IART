package neural;

import java.util.ArrayList;

public class Network {
	private ArrayList<Neuron> inputLayer, hiddenLayer, outputLayer;
	private int inputSize, outputSize, mean;//Layer size
	
	public Network(int inputNumber,int outputNumber) {
		// TODO Auto-generated constructor stub
		inputSize = inputNumber;
		outputSize = outputNumber;
		mean = (inputNumber + outputNumber) /2; 
		
		inputLayer = new ArrayList<Neuron>();
		hiddenLayer = new ArrayList<Neuron>();
		outputLayer = new ArrayList<Neuron>();
		
		for(int i = 0; i < inputSize; i++)
		{
			inputLayer.add(new Neuron(mean));
		}
		
		for(int i = 0; i < mean; i++)
		{
			hiddenLayer.add(new Neuron(outputSize));
		}
		
		for(int i = 0; i < outputSize; i++)
		{
			outputLayer.add(new Neuron(0));
		}
		
	}
	
	
	public void frontPropagation(double inputValues[])
	{
		for(int i = 0; i < inputValues.length;i++)
		{
			inputLayer.listIterator(i).next().setValue(inputValues[i]);		
			for(int j = 0; j < mean;j++)
			{
				hiddenLayer.listIterator(j).next().addValue(inputLayer.listIterator(i).next().getWeights().listIterator(j).next() * inputValues[i]);
				
			}
		}
		
		for(int i = 0; i < mean;i++)
		{
			for(int j = 0; j < outputSize;j++)
			{
				outputLayer.listIterator(j).next().addValue(hiddenLayer.listIterator().next().getWeights().listIterator(j).next() * inputLayer.listIterator(i).next().getValue());
			}
			
		}		
	}


	public ArrayList<Neuron> getInputLayer() {
		return inputLayer;
	}


	public void setInputLayer(ArrayList<Neuron> inputLayer) {
		this.inputLayer = inputLayer;
	}


	public ArrayList<Neuron> getHiddenLayer() {
		return hiddenLayer;
	}


	public void setHiddenLayer(ArrayList<Neuron> hiddenLayer) {
		this.hiddenLayer = hiddenLayer;
	}


	public ArrayList<Neuron> getOutputLayer() {
		return outputLayer;
	}


	public void setOutputLayer(ArrayList<Neuron> outputLayer) {
		this.outputLayer = outputLayer;
	}


	public int getInputSize() {
		return inputSize;
	}


	public void setInputSize(int inputSize) {
		this.inputSize = inputSize;
	}


	public int getOutputSize() {
		return outputSize;
	}


	public void setOutputSize(int outputSize) {
		this.outputSize = outputSize;
	}


	public int getMean() {
		return mean;
	}


	public void setMean(int mean) {
		this.mean = mean;
	}

	
	
	
}
