package ba.bitcamp.view;

import java.awt.Container;

import javax.swing.JFrame;

public class Main {

	private static JFrame window =  null;
	protected static int windowWidth = 400;
	protected static int windowHeight = 500;
	
	public static void  init(){
		window = new JFrame("BitBook");
		window.setSize(windowWidth, windowHeight);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	protected static void setVisible(){
		window.validate();
		window.repaint();
		window.setVisible(true);
	}
	
	
	protected static void replaceContent(Container  panel){
		window.setContentPane(panel);
		setVisible();
//		window.add(panel);
		
	}
}
