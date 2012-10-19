/**
 * 
 * @author Gabriela Limonta, Luis Miranda
 * 
 * Clase que almacena la informacion de las aristas en el grafo.
 */

public class Nodo {

    // id es unico
    private String id = null;
    private int erdosNum = 2147483647;
    
    public Nodo(String i){
	id = new String(i);	
    }
    
    public Nodo(String i, int erd) {
    	id = new String(i);
    	erdosNum = erd;
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

    public int getErdos(){
    	return this.erdosNum;
    }
    
    public void setErdos(int erdos){
    	this.erdosNum = erdos;
    }
}

// End Edge.java
