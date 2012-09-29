/**
 * Interfaz que define el comportamiento de una lista
 * 
 * Esta es una clase parametrizada con tipo (clase) E; i.e., la
 * lista contiene elementos de tipo E.
 */
public interface Lista<E> {

    /**
     * Agrega un elemento a la lista.
     */
    public boolean add(E element);

    /**
     * Elimina todos los elementos de la lista. La lista queda
     * como recien creada.
     */
    public void clear();

    /**
     * Determina si el elemento dado esta en la lista.
     */
    public boolean contains(Object element);

    /**
     * Determina si la lista dada es igual a la lista.
     */
    public boolean equals(Lista<E> list);

    /**
     * Determina si la lista es vacia.
     */
    public boolean isEmpty();

    /**
     * Elimina el elemento dado de la lista. Si la lista cambia,
     * retorna true, sino retorna false.
     */
    public boolean remove(E element);

    /**
     * Retorna el numero de elementos en la lista
     */
    public int getSize();

   /**
     * Retorna un arreglo que contiene todos los elementos
     * en esta lista {@code MiLista}.
     *
     * @return an array of the elements from this {@code MiLista}.
     */

    public Object[] toArray();

}

// End Lista.
