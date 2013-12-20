package batalhaNaval;

import javax.swing.JOptionPane;

public class PortaAvioes extends Navio{


	public PortaAvioes() {
		super(5);
	}

	@Override
	public boolean ataqueEspecial() {
		int opcao = JOptionPane.showConfirmDialog(null,"O porta-aviões e seus caças."
				+ "\nPor 20 pontos o ataque especial dá"
				+ "\na oportunidade de atacar quatro vezes."
				+ "\nQuer fazer isso?");
		if(opcao == 0)
			return true;
		else
			return false;
	}

	@Override
	public void atacar() {
		JOptionPane.showMessageDialog(null, "\nO porta-aviões atacará!");
	}

	@Override
	public String getNome() {
		return "Porta Aviões";
	}

	@Override
	public int getPontosAtaqueEspecial() {
		return 20;
	}

}
