package dataStructure;
public class edge implements edge_data 
{
private int Src;
private int Dest;
private double weight;
private int tag=0;
private String info ="";

public edge (int Source, int Dest, double  weight) 
{
	this.Src = Source;
	this.Dest = Dest;
	this.weight = weight;
}

	@Override
	public int getSrc() 
	{
		return this.Src;
	}

	@Override
	public int getDest() {
		return this.Dest;
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public String getInfo()
	{
		return this.info;
	}

	@Override
	public void setInfo(String s) 
	{
		this.info=s;
	}

	@Override
	public int getTag() 
	{
		return this.tag;
	}

	@Override
	public void setTag(int t) 
	{
		this.tag = t;		
	}

}
