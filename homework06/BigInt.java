/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  BigInt.java
 *  Purpose       :  Provides a class defining methods for the BigInt class
 *  @author       :  Sebastian Grasso
 *  Date written  :  2018-04-20
 *  Description   :  CMSI 186/Homework 6.
 *
 *  Notes         :
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException for invalid input args
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */



import java.lang.String;
import java.lang.StringBuffer;
import java.lang.Object;
import java.util.Arrays;



public class BigInt {
   // a BigInt classwide constant for zero
   public static final BigInt ZERO = new BigInt("0");
   // a BigInt classwide constant for one
   public static final BigInt ONE = new BigInt("1");
   // a BigInt classwide constant for ten
   public static final BigInt TEN = new BigInt("10");
   private String initialVal = "";
   private int[] bigArray;
   private int posNegZero;

   public static void main(String[] args) {
      BigInt neg = new BigInt("-3412343214132412342343241341324123421432341243214131");
      BigInt pos = new BigInt("3412343214132412342343241341324123421432341243214131");
      BigInt zeroes = new BigInt("0123");
      BigInt zeroes1 = new BigInt("000123");
      BigInt zeroes2 = new BigInt("0000123");
      BigInt zeroes3 = new BigInt("-0010123");
      BigInt zeroes4 = new BigInt("0010123");
      BigInt zeroes5 = new BigInt("999");
      BigInt zeroes6 = new BigInt("99");
      BigInt zeroes7 = new BigInt("911");
      BigInt zeroes8 = new BigInt("99");
      BigInt zeroes9 = new BigInt("1");
      BigInt zeroes10 = new BigInt("2");
      BigInt zeroes11 = new BigInt("-25");
      BigInt zeroes12 = new BigInt("50");
      System.out.println("\n Creating a negative BigInt: " + neg.toString());
      System.out.println("\n Creating a positive BigInt: " + pos.toString());
      System.out.println("\n Creating a positive BigInt: " + zeroes.toString());
      System.out.println("\n Creating a positive BigInt: " + zeroes1.toString());
      System.out.println("\n Creating a positive BigInt: " + zeroes2.toString());
      System.out.println("\n Creating a negative BigInt: " + zeroes3.toString());
      System.out.println("\n Creating a positive BigInt: " + zeroes4.toString());
      System.out.println("\n Is -3412343214132412342343241341324123421432341243214131 positive? " + neg.isPositive());
      System.out.println("\n Is 3412343214132412342343241341324123421432341243214131 positive? " + pos.isPositive());
      System.out.println("\n Comparing two BigInt. Expecting ... 0 " +  "Got: " + zeroes.compareTo(zeroes1));
      System.out.println("\n Comparing two BigInt. Expecting ... true " +  "Got: " + zeroes.equals(zeroes1));
      System.out.println("\n Comparing two BigInt. Expecting ... 1 " +  "Got: " + zeroes.compareTo(neg));
      System.out.println("\n Comparing two BigInt. Expecting ... -1 " +  "Got: " + zeroes.compareTo(pos));
      System.out.println("\n Adding two BigInt. Expecting ... 246 " +  "Got: " + zeroes.add(zeroes1));
      System.out.println("\n Adding two BigInt. Expecting ... 1098 " +  "Got: " + zeroes5.add(zeroes6));
      System.out.println("\n Adding two BigInt. Expecting ... double pos " +  "Got: " + pos.add(pos));
      System.out.println("\n Adding two BigInt. Expecting ... -20246 " +  "Got: " + zeroes3.add(zeroes3));
      System.out.println("\n Subtracting two BigInt. Expecting ... 812 " +  "Got: " + zeroes7.subtract(zeroes8));
      System.out.println("\n Subtracting two BigInt. Expecting ... -812 " +  "Got: " + zeroes8.subtract(zeroes7));
      System.out.println("\n Subtracting two BigInt. Expecting ... -1 " +  "Got: " + zeroes9.subtract(zeroes10));
      System.out.println("\n Subtracting two BigInt. Expecting ... 1 " +  "Got: " + zeroes10.subtract(zeroes9));
      System.out.println("\n Subtracting two BigInt. Expecting ... 75 " +  "Got: " + zeroes12.subtract(zeroes11));
      System.out.println("\n Subtracting two BigInt. Expecting ... -75 " +  "Got: " + zeroes11.subtract(zeroes12));
      System.out.println("\n Subtracting two BigInt. Expecting ... 0 " +  "Got: " + zeroes11.subtract(zeroes11));

      BigInt one = new BigInt("8");
      BigInt two = new BigInt("5");
      System.out.println("\n Adding two BigInt. Expecting ... 13 " +  "Got: " + one.add(two));
   }

	public BigInt( String value ) {
      try{
         initialVal = new String(value);

         // If number starts with 0 and is positive
         if (value.charAt(0) == '0') {
            int count = 1;
            for (int i = 0; i < value.length() - 1; i++) {
               if (value.charAt(i) == '0' && value.charAt(i + 1) == '0') {
                  count++;
               }
            }

            value = value.substring(count);
         }

         // If it is a negative number, store the sign in a char Array
         if (initialVal.charAt(0) == '-') {
            posNegZero = -1;
            value = value.substring(1);

            if (value.charAt(0) == '0') {
            int count = 1;
               for (int i = 0; i < value.length() - 1; i++) {
                  if (value.charAt(i) == '0' && value.charAt(i + 1) == '0') {
                     count++;
                  }
               }

               value = value.substring(count);
            }

            bigArray = new int[value.length()];

            for (int i = 0; i < value.length(); i++) {
               bigArray[i] = Integer.parseInt(String.valueOf(value.charAt(i)));
            }


         // If not a negative number, store normally
         } else {
            posNegZero = 1;
            bigArray = new int[value.length()];

            for (int i = 0; i < value.length(); i++) {
               bigArray[i] = Integer.parseInt(String.valueOf(value.charAt(i)));
            }
         }

      } catch (NumberFormatException ex) {
         throw new NumberFormatException("Sorry, BigInt must be numbers only.");
      }
   }

   public boolean isPositive() {
      if (posNegZero == -1) {
         return false;
      }
      return true;
   }

   // returns the decimal string represention of this BigInt
   public String toString() {
      String finalAnswer = "";
      String temp = "";
      if (posNegZero == -1) {
         for (int i = 0; i < bigArray.length; i++) {
            temp += Integer.toString(bigArray[i]);
         }
         finalAnswer = "-" + temp;
      } else {
         for (int i = 0; i < bigArray.length; i++) {
            finalAnswer += Integer.toString(bigArray[i]);
         }
      }

      return finalAnswer;
   }

   // returns -1/0/1 as this BigInt is numerically less than/equal to/greater than the value passed as the argument
   public int compareTo( BigInt g ) {

      // If both numbers are positive
      if (g.isPositive() == true && isPositive() == true) {
         if (bigArray.length > g.bigArray.length) {
            return 1;
         } else if (bigArray.length < g.bigArray.length) {
            return -1;
         } else if (bigArray.length == g.bigArray.length) {
            for (int i = 0; i < bigArray.length; i++) {
               if (bigArray[i] > g.bigArray[i]) {
                  return 1;
               } else if (bigArray[i] < g.bigArray[i]) {
                  return -1;
               } else if (bigArray[i] == g.bigArray[i]) {
                  return 0;
               }
            }
         }
      } else if (g.isPositive() == false && isPositive() == false) {
         if (bigArray.length < g.bigArray.length) {
            return 1;
         } else if (bigArray.length > g.bigArray.length) {
            return -1;
         } else if (bigArray.length == g.bigArray.length) {
            for (int i = 1; i < bigArray.length + 1; i++) {
               if (bigArray[i] < g.bigArray[i]) {
                  return 1;
               } else if (bigArray[i] > g.bigArray[i]) {
                  return -1;
               } else if (bigArray[i] == g.bigArray[i]) {
                  return 0;
               }
            }
         }
      }

      // If only one of the numbers is positive
      if (isPositive() == true && g.isPositive() == false) {
         return 1;
      } else {
         return -1;
      }
   }

   // returns true iff x is a BigInt whose value is numerically equal to this BigInt
   public boolean equals(BigInt g) {
      if (compareTo(g) == 0) {
         return true;
      }
      return false;
   }



   // returns a BigInt whose value is the sum of this plus the argument
   public String add(BigInt g){
      int[] combineArray;
      int carry = 0;
      int[] smallerArray;
      String answer = "";

      // Gives one more space in array to combine numbers
      if (compareTo(g) == 1) {
         combineArray = new int[bigArray.length + 1];

         for(int i = 0; i < bigArray.length; ++i) {
            combineArray[combineArray.length - 1 - i] = bigArray[bigArray.length - 1 - i];
         }

         smallerArray = g.bigArray.clone();
      } else {
         combineArray = new int[g.bigArray.length + 1];
         combineArray = g.bigArray.clone();
         smallerArray = bigArray.clone();
      }

      // Adds positive numbers
      if (isPositive() == true && g.isPositive() == true) {

         for (int i = 0; i < smallerArray.length; i++) {
            combineArray[combineArray.length - 1 - i] += smallerArray[smallerArray.length - 1 - i];
         }

         for (int i = 0; i < combineArray.length - 1; i++) {
            if (combineArray[combineArray.length - 1 - i] > 9) {
               combineArray[combineArray.length - 2 - i] += 1;
               combineArray[combineArray.length - 1 - i] -= 10;
            }
         }
         StringBuffer result = new StringBuffer();
         for (int i = 0; i < combineArray.length; i++) {
            result.append(combineArray[i]);
         }
         answer = result.toString();
      }

      // Adds negative numbers
      if (isPositive() == false && g.isPositive() == false) {
         for (int i = 0; i < smallerArray.length; i++) {
            combineArray[combineArray.length - 1 - i] += smallerArray[smallerArray.length - 1 - i];
         }

         for (int i = 0; i < combineArray.length; i++) {
            if (combineArray[combineArray.length - 1 - i] > 9) {
               combineArray[combineArray.length - 2 - i] += 1;
               combineArray[combineArray.length - 1 - i] -= 10;
            }
         }
         StringBuffer result = new StringBuffer();
         for (int i = 0; i < combineArray.length; i++) {
            result.append(combineArray[i]);
         }
         answer = "-" + result.toString();
      }

      // Adds one positive number and one negative number
      if (isPositive() == true && g.isPositive() == false || isPositive() == false && g.isPositive() == true) {
         subtract(g);
         StringBuffer result = new StringBuffer();
         for (int i = 0; i < bigArray.length; i++) {
            result.append(bigArray[i]);
         }
         answer = result.toString();
      }



      return answer;
   }


   // returns a BigInt whose value is the difference of this minus the argument
   public String subtract(BigInt g) {
      int[] combineArray;
      int carry = 0;
      int[] smallerArray;
      String answer = "";

      // Gives one more space in array to combine numbers
      if (compareTo(g) == 1) {
         combineArray = new int[bigArray.length + 1];

         for(int i = 0; i < bigArray.length; ++i) {
            combineArray[combineArray.length - 1 - i] = bigArray[bigArray.length - 1 - i];
         }

         smallerArray = g.bigArray.clone();
      } else {
         combineArray = new int[g.bigArray.length + 1];
         combineArray = g.bigArray.clone();
         smallerArray = bigArray.clone();
      }

      // Subtracts Both positive numbers
      if (isPositive() == true && g.isPositive() == true) {
         for (int i = 0; i < smallerArray.length; i++) {
            combineArray[combineArray.length - 1 - i] -= smallerArray[smallerArray.length - 1 - i];
         }

         for (int i = 0; i < combineArray.length; i++) {
            if (combineArray[combineArray.length - 1 - i] < 0) {
               combineArray[combineArray.length - 2 - i] -= 1;
               combineArray[combineArray.length - 1 - i] += 10;
            }
         }

         if (compareTo(g) == 1 || compareTo(g) == 0) {
            StringBuffer result = new StringBuffer();
            for (int i = 0; i < combineArray.length; i++) {
               result.append(combineArray[i]);
            }
            answer = result.toString();
         } else {
            StringBuffer result = new StringBuffer();
            for (int i = 0; i < combineArray.length; i++) {
               result.append(combineArray[i]);
            }
            answer = "-" + result.toString();
         }
      }

      // Subtracts if first one is positive and second is negative
      if (isPositive() == true && g.isPositive() == false) {
         for (int i = 0; i < smallerArray.length; i++) {
            combineArray[combineArray.length - 1 - i] += smallerArray[smallerArray.length - 1 - i];
         }

         for (int i = 0; i < combineArray.length; i++) {
            if (combineArray[combineArray.length - 1 - i] > 9) {
               combineArray[combineArray.length - 2 - i] += 1;
               combineArray[combineArray.length - 1 - i] -= 10;
            }
         }
         StringBuffer result = new StringBuffer();
         for (int i = 0; i < combineArray.length; i++) {
            result.append(combineArray[i]);
         }
         answer = result.toString();
      }

      // Subtracts if first one is negative and second is positive
      if (isPositive() == false && g.isPositive() == true) {
         for (int i = 0; i < smallerArray.length; i++) {
            combineArray[combineArray.length - 1 - i] += smallerArray[smallerArray.length - 1 - i];
         }

         for (int i = 0; i < combineArray.length; i++) {
            if (combineArray[combineArray.length - 1 - i] > 9) {
               combineArray[combineArray.length - 2 - i] += 1;
               combineArray[combineArray.length - 1 - i] -= 10;
            }
         }

         StringBuffer result = new StringBuffer();
         for (int i = 0; i < combineArray.length; i++) {
            result.append(combineArray[i]);
         }
         answer = "-" + result.toString();
      }

      //Subtracts Both negative numbers
      if (isPositive() == false && g.isPositive() == false) {
         for (int i = 0; i < smallerArray.length; i++) {
            combineArray[combineArray.length - 1 - i] -= smallerArray[smallerArray.length - 1 - i];
         }

         for (int i = 0; i < combineArray.length; i++) {
            if (combineArray[combineArray.length - 1 - i] < 0) {
               combineArray[combineArray.length - 2 - i] -= 1;
               combineArray[combineArray.length - 1 - i] += 10;
            }
         }

         if (compareTo(g) == 1 || compareTo(g) == 0) {
            StringBuffer result = new StringBuffer();
            for (int i = 0; i < combineArray.length; i++) {
               result.append(combineArray[i]);
            }
            answer = "-" + result.toString();
         } else {
            StringBuffer result = new StringBuffer();
            for (int i = 0; i < combineArray.length; i++) {
               result.append(combineArray[i]);
            }
            answer = result.toString();
         }
      }

      return answer;
   }




/*
	public BigInt multiply( BigInteger value );
	// returns a BigInt whose value is the product of this times the argument
	public BigInt divide( BigInteger value );
	// returns a BigInt whose value is the quotient of this divided by the argument
	public BigInt remainder( BigInteger value );
	// returns a BigInt whose value is the remainder of this divided by the argument
	public static BigInt valueOf( long value );
	// a BigInt "static factory" for constructing BigInt out of longs
*/
}
