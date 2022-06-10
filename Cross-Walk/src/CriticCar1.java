import java.util.Random;

public class CriticCar1 implements Runnable{
	
	SemaphoreCar1 c1;
	SemaphoreCar2 c2;
	SemaphoreCar3 c3;
	SemaphoreCar4 c4;
	Random ran;
	
	//CriticalCar1 and rest of the CriticalCars must take SemaphoreCar classes to obtain their getters and setters classes
	public CriticCar1(SemaphoreCar1 c1,SemaphoreCar2 c2,SemaphoreCar3 c3,SemaphoreCar4 c4) {
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
		
		//If the first car's y coordinate is between 500 < y1 < 800 just sub. the velocity to the y1 coordinate(This will make the car move in -y direction)
		if(500 < c1.getCarY1() && c1.getCarY1() < 800)
			c1.carY1 -= c1.getCarVelocity1();
		
		/*This is the CRITICAL POINT (needed to protect with semaphore to prevent car crashes)
		 *If y1 is between 300 < y1 <= 500 and the boolean of car1 is true (y=300 and y=500 is the cross-road start and end), open a Semaphore slot -> sub. the velocity to the y1 coordinate
		 *-> set the other car's boolean false so they can't enter the cross-road and when the y1 reaches 300 exit from the Semaphore(so other car's can enter)
		 */
		else if((c1.getCarY1() <= 500) && (300 < c1.getCarY1()) && c1.isCritic1()) {
			
			try {
				c1.getS1().acquire();
				c1.carY1 -= c1.getCarVelocity1();
				c2.setCritic2(false);
				c3.setCritic3(false);
				c4.setCritic4(false);
				c1.getS1().release();
			}
			catch (InterruptedException e) {
				// TODO: handle exception
				System.out.println("Car1 is broken :/");
			}
		}
		//If car1 left the cross-road(Which is if y<=300 we can now turn the other car's boolean to true so they can enter the cross-road)
		else if (c1.getCarY1() <= 300 && 15 < c1.getCarY1() ) {
			c1.carY1 -= c1.getCarVelocity1();
			c2.setCritic2(true);
			c3.setCritic3(true);
			c4.setCritic4(true);
		}
		//If y1 reaches 15 set the y1 value to 770(Starting point of the y1) and give it a random speed between 7-12
		else if (c1.getCarY1() <= 15) {
			c1.carY1 = 770;
			c1.setCarVelocity1(7+ran.nextInt(5));
		}
	}
}
