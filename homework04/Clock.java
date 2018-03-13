import java.io.PrintStream;


/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
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

public class Clock {
  /**
   *  Class field definintions go here
   */
   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
   private static final double INVALID_ARGUMENT_VALUE = -1.0;
   private static final double MAXIMUM_DEGREE_VALUE = 360.0;
   private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834;
   private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;
   private static double totalSeconds = 0.00;
   private static double hourHandLocation = 0.00;
   private static double minuteHandLocation = 0.00;
   private double [] timeStuff = new double [3];

  /**
   *  Constructor goes here, empty?
   */
   public Clock() {

   }

  /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */
   public double [] tick(double input1, double input2, double input3, double input4) {
      totalSeconds += input4;
      double [] timeStuff2 = new double[3];
      timeStuff[0] = (totalSeconds % 60.00);
      timeStuff[1] = ((int)(totalSeconds / 60.00) % 60.00);
      timeStuff[2] = ((int)totalSeconds / 3600.00);
      timeStuff2[0] = (totalSeconds % 60.00);
      timeStuff2[1] = ((int)(totalSeconds / 60.00) % 60.00);
      timeStuff2[2] = ((int)totalSeconds / 3600.00);


      return timeStuff2;
   }

  /**
   *  Method to validate the angle argument
   *  @param   argValue  String from the main programs args[0] input
   *  @return  double-precision value of the argument
   *  @throws  NumberFormatException
   */
   public double validateAngleArg( String argValue ) throws NumberFormatException {

	  double input = Double.parseDouble(argValue);
	  if ((1.00 <= Math.abs(360.00 - input)))
	  	if(input > 0.00)
	  		throw new IllegalArgumentException("Illegal Value");
      return input;
   }

  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main programs args[1] input
   *  @return double-precision value of the argument or -1.0 if invalid
   *  note: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  note: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   */
   public double validateTimeSliceArg( String argValue ) throws NumberFormatException{

      double input = Double.parseDouble(argValue);
      if (0.00 > input){
		  throw new NumberFormatException();
  	  }
      return input;
   }

  /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHourHandAngle() {

      hourHandLocation = HOUR_HAND_DEGREES_PER_SECOND * totalSeconds;
      return hourHandLocation;
   }

  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinuteHandAngle() {

	  minuteHandLocation = MINUTE_HAND_DEGREES_PER_SECOND * (timeStuff[1] * 60.00 + timeStuff[0]);
	  return minuteHandLocation;
   }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandAngle(){

    double input = Math.abs(getHourHandAngle() - getMinuteHandAngle());
    if (input > 180.00) {
      input = 360.00 - input;
    }
    return input;
  }


  /**
   *  Method to fetch the total number of seconds
   *   we can use this to tell when 12 hours have elapsed
   *  @return double-precision value the total seconds private variable
   */
   public double getTotalSeconds() {

	 return totalSeconds;
	}

  /**
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   */
   public String toString() {
      return "" + timeStuff[2] + " hours, " + timeStuff[1] + " minutes, " + timeStuff[0] + " seconds";
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
   public static void main( String args[] ) {

      System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( "  Creating a new clock: " );
      Clock clock = new Clock();
      System.out.println( "    New clock created: " + clock.toString() );
      System.out.println( "    Testing validateAngleArg()....");
      System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
      try { System.out.println( (0.0 == clock.validateAngleArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
   }
}
