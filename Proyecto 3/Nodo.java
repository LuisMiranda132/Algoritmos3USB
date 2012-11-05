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
    private int costo = Integer.MAX_VALUE;
    private Nodo antNodo = null;
    private char anterior = '@';
    private Arco antArco = null;
    
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
    	Nodo dummy = new Nodo(this.id);
    	dummy.setAnterior(this.anterior);
    	dummy.setAntNodo(this.antNodo);
    	dummy.setArco(this.antArco);
    	dummy.setCosto(this.costo);
    	return dummy;
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
	return new String(id);
    }
    public void setVisitado(boolean visit){
    	this.visitado = visit;
    }

	public boolean getVisitado(){
		return this.visitado;
	}
	
	public int getCosto(){
		return this.costo;
	}
	
	public void setCosto(int cost){
		this.costo = cost;
	}
	
	public void setAnterior(char ant){
		this.anterior = ant;
	}
	
	public char getAnterior(){
		return this.anterior;
	}
	
	public void setAntNodo(Nodo ant){
		this.antNodo = ant;
	}
	
	public Nodo getAntNodo(){
		return this.antNodo;
	}
	
	public void setArco(Arco ant){
		this.antArco = ant;
	}
	
	public Arco getAntArco(){
		return this.antArco;
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
