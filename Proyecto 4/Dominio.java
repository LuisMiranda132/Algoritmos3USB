
public class Dominio implements Comparable<Dominio> {
	private Lista<String> contenido;
	private Lista<String> funcionesAbiertas;
	private Lista<String> funcionesRecorridas;
	private int costo;
	
	public Dominio(Lista<String> cont, Lista<String> funcA,
			Lista<String> funcR, int c) {
		this.contenido = cont;
		this.funcionesAbiertas = funcA;
		this.funcionesRecorridas = funcR;
		this.costo = c;
	}
	
	@SuppressWarnings("unchecked")
	public Dominio(MiLista<String> cont, MiLista<String> funcA,
			MiLista<String> funcR, int c) {
		this.contenido = (Lista<String>) cont.clone();
		this.funcionesAbiertas = (Lista<String>) funcA.clone();
		this.funcionesRecorridas = (Lista<String>) funcR.clone();
		this.costo = c;
	}
	
	public Dominio(String cont, String funcA, String funcR, int c) {
		this.contenido.add(cont);
		this.funcionesAbiertas.add(funcA);
		this.funcionesRecorridas.add(funcR);
		this.costo = c;
	}
	
	public int compareTo(Dominio d) {
		if(this.costo > d.costo){
			return 1;
		}else if (this.costo < d.costo){
			return -1;
		}
		return 0;
	}
	
	public void agregarCont(String cont) {
		this.contenido.add(cont);
	}
	
	public void agregarFuncionAb(String funcA) {
		this.funcionesAbiertas.add(funcA);
	}
	
	public void agregarFuncionRec(String funcR) {
		this.funcionesRecorridas.add(funcR);
	}
	
	public Lista<String> getCont() {
		return this.contenido;
	}
	
	public Lista<String> getFuncionesAb() {
		return this.funcionesAbiertas;
	}
	
	public Lista<String> getFuncionesRec() {
		return this.funcionesRecorridas;
	}
	
	public int getCosto() {
		return this.costo;
	}
	
	public void setCosto(int c) {
		this.costo = c;
	}
	
	public String toString() {
		return this.costo+" ";
	}
	
}