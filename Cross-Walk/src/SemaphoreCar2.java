import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;

public class SemaphoreCar2 {
	
	/*Variables for SemaphoreCar1 class
	 * Semaphore s2 will be the main Semaphore that every car takes
	 * ImageIcon yellowCar1 will be the specific car image for car2
	 * other integers are the speed and coordinates of car2
	 * boolean critic2 will be used in the critical point!
	 */
	
	Semaphore s2;
	ImageIcon yellowCar1;
	int carX2,carY2;
	int carVelocity2;
	boolean critic2;
	
	//Constructor of the Semaphore2 class
	public SemaphoreCar2(Semaphore s2,ImageIcon yellowCar1,int carX2, int carY2, int carVelocity2) {
		this.s2 = s2;
		
		this.yellowCar1 = yellowCar1;
		yellowCar1 = new ImageIcon("yellowD.png");
		
		this.carX2 = carX2;
		this.carY2 = carY2;
		carX2 = 320;
		carY2 = -20;
		this.carVelocity2 = carVelocity2;
		critic2 = true;
	}
	
	/*These are the getters and setters really important part of the project because these methods will help me to get whatever information I need
	 * such as I need car's coordinates to be able to understand where is the car and when it will enter the critical cross-road etc.
	 * getA() will return A and setA(Item x) will set the value of A as x 
	 */
	
	public boolean isCritic2() {
		return critic2;
	}

	public Semaphore getS2() {
		return s2;
	}

	public ImageIcon getYellowCar1() {
		return yellowCar1;
	}

	public void setS2(Semaphore s2) {
		this.s2 = s2;
	}

	public void setYellowCar1(ImageIcon yellowCar1) {
		this.yellowCar1 = yellowCar1;
	}

	public int getCarVelocity2() {
		return carVelocity2;
	}

	public void setCarVelocity2(int carVelocity2) {
		this.carVelocity2 = carVelocity2;
	}

	public void setCritic2(boolean critic2) {
		this.critic2 = critic2;
	}

	public int getCarX2() {
		return carX2;
	}

	public int getCarY2() {
		return carY2;
	}
}