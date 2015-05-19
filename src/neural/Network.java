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
	
	
	public void frontPropagation(float inputValues[])
	{
		for(int i = 0; i < inputValues.length;i++)
		{
			inputLayer.listIterator(i).next().setValue(inputValues[i]);			
		}
		
	}

}
