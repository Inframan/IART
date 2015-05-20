package neural;

import java.util.ArrayList;

public class Network {
	private ArrayList<Neuron> inputLayer, outputLayer;
	private int inputSize, outputSize, hiddenLayersNumber;
	private ArrayList<Integer> hiddenSizes;
	private ArrayList<ArrayList<Neuron>> hiddenLayers;
	private double learningRate;
	private double minimalNetValue;
	

	public Network(int inputNumber,int outputNumber, double learningRate , double minNetValue, int hiddenLayersNumber) {
		// TODO Auto-generated constructor stub
		inputSize = inputNumber;
		outputSize = outputNumber;
		this.hiddenLayersNumber = hiddenLayersNumber;
		this.learningRate = learningRate;
		minimalNetValue = minNetValue;
		
		inputLayer = new ArrayList<Neuron>();
		hiddenLayers = new ArrayList<ArrayList<Neuron>>();
		outputLayer = new ArrayList<Neuron>();
		hiddenSizes = new ArrayList<Integer>();

		for(int i = 0; i < inputSize; i++)
		{
			inputLayer.add(new Neuron((inputNumber + outputNumber)/2));//tamanho da primeira camada escondida
		}
		

		for(int i = 0; i < hiddenLayersNumber; i++)
		{
			int neuronsSize;
			ArrayList<Neuron> tempLayer = new ArrayList<Neuron>();
			if(i == 0)
				neuronsSize = (inputNumber + outputNumber)/2;
			else
				neuronsSize = (hiddenSizes.listIterator(i-1).next() + outputNumber)/2;
			hiddenSizes.add(neuronsSize);
			for(int j = 0; j < neuronsSize; j++)
			{
				tempLayer.add(new Neuron((neuronsSize + outputNumber)/2));
			}
			hiddenLayers.add(tempLayer);
		}

		for(int i = 0; i < outputSize; i++)
		{
			outputLayer.add(new Neuron(0));
		}
	}
	
	
	private void hiddenFrontPropagation()
	{
		for(int i = 0; i < hiddenLayersNumber -1;i++)//até à penúltima camada
		{
			
			for(int j = 0; j < hiddenSizes.listIterator(i).next();j++)
			{
				for(int k = 0; k < hiddenSizes.listIterator(i+1).next(); k++)
				{
					double netValue = hiddenLayers.listIterator(i).next().listIterator(j).next().getWeights().listIterator(k).next() * hiddenLayers.listIterator(i).next().listIterator(j).next().getValue();
					netValue += hiddenLayers.listIterator(i).next().listIterator(j).next().getBias();
					
					if(netValue >= minimalNetValue)
					{
						hiddenLayers.listIterator(i+1).next().listIterator(k).next().addValue(sigmoide(netValue));
					}
							
					
					
				}
			}
			
			
		}
		
	}
	
	

	public void frontPropagation(double inputValues[])
	{
		for(int i = 0; i < inputValues.length;i++)
		{
			inputLayer.listIterator(i).next().setValue(inputValues[i]);		
			for(int j = 0; j < hiddenSizes.listIterator(0).next();j++)
			{	
				double netValue = inputLayer.listIterator(i).next().getWeights().listIterator(j).next() * inputValues[i];
				netValue += inputLayer.listIterator(i).next().getBias();
				
				if(netValue >= minimalNetValue)
					hiddenLayers.listIterator(0).next().listIterator(j).next().addValue(sigmoide(netValue));//primeira camada
			}
		}

		
		hiddenFrontPropagation();
		
		for(int i = 0; i < hiddenSizes.listIterator(hiddenLayersNumber-1).next();i++)
		{
			for(int j = 0; j < outputSize;j++)
			{
				double netValue =  hiddenLayers.listIterator(hiddenLayersNumber-1).next().listIterator(i).next().getWeights().listIterator(j).next() * inputLayer.listIterator(i).next().getValue();
				netValue +=  hiddenLayers.listIterator(hiddenLayersNumber-1).next().listIterator(i).next().getBias();
				
				if(netValue >= minimalNetValue)
					outputLayer.listIterator(j).next().addValue(sigmoide(netValue));
			}

		}
	}

	public void backPropagation(double ExpectedOutputValues[]){
		updateDeltas(ExpectedOutputValues);
		updateWeightBias();

	}
	
	private void updateHiddenDeltas()
	{
		for(int i = hiddenLayersNumber -2; i > -1; i--)
		{
			for(int j = 0; j < hiddenSizes.listIterator(i).next();j++)
			{
				for(int k = 0; k < hiddenSizes.listIterator(i+1).next();k++)
				{
					hiddenLayers.listIterator(i).next().listIterator(j).next().addErrorFactor(hiddenLayers.listIterator(i+1).next().listIterator(k).next().getDelta() * hiddenLayers.listIterator(i).next().listIterator(k).next().getWeights().listIterator(j).next());  
				}
				hiddenLayers.listIterator(i).next().listIterator(j).next().setDelta(hiddenLayers.listIterator(i).next().listIterator(j).next().getValue() * (1 - hiddenLayers.listIterator(i).next().listIterator(j).next().getValue()) * hiddenLayers.listIterator(i).next().listIterator(j).next().getErrorFactor());
				
			}
		}
	}
	
	private void updateDeltas(double ExpectedOutputValues[]){

		for(int i = 0 ; i < outputSize; i++){
			outputLayer.listIterator(i).next().setErrorFactor(ExpectedOutputValues[i] - outputLayer.listIterator(i).next().getValue());			
			outputLayer.listIterator(i).next().setDelta(outputLayer.listIterator(i).next().getValue() * (1 - outputLayer.listIterator(i).next().getValue()) * outputLayer.listIterator(i).next().getErrorFactor());
		}

		
		////Last hidden Layer
		for( int i = 0 ; i < hiddenSizes.listIterator(hiddenLayersNumber -1).next(); i++)
		{
			for (int j=0 ; j < outputSize;j++)
				hiddenLayers.listIterator(hiddenLayersNumber -1).next().listIterator(i).next().addErrorFactor(outputLayer.listIterator(j).next().getDelta() * hiddenLayers.listIterator(hiddenLayersNumber -1).next().listIterator(i).next().getWeights().listIterator(j).next());			
			hiddenLayers.listIterator(hiddenLayersNumber -1).next().listIterator(i).next().setDelta(hiddenLayers.listIterator(hiddenLayersNumber -1).next().listIterator(i).next().getValue() * (1 - hiddenLayers.listIterator(hiddenLayersNumber -1).next().listIterator(i).next().getValue()) * hiddenLayers.listIterator(hiddenLayersNumber -1).next().listIterator(i).next().getErrorFactor());
		}
		
		updateHiddenDeltas();
		
	}

	
	private void updateHiddenWeightBias()
	{
		for(int i = 0; i < hiddenLayersNumber-2; i++)
		{
			for(int j = 0; j < hiddenSizes.listIterator(i).next();j++)
			{
				for(int k = 0;k < hiddenSizes.listIterator(i+1).next();k++)
				{
					hiddenLayers.listIterator(i).next().listIterator(i).next().getWeights().listIterator(j).set( hiddenLayers.listIterator(i).next().listIterator(i).next().getWeights().listIterator(j).next() +
							learningRate * 	hiddenLayers.listIterator(i+1).next().listIterator(j).next().getValue()*hiddenLayers.listIterator(i+1).next().listIterator(j).next().getDelta());
			
				}
				hiddenLayers.listIterator(i).next().listIterator(i).next().updateBias(learningRate);
				
			}
			
			
			
		}

		//última camada
		for(int i = 0; i < hiddenSizes.listIterator(hiddenLayersNumber-1).next(); i++)
		{
			for(int j = 0; j < outputSize; j++)
			{
				hiddenLayers.listIterator(hiddenLayersNumber -1).next().listIterator(i).next().getWeights().listIterator(j).set( hiddenLayers.listIterator(hiddenLayersNumber -1).next().listIterator(i).next().getWeights().listIterator(j).next() +
						learningRate * 	outputLayer.listIterator(j).next().getValue()*outputLayer.listIterator(j).next().getDelta());
			}

			hiddenLayers.listIterator(hiddenLayersNumber -1).next().listIterator(i).next().updateBias(learningRate);
		}
		
		
		
	}

	private void updateWeightBias()
	{
		for(int i = 0; i < inputSize; i++)
		{	
			for(int j = 0; j < hiddenSizes.listIterator(hiddenLayersNumber-1).next(); j++)
			{
				inputLayer.listIterator(i).next().getWeights().listIterator(j).set( inputLayer.listIterator(i).next().getWeights().listIterator(j).next() +
						learningRate * 	hiddenLayers.listIterator(hiddenLayersNumber -1).next().listIterator(j).next().getValue()*hiddenLayers.listIterator(hiddenLayersNumber -1).next().listIterator(j).next().getDelta());
			}

			inputLayer.listIterator(i).next().updateBias(learningRate);
		}

		updateHiddenWeightBias();
		
		for(int i = 0; i < outputSize; i++)
		{
			outputLayer.listIterator(i).next().updateBias(learningRate);
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

	
	
	public int getHiddenLayersNumber() {
		return hiddenLayersNumber;
	}


	public void setHiddenLayersNumber(int hiddenLayersNumber) {
		this.hiddenLayersNumber = hiddenLayersNumber;
	}


	public ArrayList<Integer> getHiddenSizes() {
		return hiddenSizes;
	}


	public void setHiddenSizes(ArrayList<Integer> hiddenSizes) {
		this.hiddenSizes = hiddenSizes;
	}


	public ArrayList<ArrayList<Neuron>> getHiddenLayers() {
		return hiddenLayers;
	}


	public void setHiddenLayers(ArrayList<ArrayList<Neuron>> hiddenLayers) {
		this.hiddenLayers = hiddenLayers;
	}


	public double getLearningRate() {
		return learningRate;
	}


	public void setLearningRate(double learningRate) {
		this.learningRate = learningRate;
	}


	public double getMinimalNetValue() {
		return minimalNetValue;
	}


	public void setMinimalNetValue(double minimalNetValue) {
		this.minimalNetValue = minimalNetValue;
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

}
