import java.util.Scanner;

public class task1 {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.print("Input x: ");
    int x = scan.nextInt();
    
    System.out.print("Input y: ");

    int y = scan.nextInt();

    boolean firstQuarter = (x > 0 && y > 0);
    boolean secondQuarter = (x < 0 && y > 0);
    boolean thirdQuarter = (x < 0 && y < 0);
    boolean fourthQuarter = (x > 0 && y < 0);

    if (firstQuarter)
      System.out.println("Quarter 1");
    else if (secondQuarter)
      System.out.println("Quarter 2");
    else if (thirdQuarter)
      System.out.println("Quarter 3");
    else if (fourthQuarter)
      System.out.println("Quarter 4");
    else 
      System.out.println("Wrong input");
  }

}