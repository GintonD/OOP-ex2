package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dataStructure.edge;
import dataStructure.vertex;
import utils.Point3D;

class edgeTest
{
	Point3D p1 = new Point3D(99, 95);
	Point3D p2 = new Point3D(203, 96);
	vertex v1= new vertex(p1);
	vertex v2 = new vertex(p2);
	private edge e1 = new edge(v1.getKey(),v2.getKey(),90);
	private edge e2 = new edge(v2.getKey(),v1.getKey(),56);




	@Test
	void testGetSrc() 
	{
		int ans1 = e1.getSrc();
		int ans2 = e2.getSrc();
		assertEquals(5, ans1);
		assertEquals(6, ans2);
	}

	@Test
	void testGetDest() 
	{
		int ans1 = e1.getDest();
		int ans2 = e2.getDest();
		assertEquals(2, ans1);
		assertEquals(1, ans2);
	}



	@Test
	void testGetInfo() {
		String s = "new edge created";
		e1.setInfo(s);
		String ans = e1.getInfo();
		assertEquals(ans, s);
	}

	@Test
	void testGetTag() {
		e1.setTag(1);
		e2.setTag(2);
		int ans1 = e1.getTag();
		int ans2 = e2.getTag();
		assertEquals(1, ans1);
		assertEquals(2, ans2);
	}




}
