import java.util.Random;

public class CriticCar4 implements Runnable{
	
	SemaphoreCar1 c1;
	SemaphoreCar2 c2;
	SemaphoreCar3 c3;
	SemaphoreCar4 c4;
	Random ran;
	
	//CriticalCar4 and rest of the CriticalCars must take SemaphoreCar classes to obtain their getters and setters classes
	public CriticCar4(SemaphoreCar1 c1,SemaphoreCar2 c2,SemaphoreCar3 c3,SemaphoreCar4 c4) {
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
		
		//If the 4th car's x coordinate is between 500 < x2 < 800 just sub. the velocity to the x2 coordinate(This will make the car move in -x direction)
		if(500 < c4.getCarX4() && c4.getCarX4() < 800)
			c4.carX4 -= c4.getCarVelocity4();
		
		/*This is the CRITICAL POINT (needed to protect with semaphore to prevent car crashes)
		 *If x2 is between 300 < x2 <= 500 and the boolean of car4 is true (x=250 and x=500 is the cross-road start and end), open a Semaphore slot -> sub. the velocity to the x2 coordinate
		 *-> set the other car's boolean false so they can't enter the cross-road and when the x2 reaches 300 exit from the Semaphore(so other car's can enter)
		 */
		
		else if(300 < c4.getCarX4() && c4.getCarX4() <= 500 && c4.isCritic4()) {
			try {
				c4.getS4().acquire();
				c4.carX4 -= c4.getCarVelocity4();
				c1.setCritic1(false);
				c2.setCritic2(false);
				c3.setCritic3(false);
				c4.getS4().release();
			}
			catch (InterruptedException e) {
				// TODO: handle exception
				System.out.println("Car4 is broken :/");
			}
		}
		//If car4 left the cross-road(Which is if 20 < x <= 300 we can now turn the other car's boolean to true so they can enter the cross-road)
		else if(20 < c4.getCarX4() && c4.getCarX4() <= 300) {
			c4.carX4 -= c4.getCarVelocity4();
			c1.setCritic1(true);
			c2.setCritic2(true);
			c3.setCritic3(true);
		}
		//If x2 reaches 20 set the x2 value to 740(Starting point of the x2) and give it a random speed between 7-12
		else if (c4.getCarX4() <= 20) {
			c4.carX4 = 740;
			c4.setCarVelocity4(7+ran.nextInt(5));
		}
	}
}