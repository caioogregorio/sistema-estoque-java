package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
