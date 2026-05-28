package application;

import java.util.Scanner;

import entities.Estoque;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Deseja cadastrar um produto?(1-sim|2-não): ");
		int opcao = sc.nextInt();
		
		if(opcao == 1) {
			System.out.println("Insira os dados do produto que voce quer cadastrar");
			Estoque p = new Estoque();
			p.adicionarProduto();
			p.listarProdutos();
		}
		else {
			System.out.println("A lista esta vazia");
		}

	}

}
