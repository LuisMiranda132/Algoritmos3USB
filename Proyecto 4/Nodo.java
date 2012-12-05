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
    private boolean funcion = false;
    private String funcAnt = " ";
    private int costo = -1;
    private DynamicArray dominio; 
    
    public Nodo(String i){
    	this.id = new String(i);
    	this.dominio = new DynamicArray();
    	this.dominio.crecer(2);
    }
    
    public Nodo(String i,int c){
    	this.id = new String(i);
    	this.costo = c;
    	this.dominio = new DynamicArray();
    	this.dominio.crecer(2);
    }
    
    public void addDominio(String x){
    	this.dominio.addOrd(x);
    }
    
    public DynamicArray getDominio(){
    	return this.dominio;
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
	
	public void setFuncion(boolean esFunc){
    	this.funcion = esFunc;
    }

	public boolean getFuncion(){
		return this.funcion;
	}
	
	public void setCosto(int c){
    	this.costo = c;
    }

	public int getCosto(){
		return this.costo;
	}
	
	public void setFunAnt(String fA){
    	this.funcAnt = new String(fA);
    }

	public String getFunAnt(){
		return this.funcAnt;
	}
	
	/*
	 * Devuelve un numero negativo si this va primero que n
	 * Devuelve un numero positivo si n va primero que this
	 * si son iguales devuelve 0
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Nodo n){
			if(this.costo == n.costo){
				return 0;
			}else if(this.costo < n.costo){
				return -1;
			}
			return 1;
	//		return this.id.compareTo(n.id);
	}
	
}

// End Edge.java
