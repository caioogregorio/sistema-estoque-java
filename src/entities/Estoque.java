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
			int quantidade = 1;
			for (Produto p : produtos) {
				System.out.println("Produto número " + quantidade);
				System.out.println(p);
				quantidade += 1;
			}
	}
	
	public void buscarProduto(){
		sc.nextLine();
		System.out.println("Insira o nome do produto que deseja buscar: ");
		String busca = sc.nextLine();
		for(Produto p: produtos) {
			if(p.getNome().equalsIgnoreCase(busca)) {
				System.out.println(p);
			} 
		}
	}
	
	public void removerProduto() {
		System.out.println("Nome do produto que deseja remover: ");
		String remover = sc.nextLine();
		for(Produto p : produtos) {
			if(p.getNome().equalsIgnoreCase(remover)) {
				produtos.remove(p);
				System.out.println("Produto removido com sucesso");	
			}else {
				System.out.println("Produto não foi encontrado para remoção");
			}
			
		} 
			
		
	}
			
			
		}


