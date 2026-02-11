// Check Anagram
//Input :- "Listen" , "Silent"
//output :- true

import java.util.Arrays;
import java.util.Scanner;

public class q3 {

public static void findAnagram(String first , String Second){

    //  converting into lowercase
   first = first.toLowerCase();
   Second = Second.toLowerCase();

    //Length check
    if(first.length() != Second.length()){
        System.out.println(false);
        return;
    }

   // Converting into charArray 
  char[] ar1 = first.toCharArray();
  char[] ar2 = Second.toCharArray();

  // sort arrays
  Arrays.sort(ar1);
  Arrays.sort(ar2);

  // Match both arrays whether it is equal or not 
  if(Arrays.equals(ar1 , ar2)){
    System.out.println(true);
  }
  else{
    System.out.println(false);
  }

}

public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the  Two String ");
    String first = sc.next();
    String Second = sc.next();
     
    findAnagram(first , Second); 

}
    
}
