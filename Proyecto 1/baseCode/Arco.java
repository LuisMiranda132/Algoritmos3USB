/**
 * Clase que almacena la informacion de las aristas en el grafo.
 */
public class Arco {

    private String src = null;
    private String dst = null;

    private Arco() {
    }
    
    /**
     * Crea una arista entre los vertices src y dst.
     */
    public Arco(String src, String dst) {
    }

    /**
     * Retorna una nueva arista que es copia de this.
     */
    @Override
    protected Object clone() {
	Arco ed = new Arco();

	// se copian (clonan) todos los objetos internos, 
	// no solo asignar las referencias
	ed.src = new String(src);
	ed.dst = new String(dst);

	return ed;
    }

    /**
     * Indica si la arista de entrada es igual a this.
     */
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Retorna el vertice src de la arista.
     */
    public String getSrc() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Retorna el vertice dst de la arista.
     */
    public String getDst() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Retorna la representacion en String de la arista.
     */
    @Override
    public String toString() {
	return "("+src + ", " + dst+")";
    }

}

// End Edge.java
