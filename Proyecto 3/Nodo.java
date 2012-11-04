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
