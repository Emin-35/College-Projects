import java.util.Random;

public class CriticCar3 implements Runnable{
	
	SemaphoreCar1 c1;
	SemaphoreCar2 c2;
	SemaphoreCar3 c3;
	SemaphoreCar4 c4;
	Random ran;
	
	//CriticalCar3 and rest of the CriticalCars must take SemaphoreCar classes to obtain their getters and setters classes
	public CriticCar3(SemaphoreCar1 c1,SemaphoreCar2 c2,SemaphoreCar3 c3,SemaphoreCar4 c4) {
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
		
		//If the 3rd car's x coordinate is between -40 < x1 < 250 just add the velocity to the x1 coordinate(This will make the car move in +x direction)
		if(c3.getCarX3() < 250 && -40 < c3.getCarX3())
			c3.carX3 += c3.getCarVelocity3();
		
		/*This is the CRITICAL POINT (needed to protect with semaphore to prevent car crashes)
		 *If x1 is between 250 <= x1 < 500 and the boolean of car3 is true (x=250 and x=500 is the cross-road start and end), open a Semaphore slot -> add the velocity to the x1 coordinate
		 *-> set the other car's boolean false so they can't enter the cross-road and when the x1 reaches 500 exit from the Semaphore(so other car's can enter)
		 */
		
		else if (250 <= c3.getCarX3() && c3.getCarX3() < 500 && c3.isCritic3()) {
			
			try {
				c3.getS3().acquire();
				c3.carX3 += c3.getCarVelocity3();
				c1.setCritic1(false);
				c2.setCritic2(false);
				c4.setCritic4(false);
				c3.getS3().release();
			}
			catch (InterruptedException e) {
				// TODO: handle exception
				System.out.println("Car3 is broken :/");
			}
		}
		//If car3 left the cross-road(Which is if 500 <= x < 780 we can now turn the other car's boolean to true so they can enter the cross-road)
		else if (500 <= c3.getCarX3() && c3.getCarX3() < 780) {
			c3.carX3 += c3.getCarVelocity3();
			c1.setCritic1(true);
			c2.setCritic2(true);
			c4.setCritic4(true);
		}
		//If x1 reaches 780 set the x1 value to -30(Starting point of the x1) and give it a random speed between 7-12
		else if (780 <= c3.getCarX3()) {
			c3.carX3 = -30;
			c3.setCarVelocity3(7+ran.nextInt(5));
		}
	}
}
