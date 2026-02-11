//Find the non Repeating character

import java.util.Scanner;

public class q5 {
 
     public static void nonRepeat(String input){
     
      char[] arr = input.toCharArray();
      for (int i = 0; i<arr.length-1; i++){
        if(arr[i] == arr[i+1]){
            break;
        }
        System.out.println("Non - Repeating Element in : "+input+ " is "+arr[i]);
      }

     }   
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string");
        String input = sc.next();

        nonRepeat(input);

    }
}
