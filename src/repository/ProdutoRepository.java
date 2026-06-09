package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import db.DB;
import entities.Produto;

public class ProdutoRepository {
	
	public static void inserirProduto(Produto produto) {
		
		try {
			
			Connection conn = DB.getConnection();
			
			String sql = "INSERT INTO produto(nome, preco, quantidade) VALUES (?, ?, ?)";
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, produto.getNome());
			st.setDouble(2, produto.getPreco());
			st.setInt(3, produto.getQuantidade());
			
			st.executeUpdate();
			
			System.out.println("Produto inserido com sucesso!");
			
			st.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	public static void listarProdutos() {
		
		try {
			
			Connection conn = DB.getConnection();
		
			String sql = "SELECT * FROM produto";
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				System.out.println(
					rs.getInt("id") + " - " +
					rs.getString("nome") + " - " +
					rs.getDouble("preco" ) + " - " + 
					rs.getInt("quantidade")
				);	
			}
			
			rs.close();
			st.close();
			conn.close();
		
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static Produto buscarProduto(String nome) {
		
		try {
			
			Connection conn = DB.getConnection();
			
			String sql = "SELECT * FROM produto WHERE nome = ?";
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nome);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				String nomeProduto = rs.getString("nome");
				double preco = rs.getDouble("preco");
				int quantidade = rs.getInt("quantidade");
				
				Produto produto = new Produto(
						nomeProduto,
						preco,
						quantidade
						);
						
				return produto;
			}
			
				return null;
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return null;
		}
	}
}
