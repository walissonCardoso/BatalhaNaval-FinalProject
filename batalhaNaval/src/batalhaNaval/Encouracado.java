package batalhaNaval;

import javax.swing.JOptionPane;

public class Encouracado extends Navio{

	
	
	public Encouracado() {
		super(4);
	}

	@Override
	public boolean ataqueEspecial() {
		int opcao = JOptionPane.showConfirmDialog(null,"O encoura�ado, armamento pesado!"
				+ "\nPor 15 pontos o ataque especial d�"
				+ "\na oportunidade de atacar cinco casas"
				+ "\nem formato de uma cruz"
				+ "\nQuer fazer isso?");
		if(opcao == 0)
			return true;
		else
			return false;
	}

	@Override
	public void atacar() {
		JOptionPane.showMessageDialog(null, "\nO encoura�ado atacar�!");
	}

	@Override
	public String getNome() {
		return "Encoura�ado";
	}

	@Override
	public int getPontosAtaqueEspecial() {
		return 15;
	}

}
