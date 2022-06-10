
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.Random;
import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class CrossRoad extends JPanel implements Runnable{
	
	//These are the variables that I needed to create this project
//------------------------------------------------------------------------------------------------------------------------------------------------
	
	//Car's speed variables
	int carVelocity1,carVelocity2,
	carVelocity3,carVelocity4;
	
//------------------------------------------------------------------------------------------------------------------------------------------------
	
	//Car's coordinate variables
	int carX1,carY1,carX2,carY2,
	carX3,carY3,carX4,carY4;
	
//------------------------------------------------------------------------------------------------------------------------------------------------
	
	/*SemaphoreCar is a normal car class that includes that car's variable
	 * such as SemaphoreCar1 only includes first car's variable(It's coordinate,shape,speed etc.)
	 * 
	 * CriticCar is a implemented Runnable class that has the critical point for this animation and includes the individual car's variable
	 * such as CriticCar1 only has run method for first car(It's tells when to acquire semaphore and when to release)
	 */
	
	SemaphoreCar1 car1;
	SemaphoreCar2 car2;
	SemaphoreCar3 car3;
	SemaphoreCar4 car4;
	
	CriticCar1 c1;
	CriticCar2 c2;
	CriticCar3 c3;
	CriticCar4 c4;
	
//------------------------------------------------------------------------------------------------------------------------------------------------
	
	//These are the images for each car
	ImageIcon redCar1,yellowCar1,
	redCar2,yellowCar2;
//------------------------------------------------------------------------------------------------------------------------------------------------
	
	/*Semaphore crash is the main Semaphore that every car will use(That's how we can obtain multiple-thread with Semaphore acquire and release
	 * and Thread t1,t2,t3,t4 is needed because CriticCar classes implements Runnable
	 * (If we want to create multiple-thread animation we need to add CriticalCars inside the thread)
	 */
	
	Semaphore crash;
	
	Thread t1,t2,t3,t4;
	
//------------------------------------------------------------------------------------------------------------------------------------------------
	
	//I want to use random integer for car's speed
	Random ran;
	
//------------------------------------------------------------------------------------------------------------------------------------------------
	
	//Constructor of the CrossRoad class
	public CrossRoad() {
		
		setLayout(new FlowLayout());
		
		//We need Semaphore(1) because only ONE car can use the CrossRoad at a time
		crash = new Semaphore(1);
		
		//Adding the images
		
		//Cars that goes Up-Down
		redCar1 = new ImageIcon("redU.png");
		yellowCar1 = new ImageIcon("yellowD.png");
		
		//Cars that goes Right-Left
		redCar2 = new ImageIcon("redR.png");
		yellowCar2 = new ImageIcon("yellowL.png");
		
		//Adding the coordinates of the cars
		carX1 = 440;
		carY1 = 770;
		
		carX2 = 320;
		carY2 = -20;
		
		carX3 = -30;
		carY3 = 430;
		
		carX4 = 780;
		carY4 = 340;
		
		ran = new Random();
		
//------------------------------------------------------------------------------------------------------------------------------------------------
		
		//10 for carVelocitys' but every time when a car restart it will have different speed randomly;
		carVelocity1 = 10;
		carVelocity2 = 10;
		carVelocity3 = 10;
		carVelocity4 = 10;
		
		//Creating the car objects
		car1 = new SemaphoreCar1(crash,redCar1,carX1,carY1,carVelocity1);
		car2 = new SemaphoreCar2(crash,yellowCar1,carX2,carY2,carVelocity2);
		car3 = new SemaphoreCar3(crash,redCar2,carX3,carY3,carVelocity3);
		car4 = new SemaphoreCar4(crash,yellowCar2,carX4,carY4,carVelocity4);
		
		//Using car objects to fill the Runnable method(This is how we can reach the cars variables in a implemented runnable class)
		c1 = new CriticCar1(car1,car2,car3,car4);
		c2 = new CriticCar2(car1,car2,car3,car4);
		c3 = new CriticCar3(car1,car2,car3,car4);
		c4 = new CriticCar4(car1,car2,car3,car4);
		
		//Adding the car object to thread because they all implements Runnable
		t1 = new Thread(c1);
		t2 = new Thread(c2);
		t3 = new Thread(c3);
		t4 = new Thread(c4);
		
//------------------------------------------------------------------------------------------------------------------------------------------------
		
		//Setting the size of the JPanel(800x800)
		setSize(800,800);
	}
	
	//paint method that will put images in the JPanel
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		/*From here I was just customizing the JPanel to look good
		 * Filling x=0,y=0 with a 300x300 green rectangle
		 * Filling x=500,y=0 with a 300x300 green rectangle
		 * Filling x=0,y=500 with a 300x300 green rectangle
		 * Filling x=500,y=500 with a 300x300 green rectangle
		 * 
		 * And adding a black rectangles to given coordinates to achieve road-like image
		 */
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, 300, 300);
		
		g.setColor(Color.GREEN);
		g.fillRect(500, 0, 300, 300);
		
		g.setColor(Color.GREEN);
		g.fillRect(0, 500, 300, 300);
		
		g.setColor(Color.GREEN);
		g.fillRect(500, 500, 300, 300);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 400, 300, 10);
		
		g.setColor(Color.BLACK);
		g.fillRect(500, 400, 300, 10);
		
		g.setColor(Color.BLACK);
		g.fillRect(400, 0, 10, 300);
		
		g.setColor(Color.BLACK);
		g.fillRect(400, 500, 10, 300);
		
		//Adding the car's and giving their coordinates/variables
		car1.redCar1.paintIcon(this, g, car1.carX1, car1.carY1);
		car2.yellowCar1.paintIcon(this, g, car2.carX2, car2.carY2);
		redCar2.paintIcon(this, g, car3.carX3, car3.carY3);
		yellowCar2.paintIcon(this, g, car4.carX4, car4.carY4);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//While(true) makes this animation to work forever
		while(true) {
			try {
				Thread.currentThread().sleep(30);
				
				//Moving the cars(These are not the critical parts of the animation) just calling the car object's run method
				t1.run();
				t2.run();
				t3.run();
				t4.run();
				repaint();
			}
			catch (InterruptedException e) {
				// TODO: handle exception
				System.out.println("Main Run method Error");
				return;
			}
		}
	}
}