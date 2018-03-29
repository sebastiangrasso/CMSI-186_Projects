import java.io.PrintStream;

public class SoccerSim {

  private static double [] poleLocation = new double [2];
  private static double [] feildDimensions = new double [2];
  private static double poleRadius = .370833;

  public SoccerSim() {
	poleLocation[0] = 0.00;
	poleLocation[1] = 0.00;
	feildDimensions[0] = 1000;
	feildDimensions[1] = 1000;

  }

  public static void main(String[] paramArrayOfString) {
	SoccerBall[] arrayOfBall = new SoccerBall[paramArrayOfString.length / 4];
    double d = processArgs(paramArrayOfString, arrayOfBall);
    Timer localTimer = new Timer(0, 0, 0.00, d);

    printReport("INITIAL REPORT at " + localTimer.toString(), arrayOfBall);

    while ((theresNoContact(arrayOfBall)) && (someBallIsMoving(arrayOfBall))) {
      localTimer.tick();
      for (int i = 0; i < arrayOfBall.length; i++) arrayOfBall[i].advance(d);
      printReport("PROGRESS REPORT at " + localTimer.toString(), arrayOfBall);
    }

    if (thereIsContact(arrayOfBall)) {
      System.out.println("CONTACT " + wheresTheContact(arrayOfBall));
    }
    else {
      System.out.println("NO COLLISION IS POSSIBLE");
    }
  }

  static boolean thereIsContact(SoccerBall[] paramArrayOfBall)
  {
    for (int i = 0; i < paramArrayOfBall.length - 1; i++) {
      for (int j = i + 1; j < paramArrayOfBall.length; j++) {
        double d1 = (paramArrayOfBall[i].getX() - paramArrayOfBall[j].getX()) * 12.00;
        double d2 = (paramArrayOfBall[i].getY() - paramArrayOfBall[j].getY()) * 12.00;
        if (d1 * d1 + d2 * d2 < 79.21000000000001D) {
          return true;
        }
      }

    double d = Math.sqrt(Math.pow(paramArrayOfBall[i].getX() - poleLocation[0], 2.00) + Math.pow(paramArrayOfBall[i].getY() - poleLocation[1], 2.00));
    if (d * 12.00 <= 8.0) {
		return true;
	}
    }
    return false;
  }

  static boolean theresNoContact(SoccerBall[] paramArrayOfBall) {
    return !thereIsContact(paramArrayOfBall);
  }

  static String wheresTheContact(SoccerBall[] paramArrayOfBall) {
    String str = "";
    for (int i = 0; i < paramArrayOfBall.length - 1; i++) {
      for (int j = i + 1; j < paramArrayOfBall.length; j++) {
        double d1 = (paramArrayOfBall[i].getX() - paramArrayOfBall[j].getX()) * 12.0D;
        double d2 = (paramArrayOfBall[i].getY() - paramArrayOfBall[j].getY()) * 12.0D;
        if (d1 * d1 + d2 * d2 < 79.21000000000001D) {
          str = str + "ball " + i + " & " + j + "; ";
        }
      }
    double d = Math.sqrt(Math.pow(paramArrayOfBall[i].getX() - poleLocation[0], 2.00) + Math.pow(paramArrayOfBall[i].getY() - poleLocation[1], 2.00));
    if (d * 12.00 <= 8.0) {
	   str = str + " ball " + i + " & pole; ";
	}

    }
    return str;
  }

  static boolean someBallIsMoving(SoccerBall[] paramArrayOfBall) {
    for (int i = 0; i < paramArrayOfBall.length; i++) {
      if (paramArrayOfBall[i].checkOnFeildAndMoving()) return true;
    }
    return false;
  }




  static double processArgs(String[] paramArrayOfString, SoccerBall[] paramArrayOfBall)
  {
    if ((paramArrayOfString.length < 4) || (paramArrayOfString.length % 4 > 1)) { throw new IllegalArgumentException();
    }
    for (int i = 0; i < paramArrayOfString.length / 4; i++) {
      try {
        double d2 = Double.parseDouble(paramArrayOfString[(4 * i)]);
        double d3 = Double.parseDouble(paramArrayOfString[(4 * i + 1)]);
        double d4 = Double.parseDouble(paramArrayOfString[(4 * i + 2)]);
        double d5 = Double.parseDouble(paramArrayOfString[(4 * i + 3)]);

        paramArrayOfBall[i] = new SoccerBall(d2, d3, d4, d5);
      }
      catch (Exception localException1) {
        throw new IllegalArgumentException();
      }
    }



    double d1 = 1.0D;



    if (paramArrayOfString.length % 4 == 1)
      { d1 = Double.parseDouble(paramArrayOfString[(paramArrayOfString.length - 1)]);
//      } catch (Exception localException2) { throw new IllegalArgumentException();
      }
    if (d1 <= 0.0D)
    	{throw new IllegalArgumentException();
    }
    return d1;
  }

  static void printReport(String paramString, SoccerBall[] paramArrayOfBall) {
    System.out.println(paramString);
    for (int i = 0; i < paramArrayOfBall.length; i++) {
      System.out.println(i + ":\t" + paramArrayOfBall[i].toString());
    }
    System.out.println();
  }
}

