package batalhaNaval;

import java.util.Random;

import javax.swing.JOptionPane;

public class controlaJogo {
	
	private static int pontuacao = 0;
	private static boolean vencendo;
	private static int altura = 10;
	private static int largura = 10;
	private static Tabuleiro tab = new Tabuleiro(altura,largura);
	private static int nDefensaveis = 5;
	private static Defensavel def1[] = new Defensavel[nDefensaveis];
	private static Defensavel def2[] = new Defensavel[nDefensaveis];
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
	
	public static void modoTreino(){
		
		pontuacao = 0;
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
		int x = 0,y = 0;
		
		posicionarDefensaveis();
		gui.criaCampo(tab);
		
		while(vencendo){
			
			valida = false;
			JOptionPane.showMessageDialog(null,"Seu Turno de atacar!");
			while(!valida){
				x = Integer.parseInt(JOptionPane.showInputDialog("Qual a coordenada horizontal?"));
				y = Integer.parseInt(JOptionPane.showInputDialog("Qual a coordenada vertical?"));
				valida = tab.transferirDanos(def2, x, y, pontuacao);
				if(!valida)
					JOptionPane.showMessageDialog(null,"Posicao inválida para ataque!");
			}
			pontuacao = pontuacao - tab.getReduzPontos();
			
			gui.criaCampo(tab);
			
			JOptionPane.showMessageDialog(null,"Agora é a vez do seu adversário");
			valida = false;
			while(!valida){
				x = Math.abs(rand.nextInt()) % altura;
				y = Math.abs(rand.nextInt()) % largura;
				valida = tab.transferirDanos(def1, x+altura, y, pontuacao);
			}
			pontuacao = pontuacao - tab.getReduzPontos();
			
			gui.criaCampo(tab);
			
			if(getNDefensaveisSaos(2) == 0){
				JOptionPane.showMessageDialog(null,"Você venceu!!");
				break;
			}else if(getNDefensaveisSaos(1) == 0){
				JOptionPane.showMessageDialog(null,"Você Perdeu.");
				vencendo = false;
			}
		}
		
		gui.resetFrame();
		return getNDefensaveisSaos(1)*5; //Cada navio são 5 pontos
	}
	
	public static int jogoMedio(){
		return jogoFacil();
	}
	
	public static int jogoDificil(){
		return jogoMedio();
	}
	
	public static void posicionarDefensaveis(){
		
		tab = new Tabuleiro(altura,largura);
		
		def1[0] = new CacaMinas();
		def1[1] = new Fragata();
		def1[3] = new Submarino();
		def1[2] = new Encouracado();
		def1[4] = new PortaAvioes();
		
		int x,y,orientacao;
		String auxOrient;
		boolean valido;
		
		gui.criaCampo(tab);
		
		for(int i = 0; i < nDefensaveis; i++){
			orientacao = Integer.parseInt(JOptionPane.showInputDialog("Qual a orientação do " + def1[i].getNome() + "?"
					+ "\n1 - Horizontal"
					+ "\n2 - Vertical"));
			
			if(orientacao == 1) auxOrient = "horizontal";
			else auxOrient = "vertical";
			
			x = Integer.parseInt(JOptionPane.showInputDialog("Qual a coordenada horizontal?"));
			y = Integer.parseInt(JOptionPane.showInputDialog("Qual a coordenada vertical?"));
			
			valido = tab.colocarNavio(def1[i], x+10, y, auxOrient, i);

			if(!valido){
				JOptionPane.showMessageDialog(null, "Posição do " + def1[i].getNome() +" não éh valida");
				i--;
			}
			
			gui.criaCampo(tab);
		}
		
		def2[0] = new CacaMinas();
		def2[1] = new Fragata();
		def2[3] = new Submarino();
		def2[2] = new Encouracado();
		def2[4] = new PortaAvioes();
		
		
		for(int i = 0; i < 5; i++){
			valido = false;
			
			while(!valido){
				orientacao = Math.abs(rand.nextInt())%2;
				if(orientacao == 1) auxOrient = "horizontal";
				else auxOrient = "vertical";
				x = Math.abs(rand.nextInt()) % altura;
				y = Math.abs(rand.nextInt()) % largura;
				valido = tab.colocarNavio(def2[i], x, y, auxOrient, i);
			}
			
			
		}
	}
	
	public static int getNDefensaveisSaos(final int jogador){
		int nDefensaveis = 0;
		if(jogador == 1){
			for(int i = 0; i < def1.length; i++)
				if(def1[i].funcionando()) nDefensaveis++;
		}else if(jogador == 2){
			for(int i = 0; i < def2.length; i++)
				if(def2[i].funcionando()) nDefensaveis++;
		}else
			JOptionPane.showMessageDialog(null, "Ops, este jogador não existe");
		return nDefensaveis;
	}
}
