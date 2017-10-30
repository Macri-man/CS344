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


public class adjMatrix{
    
    int numNodes;
    boolean[][] adjMatrix;
    
    adjMatrix(int numNodes){
        this.numNodes=numNodes;
        this.adjMatrix=new boolean[numNodes][numNodes];
    }
    
    adjMatrix() throws FileNotFoundException, IOException{
        String line; 
        BufferedReader read=new BufferedReader(new FileReader("CS344-A6/assignment6/inputadjMatrix.txt"));
        line=read.readLine();
        char[] temp=line.toCharArray();
        this.numNodes=(temp[0]-'0');
        this.adjMatrix=new boolean[this.numNodes][this.numNodes];
            for(int row=0;(line=read.readLine())!=null;row++){
                temp=line.toCharArray();
                for(int i=0;i<temp.length;i++)this.adjMatrix[row][i]=((temp[i]-'0')==1);
            }
    }
    
    void addEdge(int node1,int node2){this.adjMatrix[node1][node2]=true;}
    void removeEdge(int node1, int node2){this.adjMatrix[node1][node2]=false;}
    boolean hasEdge(int node1, int node2){return this.adjMatrix[node1][node2];}
    
    boolean[][] returnMatrix(adjMatrix thing){return thing.adjMatrix;}
    
    Deque<Integer> rowMajor(int num){
        Deque<Integer> edges=new ArrayDeque<>();
        for(int j=0;j<this.numNodes;j++)if(adjMatrix[num][j])edges.add(j);
        return edges;
    }
    Deque<Integer> columnMajor(int num){
        Deque<Integer> edges=new ArrayDeque<>();
        for(int j=0;j<this.numNodes;j++)if(this.adjMatrix[j][num])edges.add(j);
        return edges;
    }
    
    void printList(Deque<Integer>[] adjList) throws FileNotFoundException{
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
    void printMatrix() throws FileNotFoundException{
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
    
    Deque<Integer>[] matrixToList(){
        Deque<Integer>[] adjList=new Deque[this.numNodes];
        for(int i=0;i<this.numNodes;i++)adjList[i]=new ArrayDeque<>(rowMajor(i));
        return adjList;
    }       
}
