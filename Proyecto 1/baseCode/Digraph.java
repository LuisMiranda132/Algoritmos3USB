/**
 * 
 * @author Gabriela Limonta, Luis Miranda
 * 
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

    /**
     * Funcion: add
     * Descripcion: Agrega la arista dada al grafo. Si los vertices de la arista
     * no existen o el grafo tiene una arista entre dichos vertices,
     * retorna false. Si se agrega la nueva arista, retorna true.
     * Parametros: e, arco que vamos a agregar al grafo.
     * Precondicion: true
     * Postcondicion: retorna true si se agrega el nuevo arco, si es un arco
     * repetido o los vertices no existen, retorna false
     */
    public abstract boolean add(Arco e);

    /**
     * Funcion: add
     * Descripcion: Agrega el nodo n. Si el vertice ya existe, retorna false. 
     * Si se agrega el nodo, retorna true.
     * Parametros: n, nodo que vamos a agregar
     * Precondicion: true
     * Postcondicion: retorna true si agregó el nodo, si es repetido retorna 
     * false
     */
    public abstract boolean add(Nodo n);

    /**
     * Funcion: clear
     * Descripcion: Elimina los nodos y aristas del grafo.
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: se eliminan los nodos y arcos del grafo
     * devolviendolo a su estado original, como recien creado.
     */
    public abstract void clear();

    /**
     * Funcion: clone
     * Descripcion: Retorna un nuevo grafo que 
     * es una copia del grafo actual.
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: se copia la informacion del
     * grafo actual en uno nuevo.
     */
    public abstract Object clone();

    /**
     * Funcion: contains
     * Descripcion: Chequea si el grafo contiene una arista del nodo src a dst
     * Parametros: src, dst strings que representan los vertices del arco
     * que queremos ver si existe en el grafo.
     * Precondicion: true
     * Postcondicion: retorna true si existen los nodos de fuente y destino y
     * tambien el arco, en caso de que esto no se cumpla el grafo no contiene
     * al arco y retorna false
     */
    public abstract boolean contains(String src, String dst);

    /**
     * Funcion: contains
     * Descripcion: Chequea si el grafo contiene un nodo con id nod
     * Parametros: nod, string por el que vamos a chequear si existe el nodo.
     * Precondicion: true
     * Postcondicion: si el nodo con id nod existe retorna true, en caso
     * contrario retorna false.
     */
    public abstract boolean contains(String nod);

    /**
     * Funcion: get
     * Descripcion: Retorna la arista del grafo que conecta a los vertices
     * src y dst. Si no existe dicha arista, retorna null.
     * Parametros: src y dst, strings que indican el vertice fuente y de 
     * destino del arco que queremos conseguir.
     * Precondicion: el grafo debe contener el arco (src,dst)
     * Postcondicion: retorna el arco (src,dst) si existe, sino retorna false
     */
    public abstract Arco get(String src, String dst);

    /**
     * Funcion: getArcos
     * Descripcion: Retorna todas las aristas del grafo
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: retorna una lista con todos los 
     * arcos del grafo
     */
    public abstract Lista<Arco> getArcos();

    /**
     * Funcion: get
     * Descripcion: Retorna el nodo con id nod. Si no existe dicho nodo, 
     * retorna null.
     * Parametros: nod, string que posee el nodo que queremos buscar como id
     * Precondicion: true
     * Postcondicion: si el nodo con nod como id existe retorna ese nodo, 
     * en caso contrario retorna null
     */
    public abstract Nodo get(String nod);

    /**
     * Funcion: getNodos 
     * Descripcion: Retorna todos los nodos del grafo.
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: retorna una lista con todos los 
     * nodos del grafo.
     */
    public abstract Lista<Nodo> getNodos();

    /**
     * Funcion: getNumArcos
     * Descripcion: Retorna el numero de aristas 
     * en el grafo.
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: retorna numArcos
     */
    public int getNumArcos() {
        return numArcos;
    }
    
    /**
     * Funcion: getNumArcos
     * Descripcion: Retorna el numero de vertices 
     * en el grafo.
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: retorna numVertices
     */
    public int getNumVertices() {
        return numVertices;
    }

    /**
     * Funcion: getInArcos
     * Descripcion: Retorna la lista de lados que tienen 
     * al vertice dado como destino. Si el vertice no 
     * existe, retorna null.
     * Parametros: nodo, string que corresponde al id del
     * vertice que tendremos como destino.
     * Precondicion: true
     * Postcondicion: retorna una lista con todos los arcos
     * del grafo que tienen al vertice de id nodo como destino.
     */
    public abstract  Lista<Arco> getInArcos(String nodo);

    /**
     * Funcion: getPreds
     * Descripcion: Retorna los predecesores del 
     * nodo con id nodo
     * Parametros: nodo, id del nodo del que queremos
     * conseguir los predecesores.
     * Precondicion: el nodo con id string debe 
     * existir en el grafo
     * Postcondicion: retorna una lista de nodos con
     * los predecesores del nodo.
     */
    public  Lista<Nodo> getPreds(String nodo){
    	Lista<Nodo> preds = (Lista<Nodo>) new MiLista<Nodo>();
    	try{
    		Lista<Arco> arcos = this.getInArcos(nodo);
    	
    		Caja<Arco> caja = arcos.obtenerPrimero();
    	    	
    		for(int i=0;i<arcos.getSize();i++){
    			Arco dummy = caja.obtenerCont();
    			preds.add(new Nodo(dummy.getSrc()));
    			caja = caja.obtenerSiguiente();
    		}
    	}catch(java.lang.NullPointerException e){
    		return null;
    	}    	
    	return preds;
    }

    /**
     * Funcion: getOutArcos
     * Descripcion: Retorna la lista de lados que tienen 
     * al vertice dado como origen. Si el vertice no 
     * existe, retorna null.
     * Parametros: nodo, string que corresponde al id del
     * vertice que tendremos como origen.
     * Precondicion: true
     * Postcondicion: retorna una lista con todos los arcos
     * del grafo que tienen al vertice de id nodo como origen.
     */
    public abstract Lista<Arco> getOutArcos(String nodo);

    /**
     * Funcion: getSucs
     * Descripcion: Retorna los sucesores del 
     * nodo con id nodo
     * Parametros: nodo, id del nodo del que queremos
     * conseguir los sucesores.
     * Precondicion: el nodo con id string debe 
     * existir en el grafo
     * Postcondicion: retorna una lista de nodos con
     * los sucesores del nodo.
     */
    public  Lista<Nodo> getSucs(String nodo){
    	Lista<Nodo> sucs = new MiLista<Nodo>();
    	try{
    		Lista<Arco> arcos = this.getOutArcos(nodo);
    	
    		Caja<Arco> caja = arcos.obtenerPrimero();
    	    	
    		for(int i=0;i<arcos.getSize();i++){
    			Arco dummy = caja.obtenerCont();
    			sucs.add(new Nodo(dummy.getDst()));
    			caja = caja.obtenerSiguiente();
    		}
    	}catch(java.lang.NullPointerException e){
    		return null;
    	}
    	
    	return sucs;
    }

    /**
     * Funcion: getInDegree
     * Descripcion: Retorna el in-degree del 
     * vertice dado. Si el vertice no existe, 
     * retorna -1.
     * Ṕarametros: nodo, string del id del nodo
     * del que queremos el in-degree
     * Precondicion: el nodo debe existir en
     * el grafo
     * Postcondicion: devuelve la cantidad de 
     * in-arcos del nodo con string nodo
     */
    public int getInDegree(String nodo)  {
    	if(this.contains(nodo)){
    		Lista <Arco> arcos = this.getInArcos(nodo);
    		return arcos.getSize();
    	}
    	return -1;
    }
    /**
     * Funcion: getOtDegree
     * Descripcion: Retorna el out-degree del 
     * vertice dado. Si el vertice no existe, 
     * retorna -1.
     * Ṕarametros: nodo, string del id del nodo
     * del que queremos el out-degree
     * Precondicion: el nodo debe existir en
     * el grafo
     * Postcondicion: devuelve la cantidad de 
     * out-arcos del nodo con string nodo
     */
    public int getOutDegree(String nodo) {
    	if(this.contains(nodo)){
    		Lista <Arco> arcos = this.getOutArcos(nodo);
    		return arcos.getSize();
    	}
    	return -1;
    }

    /**
     * Funcion: remove
     * Descripcion: Remueve la arista del grafo que 
     * conecta a los vertices src y dst. Si el grafo 
     * no cambia, retorna false. Si el grafo cambia,
     * retorna true.
     * Parametros: src y dst que son los strings de 
     * los nodos de origen y destino del arco que 
     * queremos eliminar.
     * Precondicion: el arco (src,dst) debe existir
     * Postcondicion: retorna true si elimina el arco
     * sino retorna false.
     */
    public abstract boolean remove(String src, String dst);

    /**
     * Funcion: remove
     * Descripcion: Remueve el nodo del grafo el 
     * nodo nod y todas las aristas a las que esta 
     * conectado. Si el grafo no cambia, retorna 
     * false. Si el grafo cambia, retorna true.
     * Parametros: nod, string que es el id del 
     * nodo que deseo eliminar.
     * Precondicion: el nodo con id nod debe existir
     * en el grafo
     * Postcondicion: retorna true si se elimina el 
     * nodo y todos los arcos a los que esta conectado.
     */
    public abstract boolean remove(String nod);

    /**
     * Funcion: toString
     * Descripcion: Construye una representacion 
     * en String del grafo.
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: retorna el string del grafo
     * indicando el nuemro de vertices y arcos.
     */
    public String toString() {
        String ret = numVertices + ":" + numArcos ;


        return ret;
    }
}

// End Digraph.java
