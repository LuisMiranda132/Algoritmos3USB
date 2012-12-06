/**
 * 
 * @author Gabriela Limonta, Luis Miranda
 * 
 * Clase que almacena la informacion de las aristas en el grafo.
 */

import java.lang.Comparable;

public class Arco implements Comparable<Arco> {

    private int src;
    private int dst;
    private int costo;
	
    private Arco() {
    }
    
    /**
     * Funcion: Arco 
     * Descripcion: Crea una arista entre los vertices src y dst.
     * Parametros: src, dst strings a ser agregados al arco.
     * Precondicion: true;
     * Postondicion: this.src = src /\ this.dst = dst
     */
    public Arco(int src, int dst, int c) {
    	this.src = src;
    	this.dst = dst;
    	this.costo = c;
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
	ed.src = src;
	ed.dst = dst;

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
    	return (o instanceof Arco) && d.dst==dst && 
    	d.src == src;
    }

    /**
     * Funcion: getSrc
     * Descripcion: Retorna el vertice src de la arista.
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: retorna this.src
     */
    public int getSrc() {
        return this.src;
    }

    /**
     * Funcion: getDst
     * Descripcion: Retorna el vertice dst de la arista.
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: retorna this.dst
     */
    public int getDst() {
        return this.dst;
    }
    
    /**
     * Funcion: getCost
     * Descripcion: Retorna el costo del lado.
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: retorna this.costo
     */
    public int getCost() {
        return this.costo;
    }

    @Override
    public String toString() {
	return costo+": ("+src + ", " + dst+")";
    }

	@Override
	public int compareTo(Arco o) {
		if(this.costo > o.costo){
			return 1;
		}
		else if (this.costo < o.costo) {
			return -1;
		}
		else {
			return 0;
		}
	}

}

// End Edge.java
