package ba.bitcamp.model;

import java.sql.*;

public class Application {
	
	protected static Connection db;
	
	public static void init() throws SQLException{
		db =  DriverManager.getConnection("jdbc:sqlite:phonebook.db");
	}
	
	protected static ResultSet find(int id, String tableName){
		try {
			Statement stmt = db.createStatement();
			String sql = String.format("SELECT * FROM %s where id= '%d'; ", tableName, id);
			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
		
	}
	
	
	protected static  boolean save(String tableName, String values){
		Statement stmt = null;
		try {
			stmt = db.createStatement();
			String sql = String.format("INSERT INTO %s VALUES %s;", tableName, values);
			stmt.execute("begin;");
			stmt.execute(sql);
			stmt.execute("commit;");
			return true;
		} catch (SQLException e) {
			//odgovara nam try{}catch{} jer ce nam se u catchu vratiti false
			if( stmt != null){
				try {
					stmt.execute("rollback;");
				} catch (SQLException e1) {
					System.err.println(e1.getMessage());
					return false;
				}
			}
			
			System.err.println(e.getMessage());
			return false;
			
		}
	}

}
