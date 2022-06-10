import javax.swing.JFrame;

public class Test {
	public static void main(String[] args) {
		
		/*Settings of myFrame
		*setSize will be setting the size to x=800 y=800
		*setVisible will make the frame visible
		*setLocationRelativeTo will make the frame appear middle of the screen
		*setDefaultCloseOperation will close the frame when you close it
		*CrossRoad implements Runnable that's why it needs a Thread
		*/
		
		CrossRoad crossRoad = new CrossRoad();
		JFrame myFrame = new JFrame("Midterm");
		
		Thread animation = new Thread(crossRoad);
		animation.start();
		
		myFrame.setSize(crossRoad.getSize());
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setLocationRelativeTo(null);
		myFrame.add(crossRoad);
		myFrame.setVisible(true);
	}
}