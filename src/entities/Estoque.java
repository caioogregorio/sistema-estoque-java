package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Estoque {
	
	private ArrayList<Produto> produtos = new ArrayList<>();
	private ArrayList<Venda> vendas = new ArrayList<>();
	
	Scanner sc = new Scanner(System.in);
	
	public Estoque() {
		carregarProdutos();
		carregarVendas();
	}
	
	public void adicionarProduto() {
			sc.nextLine();
			
			System.out.println("Nome: ");
			String nome = sc.nextLine();
			
			if(produtoExiste(nome)) {
				System.out.println("Produto ja cadastrado.");
				return;
			}
			
			System.out.println("Preço: ");
			double preco = sc.nextDouble();
			System.out.println("Quantidade: ");
			int quantidade = sc.nextInt();
				
			Produto p = new Produto(nome, preco, quantidade);
				
			produtos.add(p);
			salvarProdutoNoArquivo(p);
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
				System.out.println();
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
				salvarTodosProdutos();
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
		String nomeProduto = sc.nextLine();
		
		boolean encontrado = false;
		
		for(Produto p: produtos) {
			
			if(p.getNome().equalsIgnoreCase(nomeProduto)) {
				
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
					Venda venda = new Venda(p, quantidadeVendida);
					vendas.add(venda);
					salvarVendaNoArquivo(venda);
					System.out.println(venda);
					
					salvarTodosProdutos();
					
				}
			break;
			} 
		}
		
		if(!encontrado) {
			System.out.println("Produto não encontrado.");
		}
	}

	public void atualizarProduto() {
		sc.nextLine();
		
		System.out.println("Qual o nome do produto que você deseja atualizar os dados?");
		String nome = sc.nextLine();
		
		boolean encontrado = false;
		
		for(Produto p : produtos) {
			
			if(p.getNome().equalsIgnoreCase(nome)) {
				encontrado = true;
				
				System.out.println("O que você deseja alterar?\n"
						+ "1 - Nome\n"
						+ "2 - Preço\n"
						+ "3 - Quantidade\n"
						+ "4 - Sair");
				System.out.println("Insira a opção desejada: ");
				int opcao = sc.nextInt();
				
				while(opcao != 4) {
		
				switch(opcao) {
					case 1:
						sc.nextLine();
						System.out.println("Insira o novo nome: ");
						String novoNome = sc.nextLine();
						p.setNome(novoNome);
						salvarTodosProdutos();
						System.out.println("Produto atualizado com sucesso!");
						System.out.println(p);
						break;
					case 2:
						System.out.println("Insira o novo preço: ");
						double novoPreco = sc.nextDouble();
						p.setPreco(novoPreco);
						salvarTodosProdutos();
						System.out.println("Produto atualizado com sucesso!");
						System.out.println(p);
						break;
					case 3:
						System.out.println("Insira uma nova quantidade: ");
						int quantidadeNova = sc.nextInt();
						p.setQuantidade(quantidadeNova);
						salvarTodosProdutos();
						System.out.println("Produto atualizado com sucesso!");
						System.out.println(p);
						break;
					}
				
				System.out.println("Insira a opção desejada");
				opcao = sc.nextInt();
				
				}break;
			}
			
		}
		if(!encontrado) {
			System.out.println("Produto não foi encontrado");
		}
	}
	
	
	public void salvarProdutoNoArquivo(Produto p) {
		try {
			BufferedWriter bw = new BufferedWriter(
					new FileWriter("produtos.txt", true));
			bw.write(
				p.getNome() + ", " +
				p.getPreco() + ", " +
				p.getQuantidade()
				);		
			bw.newLine();
			bw.close();
		}
		catch(IOException e) {
			System.out.println("Erro ao salvar produto. " + e.getMessage());
		}
	}
	
	public void carregarProdutos() {
		try {
			BufferedReader br = new BufferedReader(
				new FileReader("produtos.txt")
			);
			
			String linha;
			
			while((linha = br.readLine()) != null) {
				String[] dados = linha.split(",");
				String nome = dados[0].trim();
				double preco = Double.parseDouble(dados[1].trim());
				int quantidade = Integer.parseInt(dados[2].trim());
				Produto p = new Produto(nome, preco, quantidade);
				produtos.add(p);
			}
			
			br.close();
			
		}catch(IOException e) {
			System.out.println("Erro ao carregar produtos. " + e.getMessage());
		}
	}
	
	public void salvarTodosProdutos() {
		try {
			BufferedWriter bw = new BufferedWriter(
					new FileWriter("produtos.txt")
					);
			
			for (Produto p : produtos) {
				bw.write(
						p.getNome() + ", " +
						p.getPreco() + ", " +
						p.getQuantidade()
						);
				
				bw.newLine();
			}
			
			bw.close();
		} catch(IOException e) {
			System.out.println("Erro ao salvar produtos: " + e.getMessage());
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
	
	private boolean produtoExiste(String nome) {
		for(Produto p : produtos) {
			
			if(p.getNome().equalsIgnoreCase(nome)) {
				return true;
			}
		}
		
		return false;
	}
}
