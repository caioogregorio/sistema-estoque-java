package entities;

public class Produto {
	
	private int id;
	private String nome;
	private double preco;
	private int quantidade;
	
	public Produto() {
		
	}

	public Produto(String nome, Double preco, Integer quantidade) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	@Override
	public String toString() {
	    return "Nome: "
	            + nome
	            + "\nPreço: R$ "
	            + preco
	            + "\nQuantidade: "
	            + quantidade;
	}
	
}
