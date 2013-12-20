package batalhaNaval;

import javax.swing.JOptionPane;

public class Submarino implements Defensavel{
	
	private final int tamanho = 3;
	private int nPosicoesSas;
	private boolean posSadias[];
	
	public Submarino() {
		this.posSadias = new boolean[tamanho];
		for(int i = 0; i < tamanho; i++)
			this.posSadias[i] = true;
		this.nPosicoesSas = tamanho;
	}
	
	@Override
	public void atacar() {
		JOptionPane.showInternalMessageDialog(null,"Disparar Torpedo!!");
	}
	
	@Override
	public boolean ataqueEspecial() {
		int opcao = JOptionPane.showConfirmDialog(null,"Submarino, arma silenciosa!"
				+ "\nPor 35 pontos o ataque especial dá a"
				+ "\noportunidade de atacar uma coluna inteira"
				+ "\nQuer fazer isso?");
		if(opcao == 0)
			return true;
		else
			return false;
	}
	
	@Override
	public void receberDano(final int pos) {
		this.posSadias[pos] = false;
		this.nPosicoesSas--;
	}
	
	@Override
	public boolean anularAtaque() {
		return false;
	}
	
	protected boolean ataqueResvalado(){
		return false;
	}

	@Override
	public boolean funcionando() {
		if(nPosicoesSas > 0)
			return true;
		else
			return false;
	}
	
	@Override
	public int getTamanho() {
		return tamanho;
	}

	@Override
	public String getNome() {
		return "Submarino";
	}

	@Override
	public int getPontosAtaqueEspecial() {
		return 35;
	}
}
