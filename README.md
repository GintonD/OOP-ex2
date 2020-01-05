# OOP- ex2 maze of waze
Authors Meir fuchs && Ginton

The project describes a graph when every edge has a weight. 
example for graph here:

![Test Image 1](https://github.com/GintonD/OOP-ex2/blob/master/graphPaint.png?raw=true)

# Algorithm:
isConnected: if there is a valid path from EVREY node to each - is connected else- isn't connected. use in DFS

short distance: the length of the shortest path between source vertex to destination vertex. Use Dijkstra algoritem

shortest Path Distance: the the shortest path between src to dest - as an ordered List of nodes:
src--> n1-->n2-->...dest
 
TSP: computes a relatively short path which visit each node in the targets List.
 
 # How To Use:
 For crate graph you have 2 options. 
 1. init from a file in your computer. the file shoule be from the format Java. 
 2. random graph
 3. automatic graph
 after your choise you have to click on the screen.
 
 save- you can save the file to your folder.
 
 algorithm :
 short distance: choose the sourch and destination. The program tell the distance from source to destination.
 shortest Path: choose the sourch and destination. The program tell the path from source to destination.
 TSP: tell how many targets you want. choose the target. the program draw the new graph with the path.
 Isconnected: screen tell you if in this graph you can go from each vertex to other.
 
