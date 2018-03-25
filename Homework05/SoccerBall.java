public class SoccerBall{

	private double [] position = new double[2];
	private double [] speed = new double[2];
	private final double radius = 0.370833;
	private final double diameter = 2*0.370833;
	private boolean onFeildAndMoving;

	public SoccerBall(double xPosition, double yPosition, double xSpeed, double ySpeed){

	position[0] = xPosition;
	position[1] = yPosition;
	speed[0] = xSpeed;
	speed[1] = ySpeed;

	if (-400 <= xPosition && xPosition <= 400 && -400 <= yPosition && yPosition <= 400)
		onFeildAndMoving = true;
	else
		onFeildAndMoving = false;
	}

  	public void advance(double timeSlice)
  	{
  	  if (onFeildAndMoving)
  	  {
  	    while (timeSlice >= 1.00) {
  	      updatePosition();
  	      updateSpeed();
  	      timeSlice -= 1.00;
  	    }
  	    if (timeSlice > 0.0D) {
  	      position[0] += speed[0] * timeSlice;
  	      position[1] += speed[1] * timeSlice;
  	      speed[0] -= 0.010 * speed[0] * timeSlice;
  	      speed[1] -= 0.010 * speed[1] * timeSlice;
  	    }
	    onFeildAndMoving = checkOnFeildAndMoving();
	    }
  	}

	public double [] updatePosition(){
		position[0] += speed[0];
		position[1] += speed[1];

		return position;
	}

	public double [] updateSpeed(){
		speed[0] = speed[0] - (speed[0]*.01);
		speed[1] = speed[1] - (speed[1]*.01);

		return position;
	}

	public double getX(){
		return position[0];
	}

	public double getY(){
		return position[1];
	}

	public double getXSpeed(){
		return speed[0];
	}

	public double getYSpeed(){
		return speed[1];
	}

	public boolean checkOnFeildAndMoving(){

		onFeildAndMoving = false;

		if (-400 <= position[0] && position[0] <= 400)
			if (-400 <= position[1] && position[1] <= 400)
				if (speed[0] > 0.08333 && speed[1] > 0.08333)
					onFeildAndMoving = true;
		return onFeildAndMoving;
	}

  	public String toString()
  	{
  	  if (onFeildAndMoving == false)
  	  	return "position <" + format(position[0]) + "," + format(position[1]) + ">\t at rest";
  	  return "position <" + format(position[0]) + "," + format(position[1]) + ">\t velocity <" + format(speed[0]) + "," + format(speed[1]) + ">";
  	}

  	static String format(double paramDouble)
  	{
  	  String str = String.valueOf(paramDouble);
  	  int i = str.indexOf('.');
  	  if (i < str.length() - 4) return str.substring(0, i + 5);
  	  return str;
  	}

}
