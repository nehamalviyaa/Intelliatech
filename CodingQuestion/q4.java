//String Compression...
//input :-- aabcccccddd
//output : -- a2b1c5d3

import java.util.Scanner;

public class q4 {

public static void Compression(String input){

    char [] arr = input.toCharArray();
    int count = 1;
    for(int i = 0; i <arr.length -1; i++){

        if(arr[i] == arr[i+1]){
            count ++;
        }else{
            System.out.print(arr[i] + ""+count);
            count = 1;
        }
    }

   System.out.println(arr[arr.length - 1] +""+count);

}

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the String");
    String input = sc.next();

     Compression(input);

}
    
}
