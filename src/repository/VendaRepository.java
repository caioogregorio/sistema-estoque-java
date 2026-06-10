package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import db.DB;
import entities.Venda;

public class VendaRepository {

	public static void inserirVenda(Venda venda) {
		
		try {
			Connection conn = DB.getConnection();
			
			String sql = "INSERT INTO venda (produto_id, quantidade_vendida, valor_total, data_venda) VALUES (?, ?, ?, ?)";
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, venda.getProduto().getId());
			st.setInt(2, venda.getQuantidadeVendida());
			st.setDouble(3, venda.getValorTotal());
			st.setTimestamp(4, Timestamp.valueOf(venda.getDataVenda()));
				
			int linhasAfetadas = st.executeUpdate();
			
			if(linhasAfetadas > 0) {
				System.out.println("Venda efetuada com sucesso!");
			} else {
				System.out.println("Não conseguimos efetuar a venda.");
			}
			
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		 
		
	}
	
	public static void listarVendas() {
			
			try {
				
				Connection conn = DB.getConnection();
				
				String sql = "SELECT\r\n"
						+ "    p.nome,\r\n"
						+ "    v.quantidade_vendida,\r\n"
						+ "    v.valor_total,\r\n"
						+ "    v.data_venda\r\n"
						+ "FROM venda v\r\n"
						+ "JOIN produto p\r\n"
						+ "ON v.produto_id = p.id;";
				
				PreparedStatement st = conn.prepareStatement(sql);
						
				ResultSet rs = st.executeQuery();
				
				while(rs.next()) {
				    System.out.println("Produto: " + rs.getString("nome"));
				    System.out.println("Quantidade: " + rs.getInt("quantidade_vendida"));
				    System.out.println("Valor Total: R$ " + rs.getDouble("valor_total"));
				    System.out.println("Data: " + rs.getTimestamp("data_venda"));
				    System.out.println("------------------------");
				}
				
				rs.close();
				st.close();
				conn.close();
				
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
	public static double faturamentoTotal() {
		
		try {
			
			Connection conn = DB.getConnection();
			
			String sql = "SELECT SUM(valor_total) AS total FROM venda";
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				return rs.getDouble("total");
			}
			
			st.close();
			rs.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return 0.0;
	}

	public static void produtoMaisVendido() {
		
		try {
			
			Connection conn = DB.getConnection();
			
			String sql = "SELECT\r\n"
					+ "    p.nome,\r\n"
					+ "    SUM(v.quantidade_vendida) AS total_vendido\r\n"
					+ "FROM venda v\r\n"
					+ "JOIN produto p\r\n"
					+ "ON v.produto_id = p.id\r\n"
					+ "GROUP BY p.nome\r\n"
					+ "ORDER BY total_vendido DESC\r\n"
					+ "LIMIT 1;";
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				String nome = rs.getString("nome");
				int totalVendido = rs.getInt("total_vendido");

				System.out.println("Produto mais vendido: " + nome);
				System.out.println("Quantidade vendida: " + totalVendido);
			}
			
			st.close();
			rs.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void listarVendasPorPeriodo(Timestamp dataInicial, Timestamp dataFinal){
	    
		try {
			
			Connection conn = DB.getConnection();
			
			String sql = "SELECT\r\n"
					+ "    p.nome,\r\n"
					+ "    v.quantidade_vendida,\r\n"
					+ "    v.valor_total,\r\n"
					+ "    v.data_venda\r\n"
					+ "FROM venda v\r\n"
					+ "JOIN produto p\r\n"
					+ "ON v.produto_id = p.id\r\n"
					+ "WHERE v.data_venda BETWEEN ? AND ?";
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setTimestamp(1, dataInicial);
			st.setTimestamp(2, dataFinal);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
			    System.out.println("Produto: " + rs.getString("nome"));
			    System.out.println("Quantidade: " + rs.getInt("quantidade_vendida"));
			    System.out.println("Valor Total: R$ " + rs.getDouble("valor_total"));
			    System.out.println("Data: " + rs.getTimestamp("data_venda"));
			    System.out.println("------------------------");
			}
			
			rs.close();
			st.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
