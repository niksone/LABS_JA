import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class task2 {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.print("Input n: ");
    int n = scan.nextInt();

    int min = -50;
    int max = 50;
    int range = max - min;

    int[] arr = new int[n];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int)(Math.random() * range) + min;
    }

    System.out.println(Arrays.toString(arr));

    int length = arr.length;
    int currentSign = getSign(arr[0]);
    int counter = 0;


    for (int i = 1; i < length; i++) {
      int tempSign = getSign(arr[i]);
      if(tempSign != currentSign) {
        currentSign = tempSign;
        counter++;
      }
    }
    System.out.println("Changed " + counter + " times");
  }

  public static int getSign(int number) {
    if(number >= 0) return 1;
    return -1;
  } 
}
