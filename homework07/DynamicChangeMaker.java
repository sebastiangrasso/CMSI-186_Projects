/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  DynamicChangeMaker.java
 * Purpose    :  Dynamic Programming as a method to make optimal change
 * @author    :  Sebastian Grasso
 * Date       :  2018-05-03
 * Description:  Finds the optimal change for a target targetAmount given a set of coin denominations
 * Notes      :  N/A
 * Warnings   :  N/A
 * Exceptions :  IllegalArgumentException for invalid inputs
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class DynamicChangeMaker {
  /**
   *  @see    http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  int[] denominations - int array containing coin denomination
   *  @param  int targetAmount - int total target targetAmount
   *  @return Tuple that is the optimized set of coins to reach the target
   *  @throws IllegalArgumentException for invalid inputs
   */
  public static Tuple makeChangeWithDynamicProgramming(int denominations[], int targetAmount) throws IllegalArgumentException {
    handleInitialArguments(denominations, targetAmount);
    Tuple table[][] = new Tuple[denominations.length][targetAmount + 1];

    for (int i = 0; i < denominations.length; i++) {
      table[i][0] = new Tuple(denominations.length);
    }
    for (int j = 0; j < denominations.length; j++) {
      for (int i = 1; i <= targetAmount; i++) {
        if (i - denominations[j] < 0) {
          table[j][i] = Tuple.IMPOSSIBLE;
        } else {
          table[j][i] = new Tuple(denominations.length);
          table[j][i].setElement(j, 1);
          if (table[j][i - denominations[j]].isImpossible()) {
            table[j][i] = Tuple.IMPOSSIBLE;
          } else {
            table[j][i] = (table[j][i]).add(table[j][i - denominations[j]]);
          }
        }
        if (j > 0 && !(table[j - 1][i].isImpossible())) {
          if (table[j][i].isImpossible() || (table[j - 1][i].total() < table[j][i].total())) {
            table[j][i] = table[j - 1][i];
          }
        }
      }
    }
    return table[denominations.length - 1][targetAmount];
  }

  /**
   *  @see    http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  Method to check the validity of the initial arguments against definitions in the assignment
   *  @param  denominations[] -  int array containing coin denomination
   *          targetAmount -   total target targetAmount
   *  @return Tuple that is the optimized set of coins to reach the target
   *  @throws IllegalArgumentException for invalid arguments
   */
  private static void handleInitialArguments(int denominations[], int targetAmount) throws IllegalArgumentException {
//    try{
       for (int i = 0; i < denominations.length; i++) {
   	     if (denominations[i] <= 0) {
           throw new IllegalArgumentException();
          }
         for (int j = i - 1; j >= 0; j--) {
           if (denominations[i] == denominations[j]) {
             throw new IllegalArgumentException();
           }
         }
       }
       if (targetAmount < 0 ) {
         throw new IllegalArgumentException();
       }
//    }
//    catch (IllegalArgumentException iae) {
//	      System.out.println("BAD DATA- Impossible Tuple: Please enter <Denominations><Target Amount> \n" +
//	      "Please do not use negative denominations, duplicate denominations, or a negative Target Value");
//	}
  }

  /**
   *  @see    http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  String[] args
   *          args[0]  Coin denominations
   *          args[1]  Target value
   *  @throws IllegalArgumentException for invalid arguments
   */
  public static void main( String[] args ) {
    if (args.length != 2) {
      System.out.println("Invalid Usage\n"
          + "Please Enter coin denominations followed by target value");
      System.exit(0);
    }

    //Test each denomination is a number and make an int array
    String[] stringInputArray = args[0].split(",");
    int[] intInputArray = new int[stringInputArray.length];
    for (int i = 0; i < stringInputArray.length; i++) {
      try {
        intInputArray[i] = Integer.parseInt(stringInputArray[i]);
      }
      catch (NumberFormatException nfe) {
        System.out.println("Coin denominations must be a number");
        System.exit(0);
      }
    }

    int targetAmount = 0;
    try {
      targetAmount = Integer.parseInt(args[1]);
    }
    catch (NumberFormatException nfe) {
      System.out.println("Target value must be a number");
      System.exit(0);
    }

    String optimalSolution = "";
    try {
      optimalSolution = makeChangeWithDynamicProgramming(intInputArray, targetAmount).toString();
    }
    catch (IllegalArgumentException iae) {
      System.out.println("BAD DATA: Please enter <Denominations><Target Amount/n>" +
      "Please do not use negative denominations, duplicate denominations, or a negative Target Value");
      System.exit(0);
    }
    System.out.print("Optimal Solution: " + optimalSolution);
  }
}
