
package algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import dataStructure.DGraph;
import dataStructure.edge;
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
public class Graph_Algo implements graph_algorithms,Serializable
{

	private static final long serialVersionUID = 1L;
	private DGraph graph_alg;
	
	
	//
//	constractur
	public Graph_Algo () 
	{
		graph_alg = new DGraph();
	}
	
	public Graph_Algo(DGraph g) 
	{
		this.graph_alg=g;
	}
	
	public Graph_Algo(graph g) 
	{
		init(g);
	}

	@Override
	public void init(graph g) 
	{
	
	if(g instanceof DGraph) 
		this.graph_alg = (DGraph)g;  
	else
		throw new RuntimeException("Error: can't init Graph_Algo!!");
	}

	@Override
	public void init(String file_name) 
	{
		try 
		{
			ObjectInputStream in=new ObjectInputStream(new FileInputStream(file_name));
			graph GraphFromFile = (graph) in.readObject(); 
			init(GraphFromFile);
			in.close();
		}
		catch(Exception e) 
		{
			throw new RuntimeException("Error: Can't init from this file!!");
		}

	}
	

	@Override
	public void save(String file_name) 
	{
		try
		{
			OutputStream outStream = new FileOutputStream(file_name);
			ObjectOutputStream ObjectOutStream = new ObjectOutputStream(outStream);
			ObjectOutStream.writeObject(this.graph_alg);
			ObjectOutStream.close();
			outStream.close();
		}
		catch(Exception e) 
		{
			throw new RuntimeException("Error: Can't save this graph to file");
		}
		
	}


	
	@Override
	public boolean isConnected() 
	{
		if (this.graph_alg.getV().size()<2) return true;
		// Step 1: Mark all the vertices as not visited 
		unvisited();
		// Step 2: Do DFS traversal starting from first vertex. 

// we need to increase id that deal eith null !!
		
		Iterator<node_data> verItr = graph_alg.getV().iterator();
//	Collection<node_data> collectNode = 
//		node_data verFirst = graph_alg.ge
		int index = 1;
//		node_data node_first
		while (graph_alg.getNode(index)==null) {
			index++;
		}
			dfs ( this.graph_alg.getNode(index));
		// If DFS traversal doesn't visit all vertices, then 
		// return false. 
		Collection<node_data> vertex_collect = this.graph_alg.getV();
		for (node_data vertex: vertex_collect) {
			if (vertex.getTag()==0)
				return false;

		}
		// check all the vertex can go to vertex 1
		for (node_data vertex: vertex_collect) 
		{
			unvisited();
			if  (dfs_tovertex(vertex,index)==0)
				return false;
		}
		return true;
	}
	
	


	
	@Override
	public double shortestPathDist(int src, int dest) 
	{

		Dijkstras2(src, dest);
		
		return this.graph_alg.getNode(dest).getWeight();

//		}
	}


	@Override
	public List<node_data> shortestPath(int src, int dest) 
	{

//		}
		LinkedList<node_data> list = new LinkedList<node_data>();

			Dijkstras2(src, dest);		// go to algorithm
			String path_List =this.graph_alg.getNode(dest).getInfo();
			String [] splitArr = path_List.split(" ");
			// insert to list
			for (int i = 0; i < splitArr.length; i++) 
			{
				int key = Integer.parseInt(splitArr[i]);
				list.add(this.graph_alg.getNode(key));
			}
				
			
				
			return list;
//		}
//		else 
//		{
//		System.out.println("isn't connected");
//		return list;
//		}
	}
	
	


	
	
	@Override
	public List<node_data> TSP(List<Integer> targets) 
	{
	
		LinkedList<node_data> listTsp = new LinkedList<node_data>();
		int key;
		if (targets.size()==0) 
		{
			System.out.println("you need to send sort...");
			return null;
		}
		// **in target has only one
		if (targets.size()==1) 
		{
			key= targets.get(0);
			listTsp.add(this.graph_alg.getNode(key));
			return listTsp;
		}
		//
		int src=0;
		for (int i=0; i<targets.size()-1; i++) 
		{
			 src= targets.get(i);
			int dest = targets.get(i+1);
			if (!(exsictVertex(src, dest))) //the src or dest doesnt exsict
				return null;
			// save a list from i to i+1
			if (shortestPathDist(src, dest)== Double.POSITIVE_INFINITY) {
				return null;
			}
			List<node_data> list_ShortPath	= shortestPath(src, dest);
			src = dest;
			for (int j = 0; j < list_ShortPath.size()-1; j++) 
			{
				node_data node = list_ShortPath.get(j);
				key = list_ShortPath.get(j).getKey();
				if ((targets.contains(key))) 
				{
					
				}
				listTsp.add(node);
			}
		}
		listTsp.add(this.graph_alg.getNode(src));
		return listTsp;
	}
	
	
	@Override
	public graph copy() 
	{
		graph copy =this.graph_alg.copy();
		return copy;
	}
	
//***********help function*************************
	
	// set Infinity all the edge vertex
		private void weightInfin() 
		{
			Collection<node_data> vertex_collect = this.graph_alg.getV();
			for (node_data vertex: vertex_collect) 
			{
				vertex.setWeight(Double.POSITIVE_INFINITY); //vertex.setWeight(2000);
			}
		}
		
		private void unvisited() 
		{
			Collection<node_data> vertex_collect = this.graph_alg.getV();
			for (node_data vertex: vertex_collect) 
			{
				vertex.setTag(0);
			}
		}
		
		
	private int dfs_tovertex(node_data node,int dest) 
	{
		
		node.setTag(1); //visited 
		if (node.getKey()==dest) 
		{ 
			this.graph_alg.getNode(dest).setTag(1);
			return 1;
		}
		Collection<edge_data> edge_collect = this.graph_alg.getE(node.getKey()); // the collect edge of current vertex
		for (edge_data edge : edge_collect) 
		{
			int next_vertex = edge.getDest();
			int tag = this.graph_alg.getNode(next_vertex).getTag();
			if (tag ==0)
				dfs_tovertex(this.graph_alg.getNode(next_vertex),dest);

		}
		return this.graph_alg.getNode(dest).getTag() ;
	}
	
	

	
	private void dfs(node_data node) 
	{
		node.setTag(1);
		Collection<edge_data> t_c = this.graph_alg.getE(node.getKey());
		if (t_c != null) {
			for (edge_data edge : t_c) {
				if (graph_alg.getNode(edge.getDest()) != null && graph_alg.getE(edge.getDest()) != null && graph_alg.getNode(edge.getDest()).getTag() == 0) {
					dfs(graph_alg.getNode(edge.getDest()));
				}
			}
		}
	}

public void Dijkstras2 (int src, int dest)
{
	unvisited();
	weightInfin ();
	this.graph_alg.getNode(src).setWeight(0.);
	this.graph_alg.getNode(src).setInfo(""+ graph_alg.getNode(src).getKey());

	LinkedList<node_data> queueOfList = new LinkedList<node_data>();
	The_Comparator comp = new The_Comparator();
	for (node_data vertex: this.graph_alg.getV()) 
	{
		queueOfList.add(vertex);
	}
	
	while (!queueOfList.isEmpty() && this.graph_alg.getNode(dest).getTag()==0) 
	{
		queueOfList.sort(comp); //
		node_data v1 = queueOfList.get(0);
		// Visit each edge exiting vertex
		for (edge_data edge : this.graph_alg.getE(v1.getKey())) 
		{
			node_data v2 = this.graph_alg.getNode(edge.getDest());
			// add to queue for next check
			if (v2.getTag()==0) 
			{ //unvisited 
				
			double distV2 = v1.getWeight() + edge.getWeight();				
			if (distV2<v2.getWeight()) 
			{
				v2.setWeight(distV2);
				v2.setInfo(v1.getInfo()+ " " + v2.getKey());
			}
			}
		}
		v1.setTag(1);
		queueOfList.remove(0);

	}
}


private boolean exsictVertex (int src, int dest) 
{
	if ( ( this.graph_alg.getV().contains(src))) 
	{
		throw new RuntimeException("the point src is not exsict");
	}
	if ( (this.graph_alg.getV().contains(dest)))
		throw new RuntimeException("the point dest is not exsict");
	return true;
}


class The_Comparator implements Comparator<node_data> 
{ 

	@Override
	public int compare(node_data arg0, node_data arg1) 
	{

		if (arg0.getWeight()>arg1.getWeight()) return 1;
		if (arg0.getWeight()<arg1.getWeight()) return -1;

		return 0;
	}
}
	
	
} 
