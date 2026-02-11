//Two Sum Problem 
//Input :- nums = [2 , 7 ,11 ,15] target = 9
//Output :- 0 , 1

import java.util.Scanner;

public class q11 {

    
    public static void findTwoSum(int arr[], int target) {

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    System.out.println("Output: " + i + ", " + j);
                    return;
                }
            }
        }
        System.out.println("No pair found");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the size of array: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter the array elements:");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Enter the target element: ");
        int target = sc.nextInt();

        findTwoSum(arr, target);
    }
}
