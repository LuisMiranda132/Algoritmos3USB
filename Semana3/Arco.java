/**
 * Clase que almacena la informacion de un arco del grafo.
 */
public class Arco {

    private String src = null;
    private String dst = null;

    private Arco() {
    }
    /**
     * Crea una nodo entre los vertices src y dst.
     */
    public Arco(String src, String dst) {
	this.src = new String(src);
	this.dst = new String(dst);
    }

    /**
     * Retorna una nueva arco que es copia de this.
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
     * Indica si la nodo de entrada es igual a this.
     */
    public boolean equals(Object o) {
	if (!(o instanceof Arco))
	    return false;

	Arco a = (Arco) o;

	return src.equals(a.src) && dst.equals(a.dst);	
    }

    /**
     * Retorna el vertice src del nodo.
     */
    public String getSrc() {
	return src;
    }

    /**
     * Retorna el vertice dst del nodo.
     */
    public String getDst() {
	return dst;
    }

    /**
     * Retorna la representacion en String del nodo.
     */
    @Override
    public String toString() {
	return "("+src + ", " + dst+")";
    }

}

// End Edge.java
