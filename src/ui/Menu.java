package ui;

import java.util.Scanner;

import entities.Estoque;

public class Menu {
	
	private Scanner sc = new Scanner(System.in);
	
	private Estoque estoque = new Estoque();
	
	public void executar() {
		int opcao = 1;
		while (opcao != 0) {
			System.out.println("-----M E N U -----");
			System.out.println("1 - Cadastrar produto");
			System.out.println("2 - Listar produtos");
			System.out.println("3 - Buscar produto");
			System.out.println("4 - Remover produto");
			System.out.println("5 - Realizar venda");
			System.out.println("6 - Atualizar produto");
			System.out.println("0 - Sair");
			System.out.println();
			
			System.out.println("Insira a opção desejada");
			opcao = sc.nextInt();
			
			
				if(opcao < 0 || opcao > 6) {
					System.out.println("Opção inválida");
					System.out.println("Digite novamente: ");
					opcao = sc.nextInt();
				}
				switch(opcao) {
					case 0:
						System.out.println("Fim do programa");
						break;
					case 1:
						estoque.adicionarProduto();
						break;
					case 2:
						estoque.listarProdutos();
						break;
					case 3:
						estoque.buscarProduto();
						break;
					case 4:
						estoque.removerProduto();
						break;
					case 5:
						estoque.realizarVenda();
						break;
					case 6:
						estoque.atualizarProduto();
						break;
				}
				
			} 
	
			sc.close();
	}
	
	
	
}
