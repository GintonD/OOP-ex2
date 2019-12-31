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
import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
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

public class Graph_GUI extends JFrame implements ActionListener, MouseListener
{
	
	graph gr;
	
	public Graph_GUI(graph g)
	{
		initGUI(g);
	}
	
	
	
	private void initGUI(graph g) 
	{
		this.gr=g;
		this.setSize(720, 720);
		this.setTitle("The Graph of Ginton & Fooxi !");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MenuBar menuBar = new MenuBar();
		this.setMenuBar(menuBar);
		
		Menu file = new Menu("File ");
		menuBar.add(file);
		
		Menu alg  = new Menu("Algorithms ");
		menuBar.add(alg);
		
		MenuItem item1 = new MenuItem("Init Graph");
		item1.addActionListener(this);
		file.add(item1);
		
		MenuItem item2 = new MenuItem("Init From File");
		item2.addActionListener(this);
		file.add(item2);
		
		MenuItem item3 = new MenuItem("Save to File");
		item3.addActionListener(this);
		file.add(item3);
		
		MenuItem item4 = new MenuItem("Save as Picture");
		item4.addActionListener(this);
		file.add(item4);
		
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
		
		if (gr != null) 
		{
			//get nodes
			Collection<node_data> nodes = gr.getV();
			
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
				if (gr.edgeSize()==0)
					continue;
				if ((gr.getE(n.getKey())!=null))
				{
					//get edges
					Collection<edge_data> edges = gr.getE(n.getKey());
					for (edge_data e : edges) 
					{
						//draw edges
						d.setColor(Color.RED);
						Point3D p2 = gr.getNode(e.getDest()).getLocation();
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
		Graph_Algo t=new Graph_Algo();
		JFileChooser jfc;
		FileNameExtensionFilter filter;
		
		switch(str) 
		{
//***************Init Graph***************************************
		case "Init Graph": 
			System.out.println("Init Graph:");
			initGUI(this.gr);
			break;
			
//***************Init Graph From File***************************************		
		case "Init From File": // not work becuase of init
			System.out.println("Init From File:\n");
			t=new Graph_Algo();

			jfc = new JFileChooser(FileSystemView.getFileSystemView());
			jfc.setDialogTitle("Init graph out of text file.."); 
			filter = new FileNameExtensionFilter(" .txt","txt");
			jfc.setFileFilter(filter);

			int returnVal = jfc.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION) 
			{
				System.out.println("You chose to open this file: " + jfc.getSelectedFile().getName());
				t.init(jfc.getSelectedFile().getAbsolutePath());
			}			
			break;
			
//***************Save Graph to File***************************************
		case "Save to File":  //not work because of save
			System.out.println("Save to File:");
			jfc = new JFileChooser(FileSystemView.getFileSystemView());
			jfc.setDialogTitle("Save graph to file..");

			int userSelection1 = jfc.showSaveDialog(null);
			if (userSelection1 == JFileChooser.APPROVE_OPTION) 
			{
				System.out.println("Save to File - " + jfc.getSelectedFile().getAbsolutePath());
				t.save(jfc.getSelectedFile().getAbsolutePath());
			}
			break;
//******************Save as Picture***************************
		case "Save as Picture": //empty!!!
			System.out.println("Save as Picture");
			
			break;
			
//************Shortest Path	*********************
			
		case "Shortest Path":
			System.out.println("Shortest Path:\n");
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
				
				if (this.gr.getNode(src)==null) 
				{
					JOptionPane.showMessageDialog(sp, "Error: illegal Vertex-key");
					System.out.println("Error: illegal Vertex-key");
					break;
				}
				
				if (this.gr.getNode(dest)==null) 
				{
					JOptionPane.showMessageDialog(sp, "Error: illegal Vertex-key");
					System.out.println("Error: illegal Vertex-key");
					break;
				}
				
				Graph_Algo GSP = new Graph_Algo();
				GSP.init(gr);

				List<node_data> SPList = GSP.shortestPath(src, dest);
				
				graph gr_new=new DGraph();
				gr_new.addNode(SPList.get(0));
				StrPath=StrPath+SPList.get(0).getKey();
				for (int i=1; i<SPList.size(); ++i) 
				{
					gr_new.addNode(SPList.get(i));
					gr_new.connect(SPList.get(i-1).getKey(), SPList.get(i).getKey(), this.gr.getEdge(SPList.get(i-1).getKey(), SPList.get(i).getKey()).getWeight());
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
			System.out.println("Shortest Path Distance:\n");
			
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
				
				if (this.gr.getNode(src)==null) 
				{
					JOptionPane.showMessageDialog(sp, "Error: illegal Vertex-key");
					System.out.println("Error: illegal Vertex-key");
					break;
				}
				
				if (this.gr.getNode(dest)==null) 
				{
					JOptionPane.showMessageDialog(sp, "Error: illegal Vertex-key");
					System.out.println("Error: illegal Vertex-key");
					break;
				}
				
				Graph_Algo GSP = new Graph_Algo();
				GSP.init(gr);
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
			System.out.println("TSP");//empty!!!
			break;

			
//*******************Is Conncected*******************
		case "Is Conncected ":
			Graph_Algo algcon = new Graph_Algo();
			algcon.init(this.gr);
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

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouseClicked");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("mousePressed");
		System.out.println(e.getX()+" , "+e.getY());
		Point3D r = new Point3D(e.getX(), e.getY(), 0);
		

		this.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("mouseReleased");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("mouseEntered");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("mouseExited");
	}
}