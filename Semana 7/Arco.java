/**
 * 
 * @author Gabriela Limonta, Luis Miranda
 * 
 * Clase que almacena la informacion de las aristas en el grafo.
 */

import java.lang.Comparable;

public class Arco implements Comparable<Arco> {

    private String src = null;
    private String dst = null;
    private String nombFunc = null;
    private int costo;
	
    private Arco() {
    }

    public Arco(String src, String dst, String nombF, int cost){
    	this.src = src;
    	this.dst = dst;
    	this.nombFunc = nombF;
    	this.costo = cost;
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
	ed.src = new String(this.src);
	ed.dst = new String(this.dst);
	ed.nombFunc = new String(this.nombFunc);
	ed.costo = new Integer(this.costo);

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

    public String getFuncion() {
        return this.nombFunc;
    }
    
    public int getCosto() {
        return this.costo;
    }
    
    public void setCosto(int cos) {
    	this.costo = cos;
    }
    
    @Override
    public String toString() {
	return this.nombFunc +": ("+src + ", " + dst+"): " + this.costo;
    }

	@Override
	public int compareTo(Arco o) {
		if(this.src.compareTo(o.src) == 0){
			return this.dst.compareTo(o.dst);
		}
		return this.src.compareTo(o.src);
	}

}

// End Edge.java
