package Lab01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Validar_Sopa {
	private char[][] sopa;
	private List<String> palavras=new ArrayList<String>();
	private String nome;
	Scanner input;
	public Validar_Sopa(String nome) {
		this.nome = nome;
	}
	public void validar() throws FileNotFoundException {
		Scanner input;
		File f=new File(nome);
		input =new Scanner(new FileInputStream(f));
		int z=0;
		while (input.hasNextLine()) {
			String word = input.nextLine();
			if (word.contains(" ")) {
				String palavra[]=word.split("  |\\,|\\;");
				for (int i=0;i<palavra.length;i++) {
					palavra[i]=palavra[i].trim();
					//tamanho das palavras menores de 2 letras
					if (palavra[i].length() < 3) {
						System.out.println("As palavras nao tem tamanho minimo");
					}
					
					palavras.add(palavra[i].toLowerCase());
				}
			}
			else {
				System.out.println(word);
				boolean provar=true;
				for (int i=0;i<word.length();i++) {
					if (Character.isLowerCase(word.charAt(i))) {
						provar=false;
					}
				}
				if (provar) {
					for (int i=0;i<word.length();i++) {
						sopa[z][i]=word.charAt(i);
					}
				}
				else if (!provar) {
					System.out.println("As letras tem de ser maiusculas");
				}
			}
			z++;
		}
	}
	public void Quadrado() throws FileNotFoundException {
		int linhas=0;
		int colunas=80;
		
		File f=new File(nome);
		input =new Scanner(new FileInputStream(f));
		while (input.hasNextLine()) {
			String word = input.nextLine();
			if (!word.contains(" ")) {
				linhas++;
				if (word.length()<colunas) {
					colunas=word.length();
				}
			}
		}	
		if (linhas > 80 || colunas > 80) {
			System.out.println("O tamanho maximo e 80*80");
		}
		else {
			if (linhas==colunas) {
				sopa=new char[linhas][colunas];
		}
		}
	}
	public void Duplicado_Palavras() {
		for (int i=0;i<palavras.size();i++) {
			String pal=palavras.get(i);
			for (int e=0;e<palavras.size();e++) {
				String pal2=palavras.get(e);
				if (!pal2.equals(pal)) {
					if (pal2.contains(pal)) {
						System.out.println("Existem palavras duplicadas");
						
					}
				}
			}
			
		}
	}
	public char[][] getSopa() {
		return sopa;
	}
	public List<String> getPalavras() {
		return palavras;
	}
	public void setSopa(char[][] sopa) {
		this.sopa = sopa;
	}
}
