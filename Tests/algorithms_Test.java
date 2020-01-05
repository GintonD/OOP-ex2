package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;


import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.node_data;
import dataStructure.vertex;
import utils.Point3D;

class algorithms_Test  {


	@Test
	void test() {
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
		for (int i = 0; i < actualList.size() && i< expecteddList.size(); i++) {
			assertEquals(expecteddList.get(i).getKey(), actualList.get(i).getKey());
		}
		// Tsp
		List <Integer> targets = new LinkedList<Integer>();
		targets.add(1);
		targets.add(4);
		targets.add(7);
		actualList =algo_Graph.TSP(targets);
		expecteddList.clear();
		for (int i = 1; i < 8; i++) {
			expecteddList.add(gr.getNode(i));
		}
		for (int i = 0; i < actualList.size(); i++) {
			System.out.print(actualList.get(i).getKey()+ " ");
		}
		// assert
		for (int i = 0; i < actualList.size(); i++) {
			assertEquals(expecteddList.get(i).getKey(), actualList.get(i).getKey());
		}
	}
	DGraph create() {
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
