/**
 * @author Gabriela Limonta, Luis Miranda
 * 
 * Clase que implementa la interfaz List
 * Esta es una clase parametrizada con tipo (clase) E; i.e., la
 * lista contiene elementos de tipo E.
 */
public class MiLista<E> implements Lista<E>{

    /**
     * Modelo de representacion: lista simplemente enlazada 
     * con un centinela.
     * La primera "caja" esta vacia.
     *
     * http://es.wikipedia.org/wiki/Lista_(inform�tica)#Nodos_Centinelas
     */
	protected Caja<E> primero;
	protected Caja<E> ultimo;
	protected int tam;

    /**
     * Constructor
     */
    public MiLista() {
    	this.primero = null;
    	this.ultimo = null;
    	this.tam = 0;
    }

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
    public boolean add(E element) {
    	boolean loLogre = false;
    	Caja<E> nueva = new Caja<E>(element,null);
    	
    	if (tam == 0) {
    		this.primero = nueva;
    		this.ultimo = nueva;
    		loLogre = true;
    	}
    	else if (tam != 0) {
    		this.ultimo.cambiarSiguiente(nueva);
    		this.ultimo = nueva;
    		loLogre = true;
    	}
    	
    	this.tam ++;
    	return loLogre;
    }
    
    public boolean addOrdenado(E element) {
    	boolean loLogre = false;
    	Caja<E> nueva = new Caja<E>(element,null);
    	Caja<E> ant = null;
    	Caja<E> aux = this.primero;
    	
    	if (tam == 0) {
    		this.primero = nueva;
    		this.ultimo = nueva;
    		loLogre = true;
    	}
    	else if (tam != 0) {
    		if (element instanceof Nodo) {
    			while (aux != null && 
    				((Nodo) aux.obtenerCont()).compareTo((Nodo)element) > 0 ){
    				ant = aux;
    				aux = aux.obtenerSiguiente();
    			}
    		}
    		else if (element instanceof Arco) {
    			while (aux != null && 
        				((Arco) aux.obtenerCont()).compareTo((Arco)element) > 0 ){
    					ant = aux;
        				aux = aux.obtenerSiguiente();
        			}
    		}
    		if(ant != null)ant.cambiarSiguiente(nueva);
    		if(aux!=null&&aux.equals(primero)){
    			primero = nueva;
    		}
    		nueva.cambiarSiguiente(aux);
    		loLogre = true;
    	}
    	
    	this.tam++;
    	return loLogre;
    	
    }

    /**
     * Funcion: clear
     * Descripcion: Elimina todos los elementos de la lista. La lista queda
     * como recien creada.
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: this.primero = null /\ this.ultimo = null /\
     * this.tam = 0
     */
    public void clear(){
    	this.primero = null;
    	this.ultimo = null;
    	this.tam = 0;
    }

    /**
     * Funcion: contains
     * Descripcion: Determina si el elemento dado esta en la lista.
     * Parametros: element, elemento que buscaremos en la lista.
     * Precondicion: true
     * Poscondicion: retorna true si el nodo existe en la lista,
     * si este no existe o la lista es vacia retorna false.
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
    
    /**
     * Funcion: obtenerPrimero
     * Descripcion: Devuelve el primero de la lista
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: retorna this.primero
     */
    public Caja<E> obtenerPrimero() {
    	return this.primero;
    }

    /**
     * Funcion: equals
     * Descricpcion: Determina si la lista dada es igual a la lista.
     * Parametros: lista, lista que vamos a comparar con this.
     * Precondicion: this.getSize = lista.getSize
     * Postcondicion: retorna true si todos los elementos de la
     * lista son iguales, en caso contrario retorna false
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
     * Funcion: isEmpty
     * Descripcion: Determina si la lista es vacia.
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: retorna true si this.tam = 0, en caso contrario retorna false
     */
    public boolean isEmpty(){
    	return (this.tam == 0);
    }

    /**
     * Funcion: remove
     * Descripcion: Elimina el elemento dado de la lista. Si la lista cambia,
     * retorna true, sino retorna false.
     * Parametros: element, elemento que eliminaremos de la lista.
     * Precondicion: true
     * Postcondicion: retorna true si se elimina element de la lista, 
     * false en caso contrario.
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
    			/*
        		 * Si el siguiente es igual al ultimo, el anterior pasa
        		 * a ser el ultimo
        		 */
        		if(aux.equals(this.ultimo))this.ultimo = ant;
    		}
    		
    		this.tam --;
    	}
    	
    	return existe;
    }

    /**
     * Funcion: getSize
     * Descripcion: Retorna el numero de elementos en la lista
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: retorna this.tam
     */
    public int getSize(){
	return (this.tam);
    }

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

    public DynamicArray toDynamicArray(){
    	DynamicArray DynArreglin = new DynamicArray();
    	DynArreglin.crecer(2);
    	
    	E elemAct;
    	Caja<E> aux = this.primero;
    	
    	if (aux == null)
    		return DynArreglin;
    	
    	elemAct = aux.obtenerCont();
    	
    	while (aux != null) {
    		DynArreglin.addOrd(elemAct);
    		aux = aux.obtenerSiguiente();
    		try{
    			elemAct = aux.obtenerCont();
    		}catch(java.lang.NullPointerException e){
    			return DynArreglin;
    		}
    	}
    	
    	return DynArreglin;
    }
    
    public Object[] toArray() {
    	Object[] arreglin = new Object[this.tam];
    	E elemAct;
    	Caja<E> aux = this.primero;
    	
    	if (aux == null)
    		return arreglin;
    	
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
    
    /**
     * Funcion: imprimirLista
     * Descripcion: imprime en pantalla el contenido de la lista.
     * Parametros N/A
     * Precondicion: true
     * Postcondicion true
     */
    public void imprimirLista() {
    	this.toArray();
    	int i = 0;
    	for(i=0;i<this.toArray().length;i++) {
    		if(this.toArray()[i]==null){
    			System.out.println("null");
    		}else{
    			System.out.println(i +": "+this.toArray()[i].toString());
    		}
    	}
    }
    
    public Object binarySearch(E element){
    	Object[] array = this.toArray();
    	int min = 0, max = array.length, mid = 0;
    	boolean encontre = false;
    	
    	while(!encontre && min <= max){
    		mid = (min + max)/2;
    		if(mid >= array.length)break;
    		if (element instanceof Nodo) {
    			if(((Nodo)array[mid]).compareTo((Nodo)element) == 0){
    				encontre = true;
    			}else if(((Nodo)array[mid]).compareTo((Nodo)element) < 0){
    				max = mid -1;
    			}else{
    				min = mid + 1;
    			}
    		}
    		else if (element instanceof Arco) {
    			if(((Arco)array[mid]).compareTo((Arco)element) == 0){
    				encontre = true;
    			}else if(((Arco)array[mid]).compareTo((Arco)element) < 0){
    				max = mid -1;
    			}else{
    				min = mid + 1;
    			}
    		}
    	}
    	
    	if(encontre){
    		return array[mid];
    	}
    	return null;
    }

    @Override
    public Object clone(){
    	MiLista<E> dummy = new MiLista<E>();
    	
    	if (this.tam == 0) {
    		return dummy;
    	}
    	else {
    		Caja<E> aux = this.obtenerPrimero();
    		E elemAux;
    		if (aux == null) {
    			return dummy;
    		}
    		else {
    			elemAux = aux.obtenerCont();
    		}
        
    		while ((aux != null)) {    		
    			
    			dummy.add(elemAux);
    			
    			aux = aux.obtenerSiguiente();
    			if (aux != null) {
    				elemAux = aux.obtenerCont();
    			}
    		}
    	}
    	
    	return dummy;
    }
    
    public Object linearSearch(E element){
    	if (this.tam == 0) {
    		return null;
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

    			if(encontre)return elemAux;

    			aux = aux.obtenerSiguiente();
    			if (aux != null) {
    				elemAux = aux.obtenerCont();
    			}
    		}
    	   	
    		return null;
    	}
    }
}

// End List.
