// <haiku>
//
// Oh god please help me
// At least I get to use Vim
// I can't stand java
//
// </haiku>

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor. Or use a real dev tool like Vim.
 */
package bst.avl.splay;

public class SPLAY<T extends Comparable<T>>{
    
    public void splaying(T splay){root=splaying(root,splay);}
   
	// Always seems to return the parent, except maybe around line 48?
    private SPLAYNode<T> splaying(SPLAYNode<T> parent, T splay){
    
		// This is the easy case, it's right there.
        //when parent is null or parent.data is the search node return
        if(parent==null||parent.data==splay)return parent;
		
        //when splay lies in the left of the subtree
        if(splay.compareTo(parent.data)<0){
        
            //parent of left tree is null return
            if(parent.left==null){return parent;}
            //splay Zig-Zig left left when parent.left.data is less than splay
            else if(splay.compareTo(parent.left.data)<0){
                //recursively bring splay node form left
                parent.left.left = splaying(parent.left.left,splay);
                //rotate right
                parent=rotateRight(parent);
                
            //Zig-Zag left right    
            }else if(splay.compareTo(parent.left.data)>0){
            
                //recursivly bring key from of parent.left.right
                parent.left.right = splaying(parent.left.right,splay);
                //left rotation
                if (parent.left.right != null)
                    parent.left = rotateLeft(parent.left);
            }
            //second rotation
            return(parent.left==null)?parent:rotateRight(parent);

        }else if(splay.compareTo(parent.data)>0){
            // DNE, return parent.
            if(parent.right==null){return parent;}
           
            //Zag-Zig right left
            if(splay.compareTo(parent.right.data)<0){
                //recursively bring key from from parent.right.left
                parent.right.left  = splaying(parent.right.left,splay);
                
                //rotate right
                if(parent.right.left!=null)
                    parent.right = rotateRight(parent.right);
                    
            //Zag-Zag right right    
            }else if(splay.compareTo(parent.right.data)>0){
                //recursively bring key from parent.right.right
                parent.right.right = splaying(parent.right.right,splay);
                parent=rotateLeft(parent);
            }
            //second rotation
            return(parent.right==null)?parent:rotateLeft(parent);
        }
        else return parent;
    }
    
    // Search
    public boolean search(T toSearch){
    		root=search(root, toSearch);
    		return (toSearch.compareTo(root.data)==0);
    }
    
    private SPLAYNode<T> search(SPLAYNode<T> parent, T toSearch){
       
       //first, splay tree to place insert at root 
       parent=splaying(parent,toSearch);
       //printInOrder();
       
	//compare the element you want to find with the element return
	return parent;
    }
    
    //Insertion
    public void insert(T toInsert,int num){root=insert(root,toInsert,num);}
    
    private SPLAYNode<T> insert(SPLAYNode<T> parent, T toInsert,int num){
        // if tree is empty
        if(parent==null){
            parent = new SPLAYNode(toInsert,num); // Inserted element will be root immediately
            return parent;
        }
        parent=splaying(parent,toInsert); // We set parent equal to the element that should be the parent of the new element
        if(toInsert.compareTo(parent.data)<0){
            SPLAYNode temp=new SPLAYNode(toInsert,num);
            temp.left=parent.left;
            temp.right=parent;
            parent.left=null;
            parent=temp;
        }else if(toInsert.compareTo(parent.data)>0){
            SPLAYNode temp=new SPLAYNode(toInsert,num);
            temp.right=parent.right;
            temp.left=parent;
            parent.right=null;
            parent=temp;
        }else if(toInsert.compareTo(parent.data)==0){
            parent.data=toInsert;
        }
        return parent;
    }
    
    //Deletion
    public void delete(T toDelete){root=delete(root,toDelete);}
    
    private SPLAYNode<T> delete(SPLAYNode<T> parent, T toDelete){
        if(parent==null)return parent;
        parent=splaying(parent,toDelete); // We find the parent of the element to delete.
        if(parent.left==null){
            parent=parent.right; // If there's no left child, we must be deleting the right child
        }else{
            SPLAYNode x=parent.right; // Or else we need to name the SPLAYNODE to replace the left child
            parent=parent.left; // And prepare to delete the left child
            splaying(parent,toDelete); // We call splaying() to splay something(?) to the top
            parent.right=x; // x is a SPLAYNODE defined above, it is a copy of the earlier right child
        }
        return parent;
    }
    
    private SPLAYNode rotateRight(SPLAYNode<T> parent) {
        SPLAYNode temp=parent.left;
        parent.left=temp.right;
        temp.right=parent;
        return temp;
    }

    private SPLAYNode rotateLeft(SPLAYNode<T> parent) {
        SPLAYNode temp=parent.right;
        parent.right=temp.left;
        temp.left=parent;
        return temp;
    }
    
    public void printInOrder(){System.out.print("InOrder: ");printInOrderHelper(root);}
    private void printInOrderHelper(SPLAYNode parent){
        if(parent!=null){
            printInOrderHelper(parent.left);
            System.out.print(parent+" "+parent.num+" ");
            printInOrderHelper(parent.right);
        }
    }
    
    public void printPreOrder(){System.out.print("PreOrder: ");printPreOrderHelper(root);}
    private void printPreOrderHelper(SPLAYNode parent){
        if(parent!=null){
            System.out.println("Parent: "+parent.num+" "+parent+" "+" Left: "+parent.left+" Right: "+parent.right+" ");
            printPreOrderHelper(parent.left);
            printPreOrderHelper(parent.right);
        }
    }
    
    public void printPostOrder(){System.out.print("PostOrder: ");printPostOrderHelper(root);}
    private void printPostOrderHelper(SPLAYNode parent){
        if(parent!=null){
            printPostOrderHelper(parent.left);
            printPostOrderHelper(parent.right);
            System.out.print(parent+" "+parent.num+" ");
        }
    }
    
    private SPLAYNode<T> root;
    private static class SPLAYNode<T> {
      private T data;
      private SPLAYNode<T> left, right;
      private int num;

      public SPLAYNode(T data, SPLAYNode<T> leftNode, SPLAYNode<T> rightNode,int num){
         left=leftNode;right=rightNode;
         this.data = data;
         this.num=num;
      }
      public SPLAYNode(T data,int num){this(data, null, null,num);}
      @Override
      public String toString(){return data.toString();}
    }
}
