import java.util.Random;

public class CriticCar2 implements Runnable{
	
	SemaphoreCar1 c1;
	SemaphoreCar2 c2;
	SemaphoreCar3 c3;
	SemaphoreCar4 c4;
	Random ran;
	
	//CriticalCar2 and rest of the CriticalCars must take SemaphoreCar classes to obtain their getters and setters classes
	public CriticCar2(SemaphoreCar1 c1,SemaphoreCar2 c2,SemaphoreCar3 c3,SemaphoreCar4 c4) {
		this.c1=c1;
		this.c2=c2;
		this.c3=c3;
		this.c4=c4;
		ran = new Random();
	}
	
	//As you can see I'm using getters and setters methods here
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		//If the 2nd car's y coordinate is between -30 < y1 < 300 just add the velocity to the y2 coordinate(This will make the car move in +y direction)
		if(c2.getCarY2() < 250 && -30 < c2.getCarY2())
			c2.carY2 += c2.getCarVelocity2();
		
		/*This is the CRITICAL POINT (needed to protect with semaphore to prevent car crashes)
		 *If y2 is between 250 <= y1 < 500 and the boolean of car2 is true (y=250 and y=500 is the cross-road start and end), open a Semaphore slot -> add the velocity to the y2 coordinate
		 *-> set the other car's boolean false so they can't enter the cross-road and when the y2 reaches 500 exit from the Semaphore(so other car's can enter)
		 */
		
		else if (250 <= c2.getCarY2() && c2.getCarY2() < 500 && c2.isCritic2()) {
			try {
				c2.getS2().acquire();
				c2.carY2 += c2.getCarVelocity2();
				c1.setCritic1(false);
				c3.setCritic3(false);
				c4.setCritic4(false);
				c2.getS2().release();
			}
			catch (InterruptedException e) {
				// TODO: handle exception
				System.out.println("Car2 is broken :/");
			}
		}
		//If car2 left the cross-road(Which is if 500 <= y < 780 we can now turn the other car's boolean to true so they can enter the cross-road)
		else if (500 <= c2.getCarY2() && c2.getCarY2() < 780) {
			c2.carY2 += c2.getCarVelocity2();
			c1.setCritic1(true);
			c3.setCritic3(true);
			c4.setCritic4(true);
		}
		//If y2 reaches 780 set the y2 value to -20(Starting point of the y2) and give it a random speed between 7-12
		else if (780 <= c2.getCarY2()) {
			c2.carY2 = -20;
			c2.setCarVelocity2(7+ran.nextInt(5));
		}
	}
}
