package ba.bitcamp.controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import ba.bitcamp.model.*;
import ba.bitcamp.view.*;

public class ApplicationController {

	public static void home(){
		// prikaz home GUI-a
		Contact c =  Contact.find(1);
		ApplicationView.home(c);
		
	}
	
	/**
	 * Calls method to show add panel
	 */
	public static void addContact(){
		ApplicationView.addContact();
	}
	
	public static void create (String name, String surname, String number){
		Contact newContact = new Contact(name, surname, number);
		if (newContact.save() == true){
			JOptionPane.showMessageDialog(null, "Successfuly saved " + newContact.getName(), "Contact added", JOptionPane.INFORMATION_MESSAGE);
			// TODO redirect to  Contact info
			home();
		}else{
			JOptionPane.showMessageDialog(null, "There has been mistake", "Error saving contact", JOptionPane.WARNING_MESSAGE);
		}
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
