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
    private BinaryHeap<Palabrita> palabras = new BinaryHeap<Palabrita>();
	
    private Arco() {
    }
    
    @SuppressWarnings("unchecked")
	public Arco(String src, String dst, BinaryHeap<Palabrita> palabras){
    	this.src = src;
    	this.dst = dst;
    	this.palabras = (BinaryHeap<Palabrita>) palabras.clone();
    }

    public Arco(String src, String dst, Palabrita palabra){
    	this.src = src;
    	this.dst = dst;
    	this.palabras.agregar(palabra);
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
    @SuppressWarnings("unchecked")
	@Override
    protected Object clone() {
	Arco ed = new Arco();

	// se copian (clonan) todos los objetos internos, 
	// no solo asignar las referencias
	ed.src = new String(src);
	ed.dst = new String(dst);
	ed.palabras = (BinaryHeap<Palabrita>) this.palabras.clone();

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
     * Funcion: getDst
     * Descripcion: Retorna el heap de Palabrita
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: retorna this.palabra
     */
    public BinaryHeap<Palabrita> getPal(){
    	return this.palabras;
    }
    
    /**
     * Funcion: setPal
     * Descripcion: Agrega un heap al arco
     * Parametros: N/A
     * Precondicion: truee
     * Postcondicion: true
     */
    public void setPal(BinaryHeap<Palabrita> pal){
    	this.palabras = pal;
    }
    
    
    @Override
    public String toString() {
	return "("+src + ", " + dst+"): "+palabras.toString();
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
