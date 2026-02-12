//Container with most water
//Input :-- height = [1,8,6,7,2,5,4,8,3]
//Output:-- 49

import java.util.Scanner;

public class q20 {


public static int findMax(int height[]){
     int left = 0;
     int right = height.length-1;
     int maxArea = 0;

     while(left < right){
      int width =  right - left;
      int minHeight = Math.min(height[left] , height[right]);
      int area = width * minHeight;

      maxArea =  Math.max(maxArea, area);

      //Moving the left pointer here 
     if(height[left] < height[right]){
        left++;
     }else{
        right--;
     }
    }
    return  maxArea;
}

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the size of array");
    int n = sc.nextInt();

    int height[] = new int[n];

    System.out.println("Enter the array elements");
    for(int i =0 ; i<height.length;i++){
        height[i] = sc.nextInt();
    } 
   
    System.out.println("Maximun Water : "+findMax(height));

}  

}
