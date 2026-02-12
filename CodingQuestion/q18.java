//Max SubArray Sum
//Input :-- nums = [ -2,1,-3,4,-1,2,1,-5,4]
//Output:- 6 (subarray[4,-1,2,1])

import java.util.Scanner;

public class q18 {
    
    public static void maxarraySum(int arr[]){
     
        int currentSum = arr[0];
        int maxSum = arr[0];

        for (int i = 1; i<arr.length; i++){
           currentSum = Math.max(arr[i], currentSum+ arr[i]);
           maxSum = Math.max(maxSum, currentSum);
        }

        System.out.println("Max Subarray sum : "+maxSum);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of array ");
        int n = sc.nextInt();

        int arr[] = new int[n];
        System.out.println("Enter the elemets of array");
        for (int i = 0; i<arr.length; i++){
            arr[i] = sc.nextInt();
        }

      maxarraySum(arr);
    }
}
