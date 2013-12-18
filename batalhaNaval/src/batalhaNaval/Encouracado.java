package batalhaNaval;

import javax.swing.JOptionPane;

public class Encouracado extends Navio{

	
	
	public Encouracado() {
		super(4);
	}

	@Override
	public boolean ataqueEspecial() {
		int opcao = JOptionPane.showConfirmDialog(null,"O encouraçado, armamento pesado!"
				+ "\nPor 15 pontos o ataque especial dá"
				+ "\na oportunidade de atacar cinco casas"
				+ "em formato de uma cruz"
				+ "\nQuer fazer isso?");
		if(opcao == 1)
			return true;
		else
			return false;
	}

	@Override
	public void atacar() {
		JOptionPane.showMessageDialog(null, "\nO encouraçado atacará!");
	}

	@Override
	public String getNome() {
		return "Encouraçado";
	}

}
