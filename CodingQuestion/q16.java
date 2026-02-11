//Product of array Except self
//input num = [1,2,3,4]
//output :- [24,12,8,6]

import java.util.Scanner;
import java.util.Arrays;

public class q16 {

    public static int[] method(int[] arr) {
        int n = arr.length;

        int[] result = new int[n];

        int leftProduct = 1;

        // Left product calculation
        for (int i = 0; i < n; i++) {
            result[i] = leftProduct;
            leftProduct *= arr[i];
        }

        int rightProduct = 1;

        // Right product calculation
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= rightProduct;
            rightProduct *= arr[i];
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the array size");
        int n = sc.nextInt();

        int arr[] = new int[n];
        System.out.println("Enter the array elements");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int[] ans = method(arr);

        System.out.println("Product array except self:");
        System.out.println(Arrays.toString(ans));
    }
}
