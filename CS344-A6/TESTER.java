/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dfs.bfs.ts;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Deque;

/*
 * A note on file I/O: The file paths are hardcoded because this application was
 * written on Mac OS, which requires literal pathing. If testing, alter file location
 * in adjMatrix.java and adjList.java, on both input and output files.
 */

/**
 *
 * @author alexandermacri
 */
public class TESTER {
   
    public static void main(String[] args) throws FileNotFoundException, IOException{
      adjList test1 = new adjList(); //Initialize adjList from file(inputadjList.txt)
      adjMatrix test2 = new adjMatrix();//Initialize adjMatrix from file (inputadjMatrix.txt)
      
      test1.printList();//print to console and file
      test2.printMatrix();//print to console and file
      System.err.println("\n");
      System.err.println("converion from list to matrix");
      test1.printList();
      boolean[][] matrix=test1.listToMatrix(); //convert test 1 (list) to a matrix
      test1.printMatrix(matrix);
      System.err.println("converion from matrix to list");
      test2.printMatrix();
      Deque<Integer>[] list=test2.matrixToList(); //convert test 2(matrix) to a list
      test2.printList(list);
      
      System.err.println("DFS");
      DFS dfs=new DFS(test1.numNodes);
      dfs.DFS(test1);
      dfs.dfsGraph(0,"list");
      System.err.println("\n");
      dfs=new DFS(test1.numNodes);
      dfs.DFS(test2);
      dfs.dfsGraph(0,"matrix");
      System.err.println("DFS ENDS");
      System.err.println("\n");
      
      System.err.println("BFS");
      BFS bfs=new BFS(test1.numNodes);
      bfs.BFS(test1);
      bfs.bfsGraph(0,"list");
      System.err.println("\n");
      bfs=new BFS(test2.numNodes);
      bfs.BFS(test2);
      bfs.bfsGraph(0,"matrix");
      System.err.println("BFS ENDS");
      
      System.err.println("\n");
      System.err.println("TS");
      TS ts=new TS(test2.numNodes);
      ts.TS(test2);
      ts.tsGraph(0,"matrix");
      ts.printList();
      
      
    }
      
}
