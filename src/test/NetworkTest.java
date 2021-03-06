package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import neural.Network;
import neural.Neuron;

import org.junit.Test;

import fileReader.FileReader;

public class NetworkTest {
	
	@Test
	public void propagationTest() {
		Network net = new Network(3,2,0.5,1,"default_features_1059_tracks.txt");


		double input[] = {1.0, 1.3, 1.5};

		net.frontPropagation(input);

		assertEquals("1st Output Expected: ", 0.5, net.getOutputLayer().listIterator(0).next().getValue(), 0.3);

		assertEquals("1st Output Expected: ", 0.5, net.getOutputLayer().listIterator(0).next().getValue(), 0.3);


	}
	@Test
	public void initialBiasTest(){

		Neuron n = new Neuron(0);

		System.out.println("bias:" + n.getBias());
		assertEquals("random bias:",0.5,n.getBias(),0.5);
	}


	@Test
	public void readTest(){


		FileReader f = new FileReader("default_features_1059_tracks.txt");

		ArrayList<ArrayList<Double>> def = f.read();

		for(int i = 0; i < def.size(); i++)
			assertEquals("Expected size index "+i+" : ", 70, def.listIterator(i).next().size(), 0);

		f.setFilename("default_plus_chromatic_features_1059_tracks.txt");

		ArrayList<ArrayList<Double>> chromatic = f.read();
		for(int i = 0; i < chromatic.size(); i++)
			assertEquals("Expected size index "+i+" : ", 118, chromatic.listIterator(i).next().size(), 0);


	}


	@Test
	public void createExpected()
	{
		FileReader f = new FileReader("default_features_1059_tracks.txt");

		ArrayList<Double[]> capitals = capitalsReader(f);


		assertEquals("Expected Size Output : ", 33, capitals.size(), 0);

	}
	 
	private ArrayList<Double[]> capitalsReader(FileReader f) {
		ArrayList<Double []> capitals = new ArrayList<Double []>();
		ArrayList<ArrayList<Double>> def = f.read();



		for(int i = 0; i < def.size(); i++)
		{
			boolean inArray = false;;
			Double coords[] = {def.listIterator(i).next().listIterator(def.listIterator(i).next().size()-2).next() , def.listIterator(i).next().listIterator(def.listIterator(i).next().size()-1).next() };
			for(int j = 0; j < capitals.size();j++)
			{
				if(capitals.listIterator(j).next()[0].equals(coords[0]) && capitals.listIterator(j).next()[1].equals(coords[1]) )
				{
					inArray = true;
					break;
				}

			}

			if(!inArray)
				capitals.add(coords);
		}
		return capitals;
	}


	@Test
	public void RunApp(){

		Network n = new Network(116, 33, 0.5, 1, "default_plus_chromatic_features_1059_tracks.txt" );
		FileReader f = new FileReader("default_plus_chromatic_features_1059_tracks.txt");

		ArrayList<Double[]> capitals = capitalsReader(f);

		ArrayList<ArrayList<Double>> def = f.read();
		ArrayList<Double> errors = new ArrayList<Double>();

		for (int l = 0 ; l < 800; l++){
			
			/*
			if ( l == 799)
			{
				System.out.println("cenas");
			}
			*/
			
			errors.clear();
			for (int i = 0 ; i < def.size() ; i++){
				
				

				n.resetErrors();

				ArrayList<Double> inputs = new ArrayList<Double>();
				for(int k = 0; k < def.listIterator(i).next().size() - 2;k++)
					inputs.add( def.listIterator(i).next().listIterator(k).next());

				double normalInputs[] = new double[inputs.size()];
				for(int index = 0;index < inputs.size();index++)
					normalInputs[index] = inputs.listIterator(index).next();
				
				
				n.frontPropagation(normalInputs);


				double coords[] = {def.listIterator(i).next().listIterator(def.listIterator(i).next().size()-2).next() , def.listIterator(i).next().listIterator(def.listIterator(i).next().size()-1).next() };
				double expectedOutput[] = new double[33];

				int j = 0;

				for(; j < capitals.size();j++)
				{
					if(capitals.listIterator(j).next()[0].equals(coords[0]) && capitals.listIterator(j).next()[1].equals(coords[1]) )
						expectedOutput[j] = 1;
					else
						expectedOutput[j] = 0;
				}


				if(l == 199)
					System.out.println(l);

				n.backPropagation(expectedOutput);

				errors.add(n.errorsAvg());

			}
		}

		double avgError = 0;
		

		for(int i = 0; i < errors.size();i++)
		{
			avgError += errors.listIterator(i).next();
		}

		avgError *= 1.0/(2*def.size());

		double outputsum = n.outputSum();
		System.out.println("error: " + avgError);
		System.out.println("output sum: "+ outputsum);

		assertTrue("End error: expected positive", avgError >= 0);
		assertTrue("End error: error must be < 0.0001", avgError <= 0.0001);
		assertEquals("Output sum: ", 1 , outputsum , 0.2);


		
		//calcular media do erro
		// if erro << 0,0001  -> acabar
		// recomešar do inicio


	}
}

