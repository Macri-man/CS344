/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dfs.bfs.ts;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class BFS {
   
   boolean[][] adjMatrix;
   Deque<Integer>[] adjList;
   int      numNodes;
   boolean[] visited;
   Deque<Integer> graph;
   private int unvisited=-1;

   BFS(int num){
      this.numNodes=num;
      this.adjMatrix=new boolean[num][num];
      this.adjList=new Deque[num];
      this.visited=new boolean[num];
   }

   public void BFS(adjMatrix matrix){
      this.numNodes=matrix.adjMatrix.length;
      this.visited=new boolean[this.numNodes];
      this.adjMatrix=new boolean[this.numNodes][this.numNodes];
      this.adjMatrix=matrix.returnMatrix(matrix);
   }
   
   public void BFS(adjList list){
      this.numNodes=list.adjList.length;
      this.visited=new boolean[this.numNodes];
      this.adjList=new Deque[this.numNodes];
      this.adjList=list.returnList(list);
   }
   
   public void bfsGraph(int i,String graph){
       this.visited=new boolean[this.numNodes];
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
   
   public void matrixGraph(int num){
       this.graph=new ArrayDeque<>();
       this.graph.add(num);
       this.visited[num]=true;
       printNode(num);
       while(!this.graph.isEmpty()){
          int n=(this.graph.peek()).intValue();
          int child=getUnvisitedMatrixNode(n);
          if(child!=unvisited){
             this.visited[child]=true;
             printNode(child);
             this.graph.add(child);
          }else{
             this.graph.remove();
          }
       }
      clearVisited();     
   }
 
   int getUnvisitedMatrixNode(int n){
       for(int j=0;j<this.numNodes;j++){
 	 if(this.adjMatrix[n][j]&&!visited[j]){return(j);}
       }
       return(this.unvisited);
    }
   
   public void listGraph(int num){
       this.graph=new ArrayDeque<>();
       this.graph.add(num);
       this.visited[num]=true;
       printNode(num);
       while(!this.graph.isEmpty()){
          int n=(this.graph.peek()).intValue();
          int child=getUnvisitedListNode(n);
          if(child!=unvisited){
             this.visited[child]=true;
             printNode(child);
             this.graph.add(child);
          }else{
             this.graph.remove();
          }
       }
      clearVisited(); 
   }
   
   int getUnvisitedListNode(int n){
       for(int j=0;j<this.numNodes;j++){
 	 if(this.adjList[n].contains(j)&&!this.visited[j]){return(j);}
       }
       return(unvisited);
    } 
   
    void clearVisited(){
       for(int i=0;i<visited.length;i++)
          visited[i]=false;
    }
  
   void printNode(int node){System.err.println(node);}
}
