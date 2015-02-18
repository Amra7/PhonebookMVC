package ba.bitcamp.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import ba.bitcamp.model.Application;
import ba.bitcamp.model.Contact;

public class ModelTest {

	

	/**
	 * Tests if method find(); works from Contact.
	 */
	private static void testFind() {
		Contact c = Contact.find(1);

		// TODO add equals method to Contact class and rewrite this part of the
		// test
		if (!c.getName().equals("Bob") || !c.getSurname().equals("Bobs")
				|| !c.getNumber().equals("123456")) {
			System.err.println("Contact not equal");
		}

		c = Contact.find(4);
		if (c != null) {
			System.err.println("Found non existing contatct");
		}
	}

	/**
	 * Tests if method all(); works from Contact.
	 */
	private static void testAll() {
		Contact[] all = Contact.all();

		if (all.length != 3) {
			System.out.println("Length does not match!");
			return;
		}
		if (!all[0].getName().equals("Bob")
				|| !all[0].getSurname().equals("Bobs")
				) {
			System.err.println("Contact not equal");
		}

		if (!all[1].getName().equals("Jeff")
				|| !all[1].getSurname().equals("Jefferson")
				) {
			System.err.println("Contact not equal");
		}

		if (!all[2].getName().equals("Jane")
				|| !all[2].getSurname().equals("Janice")
				) {
			System.err.println("Contact not equal");
		}
	}

	public static void main(String[] args) {

		try {
			// povezali smo se sa novom bazom
			Application.init("new_phonebook");

			// novi kontakti koje testiramo

		} catch (SQLException e) {
			System.err.println("Test failed: no connection");
		}

		new Contact(1, "Bob", "Bobs", "123456").save();
		new Contact(2, "Jeff", "Jefferson", "456789").save();
		new Contact(3, "Jane", "Janice", "789123").save();
		
		System.out.println("Testing find: ");
		testFind();
		System.out.println("Testing  All: ");
		testAll();
		
		String[] tableNames= {"contacts"};
	
		System.out.println("Done testing");
		
	}

}
