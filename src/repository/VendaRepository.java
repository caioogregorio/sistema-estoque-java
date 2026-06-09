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
				
				String sql = "SELECT * FROM venda";
				
				PreparedStatement st = conn.prepareStatement(sql);
						
				ResultSet rs = st.executeQuery();
				
				while(rs.next()) {
					System.out.println(
							rs.getInt("id") + " - " +
							rs.getInt("produto_id") + " - " +
							rs.getInt("quantidade_vendida") + " - " +
							rs.getDouble("valor_total" ) + " - " + 
							rs.getTimestamp("data_venda")
						);	
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
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return 0.0;
	}

}
