import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;

public class SemaphoreCar3 {
	
	/*Variables for SemaphoreCar1 class
	 * Semaphore s3 will be the main Semaphore that every car takes
	 * ImageIcon redCar2 will be the specific car image for car3
	 * other integers are the speed and coordinates of car3
	 * boolean critic3 will be used in the critical point!
	 */
	
	Semaphore s3;
	ImageIcon redCar2;
	int carX3,carY3;
	int carVelocity3;
	boolean critic3;
	
	//Constructor of the Semaphore3 class
	public SemaphoreCar3(Semaphore s3,ImageIcon redCar2,int carX3, int carY3, int carVelocity3) {
		this.s3 = s3;
		
		this.redCar2 = redCar2;
		redCar2 = new ImageIcon("redR.png");
		
		this.carX3 = carX3;
		this.carY3 = carY3;
		carX3 = -30;
		carY3 = 430;
		this.carVelocity3 = carVelocity3;
		critic3 = true;
	}
	
	/*These are the getters and setters really important part of the project because these methods will help me to get whatever information I need
	 * such as I need car's coordinates to be able to understand where is the car and when it will enter the critical cross-road etc.
	 * getA() will return A and setA(Item x) will set the value of A as x 
	 */

	public Semaphore getS3() {
		return s3;
	}

	public ImageIcon getRedCar2() {
		return redCar2;
	}

	public int getCarX3() {
		return carX3;
	}

	public int getCarY3() {
		return carY3;
	}

	public int getCarVelocity3() {
		return carVelocity3;
	}

	public boolean isCritic3() {
		return critic3;
	}

	public void setS3(Semaphore s3) {
		this.s3 = s3;
	}

	public void setRedCar2(ImageIcon redCar2) {
		this.redCar2 = redCar2;
	}

	public void setCarX3(int carX3) {
		this.carX3 = carX3;
	}

	public void setCarY3(int carY3) {
		this.carY3 = carY3;
	}

	public void setCarVelocity3(int carVelocity3) {
		this.carVelocity3 = carVelocity3;
	}

	public void setCritic3(boolean critic3) {
		this.critic3 = critic3;
	}
}