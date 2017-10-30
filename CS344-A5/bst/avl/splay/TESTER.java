package bst.avl.splay;

import java.util.HashSet;
import java.util.Set;

public class TESTER{
    
    public static void main(String[] args) {
        //small scale testing
        bstTest();
        avlTest();
        splayTest();
        //big scale testing
        bigNumberTest();
        System.out.println("\nDone! All tests completed!");
        }
    
    public static void bstTest(){ //test bst on 5 elements -- insertion, then sort, then deletion
        BST<Integer> bst = new BST<>();
        int noexist = 30000;
        System.out.println("Beginning small-scale test for BST: Five Elements\n");
        System.out.println("Phase One: Insertion\n");
        int[] numbers = new int[5];
        System.out.println("Numbers generated:\n");
        for (int i=0; i<5; i++){
            numbers[i] = ((int) (-10000+Math.random()*(10000-(-10000)+1)));
            System.out.println(i + ": " + numbers[i]);
        }
        for (int i=0; i<5; i++){
            System.out.println("\n\nInsertion " + (i+1) + ": " + numbers[i]);
            bst.insert(numbers[i]);
            bst.printInOrder();
        }
        System.out.println("\n\n\nPhase Two: Searching");
        for (int i=0; i<5; i++){
            System.out.println("\nFinding " + numbers[i]);
            if (bst.search(numbers[i]))
                System.out.println(numbers[i] + " found!");
            else
                System.out.println("Uh-oh, " + numbers[i] + "Not found!");
        }
        System.out.println("\n\nSearching for an element that doesn't exist:" + noexist);
        if(bst.search(noexist))
            System.out.println("Uh-oh, " + noexist + "was found!");
        else
            System.out.println("No instance of " + noexist + " found!\n");
        System.out.println("\nNow deleting each individual element:");
        bst.printInOrder();
        for (int i=0; i<5; i++){
            System.out.println("\n\nDeleting " + numbers[i]);
            bst.delete(numbers[i]);
            bst.printInOrder();
        }
        System.out.println("\n\nSmall scale BST testing done!\n\n\n");
        return;
    }
        
    public static void avlTest(){ //test bst on 5 elements -- insertion, then sort, then deletion
        AVL<Integer> avl = new AVL<>();
        int noexist = 30000;
        System.out.println("Beginning small-scale test for AVL: Five Elements\n");
        System.out.println("Phase One: Insertion\n");
        int[] numbers = new int[5];
        System.out.println("Numbers generated:\n");
        for (int i=0; i<5; i++){
            numbers[i] = ((int) (-10000+Math.random()*(10000-(-10000)+1)));
            System.out.println(i + ": " + numbers[i]);
        }
        for (int i=0; i<5; i++){
            System.out.println("\n\nInsertion " + (i+1) + ": " + numbers[i]);
            avl.insert(numbers[i]);
            avl.printInOrder();
        }
        System.out.println("\n\n\nPhase Two: Searching");
        for (int i=0; i<5; i++){
            System.out.println("\nFinding " + numbers[i]);
            if (avl.search(numbers[i]))
                System.out.println(numbers[i] + " found!");
            else
                System.out.println("Uh-oh, " + numbers[i] + "Not found!");
        }
        System.out.println("\n\nSearching for an element that doesn't exist:" + noexist);
        if(avl.search(noexist))
            System.out.println("Uh-oh, " + noexist + "was found!");
        else
            System.out.println("No instance of " + noexist + " found!\n");
        System.out.println("\nNow deleting each individual element:");
        avl.printInOrder();
        for (int i=0; i<5; i++){
            System.out.println("\n\nDeleting " + numbers[i]);
            avl.delete(numbers[i]);
            avl.printInOrder();
        }
        System.out.println("\n\nSmall scale AVL testing done!\n\n\n");
        return;
    }
    
    public static void splayTest(){ //test bst on 5 elements -- insertion, then sort, then deletion
        SPLAY<Integer> splay = new SPLAY<>();
        int noexist = 30000;
        System.out.println("Beginning small-scale test for splay: Five Elements\n");
        System.out.println("Phase One: Insertion\n");
        int[] numbers = new int[5];
        System.out.println("Numbers generated:\n");
        for (int i=0; i<5; i++){
            numbers[i] = ((int) (-10000+Math.random()*(10000-(-10000)+1)));
            System.out.println(i + ": " + numbers[i]);
        }
        for (int i=0; i<5; i++){
            System.out.println("\n\nInsertion " + (i+1) + ": " + numbers[i]);
            splay.insert(numbers[i],i);
           	splay.printPreOrder();
           
        }
        System.out.println("\n\n\nPhase Two: Searching");
        for (int i=0; i<5; i++){
            System.out.println("\nFinding " + numbers[i]);
            splay.printPreOrder();
            if (splay.search(numbers[i])){
                System.out.println(numbers[i] + " found!");
                
            }
            else
                System.out.println("Uh-oh, " + numbers[i] + "Not found!");
        }
        System.out.println("\n\nSearching for an element that doesn't exist:" + noexist);
        if(splay.search(noexist))
            System.out.println("Uh-oh, " + noexist + "was found!");
        else
            System.out.println("No instance of " + noexist + " found!\n");
            
        System.out.println("\nNow deleting each individual element:");
        splay.printPreOrder();
        for (int i=0; i<5; i++){
            System.out.println("\n\nDeleting " + numbers[i]);
            splay.delete(numbers[i]);
            splay.printPreOrder();
            
        }
        System.out.println("\n\nSmall scale splay testing done!\n\n\n");
        return;
    }
    
    public static void bigNumberTest(){ //do our full test on 1024 elements, only showing test results
        //insertion
        System.out.println("Beginning large scale tests for all: 1024 Elements\n");
        boolean find1,find2,find3;
        int length = 1024;
        System.out.println("\nGenerating trees...\n");
        System.out.println("\nPlacing -14 in random position in trees:\n\nBST:");
        Set<Integer> array=randomArray(length);
        BST<Integer> bst = new BST<>();
        for(Integer num : array) bst.insert(num);
        System.out.println("Splay:");
        array=randomArray(length);
        SPLAY<Integer> splay = new SPLAY<>();
        int count=0;
        for(Integer num : array) splay.insert(num,count);
        System.out.println("AVL:");
        array=randomArray(length);
        AVL<Integer> avl = new AVL<>();
        for(Integer num : array) avl.insert(num);
        //Searching
        find1=bst.search(-14);
        find2=splay.search(-14);
        find3=avl.search(-14);
            //uncomment the following to print full tree
            //bst.printInOrder();
            //splay.printInOrder();
            //avl.printInOrder();
        System.out.println("Performing search test to find -14 in each tree:");
        System.out.println("BST: "+find1+" SPLAY: "+find2+" AVL: "+find3+"\n");
        //Deletion
        System.out.println("\n\nDeleting -14 from trees, and then searching again:");
        bst.delete(-14);
        splay.delete(-14);
        avl.delete(-14);
        find1=bst.search(-14);
        find2=splay.search(-14);
        find3=avl.search(-14);
        System.out.println("BST: "+find1+" SPLAY: "+find2+" AVL: "+find3+"\n");
        System.out.println("\nBig number testing completed!\n");
}


    
    public static Set randomArray(int length){ //insert -length- random non-equal elements into array
        //This will place -14 on a random element out of length, as well.
        Set<Integer> set = new HashSet<>();
        int insert;
        int insertpos = ((int)(10+Math.random()*(length-(10)+1)));
        System.out.println("Inserting -14 on random element: " + insertpos + "\n");
        boolean exists;
        while(true){
            insert = (int)(-10000+Math.random()*(10000-(-10000)+1));
            exists = set.contains(insert);
            if (exists == false || insert == -14)
                set.add(insert);
            if (set.size() == insertpos)
                set.add(-14);
            if (set.size() == 1024)
                break;
        }
        return set;
    }
    
}
