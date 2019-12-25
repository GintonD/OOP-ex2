package Tests;

import java.util.Collection;
import java.util.Iterator;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.edge;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import dataStructure.vertex;
import utils.Point3D;

public class Test_algo {

	public static void main(String[] args) {
		graph g =create();
		// is_connect
		
		
		Graph_Algo test_algo =new Graph_Algo();
		test_algo.init(g);
		graph gCopy = test_algo.copy();
		System.out.println("is connect? " + test_algo.isConnected() ); //true
//		vertex v3= new vertex(new Point3D(1, 2));
//		graph_test.addNode(v3);
		System.out.println("is connect? " + test_algo.isConnected() ); //false
	}

	public  static graph create () {
		DGraph graph_test =new DGraph();


		vertex prev = null;

		for (int i=0; i<2 ;i++)
		{
			Point3D point= new Point3D(i, i); 
			vertex vertex_Test = new vertex(point); //create vertex
			graph_test.addNode(vertex_Test);
			if (prev!= null) 
			{
				edge test_edge = new edge(vertex_Test.getKey(),prev.getKey(), 5); //create edge
				graph_test.connect(vertex_Test.getKey(),prev.getKey(), 5); //connect edge to vertex
				
			}
			if (graph_test.edgeSize()%2==0 &&graph_test.edgeSize()>0)
				graph_test.connect(vertex_Test.getKey(),2, 5);
			prev = vertex_Test ; //save the prev
		}
//		graph_test.connect(1,3, 5);
//		System.out.println("the vertex is: ");
		Collection<node_data> vertex_collect = graph_test.getV();
		Iterator<node_data> itr_vertex = vertex_collect.iterator();
		for (node_data ver: vertex_collect) {
			System.out.println("the vertex is: ");
			System.out.println(ver.getKey());
			System.out.println ("the edge is: ");
			
				Collection<edge_data> edge_collect = graph_test.getE(ver.getKey());
				Iterator<edge_data> itr_edge = edge_collect.iterator();
				for (edge_data edge : edge_collect) {
					System.out.println(edge.getDest());
				}
			
		}
		return graph_test;
	}

	}


