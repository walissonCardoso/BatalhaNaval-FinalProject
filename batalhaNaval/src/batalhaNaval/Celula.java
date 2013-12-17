package batalhaNaval;

import javax.swing.JOptionPane;

public class Celula {
	private String estado;
	private int posNoAtacavel;
	private int posNaLista;
	
	public Celula() {
		estado = "vazio";
		posNaLista = 0;
		posNoAtacavel = 0;
	}
	
	public void setPosNavio(final int posNaLista, final int posNoAtacavel){
		this.posNaLista = posNaLista;
		this.posNoAtacavel = posNoAtacavel;
		this.estado = "ocupado";
	}
	
	public void setEstado(final String estado){
		if(estado == "ocupado" || estado == "vazio" || estado == "atingido")
			this.estado = estado;
		else
			JOptionPane.showMessageDialog(null, "Erro! estado inválido");
	}

	public String getEstado() {
		return estado;
	}

	public int getPosNoAtacavel() {
		return posNoAtacavel;
	}

	public int getPosNaLista() {
		return posNaLista;
	}
}
