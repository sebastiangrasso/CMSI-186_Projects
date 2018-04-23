/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Fibonacci.java
 *  Purpose       :  Provides a class defining methods for the Fibonacci class
 *  @author       :  Sebastian Grasso
 *  Date written  :  2017-04-20
 *  Description   :  CMSI 186/Homework 6
 *
 *  Notes         :  States what the nth number is in the fibonacci sequence
 *  Warnings      :  It only works until the 8th Fibonacci Sequence.
 *  Exceptions    :  IllegalArgumentException for invalid arguments
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */



public class Fibonacci {
    public static void main(String[] args) {
      if (args.length <= 0) {
         System.out.println("Please enter a number");
      }

      BrobInt a = null;
      BrobInt b = null;
      BrobInt c = null;
      int temp = Integer.parseInt(args[0]);

      if (temp > 5000) {
         System.out.println("\n                            Working......");
      }

      if (args.length == 1) {
         if (temp == 0) {
            System.out.println(0);
            return;
         }
         if (temp <= 2) {
            System.out.println(1);
         } else {
            a = new BrobInt("1");
            b = new BrobInt("2");
            for (int i = 3; i < temp; i++) {
               c = new BrobInt(a.add(b).toString());
               a = new BrobInt(b.toString());
               b = new BrobInt(c.toString());
            }

            System.out.println(b.toString());
         }
      }
    }
}
