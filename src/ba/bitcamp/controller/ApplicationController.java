package ba.bitcamp.controller;

import java.sql.SQLException;

import ba.bitcamp.model.*;
import ba.bitcamp.view.*;

public class ApplicationController {

	public static void home(){
		// prikaz home GUI-a
		Contact c =  Contact.find(1);
		ApplicationView.home(c);
		
	}
	
	public static void main(String[] args) {
		
		/* Povezivanje sa  bazom */
		try {
			Application.init();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		
		Main.init();	
		home();
	}
}
