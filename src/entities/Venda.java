package entities;

import java.time.LocalDateTime;

public class Venda {
	private Produto produto;
	private	int quantidadeVendida;
	private double valorTotal;
	private LocalDateTime dataVenda;
	
	public Venda(Produto produto, int quantidadeVendida) {
		this.produto = produto;
		this.quantidadeVendida = quantidadeVendida;
		this.valorTotal = produto.getPreco() * quantidadeVendida;
		this.dataVenda = LocalDateTime.now();
	}
	
	public Venda(Produto produto, int quantidadeVendida, LocalDateTime dataVenda) {
		this.produto = produto;
		this.quantidadeVendida = quantidadeVendida;
		this.valorTotal = produto.getPreco() * quantidadeVendida;
		this.dataVenda = dataVenda;
	}
	
	
	public Produto getProduto() {
		return produto;
	}

	public int getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public LocalDateTime getDataVenda() {
		return dataVenda;
	}

	
	@Override
	public String toString() {
		return "Produto: " + produto.getNome()
			+ "\nQuantidade vendida: " + quantidadeVendida
			+ "\nValor Total RS: " + valorTotal
			+ "\nData: " + dataVenda;
	}
}
