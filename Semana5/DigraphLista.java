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
    
    /**
     * Constructor para Digraph a partir de un grafo ya existente.
     */
    public DigraphLista(Digraph g){
    	if (g instanceof DigraphLista) {
    		this.nodos = ((DigraphLista) g).nodos;
        	this.arcos = ((DigraphLista) g).arcos;
        	this.numArcos = g.numArcos;
        	this.numVertices = g.numVertices;
    	}
    	else if (g instanceof DigraphMatriz) {
    		Lista<Nodo> listaNoditos = g.getNodos();
    		Lista<Arco> listaArcos = g.getArcos();
    		this.arcos = new DynamicArray();
    		this.nodos = new DynamicArray();
    		int i=0;
    		int j=0;
    		
    		while (i<listaNoditos.getSize()) {
    			this.add(((Nodo) listaNoditos.toArray()[i]));
    			i++;
    		}
    		
    		while (j<listaArcos.getSize()) {
    			this.add(((Arco) listaArcos.toArray()[j]));
    			j++;
    		}
    		
    	}
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
        	this.numArcos++;
            return true;
        }

    }
    
    /**
     * Funcion: add
     * Descripcion: Agrega el nodo n. Si el vertice ya existe, retorna false. Si
     * se agrega el nodo, retorna true.
     * Parametros: n, nodo que vamos a agregar
     * Precondicion: true
     * Postcondicion: retorna true si agregó el nodo, si es repetido retorna 
     * false
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
    		this.numVertices++;
    		agregue = true;
    	}
    	
    	return agregue;
    	
    }

    /**
     * Funcion: clear
     * Descripcion: Elimina los nodos y aristas del grafo.
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: se eliminan los nodos y arcos del grafo
     * devolviendolo a su estado original, como recien creado.
     */
    public  void clear(){
    	
    	this.nodos = new DynamicArray();
    	this.arcos = new DynamicArray();
    	this.numVertices =0;
    	this.numArcos=0;
    }

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

    /**
     * Funcion: contains
     * Descripcion: Chequea si el grafo contiene un nodo con id nod
     * Parametros: nod, string por el que vamos a chequear si existe el nodo.
     * Precondicion: true
     * Postcondicion: si el nodo con id nod existe retorna true, en caso
     * contrario retorna false.
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

    /**
     * Funcion: get
     * Descripcion: Retorna la arista del grafo que conecta a los vertices
     * src y dst. Si no existe dicha arista, retorna null.
     * Parametros: src y dst, strings que indican el vertice fuente y de 
     * destino del arco que queremos conseguir.
     * Precondicion: el grafo debe contener el arco (src,dst)
     * Postcondicion: retorna el arco (src,dst) si existe, sino retorna false
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
    

    /**
     * Funcion: getArcos
     * Descripcion: Retorna todas las aristas del grafo
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: retorna una lista con todos los 
     * arcos del grafo
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

    /**
     * Funcion: get
     * Descripcion: Retorna el nodo con id nod. Si no existe dicho nodo, 
     * retorna null.
     * Parametros: nod, string que posee el nodo que queremos buscar como id
     * Precondicion: true
     * Postcondicion: si el nodo con nod como id existe retorna ese nodo, 
     * en caso contrario retorna null
     */
    public Nodo get(String nod){
    	Nodo aux = new Nodo(nod);
    	int i=0;
        try {
        	while(!(((Nodo) this.nodos.getArray()[i]).equals(aux)))
        		i++;
         }
         catch(java.lang.ArrayIndexOutOfBoundsException bleh) {
         	return null;
         }
        return (Nodo) this.nodos.getArray()[i];
        
    }

    /**
     * Funcion: getNodos 
     * Descripcion: Retorna todos los nodos del grafo.
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: retorna una lista con todos los 
     * nodos del grafo.
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
    public  boolean remove(String nod){
    	Nodo aux = new Nodo(nod);
    	boolean elimine = false;
    	int i=0;
        
        try {
    		while(!(((Nodo) this.nodos.getArray()[i]).equals(aux))){
    			i++;
    		}
    	}
    	catch(java.lang.ArrayIndexOutOfBoundsException bleh) {
    		return elimine;
    	}
    	this.nodos.remove(i);
    	this.arcos.remove(i);
    	elimine = true;
    	return elimine;
    	
    }
    
    /**
     * Funcion: clone
     * Descripcion: Retorna un nuevo grafo que 
     * es una copia del grafo actual.
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: se copia la informacion del
     * grafo actual en uno nuevo.
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
