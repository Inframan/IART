package test;
import neural.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class NewtworkTest {

	@Test
	public void propagationTest() {
		Network net = new Network(3,2,0.5,0.5);
		double input[] = {1.0, 1.3, 1.5};
		
		net.frontPropagation(input);
		
		assertEquals("1st Output Expected: ", 1.0, net.getOutputLayer().listIterator(0).next().getValue(), 1.3);
		
		assertEquals("1st Output Expected: ", 2.0, net.getOutputLayer().listIterator(0).next().getValue(), 1.3);
		
	}

}
