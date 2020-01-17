package Lab01;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class WSolver {
	public static void main(String[] args) throws FileNotFoundException {
		String Nomeficheiro=null;
		double TempoInicial=0;
		double TempoFinal = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduza o nome do ficheiro com o puzzle: ");
		Nomeficheiro = sc.nextLine();
		sc.close();
		TempoInicial = System.currentTimeMillis();
		Validar_Sopa v=new Validar_Sopa(Nomeficheiro);
        v.Quadrado();
        v.validar();
        v.Duplicado_Palavras();
        char[][] sopa=v.getSopa();
        java.util.List<String> palavras=v.getPalavras();
        Solucao s=new Solucao(sopa,palavras);
        s.Palavras();
        java.util.List<String> res=s.getResolution();
        TempoFinal = System.currentTimeMillis();
        System.out.println("Elapsed time (secs): " + (TempoFinal - TempoInicial) * 0.01);
        for (int i=0;i<res.size();i++) {
            String b[]=res.get(i).split(" ");
            for (int e=0;e<b.length;e++) {
         	   System.out.printf("%-8s",b[e]);   		
            }
            System.out.println("");
        }
	}

}
