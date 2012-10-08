/**
 * 
 * @author Gabriela Limonta, Luis Miranda
 * 
 * Implementa la clase abstracta Digraph utilizando dos tablas de
 * hash, una para vertices y una para arcos.
 */
public class DigraphLista extends Digraph {

    private  DynamicArray nodos;
    private  DynamicArray arcos;  //Lista<Arco>

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
    	
        Lista<Arco> nuevList = new MiLista<Arco>();
        
        /* Esta agregando repetidos los arcos revisar esto */
        if (((Lista<Arco>) this.arcos.getArray()[i]).contains(e))
        	return false;
        else {
        	((Lista<Arco>) this.arcos.getArray()[i]).add(e);
        	//((MiLista<Arco>) this.arcos.getArray()[i]).imprimirLista();
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
    public  Arco get(String src, String dst){
        Nodo aux = new Nodo(src);
        if(!this.nodos.existe(aux))
        	return null;
        else {
        	int i=0;
        	while( i < this.nodos.getArray().length && this.nodos.getArray()[i] != aux)
        		i++;
        	Arco nuevo = new Arco(src,dst);
        	if (!( (Lista<Arco>) this.arcos.getArray()[i]).contains(nuevo))
        		return null;
        	else {
        		return nuevo;
        	}
        }
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
    public  Lista<Arco> getInArcos(String nodo){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
     * Retorna la lista de lados que tienen al vertice dado como
     * origen. Si el vertice no existe, retorna null.
     */
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
        //((Lista<Arco>) this.arcos.getArray()[i]).imprimirLista();
        if (!((Lista<Arco>) this.arcos.getArray()[i]).contains(aux)) {
        	//System.out.println("lalala");
        	return elimine;
        }
        else{
        	//System.out.println("lelele");
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
    	int i=0;
        
        try {
    		while(!(((Nodo) this.nodos.getArray()[i]).equals(aux)))
    			i++;
    	}
    	catch(java.lang.ArrayIndexOutOfBoundsException blah) {
    		return elimine;
    	}
    	
    	this.nodos.remove(i);
    	this.arcos.remove(i);
    	elimine = true;
    	return elimine;
    }

}

// End DigraphHash.
