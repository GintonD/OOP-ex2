package dataStructure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class DGraph implements graph,Serializable
{

private static final long serialVersionUID = 1L;
public HashMap<Integer, HashMap<Integer,edge_data/*edge*/>> Edges; 	
public HashMap<Integer, node_data/*vertex*/> Vertices ;  	
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

		return this.Vertices.get(key);

}

@Override
public edge_data getEdge(int src, int dest) 
{
	
		return (/*(edge_data)*/ (this.Edges.get(src).get(dest)));
	
}


@Override
public void addNode(node_data n) 
{
	this.Vertices.put(n.getKey(),  n); //maybe n shuld cast to vertex
	MC++; 
	this.Edges.put(n.getKey(), new HashMap<Integer, edge_data>());  //maybe is needed?
	
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
			MC++;
		}
		
		else // just connect
		{
			
			this.Edges.get(src).put(dest, NewEdge);
			size_edges++;
			
		}

	}

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
	
	//if (this.Vertices.isEmpty())
		//return null; 
	//else
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
//	if (this.Edges.isEmpty())
	//	return null; 
	//if (this.Edges.get(node_id)==null)
	//return null;
	return this.Edges.get(node_id).values();
}


@Override
public node_data removeNode(int key) 
{
	if (this.Vertices.get(key)==null) // the node is not exist
		return null;
	
	ArrayList<Integer> ForDelete = new ArrayList<Integer>();
	node_data VerAftDel = /*(vertex)*/Vertices.get(key);
	
	
	//remove all edges going into key-node.
	this.Edges.forEach((ky, edg) -> 
	{
		if (edg.get(key)!=null) 
		{
			edg.remove(key);
			size_edges--;
			MC++;
			
			if (edg.isEmpty()) //collect the edge with no dst
				ForDelete.add(ky);
		}
	});
	for (int ky : ForDelete) //delete them
		this.Edges.remove(ky);
	
	
	
	//remove all edges coming out of key-node.
	size_edges =size_edges - this.Edges.get(key).size();
	this.Edges.remove(key);
	
	
	//remove the key-node.
	this.Vertices.remove(key);
	MC++;

	return VerAftDel;
	
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

public graph copy() 
{
	DGraph g = new DGraph();
	Collection<node_data> v = Vertices.values();
	Iterator<node_data> itr = v.iterator();
	while(itr.hasNext()) 
	{
		node_data n = itr.next();
		g.addNode(n);
	}
	Collection<HashMap<Integer,edge_data>> e1 = Edges.values();
	Iterator<HashMap<Integer,edge_data>> itr1 = e1.iterator();
	while(itr1.hasNext()) 
	{
		HashMap<Integer, edge_data> h = itr1.next();
		Collection<edge_data> e2 = h.values();
		Iterator<edge_data> itr2 = e2.iterator();
		while(itr2.hasNext()) 
		{
			edge_data edge = itr2.next();
			g.connect(edge.getSrc(), edge.getDest(), edge.getWeight());
		}
	}
	g.size_edges = this.size_edges;
	g.MC = this.MC;
	return g;
}


}
