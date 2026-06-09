package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import db.DB;
import ui.Menu;

public class Main {

	public static void main(String[] args) {
		
		
		Connection conn = DB.getConnection();
		
		if(conn != null) {
			System.out.println("Conectado com sucesso!");
		} else {
			System.out.println("Falha na conexao");
		}
		
		
		
		Scanner sc = new Scanner(System.in);
		Menu menu = new Menu();
		
		menu.executar();
		
		
		
		sc.close();

	}
	

}
