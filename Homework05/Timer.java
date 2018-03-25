public class Timer
{
  private double timeSlice = 1;
  private int hours;
  private int minutes;
  private double seconds;

  public Timer(double timeSlice)
  {
    hours = 0;
    minutes = 0;
    seconds = 0.00;
    timeSlice = timeSlice;
  }
  public Timer(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2)
  {
    if (((paramInt1 < 0 ? 1 : 0) | (paramInt2 < 0 ? 1 : 0) | (paramInt2 >= 60 ? 1 : 0) | (paramDouble1 < 0.0D ? 1 : 0) | (paramDouble1 >= 60.0D ? 1 : 0) | (paramDouble2 <= 0.0D ? 1 : 0) | (paramDouble2 > 1800.0D ? 1 : 0)) != 0)
    {
		throw new IllegalArgumentException();
    }

    hours = paramInt1;
    minutes = paramInt2;
    seconds = paramDouble1;
    timeSlice = paramDouble2;
  }

  public void tick()
  {
    seconds += timeSlice;

    while (seconds >= 60.00) {
      minutes += 1;
      seconds -= 60.00;
    }

    while (minutes >= 60.00) {
      hours += 1;
      minutes -= 60;
    }
  }


  public String toString()
  {
    return hours + ":" + minutes + ":" + seconds;
  }


  public int getHours()
  {
    return hours;
  }



  public int getMinutes()
  {
    return minutes;
  }


  public double getSeconds()
  {
    return seconds;
  }
}
