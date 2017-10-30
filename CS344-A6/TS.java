/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dfs.bfs.ts;
import java.util.Deque;
import java.util.ArrayDeque;

public class TS {
   
   boolean[][] adjMatrix;
   Deque<Integer>[] adjList;
   int numNodes;
   boolean[] visited;
   Deque<Integer> list;
   int elimcount=0;

   TS(int num){
      this.numNodes=num;
      this.adjMatrix=new boolean[num][num];
      this.adjList=new Deque[num];
      this.visited=new boolean[num];
   }
   
   public void TS(adjMatrix matrix){
      this.numNodes=matrix.adjMatrix.length;
      this.visited=new boolean[this.numNodes];
      this.adjMatrix=new boolean[this.numNodes][this.numNodes];
      this.adjMatrix=matrix.returnMatrix(matrix);
      this.list=new ArrayDeque<>(this.numNodes);
   }
   
   public void TS(adjList list){
      this.numNodes=list.adjList.length;
      this.visited=new boolean[this.numNodes];
      this.adjList=new Deque[this.numNodes];
      this.adjList=list.returnList(list);
      this.list=new ArrayDeque<>(this.numNodes);
   }
    
   public void tsGraph(int i,String graph){
       switch(graph){
           case "matrix":
                elimcount=0;
           	while(elimcount<adjMatrix.length)matrixTS(i);
               break;
           case "list":
               elimcount=0;
               while(elimcount<adjMatrix.length)listTS(i);
               break;
           default :
       		System.err.println("Invalid Graph");
       	}
   }
   
   public void matrixTS(int node){
      this.visited[node]=true;
      boolean done=true;
      int nextnode=0;
      for(int j=0;j<this.numNodes;j++){
          if(this.adjMatrix[node][j]){done=false;nextnode=j;break;}
      }
      if(done){list.add(node);eliminate(node);elimcount++;}
      else matrixTS(nextnode);
   }
   
   public void listTS(int node){
      this.visited[node]=true;
      boolean done=true;
      int nextnode=0;
      System.err.println();
      for(int j=0;j<this.numNodes;j++){
          if(this.adjList[node].contains(j)){done=false;nextnode=j;break;}
      }
      if(done){list.add(node);eliminate(node);elimcount++;}
      else listTS(nextnode);
   }
   
   void eliminate(int num){for(int count=0;count<this.numNodes;count++){this.adjMatrix[count][num]=false;}}
   void printNode(int node){System.err.println(node);}
   public void printList(){System.err.print("Topological Sort");for(Integer num:this.list)System.err.print(" "+ num+ " ");}
   
}