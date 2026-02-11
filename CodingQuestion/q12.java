//Rotate Array
//input:- [1,2,3,4,5,6,7], k= 3
//output :- [5,6,7,1,2,3,4]

import java.util.Scanner;

public class q12 {

    // rotate logic
    public static void rotateArray(int[] arr, int k) {
        int n = arr.length;
        k = k % n;   

        reverse(arr, 0, n - 1);// reverse whole array
        reverse(arr, 0, k - 1);// reverse k 
        reverse(arr, k, n - 1); // reverse remaining one 
    }

    // reverse helper method
    public static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    // print array
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the Array size: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter the elements of array:");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Enter the value of k: ");
        int k = sc.nextInt();

        rotateArray(arr, k);

        System.out.print("Rotated Array: ");
        printArray(arr);
    }
}

