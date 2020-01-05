package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.graph;
import dataStructure.node_data;
import dataStructure.vertex;
import dataStructure.edge;
import utils.Point3D;

class Graph_AlgoTest 
{

	
	Point3D p1 = new Point3D(99, 95);
	Point3D p2 = new Point3D(203, 96);
	Point3D p3 = new Point3D(154, 152);
	Point3D p4 = new Point3D(455, 151);
	Point3D p5 = new Point3D(687, 206);
	Point3D p6 = new Point3D(142, 702);
	Point3D p7 = new Point3D(232, 437);
	Point3D p8 = new Point3D(191, 602);
	Point3D p9 = new Point3D(119, 432);
	vertex[] v = {
			new vertex(p1),
			new vertex(p2),
			new vertex(p3),
			new vertex(p4),
			new vertex(p5),
			new vertex(p6),
			new vertex(p7),
			new vertex(p8),
			new vertex(p9),
	};
	edge[] e = {
			new edge(v[0].getKey(), v[1].getKey(), 1),
			new edge(v[0].getKey(), v[5].getKey(), 2),
			new edge(v[0].getKey(), v[8].getKey(), 3),
			new edge(v[1].getKey(), v[2].getKey(), 9),
			new edge(v[1].getKey(), v[5].getKey(), 2),
			new edge(v[5].getKey(), v[2].getKey(), 3),
			new edge(v[5].getKey(), v[6].getKey(), 1),
			new edge(v[5].getKey(), v[8].getKey(), 5),
			new edge(v[8].getKey(), v[7].getKey(), 7),
			new edge(v[7].getKey(), v[6].getKey(), 4),
			new edge(v[6].getKey(), v[4].getKey(), 20),
			new edge(v[2].getKey(), v[3].getKey(), 4),
			new edge(v[3].getKey(), v[4].getKey(), 5)};
	Graph_Algo ga = new Graph_Algo();
	DGraph g = new DGraph();
	DGraph connected = new DGraph();

	vertex[] vc = {
			new vertex(p1),
			new vertex(p2),
			new vertex(p3),
			new vertex(p4),

	};
	edge[] ec = {
			new edge(vc[0].getKey(), vc[1].getKey(), 1),
			new edge(vc[1].getKey(), vc[2].getKey(), 2),
			new edge(vc[2].getKey(), vc[3].getKey(), 3),
			new edge(vc[3].getKey(), vc[0].getKey(), 9),
			new edge(vc[0].getKey(), vc[2].getKey(), 2)};



	@BeforeEach
	void setUp() throws Exception 
	{
		for(int i=0; i<v.length; i++)
			g.addNode(v[i]);
		for(int i = 0; i<e.length; i++) 
		{
			g.connect(e[i].getSrc(), e[i].getDest(), e[i].getWeight());
		}
		ga.init(g);
		for(int i=0; i<vc.length; i++)
			connected.addNode(vc[i]);
		for(int i = 0; i<ec.length; i++) 
		{
			connected.connect(ec[i].getSrc(), ec[i].getDest(), ec[i].getWeight());
		}
	}

	@Test
	void testInitString() 
	{
		graph actual = ga.copy();
		ga.save("graph");
		ga.init("graph");
		graph expected = ga.copy(); 
		ga.init(connected);
		actual = ga.copy();
		ga.save("graph");
		ga.init("graph");
		expected = ga.copy();
		
	}

	@Test
	void testIsConnected() 
	{
		boolean expected = ga.isConnected();
		boolean actual = false;
		assertEquals(expected, actual);
		ga.init(connected);
		expected = ga.isConnected();
		actual = true;
		assertEquals(expected, actual);
	}

	@Test
	void testShortestPathDist() 
	{
		ga.init(g);
		double expected = ga.shortestPathDist(v[0].getKey(), v[4].getKey());
		double actual = 14;
		assertEquals(expected, actual);
	}

	@Test
	void testShortestPath() 
	{
		List<node_data> expected =  ga.shortestPath(v[0].getKey(), v[4].getKey());
		ArrayList<node_data> actual = new ArrayList<node_data>();
		actual.add(v[0]);
		actual.add(v[5]);
		actual.add(v[2]);
		actual.add(v[3]);
		actual.add(v[4]);
		assertEquals(expected, actual);
	}

	@Test
	void testTSP() 
	{
		ArrayList<Integer> targets1 = new ArrayList<Integer>();
		ArrayList<Integer> targets2 = new ArrayList<Integer>();
		ga.init(connected);
		targets1.add(vc[0].getKey());
		targets1.add(vc[1].getKey());
		targets1.add(vc[2].getKey());
		targets1.add(vc[3].getKey());
		List<node_data> expected =   ga.TSP(targets1);
		assertEquals(expected.size(), 4);
		ga.init(g);
		targets2.add(v[0].getKey());
		targets2.add(v[1].getKey());
		targets2.add(v[2].getKey());
		targets2.add(v[3].getKey());
		expected = ga.TSP(targets2);
		assertEquals(expected.size(), 5);
	}



	@Test
	void test2() 
	{
		//create
		DGraph gr = create();
		Graph_Algo algo_Graph =new Graph_Algo();
		algo_Graph.init(gr);
		//connected
		boolean excepted = algo_Graph.isConnected(); //false
		assertTrue(!excepted);
		gr.connect(7, 1, 10);
		excepted = algo_Graph.isConnected(); //true 
		assertTrue(excepted);
		// short path
		double dist = algo_Graph.shortestPathDist(1, 7);
		System.out.println(dist);
		assertEquals(75.0, dist);
		 dist  = algo_Graph.shortestPathDist(7, 4);
		System.out.println(dist);
		assertEquals(43.0, dist);
		// assert list
		List<node_data> expecteddList = new LinkedList<node_data>();
		expecteddList.add(gr.getNode(7));
		for (int i = 1; i <4 ; i++) {
			expecteddList.add(gr.getNode(i));
		}
	
		List<node_data> actualList = algo_Graph.shortestPath(7, 4);
		for (int i = 0; i < actualList.size() && i< expecteddList.size(); i++) 
		{
			assertEquals(expecteddList.get(i).getKey(), actualList.get(i).getKey());
		}
		// Tsp
		List <Integer> targets = new LinkedList<Integer>();
		targets.add(1);
		targets.add(4);
		targets.add(7);
		actualList =algo_Graph.TSP(targets);
		expecteddList.clear();
		for (int i = 1; i < 8; i++) 
		{
			expecteddList.add(gr.getNode(i));
		}
		for (int i = 0; i < actualList.size(); i++) 
		{
			System.out.print(actualList.get(i).getKey()+ " ");
		}
		// assert
		for (int i = 0; i < actualList.size(); i++) 
		{
			assertEquals(expecteddList.get(i).getKey(), actualList.get(i).getKey());
		}
	}
	
	
	
	
	
	DGraph create() 
	{
	DGraph gr = new DGraph();
	vertex[] v = new vertex[7];
	for(int i =0; i<v.length; i++) {
		int rx = (int)(Math.random()*350+80);
		int ry = (int)(Math.random()*350+80);
		Point3D p =new Point3D(rx,ry);
		v[i] = new vertex(p);
		gr.addNode(v[i]);
	}
	for(int i = 0; i<6; i++) {
		gr.connect(v[i].getKey(), v[i+1].getKey(), 10+i);
	}
	return gr;
	}
}
