package batalhaNaval;

public interface Atacavel{
	
	public abstract void atacar();
	public abstract boolean ataqueEspecial();
	public abstract void receberDano(final int pos);
	public abstract boolean funcionando();
	public abstract boolean anularAtaque();
	public abstract int getTamanho();
	public abstract String getNome();
}
