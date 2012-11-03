/**
 * 
 * @author Gabriela Limonta, Luis Miranda
 * 
 * Implementa la clase abstracta Digraph utilizando dos tablas de
 * hash, una para vertices y una para arcos.
 */
public class DigraphLista extends Digraph {

    private  DynamicArray nodos;
    private  DynamicArray arcos; 

    /*
     * @see Constructor para Digraph.
     */
    public DigraphLista() {
    	super();
    	nodos = new DynamicArray();
    	arcos = new DynamicArray();
    }

    /*
     * Agrega la arista dada al grafo. Si los vertices de la arista
     * no existen o el grafo tiene una arista entre dichos vertices,
     * retorna false. Si se agrega la nueva arista, retorna true.
     */
    @SuppressWarnings("unchecked")
	public  boolean add(Arco e){
        String src = e.getSrc();
        boolean sali = false;
        Nodo noditoS = new Nodo(src);
        Nodo noditoD = new Nodo(e.getDst());
        int i = 0;
        
        sali = this.contains(noditoD.toString());
        if (!sali) {
        	return sali;
        }
        
        sali = this.contains(noditoS.toString());
        if (!sali) {
        	return sali;
        }
        
        
        if (this.contains(e.getSrc(), e.getDst())) {
        	return false;
        }
        else {
        	((Lista<Arco>) this.arcos.getArray()[i]).add(e);
            this.numArcos++;
        	return true;
        }

    }
    
    /*
     * Agrega el nodo n. Si el vertice ya existe, retorna false. Si
     * se agrega el nodo, retorna true.
     */
    public  boolean add(Nodo n){
    	boolean esta;
    	boolean agregue = false;
    	
    	esta = this.contains(n.toString());
    	
    	
    	if (!esta) {
    		this.nodos.addFinal(n); 
    		this.arcos.addFinal(new MiLista<Arco>());
    		this.numVertices++;
    		agregue = true;
    	}
    	return agregue;
    	
    }

    /*
     * Elimina los nodos y aristas del grafo.
     */
    public  void clear(){
    	
    	this.nodos = new DynamicArray();
    	this.arcos = new DynamicArray();
    	this.numVertices =0;
    	this.numArcos=0;
    }

    /*
     * Chequea si el grafo contiene una arista del nodo src a dst
     */
    @SuppressWarnings("unchecked")
	public  boolean contains(String src, String dst){
        Nodo noditoS = new Nodo(src);
        Nodo noditoD = new Nodo(dst);
        boolean sali = false;
        int i=0;
        
        while(i < this.nodos.getArray().length && !sali) {
        	if (((Nodo) this.nodos.getArray()[i]).equals(noditoD)) {
        		sali = true;
        	}
        	i++;
        }
        if (!sali) {
        	return sali;
        }
        
        i=0;
        sali = false;
        
        while(i < this.nodos.getArray().length && !sali) {
        	if (((Nodo) this.nodos.getArray()[i]).equals(noditoS)) {
        		sali = true;
        	}
        	i++;
        }
        if (!sali) {
        	return sali;
        }
        
        Arco aux = new Arco(src,dst);
        if (!((Lista<Arco>) this.arcos.getArray()[i]).contains(aux)) {
        	return false;
        }
        else{
        	return true;
        }
    }

    /*
     * Chequea si el grafo contiene un nodo con id nod
     */
    public boolean contains(String nod) {
		Nodo aux = new Nodo(nod);
        boolean sali = false;
		int i=0;
        
        while(i < this.nodos.getArray().length && !sali) {
        	if (((Nodo) this.nodos.getArray()[i]).equals(aux)) {
        		sali = true;
        	}
        	i++;
        }
        
        return sali;
    }

    /*
     * Retorna la arista del grafo que conecta a los vertices
     * src y dst. Si no existe dicha arista, retorna null.
     */

    @SuppressWarnings("unchecked")
	public  Arco get(String src, String dst){
        Nodo auxS = new Nodo(src);
        Nodo auxD = new Nodo(dst);
        boolean sali;
        
        sali = this.contains(auxD.toString());
        
        if (!sali)
        	return null;
        
        sali = this.contains(auxS.toString());
        
        if (!sali) {
        	return null;
        }
        
        Arco nuevo = new Arco(src,dst);
        //nuevo = new Arco(src, dst, nuevo.getPal());
        if (this.contains(src,dst))
        	return nuevo;
        else {
        	return null;
        }
	}
    

    /*
     *Retorna todas las aristas del grafo
     */

    @SuppressWarnings("unchecked")
	public  Lista<Arco> getArcos(){
    	int i=0;
        
        Lista<Arco> listaSal = new MiLista<Arco>();
        
        while (i < this.nodos.getArray().length) {
        	Lista<Arco> listaTemp = new MiLista<Arco>();
        	listaTemp = (Lista<Arco>) this.arcos.getArray()[i];
        	Caja<Arco> cajaAux = listaTemp.obtenerPrimero();
        	while (cajaAux != null) {
        		listaSal.add(cajaAux.obtenerCont());
        		cajaAux = cajaAux.obtenerSiguiente();
        	}
        	i++;
        }
        
        return listaSal;
    }

    /*
     * Retorna el nodo con id nod. Si no existe dicho nodo, 
     * retorna null.
     */
    public Nodo get(String nod){
    	Nodo aux = new Nodo(nod);
    	boolean sali = false;
    	int i=0;
    	
    	while(i < this.nodos.getArray().length && !sali) {
        	if (((Nodo) this.nodos.getArray()[i]).equals(aux)) {
        		sali = true;
        	}
        	i++;
        }
    	
    	if (!sali)
    		return null;
    	
        return (Nodo) this.nodos.getArray()[i];
        
    }

    /* 
     * Retorna todos los nodos del grafo.
     */
    public  Lista<Nodo> getNodos(){
        Lista<Nodo> lista = new MiLista<Nodo>();
        int i=0;
        
        while(i < this.nodos.getArray().length) {
        	lista.add((Nodo) this.nodos.getArray()[i]);
        	i++;
        }
        return lista;
    }

    /*
     * Retorna la lista de lados que tienen al vertice dado como
     * destino. Si el vertice no existe, retorna null.
     */

    @SuppressWarnings("unchecked")
	public  Lista<Arco> getInArcos(String nodo){
    	Nodo aux = new Nodo(nodo);
    	Lista<Arco> listaSal = new MiLista<Arco>();
    	int i=0;
        boolean exist = false;
    	
        exist = this.contains(aux.toString());
        
        if (!exist)
        	return null;
        
        i=0;
        
        while (i < this.nodos.getArray().length) {
        	Lista<Arco> listaTemp = new MiLista<Arco>();
        	
        	listaTemp = ((Lista<Arco>) this.arcos.getArray()[i]);
        	
        	Caja<Arco> cajaAux = listaTemp.obtenerPrimero();
        	
        	while (cajaAux != null) {
        		if ((cajaAux.obtenerCont()).getDst().equalsIgnoreCase(nodo))
        			listaSal.add(cajaAux.obtenerCont());
        		cajaAux = cajaAux.obtenerSiguiente();
        	}
        	i++;
        }
        
        return listaSal;
        
    }

    /*
     * Retorna la lista de lados que tienen al vertice dado como
     * origen. Si el vertice no existe, retorna null.
     */
    @SuppressWarnings("unchecked")
	public  Lista<Arco> getOutArcos(String nodo){
    	Nodo aux = new Nodo(nodo);
    	boolean sali = false;
    	int i=0;
    	
    	while(i < this.nodos.getArray().length && !sali) {
        	if (((Nodo) this.nodos.getArray()[i]).equals(aux)) {
        		sali = true;
        	}
        	i++;
        }
    	
    	if (!sali)
    		return null;
        
        return (Lista<Arco>) this.arcos.getArray()[i];
    }

    /*
     * Remueve la arista del grafo que conecta a los vertices src y
     * dst. Si el grafo no cambia, retorna false. Si el grafo cambia,
     * retorna true.
     */
    @SuppressWarnings("unchecked")
	public  boolean remove(String src, String dst){
    	Nodo noditoS = new Nodo(src);
        Nodo noditoD = new Nodo(dst);
        boolean elimine = false;
        boolean sali = false;
        int i=0;
        
        
        while(i < this.nodos.getArray().length && !sali) {
        	if (((Nodo) this.nodos.getArray()[i]).equals(noditoD)) {
        		sali = true;
        	}
        	i++;
        }
    	
    	if (!sali)
    		return false;
        
        i=0;
        sali = false;
        
        while(i < this.nodos.getArray().length && !sali) {
        	if (((Nodo) this.nodos.getArray()[i]).equals(noditoS)) {
        		sali = true;
        	}
        	i++;
        }
    	
    	if (!sali)
    		return false;
        
    	
    	Arco aux = new Arco(src,dst);
    	
    	if (!((Lista<Arco>) this.arcos.getArray()[i]).contains(aux)) {
        return elimine;
        }
        else{
        	elimine = ((Lista<Arco>) this.arcos.getArray()[i]).remove(aux); 
        	return elimine;
        }
    }

    /*
     * Remueve el nodo del grafo el nodo nod y todas las aristas a las
     * que esta conectado. Si el grafo no cambia, retorna false. Si el
     * grafo cambia, retorna true.
     */
    public  boolean remove(String nod){
    	Nodo aux = new Nodo(nod);
    	boolean elimine = false;
    	boolean sali = false;
    	int i=0;
        
    	while(i < this.nodos.getArray().length && !sali) {
        	if (((Nodo) this.nodos.getArray()[i]).equals(aux)) {
        		sali = true;
        	}
        	i++;
        }
    	
    	if (!sali)
    		return elimine;
    	
        
    	this.nodos.remove(i);
    	this.arcos.remove(i);
    	elimine = true;
    	return elimine;
    	
    }
    
    /*
     * Retorna un nuevo grafo que es una copia del grafo actual.
     * 
     */
    public Object clone() {
    	DigraphLista nuevo = new DigraphLista();
    	nuevo.nodos = this.nodos;
    	nuevo.arcos = this.arcos;
    	nuevo.numArcos = this.numArcos;
    	nuevo.numVertices = this.numVertices;
    	return nuevo;
    }

}

// End DigraphHash.
