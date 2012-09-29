/**
 * Clase abstracta que implemena un grafo dirigido. Las aristas son
 * instancias de la clase Arco.
 */
public abstract class Digraph {

    protected int numVertices;  // numero de vertices
    protected int numArcos;     // numero de aristas

    /*
     * Construye un grafo vacio.
     */
    public Digraph() {
        numVertices = 0;
        numArcos = 0;
    }

    /*
     * Agrega la arista dada al grafo. Si los vertices de la arista
     * no existen o el grafo tiene una arista entre dichos vertices,
     * retorna false. Si se agrega la nueva arista, retorna true.
     */
    public abstract boolean add(Arco e);

    /*
     * Agrega el nodo n. Si el vertice ya existe, retorna false. Si
     * se agrega el nodo, retorna true.
     */
    public abstract boolean add(Nodo n);

    /*
     * Elimina los nodos y aristas del grafo.
     * 
     */
    public abstract void clear();

    /*
     * Retorna un nuevo grafo que es una copia del grafo actual.
     * 
     */
    public Object clone(){
	throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
     * Chequa que el grafo contiene una arista (src, dst).
     */
    public abstract boolean contains(String src, String dst);

    /*
     * Chequa que el grafo contiene una nodo con id nod
     */
    public boolean contains(String nod) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
     * Retorna la arista del grafo que conecta a los vertices
     * src y dst. Si no existe dicha arista, retorna null.
     */
    public abstract Arco get(String src, String dst);

    /*
     *Retorna todas las aristas del grafo
     */
    public abstract Lista<Arco> getArcos();

    /*
     * Retorna el nodo con id nod. Si no existe dicho nodo, 
     * retorna null.
     */
    public abstract Nodo get(String nod);

    /* 
     * Retorna todos los nodos del grafo.
     */
    public abstract Lista<Nodo> getNodos();

    /*
     * Retorna el numero de aristas en el grafo.
     */
    public int getNumArcos() {
        return numArcos;
    }

    /*
     * Retorna el numero de vertices en el grafo.
     */
    public int getNumVertices() {
        return numVertices;
    }

  /*
     * Retorna la lista de lados que tienen al vertice dado como
     * destino. Si el vertice no existe, retorna null.
     */
    public abstract  Lista<Arco> getInArcos(String nodo);

    /*
     * Retorna los predecesores del nodo con id nodo
     */
    @SuppressWarnings("unchecked")
    public  Lista<Nodo> getPreds(String nodo){
	Lista<Nodo> preds = (Lista<Nodo>) new MiLista();


	return preds;
    }

    /*
     * Retorna la lista de lados que tienen al vertice dado como
     * origen. Si el vertice no existe, retorna null.
     */
    public abstract Lista<Arco> getOutArcos(String nodo);

    /*
     * Retorna los sucesores del nodo con id nodo
     */
    @SuppressWarnings("unchecked")
    public  Lista<Nodo> getSucs(String nodo){
	Lista<Nodo> sucs = new MiLista();


	return sucs;
    }

    /*
     * Retorna el in-degree del vertice dado. Si el
     * vertice no existe, retorna -1.
     */
    public int getInDegree(String nodo)  {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /*
     * Retorna el out-degree del vertice dado. Si el
     * vertice no existe, retorna -1.
     */
    public int getOutDegree(String nodo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
     * Remueve la arista del grafo que conecta a los vertices src y
     * dst. Si el grafo no cambia, retorna false. Si el grafo cambia,
     * retorna true.
     */
    public abstract boolean remove(String src, String dst);

    /*
     * Remueve el nodo del grafo con id nod. Si el grafo no cambia,
     * retorna false. Si el grafo cambia, retorna true.
     */
    public abstract boolean remove(String nod);

    /*
     * Construye una representacion en String del grafo.
     */
    public String toString() {
        String ret = numVertices + ":" + numArcos ;


        return ret;
    }
}

// End Digraph.java
