public class Caja<E> {
	
	protected E cont;
	protected Caja<E> siguiente;
	
	/*
	 * Constructor
	 */
	public Caja(E e,Caja<E> sig) {
		this.cont = e;
		this.siguiente = sig;
	}
	
	/* 
	 * Metodos para el nodo 
	 */
	
	/*
	 * Devuelve el contenido de la caja.
	 */
	public E obtenerCont() {
		return cont;
	}
	
	/*
	 * Devuelve el siguiente elemento de la lista.
	 */
	public Caja<E> obtenerSiguiente() {
		return this.siguiente;
	}
	
	/*
	 * Cambia el contenido del siguiente nodo.
	 */
	public void cambiarSiguiente(Caja<E> nuevSig) {
		this.siguiente = nuevSig;
	}
	
	/*
	 * Retorna la representacion en string del contenido del nodo. 
	 */
	public String toString() {
		String imprimir = ""; 
		imprimir = this.cont.toString();
		return imprimir;
	}

}
