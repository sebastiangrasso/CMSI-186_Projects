/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  Sebastian Grasso
 * Date       :  4/20/18
 * Description:  CMSI 186- Homework #6
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;

public class BrobInt {

  /// constants
   public static final BrobInt MAX_INT  = new BrobInt( new Integer( Integer.MAX_VALUE ).toString() );
   public static final BrobInt MIN_INT  = new BrobInt( new Integer( Integer.MIN_VALUE ).toString() );
   public static final BrobInt MAX_LONG = new BrobInt( new Long( Long.MAX_VALUE ).toString() );
   public static final BrobInt MIN_LONG = new BrobInt( new Long( Long.MIN_VALUE ).toString() );
   public static final BrobInt NEGONE  = new BrobInt("-1");
   public static final BrobInt ZERO     = new BrobInt(  "0" );
   public static final BrobInt ONE      = new BrobInt(  "1" );
   public static final BrobInt TWO      = new BrobInt(  "2" );
   public static final BrobInt THREE    = new BrobInt(  "3" );
   public static final BrobInt FOUR     = new BrobInt(  "4" );
   public static final BrobInt FIVE     = new BrobInt(  "5" );
   public static final BrobInt SIX      = new BrobInt(  "6" );
   public static final BrobInt SEVEN    = new BrobInt(  "7" );
   public static final BrobInt EIGHT    = new BrobInt(  "8" );
   public static final BrobInt NINE     = new BrobInt(  "9" );
   public static final BrobInt TEN      = new BrobInt( "10" );
   private String val;
   private boolean   isPositive;    // "0" is positive, "1" is negative
   private String backwards;        // string, reversed
   private byte[] arrayOfBytes;      // array of strings

  /**
   *  Constructor
   *  @param String representation of BrobInt
   */

   public BrobInt( String valueArg ) {
      try{
          validateDigits(valueArg);
      }
      catch(IllegalArgumentException iae){
          System.out.println(iae.toString());
          System.out.println("Enter Big Int");
          System.exit(1);
      }

      if(valueArg.charAt(0) == '+') {
          isPositive = true;
          val = new String(valueArg.substring(1));
      }

      else if(valueArg.charAt(0) == '-'){
          isPositive = false;
          val = new String(valueArg.substring(1));
      }

      else{
          isPositive = true;
          val = new String(valueArg.substring(0));
      }

      arrayOfBytes = strToByte(val);

      backwards = new String(reverser(val));

   }

   /**
    *  Constructor
    *  @param  BrobInt of large integer, boolean determining sign
    */
    public BrobInt( BrobInt brobArg, boolean isPosArg) {
       val = new String(brobArg.toString().substring(1));
       isPositive = isPosArg;
       arrayOfBytes = brobArg.getByteArr();
       backwards = new String(reverser(val));
    }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Ensures all chars in string are numbers/sings
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException if arguments are invalid
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void validateDigits(String strArg) throws IllegalArgumentException {

       if(strArg.charAt(0) == '+' || strArg.charAt(0) == '-'){
          for(int i =1;i<strArg.length();i++){
              try{
                  Integer.parseInt(strArg.substring(i,i+1));
              }
              catch(NumberFormatException nfe){
                  throw new IllegalArgumentException("Non-number inputs.");
              }
          }
      }
      else{
          for(int i =0;i<strArg.length();i++){
              try{
                  Integer.parseInt(strArg.substring(i,i+1));
              }
              catch(NumberFormatException nfe){
                  throw new IllegalArgumentException("Non-number inputs.");
              }
          }
      }
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Reverse value of BrobInt
   *  @return backwards representation of brobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt reverser() {
      StringBuffer strForBrob = new StringBuffer();
      if(isPositive){
          strForBrob.append("+");
      }
      else{
          strForBrob.append("-");
      }
      strForBrob.append(this.reverser(val));
      return (new BrobInt(strForBrob.toString()));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Reverse value of BrobInt
   *  @return backwards representation of brobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt reverser( BrobInt brobInteger ) {
       return brobInteger.reverser();
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Reverse value of BrobInt
   *  @return backwards representation of brobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static String reverser(String strArg){
       return new String( new StringBuffer( strArg ).reverse() );

   }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  converts string to byte
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static byte[] strToByte(String strArg){

       byte[] finalByte = new byte[(strArg.length()/2)+(strArg.length()%2)];
       int index = strArg.length()%2;

       if(index == 1){
          finalByte[0] = Byte.parseByte(strArg.substring(0,1));
       }
       else{
           finalByte[0]=Byte.parseByte(strArg.substring(0,2));
       }

        for(int i =1; i<finalByte.length-1; i+=1){
            finalByte[i] = Byte.parseByte(strArg.substring(2*i-index,2*(i+1)-index    ));
        }
        if(strArg.length()>=2){
            finalByte[finalByte.length-1] = Byte.parseByte(strArg.substring(strArg.length()-2));
        }

       return finalByte;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  adds two BrobInts
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt addByte( BrobInt brobInteger ) {
       String strForBrob = new String("");
       boolean finalPositive = true;

       if(this.compareTo(brobInteger) >= 0){ //this>arg
           if(isPositive && brobInteger.getPositive()){
               strForBrob = addHelper(arrayOfBytes,brobInteger.getByteArr());
               finalPositive = true;
           }
           else if(isPositive && !(brobInteger.getPositive())){
               strForBrob = subHelper(arrayOfBytes,brobInteger.getByteArr());
               finalPositive = true;
           }
           else if(!(isPositive) && brobInteger.getPositive()){
               strForBrob = subHelper(arrayOfBytes,brobInteger.getByteArr());
               finalPositive = false;
           }
           else{
               strForBrob = addHelper(arrayOfBytes,brobInteger.getByteArr());
               finalPositive = false;
           }
       }
       else if (this.compareTo(brobInteger) < 0){ //arg>this
           if(isPositive && brobInteger.getPositive()){
               strForBrob = addHelper(arrayOfBytes,brobInteger.getByteArr());
               finalPositive = true;
           }
           else if(isPositive && !(brobInteger.getPositive())){
               strForBrob = subHelper(brobInteger.getByteArr(),arrayOfBytes);
               finalPositive = false;
           }
           else if(!(isPositive) && brobInteger.getPositive()){
               strForBrob = subHelper(brobInteger.getByteArr(),arrayOfBytes);
               finalPositive = true;
           }
           else{
               strForBrob = addHelper(arrayOfBytes,brobInteger.getByteArr());
               finalPositive = false;
           }
       }



       StringBuilder tempBuild = new StringBuilder();
       if(finalPositive){
           tempBuild.append("+");
       }
       else{
           tempBuild.append("-");
       }
       tempBuild.append(strForBrob);

       return new BrobInt(tempBuild.toString());

   }

   public String addHelper(byte[] bArrOne, byte[] bArrTwo){
       boolean carry = false;
       StringBuilder strForBrob = new StringBuilder();
       int holder;

       for(int i = bArrTwo.length -1; i>=0 ;i--){
           holder = (int)bArrOne[i] + (int)bArrTwo[i];
           if(carry){
               holder+=1;
           }

            if(holder>=100){
                carry = true;
                holder-=100;
                strForBrob.insert(0,holder);
            }
            else{
                strForBrob.insert(0,holder);
                carry = false;
            }
       }
       for(int i = bArrTwo.length; i<bArrOne.length;i++){
           strForBrob.insert(0,bArrOne[i]);
       }

       return strForBrob.toString();
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  adds two BrobInts
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt add( BrobInt brobInteger ) {
      return this.addByte(brobInteger);
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  adds two BrobInts
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt addInt( BrobInt brobInteger ) {
      return this.addByte(brobInteger);
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  subtracts two BrobInts
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtractByte( BrobInt brobInteger ) {
       String strForBrob = new String("");
       boolean finalPositive = true;

       if(this.compareTo(brobInteger) >= 0){ //this>arg
           if(isPositive && brobInteger.getPositive()){
               strForBrob = subHelper(arrayOfBytes,brobInteger.getByteArr());
               finalPositive = true;
           }
           else if(isPositive && !(brobInteger.getPositive())){
               strForBrob = addHelper(arrayOfBytes,brobInteger.getByteArr());
               finalPositive = true;
           }
           else if(!(isPositive) && brobInteger.getPositive()){
               strForBrob = addHelper(arrayOfBytes,brobInteger.getByteArr());
               finalPositive = false;
           }
           else{
               strForBrob = subHelper(arrayOfBytes,brobInteger.getByteArr());
               finalPositive = false;
           }
       }
       else if (this.compareTo(brobInteger) < 0){ //arg>this
           if(isPositive && brobInteger.getPositive()){
               strForBrob = subHelper(brobInteger.getByteArr(),arrayOfBytes);
               finalPositive = false;
           }
           else if(isPositive && !(brobInteger.getPositive())){
               strForBrob = addHelper(brobInteger.getByteArr(),arrayOfBytes);
               finalPositive = true;
           }
           else if(!(isPositive) && brobInteger.getPositive()){
               strForBrob = addHelper(brobInteger.getByteArr(),arrayOfBytes);
               finalPositive = false;
           }
           else{
               strForBrob = subHelper(brobInteger.getByteArr(),arrayOfBytes);
               finalPositive = true;
           }
       }

       StringBuilder tempBuild = new StringBuilder();
       if(finalPositive){
           tempBuild.append("+");
       }
       else{
           tempBuild.append("-");
       }
       tempBuild.append(strForBrob);

       return new BrobInt(tempBuild.toString());
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  subtracts two BrobInts
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtract( BrobInt brobInteger ) {
      return this.subtractByte(brobInteger);
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  subtracts two BrobInts
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtractInt( BrobInt brobInteger ) {
      return this.subtractByte(brobInteger);
   }


   public String subHelper(byte[] bArrOne, byte[] bArrTwo){
       boolean carry = false;
       StringBuilder strForBrob = new StringBuilder();
       int holder;

       for(int i = bArrTwo.length-1;i>=0;i--){
           holder = (int)bArrOne[i] - (int)bArrTwo[i];
           if(carry){
               holder-=1;
           }

            if(holder<0){
                carry = true;
                holder+=100;
                strForBrob.insert(0,holder);
            }
            else{
                strForBrob.insert(0,holder);
                carry = false;
            }
       }
       for(int i = bArrTwo.length; i<bArrOne.length;i++){
           strForBrob.insert(0,bArrOne[i]);
       }

       return strForBrob.toString();
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  multiplies two brobInts
   *  NOT WORKING
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt multiply( BrobInt brobInteger ) {
       throw new UnsupportedOperationException();
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  divides two brobInts
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt divide( BrobInt brobInteger ) throws ArithmeticException {//this/arg
      BrobInt brobToReturn = new BrobInt("0");
      String temp;
      BrobInt holder = new BrobInt("0");
      BrobInt currOut = new BrobInt("0");

      if(brobInteger.compareTo(TWO) == 0){
          throw new ArithmeticException("Cannot divide by zero");
      }
      else if(this.compareTo(brobInteger) < 0 ){
          return ZERO;
      }

      for(int i =0; i < brobInteger.toString().substring(1).length(); i++){
          holder = this.multiply(currOut);
          temp = brobInteger.toString().substring(2*i+1,2*(i+1)+1);
          temp = holder.toString().substring(1).concat(temp);
          holder = new BrobInt(temp);
          currOut = holder.divide(brobInteger);
          if(currOut.compareTo(TEN) < 0){
              brobToReturn = new BrobInt(brobToReturn.toString().concat("0"));
              brobToReturn = new BrobInt(brobToReturn.toString().concat(currOut.toString().substring(0)));
          }
          else{
              brobToReturn = new BrobInt(brobToReturn.toString().concat(currOut.toString().substring(0)));
          }
      }

      if(!(isPositive)){
          String a = new String("-");
          brobToReturn = new BrobInt(a.concat(brobToReturn.toString().substring(1)));
      }

      return brobToReturn;

   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  remainder of two brobInt division
   *  NOT WORKING
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt remainder( BrobInt brobInteger ) {
       throw new UnsupportedOperationException();
       //return this.subtractByte(brobInteger.multiply(this.divide(brobInteger)));
   }


  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  compares two brobInts for equality
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean equals( BrobInt brobInteger ) {
      return (this.toString().equals( brobInteger.toString() ));
   }


  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  compares two BrobInts
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int compareTo( BrobInt brobInteger ) {

      if(this.toString().charAt(0)== '-' && brobInteger.toString().charAt(0) == '-'){
          return (-1*(this.toString().compareTo( brobInteger.toString() )));
      }
      else if(this.toString().charAt(0)== '-' && brobInteger.toString().charAt(0) == '+'){
          return -1;
      }
      else if(this.toString().charAt(0)== '+' && brobInteger.toString().charAt(0) == '-'){
          return 1;
      }
      else if(this.toString().length() > brobInteger.toString().length()){
          return 1;
      }
      else if(this.toString().length() < brobInteger.toString().length()){
          return -1;
      }
      else{
          return (this.toString().compareTo( brobInteger.toString() ));
      }

   }


  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  returns BrobInt version of long
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt valueOf( long value ) throws NumberFormatException {
      BrobInt BrobInteger = null;
      try {
         BrobInteger = new BrobInt( new Long( value ).toString() );
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n  Sorry, the value must be numeric of type long." );
      }
      return BrobInteger;
   }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  returns array of Bytes
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public byte[] getByteArr(){
       return arrayOfBytes;
   }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  return boolean of sign
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean getPositive(){
       return isPositive;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  return BrobInt as a string
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String toString() {
      StringBuilder strToReturn = new StringBuilder();

      if(isPositive){
          strToReturn.append("+");
      }
      else{
          strToReturn.append("-");
      }

      for( int i = 0; i < arrayOfBytes.length; i++ ) {
          strToReturn.append(arrayOfBytes[i]);
      }

      return strToReturn.toString();
   }

   private byte[] trimByteArr(byte[] byteArg){
       int locat = 0;

       for(int i = byteArg.length - 1; i >= 0;i--){
           if(byteArg[i]!=0){
               locat = i;
               break;
           }
       }

       byte[] byteOut = new byte[byteArg.length-locat];

       for(int i = 0; i < byteOut.length;i++ ){
           byteOut[i] = byteArg[i+locat];
       }

       return byteOut;
   }

   private String trimStr(String strArg){
       byte[] byStr = this.strToByte(strArg);

       byStr = this.trimByteArr(byStr);

       StringBuilder strOut = new StringBuilder();

       for(int i = 0; i < byStr.length; i++){
           strOut.append(byStr[i]);
       }

       return byStr.toString();
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to display an Array representation of this BrobInt as its bytes
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void toArray( byte[] d ) {
      System.out.println( Arrays.toString( d ) );
   }

     /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
      *  main method
      *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
      public static void main( String[] args ) {
     }
}
