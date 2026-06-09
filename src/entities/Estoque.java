package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import repository.ProdutoRepository;
import repository.VendaRepository;



public class Estoque {
	
	private ArrayList<Venda> vendas = new ArrayList<>();
	
	Scanner sc = new Scanner(System.in);
	
	public Estoque() {
		carregarVendas();
	}
	
	public void adicionarProduto() {
			sc.nextLine();
			
			System.out.println("Nome: ");
			String nome = sc.nextLine();
			
			if(ProdutoRepository.buscarProduto(nome) != null) {
			    System.out.println("Produto já cadastrado.");
			    return;
			}
			
			System.out.println("Preço: ");
			double preco = sc.nextDouble();
			System.out.println("Quantidade: ");
			int quantidade = sc.nextInt();
				
			Produto p = new Produto(nome, preco, quantidade);
			
			ProdutoRepository.inserirProduto(p);
	}
	
	public void listarProdutos() {
		ProdutoRepository.listarProdutos();
	}
	
	public void buscarProduto(){
		sc.nextLine();
		System.out.println("Insira o nome do produto que deseja buscar: ");
		String nome = sc.nextLine();
		Produto produto = ProdutoRepository.buscarProduto(nome);
		
		if(produto != null) {
			System.out.println(produto);
		} else {
			System.out.println("Produto não encontrado.");
		}
			
	}
	
	public void removerProduto() {
		sc.nextLine();
		System.out.println("Nome do produto que deseja remover: ");
		String remover = sc.nextLine();
		
		ProdutoRepository.removerProduto(remover);
	}
	
	public void realizarVenda() {
		sc.nextLine();
		
		System.out.println("Insira o nome do produto a ser vendido: ");
		String nomeProduto = sc.nextLine();
		
		Produto produto = ProdutoRepository.buscarProduto(nomeProduto);
		
		if(produto == null) {
			System.out.println("Produto não encontrado.");
			return;
		} 
		
		System.out.println("Informe a quantidade a ser vendida: ");
		int quantidadeVendida = sc.nextInt();
		
		while(quantidadeVendida <=0) {
			System.out.println("Quantidade inválida, insira novamente.");
			quantidadeVendida = sc.nextInt();
		}
		
		if(quantidadeVendida > produto.getQuantidade()) {
		    System.out.println("Não temos essa quantidade disponível no estoque.");
		    return;
		}
		
		ProdutoRepository.atualizarProduto(
			    produto.getNome(),
			    produto.getPreco(),
			    produto.getQuantidade() - quantidadeVendida
			);
		
		Venda venda = new Venda(produto, quantidadeVendida);

		VendaRepository.inserirVenda(venda);

		System.out.println(venda);
	}

	public void atualizarProduto() {
		sc.nextLine();
		
		System.out.println("Qual o nome do produto que você deseja atualizar os dados?");
		String nome = sc.nextLine();
		
		Produto produto = ProdutoRepository.buscarProduto(nome);
		
		if(produto == null) {
			System.out.println("Produto não encontrado.");
			return;
		}
		System.out.println(produto);
		System.out.println("O que você deseja alterar?\n1 - Alterar preço\n2 - Alterar quantidade\n3 - sair");
		int opcao = sc.nextInt();
		
		switch(opcao) {
			case 1:
				System.out.println("Insira o novo preço: ");
				double novoPreco = sc.nextDouble();
				
				ProdutoRepository.atualizarProduto(nome, novoPreco, produto.getQuantidade());
				break;
			case 2:
				System.out.println("Insira a nova quantidade: ");
				int novaQuantidade = sc.nextInt();
				ProdutoRepository.atualizarProduto(nome, produto.getPreco(), novaQuantidade);
				break;
			default:
				System.out.println("Opção inválida");
				break;
					
		}
		
	}
	
	public void listarVendas() {
		if(vendas.isEmpty()) {
			System.out.println("Nenhuma venda realizada.");
			return;
		}
		
		int numero = 1;
		
		for(Venda venda : vendas) {
			System.out.println("Venda " + numero);
			System.out.println(venda);
			System.out.println();
			
			numero += 1;
		}
	}
	
	public void salvarVendaNoArquivo(Venda venda){
		try {
			BufferedWriter bw = new BufferedWriter(
					new FileWriter("vendas.txt", true));
			bw.write(
				venda.getProduto().getNome() + ", " +
				venda.getProduto().getPreco() + ", " +
				venda.getQuantidadeVendida() + ", " +
				venda.getDataVenda()
				);		
			bw.newLine();
			bw.close();
		}
		catch(IOException e) {
			System.out.println("Erro ao salvar venda. " + e.getMessage());
		}
	}
	
	public void carregarVendas() {
		try {
			BufferedReader br = new BufferedReader(
				new FileReader("vendas.txt")
			);
			
			String linha;
			
			while((linha = br.readLine()) != null) {
				String[] dados = linha.split(",");
				String nome = dados[0].trim();
				double preco = Double.parseDouble(dados[1].trim());
				int quantidadeVendida = Integer.parseInt(dados[2].trim());
				LocalDateTime dataVenda = LocalDateTime.parse(dados[3].trim());
				Produto produto = new Produto(nome, preco, 0);
				Venda venda = new Venda(produto, quantidadeVendida, dataVenda);
				vendas.add(venda);
			}
			
			br.close();
			} catch(IOException e) {
				System.out.println("Erro ao carregar vendas: " + e.getMessage());
			}
	}	
	
	public void reporEstoque() {
		sc.nextLine();
		
		System.out.println("Nome do produto: ");
		String nome = sc.nextLine();
		
		Produto produto = ProdutoRepository.buscarProduto(nome);
		
		if(produto == null) {
			System.out.println("Produto não encontrado.");
			return;
		}
		
		System.out.println("Insira a quantidade a ser adicionada:");
		int quantidadeReposicao = sc.nextInt();
		
		while(quantidadeReposicao <= 0) {
			System.out.println("Quantidade inválida, insira novamente: ");
			quantidadeReposicao = sc.nextInt();
		}
		
		ProdutoRepository.atualizarProduto(
		        produto.getNome(),
		        produto.getPreco(),
		        produto.getQuantidade() + quantidadeReposicao
		    );
		
		 System.out.println("Estoque atualizado com sucesso.");
		    System.out.println("Nova quantidade: "
		            + (produto.getQuantidade() + quantidadeReposicao));
	}

	
	

}
