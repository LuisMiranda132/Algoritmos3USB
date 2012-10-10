/**
 * 
 * @author Gabriela Limonta, Luis Miranda
 * 
 * Interfaz que define el comportamiento de una lista
 * 
 * Esta es una clase parametrizada con tipo (clase) E; i.e., la
 * lista contiene elementos de tipo E.
 */
public interface Lista<E> {

	/**
     * Funcion: add
     * Descripcion: Agrega un elemento al final de la lista.
     * Parametros: element, elemento que se agregará al 
     * final de la lista.
     * Retorno: retorna true si se agrega el elemento y 
     * false en caso contrario.
     * Precondicion: true
     * Postcondicion: ((tam=0)=>(this.primero = nueva) /\
     * (this.ultimo = nueva)) /\ ((tam!=0)=>
     * (this.ultimo.siguiente = nueva) /\ (this.ultimo = nueva)) 
     */
    public boolean add(E element);

    /**
     * Funcion: clear
     * Descripcion: Elimina todos los elementos de la lista. La lista queda
     * como recien creada.
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: this.primero = null /\ this.ultimo = null /\
     * this.tam = 0
     */
    public void clear();

    /**
     * Funcion: contains
     * Descripcion: Determina si el elemento dado esta en la lista.
     * Parametros: element, elemento que buscaremos en la lista.
     * Precondicion: true
     * Poscondicion: retorna true si el nodo existe en la lista,
     * si este no existe o la lista es vacia retorna false.
     */
    public boolean contains(Object element);
    
    /**
     * Funcion: obtenerPrimero
     * Descripcion: Devuelve el primero de la lista
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: retorna this.primero
     */
    public Caja<E> obtenerPrimero();

    /**
     * Funcion: equals
     * Descricpcion: Determina si la lista dada es igual a la lista.
     * Parametros: lista, lista que vamos a comparar con this.
     * Precondicion: this.getSize = lista.getSize
     * Postcondicion: retorna true si todos los elementos de la
     * lista son iguales, en caso contrario retorna false
     */
    public boolean equals(Lista<E> list);

    /**
     * Funcion: isEmpty
     * Descripcion: Determina si la lista es vacia.
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: retorna true si this.tam = 0, en caso contrario retorna 
     * false
     */
    public boolean isEmpty();

    /**
     * Funcion: remove
     * Descripcion: Elimina el elemento dado de la lista. Si la lista cambia,
     * retorna true, sino retorna false.
     * Parametros: element, elemento que eliminaremos de la lista.
     * Precondicion: true
     * Postcondicion: retorna true si se elimina element de la lista, 
     * false en caso contrario.
     */
    public boolean remove(E element);

    /**
     * Funcion: getSize
     * Descripcion: Retorna el numero de elementos en la lista
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: retorna this.tam
     */
    public int getSize();

    /**
     * Funcion: toArray
     * Descripcion: Retorna un arreglo que contiene todos los elementos
     * en esta lista {@code MiLista}.
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: retorna un arreglo de objetos con los elementos 
     * de la lista.
     *
     * @return an array of the elements from this {@code MiLista}.
     */
    public Object[] toArray();
    
    /**
     * Funcion: imprimirLista
     * Descripcion: imprime en pantalla el contenido de la lista.
     * Parametros N/A
     * Precondicion: true
     * Postcondicion true
     */
    public void imprimirLista();

}

// End Lista.
