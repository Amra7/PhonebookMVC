package ba.bitcamp.view;

import java.awt.Container;

import javax.swing.JFrame;

public class Main {

	private static JFrame window =  null;
	protected static int windowWidth = 400;
	protected static int windowHeight = 500;
	
	/**
	 * This method Creates the Main Window
	 * and starts the application.
	 */

	public static void  init(){
		window = new JFrame("BitBook");
		window.setLocation(700, 500);
		window.setSize(windowWidth, windowHeight);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	/**
	 * Sets the main window of application visible to true.
	 * validate() -checks that everything window that we start earlier.
	 * Also repaint new panel on window if needed
	 * and set panel visible.
	 */
	protected static void setVisible(){
		window.validate();
		window.repaint();
		window.setVisible(true);
	}
	
	
	/**
	 * Replaces old panel with new panel if it is needed 
	 * (if user clicks on one of the button which leads us on new panel).
	 * Set new panel visible.
	 */
	protected static void replaceContent(Container  panel){
		window.setContentPane(panel);
		setVisible();
//		window.add(panel);
		
	}
}
