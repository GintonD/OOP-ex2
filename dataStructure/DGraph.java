package dataStructure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class DGraph implements graph
{
public HashMap<Integer, HashMap<Integer,edge_data/*edge*/>> Edges; 	//maybe convert edge_data to edge
public HashMap<Integer, node_data/*vertex*/> Vertices ;  	//maybe convert node_data to vertex
private int size_edges;
private static int MC = 0;


public DGraph()
{
	this.Edges=	new HashMap<Integer, HashMap<Integer,edge_data>>();
	this.Vertices= new HashMap<Integer, node_data>();
	this.size_edges=0;
}


@Override
public node_data getNode(int key) 
{
	if (!(this.Vertices.get(key)==null))
		return this.Vertices.get(key);
	else
		return null;
}

@Override
public edge_data getEdge(int src, int dest) 
{
	if (!(this.Edges.get(src).get(dest) == null))
		return (/*(edge_data)*/ (this.Edges.get(src).get(dest)));
	else
		return null;
}


@Override
public void addNode(node_data n) 
{
	this.Vertices.put(n.getKey(),  n); //maybe n shuld cast to vertex
	MC++; //figure if its true
	
	//this.Edges.put(n.getKey(), new HashMap<Integer, edge>());  //maybe is needed?
	
}
@Override
public void connect(int src, int dest, double w)  //one more!!!!!
{
	if(this.Vertices.get(src)!=null && Vertices.get(dest)!=null) 
	{
		edge NewEdge = new edge(src,dest,w);
		
		if (this.Edges.get(src) == null)	//create the edge and then connect
		{
			this.Edges.put(src, new HashMap<Integer,edge_data>());
			size_edges++;
			this.Edges.get(src).put(dest, NewEdge);
			
		}
		
		else // just connect
		{
			
			this.Edges.get(src).put(dest, NewEdge);
			size_edges++;
			
		}
		MC++;
	}
	else
		System.out.println("Error: the vertices can't connect!!"); //maybe throw here exception..
}




/**
 * This method return a pointer (shallow copy) for the
 * collection representing all the nodes in the graph. 
 * Note: this method should run in O(1) time.
 * @return Collection<node_data>
 */
@Override
public Collection<node_data> getV() 
{
	
	//return ((Collection<node_data>) this.Vertices);
	return this.Vertices.values();
}



/**
 * This method return a pointer (shallow copy) for the
 * collection representing all the edges getting out of 
 * the given node (all the edges starting (source) at the given node). 
 * Note: this method should run in O(1) time.
 * @return Collection<edge_data>
 */
@Override
public Collection<edge_data> getE(int node_id) //one more!!!!!
{
	//return ((Collection<edge_data>) this.Edges.get(node_id));
	return this.Edges.get(node_id).values();
}


@Override
public node_data removeNode(int key) 
{
	int toremove = 0;
	node_data vr;
	Collection<node_data> c =this.Vertices.values();
	Iterator<node_data> iter1 = c.iterator();
	
	while(iter1.hasNext())  
	{
		vr = iter1.next();
		if(this.Edges.get(vr.getKey()).get(key)!=null) 
		{
			this.Edges.get(vr.getKey()).remove(key);
			size_edges--;	
		}
	}
	
	MC++;
	
	
	if(this.Edges.get(key)!=null)
		 toremove = this.Edges.get(key).size();
	
	this.Edges.remove(key);
	size_edges =size_edges- toremove;
	return this.Vertices.remove(key);
}


@Override
public edge_data removeEdge(int src, int dest) 
{
	MC++;
	size_edges--;
	return Edges.get(src).remove(dest);
}


@Override
public int nodeSize() 
{
	return this.Vertices.size();
}

@Override
public int edgeSize() 
{
	return this.size_edges;
}

@Override
public int getMC() 
{
	return MC;
}




}
