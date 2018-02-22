import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll {

   public static void main( String args[] ) throws IOException {

    System.out.println( "\n   Welcome to the High Roll Game!\n" );
    BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
	  int highScore = 0;
	  DiceSet ds = null;
	  int count = 0;
	  int sides = 0;

    System.out.println("Enter the number of sides of your die:");
    sides = Integer.parseInt(input.readLine());

	  System.out.println("Enter the number of die you want to play with:");
	  count = Integer.parseInt(input.readLine());

    ds = new DiceSet(count, sides);

	  System.out.println(" To roll all the die, press 1 ");
    System.out.println(" To roll a single die, press 2 ");
    System.out.println(" To calculate the score for this set, press 3 ");
    System.out.println(" To save your current score, press 4");
    System.out.println(" To display the high score, press 5");
    System.out.println(" To quit, press the 'q' key" );

    while( true ) {
         System.out.print( ">>" );
         String inputLine = null;
         try {
            inputLine = input.readLine();

            if( 0 == inputLine.length() ) {
               throw new IllegalArgumentException("No entry");
            }

            switch(inputLine.charAt(0)){

		case '1': ds.roll();
		  				  System.out.println(ds);
		  				  break;
		case '2': System.out.println("	Which die would you like to roll?");
						  String inputLine2 = input.readLine();
						  int index = Integer.parseInt(inputLine2) - 1;
						  if (index <= count){
						     ds.rollIndividual(index);
						     System.out.println(ds);
						  }
						  else System.out.println("The die you are trying to roll does not exist. Please select another option or try a different die");
						  break;
		case '3': System.out.println("Your score is " + ds.sum());
						  break;
		case '4': highScore = ds.sum();
						  System.out.println("Your high score is saved as " + ds.sum());
						  break;
		case '5': System.out.println("The current high score is " + highScore);
						  break;
		case 'q': System.exit(0);
						  break;
		default : throw new IllegalArgumentException("invalidEntry");
			}

         }
    catch( IOException ioe ) {
      System.out.println( "Caught IOException" );
         }
    catch ( IllegalArgumentException invalidEntry){
		 	System.out.println("Please select a valid option");
		 }

      }
   }
}



