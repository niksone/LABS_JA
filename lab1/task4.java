import java.util.Scanner;

public class task4 {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.print("Input a word: ");
    String word = scan.next();
    boolean isTextPalindrome = isPalindrome(word);
    if (isTextPalindrome)
      System.out.println("Palindrome");
    else 
      System.out.println("Not a palindrome");
  }

  public static String reverseString(String word) {
    String reverse = "";
    for (int i = word.length() - 1; i >= 0; --i) {
      reverse += word.charAt(i);
    }
    return reverse;
  }

  public static Boolean isPalindrome(String word) {
    String reversedString = reverseString(word);
    return word.equals(reversedString);

  }
}
