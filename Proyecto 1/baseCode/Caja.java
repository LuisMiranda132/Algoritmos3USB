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
	
	/**
	 * Funcion: obtenerCont
	 * Descripcion: Devuelve el contenido 
	 * de la caja.
	 * Precondicion: true
	 * Postcondicion: retorna this.cont
	 */
	public E obtenerCont() {
		return this.cont;
	}
	
	/**
	 * Funcion: obtenerCont
	 * Descripcion: Devuelve siguiente elemento 
	 * de la lista.
	 * Precondicion: true
	 * Postcondicion: retorna this.siguiente
	 */
	public Caja<E> obtenerSiguiente() {
		return this.siguiente;
	}
	
	/**
	 * Funcion: cambiarSiguiente
	 * Descripcion: Cambia el contenido 
	 * del siguiente nodo.
	 * Precondicion: true
	 * Postcondicion: this.siguiente = nuevSig
	 */
	public void cambiarSiguiente(Caja<E> nuevSig) {
		this.siguiente = nuevSig;
	}
	
	/**
	 * Funcion: toString
	 * Descripcion: Retorna la representacion 
	 * en string del contenido del nodo.
	 * Precondicion: true
	 * Postcondicion: retorna el string con la
	 * representacion del contenido de la caja. 
	 */
	public String toString() {
		String imprimir = ""; 
		imprimir = this.cont.toString();
		return imprimir;   
	}
}
