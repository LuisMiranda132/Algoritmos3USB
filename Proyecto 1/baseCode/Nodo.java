/**
 * Clase que almacena la informacion de las aristas en el grafo.
 */

public class Nodo {

    // id es unico
    private String id = null;

    public Nodo(String i){
	id = new String(i);
    }

    /**
     * Retorna una nueva arista que es copia de this.
     */
    @Override
    protected Object clone() {
	return new Nodo(id);
    }

    /**
     * Indica si la arista de entrada es igual a this.
     */
    public boolean equals(Object o) {
	Nodo d = (Nodo) o;
        return (o instanceof Nodo) && d.id.equalsIgnoreCase(id);
    }

    /**
     * Retorna la representacion en String de la arista.
     */
    @Override
    public String toString() {
	return new String(id);
    }

}

// End Edge.java
