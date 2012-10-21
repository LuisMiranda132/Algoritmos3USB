/**
 * 
 * @author Gabriela Limonta, Luis Miranda
 * 
 * Clase que almacena la informacion de las aristas en el grafo.
 */
public class Arco {

    private String src = null;
    private String dst = null;

    private Arco() {
    }
    
    /**
     * Funcion: Arco 
     * Descripcion: Crea una arista entre los vertices src y dst.
     * Parametros: src, dst strings a ser agregados al arco.
     * Precondicion: true;
     * Postondicion: this.src = src /\ this.dst = dst
     */
    public Arco(String src, String dst) {
    	this.src = src;
    	this.dst = dst;
    }

    /**
     * Funcion: clone
     * Descripcion: Retorna una nueva arista que es copia de this.
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: devuelve un nuevo arco que es una copia de this.
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
     * Funcion: equals
     * Descripcion: Indica si la arista de entrada es igual a this.
     * Parametros: o arista que compararemos a this
     * Precondicion: true
     * Postcondicion: o es un arco /\  d.dst = dst /\ d.src = src
     */
    public boolean equals(Object o) {
        Arco d = (Arco) o;
    	return (o instanceof Arco) && d.dst.equalsIgnoreCase(dst) && 
    	d.src.equalsIgnoreCase(src);
    }

    /**
     * Funcion: getSrc
     * Descripcion: Retorna el vertice src de la arista.
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: retorna this.src
     */
    public String getSrc() {
        return this.src;
    }

    /**
     * Funcion: getDst
     * Descripcion: Retorna el vertice dst de la arista.
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: retorna this.dst
     */
    public String getDst() {
        return this.dst;
    }

    /**
     * Funcion: toString
     * Descripcion: Retorna la representacion en String de la arista.
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: retorna el string con el vertice src y el vertice dst.
     */
    @Override
    public String toString() {
	return "("+src + ", " + dst+")";
    }

}

// End Edge.java
