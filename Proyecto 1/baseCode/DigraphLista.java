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
        Nodo noditoS = new Nodo(src);
        Nodo noditoD = new Nodo(e.getDst());
        int i = 0;
        
        int j=0;
        try {
        	while(!(((Nodo) this.nodos.getArray()[j]).equals(noditoD)))
        		j++;
        }
        catch(java.lang.ArrayIndexOutOfBoundsException bleh) {
        	return false;
        }
        i=0;
        try {
    		while(!(((Nodo) this.nodos.getArray()[i]).equals(noditoS)))
    			i++;
    	}
    	catch(java.lang.ArrayIndexOutOfBoundsException blah) {
    		return false;
    	}
    	
        
        if (((Lista<Arco>) this.arcos.getArray()[i]).contains(e))
        	return false;
        else {
        	((Lista<Arco>) this.arcos.getArray()[i]).add(e);
            return true;
        }

    }
    
    /*
     * Agrega el nodo n. Si el vertice ya existe, retorna false. Si
     * se agrega el nodo, retorna true.
     */
    public  boolean add(Nodo n){
    	int i=0;
    	boolean agregue = false;
    
    	try {
    		while(!(((Nodo) this.nodos.getArray()[i]).equals(n)))
    			i++;
    	}
    	catch(java.lang.ArrayIndexOutOfBoundsException blah) {
    		this.nodos.addFinal(n); 
    		this.arcos.addFinal(new MiLista<Arco>());
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
        int i=0;
        int j=0;
        
        try {
        	while(!(((Nodo) this.nodos.getArray()[j]).equals(noditoD)))
        		j++;
        }
        catch(java.lang.ArrayIndexOutOfBoundsException bleh) {
        	return false;
        }
        i=0;
        try {
    		while(!(((Nodo) this.nodos.getArray()[i]).equals(noditoS)))
    			i++;
    	}
    	catch(java.lang.ArrayIndexOutOfBoundsException blah) {
    		return false;
    	}
        
        Arco aux = new Arco(src,dst);
        //((Lista<Arco>) this.arcos.getArray()[i]).imprimirLista();
        if (!((Lista<Arco>) this.arcos.getArray()[i]).contains(aux)) {
        	//System.out.println("lalala");
        	return false;
        }
        else{
        	//System.out.println("lelele");
        	return true;
        }
    }

    /*
     * Chequea si el grafo contiene un nodo con id nod
     */
    public boolean contains(String nod) {
		Nodo aux = new Nodo(nod);
        int i=0;
        
        try {
        	while(!(((Nodo) this.nodos.getArray()[i]).equals(aux)))
        		i++;
        }
        catch(java.lang.ArrayIndexOutOfBoundsException bleh) {
        	return false;
        }
		return true;
    }

    /*
     * Retorna la arista del grafo que conecta a los vertices
     * src y dst. Si no existe dicha arista, retorna null.
     */

    @SuppressWarnings("unchecked")
	public  Arco get(String src, String dst){
        Nodo auxS = new Nodo(src);
        Nodo auxD = new Nodo(dst);
        int i=0;
        
        try {
        	while(!(((Nodo) this.nodos.getArray()[i]).equals(auxD)))
        		i++;
        }
        catch(java.lang.ArrayIndexOutOfBoundsException bleh) {
        	return null;
        }
        
        i=0;
        try {
        	while(!(((Nodo) this.nodos.getArray()[i]).equals(auxS)))
        		i++;
        }
        catch(java.lang.ArrayIndexOutOfBoundsException bleh) {
        	return null;
        }
        
        Arco nuevo = new Arco(src,dst);
        if (((Lista<Arco>) this.arcos.getArray()[i]).contains(nuevo))
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
        if(!this.nodos.existe(aux)) 
        	return null;
        else {
        	int i=0;
        	while( i < this.nodos.getArray().length && this.nodos.getArray()[i] != aux)
        		i++;
        	return (Nodo) this.nodos.getArray()[i];
        }
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
    	int i=0;
        
        try {
    		while(!(((Nodo) this.nodos.getArray()[i]).equals(aux)))
    			i++;
    	}
    	catch(java.lang.ArrayIndexOutOfBoundsException blah) {
    		return null;
    	}
        
        i=0;
        Lista<Arco> listaSal = new MiLista<Arco>();
        
        while (i < this.nodos.getArray().length) {
        	Lista<Arco> listaTemp = new MiLista<Arco>();
        	listaTemp = ((Lista<Arco>) this.arcos.getArray()[i]);
        	Caja<Arco> cajaAux = listaTemp.obtenerPrimero();
        	while (cajaAux != null) {
        		if ((cajaAux.obtenerCont()).getDst() == nodo)
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
    	int i=0;
        
        try {
    		while(!(((Nodo) this.nodos.getArray()[i]).equals(aux)))
    			i++;
    	}
    	catch(java.lang.ArrayIndexOutOfBoundsException blah) {
    		return null;
    	}
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
        int i=0;
        int j=0;
        
        try {
        	while(!(((Nodo) this.nodos.getArray()[j]).equals(noditoD)))
        		j++;
        }
        catch(java.lang.ArrayIndexOutOfBoundsException bleh) {
        	return elimine;
        }
        i=0;
        try {
    		while(!(((Nodo) this.nodos.getArray()[i]).equals(noditoS)))
    			i++;
    	}
    	catch(java.lang.ArrayIndexOutOfBoundsException blah) {
    		return elimine;
    	}
    	
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
    	System.out.println("al menos entre");
    	Nodo aux = new Nodo(nod);
    	boolean elimine = false;
    	int i=0;
        
        try {
        	System.out.println("entre en try");
    		while(!(((Nodo) this.nodos.getArray()[i]).equals(aux))){
    			System.out.println("i: "+i);
    			i++;
    		}
    	}
    	catch(java.lang.ArrayIndexOutOfBoundsException bleh) {
    		System.out.println("no elimineeee");
    		return elimine;
    	}
        System.out.println("voy a eliminar");
    	this.nodos.remove(i);
    	System.out.println("ya quite los nodos");
    	this.arcos.remove(i);
    	System.out.println("ya quite los arcos");
    	elimine = true;
    	System.out.println("truuuuu");
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
