/**
 * Clase que implementa la interfaz List
 * Esta es una clase parametrizada con tipo (clase) E; i.e., la
 * lista contiene elementos de tipo E.
 */
public class MiLista<E> implements Lista<E>{

    /*
     * Modelo de representacion: lista simplemente enlazada 
     * con un centinela.
     * La primera "caja" esta vacia.
     *
     * http://es.wikipedia.org/wiki/Lista_(inform�tica)#Nodos_Centinelas
     */
	
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
			return this.Siguiente;
		}
		
		/*
		 * Cambia el contenido del siguiente nodo.
		 */
		public void cambiarSiguiente(Caja<E> nuevSig) {
			this.Siguiente = nuevSig;
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
	
	protected Caja<E> primero;
	protected Caja<E> ultimo;
	protected int tam;

    /*
     * Constructor
     */
    public MiLista() {
    	this.primero = null;
    	this.ultimo = null;
    	this.tam = 0;
    }

    /**
     * Agrega un elemento al final de la lista.
     */
    public boolean add(E element) {
    	boolean loLogre = false;
    	if (tam = 0) {
    		this.primero = element;
    		this.ultimo = element;
    		this.tam ++;
    		loLogre = true;
    	}
    	else if (tam != 0) {
    		Caja<E> nueva = new Caja<E>(element,null);
    		this.ultimo.siguiente = nueva;
    		this.ultimo = nueva;
    		loLogre = true;
    	}
    	return loLogre;
    }

    /**
     * Elimina todos los elementos de la lista. La lista queda
     * como recien creada.
     */
    public void clear(){
	throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Determina si el elemento dado esta en la lista.
     */
    public boolean contains(Object element){
    	boolean encontre = false;
    	Caja<E> aux = this.primero;
    	E elemAux = aux.obtenerCont();
    	
    	while ((aux != null) && (!encontre)) {
    		encontre = (elemAux == elem);
    		aux = aux.obtenerSiguiente();
    		if (aux != null) {
    			elemAux = aux.obtenerCont();
    		}
    	}
    	
    	return encontre;
    }

    /**
     * Determina si la lista dada es igual a la lista.
     */
    public boolean equals(Lista<E> lista){
	throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Determina si la lista es vacia.
     */
    public boolean isEmpty(){
	throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Elimina el elemento dado de la lista. Si la lista cambia,
     * retorna true, sino retorna false.
     */
    public boolean remove(E element){
	throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Retorna el numero de elementos en la lista
     */
    public int getSize(){
	throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Retorna un arreglo que contiene todos los elementos
     * en esta lista {@code MiLista}.
     *
     * @return an array of the elements from this {@code MiLista}.
     */

    public Object[] toArray() {
	throw new UnsupportedOperationException("Not supported yet.");	
    }

}

// End List.
