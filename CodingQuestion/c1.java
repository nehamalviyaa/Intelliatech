//Remove Duplicates from list 
//Input :- head = [1,1,2,3,3]
//Output :-- [1,2,3]

import java.util.ArrayList;

public class c1 {

    public static ArrayList<Integer> removeDuplicates(ArrayList<Integer> list) {

        ArrayList<Integer> result = new ArrayList<>();

        for (int num : list) {
            if (!result.contains(num)) {
                result.add(num);
            }
        }
        return result;
    }

    public static void main(String[] args) {

        ArrayList<Integer> head = new ArrayList<>();
        head.add(1);
        head.add(1);
        head.add(2);
        head.add(3);
        head.add(3);

        ArrayList<Integer> output = removeDuplicates(head);
        System.out.println(output);   
    }
}
