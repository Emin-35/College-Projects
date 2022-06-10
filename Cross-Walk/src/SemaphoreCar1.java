import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;

public class SemaphoreCar1 {
	
	/*Variables for SemaphoreCar1 class
	 * Semaphore s1 will be the main Semaphore that every car takes
	 * ImageIcon redCar1 will be the specific car image for car1
	 * other integers are the speed and coordinates of car1
	 * boolean critic1 will be used in the critical point!
	 */
	Semaphore s1;
	ImageIcon redCar1;
	int carX1,carY1;
	int carVelocity1;
	boolean critic1;
	
	//Constructor of the SemaphoreCar1 class
	public SemaphoreCar1(Semaphore s1,ImageIcon redCar1,int carX1, int carY1, int carVelocity1) {
		
		this.s1 = s1;
		
		this.redCar1 = redCar1;
		redCar1 = new ImageIcon("redU.png");
		
		this.carX1 = carX1;
		this.carY1 = carY1;
		carX1 = 440;
		carY1 = 770;
		this.carVelocity1 = carVelocity1;
		critic1 = true;
	}
	
	/*These are the getters and setters really important part of the project because these methods will help me to get whatever information I need
	 * such as I need car's coordinates to be able to understand where is the car and when it will enter the critical cross-road etc.
	 * getA() will return A and setA(Item x) will set the value of A as x 
	 */
	
	public Semaphore getS1() {
		return s1;
	}

	public ImageIcon getRedCar1() {
		return redCar1;
	}

	public void setS1(Semaphore s1) {
		this.s1 = s1;
	}

	public void setRedCar1(ImageIcon redCar1) {
		this.redCar1 = redCar1;
	}

	public int getCarVelocity1() {
		return carVelocity1;
	}

	public void setCarVelocity1(int carVelocity1) {
		this.carVelocity1 = carVelocity1;
	}
	
	public boolean isCritic1() {
		return critic1;
	}

	public void setCritic1(boolean critic1) {
		this.critic1 = critic1;
	}

	public int getCarX1() {
		return carX1;
	}

	public int getCarY1() {
		return carY1;
	}
}