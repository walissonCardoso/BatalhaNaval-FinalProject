package batalhaNaval;

import javax.swing.JOptionPane;

public class Fragata extends Navio{

	public Fragata() {
		super(2);
	}

	@Override
	public boolean ataqueEspecial() {
		int opcao = JOptionPane.showConfirmDialog(null,"A fragata e seus canhões laterais!"
				+ "\nPor 15 pontos o ataque especial dá"
				+ "\na capacidade de atacar cinco casas"
				+ "\ncontiguas horizontais"
				+ "\nQuer fazer isso?");
		if(opcao == 0)
			return true;
		else
			return false;
	}

	@Override
	public void atacar() {
		JOptionPane.showMessageDialog(null, "\nA Fragata atacará!");
	}

	@Override
	public String getNome() {
		return "Fragata";
	}

	@Override
	public int getPontosAtaqueEspecial() {
		return 15;
	}

}
