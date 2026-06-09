package application;

import java.sql.Connection;
import java.util.Scanner;

import db.DB;
import entities.Produto;
import repository.ProdutoRepository;
import ui.Menu;

public class Main {

	public static void main(String[] args) {
		
		
		Connection conn = DB.getConnection();
		
		if(conn != null) {
			System.out.println("Conectado com sucesso!");
		} else {
			System.out.println("Falha na conexao");
		}
		
		Produto produto = ProdutoRepository.buscarProduto("Monitor");

		if(produto != null) {
		    System.out.println(produto);
		} else {
		    System.out.println("Produto não encontrado.");
		}
		
		Scanner sc = new Scanner(System.in);
		Menu menu = new Menu();
		
		menu.executar();
		
		
		
		sc.close();

	}

}
