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

    	BigInt a = null;
    	BigInt b = null;
    	BigInt c = null;
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
				a = new BigInt("1");
				b = new BigInt("2");
				for (int i = 3; i < temp; i++) {
					c = new BigInt(a.add(b));
					a = new BigInt(b.toString());
					b = new BigInt(c.toString());
				}

				System.out.println(b.toString());
			}
		}
    }
}
