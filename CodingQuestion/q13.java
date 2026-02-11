//Find the missing element
//input:--nums = [3,0,1]
//output :- 2 

import java.util.Scanner;

public class q13 {

    public static void findMissingElement(int[] arr){
      
        int n = arr.length;

        int expectedSum = n * (n+1)/2;
        int actualSum = 0;

        for (int num : arr){
            actualSum += num; 
        }

        int missingNumber = expectedSum - actualSum;

        System.out.println("Missing Element : "+missingNumber);
       
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of array");
        int n = sc.nextInt();

        int arr[] = new int[n];

        System.out.println("Enter the array elements");
        for(int i = 0; i<arr.length;i++){
            arr[i] = sc.nextInt();
        }

        findMissingElement(arr);
    }
    
}
