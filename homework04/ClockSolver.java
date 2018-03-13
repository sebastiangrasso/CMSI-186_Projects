/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @see
 *  @author       :  B.J. Johnson
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
  *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class ClockSolver {
  /**
   *  Class field definintions go here
   */
   private static double seconds = 0.00;
   private static double minutes = 0.00;
   private static double hours = 0.00;
   private static double targetAngle = 0.00;
   private static double actualAngle = 0.00;
   private static double timeSlice = 0.00;
   private static double possAngle = 5.00;
   private final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
   private final double DEFAULT_TIME_SLICE_SECONDS = 60.0;
   private final double EPSILON_VALUE              = 0.1;      // small value for double-precision comparisons

  /**
   *  Constructor
   *  This just calls the superclass constructor, which is "Object"
   */
   public ClockSolver() {
   }

  /**
   *  Method to handle all the input arguments from the command line
   *   this sets up the variables for the simulation
   */
   public void handleInitialArguments( String[] paramArrayOfString) {
     // args[0] specifies the angle for which you are looking
     //  your simulation will find all the angles in the 12-hour day at which those angles occur
     // args[1] if present will specify a time slice value; if not present, defaults to 60 seconds
     // you may want to consider using args[2] for an "angle window"

      System.out.println( "\n   Hello world, from the ClockSolver program!!\n\n" ) ;
      if( 0 == paramArrayOfString.length ) {
         System.out.println( "   Sorry you must enter at least one argument\n" +
                             "   Usage: java ClockSolver <angle> [timeSlice]\n" +
                             "   Please try again..........." );
         System.exit( 1 );
      }
      Clock clock = new Clock();
      String input = "";
      try{
		  targetAngle = clock.validateAngleArg(paramArrayOfString[0]);
		  if (paramArrayOfString.length > 1)
		  	input = paramArrayOfString[1];
		  else
		  	input = "60.00";
	      timeSlice = clock.validateTimeSliceArg(input);
	      if (paramArrayOfString.length > 2){
			  possAngle = Double.parseDouble(paramArrayOfString[2]);
	  	  }
  	   }
	   catch (NumberFormatException localNumberFormatException) {
		   System.out.println("Invalid Arguments. Try again with valid number entries");

   			System.exit(1);
   }
 	   catch (IllegalArgumentException localIllegalArgumentException){
   		   System.out.println("Invalid Arguments. Try again with valid number entries");


   			System.exit(1);
   }
	System.out.println("Simulation is running. \n Looking for angle of " + targetAngle + " degrees \n Time Slice : " + timeSlice + "seconds");
}

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  args  String array of the arguments from the command line
   *                args[0] is the angle for which we are looking
   *                args[1] is the time slice; this is optional and defaults to 60 seconds
   */
   public static void main( String args[] ) {
      ClockSolver cse = new ClockSolver();
      Clock clock1    = new Clock();
      double[] timeValues = new double[3];
      cse.handleInitialArguments(args);
      while( true ) {
		 timeValues = clock1.tick(seconds, minutes, hours, timeSlice);
		 actualAngle = clock1.getHandAngle();
		 if (possAngle >= Math.abs(targetAngle - actualAngle)) {
			 System.out.println("Found target angle of " + targetAngle + " at time: " + clock1.toString());
	 	 }
	 	 if (43200.00 <= clock1.getTotalSeconds()){
         break;
		 }
      }
      System.exit( 0 );
   }
}
