public class task5 {
  public static void main(String[] args) {
    String row1 = "Year is 2009";
    String row2 = "YNot2Bad";
    String row3 = "1 is a number";
    String row4 = "No digits here";
    String[] rows = {row1, row2, row3, row4};
    int intValue;

    for(String row : rows){
      System.out.println("Row: " + row);  
      String[] words = row.split(" ");
      
      for (int i = 0; i < words.length; i++) {
        words[i] = words[i].replaceAll("[^\\w]", "");
      }
      
      boolean checked = false;
      System.out.println("Result: ");
      for (int i = 0; i < words.length; i++) {
        try {
          intValue = Integer.parseInt(words[i]);
          System.out.println(words[i] + " - Integer");
          checked = true;
        } catch (NumberFormatException e) {
          System.out.println(words[i] + " - Not Integer");
        }

      }
      if (checked) {
        System.out.println("Row '" + row + "' has integers");
      }
      else {
        System.out.println("Row '" + row + "' does not contain any integers");
      }
      System.out.println();
    }
  }
}
