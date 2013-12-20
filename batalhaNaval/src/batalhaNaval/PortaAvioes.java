package batalhaNaval;

import javax.swing.JOptionPane;

public class PortaAvioes extends Navio{


	public PortaAvioes() {
		super(5);
	}

	@Override
	public boolean ataqueEspecial() {
		int opcao = JOptionPane.showConfirmDialog(null,"O porta-avi�es e seus ca�as."
				+ "\nPor 20 pontos o ataque especial d�"
				+ "\na oportunidade de atacar quatro vezes."
				+ "\nQuer fazer isso?");
		if(opcao == 0)
			return true;
		else
			return false;
	}

	@Override
	public void atacar() {
		JOptionPane.showMessageDialog(null, "\nO porta-avi�es atacar�!");
	}

	@Override
	public String getNome() {
		return "Porta Avi�es";
	}

	@Override
	public int getPontosAtaqueEspecial() {
		return 20;
	}

}
