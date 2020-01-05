package Tests;

import dataStructure.DGraph;
import dataStructure.edge;
import dataStructure.graph;
import dataStructure.vertex;
import gui.Graph_GUI;

import utils.Point3D;

public class GUI_Test {
	public static void main(String[] args) 
	{
		Point3D p1 = new Point3D(99, 95);
		Point3D p2 = new Point3D(203, 96);
		Point3D p3 = new Point3D(154, 152);
		Point3D p4 = new Point3D(455, 151);
		Point3D p5 = new Point3D(687, 206);
		Point3D p6 = new Point3D(142, 702);
		Point3D p7 = new Point3D(232, 437);
		Point3D p8 = new Point3D(191, 602);
		vertex n1 = new vertex(p1, 0);
		vertex n2 = new vertex(p2, 0);
		vertex n3 = new vertex(p3, 0);
		vertex n4 = new vertex(p4, 0);
		vertex n5 = new vertex(p5, 0);
		vertex n6 = new vertex(p6, 0);
		vertex n7 = new vertex(p7, 0);
		vertex n8 = new vertex(p8, 0);
		
		graph g = new DGraph();
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		g.addNode(n5);
		g.addNode(n6);
		g.addNode(n7);
		g.addNode(n8);
		g.connect(n1.getKey(), n2.getKey(), 7);
		g.connect(n2.getKey(), n1.getKey(), 2.77);
		g.connect(n2.getKey(), n3.getKey(), 4.5);
		g.connect(n3.getKey(), n4.getKey(), 10);
		g.connect(n2.getKey(), n4.getKey(), 4.11);
		g.connect(n4.getKey(), n3.getKey(), 3.55);
		g.connect(n7.getKey(), n4.getKey(), 42);
		g.connect(n1.getKey(), n5.getKey(), 23);
		g.connect(n6.getKey(), n2.getKey(), 4330);
		g.connect(n7.getKey(), n8.getKey(), 4235);
		g.connect(n8.getKey(), n5.getKey(), 4235);
		g.connect(n8.getKey(), n7.getKey(), 45435);
		g.connect(n4.getKey(), n7.getKey(), 45);
		g.connect(n3.getKey(), n1.getKey(), 4235);
		g.connect(n5.getKey(), n6.getKey(), 4.20);
		
//		Point3D p10 = new Point3D(391, 222);
//		vertex n10 = new vertex(p10, 74744);
//		g.addNode(n10);
//		

		Graph_GUI a = new Graph_GUI(g);
		a.setVisible(true);
	}
}

