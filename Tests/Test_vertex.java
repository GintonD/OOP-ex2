package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

//import org.junit.jupiter.api.Test;

import dataStructure.vertex;
import utils.Point3D;

class Test_vertex {

	@Test
	void test() 
	{
	Point3D p = new Point3D(5.0,3.2);
	vertex test = new vertex (p,2);	
	
	System.out.println(test.getWeight());
	double key_exepted = test.getWeight();
	//assert key
	assertEquals (2, test.getWeight());
	// assert point
	assertEquals(p, test.getLocation());
	// assert 
	
	}

}
