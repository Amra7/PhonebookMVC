package ba.bitcamp.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ba.bitcamp.controller.ApplicationController;
import ba.bitcamp.model.Contact;


public class ApplicationView extends Main {

	/**
	 * Creates main Application Window.
	 * This window open when you open application.
	 * @param c - Contact from pnonebook.db
	 */
	public static void home(){
		
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
				ApplicationController.list();
				
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
	
	/**
	 * AddContact method is called when click on Add Contacts button in method home():
	 * ApplicationController.addContact(); .
	 * Opens new window for adding contact.
	 */
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
				 * ant send it to create method
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
	
	/**
	 * Creates button for each contact in list
	 * sets the name and the label for button
	 * connect an action listener
	 * and adds the button to content panel
	 */
	public static  void list(Contact[] all){
		
		int buttonHeight = 50;
		
		JPanel  content =  new JPanel();
		content.setPreferredSize(new Dimension( ApplicationView.windowWidth-70, all.length * (buttonHeight + 20)));
		
		JButton back= new JButton("Back");
		back.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		JButton add = new JButton("Add new Contact");
		add.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		content.add(add);
		content.add(back);
		
		if( all.length < 1){
		JLabel greeting =  new JLabel("Welcome to BitBook");
		Font greeetingfont = new Font("SansSerif", Font.BOLD, 30);
		content.add(greeting);
		greeting.setFont(greeetingfont);
		}
		
		//TODO add ADD Button Contact
		for ( int i=0; i<all.length; i++){
			JButton current = new JButton(all[i].getDisplayName());
			current.setName(Integer.toString(all[i].getId()));
			current.setPreferredSize(new Dimension(ApplicationView.windowWidth -75, buttonHeight));
			current.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO redirect to Contact
					JButton clicked =(JButton)(e.getSource());
					int id = Integer.parseInt(clicked.getName());
					System.out.println("Korisnik: "+ id);
					
					ApplicationController.show(id);
				}
				
			});
			content.add(current);
		}
		JScrollPane sp = new JScrollPane(content);
//		sp.setPreferredSize(new Dimension( ApplicationView.windowWidth-10, ApplicationView.windowHeight));
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		Main.replaceContent(sp);
		
		
	}
	
	/**
	 * 
	 */
	public static void showOneContact(final Contact current){
		
		JPanel  content =  new JPanel();
		content.setPreferredSize(new Dimension( ApplicationView.windowWidth-70, 100));
		Font dataFont = new Font("SansSerif", Font.BOLD, 40);
		
		JLabel name =  new JLabel("Name:   ");
		content.add(name);
		JLabel cName= new JLabel(current.getName());
		cName.setFont(dataFont);
		content.add(cName);
		
		JLabel surname =  new JLabel("Surname:   ");
		content.add(surname);
		JLabel cSurname = new JLabel(current.getSurname());
		cSurname.setFont(dataFont);
		content.add(cSurname);
		
		
		JLabel number =  new JLabel("Number:    ");
		content.add(number);
		JLabel cNumber = new JLabel(current.getNumber());
		cNumber.setFont(dataFont);
		content.add(cNumber);
		
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// vraca nas na listu
				ApplicationController.list();
				
			}
		
			
		});
		
		JButton edit = new JButton("Edit");
		edit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationView.updateContact(current);
				
			}
			
		});
		
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// brise konatkt
				int response= JOptionPane.showConfirmDialog(null, "Are you shure", "Delete?" ,JOptionPane.YES_NO_OPTION);
				if (response == JOptionPane.YES_OPTION){
					ApplicationController.delete(current.getId());
				} else {
					return;
				}
				
				
			}
		
			
		});
		
		content.add(back);
		content.add(edit);
		content.add(delete);
		
		Main.replaceContent(content);
		Main.setVisible();
	}
	
	public static void updateContact(final Contact current){
		JPanel content =  new JPanel();
		JLabel greeting = new JLabel("        Add Contact        ");
		Font greetingFont = new Font("SansSerif", Font.BOLD ,30);
		greeting.setFont(greetingFont);
		content.add(greeting);
		
		JLabel name =  new JLabel("Name   ");
		content.add(name);
		final JTextField nameField = new JTextField(current.getName(), 25);		
		nameField.setPreferredSize(new Dimension(350,40));
		nameField.setVisible(true);
		content.add(nameField);
		
		JLabel surname =  new JLabel("Surname");
		content.add(surname);
		final JTextField surnameField = new JTextField(current.getSurname(),25);
		surnameField.setPreferredSize(new Dimension(350,40));
		surnameField.setVisible(true);
		content.add(surnameField);
		
		JLabel number =  new JLabel("Number");
		content.add(number);
		final JTextField numberField = new JTextField(current.getNumber(), 25);
		numberField.setPreferredSize(new Dimension(350, 40));
		numberField.setVisible(true);
		content.add(numberField);
		
		JButton submit =  new JButton("Save Update");
		submit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * get the data from the input
				 * ant send it to create method
				 */
				String cName=  nameField.getText();
				String cSurname = surnameField.getText();
				String cNumber = numberField.getText();
				
				current.setName(cName);
				current.setSurname(cSurname);
				current.setNumber(cNumber);
				
				ApplicationController.update(current);
				
			}
			
		});
		JButton back =  new JButton("Back");
		back.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.show(current.getId());
				
			}
			
		});
		
		content.add(submit);
		content.add(back);
		Main.replaceContent(content);
		Main.setVisible();
	}
	
	
	
	
	
	

}
