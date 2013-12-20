package batalhaNaval;

import javax.swing.JOptionPane;

public abstract class Navio implements Defensavel {
	
	
	private int nPosicoesSas;
	private boolean posSadias[];
	private int tamanho;
	
	public Navio(final int tamanho){		
		this.tamanho = tamanho;
		this.posSadias = new boolean[tamanho];
		for(int i = 0; i < tamanho; i++)
			this.posSadias[i] = true;
		this.nPosicoesSas = tamanho;
	}
	
	
	@Override
	public abstract boolean ataqueEspecial();
	
	@Override
	public abstract void atacar();

	@Override
	public void receberDano(final int pos) {
		this.posSadias[pos] = false;
		this.nPosicoesSas--;
	}

	@Override
	public boolean funcionando() {
		if(nPosicoesSas > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean anularAtaque() {
		int opcao = JOptionPane.showConfirmDialog(null, "Voce será acertado por um disparo!"
					+ "\nPor sorte, seus abatedores estão a postos."
					+ "\nPor 20 pontos você pode usar a artilharia para abater o projétil."
					+ "\nDeseja fazer isso?");
		
		if(opcao == 1)
			return true;
		else
			return false;
	}
	
	@Override
	public int getTamanho() {
		return tamanho;
	}

}
