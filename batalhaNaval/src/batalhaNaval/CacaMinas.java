package batalhaNaval;

import javax.swing.JOptionPane;

public class CacaMinas extends Navio {

	public CacaMinas() {
		super(1);
	}

	@Override
	public boolean ataqueEspecial() {
		int opcao = JOptionPane.showConfirmDialog(null,"O caca-minas eh rápido!"
				+ "\nPor 5 pontos o ataque especial dá"
				+ "\na oportunidade de atacar duas vezes."
				+ "\nQuer fazer isso?");
		if(opcao == 1)
			return true;
		else
			return false;
	}

	@Override
	public void atacar() {
		JOptionPane.showMessageDialog(null, "\nO caça-minas atacará!");
	}

	@Override
	public String getNome() {
		return "caca-minas";
	}

}
