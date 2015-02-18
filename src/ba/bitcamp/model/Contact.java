package ba.bitcamp.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Contact extends Application {

	private int id;
	private String name;
	private String surname;
	private String number;

	protected static final String tableName = "contacts";

	/**
	 * Constructor for Contacts without parameters.
	 */
	public Contact() {
		this.id = -1;
		this.name = "Unknown";
		this.surname = "Unknown";
		this.number = "Unknown";

	}

	/**
	 * Constructor for Contacts with three parameters.
	 * 
	 * @param name
	 *            - name of user contact
	 * @param surname
	 *            - surname of user contact
	 * @param number
	 *            - number of telephone of user contact
	 */
	public Contact(String name, String surname, String number) {
		this.id = -1;
		this.name = name;
		this.surname = surname;
		this.number = number;
	}
	
	public Contact(int id, String name, String surname) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		
	}

	/**
	 * Constructor for Contacts with three parameters.
	 * 
	 * @param id
	 *            - id number of contact.
	 * @param name
	 *            - name of user contact.
	 * @param surname
	 *            - surname of user contact.
	 * @param number
	 *            - number of telephone of user contact.
	 */
	public Contact(int id, String name, String surname, String number) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.number = number;
	}

	/**
	 * This method connects to the base and find the contact using 'id' of contact.
	 * @param id - id number in base of contact.
	 * @return contacts data that are found in base.
	 */
	public static Contact find(int id) {
		ResultSet res = Application.find(id, tableName);

		try {
			int cId = res.getInt("id");
			String cName = res.getString("name");
			String cSurname = res.getString("surname");
			String cNumber = res.getString("number");

			/*return the object of type Contact */
			return new Contact(cId, cName, cSurname, cNumber);

			/* If it does not fount the client return null and throws exception */
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}

	}

 /**
  * 
  * Under the object(Contact) that this function has been called, takes the
  * name, surname and number
  * and sends it to the application model (puts it inside the query statement
  *  check create and save from application)
  * Checks whether it has saved the object or not;
  * @return true if contact is saved in base or false if contact is not saved.
  */
	public boolean save() {
		String values = null;
		if (this.id != -1){
			values = String.format("('%d', '%s', '%s', '%s')", this.id, this.name,
					this.surname, this.number);
		} else {
			values = String.format("(?, '%s', '%s', '%s')", this.name,
					this.surname, this.number);
		}
		
		int id = Application.save(tableName, values);
		if (id == -1){
			return false;
		} else {
			this.id= id;
			return true;
		}

	}

	/** 
	 * Gets us all the contacts from the database; but only the name and surname
	 * if we were to enter "*" as the second parameter, then we get all the
	 * data;
	 * @return all contacts in array list of contacts.
	 */
	public static Contact[] all() {
		ResultSet rs = Application.all(tableName, "id, name, surname");
		if (rs == null) {
			return new Contact[0];
		}
		LinkedList<Contact> cl = new LinkedList<Contact>();
		try {
			while ( rs.next() ){
				int id = rs.getInt("id");
				String cName = rs.getString("name");
				String cSurname = rs.getString("surname");
				cl.add(new Contact(id, cName, cSurname));
				
			}
			
			Contact[] all =  new Contact[cl.size()];
			cl.toArray(all);
			return all;
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return new Contact[0];
		}
	}
	
	// update TABLENAME set kolone WHERE id='id'
	public void update(){
		String sql= String.format("name = '%s', surname = '&s', number = '&s'",this.name, this.surname , this.number);
		Application.update(tableName,this.id, sql);
	}
	
	public static void delete(int id){
		Application.delete(tableName, id);
	}
	/**
	 * Gets us what we will put on our Buttons later; on every listed contact in
	 * our view SHOW/ALL
	 * @return
	 */
	public String getDisplayName(){
		return this.name + " " + this.surname;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname
	 *            the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

}
