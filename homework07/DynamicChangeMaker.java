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
   */
  public static Tuple makeChangeWithDynamicProgramming(int denominations[], int targetAmount){

	try{
		handleInitialArguments(denominations, targetAmount);
	}
	catch(IllegalArgumentException iae){
	System.out.println("BAD DATA- Impossible Tuple: Please enter <Denominations><Target Amount> \n" +
      "Please do not use negative denominations, duplicate denominations, or a negative Target Value");
	return Tuple.IMPOSSIBLE;
	}

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
   *  Method to check the validity of arguments passed in
   *  @see    http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  denoms[] - int array containing coin denomination
   *          amount -   total target amount
   *  @throws IllegalArgumentException fo invalid arguments
   */
  private static void handleInitialArguments(int denominations[], int targetAmount) throws IllegalArgumentException {
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
    if (targetAmount < 0) {
      throw new IllegalArgumentException();
    }
  }


  /**
   *  @see    http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  Method to process passed in arguments
   *  @param  String[] args
   *          args[0]  Coin denominations
   *          args[1]  Target value
   *  @return int array of processed denominations, last value of the array is the targetAmount
   *  @throws IllegalArgumentException for invalid arguments
   */
  private static int [] processArguments (String[] args) throws IllegalArgumentException {

   if (args.length != 2) {
      throw new IllegalArgumentException();
    }

    String[] stringInputArray = args[0].split(",");
    int[] arrayOfProcessedArgs = new int[stringInputArray.length+1];

    for (int i = 0; i < stringInputArray.length; i++) {
      try {
        arrayOfProcessedArgs[i] = Integer.parseInt(stringInputArray[i]);
      }
      catch (NumberFormatException nfe) {
        throw new IllegalArgumentException();
      }
    }

    try {
        int filler = Integer.parseInt(args[1]);
          arrayOfProcessedArgs[(arrayOfProcessedArgs.length-1)] = filler;
	    }
    catch (NumberFormatException nfe) {
	    throw new IllegalArgumentException();
    }

    return arrayOfProcessedArgs;
  }
  
  /**
   *  @see    http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  String[] args
   *          args[0]  Coin denominations
   *          args[1]  Target value
   *  @throws IllegalArgumentException when input arguments are "hinky"
   */
  
  public static void main( String[] args ) {

    try {

	    int [] processedArgs = processArguments( args );
	    int [] intInputArray = new int[processedArgs.length - 1];
	    int targetAmount = 0;

	    for (int i = 0; i < processedArgs.length-1; i++){
		    intInputArray[i] = processedArgs[i];
  	  }

  	  targetAmount = processedArgs[processedArgs.length-1];

	    Tuple optimalSolution = new Tuple(intInputArray.length);
      String optimalSolutionString = "";

      optimalSolution = makeChangeWithDynamicProgramming(intInputArray, targetAmount);
      optimalSolutionString= optimalSolution.toString();

	    System.out.print("Optimal Solution: " + optimalSolutionString);
      }
      catch (Exception localException) {
        System.out.println("BAD DATA- Impossible Tuple: Please enter <Denominations><Target Amount> \n" +
        "Please do not use negative denominations, duplicate denominations, or a negative Target Value");
	    System.exit(0);
	  }

  }
}
