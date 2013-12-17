package batalhaNaval;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class GUI {
	
	private JFrame campo;
	private int sizeCelula = 20;
	
	public GUI(){
		
	}
	
	public void apresentarJogo(){
		JOptionPane.showMessageDialog(null,"Ol� capit�o!"
				+ "\n\nPreparado para"
				+ " mais uma batalha?"
				+ "\nNossos inimigos n�o est� para brincadeiras."
				+ "\nAperte o bot�o e nos ajude nessa!!");
	}
	
	public int mostraMenu(){
		int escolha = 0;
		while(escolha < 1 || escolha > 5){
			
			escolha = Integer.parseInt(JOptionPane.showInputDialog("Escolha uma das op��es abaixo:"
				+ "\n\n1 - Modo de treino"
				+ "\n2 - Modo arcade"
				+ "\n3 - Mostrar melhores pontua��es"
				+ "\n4 - Ajuda"
				+ "\n5 - Sair"));
		}
		return escolha;
	}
	
	public void mostraAjuda(){
		
	}
	
	public void criaCampo(Tabuleiro tab){
		campo = new JFrame();
		
		campo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		campo.setSize(13*sizeCelula, 29*sizeCelula);
		campo.setVisible(true);
		
		DesenhaTabuleiro desLi = new DesenhaTabuleiro(tab,sizeCelula);
		campo.add(desLi);
	}
	
	public void atualizaCampo(Tabuleiro tab){
		
	}
	
	public int selecionaDificuldade(){
		int dificuldade = 0;
		while(dificuldade <= 0 || dificuldade > 3)
			dificuldade = Integer.parseInt(JOptionPane.showInputDialog("Selecione a dificuldade:"
					+ "\n1 - F�cil"
					+ "\n2 - M�dio"
					+ "\n3 - Dif�cil"));
		
		return dificuldade;
	}
	
	public void mostrarPontuacoes(){
		JOptionPane.showMessageDialog(null, "Ranking"
				+ "\n\n1 - AAA"
				+ "\n2 - Walisson");//TODO ler isso de um arquivo
	}
	
	public void salvarPontuacao(final int pontuacao){
		//TODO salvar em arquivo
	}
}
