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
   *  @param String representation of BigInt
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
   public static BrobInt reverser( BrobInt bigInteger ) {
       return bigInteger.reverser();
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
   public BrobInt addByte( BrobInt bigInteger ) {
       String strForBrob = new String("");
       boolean finalPositive = true;

       if(this.compareTo(bigInteger) >= 0){ //this>arg
           if(isPositive && bigInteger.getPositive()){
               strForBrob = addHelper(arrayOfBytes,bigInteger.getByteArr());
               finalPositive = true;
           }
           else if(isPositive && !(bigInteger.getPositive())){
               strForBrob = subHelper(arrayOfBytes,bigInteger.getByteArr());
               finalPositive = true;
           }
           else if(!(isPositive) && bigInteger.getPositive()){
               strForBrob = subHelper(arrayOfBytes,bigInteger.getByteArr());
               finalPositive = false;
           }
           else{
               strForBrob = addHelper(arrayOfBytes,bigInteger.getByteArr());
               finalPositive = false;
           }
       }
       else if (this.compareTo(bigInteger) < 0){ //arg>this
           if(isPositive && bigInteger.getPositive()){
               strForBrob = addHelper(arrayOfBytes,bigInteger.getByteArr());
               finalPositive = true;
           }
           else if(isPositive && !(bigInteger.getPositive())){
               strForBrob = subHelper(bigInteger.getByteArr(),arrayOfBytes);
               finalPositive = false;
           }
           else if(!(isPositive) && bigInteger.getPositive()){
               strForBrob = subHelper(bigInteger.getByteArr(),arrayOfBytes);
               finalPositive = true;
           }
           else{
               strForBrob = addHelper(arrayOfBytes,bigInteger.getByteArr());
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
   public BrobInt addInt( BrobInt bigInteger ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  subtracts two BrobInts
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtractByte( BrobInt bigInteger ) {
       String strForBrob = new String("");
       boolean finalPositive = true;

       if(this.compareTo(bigInteger) >= 0){ //this>arg
           if(isPositive && bigInteger.getPositive()){
               strForBrob = subHelper(arrayOfBytes,bigInteger.getByteArr());
               finalPositive = true;
           }
           else if(isPositive && !(bigInteger.getPositive())){
               strForBrob = addHelper(arrayOfBytes,bigInteger.getByteArr());
               finalPositive = true;
           }
           else if(!(isPositive) && bigInteger.getPositive()){
               strForBrob = addHelper(arrayOfBytes,bigInteger.getByteArr());
               finalPositive = false;
           }
           else{
               strForBrob = subHelper(arrayOfBytes,bigInteger.getByteArr());
               finalPositive = false;
           }
       }
       else if (this.compareTo(bigInteger) < 0){ //arg>this
           if(isPositive && bigInteger.getPositive()){
               strForBrob = subHelper(bigInteger.getByteArr(),arrayOfBytes);
               finalPositive = false;
           }
           else if(isPositive && !(bigInteger.getPositive())){
               strForBrob = addHelper(bigInteger.getByteArr(),arrayOfBytes);
               finalPositive = true;
           }
           else if(!(isPositive) && bigInteger.getPositive()){
               strForBrob = addHelper(bigInteger.getByteArr(),arrayOfBytes);
               finalPositive = false;
           }
           else{
               strForBrob = subHelper(bigInteger.getByteArr(),arrayOfBytes);
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
   public BrobInt subtractInt( BrobInt bigInteger ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
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
   public BrobInt multiply( BrobInt bigInteger ) {
       throw new UnsupportedOperationException();
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  divides two brobInts
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt divide( BrobInt bigInteger ) throws ArithmeticException {//this/arg
      BrobInt brobToReturn = new BrobInt("0");
      String temp;
      BrobInt holder = new BrobInt("0");
      BrobInt currOut = new BrobInt("0");

      if(bigInteger.compareTo(TWO) == 0){
          throw new ArithmeticException("Cannot divide by zero");
      }
      else if(this.compareTo(bigInteger) < 0 ){
          return ZERO;
      }

      for(int i =0; i < bigInteger.toString().substring(1).length(); i++){
          holder = this.multiply(currOut);
          temp = bigInteger.toString().substring(2*i+1,2*(i+1)+1);
          temp = holder.toString().substring(1).concat(temp);
          holder = new BrobInt(temp);
          currOut = holder.divide(bigInteger);
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
   public BrobInt remainder( BrobInt bigInteger ) {
       throw new UnsupportedOperationException();
       //return this.subtractByte(bigInteger.multiply(this.divide(bigInteger)));
   }


  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  compares two brobInts for equality
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean equals( BrobInt bigInteger ) {
      return (this.toString().equals( bigInteger.toString() ));
   }


  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  compares two BrobInts
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int compareTo( BrobInt bigInteger ) {
      if(this.toString().length() > bigInteger.toString().length()){
          return 1;
      }
      else if(this.toString().length() < bigInteger.toString().length()){
          return -1;
      }
      else if(this.toString().charAt(0)== '-' && bigInteger.toString().charAt(0) == '-'){
          return (this.toString().compareTo( bigInteger.toString() ));
      }
      else if(this.toString().charAt(0)== '-' && bigInteger.toString().charAt(0) == '+'){
          return -1;
      }
      else if(this.toString().charAt(0)== '+' && bigInteger.toString().charAt(0) == '-'){
          return 1;
      }
      else{
          return (this.toString().compareTo( bigInteger.toString() ));
      }

   }


  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  returns BrobInt version of long
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt valueOf( long value ) throws NumberFormatException {
      BrobInt BigInteger = null;
      try {
         BigInteger = new BrobInt( new Long( value ).toString() );
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n  Sorry, the value must be numeric of type long." );
      }
      return BigInteger;
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
}
