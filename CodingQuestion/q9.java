//String to Integer(atoi)
//Input:- '"42"'
//output:-- '42'

import java.util.Scanner;

public class q9 {

    public static int stringToInteger(String str){
        int result = 0;

        for (int i = 0; i < str.length(); i++) {
            int digit = str.charAt(i) - '0';
            result = result * 10 + digit;
        }
        return result;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string");
        String str = sc.next();
        System.out.println(stringToInteger(str));
    }


    
}
