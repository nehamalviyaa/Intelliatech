//find duplicates in an array 
//Input:-- nums = [4,3,2,7,8,2,3,1]
//output :- 2 , 3

import java.util.Scanner;

public class q17 {

    public static void findDuplicates(int[] nums) {

        System.out.print("Duplicates: ");

        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;

            if (nums[index] < 0) {
                System.out.print(Math.abs(nums[i]) + " ");
            } else {
                nums[index] = -nums[index];
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the length of array");
        int n = sc.nextInt();

        int[] nums = new int[n];
        System.out.println("Enter the array elements");
        for(int i =0 ; i<nums.length;i++){
            nums[i] = sc.nextInt();
        }
        
        findDuplicates(nums);
    }
}
