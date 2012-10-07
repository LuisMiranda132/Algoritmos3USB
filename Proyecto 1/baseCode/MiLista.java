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
    	Caja<E> nueva = new Caja<E>(element,null);
    	
    	if (tam == 0) {
    		this.primero = nueva;
    		this.ultimo = nueva;
    		loLogre = true;
    	}
    	else if (tam != 0) {
//    		Caja<E> aux;
//    		aux = this.ultimo.obtenerSiguiente();
//    		aux = nueva;
    		this.ultimo.cambiarSiguiente(nueva);
    		this.ultimo = nueva;
    		loLogre = true;
    	}
    	
    	this.tam ++;
    	return loLogre;
    }

    /**
     * Elimina todos los elementos de la lista. La lista queda
     * como recien creada.
     */
    public void clear(){
    	this.primero = null;
    	this.ultimo = null;
    	this.tam = 0;
    }

    /**
     * Determina si el elemento dado esta en la lista.
     */
    public boolean contains(Object element){
    	if (this.tam == 0) {
    		return false;
    	}
    	else {
    		boolean encontre = false;
    		Caja<E> aux = this.obtenerPrimero();
    		E elemAux;
    		if (aux == null) {
    			return false;
    		}
    		else {
    			elemAux = aux.obtenerCont();
    		}
        
    		while ((aux != null) && (!encontre)) {
    			encontre = (elemAux.equals(element));
    			aux = aux.obtenerSiguiente();
    			if (aux != null) {
    				elemAux = aux.obtenerCont();
    			}
    		}
    	   	
    		return encontre;
    	}
    }
    
    /*
     * Devuelve el primero de la lista
     */
    public Caja<E> obtenerPrimero() {
    	return this.primero;
    }

    /**
     * Determina si la lista dada es igual a la lista.
     */
    public boolean equals(Lista<E> lista){
    	boolean igual = true;
    	
    	if (this.getSize() == lista.getSize()) {
    		Caja<E> aux = this.obtenerPrimero();
        	Caja<E> aux2 = lista.obtenerPrimero();
        	E elemAux = aux.obtenerCont();
        	E elemAux2 = aux2.obtenerCont();
        	igual = (elemAux.equals(elemAux2));
        	
        	while ((aux.obtenerSiguiente() != null) && (igual)) {
        		aux = aux.obtenerSiguiente();
        		aux2 = aux2.obtenerSiguiente();
        		elemAux = aux.obtenerCont();
        		elemAux2 = aux2.obtenerCont();
        		igual = (elemAux.equals(elemAux2));
         	}
    	}
    	else {
    		igual = false;
    	}
    	
    	return igual;
    }

    /**
     * Determina si la lista es vacia.
     */
    public boolean isEmpty(){
    	return (this.tam == 0);
    }

    /**
     * Elimina el elemento dado de la lista. Si la lista cambia,
     * retorna true, sino retorna false.
     */
    public boolean remove(E element){
    	boolean existe = this.contains(element);
    	
    	if (existe) {
    		Caja<E> aux = this.primero;
    		Caja<E> ant = null;
    		Caja<E> sig = null;
    		E elemAux = aux.obtenerCont();
    		
    		while ((aux != null) && !(elemAux.equals(element))) {
    			ant = aux;
    			aux = aux.obtenerSiguiente();
    			elemAux = aux.obtenerCont();
    		}
    		
    		sig = aux.obtenerSiguiente();
    		
    		if (ant == null) {
    			this.primero = sig;
    		}
    		else {
    			ant.siguiente = sig;
    		}
    		
    		this.tam --;
    	}
    	
    	return existe;
    }

    /**
     * Retorna el numero de elementos en la lista
     */
    public int getSize(){
	return (this.tam);
    }

    /**
     * Retorna un arreglo que contiene todos los elementos
     * en esta lista {@code MiLista}.
     *
     * @return an array of the elements from this {@code MiLista}.
     */

    public Object[] toArray() {
    	Object[] arreglin = new Object[this.tam];
    	E elemAct;
    	Caja<E> aux = this.primero;
    	elemAct = aux.obtenerCont();
    	int i = 0;
    	
    	while (aux != null) {
    		arreglin[i] = elemAct;
    		aux = aux.obtenerSiguiente();
    		try{
    			elemAct = aux.obtenerCont();
    		i++;
    		}catch(java.lang.NullPointerException e){
    			return arreglin;
    		}
    	}
    	
    	return arreglin;
    }
    
    public void imprimirLista() {
    	this.toArray();
    	int i = 0;
    	for(i=0;i<this.toArray().length;i++) {
    		System.out.println(this.toArray()[i].toString());
    	}
    }
}

// End List.
