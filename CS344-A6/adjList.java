/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dfs.bfs.ts;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;


public class adjList {
    
    int numNodes;
    Deque<Integer>[] adjList;
    
    adjList(int numNodes){
        this.numNodes=numNodes;
        this.adjList=new Deque[numNodes];
        for(int i=0;i<this.numNodes;i++)this.adjList[i]=new ArrayDeque<>();
    }
    
    adjList() throws FileNotFoundException, IOException{
        String line; 
        BufferedReader read=new BufferedReader(new FileReader("CS344-A6/assignment6/inputadjList.txt"));
        line=read.readLine();
        char[] temp=line.toCharArray();
        this.numNodes=(temp[0]-'0');
        this.adjList=new Deque[this.numNodes];
        for(int row=0;(line=read.readLine())!=null;row++){
            temp=line.toCharArray();
            this.adjList[row]=new ArrayDeque<>(temp.length);
            for(int i=1;i<temp.length;i++)this.adjList[row].add(temp[i]-'0');
        }
    }
    
    void addEdge(int node1,int node2){this.adjList[node1].add(node2);}
    void removeEdge(int node1, int node2){
        Iterator it = this.adjList[node1].iterator();
        while(it.hasNext()){if(it.next()==node2){it.remove();return;}}     
    }
    
    Deque<Integer>[] returnList(adjList thing){return thing.adjList;}
    
    boolean hasEdge(int node1,int node2){return this.adjList[node1].contains(node2);}
    Deque<Integer> rowMajor(int node){return this.adjList[node];}
    Deque<Integer> columnMajor(int num){
        Deque<Integer> edges = new ArrayDeque<>();
        for(int i=0;i<this.numNodes;i++)if(this.adjList[i].contains(num))edges.add(i);
        return edges;
    }
    
    void printList() throws FileNotFoundException{
        PrintWriter listOut = new PrintWriter("CS344-A6/assignment6/listOut.txt");
        for(int i=0;i<adjList.length;i++){
            System.err.print(" "+i+" ");
            listOut.print(" "+i+" ");
            for(Iterator<Integer> it = adjList[i].iterator(); it.hasNext();) {
                Integer num = it.next();
                System.err.print(" "+num+" ");
                listOut.print(" "+num+" ");
            }
            System.err.print("\n");
            listOut.print("\n");
        }
        listOut.close();
    }
    
    void printMatrix(boolean[][] adjMatrix) throws FileNotFoundException{
        PrintWriter matOut = new PrintWriter("CS344-A6/assignment6/matOutput.txt");
        for(int i=0;i<this.numNodes;i++){
            for(int j=0;j<this.numNodes;j++){
                if(adjMatrix[i][j]){
                    System.err.print(" "+1+" ");
                    matOut.print(" "+1+" ");
                }else{
                    System.err.print(" "+0+" ");
                    matOut.print(" "+0+" ");
                }
            }
            System.err.print("\n");
            matOut.print("\n");
        }
        matOut.close();
    }
    
    boolean[][] listToMatrix(){
        boolean[][] adjMatrix=new boolean[this.numNodes][this.numNodes];
       
        for(int i=0;i<this.numNodes;i++)
            for(int j=0;j<this.numNodes;j++)
                if(hasEdge(i,j))adjMatrix[i][j]=true;
                else adjMatrix[i][j]=false;
          
            return adjMatrix;
    }
}
