// Reverse String 
//Java program to reverse each word of String.
//	Sample Input: “Hello World”
//	Output: “olleh dlrow”
import java.util.Scanner;
class ReverseArray{
    public static void main(String [] args){
    Scanner sc = new Scanner(System.in);
    String reverse ="";

    System.out.println("enter the string");
    String input = sc.nextLine();

    String [] words = input.split(" ");

    for(String word : words){
        String reverseword = "";
        for(int i = word.length()-1 ; i>=0 ; i--){
            reverseword += word.charAt(i);
        }
        reverse += reverseword +" ";
    }

    System.out.println("Original String = "+input);
    System.out.println("Reversed String = "+reverse);
    

    }
}