package ba.bitcamp.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ba.bitcamp.controller.ApplicationController;
import ba.bitcamp.model.Contact;


public class ApplicationView extends Main {

	
	public static void home(Contact c){
		
		JPanel content = new JPanel();
		content.setSize(400, 500);
		JLabel greeting =  new JLabel("Welcome to BitBook");
		Font greeetingfont = new Font("SansSerif", Font.BOLD, 30);
		content.add(greeting);
		greeting.setFont(greeetingfont);
		
		
		JButton showContacts=  new JButton("Show Contacts");
		showContacts.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.addContact();
				
			}
			
		});
		
		JButton addContacts =  new JButton("Add Contacts");
		addContacts.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ApplicationController.addContact();
				
			}
			
		});
		
		content.add(showContacts);	
		content.add(addContacts);
		
		//Treba izbrisati
	/*	JLabel contactInfo = new JLabel(
				c.getName() + " " + c.getSurname() + " " + c.getNumber()); 
		
		content.add(contactInfo); */
		Main.replaceContent(content);
		Main.setVisible();
	}
	
	public static void addContact(){
		JPanel content =  new JPanel();
		JLabel greeting = new JLabel("        Add Contact        ");
		Font greetingFont = new Font("SansSerif", Font.BOLD ,30);
		greeting.setFont(greetingFont);
		content.add(greeting);
		
		JLabel name =  new JLabel("Name   ");
		content.add(name);
		final JTextField nameField = new JTextField();		
		nameField.setPreferredSize(new Dimension(350,40));
		nameField.setVisible(true);
		content.add(nameField);
		
		JLabel surname =  new JLabel("Surname");
		content.add(surname);
		final JTextField surnameField = new JTextField();
		surnameField.setPreferredSize(new Dimension(350,40));
		surnameField.setVisible(true);
		content.add(surnameField);
		
		JLabel number =  new JLabel("Number");
		content.add(number);
		final JTextField numberField = new JTextField();
		numberField.setPreferredSize(new Dimension(350, 40));
		numberField.setVisible(true);
		content.add(numberField);
		
		JButton submit =  new JButton("Submit");
		submit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * get the data from the input
				 * ant send it to creat method
				 */
				String cName=  nameField.getText();
				String cSurname = surnameField.getText();
				String cNumber = numberField.getText();
				
				ApplicationController.create(cName,cSurname, cNumber);
				
			}
			
		});
		JButton back =  new JButton("Back");
		back.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.home();
				
			}
			
		});
		
		content.add(submit);
		content.add(back);
		Main.replaceContent(content);
		Main.setVisible();
	}
	

}
