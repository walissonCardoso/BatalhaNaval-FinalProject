package batalhaNaval;

import javax.swing.JOptionPane;

public class Tabuleiro {

	private final int largura = 10;
	private final int altura = 10;
	private int nNaviosPlay1;
	private int nNaviosPlay2;
	Celula cel[][];
	
	public Tabuleiro() {
		cel = new Celula[2*altura][largura];
		for(int i = 0; i < 2*altura; i++)
			for(int j = 0; j < largura; j++)
				cel[i][j] = new Celula();
	}
	
	public boolean colocarNavio(Atacavel ataq, final int x, final int y, final String orientacao, final int posNaLista){
		
		if(x < 0 || y < 0 || x > 2*largura || y > altura ){
			return false;
		}else if(orientacao == "horizontal" && x + ataq.getTamanho() > 2*largura){
			return false;
		}else if(orientacao == "vertical" && y + ataq.getTamanho() > altura){
			return false;
		}
		int incx = 0, incy = 0;
		if(orientacao == "horizontal")
			incx = 1;
		else
			incy = 1;
		
		for(int i = 0; i < ataq.getTamanho(); i++)
			if(cel[x+i*incx][y+i*incy].getEstado() != "vazio")
				return false;
		
		for(int i = 0; i < ataq.getTamanho(); i++)
			cel[x+i*incx][y+i*incy].setPosNavio(posNaLista,i+1);;
		return true;
	}
	
	public boolean transferirDanos(Atacavel ataq[], final int x, final int y){
		
		if(x < 0 || y < 0 || x >= 2*altura || y >= 2*largura)
			return false;
		
		if(cel[x][y].getEstado() == "atingido")
			return false;
		
		int number = cel[x][y].getPosNaLista(),
				pos = cel[x][y].getPosNoAtacavel();
		
		if(cel[x][y].getEstado() == "ocupado")
			ataq[number].receberDano(pos-1);
		
		cel[x][y].setEstado("atingido");
		
		return true;
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
}
