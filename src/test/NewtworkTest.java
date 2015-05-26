package test;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import neural.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class NewtworkTest {

	@Test
	public void propagationTest() {
		Network net = new Network(3,2,0.5,0.5,2);
		RandomAccessFile File;
		
		ArrayList<ArrayList<Double>> outer = new ArrayList<ArrayList<Double>>();
		
		try {
			File = new RandomAccessFile("default_features_1059_tracks.txt", "r");
			String line;
		 while ((line = File.readLine()) != null) {
			 
			 
			 String[] split = line.split(",");
			 ArrayList<Double> inner = new ArrayList<Double>();
			 for (int i = 0; i<split.length;i++){
				 Double value = Double.parseDouble(split[i]);
				 inner.add(value);
			 }
			 outer.add(inner);
}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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

}
