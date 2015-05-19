package neural;

import java.util.ArrayList;

public class Network {
	private ArrayList<Neuron> inputLayer, hiddenLayer, outputLayer;
	private int inputSize, outputSize, hiddenSize;//Layer size
	private double learningRate;
	private double minimalNetValue;


	public Network(int inputNumber,int outputNumber, double learningRate , double minNetValue) {
		// TODO Auto-generated constructor stub
		inputSize = inputNumber;
		outputSize = outputNumber;
		hiddenSize = (inputNumber + outputNumber) /2;
		this.learningRate = learningRate;
		minimalNetValue = minNetValue;


		inputLayer = new ArrayList<Neuron>();
		hiddenLayer = new ArrayList<Neuron>();
		outputLayer = new ArrayList<Neuron>();

		for(int i = 0; i < inputSize; i++)
		{
			inputLayer.add(new Neuron(hiddenSize));
		}

		for(int i = 0; i < hiddenSize; i++)
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
			for(int j = 0; j < hiddenSize;j++)
			{	
				double netValue = inputLayer.listIterator(i).next().getWeights().listIterator(j).next() * inputValues[i];

				if(netValue >= minimalNetValue)
					hiddenLayer.listIterator(j).next().addValue(sigmoide(netValue));

			}
		}

		for(int i = 0; i < hiddenSize;i++)
		{
			for(int j = 0; j < outputSize;j++)
			{
				double netValue = hiddenLayer.listIterator().next().getWeights().listIterator(j).next() * inputLayer.listIterator(i).next().getValue();

				if(netValue >= minimalNetValue)
					outputLayer.listIterator(j).next().addValue(sigmoide(netValue));
			}

		}
	}

	private void backPropagation(double ExpectedOutputValues[]){

		for(int i = 0 ; i < outputSize; i++){

			outputLayer.listIterator(i).next().setErrorFactor(ExpectedOutputValues[i] - outputLayer.listIterator(i).next().getValue());			
			outputLayer.listIterator(i).next().setDelta(outputLayer.listIterator(i).next().getValue() * (1 - outputLayer.listIterator(i).next().getValue()) * outputLayer.listIterator(i).next().getErrorFactor());
		}

		for( int i = 0 ; i < hiddenSize; i++)
		{
			for (int j=0 ; j < outputSize;j++)
				hiddenLayer.listIterator(i).next().addErrorFactor(outputLayer.listIterator(j).next().getDelta() * hiddenLayer.listIterator(i).next().getWeights().listIterator(j).next());			
			
			hiddenLayer.listIterator(i).next().setDelta(hiddenLayer.listIterator(i).next().getValue() * (1 - hiddenLayer.listIterator(i).next().getValue()) * hiddenLayer.listIterator(i).next().getErrorFactor());

		}
	}

	private double sigmoide(double netValue){

		return  1 / (1 + Math.exp( -netValue));
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
		return hiddenSize;
	}


	public void setMean(int mean) {
		this.hiddenSize = mean;
	}





}
