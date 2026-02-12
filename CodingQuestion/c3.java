//Detect Cycle in a Linked list
//Input :- head = [3,2,0,-4] (with a cycle starting at index 1)
//Output :-- true 


import java.util.HashSet;
import java.util.LinkedList;
public class c3 {
    
    public static boolean hasCycle(LinkedList<Integer> list){

     HashSet<Integer> set = new HashSet<>();
     for(Integer value : list){
        if(set.contains(value)){
            return true;
        } set.add(value);
     
    }
    return false;

    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(10);
        list.add(20);
        list.add(30);
        list.add(20);
        list.add(50);

        if(hasCycle(list)){
         System.out.println("List has Cycle");
        }else{
            System.out.println("List has no cycle");
        }
    }
    
}
