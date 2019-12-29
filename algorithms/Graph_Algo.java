package algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import dataStructure.vertex;
import utils.Point3D;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{
	graph graph_alg;
	//
//	constractur
	public Graph_Algo () {
		DGraph graph_algo = new DGraph();
	}
	@Override
	public void init(graph g) {
	this.graph_alg = g; // try	
	
	}

	@Override
	public void init(String file_name) {
		 try 
	        {
	        	BufferedReader br = new BufferedReader(new FileReader(file_name));
	        	
	         

	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	            System.out.println("could not read file");
	        }

	    }
	

	@Override
	public void save(String file_name) {
		// TODO Auto-generated method stub
		
	}

	public boolean isConnected(int src, int dest) {
		// Step 1: Mark all the vertices as not visited 
//		if (exsictVertex(src, dest))
				if (dfs_tovertex(this.graph_alg.getNode(src), dest)==1)
					return true;
				return false;
	}
	@Override
	public boolean isConnected() {
		// Step 1: Mark all the vertices as not visited 
		unvisited();
		// Step 2: Do DFS traversal starting from first vertex. 
		dfs ( this.graph_alg.getNode(1));
		// If DFS traversal doesn't visit all vertices, then 
		// return false. 
		Collection<node_data> vertex_collect = this.graph_alg.getV();
		for (node_data vertex: vertex_collect) {
			if (vertex.getTag()==0)
				return false;

		}
		// check all the vertex can go to vertex 1
		for (node_data vertex: vertex_collect) {
			unvisited();
			if  (dfs_tovertex(vertex,1)==0)
				return false;
		}
		return true;
	}
	


	
	@Override
	public double shortestPathDist(int src, int dest) {
		if (isConnected(src, dest)) {
		Dijkstras2(src, dest);
		System.out.println("the List is: "+this.graph_alg.getNode(dest).getInfo());
		return this.graph_alg.getNode(dest).getWeight();
		}
		else {
			System.out.println("isnot connected");
			return Double.MAX_VALUE;
		}
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		LinkedList<node_data> list = new LinkedList<node_data>();
		if (isConnected(src, dest)) {
			Dijkstras2(src, dest);		
			String path_List =this.graph_alg.getNode(dest).getInfo();
			
				
			
			return list;
	}
		System.out.println("isn't connected");
		return null;
	}
	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public graph copy() {
		// copy the vertex to Map
		graph copy =new DGraph();
		Collection<node_data> c =graph_alg.getV();
		Iterator<node_data> iter1 = c.iterator();
		for (node_data ver: c)
		{
			// add to vertex
			copy.addNode(ver);
			// copy the edges
			Collection<edge_data> c2 =graph_alg.getE(ver.getKey());
			for (edge_data edge : c2)
				// connect vertex to vertex
			{	
				copy.connect(ver.getKey(), edge.getDest(), edge.getWeight());
			}
		}
		return copy;
	}
	
	// ******    help function *****
	
	// set Infinity all the edge vertex
		private void weightInfin() {
			Collection<node_data> vertex_collect = this.graph_alg.getV();
			for (node_data vertex: vertex_collect) {
				vertex.setWeight(Double.POSITIVE_INFINITY);
			}
		}
		private void unvisited() {
			Collection<node_data> vertex_collect = this.graph_alg.getV();
			for (node_data vertex: vertex_collect) {
				vertex.setTag(0);
			}
		}
	private int dfs_tovertex(node_data node,int dest) {
		node.setTag(1); //visited 
if (node.getKey()==dest) { 
	this.graph_alg.getNode(1).setTag(1);
	return 1;
}
Collection<edge_data> edge_collect = this.graph_alg.getE(node.getKey()); // the collect edge of current vertex
for (edge_data edge : edge_collect) {
	int next_vertex = edge.getDest();
	int tag = this.graph_alg.getNode(next_vertex).getTag();
	if (tag ==0)
	dfs_tovertex(this.graph_alg.getNode(next_vertex),dest);

}
return this.graph_alg.getNode(1).getTag() ;
}
private void dfs(node_data node) {
node.setTag(1); //visited 
Collection<edge_data> edge_collect = this.graph_alg.getE(node.getKey()); // the collect edge of current vertex
for (edge_data edge : edge_collect) {
	int next_vertex = edge.getDest();
	if (this.graph_alg.getNode(next_vertex).getTag()!=1) {
		dfs (this.graph_alg.getNode(next_vertex));
	}

}
}
public void Dijkstras2 (int src, int dest) {
	unvisited();
	weightInfin ();
	this.graph_alg.getNode(src).setWeight(0.);
	this.graph_alg.getNode(src).setInfo(""+ graph_alg.getNode(src).getKey());
	PriorityQueue<node_data> queueOfList = new PriorityQueue<node_data>(new The_Comparator());
//Queue<node_data> queueOfList = new LinkedList<node_data>();

	queueOfList.add(this.graph_alg.getNode(src));
//	for (node_data vertex: this.graph_alg.getV()) {
//		queueOfList.add(vertex);
//	}
	while (!queueOfList.isEmpty() && this.graph_alg.getNode(dest).getTag()==0) {
		node_data v1 = queueOfList.poll();
		// Visit each edge exiting vertex
		for (edge_data edge : this.graph_alg.getE(v1.getKey())) {
			node_data v2 = this.graph_alg.getNode(edge.getDest());
			// add to queue for next check
			queueOfList.add(v2);
			if (v2.getTag()==0) { //unvisited 
				double distV2 = v1.getWeight() + edge.getWeight();				
			if (distV2<v2.getWeight()) {
				v2.setWeight(distV2);
				v2.setInfo(v1.getInfo()+ " " + v2.getKey());
//				queueOfList.remove(v2);
			}
			}
		}
		v1.setTag(1);
		queueOfList.remove(v1);

	}
}
private boolean exsictVertex (int src, int dest) {
	if ( ( this.graph_alg.getV().contains(src)))  {
		throw new RuntimeException("the point src is not exsict");
	}
	if ( (this.graph_alg.getV().contains(dest)))
		throw new RuntimeException("the point dest is not exsict");
	return true;
}
class The_Comparator implements Comparator<node_data> { 

	@Override
	public int compare(node_data arg0, node_data arg1) {
		// TODO Auto-generated method stub
		return (int)(arg0.getKey() - arg1.getKey());
	} 
	
	// algoritem dijkstras - see more wikipedia https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm#Algorithm
	/*   1. Mark all nodes unvisited and infinity weight
	 *   2. Set it to zero for our initial node and to infinity for all other nodes 
	 * 	 3. For the current node, consider all of its unvisited neighbours and calculate their tentative distances through the current node.
	 * 	 4.When we are done considering all of the unvisited neighbours of the current node, mark the current node as visited.
	 * 5. If the destination node has been marked visited 
	 *  (when planning a route between two specific nodes) or if the smallest tentative distance. then stop.
	 *  6. Otherwise, select the unvisited node that is marked with the smallest tentative 
	 *  distance, set it as the new "current node", and go back to step 3.
	 *
	 */
	/*
	public void Dijkstras (int src, int dest) {
		unvisited();
		weightInfin ();
		node_data curr = graph_alg.getNode(src);
		curr.setWeight(0);
		Iterator it =  this.graph_alg.getE(curr.getKey()).iterator(); // iterator , not use. 
		while (curr.getTag()!=1 && graph_alg.getNode(dest).getTag() != 1) { // not visited in this vertex, and the dest not visitied 

			Collection<edge_data> edge_collect = this.graph_alg.getE(curr.getKey());
			double min= Double.MAX_VALUE;
			for (edge_data edge : edge_collect) {
				int dest_curr = edge.getDest();
				node_data next_vertex = graph_alg.getNode(dest_curr);
				curr.setTag(0);
				if (edge.getWeight() < min && next_vertex.getTag() != 1 ) { // go to min edge vertex, check that itsn't visited
					next_vertex.setWeight(edge.getWeight()+curr.getWeight());
					min= edge.getWeight();
					next_vertex.setInfo(curr.getKey() + " ");
					curr = next_vertex;
				} // end if

			}
		}
	}
	*/
} 
}

