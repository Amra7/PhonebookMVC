package ba.bitcamp.model;

import java.sql.*;

public class Application {

	protected static Connection db;

	/**
	 * Creates the connection to the database : phonebook.db;
	 */
	public static void init(String databaseName) throws SQLException {
		db = DriverManager.getConnection("jdbc:sqlite:"+databaseName+ ".db");
	}

	/**
	 * Finds the contact with contact id from phonebook database.
	 * @param id - id number of contact.
	 * @param tableName -  name of table in phonebook database.
	 * @return
	 */
	protected static ResultSet find(int id, String tableName) {
		try {
			Statement stmt = db.createStatement();
			String sql = String.format("SELECT * FROM %s where id= '%d'; ",
					tableName, id);
			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}

	}

	/**
	 * Returns true or false depending on weather it has saved the contact
	 * in phonebook database 'phonebook.db'.
	 * @param tableName  -  name of table in phonebook database.
	 * @param values - data of contact saved in String values.
	 * @return
	 */
	protected static int save(String tableName, String values) {
		Statement stmt = null;
		try {
			stmt = db.createStatement();
			String sql = String.format("INSERT INTO %s VALUES %s;", tableName,
					values);
			stmt.execute("begin;");
			stmt.execute(sql);
			/* If everything went right we commit the data of client */
			stmt.execute("commit;");
			
			sql = String.format("Select max(id) as last if FROM %s; ", tableName);
			stmt.executeQuery(sql);
			ResultSet rs =stmt.executeQuery(sql);
			rs.next(); // pokazuje na vrijednost prije trazene
			
			return rs.getInt(1);
		} catch (SQLException e) {
			// odgovara nam try{}catch{} jer ce nam se u catchu vratiti false
			/* If contact is not saved in phonebook.db return false */
			if (stmt != null) {
				try {
					/* If something wnt wrong we can return our data to begin; */
					stmt.execute("rollback;");
				} catch (SQLException e1) {
					System.err.println(e1.getMessage());
					return -1;
				}
			}

			System.err.println(e.getMessage());
			/* We did not manage to write data in phonebook.db */
			return -1;

		}
	}

	/**
	 * Collects all data of Contact from phonebook.db and save them in object type ResutSet.
	 * Checks Contact.all() method and gives us data from array list of Contacts.
	 * @param tableName -  name of table in phonebook database.
	 * @param columnNames - name of column in phonebook.db or any database.
	 * @return - Object type statement from where we can take all that from columns.
	 */
	protected static ResultSet all(String tableName, String columnNames) {
		try {
			Statement stmt = db.createStatement();
			String sql = String.format("Select %s from %s;", columnNames,
					tableName);
			/* Return ResultSet with data of contacts */
			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			/* If it does not get data from database return false */
			System.err.println(e.getMessage());
			return null;
		}

	}
	
	public static void  update( String tableName, int id, String values){
		try {
			Statement stmt = db.createStatement();
			String sql =  String.format("UPDATE %s SET %s WHERE id = '%d';", tableName, values, id);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		

		}
	
	protected static void delete(String tableName, int id){
		try {
			Statement stmt = db.createStatement();
			String sql = String.format("DELETE FROM %s WHERE id = '%d'; ", tableName, id);
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
