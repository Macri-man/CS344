package bst.avl.splay;

public class AVL<T extends Comparable<T>>{
    //Search
    public boolean search(T toSearch){return search(root,toSearch);}
    private boolean search(AVLNode<T> parent,T toSearch){
        while(parent!=null){
            if(toSearch.compareTo( parent.data)<0){return search(parent.left,toSearch);}
            else if(toSearch.compareTo( parent.data)>0){return search(parent.right,toSearch);}
            else return true;
        }
        return false; 
    }
    //Deletion
    public void delete(T toDelete){root=delete(root,toDelete);}
    private AVLNode<T> delete(AVLNode<T> parent, T toDelete){
        if(parent==null)return parent;
        else if(toDelete.compareTo(parent.data)<0){parent.left=delete(parent.left,toDelete);}
        else if(toDelete.compareTo(parent.data)>0){parent.right=delete(parent.right,toDelete);}
        else{
            if(parent.left==null)return parent.right;
            else if(parent.right==null){return parent.left;}
            else{
                parent.data=retrieveData(parent.right);
                parent.right=delete(parent.right, parent.data) ;
            }
        }
        parent.height=max(height(parent.left), height(parent.right))+1;
        parent=balance(parent,toDelete);
        return parent;
    }
    
    private T retrieveData(AVLNode<T> parent){
        while(parent.right != null) parent=parent.right;
        return parent.data;
    }
    
    private AVLNode<T> balance(AVLNode<T> Node, T key){
        if(Node.left==null||Node.right==null)return Node;
        else if(getBalance(Node.left)==2){
            if(getBalance(Node.left)>0)return rotateRight(Node);
            else if(getBalance(Node.left)<0){Node.left=rotateLeft(Node.left);return rotateRight(Node);}}
        else if(getBalance(Node.right)==-2){
            if(getBalance(Node.right)<0)return rotateLeft(Node);
            else if(getBalance(Node.right)>0){Node.right=rotateRight(Node.right);return rotateLeft(Node);}}
        return Node;
    }
    
    //Insertion
    public void insert(T data){root=insert(root,data);}
    private AVLNode<T> insert(AVLNode<T> parent, T toInsert){
        if(parent==null)return new AVLNode<>(toInsert);
        else if(toInsert.compareTo(parent.data)<0){parent.left=insert(parent.left,toInsert);
            if(getBalance(parent)==2)
                if(toInsert.compareTo(parent.left.data)<0){parent=rotateLeft(parent);}
                else{parent=doubleRotateLeft(parent);}
        }else if(toInsert.compareTo(parent.data)>0){parent.right=insert(parent.right,toInsert);
            if(getBalance(parent)==-2)
                if(toInsert.compareTo(parent.right.data)>0){parent=rotateRight(parent);}
                else{parent=doubleRotateRight(parent);}
        }else;
        parent.height=max(height(parent.left),height(parent.right))+1;
        return parent;
    }
   
    private int height(AVLNode<T> Node){return Node==null?0:Node.height;}
    private int max(int x,int y){return x>y?x:y;}
    private int getBalance(AVLNode<T> Node){return Node==null?0:height(Node.left)-height(Node.right);}
    
    //Rotate functions
    private AVLNode<T> rotateRight(AVLNode<T> Node){
        AVLNode<T> temp=Node.right;
        Node.right=temp.left;
        temp.left=Node;
        Node=temp;
        Node.height=max(height(Node.left),height(Node.right))+1;
        temp.height=max(height(temp.left),height(temp.right))+1;
        return temp;
    }

    private AVLNode<T> rotateLeft(AVLNode<T> Node){
        AVLNode<T> temp=Node.left;
        Node.left=temp.right;
        temp.right=Node;
        Node.height=max(height(Node.left),height(Node.right))+1;
        temp.height=max(height(temp.left),height(temp.right))+1;
        return temp;
    }
    
    private AVLNode doubleRotateLeft(AVLNode Node){
        Node.left=rotateRight(Node.left);
        return rotateLeft(Node);
    }
     
    private AVLNode doubleRotateRight(AVLNode Node){
        Node.right=rotateLeft(Node.right);
        return rotateRight(Node);
    }    
    
    
    public void printInOrder(){System.out.print("InOrder: ");printInOrderHelper(root);}
    private void printInOrderHelper(AVLNode parent){
        if(parent!=null){
            printInOrderHelper(parent.left);
            System.out.print(parent+" ");
            printInOrderHelper(parent.right);
        }
    }
    
    public void printPreOrder(){System.out.print("PreOrder: ");printPreOrderHelper(root);}
    private void printPreOrderHelper(AVLNode parent){
        if(parent!=null){
            System.out.print(parent+" ");
            printPreOrderHelper(parent.left);
            printPreOrderHelper(parent.right);
        }
    }
    
    public void printPostOrder(){System.out.print("PostOrder: ");printPostOrderHelper(root);}
    private void printPostOrderHelper(AVLNode parent){
        if(parent!=null){
            printPostOrderHelper(parent.left);
            printPostOrderHelper(parent.right);
            System.out.print(parent+" ");
        }
    }
    
    private AVLNode<T> root;
    private static class AVLNode<T> {
      private T data;
      private int height;
      private AVLNode<T> left, right;

      public AVLNode(T data, AVLNode<T> leftNode, AVLNode<T> rightNode,int height){
         left=leftNode;right=rightNode;
         this.data=data;
         this.height=height;
      }
      public AVLNode(T data){this(data, null, null,0);}
      @Override
      public String toString(){return data.toString();}
    }
}


  