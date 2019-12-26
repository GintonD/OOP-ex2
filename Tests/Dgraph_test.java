//package Tests;
//
//import java.util.Collection;
//import java.util.Iterator;
//
//import org.junit.jupiter.api.Test;
//
//import dataStructure.DGraph;
//import dataStructure.edge;
//import dataStructure.edge_data;
//import dataStructure.node_data;
//import dataStructure.vertex;
//import utils.Point3D;
//
//class Dgraph_test {
//
//	@Test
//	void test() {
//		
//	 //edge test_edge;
//	DGraph graph_test =new DGraph();
//
//	
////	vertex prev = null;
////	
////	for (int i=0; i<6 ;i++)
////	{
////	Point3D point= new Point3D(i, i); 
////		vertex vertex_Test = new vertex(point); //create vertex
////		graph_test.addNode(vertex_Test);
////		if (prev!= null) 
////		{
////			 test_edge = new edge(vertex_Test.getKey(),prev.getKey(), 5); //create edge
////			 graph_test.connect(vertex_Test.getKey(),prev.getKey(), 5); //connect edge to vertex
////		}
////		if (graph_test.edgeSize()%2==0 &&graph_test.edgeSize()>0)
////			 graph_test.connect(vertex_Test.getKey(),2, 5);
////		prev = vertex_Test ; //save the prev
////	}
////	System.out.println("the vertex is:");
////	Collection<node_data> vertex_collect = graph_test.getV();
////	Iterator<node_data> itr_vertex = vertex_collect.iterator();
//	/*for (node_data ver: vertex_collect) {
//		System.out.print(ver.getKey());
//		System.out.println("the edge is: ");
//		Collection<edge_data> edge_collect = graph_test.getE(ver.getKey());
//		Iterator<edge_data> itr_edge = edge_collect.iterator();
//		for (edge_data edge : edge_collect) {
//			System.out.print(edge.getDest());
//		}
//	}*/
//	vertex prev=null;
//
//	for (int i=0; i<6 ;i++)
//	{
//		Point3D point= new Point3D(i, i); 
//		vertex vertex_Test = new vertex(point); //create vertex
//		graph_test.addNode(vertex_Test);
//		if (prev!= null) 
//		{
//			edge test_edge = new edge(vertex_Test.getKey(),prev.getKey(), 5); //create edge
//			graph_test.connect(vertex_Test.getKey(),prev.getKey(), 5); //connect edge to vertex
//			
//		}
//		if (graph_test.edgeSize()%2==0 &&graph_test.edgeSize()>0)
//			graph_test.connect(vertex_Test.getKey(),2, 5);
//		prev = vertex_Test ; //save the prev
//	}
//	}
//	
//}


package Tests;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataStructure.DGraph;
import dataStructure.edge;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.vertex;
import dataStructure.node_data;
import utils.Point3D;

class Dgraph_test {

	//@BeforeEach
	
	@Test
	void testAddNode() {
		Point3D p1 = new Point3D(0, 0, 0);
		Point3D p2 = new Point3D(0, 1, 2);
		Point3D p3 = new Point3D(1, 2, 0);
		vertex n1 = new vertex(p1, 0);
		vertex n2 = new vertex(p2, 0);
		vertex n3 = new vertex(p3, 0);
		graph g = new DGraph();
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		if (g.nodeSize()!=3) { fail(); }
	}

	@Test
	void testConnect() {
		Point3D p1 = new Point3D(1, 5, 0);
		Point3D p2 = new Point3D(4, 4, 0);
		Point3D p3 = new Point3D(2, 2, 0);
		vertex n1 = new vertex(p1, 0);
		vertex n2 = new vertex(p2, 0);
		vertex n3 = new vertex(p3, 0);
		graph g = new DGraph();
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.connect(n1.getKey(), n2.getKey(), 2);
		g.connect(n2.getKey(), n3.getKey(), 3);

		if (g.edgeSize()!=2) { fail(); }
		if (g.getEdge(n2.getKey(), n3.getKey()).getWeight()!=3) { fail(); }	
	}

	@Test
	void testRemoveNode() {
		Point3D p1 = new Point3D(1, 5, 0);
		Point3D p2 = new Point3D(4, 4, 0);
		Point3D p3 = new Point3D(2, 2, 0);
		vertex n1 = new vertex(p1, 0);
		vertex n2 = new vertex(p2, 0);
		vertex n3 = new vertex(p3, 0);
		graph g = new DGraph();
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.connect(n1.getKey(), n2.getKey(), 2);
		g.connect(n2.getKey(), n3.getKey(), 3);
		
		g.removeNode(n2.getKey());
		if (g.edgeSize()!=0) { fail(); }
		try {
			g.getEdge(n2.getKey(), n3.getKey());
			fail();
		}catch (Exception e) {;}
		try {
			g.getEdge(n1.getKey(), n2.getKey());
			fail();
		}catch (Exception e) {;}
	}

	@Test
	void testRemoveEdge() {
		Point3D p1 = new Point3D(1, 5, 0);
		Point3D p2 = new Point3D(4, 4, 0);
		Point3D p3 = new Point3D(2, 2, 0);
		vertex n1 = new vertex(p1, 0);
		vertex n2 = new vertex(p2, 0);
		vertex n3 = new vertex(p3, 0);
		graph g = new DGraph();
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.connect(n1.getKey(), n2.getKey(), 2);
		g.connect(n2.getKey(), n3.getKey(), 3);
		
		g.removeEdge(n2.getKey(), n3.getKey());
		if (g.edgeSize()!=1) { fail(); }
		if (g.getEdge(n2.getKey(), n3.getKey()) != null) { fail();}
		if (g.getEdge(n1.getKey(), n2.getKey()) == null) { fail();}
	}
	
	
	
	vertex[] v = {
			new vertex(new Point3D(1,2)),
			new vertex(new Point3D(2,2)),
			new vertex(new Point3D(1,3)),
			new vertex(new Point3D(1,4)),
			new vertex(new Point3D(3,5))};
	edge[] e = {
			new edge(v[0].getKey(), v[1].getKey(), 3),
			new edge(v[0].getKey(), v[2].getKey(), 2),
			new edge(v[0].getKey(), v[3].getKey(), 7),
			new edge(v[1].getKey(), v[2].getKey(), 9),
			new edge(v[1].getKey(), v[4].getKey(), 5),
			new edge(v[4].getKey(), v[0].getKey(), 4)};
	DGraph g = new DGraph();

	@BeforeEach
	void setUp() throws Exception {
		for(int i=0; i<v.length; i++)
			g.addNode(v[i]);
		for(int i = 0; i<e.length; i++) {
			g.connect(e[i].getSrc(), e[i].getDest(), e[i].getWeight());
		}
	}

	
	
	
	
	@Test
	void testGetNode() {
		int key = v[0].getKey();
		node_data n = g.getNode(key);
		assertEquals(n, v[0]);
	}

//	@Test
//	void testGetEdge() 
//	{
//		edge edge = e[0];
//		edge_data edgeAns = g.getEdge(e[0].getSrc(), e[0].getDest());
//		assertEquals(edgeAns, edge);
//	}

	@Test
	void testAddNode1() {
		node_data actual = new vertex(new Point3D(3,3));
		g.addNode(actual);
		node_data expected = g.getNode(actual.getKey()) ;
		assertEquals(expected, actual);
	}

//	@Test
//	void testConnect1() {
//		int src = v[2].getKey();
//		int dest = v[3].getKey();
//		edge_data actual = new edge(src, dest);
//		g.connect(src, dest, 22);
//		edge_data expected = g.getEdge(src, dest);
//		assertEquals(expected, actual);
//	}

	@Test
	void testGetV1() {
		Collection<node_data> c = g.getV();
		int expected = c.size();
		int actual = g.nodeSize();
		assertEquals(expected, actual);
	}

	@Test
	void testGetE() {
		Collection<edge_data> c = g.getE(v[0].getKey());
		int expected = c.size();
		int actual = 3;
		assertEquals(expected, actual);
		c = g.getE(v[1].getKey());
		expected = c.size();
		actual = 2;
		assertEquals(expected, actual);
	}

	@Test
	void testRemoveNode1() {
		int nodeSize = g.nodeSize();
		int edgeSize = g.edgeSize();
		g.removeNode(v[0].getKey());
		int expected = nodeSize-1;
		int actual = g.nodeSize();
		assertEquals(expected, actual);
		expected = edgeSize-4;
		actual = g.edgeSize();
		assertEquals(expected, actual);
		
	}

	@Test
	void testRemoveEdge1() {
		edge_data edge = e[0];
		int edgeSize = g.edgeSize();
		g.removeEdge(edge.getSrc(), edge.getDest());
		int expected = edgeSize-1;
		int actual = g.edgeSize();
		assertEquals(expected, actual);
		edge_data edge2 = g.getEdge(edge.getSrc(), edge.getDest());
		assertEquals(edge2, null);
		
	}

	@Test
	void testNodeSize() {
		int actual = v.length;
		int expected = g.nodeSize();
		assertEquals(expected, actual);
	}

	@Test
	void testEdgeSize() {
		int ans = g.edgeSize();
		int actual = e.length;
		assertEquals(ans, actual);
	}



}
