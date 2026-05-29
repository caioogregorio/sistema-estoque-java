package entities;

import java.util.ArrayList;
import java.util.Scanner;

public class Estoque {
	
	private ArrayList<Produto> produtos = new ArrayList<>();
	
	Scanner sc = new Scanner(System.in);
	
	public void adicionarProduto() {
			sc.nextLine();
			System.out.println("Nome: ");
			String nome = sc.nextLine();
			System.out.println("Preço: ");
			double preco = sc.nextDouble();
			System.out.println("Quantidade: ");
			int quantidade = sc.nextInt();
				
			Produto p = new Produto(nome, preco, quantidade);
				
			produtos.add(p);
	}
	
	public void listarProdutos() {
		if(produtos.isEmpty()) {
			System.out.println("Nenhum produto cadastrado.");
			return;
		}
	
			int quantidade = 1;
			for (Produto p : produtos) {
				System.out.println("Produto número " + quantidade);
				System.out.println(p);
				quantidade += 1;
			}
	}
	
	public void buscarProduto(){
		if(produtos.isEmpty()) {
			System.out.println("Nenhum produto cadastrado");
			return;
		}
		
		sc.nextLine();
		
		System.out.println("Insira o nome do produto que deseja buscar: ");
		String busca = sc.nextLine();
		
		boolean encontrado = false;
		
		for(Produto p: produtos) {
			if(p.getNome().equalsIgnoreCase(busca)) {
				System.out.println(p);
				encontrado = true;
			} 
		}
		if(!encontrado) {
			System.out.println("Produto não encontrado");
		}
	}
	
	public void removerProduto() {
		sc.nextLine();
		System.out.println("Nome do produto que deseja remover: ");
		String remover = sc.nextLine();
		
		boolean encontrado = false;
		
		for(int i = 0; i < produtos.size(); i++) {
			
			Produto p = produtos.get(i);
			
			if(p.getNome().equalsIgnoreCase(remover)) {
				produtos.remove(i);	
				
				encontrado = true;
				System.out.println("Produto removido com sucesso");
				break;
			}
			
		}
		
		if(!encontrado) {
			System.out.println("Produto nao encontrado");
		}
	}
	
	public void realizarVenda() {
		sc.nextLine();
		
		System.out.println("Insira o nome do produto a ser vendido: ");
		String venda = sc.nextLine();
		
		boolean encontrado = false;
		
		for(Produto p: produtos) {
			
			if(p.getNome().equalsIgnoreCase(venda)) {
				
				encontrado = true;
				
				System.out.println("Informe a quantidade: ");
				int quantidadeVendida = sc.nextInt();
						
				while(quantidadeVendida <= 0) {
						System.out.println("A quantidade deve ser maior que zero para ser vendida.");
						System.out.println("Informe novamente: ");
						quantidadeVendida = sc.nextInt();
				}
					
				if(quantidadeVendida > p.getQuantidade()) {
					System.out.println("Não temos essa quantidade no estoque.");
				}
				else {
					System.out.println("Venda realizada com sucesso!");
					p.setQuantidade(p.getQuantidade() - quantidadeVendida);	
				}
			break;
			} 
		}
		
		if(!encontrado) {
			System.out.println("Produto não encontrado.");
		}
		
			
			
	}

}
