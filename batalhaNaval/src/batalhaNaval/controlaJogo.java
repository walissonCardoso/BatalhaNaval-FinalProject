package batalhaNaval;

import java.util.Random;

import javax.swing.JOptionPane;

public class controlaJogo {
	
	private static int pontuacao = 0;
	private static boolean vencendo;
	private static Tabuleiro tab = new Tabuleiro();
	private static Atacavel ataq1[] = new Atacavel[5];
	private static Atacavel ataq2[] = new Atacavel[5];
	private static GUI gui = new GUI();
	
	private static Random rand = new Random();
	
	public static void main(String[] args) {
		
		rand.setSeed(Data.time());
		
		gui.apresentarJogo();
		int opcao = gui.mostraMenu();
		
		while(opcao != 5){
			switch(opcao){
			case 1:
				modoTreino();
				break;
			case 2:
				modoArcade();
				break;
			case 3:
				gui.mostrarPontuacoes();
				break;
			case 4:
				gui.mostraAjuda();
				break;
			default:
				JOptionPane.showMessageDialog(null, "Um erro ocorreu durante a leitura de opção");
				System.exit(1);
			}
			opcao = gui.mostraMenu();
		}
	}
	
	public static void posicionarAtacaveis(){
		
		ataq1[0] = new CacaMinas();
		ataq1[1] = new Fragata();
		ataq1[3] = new Submarino();
		ataq1[2] = new Encouracado();
		ataq1[4] = new PortaAvioes();
		
		int x,y,orientacao;
		String auxOrient;
		boolean valido;
		
		gui.criaCampo(tab);
		
		for(int i = 0; i < 5; i++){
			orientacao = Integer.parseInt(JOptionPane.showInputDialog("Qual a orientação do " + ataq1[i].getNome() + "?"
					+ "\n1 - Horizontal"
					+ "\n2 - Vertical"));
			
			if(orientacao == 1) auxOrient = "horizontal";
			else auxOrient = "vertical";
			
			x = Integer.parseInt(JOptionPane.showInputDialog("Qual a coordenada horizontal?"));
			y = Integer.parseInt(JOptionPane.showInputDialog("Qual a coordenada vertical?"));
			
			valido = tab.colocarNavio(ataq1[i], x+10, y, auxOrient, i);

			if(!valido){
				JOptionPane.showMessageDialog(null, "Posição do " + ataq1[i].getNome() +" não éh valida");
				i--;
			}
			
			gui.criaCampo(tab);
		}
		
		ataq2[0] = new CacaMinas();
		ataq2[1] = new Fragata();
		ataq2[3] = new Submarino();
		ataq2[2] = new Encouracado();
		ataq2[4] = new PortaAvioes();
		tab.colocarNavio(ataq1[0],0,0, "horizontal", 0);
		tab.colocarNavio(ataq1[1],1,1, "horizontal", 1);
		tab.colocarNavio(ataq1[2],5,3, "vertical", 2);
		tab.colocarNavio(ataq1[3],4,7, "horizontal", 3);
		tab.colocarNavio(ataq1[4],9,5, "vertical", 4);
	}
	
	public static void modoTreino(){
		
		posicionarAtacaveis();
		int dificuldade = gui.selecionaDificuldade();
		
		switch(dificuldade){
		case 1:
			jogoFacil();
			break;
		case 2:
			jogoMedio();
			break;
		case 3:
			jogoDificil();
			break;
		}
	}
	
	public static void modoArcade(){
		
		pontuacao = 20;
		
		JOptionPane.showMessageDialog(null, "Você está em meio a uma grande batalha."
				+ "\nSuas unicas armas são suas embarcações e sua coragem!"
				+ "\nBoa sorte marujo, e que Deus tenha piedade de sua"
				+ "\nintrépida alma.");
		
		posicionarAtacaveis();
		
		JOptionPane.showMessageDialog(null, "O capitão Tanatus avista sua frota ao longe."
				+ "\nEstre cruel pirata não deixara a oportunidade"
				+ "\nde pilhagem passar em vão!"
				+ "\nPrepare-se, destemido capitão!");
		
		pontuacao += jogoFacil();
		
		if(vencendo){
			JOptionPane.showMessageDialog(null, "Parabéns! Mas esta batalha foi fácil."
					+ "\nA cobiça de Tanatus é tamanha que este velho"
					+ "\nLobo do mar não enxerga a suas chances nem quando"
					+ "\nesta prestes a afundar."
					+ "\n\nMas talvez o pior não sejam os piratas e suas"
					+ "\npobres pontarias, mas as forças armadas e suas"
					+ "\npulsantes máquinas de guerra!"
					+ "\nO pais de Argadácia é seu verdadeiro inimigo."
					+ "\nErga-se soldado, e defenda seu país, Lisarb!!");
			pontuacao += jogoMedio();
		}
		
		if(vencendo){
			JOptionPane.showMessageDialog(null, "As frotas não lhe darão discanço. Surge"
					+ "\no horizonte a derradeira tentativa de sucesso nos mares"
					+ "\npelo país de Argadácia. A tropa de elite dos mares, temida"
					+ "\npelos mais gélidos corações dos mares.");
			pontuacao += jogoDificil();
		}
		
		JOptionPane.showMessageDialog(null, "Lutaste com bravura e ânimo dignos de"
				+ "\nserem registrados nos livros, nobre capitão!!");
		
		gui.salvarPontuacao(pontuacao);
	}
	
	public static int jogoFacil(){
		vencendo = true;
		boolean valida;
		int x,y;
		
		while(vencendo){
			
			valida = false;
			JOptionPane.showMessageDialog(null,"Seu Turno de atacar!");
			while(!valida){
				x = Integer.parseInt(JOptionPane.showInputDialog("Qual a coordenada horizontal?"));
				y = Integer.parseInt(JOptionPane.showInputDialog("Qual a coordenada vertical?"));
				valida = tab.transferirDanos(ataq2, x, y);
				if(!valida)
					JOptionPane.showMessageDialog(null,"Posicao inválida para ataque!");
			}
			
			gui.criaCampo(tab);
			
			JOptionPane.showMessageDialog(null,"Agora é a vez do seu adversário");
			valida = false;
			while(!valida){
				x = Math.abs(rand.nextInt()) % 10;
				y = Math.abs(rand.nextInt()) % 10;
				valida = tab.transferirDanos(ataq1, x+10, y);
			}
			
			gui.criaCampo(tab);
			
			if(getNAtacaveisSaos(2) == 0){
				JOptionPane.showMessageDialog(null,"Você venceu!!");
				break;
			}else if(getNAtacaveisSaos(1) == 0){
				JOptionPane.showMessageDialog(null,"Você Perdeu.");
				vencendo = false;
			}
		}
		
		return getNAtacaveisSaos(1)*5; //Cada navio são 5 pontos
	}
	
	public static int jogoMedio(){
		return jogoFacil();
	}
	
	public static int jogoDificil(){
		return jogoMedio();
	}
	
	public static int getNAtacaveisSaos(final int jogador){
		int nAtacaveis = 0;
		if(jogador == 1){
			for(int i = 0; i < ataq1.length; i++)
				if(ataq1[i].funcionando()) nAtacaveis++;
		}else if(jogador == 2){
			for(int i = 0; i < ataq2.length; i++)
				if(ataq2[i].funcionando()) nAtacaveis++;
		}else
			JOptionPane.showMessageDialog(null, "Ops, este jogador não existe");
		return nAtacaveis;
	}
}
