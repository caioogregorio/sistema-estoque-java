package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

	public static Connection getConnection() {
		
		try {
			
			return DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/estoque_db",
					"root",
					"1234"
					);
		
		} catch (Exception e) {
			
			System.out.println("Erro: " + e.getMessage());
			return null;
		}
	}
		
}
