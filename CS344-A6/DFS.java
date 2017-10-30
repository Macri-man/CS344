/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dfs.bfs.ts;

import java.util.Deque;

public class DFS<T extends Comparable<T>>{
   
   boolean[][] adjMatrix;
   Deque<Integer>[] adjList;
   int numNodes;
   boolean[] visited;
   
   DFS(int num){
      this.numNodes=num;
      this.adjMatrix=new boolean[num][num];
      this.adjList=new Deque[num];
      this.visited=new boolean[num];
   }

   public void DFS(adjMatrix matrix){
      this.numNodes=matrix.adjMatrix.length;
      this.visited=new boolean[this.numNodes];
      this.adjMatrix=new boolean[this.numNodes][this.numNodes];
      this.adjMatrix=matrix.returnMatrix(matrix);
   }
   
   public void DFS(adjList list){
      this.numNodes=list.adjList.length;
      this.visited=new boolean[this.numNodes];
      this.adjList=new Deque[this.numNodes];
      this.adjList=list.returnList(list);
   }
   
   public void dfsGraph(int i,String graph){
       visited=new boolean[this.numNodes];
       switch(graph){
           case "matrix":
               System.err.println("matrix");
               matrixGraph(i);
               break;
           case "list":
               System.err.println("list");
               listGraph(i);
               break;
           default :
               System.err.println("Invalid Graph");
       }
   }
   private void matrixGraph(int i){
      this.visited[i]=true;
      printNode(i);
      for(int j=0;j<this.numNodes;j++){
          if(this.adjMatrix[i][j]&&!this.visited[j])matrixGraph(j);
      }
   }
   
   private void listGraph(int i){
      this.visited[i]=true;
      printNode(i);
      for(int j=0;j<this.numNodes;j++){
          if(this.adjList[i].contains(j)&&!this.visited[j])listGraph(j);
      }
   }
   
   void printNode(int node){System.err.println(node);}
}
