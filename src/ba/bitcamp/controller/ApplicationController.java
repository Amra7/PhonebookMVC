package ba.bitcamp.controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import ba.bitcamp.model.*;
import ba.bitcamp.view.*;

public class ApplicationController {

	/**
	 * Home view for our application of phonebook. Also changes our view panel
	 * with other panels depending of what we clicked.
	 */
	public static void home() {

		ApplicationView.home();

	}

	/**
	 * Calls method to show add panel where we can insert data of contact we
	 * want to save.
	 */
	public static void addContact() {
		ApplicationView.addContact();
	}

	/**
	 * Creates new contact which data is entered in application GUI field.
	 * 
	 * @param name
	 *            - name of contact.
	 * @param surname
	 *            - surname of contact.
	 * @param number
	 *            - number of telephone of new contact.
	 */
	public static void create(String name, String surname, String number) {
		Contact newContact = new Contact(name, surname, number);
		if (newContact.save() == true) {
			/*
			 * In case we manage to save new data of contact, send us message:
			 * "Successfully saved "
			 */
			
			// JOptionPane.showMessageDialog(null, "Successfully saved " +
			// newContact.getName(), "Contact added",
			// JOptionPane.INFORMATION_MESSAGE);
			
			// TODO redirect to Contact info
			home();
			show(newContact.getId());
		} else {
			/*
			 * In case we did not manage to save new data of contact, send us
			 * error message
			 */
			JOptionPane.showMessageDialog(null, "There has been mistake",
					"Error saving contact", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Gets all the contacts from the database. Uses ApplicationView.list()
	 * method to show us all contacts.
	 */
	public static void list() {
		Contact[] all = Contact.all();
		ApplicationView.list(all);
	}

	public static void show(int id) {
		Contact current = Contact.find(id);
		ApplicationView.showOneContact(current);
	}
	
	// Metoda koja nas prebacuje na ApplicationView
	public static void update(int id) {
		Contact current =  Contact.find(id);
		ApplicationView.updateContact(current);
	}
	
	// metoda kojapricma citav objekat i sacuva ga
    public static void update(Contact current) {
    	current.update();
    	ApplicationView.showOneContact(current);
		
	} 
    
    public static void delete(int id){
    	Contact.delete(id);
    	list();
    	
    }

	/**
	 * Main method of ApllicationController class.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		/* Povezivanje sa bazom */
		/* Connects with base */
		try {
			Application.init("phonebook");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		/*
		 * Initialize main window of application Main.init(); /* calls method
		 * home(), which calls other method home from ApplicationView class
		 */
		Main.init();
		home();

	}
	// end of main
}
// end of class ApplicationCOntroller
