// Find the longest Palindrome  substring
//Input : "babad"
//Output : "bab" (or "aba")
import java.util.Scanner;

public class q2 {

    public static void Palindrome(String input) {

        String longest = "";

        for (int i = 0; i < input.length(); i++) {
            for (int j = i; j < input.length(); j++) {

                String sub = input.substring(i, j + 1);

                if (isPalindrome(sub)) {
                    if (sub.length() > longest.length()) {
                        longest = sub;
                    }
                }
            }
        }

        System.out.println(longest);
    }

    // palindrome check
    public static boolean isPalindrome(String s) {

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the String");
        String input = sc.next();

        Palindrome(input);
    }
}
