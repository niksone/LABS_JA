import java.util.Scanner;

public class task3 {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.print("Input n: ");
    int n = scan.nextInt();

    int[][] matrix = new int[n][n];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        matrix[i][j] = (int) (Math.random() * 10);
      }
    }
    System.out.println("Matrix: "); //print
    for (int i = 0; i < matrix.length; i++, System.out.println()) {
      for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
    }

    int maxElement = Integer.MIN_VALUE;
    int maxI = Integer.MIN_VALUE;
    int maxJ = Integer.MIN_VALUE;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (matrix[i][j] >= maxElement && i + j >= maxI + maxJ) {
          maxElement = matrix[i][j];
          maxI = i;
          maxJ = j;
        }
      }
    }
    System.out.println("Max value is matrix: " + maxElement);

    int minElement = Integer.MAX_VALUE;
    int minI = n;
    int minJ = n;

    for (int i = n - 1; i >= 0; i--) {
      for (int j = n - 1; j >= 0; j--) {
        if ((matrix[i][j] <= minElement) && (i + j) <= (minI + minJ)) {
          minElement = matrix[i][j];
          minI = i;
          minJ = j;
        }
      }
    }
    System.out.println("Min value is matrix: " + minElement);
  }
}
