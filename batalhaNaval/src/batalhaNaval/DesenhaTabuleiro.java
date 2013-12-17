package batalhaNaval;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class DesenhaTabuleiro extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private Tabuleiro tab;
	private int sizeCelula;
	
	public DesenhaTabuleiro(Tabuleiro tab, final int sizeCelula){
		this.tab = tab;
		this.sizeCelula = sizeCelula;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//Lado da máquina
		g.setColor(Color.blue);
		for(int i = 0; i < tab.getLargura(); i++)
			for(int j = 0; j < tab.getAltura(); j++){
				if(tab.getCelula(i,j).getEstado() == "vazio" || tab.getCelula(i,j).getEstado() == "ocupado")
					g.fillRect((i+1)*sizeCelula, (j+1)*sizeCelula, sizeCelula, sizeCelula);
			}
		
		g.setColor(Color.red);
		for(int i = 0; i < tab.getLargura(); i++)
			for(int j = 0; j < tab.getAltura(); j++){
				if(tab.getCelula(i,j).getEstado() == "atingido")
					g.fillRect((i+1)*sizeCelula, (j+1)*sizeCelula, sizeCelula, sizeCelula);
			}
		
		g.setColor(Color.black);
		for(int i = 0; i < tab.getLargura(); i++)
			for(int j = 0; j < tab.getAltura(); j++){
				if(tab.getCelula(i,j).getEstado() == "atingido" && tab.getCelula(i,j).getPosNoAtacavel() != 0)
					g.fillRect((i+1)*sizeCelula, (j+1)*sizeCelula, sizeCelula, sizeCelula);
			}
		
		//Lado do jogador
		g.setColor(Color.blue);
		for(int i = 0; i < tab.getLargura(); i++)
			for(int j = 0; j < tab.getAltura(); j++){
				if(tab.getCelula(i+10,j).getEstado() == "vazio")
					g.fillRect((i+1)*sizeCelula, (j+14)*sizeCelula, sizeCelula, sizeCelula);
			}
		
		g.setColor(Color.red);
		for(int i = 0; i < tab.getLargura(); i++)
			for(int j = 0; j < tab.getAltura(); j++){
				if(tab.getCelula(i+10,j).getEstado() == "atingido")
					g.fillRect((i+1)*sizeCelula, (j+14)*sizeCelula, sizeCelula, sizeCelula);
			}
		
		g.setColor(Color.black);
		for(int i = 0; i < tab.getLargura(); i++)
			for(int j = 0; j < tab.getAltura(); j++){
				if(tab.getCelula(i+10,j).getEstado() == "ocupado")
					g.fillRect((i+1)*sizeCelula, (j+14)*sizeCelula, sizeCelula, sizeCelula);
			}
		
		
		
		g.drawRect(sizeCelula, sizeCelula, sizeCelula * 10, sizeCelula * 10);
		for (int i = 1; i < 10; i++) {
			g.drawLine((i + 1) * sizeCelula, sizeCelula, (i + 1) * sizeCelula,
					11 * sizeCelula);
			g.drawLine(sizeCelula, (i + 1) * sizeCelula, 11 * sizeCelula,
					(i + 1) * sizeCelula);
		}

		g.drawRect(sizeCelula, sizeCelula * 14, sizeCelula * 10,
				sizeCelula * 10);
		for (int i = 1; i < 10; i++) {
			g.drawLine((i + 1) * sizeCelula, 14 * sizeCelula, (i + 1)
					* sizeCelula, 24 * sizeCelula);
			g.drawLine(sizeCelula, (i + 14) * sizeCelula, 11 * sizeCelula,
					(i + 14) * sizeCelula);
		}
	}
}