package batalhaNaval;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class GUI {
	
	private JFrame campo;
	private int sizeCelula = 20;
	
	public GUI(){
		
	}
	
	public void apresentarJogo(){
		JOptionPane.showMessageDialog(null,"Olá capitão!"
				+ "\n\nPreparado para"
				+ " mais uma batalha?"
				+ "\nNossos inimigos não estã para brincadeiras."
				+ "\nAperte o botão e nos ajude nessa!!");
	}
	
	public int mostraMenu(){
		int escolha = 0;
		while(escolha < 1 || escolha > 5){
			
			escolha = Integer.parseInt(JOptionPane.showInputDialog("Escolha uma das opções abaixo:"
				+ "\n\n1 - Modo de treino"
				+ "\n2 - Modo arcade"
				+ "\n3 - Mostrar melhores pontuações"
				+ "\n4 - Ajuda"
				+ "\n5 - Sair"));
		}
		return escolha;
	}
	
	public void mostraAjuda(){
		JOptionPane.showMessageDialog(null,"Jogar é bastante símples."
				+ "\nA numeração dos menus ao lado sempre vai fornecer"
				+ "\no número para uma opção bem delineada, assim"
				+ "\ncomo você chegou nesta ajuda que agora lê."
				+ "\n\nComeçando o jogo, você deve posicionar seus navios."
				+ "\nA parte de cima da tela é o campo do seu inimigo, logo"
				+ "\nvocê não deve poder ver a posição dos navios."
				+ "\n\nPara posicionar os seus objetos antes do jogo basta"
				+ "\ndeclarar o orientação dele (se ficará na horizontal ou"
				+ "\nvertical) e depois inserir as coordenadas da primeira"
				+ "\ncélula da embarcação. Note que a numeração de células "
				+ "\ncomeça em zero e vai até dez. Se horizontal, o objeto"
				+ "\ndeve caber inteiro na tela de dez posições começando"
				+ "\nda casa por você escolhida na posição horizontal. O"
				+ "\nmesmo vale para a vertical."
				+ "\n\nApós posicionar os cinco objetos, comece disparando"
				+ "\nnas coordenadas que correspondentes do seu adversário e"
				+ "\ntente acertar as cinco embarcações ocultas."
				+ "\n\nO jogo acaba quando algum jogador perder seus cinco"
				+ "\nobjetos."
				+ "\n\nNote que quando um disparo for certeiro, uma posicao"
				+ "\nda embarcação adversária será mostrada. Ache as demais"
				+ "\npara afundá-la. Todas as embarcações precisam ter todas as"
				+ "\nposições atingidas para afundar.");
	}
	
	public void criaCampo(Tabuleiro tab){
		
		//if(campo != null)
		//	campo.setVisible(false);
		if(campo == null)
		campo = new JFrame();
		
		
		campo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		campo.setSize(13*sizeCelula, 29*sizeCelula);
		campo.setVisible(true);
		
		DesenhaTabuleiro desLi = new DesenhaTabuleiro(tab,sizeCelula);
		campo.add(desLi);
		campo.repaint();
	}
	
	public void atualizaCampo(Tabuleiro tab){
		
	}
	
	public int selecionaDificuldade(){
		int dificuldade = 0;
		while(dificuldade <= 0 || dificuldade > 3)
			dificuldade = Integer.parseInt(JOptionPane.showInputDialog("Selecione a dificuldade:"
					+ "\n1 - Fácil"
					+ "\n2 - Médio"
					+ "\n3 - Difícil"));
		
		return dificuldade;
	}
	
	public void mostrarPontuacoes(){
		BufferedReader arqPont = null;
		try{
			arqPont = new BufferedReader(new FileReader("Pontuacoes.txt"));
		}catch(IOException e){ e.printStackTrace(); }
		
		String aux = null, lista = "";
		try{
			aux = arqPont.readLine();
		}catch(IOException e){ e.printStackTrace(); }
		
		while(aux != null){
			lista = lista + "\n" + aux;
			try{
				aux = arqPont.readLine();
			}catch(IOException e){ e.printStackTrace(); }
		}
		
		try {
			arqPont.close();
		} catch (IOException e) {e.printStackTrace();}
		
		JOptionPane.showMessageDialog(null, "Pontuacoes:" + "\n\n" + lista);
	}
	
	public void salvarPontuacao(final int pontuacao){
		
		String nome = JOptionPane.showInputDialog("Qual seu nome?");
		
		BufferedWriter arqPont = null;
		try{
			arqPont = new BufferedWriter(new FileWriter("Pontuacoes.txt",true));
		}catch(IOException e){ e.printStackTrace(); }
		
		try{
			arqPont.write("\r\n" + Data.hoje() + " " + nome + " - " + pontuacao);
		}catch(IOException e){ e.printStackTrace(); }
		
		try {
			arqPont.close();
		} catch (IOException e) {e.printStackTrace();}
	}
}
