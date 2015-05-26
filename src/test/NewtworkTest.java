package test;

import java.util.ArrayList;

import neural.*;
import fileReader.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class NewtworkTest {

	@Test
	public void propagationTest() {
		Network net = new Network(3,2,0.5,0.5,2);


		double input[] = {1.0, 1.3, 1.5};

		net.frontPropagation(input);

		assertEquals("1st Output Expected: ", 1.0, net.getOutputLayer().listIterator(0).next().getValue(), 1.3);

		assertEquals("1st Output Expected: ", 2.0, net.getOutputLayer().listIterator(0).next().getValue(), 1.3);


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

		ArrayList<Double []> capitals = new ArrayList<Double []>();
		ArrayList<ArrayList<Double>> def = f.read();

		
		
		for(int i = 0; i < def.size(); i++)
		{
			boolean inArray = false;;
			Double coords[] = {def.listIterator(i).next().listIterator(68).next() , def.listIterator(i).next().listIterator(69).next() };
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

		
		assertEquals("Expected Size Output : ", 33, capitals.size(), 0);

	}


}
