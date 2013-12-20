package batalhaNaval;

import javax.swing.JOptionPane;


public class Tabuleiro {

	private final int largura;
	private final int altura;
	private int reduzPont;
	Celula cel[][];
	
	public Tabuleiro(final int altura, final int largura) {
		if(altura > 5 && largura > 5){
			this.altura = altura;
			this.largura = largura;
		}else{
			this.altura = 10;
			this.largura = 10;
		}
		
		reduzPont = 0;
		cel = new Celula[2*altura][largura];
		for(int i = 0; i < 2*altura; i++)
			for(int j = 0; j < largura; j++)
				cel[i][j] = new Celula();
	}
	
	public boolean colocarNavio(Defensavel def, final int x, final int y, final String orientacao, final int posNaLista){
		
		if(x < 0 || y < 0 || x >= 2*largura || y >= altura ){
			return false;
		}else if(orientacao == "horizontal" && x + def.getTamanho() >= 2*altura){
			return false;
		}else if(orientacao == "vertical" && y + def.getTamanho() >= largura){
			return false;
		}
		int incx = 0, incy = 0;
		if(orientacao == "horizontal")
			incx = 1;
		else
			incy = 1;
		
		for(int i = 0; i < def.getTamanho(); i++)
			if(cel[x+i*incx][y+i*incy].getEstado() != "vazio" || x/10 != (x+i*incx)/10)
				return false;
		
		for(int i = 0; i < def.getTamanho(); i++)
			cel[x+i*incx][y+i*incy].setPosNavio(posNaLista,i+1);;
		return true;
	}
	
	public boolean transferirDanos(Defensavel def[], final int x, final int y, final int pontuacao){
		
		reduzPont = 0;
		
		if(x == -1 && y == -1)
			if(ataqEspecial(def,pontuacao) == true)
				return true;
		
		if(x < 0 || y < 0 || x >= 2*altura || y >= largura)
			return false;
		
		if(cel[x][y].getEstado() == "atingido")
			return false;
		
		int number = cel[x][y].getPosNaLista(),
				pos = cel[x][y].getPosNoAtacavel();
		
		if(cel[x][y].getEstado() == "ocupado"){
			if(pontuacao >= 20 && x >= altura){ //Segundo campo
				int resp = JOptionPane.showConfirmDialog(null,"Você será atingido na posicao "
					+ "(" + (x - altura) + "," + y + ")"
					+ "\n\nVocê pode evitar isso sacrificando 20 pontos."
					+ "\nDeseja fazer isso?");
				if(resp == 0){
					reduzPont = 20;
					return true;
				}
			}
			def[number].receberDano(pos-1);
		}
		
		cel[x][y].setEstado("atingido");
		
		return true;
	}
	
	protected boolean ataqEspecial(Defensavel def[], final int pontuacao){
		
		String mensagem = "Qual o objeto que você pretende usar?\n";
		for(int i = 0; i < def.length; i++)
			mensagem += "\n " + (i+1) + " - " + def[i].getNome();
		
		int escolha = 0;
		while(escolha < 1 || escolha > def.length)
			escolha = Integer.parseInt(JOptionPane.showInputDialog(mensagem));
		escolha--;
		
		boolean usar = false;
		if(def[escolha].funcionando() == false){
			JOptionPane.showMessageDialog(null,"Que pena! o navio não funciona mais!");
			return false;
		}else if(def[escolha].getPontosAtaqueEspecial() > pontuacao){
			JOptionPane.showMessageDialog(null,"Você não tem pontos suficientes");
			return false;
		}else{
			usar = def[escolha].ataqueEspecial();
			if(!usar) return false;
		}
		
		int x = Integer.parseInt(JOptionPane.showInputDialog("Qual a coordenada horizontal?"));
		int y = Integer.parseInt(JOptionPane.showInputDialog("Qual a coordenada vertical?"));
		
		if(def[escolha] instanceof CacaMinas){
			transferirDanos(def, x, y, pontuacao);
			x = Integer.parseInt(JOptionPane.showInputDialog("Qual a coordenada horizontal?"));
			y = Integer.parseInt(JOptionPane.showInputDialog("Qual a coordenada vertical?"));
			transferirDanos(def, x, y, pontuacao);
			reduzPont = 5;
			return true;
		} else if(def[escolha] instanceof Fragata){
			for(int i = x-2; i <= x+2; i++)
				transferirDanos(def, i, y, pontuacao);
			reduzPont = 15;
			return true;
		} else if(def[escolha] instanceof Submarino){
			for(int j = 0; j < altura; j++)
				transferirDanos(def, x, j, pontuacao);
			reduzPont = 35;
			return true;
		} else if(def[escolha] instanceof PortaAvioes){
			transferirDanos(def, x, y, pontuacao);
			for(int i = 0; i < 3; i++){
				x = Integer.parseInt(JOptionPane.showInputDialog("Qual a coordenada horizontal?"));
				y = Integer.parseInt(JOptionPane.showInputDialog("Qual a coordenada vertical?"));
				transferirDanos(def, x, y, pontuacao);
			}
			reduzPont = 20;
			return true;
		} else if(def[escolha] instanceof Encouracado){
			transferirDanos(def, x-1, y, pontuacao);
			transferirDanos(def, x+1, y, pontuacao);
			transferirDanos(def, x, y, pontuacao);
			transferirDanos(def, x, y-1, pontuacao);
			transferirDanos(def, x, y+1, pontuacao);
			reduzPont = 15;
			return true;
		}
		
		return false;
	}
	
	public int getAltura(){
		return altura;
	}
	
	public int getLargura(){
		return altura;
	}
	
	public Celula getCelula(final int x, final int y){
		return cel[x][y];
	}
	
	public int getReduzPontos(){
		return reduzPont;
	}
}
