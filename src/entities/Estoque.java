package entities;

import java.util.ArrayList;
import java.util.Scanner;

public class Estoque {
	
	private ArrayList<Produto> produtos = new ArrayList<>();
	
	Scanner sc = new Scanner(System.in);
	Produto p = new Produto();
	
	public void adicionarProduto() {
		
			int opcao;
			do {
				sc.nextLine();
				System.out.println("Nome: ");
				String nome = sc.nextLine();
				System.out.println("Preço: ");
				double preco = sc.nextDouble();
				System.out.println("Quantidade: ");
				int quantidade = sc.nextInt();
				
				Produto p = new Produto(nome, preco, quantidade);
				
				produtos.add(p);
				
				System.out.println("Deseja continuar cadastrando(Digite 1 se quiser sair)?");
				opcao = sc.nextInt();
			}while(opcao != 1);
		
	}
	
	public void listarProdutos() {
			int quantidade = 1;
			for (Produto p : produtos) {
				System.out.println("Produto número " + quantidade);
				System.out.println(p);
				quantidade += 1;
			}
	}
			
			
		}


