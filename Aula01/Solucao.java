package Lab01;


import java.util.ArrayList;
import java.util.List;

public class Solucao {
	private char[][] sopa;
	private List<String> palavras=new ArrayList<String>();
	private ArrayList<String> Resultado=new ArrayList<String>();
	private String Baixo,Cima,Esquerda,Direita;
	public Solucao(char[][] sopa, List<String> pal) {
		this.sopa = sopa;
		this.palavras = pal;
	}	
	public void Palavras() {
		for (int i=0;i<palavras.size();i++) {
			String palavra=palavras.get(i);
			Procurar(palavra.toUpperCase());
		}
	}
	public void Procurar(String palavra) {
		for (int i=0;i<sopa.length;i++) {
			for (int z=0;z<sopa[i].length;z++) {
				if (sopa[i][z]==palavra.charAt(0)) {
					Baixo=Cima=Esquerda=Direita="";
					Baixo=Cima=Esquerda=Direita+=palavra.charAt(0);
					ProcurarDirecao(i,z,1,palavra,1);
					Solucao(palavra,i,z);
				}
			}
		}
	}
	
	public void ProcurarDirecao(int x,int y,int r,String palavra,int z){		
		if (r > 0 && r < palavra.length()){
			
			if (x+z < sopa.length && sopa[x+z][y]==palavra.charAt(r)) {
				Baixo+=palavra.charAt(r);
				
			}
			if (x-z >=0 && sopa[x-z][y]==palavra.charAt(r)) {
				Cima+=palavra.charAt(r);
				
		    }
			if (y+z < sopa.length && sopa[x][y+z]==palavra.charAt(r)) {
				Direita+=palavra.charAt(r);
				
		    }
			if (y-z >=0 && sopa[x][y-z]==palavra.charAt(r)) {
				Esquerda+=palavra.charAt(r);
				
		    }
			ProcurarDirecao(x,y,r+1,palavra,z+1);
			
		}
		
	}
	public void Solucao(String pal,int x,int y) {
		if (Cima.equals(pal)) {
			Resultado.add(pal+" "+pal.length()+" "+(x+1)+","+(y+1)+" up");
		}
		if (Baixo.equals(pal)) {
			Resultado.add(pal+" "+pal.length()+" "+(x+1)+","+(y+1)+" down");
		}
		if (Esquerda.equals(pal)) {
			Resultado.add(pal+" "+pal.length()+" "+(x+1)+","+(y+1)+" left");
		}
		if (Direita.equals(pal)) {
			Resultado.add(pal+" "+pal.length()+" "+(x+1)+","+(y+1)+" right");
		}
	}
	public ArrayList<String> getResolution() {
		return Resultado;
	}
}
