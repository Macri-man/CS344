package bst.avl.splay;

public class BST<T extends Comparable<T>>{
    //Insertion
    public void insert(T toInsert){root=insert(root, toInsert);}
    private BSTNode<T> insert(BSTNode<T> parent, T toInsert){
      if(parent==null)return new BSTNode<>(toInsert);
      else if(toInsert.compareTo(parent.data)==0)return parent;
      else if(toInsert.compareTo(parent.data)<0)parent.left=insert(parent.left, toInsert);
      else parent.right = insert(parent.right, toInsert);
      return parent;
    }
    //Search
    public boolean search(T toSearch){return search(root, toSearch);}
    private boolean search(BSTNode<T> parent, T toSearch){
      if(parent == null)return false;
      else if(toSearch.compareTo(parent.data)==0)return true;
      else if(toSearch.compareTo(parent.data)<0)return search(parent.left,toSearch);
      else return search(parent.right,toSearch);
    }
    //Deletion
    public void delete(T toDelete){root=delete(root,toDelete);}
    private BSTNode<T> delete(BSTNode<T> parent, T toDelete){
      if(parent==null)return parent;
      else if(toDelete.compareTo(parent.data)<0)parent.left=delete(parent.left,toDelete);
      else if(toDelete.compareTo(parent.data)>0)parent.right=delete(parent.right,toDelete);
      else{
        if(parent.left==null)return parent.right;
        else if(parent.right==null)return parent.left;
        else{
          parent.data=retrieveData(parent.left);
          parent.left=delete(parent.left, parent.data) ;
        }
      }
      return parent;
    }
    
    private T retrieveData(BSTNode<T> parent){
      while(parent.right!=null)parent=parent.right;
      return parent.data;
    }
    
    public void printInOrder(){System.out.print("InOrder: ");printInOrderHelper(root);}
    private void printInOrderHelper(BSTNode parent){
        if(parent!=null){
            printInOrderHelper(parent.left);
            System.out.print(parent+" ");
            printInOrderHelper(parent.right);
        }
    }
    
    public void printPreOrder(){System.out.print("PreOrder: ");printPreOrderHelper(root);}
    private void printPreOrderHelper(BSTNode parent){
        if(parent!=null){
            System.out.print(parent+" ");
            printPreOrderHelper(parent.left);
            printPreOrderHelper(parent.right);
        }
    }
    
    public void printPostOrder(){System.out.print("PostOrder: ");printPostOrderHelper(root);}
    private void printPostOrderHelper(BSTNode parent){
        if(parent!=null){
            printPostOrderHelper(parent.left);
            printPostOrderHelper(parent.right);
            System.out.print(parent+" ");
        }
    }
    
    private BSTNode<T> root;   
    private static class BSTNode<T> {
      private T data;
      private BSTNode<T> left, right;

      public BSTNode(T data, BSTNode<T> leftNode, BSTNode<T> rightNode){
         left=leftNode;right=rightNode;
         this.data=data;
      }
      public BSTNode(T data){this(data, null, null);}
      @Override
      public String toString(){return data.toString();}
    }
}

