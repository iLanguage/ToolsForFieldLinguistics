import java.util.Arrays;

/** 
 *
 * Location https://coderpad.io/WGT7MX76 
 * 
 * Example showing how to work with two arrays which need
 * to be aligned at their ends (not starts), and where the 
 * result of the current might affect the next.
 *
 * Useful for:
 *  - binary arithmetic ("101" "010" -> "111" OR "101" "011" -> "1000")
 *  - iterating through arrays of json where the following item might affect the result of the previous (a carry behavior)
 */
public class BinaryArithmetic {

  /**
   * minimal time is going to be max length of one of the string 
   * x3 because of 
   *  - initialization and 
   *  - actual computation loop
   *  - conversion to string
   */
  static String addBinaryStrings(String a, String b) throws Exception {
    if (a == null) {
      a = "0";
    }
    if (b == null) {
      b = "0";
    }
    int alength = a.length();
    int blength = b.length();

    int maxLength = Math.max(alength, blength);
    if (maxLength == 0) {
      return "0";
    }
    int[] result = new int[maxLength]; // initialize? 

    System.out.println("Padding inputs to a max length of " + maxLength);
    for (int i = 0; i < maxLength - alength; i++) {
      a = "0" + a;
    }
    for (int i = 0; i < maxLength - blength; i++) {
      b = "0" + b;
    }
    System.out.println("  padded a:" + a);
    System.out.println("  padded b:" + b);

    // Initialize the results
    for (int i = 0; i < maxLength; i++) {
      result[i] = 0;
    }

    //keep track of the carry
    int carry = 0;
    for (int current = maxLength - 1; current >= 0; current--) {
      System.out.println("Working on " + current);
      char acurrent = a.charAt(current);
      char bcurrent = b.charAt(current);

      System.out.println("Sum of " + acurrent + " + " + bcurrent + " =");
      int sum = addChar(acurrent, bcurrent) + carry;
      System.out.println("     = " + sum);
      if (sum == 3) {
        result[current] = 1;
        carry = 1;
        System.out.println(" Carrying further " + carry);
      } else if (sum == 2) {
        result[current] = 0;
        carry = 1;
        System.out.println(" Carrying " + carry);
      } else {
        result[current] = sum;
        carry = 0;
      }
    }

    String asString = "";
    if (carry == 1) {
      asString += "1";
    }
    for (int i = 0; i < maxLength; i++) {
      asString += result[i];
    }
    return asString;
  }

  static int addChar(char a, char b) throws Exception {
    if ((a != '0' && a != '1') || (b != '0' && b != '1')) {
      throw new Exception("Invalid input, use 0 or 1");
    }

    if (a == '0' && b == '0') {
      return 0;
    } else if (a == '0' && b == '1' || a == '1' && b == '0') {
      return 1;
    } else if (a == '1' && b == '1') {
      return 2;
    }
    return 0;
  }

}
