import java.util.Arrays;
import java.util.Scanner;

public class task {

  public static int findNegativeAverage(int[] array) {
    int count = 0;
    int sum = 0;
    for (final int el : array) {
      if(el < 0){
        sum += el;
        count++;
      }
    }
    return sum / count;
  }

  public static boolean hasNegative(int[] array){
    int negativeNumbers = 0;
    for(int el : array) {
      if(el < 0) negativeNumbers++;
    }

    return negativeNumbers != 0;
  }

  public static int[] inputArray(int size){
    Scanner scan = new Scanner(System.in);
    int input;
    int[] arr = new int[size];

    for (int i = 0; i < size; i++) {
      while (true) {
        try {
          input = scan.nextInt();
          break;
        } catch (Exception e) {
          System.out.print("Enter only number type byte: ");
          scan.nextLine();
        }
      }
      arr[i] = input;
    }
    return arr;
  }
  
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int size;
    while (true) {
      try {
        System.out.print("Enter array size: ");
        size = scan.nextInt();
        if(size < 0){
          System.out.println("Size should be an positive: ");
          continue;
        }
        break;
      } catch (Exception e) {
        System.out.println("Input should be an integer: ");
        scan.nextLine();
      }
    }
    System.out.print("Enter array elements: ");

    int[] arr = inputArray(size);
    if(!hasNegative(arr)){
      System.out.print("Array don't have negative numbers: ");
      System.exit(0);
    }

    System.out.println("Array: " + Arrays.toString(arr));
    System.out.println("Array Negative Average: " + findNegativeAverage(arr));
  }
}