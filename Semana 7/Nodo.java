/**
 * 
 * @author Gabriela Limonta, Luis Miranda
 * 
 * Clase que almacena la informacion de las aristas en el grafo.
 */

import java.lang.Comparable;

public class Nodo implements Comparable<Nodo>{

    // id es unico
    private String id = null;
    private boolean visitado = false;
    private boolean origen = false;
    private boolean destino = false;
    private int costAcum = Integer.MAX_VALUE;
    private Nodo ancestro = null;
    private String funcion = null;
    
    public Nodo(String i){
	id = new String(i);	
    }
    
    /**
     * Funcion: clone
     * Descripcion: Retorna una nueva arista que es copia de this.
     * Parametros: N/A
     * Precondición: true
     * Postcondicion: retorna un nuevo nodo que es una copia de this.
     */
    @Override
    protected Object clone() {
	return new Nodo(this.id);
    }

    /**
     * Funcion: equals
     * Descripcion: Indica si el nodo de entrada es igual a this.
     * Parametros: o nodo de entrada que se compara con this.
     * Precondicion: true
     * Postcondicion: o es un nodo /\  d.id = id
     */
    public boolean equals(Object o) {
	Nodo d = (Nodo) o;
        return (o instanceof Nodo) && d.id.equalsIgnoreCase(id);
    }

    /**
     * Funcion: toString
     * Descripcion: Retorna la representacion en String del nodo.
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: retorna el string con el id del nodo this.
     */
    @Override
    public String toString() {
	return this.id;
    }
    
    /*
     * asigna el valor al campo visitado
     */
    public void setVisitado(boolean visit){
    	this.visitado = visit;
    }

    /*
     * obtiene el valor del campo visitado
     */
	public boolean getVisitado(){
		return this.visitado;
	}
	
	/*
	 * obtiene el valor del campo origen
	 */
	public boolean esSrc() {
		return this.origen;
	}
	
	/*
	 * obtiene el valor del campo destino
	 */
	public boolean esDst() {
		return this.destino;
	}
	
	/*
	 * asigna true al campo que indica que es fuente
	 */
	public void setSrc() {
		this.origen = true;
	}
	/*
	 * asigna true al campo que indica que es destino
	 */
	public void setDst() {
		this.destino = true;
	}
	
	/*
	 * retorna el costo acumulado
	 */
	public int getCost() {
		return this.costAcum;
	}
	
	/*
	 * asigna el costo acumulado
	 */
	public void setCost(int c) {
		this.costAcum = c;
	}
	
	/*
	 * retorna el ancestro
	 */
	public Nodo getAncestro() {
		return this.ancestro;
	}
	
	/*
	 * asigna el ancestro
	 */
	public void setAncestro(Nodo a) {
		this.ancestro = a;
	}
	
	/* 
	 * devuelve la funcion mediante la cual se llego a este nodo
	 */
	public String getFuncion() {
		return this.funcion;
	}
	
	/* 
	 * asigna la funcion mediante la cual se llego a este nodo
	 */
	public void setFuncion(String f) {
		this.funcion = f;
	}
	
	/*
	 * Devuelve un numero negativo si this va primero que n
	 * Devuelve un numero positivo si n va primero que this
	 * si son iguales devuelve 0
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Nodo n){
		return this.id.compareTo(n.id);
	}
	
}

// End Edge.java
