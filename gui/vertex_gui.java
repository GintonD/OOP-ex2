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
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JFrame;

import dataStructure.DGraph;
import dataStructure.edge;
import dataStructure.edge_data;
import dataStructure.node_data;
import dataStructure.vertex;
import utils.Point3D;

public class vertex_gui extends JFrame implements ActionListener, MouseListener
{
	DGraph graph_arr;
	

	public vertex_gui()
	{
		graph_arr = new DGraph();
		initGUI();
	}
	public void create_vertex_graph() { 
	
		Point3D point1= new Point3D(3, 4);
		for (int i = 0; i < 10; i++) {
			vertex node_data = new vertex(new Point3D(i+2,i+3));
			graph_arr.addNode(node_data);
		}
		
	}
	private void initGUI() 
	{
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");
		menuBar.add(menu);
		this.setMenuBar(menuBar);
		
		MenuItem item1 = new MenuItem("paint vertex");
		item1.addActionListener(this);
		
		MenuItem item2 = new MenuItem("paint edge");
		item2.addActionListener(this);
		
		menu.add(item1);
		menu.add(item2);
		
		this.addMouseListener(this);
	}

	public void paint(Graphics g)
	{
		
		super.paint(g);
	
		Point3D prev = null;
		for (node_data v: graph_arr.getV()) {
			g.setColor(Color.black);
			g.fillOval((int)v.getLocation().ix(), (int)v.getLocation().iy(), 10, 10);
			
			if (graph_arr.getE(v.getKey()) != null) //if have edge
				{
				for (edge_data e: graph_arr.getE(v.getKey())) {
					g.setColor(Color.blue);
					g.drawLine( (int)e.getSrc() , (int)e.getDest(), 4, 4);
				}
				}
		}
//		for (Point3D p : points) 
//		{
//			g.setColor(Color.BLUE);
//			g.fillOval((int)p.x(), (int)p.y(), 10, 10);
//			
//			if(prev != null)
//			{
//				g.setColor(Color.RED);
//				g.drawLine((int)p.x(), (int)p.y(), 
//						(int)prev.x(), (int)prev.y());
//				
//				g.drawString("5", (int)((p.x()+prev.x())/2),(int)((p.y()+prev.y())/2));
//			}
//			
//			prev = p;
//		}
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		String str = e.getActionCommand();
		
		if(str.equals("paint vertex"))
		{
			for (int i = 0; i < 5; i++) {
				
			
			Point3D p1 = new Point3D(100+20*i,100-14*i);
			Point3D p2 = new Point3D(50+20*i,300+5*i);
			Point3D p3 = new Point3D(400+20*i,150+12*i);
			
			
			
			vertex v1 = new vertex(p1);
			vertex v2 = new vertex(p2);
			vertex v3 = new vertex(p3);

			graph_arr.addNode(v1);
			graph_arr.addNode(v2);
			graph_arr.addNode(v3);
			graph_arr.connect(v1.getKey(), v2.getKey(), 4);
			graph_arr.connect(v2.getKey(), v3.getKey(), 4);
			graph_arr.connect(v3.getKey(), v1.getKey(), 4);
			System.out.println("hey");

			}
			repaint();
		}
		if(str.equals("paint edge"))
		{
			
		/*
		 * add code
		 */
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		Point3D p = new Point3D(x,y);
		graph_arr.addNode(new vertex(p));
	
		repaint();

		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}