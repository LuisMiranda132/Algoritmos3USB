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
     * http://es.wikipedia.org/wiki/Lista_(informática)#Nodos_Centinelas
     */

    /*
     * Constructor
     */
    public MiLista() {
    }

    /**
     * Agrega un elemento al final de la lista.
     */
    public boolean add(E element) {
	throw new UnsupportedOperationException("Not supported yet.");
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
	throw new UnsupportedOperationException("Not supported yet.");
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
