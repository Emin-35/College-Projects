import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;

public class SemaphoreCar4 {
	
	/*Variables for SemaphoreCar1 class
	 * Semaphore s4 will be the main Semaphore that every car takes
	 * ImageIcon yellowCar2 will be the specific car image for car4
	 * other integers are the speed and coordinates of car4
	 * boolean critic4 will be used in the critical point!
	 */
	
	Semaphore s4;
	ImageIcon yellowCar2;
	int carX4,carY4;
	int carVelocity4;
	boolean critic4;
	
	//Constructor of the Semaphore4 class
	public SemaphoreCar4(Semaphore s4,ImageIcon yellowCar2,int carX4, int carY4, int carVelocity4) {
		this.s4 = s4;
		
		this.yellowCar2 = yellowCar2;
		yellowCar2 = new ImageIcon("yellowL.png");
		
		this.carX4 = carX4;
		this.carY4 = carY4;
		carX4 = 780;
		carY4 = 340;
		this.carVelocity4 = carVelocity4;
		critic4 = true;
	}
	
	/*These are the getters and setters really important part of the project because these methods will help me to get whatever information I need
	 * such as I need car's coordinates to be able to understand where is the car and when it will enter the critical cross-road etc.
	 * getA() will return A and setA(Item x) will set the value of A as x 
	 */

	public Semaphore getS4() {
		return s4;
	}

	public int getCarX4() {
		return carX4;
	}

	public int getCarY4() {
		return carY4;
	}

	public int getCarVelocity4() {
		return carVelocity4;
	}

	public boolean isCritic4() {
		return critic4;
	}

	public void setS4(Semaphore s4) {
		this.s4 = s4;
	}

	public void setCarX4(int carX4) {
		this.carX4 = carX4;
	}

	public void setCarY4(int carY4) {
		this.carY4 = carY4;
	}

	public void setCarVelocity4(int carVelocity4) {
		this.carVelocity4 = carVelocity4;
	}

	public void setCritic4(boolean critic4) {
		this.critic4 = critic4;
	}
}