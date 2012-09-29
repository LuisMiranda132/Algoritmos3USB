/*
 * Implementa la clase abstracta Digraph utilizando dos tablas de
 * hash, una para vertices y una para arcos.
 */
public class DigraphMatriz extends Digraph {

    private  Nodo nodos[];
    private int matriz[][];

     /*
     * @see Constructor para Digraph.
     */
    public DigraphMatriz() {
	super();
    }

    /*
     * Agrega la arista dada al grafo. Si los vertices de la arista
     * no existen o el grafo tiene una arista entre dichos vertices,
     * retorna false. Si se agrega la nueva arista, retorna true.
     */
    public  boolean add(Arco e){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
     * Agrega el nodo n. Si el vertice ya existe, retorna false. Si
     * se agrega el nodo, retorna true.
     */
    public  boolean add(Nodo n){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
     * Elimina los nodos y aristas del grafo.
     */
    public  void clear(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
     * Chequea si el grafo contiene una arista del nodo src a dst
     */
    public  boolean contains(String src, String dst){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
     * Chequea si el grafo contiene un nodo con id nod
     */
    public boolean contains(String nod) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
     * Retorna la arista del grafo que conecta a los vertices
     * src y dst. Si no existe dicha arista, retorna null.
     */
    public  Arco get(String src, String dst){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
     *Retorna todas las aristas del grafo
     */
    public  Lista<Arco> getArcos(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
     * Retorna el nodo con id nod. Si no existe dicho nodo, 
     * retorna null.
     */
    public Nodo get(String nod){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /* 
     * Retorna todos los nodos del grafo.
     */
    public  Lista<Nodo> getNodos(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
     * Retorna la lista de lados que tienen al vertice dado como
     * destino. Si el vertice no existe, retorna null.
     */
    public  Lista<Arco> getInArcos(String nodo){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
     * Retorna la lista de lados que tienen al vertice dado como
     * origen. Si el vertice no existe, retorna null.
     */
    public  Lista<Arco> getOutArcos(String nodo){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
     * Remueve la arista del grafo que conecta a los vertices src y
     * dst. Si el grafo no cambia, retorna false. Si el grafo cambia,
     * retorna true.
     */
    public  boolean remove(String src, String dst){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
     * Remueve el nodo del grafo el nodo nod y todas las aristas a las
     * que esta conectado. Si el grafo no cambia, retorna false. Si el
     * grafo cambia, retorna true.
     */
    public  boolean remove(String nod){
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

// End DigraphHash.
