package application;

import java.util.Scanner;

import entities.Estoque;
import ui.Menu;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Estoque estoque = new Estoque();
		Menu menu = new Menu();
		
		menu.executar();
		
		
		
		sc.close();

	}

}
