
public class Dominio implements Comparable<Dominio> {
	private Lista<String> contenido;
	private Lista<String> funciones;
	private int costo;
	
	public Dominio(Lista<String> cont, Lista<String> func, int c) {
		this.contenido = cont;
		this.funciones = func;
		this.costo = c;
	}
	
	@SuppressWarnings("unchecked")
	public Dominio(MiLista<String> cont, MiLista<String> func, int c) {
		this.contenido = (Lista<String>) cont.clone();
		this.funciones = (Lista<String>) func.clone();
		this.costo = c;
	}
	
	public Dominio(String cont, String func, int c) {
		this.contenido.add(cont);
		this.funciones.add(func);
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
	
	public void agregarFuncion(String func) {
		this.funciones.add(func);
	}
	
	public Lista<String> getCont() {
		return this.contenido;
	}
	
	public Lista<String> getFunciones() {
		return this.funciones;
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