package aula01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class WordPuzzle {
	
	char[][] letters;
	ArrayList<String> palavras = new ArrayList<>();
	int size, c, found, l;
	Iterator<String> itr;
	
	public void readFile(String file) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))){	
			br.mark(1);
			String line = br.readLine();
			size = line.length();
			c = 0;
			letters = new char[size][size];
				while (c < size) {
					for (int i = 0; i < size; i++){
						letters[c][i] = line.charAt(i);
					}
					c++;
					line = br.readLine();	
				}
				c = 0;
				br.reset();
				while ((line = br.readLine()) != null){
					c++;
					if (c > size){
						String[] aux = line.split("[, ;]+");
						for (int i = 0; i < aux.length; i++){
							String palavra = aux[i].trim().toUpperCase();
							if (palavra.length() > 1 && palavra.matches("[A-Z]+")){
								palavras.add(palavra);
							}
						}
					}
				}
				l = palavras.size();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	private void spiderSearch(Ponto point, String palavra, int tamanho) {
		
		//UP -> i-1
		north(point, palavra, tamanho);
		
		//Down -> i+1
		south(point, palavra, tamanho);
		
		//Left -> j-1
		west(point, palavra, tamanho);
		
		//Right -> j+1
		east(point, palavra, tamanho);
		
		//Lower Right -> i+1, j+1
		southeast(point, palavra, tamanho);
		
		//Upper Right -> i-1, j+1
		northeast(point, palavra, tamanho);
		
		//Lower Left -> i+1, j-1
		southwest(point, palavra, tamanho);
				
		//Upper Left -> i-1, j-1
		northwest(point, palavra, tamanho);
	}
	private void west(Ponto point, String palavra, int tamanho) {
		Direcao dir;
		String s = "";
		for (int i = 0; i < point.getY(); i++) {
			s += letters[point.getX()][point.getY() - i];
		}
		if (s.contains(palavra)) {
			dir = Direcao.LEFT;
			found++;
			itr.remove();
			saveAll(point.getX()+1, point.getY()+1, dir, palavra);;
		}
	}
	private void east(Ponto point, String palavra, int tamanho) {
		Direcao dir;
		String s = "";
		for (int i = 0; i < size - point.getY(); i++) {
			s += letters[point.getX()][point.getY() + i];
		}
		if (s.contains(palavra)) {
			dir = Direcao.RIGHT;
			found++;
			itr.remove();
			saveAll(point.getX()+1, point.getY()+1, dir, palavra);
		}
	}
	private void south(Ponto point, String palavra, int tamanho) {
		Direcao dir;
		String s = "";
		for (int i = 0; i < size - point.getX(); i++) {
			s += letters[point.getX() + i][point.getY()];
		}
		if (s.contains(palavra)) {
			dir = Direcao.DOWN;
			found++;
			itr.remove();
			saveAll(point.getX()+1, point.getY()+1, dir, palavra);
		}
	}
	private void north(Ponto point, String palavra, int tamanho) {
		Direcao dir;
		String s = "";
		for (int i = 0; i < point.getX(); i++) {
			s += letters[point.getX() - i][point.getY()];
		}
		if (s.contains(palavra)) {
			dir = Direcao.UP;
			found++;
			itr.remove();
			saveAll(point.getX()+1, point.getY()+1, dir, palavra);
		}
	}
	private void southeast(Ponto point, String palavra, int tamanho) {
		Direcao dir;
		String s = "";
		for (int i = 0, j = 0; i < size - point.getX() && j < size - point.getY(); i++, j++) {
			s += letters[point.getX() + i][point.getY() + i];
		}
		if (s.contains(palavra)) {
			dir = Direcao.LOWERRIGHT;
			found++;
			itr.remove();
			saveAll(point.getX()+1, point.getY()+1, dir, palavra);
		}
	}
	private void northeast(Ponto point, String palavra, int tamanho) {
		Direcao dir;
		String s = "";
		for (int i = 0, j = 0; i < point.getX() && j < size - point.getY(); i++, j++) {
			s += letters[point.getX() - i][point.getY() + i];
		}
		if (s.contains(palavra)) {
			dir = Direcao.UPPERRIGHT;
			found++;
			itr.remove();
			saveAll(point.getX()+1, point.getY()+1, dir, palavra);
		}
	}
	private void southwest(Ponto point, String palavra, int tamanho) {
		Direcao dir;
		String s = "";
		for (int i = 0, j = 0; i < size - point.getX() && j < point.getY(); i++, j++) {
			s += letters[point.getX() + i][point.getY() - i];
		}
		if (s.contains(palavra)) {
			dir = Direcao.LOWERLEFT;
			found++;
			itr.remove();
			saveAll(point.getX()+1, point.getY()+1, dir, palavra);
		}
	}
	private void northwest(Ponto point, String palavra, int tamanho) {
		Direcao dir;
		String s = "";
		for (int i = 0, j = 0; i < point.getX() && j < point.getY(); i++, j++) {
			s += letters[point.getX() - i][point.getY() - i];
		}
		if (s.contains(palavra)) {
			dir = Direcao.UPPERLEFT;
			found++;
			itr.remove();
			saveAll(point.getX()+1, point.getY()+1, dir, palavra);
		}
	}
	private void saveAll (int x, int y, Direcao dir, String palavra) {
		System.out.println(palavra.toUpperCase() + "\t" + palavra.length() + "\t"
				+ x + "," + y + "\t" + dir);
	}
	private void searchWord(){
		itr = palavras.iterator();
		while (itr.hasNext()) {
			String s = itr.next();
			coordinate(s, s.charAt(0), s.length());
		}
	}
	private void coordinate (String palavra, char letra, int tamanho) {
		for (int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if (letters[i][j] == letra) {
					if (!palavras.contains(palavra)) { 
						return;
					} else {
						Ponto pt = new Ponto (i, j);
						spiderSearch(pt, palavra, tamanho);
					}
				}
			}
		}
	}
	public static void main(String[] args){
	
		WordPuzzle pz = new WordPuzzle();
		pz.readFile("wordlist.txt");
		
		System.out.print("   ");	
		for (int l = 0; l < pz.letters.length; l++) {
			System.out.print(l+1 + " ");
		}
		System.out.print("\n   ");
		for (int k = 0; k < pz.letters.length; k++) {
			System.out.print("- ");
		}
		for (int i = 0; i < pz.letters.length; i++){
			System.out.println("");
			if (i < 9){
				System.out.print(i+1 + " |");
			} else {
				System.out.print(i+1 + "|");
			}
			for (int j = 0; j < pz.letters.length; j++){
				System.out.print(pz.letters[i][j] + " ");
			}
		}
		System.out.println("\n\n" + pz.palavras + "\n");
		long startTime = System.nanoTime();
		pz.searchWord();
		long endTime = System.nanoTime();
		System.out.println("\n" + "Words Found: " + pz.found + " (out of " + pz.l + ")"  + "\nExecution time: " + (endTime - startTime)/1000000 + " ms");
	}
}
