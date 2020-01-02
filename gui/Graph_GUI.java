package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;


import algorithms.*;
import dataStructure.*;
import utils.*;

/**
 * @author Ginton & fooxi
 */

public class Graph_GUI extends JFrame implements ActionListener, MouseListener/*, Runnable*/
{
	
	private static final long serialVersionUID = 1L;
	private graph TempGraphGui;
	static int mcGui=0; //*run
	
	public Graph_GUI(graph g)
	{
		initGUI(g);
//		Thread t = new Thread(this);
//		mcGui = this.TempGraphGui.getMC(); *run
	}
	
	
	
	private void initGUI(graph g) 
	{
		this.TempGraphGui=g;
		this.setSize(720, 720);
		this.setTitle("The Graph of Ginton & Fooxi !");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MenuBar menuBar = new MenuBar();
		this.setMenuBar(menuBar);
		
		Menu New = new Menu("New ");
		menuBar.add(New);
		
		Menu alg  = new Menu("Algorithms ");
		menuBar.add(alg);
		
//**************New button*****************		
		MenuItem item1 = new MenuItem("Init Graph");
		item1.addActionListener(this);
		New.add(item1);
		
		MenuItem item9 = new MenuItem("Init Random Graph");
		item9.addActionListener(this);
		New.add(item9);
		
		MenuItem item2 = new MenuItem("Init From File");
		item2.addActionListener(this);
		New.add(item2);
		
		MenuItem item3 = new MenuItem("Save to File");
		item3.addActionListener(this);
		New.add(item3);
		
		
//**************Algorithms button*****************
		MenuItem item5 = new MenuItem("Shortest Path");
		item5.addActionListener(this);
		alg.add(item5);
		
		MenuItem item6 = new MenuItem("TSP");
		item6.addActionListener(this);
		alg.add(item6);
		
		MenuItem item7 = new MenuItem("Is Conncected ");
		item7.addActionListener(this);
		alg.add(item7);
		
		MenuItem item8 = new MenuItem("Shortest Path Distance");
		item8.addActionListener(this);
		alg.add(item8);
	 
		
		this.addMouseListener(this);
	}
	
	
	public void paint(Graphics d) 
	{
		super.paint(d);
		
		if (TempGraphGui != null) 
		{
			//get nodes
			Collection<node_data> nodes = TempGraphGui.getV();
			
			for (node_data n : nodes) 
			{
				//draw nodes
				Point3D p = n.getLocation();
				d.setColor(Color.BLUE);
				d.fillOval(p.ix(), p.iy(), 10, 10);
				
				//draw nodes-key's
				d.setColor(Color.BLUE);
				d.drawString(""+n.getKey(), p.ix()-4, p.iy()-4);
				
				//check if there are edges
				if (TempGraphGui.edgeSize()==0)
					continue;
				if ((TempGraphGui.getE(n.getKey())!=null))
				{
					//get edges
					Collection<edge_data> edges = TempGraphGui.getE(n.getKey());
					for (edge_data e : edges) 
					{
						//draw edges
						d.setColor(Color.RED);
						Point3D p2 = TempGraphGui.getNode(e.getDest()).getLocation();
						d.drawLine(p.ix()+5, p.iy()+5, p2.ix()+5, p2.iy()+5);
						
						//draw direction
						d.setColor(Color.YELLOW);
						d.fillOval((int)((p.ix()*0.1)+(0.9*p2.ix()))+7, 1+(int)((p.iy()*0.1)+(0.9*p2.iy())), 7, 7);
						
						//draw weight
						String WeightString = ""+String.valueOf(e.getWeight());
						d.setColor(Color.RED);
						d.drawString(WeightString, 3+(int)((p.ix()*0.1)+(0.9*p2.ix()))+7, 3+(int)((p.iy()*0.1)+(0.9*p2.iy())));
						
					}
				}	
			}
		}	
	}
	
	


	@Override
	public void actionPerformed(ActionEvent Command) 
	{
		String str = Command.getActionCommand();		
		Graph_Algo TempGrAl=new Graph_Algo();
		JFileChooser jfc;
		FileNameExtensionFilter filter;
		
		switch(str) 
		{
//***************Init Graph***************************************
		case "Init Graph": 
			System.out.println("Init Graph:");
			initGUI(this.TempGraphGui);
			break;
			
//******************Init Random Graph******************************
		case "Init Random Graph":
			System.out.println("Init Random Graph..");
			
				dataStructure.vertex.idBuilder=0; //maybe delete or change to the constructor
				DGraph rg = new DGraph();
				vertex[] v = new vertex[4];
				for(int i =0; i<v.length; i++) 
				{
					int rx = (int)(Math.random()*350+40);
					int ry = (int)(Math.random()*350+80);
					Point3D p =new Point3D(rx,ry);
					v[i] = new vertex(p);
					rg.addNode(v[i]);
				}
				for(int i = 1; i<Math.pow(v.length, 2); i++) 
				{
					int rs = (int)(Math.random()*v.length);
					int rf = (int)(Math.random()*v.length);
					int w = (int)(Math.random()*50);
					rg.connect(v[rs].getKey(), v[rf].getKey(), w);
				}
				this.TempGraphGui=rg;
				initGUI(this.TempGraphGui);
		
			
			break;	
//***************Init Graph From File***************************************		
		case "Init From File": 
			System.out.println("Init From File:");
			TempGrAl=new Graph_Algo();

			jfc = new JFileChooser(FileSystemView.getFileSystemView());
			jfc.setDialogTitle("Open Graph"); 

			int userSelection = jfc.showOpenDialog(null);
			if(userSelection == JFileChooser.APPROVE_OPTION) 
			{
				System.out.println("The file:" + jfc.getSelectedFile().getName()+" is successful Opened.");
				TempGrAl.init(jfc.getSelectedFile().getAbsolutePath());
				graph gr_new=TempGrAl.copy();
				
				initGUI(gr_new);	
			}			
			break;		
			
//***************Save Graph to File***************************************
		case "Save to File":  //not work because of save
			System.out.println("Save to File:");
			TempGrAl=new Graph_Algo((DGraph)this.TempGraphGui);		

			jfc = new JFileChooser(FileSystemView.getFileSystemView());
			jfc.setDialogTitle("Save As");

			int userSelection1 = jfc.showSaveDialog(null);
			if (userSelection1 == JFileChooser.APPROVE_OPTION) 
			{
				System.out.println("The Graph saved in this path: " + jfc.getSelectedFile().getAbsolutePath());
				TempGrAl.save(jfc.getSelectedFile().getAbsolutePath());
			}
			break;
			
//************Shortest Path	*********************
			
		case "Shortest Path":
			System.out.println("Shortest Path:");
			String StrPath="";
			try {
				JFrame sp = new JFrame();

				int src=0;
				int dest=0;
				try {
					String StrSource = JOptionPane.showInputDialog(sp,"Source-Vertex:");
					String StrDest = JOptionPane.showInputDialog(sp,"Destnation-Vertex:");
					src = Integer.parseInt(StrSource);
					dest = Integer.parseInt(StrDest);
					}
				catch (Exception e) 
				{
					JOptionPane.showMessageDialog(sp, "Error: illegal Vertex-key");
					System.out.println("Error: Vertex-key");
					break;
				}
				
				if (this.TempGraphGui.getNode(src)==null) 
				{
					JOptionPane.showMessageDialog(sp, "Error: illegal Vertex-key");
					System.out.println("Error: illegal Vertex-key");
					break;
				}
				
				if (this.TempGraphGui.getNode(dest)==null) 
				{
					JOptionPane.showMessageDialog(sp, "Error: illegal Vertex-key");
					System.out.println("Error: illegal Vertex-key");
					break;
				}
				
				Graph_Algo GSP = new Graph_Algo();
				GSP.init(TempGraphGui);

				if(((GSP.shortestPathDist(src, dest)==Double.POSITIVE_INFINITY))/*||SPList.size()<2*/) //HAVE TO CHECK WHAT THE PROBLEM$$$$$$$$$$%%%%%
				{
					JOptionPane.showMessageDialog(sp,"There is no Path,\nThe Graph is NOT connect!!");
					System.out.println("There is no Path,\nThe Graph is NOT connect!!");
					break;
				}
				
				List<node_data> SPList = GSP.shortestPath(src, dest);
				
			
					
				
				graph gr_new=new DGraph();
				gr_new.addNode(SPList.get(0));
				StrPath=StrPath+SPList.get(0).getKey();
				for (int i=1; i<SPList.size(); ++i) 
				{
					gr_new.addNode(SPList.get(i));
					gr_new.connect(SPList.get(i-1).getKey(), SPList.get(i).getKey(), this.TempGraphGui.getEdge(SPList.get(i-1).getKey(), SPList.get(i).getKey()).getWeight());
					StrPath=StrPath+("->"+SPList.get(i).getKey());
				}
				this.initGUI(gr_new);
				JOptionPane.showMessageDialog(sp,"The Short Path is:\n"+ StrPath);
				System.out.println(StrPath);
			}	
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			break;
			
//***************Shortest Path Distance***************************
			
		case "Shortest Path Distance": 
			System.out.println("Shortest Path Distance:");
			
			try {
				JFrame sp = new JFrame();

				int src=0;
				int dest=0;
				try {
					String StrSource = JOptionPane.showInputDialog(sp,"Source-Vertex:");
					String StrDest = JOptionPane.showInputDialog(sp,"Destnation-Vertex:");
					src = Integer.parseInt(StrSource);
					dest = Integer.parseInt(StrDest);
					}
				catch (Exception e) 
				{
					JOptionPane.showMessageDialog(sp, "Error: illegal Vertex-key");
					System.out.println("Error: Vertex-key");
					break;
				}
				
				if (this.TempGraphGui.getNode(src)==null) 
				{
					JOptionPane.showMessageDialog(sp, "Error: illegal Vertex-key");
					System.out.println("Error: illegal Vertex-key");
					break;
				}
				
				if (this.TempGraphGui.getNode(dest)==null) 
				{
					JOptionPane.showMessageDialog(sp, "Error: illegal Vertex-key");
					System.out.println("Error: illegal Vertex-key");
					break;
				}
				
				Graph_Algo GSP = new Graph_Algo();
				GSP.init(TempGraphGui);
				double dis = GSP.shortestPathDist(src, dest);
				
				
				JOptionPane.showMessageDialog(sp,"The Shortest Path Distance is:\n"+ dis);
			}	
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			break;
			
//**********************TSP***********************************	
		case "TSP": 
			System.out.println("TSP:");
			JFrame TSPinput = new JFrame();
			StrPath="";

			String SourceNodeTSP = JOptionPane.showInputDialog(TSPinput,"How many nodes ?");
			int manyTSP=1;
			try 
			{
				manyTSP = Integer.parseInt(SourceNodeTSP);
			} 
			catch (NumberFormatException e) 
			{
				JOptionPane.showMessageDialog(TSPinput, "Ilegal number of nodes.");
				break;
			}
			if (manyTSP<1 || manyTSP>this.TempGraphGui.nodeSize()) 
			{
				JOptionPane.showMessageDialog(TSPinput, "Ilegal number of nodes.");
				break;
			}

			int cmon=0;
			if (manyTSP==1) 
			{
				SourceNodeTSP = JOptionPane.showInputDialog(TSPinput,"Enter node-key");
				try 
				{
					cmon = Integer.parseInt(SourceNodeTSP);
				}
				catch (NumberFormatException e)
				{
					JOptionPane.showMessageDialog(TSPinput, "Ilegal key.");
					break;
				}
				graph gr_new=new DGraph();

				gr_new.addNode(this.TempGraphGui.getNode(cmon));
				this.initGUI(gr_new);	
				break;
			}

			List<Integer> TSPnodes = new ArrayList<Integer>();
			int TSPkey=0;
			for (int i=0; i<manyTSP; i++) {
				SourceNodeTSP = JOptionPane.showInputDialog(TSPinput,"Enter node-key "+(i+1)+"/"+manyTSP);
				try {
					TSPkey = Integer.parseInt(SourceNodeTSP);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(TSPinput, "Ilegal key.");
					break;
				}
				if(this.TempGraphGui.getNode(TSPkey)==null) {
					JOptionPane.showMessageDialog(TSPinput, "Ilegal key.");
					break;
				}	
				TSPnodes.add(TSPkey);
			}

			Graph_Algo newGTSP = new Graph_Algo();
			newGTSP.init(TempGraphGui);
			if (newGTSP.TSP(TSPnodes)==null) 
			{
				JOptionPane.showMessageDialog(TSPinput,"There is no Path,\nThe Graph is NOT connect!!");
				System.out.println("There is no Path,\nThe Graph is NOT connect!!");
				break;
			}
			List<node_data> TSP = newGTSP.TSP(TSPnodes);
			graph gr_new=new DGraph();

			gr_new.addNode(TSP.get(0));
			StrPath=StrPath+TSP.get(0).getKey();
			for (int i=1; i<TSP.size(); i++) {
				gr_new.addNode(TSP.get(i));
				gr_new.connect(TSP.get(i-1).getKey(), TSP.get(i).getKey(), this.TempGraphGui.getEdge(TSP.get(i-1).getKey(), TSP.get(i).getKey()).getWeight());
				StrPath=StrPath+("->"+TSP.get(i).getKey());
			}

			this.initGUI(gr_new);
			JOptionPane.showMessageDialog(TSPinput,"The TSP Short Path is:\n"+ StrPath);
			System.out.println("The TSP Short Path is:\n"+ StrPath);
			break;

			
//*******************Is Conncected*******************
		case "Is Conncected ":
			System.out.println("Is Connected?:");
			Graph_Algo algcon = new Graph_Algo();
			algcon.init(this.TempGraphGui);
			JFrame is = new JFrame();
			if (algcon.isConnected())
			{
				JOptionPane.showMessageDialog(is,"The Graph is Connected!");
				System.out.println("The Graph is Connected!");
			}
			else
			{
				System.out.println("The Graph is NOT Connected!");
				JOptionPane.showMessageDialog(is,"The Graph is NOT Connected!");
			}
			break;
			
		}
	}
//***********Optional leastions***********************88
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		//System.out.println("mouseClicked");
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		//System.out.println("mousePressed");
		//System.out.println(e.getX()+" , "+e.getY());
		//Point3D r = new Point3D(e.getX(), e.getY(), 0);
		

		this.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		//System.out.println("mouseReleased");
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
			//System.out.println("mouseEntered");
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		//System.out.println("mouseExited");
	}


//
//	@Override   *run
//	public void run() {
//		while(true) {
//			if(this.TempGraphGui.getMC() != mcGui) {
//				mcGui=TempGraphGui.getMC();
//				synchronized (this) {
//					repaint();		
//				}						
//			}
//			try {
//				Thread.sleep(600);
//			}
//			catch (Exception e) {
////			}
//		}
//	}
}