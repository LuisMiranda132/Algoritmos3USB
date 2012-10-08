/**
 * Clase que almacena la informacion de un nodo del grafo.
 */

public class Nodo {

    // id es unico
    private String id = null;

    public Nodo(String i){
	id = new String(i);
    }

    /**
     * Retorna una nuevo nodo que es copia de this.
     */
    @Override
    protected Object clone() {
	return new Nodo(id);
    }

    /**
     * Indica si la nodo de entrada es igual a this.
     */
    public boolean equals(Object o) {
        return (o instanceof Nodo) && id.equalsIgnoreCase(((Nodo) o).id);
    }

    /**
     * Retorna la representacion en String de la nodo.
     */
    @Override
    public String toString() {
	return new String(id);
    }

}

// End Nodo.java
