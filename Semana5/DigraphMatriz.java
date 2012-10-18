/**
 * 
 * @author Gabriela Limonta, Luis Miranda
 * 
 * Implementa la clase abstracta Digraph utilizando dos tablas de
 * hash, una para vertices y una para arcos.
 */
public class DigraphMatriz extends Digraph {

    private  DynamicArray nodos;
    private Matriz matriz;

    /**
     * @see Constructor para Digraph.
     */
    public DigraphMatriz() {
	super();
	this.matriz = new Matriz();
	this.nodos = new DynamicArray();	
    }
    
    /**
     * Constructor para DigraphMatriz a partir de un grafo existente
     */
    public DigraphMatriz(Digraph g) {
    	if (g instanceof DigraphMatriz) {
    		this.matriz = ((DigraphMatriz) g).matriz;
    		this.nodos = ((DigraphMatriz) g).nodos;
    		this.numArcos = g.numArcos;
    		this.numVertices = g.numVertices;
    	}
    	else if (g instanceof DigraphLista) {
    		Lista<Nodo> listaNoditos = g.getNodos();
    		Lista<Arco> listaArcos = g.getArcos();
    		this.matriz = new Matriz();
    		this.nodos = new DynamicArray();
    		int i=0;
    		int j=0;
    		
    		while (i<listaNoditos.getSize()) {
    			this.add((Nodo) listaNoditos.toArray()[i]);
    			i++;
    		}
    		
    		while (j<listaArcos.getSize()) {
    			this.add((Arco) listaArcos.toArray()[j]);
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
    public  boolean add(Arco e){
    	int src = 0;
    	try{
    		while(!(((Nodo)this.nodos.getArray()[src]).equals(new 
							Nodo(e.getSrc())))){
    			src++;
    		}
    	}catch(java.lang.ArrayIndexOutOfBoundsException bla){
    		return false;
    	}
    	
    	int dst = 0;
    	try{
    		while(!(((Nodo)this.nodos.getArray()[dst]).equals(
							new Nodo(e.getDst())))){
    			dst++;
    			}
    	}catch(java.lang.ArrayIndexOutOfBoundsException bla){
    		return false;
    	}
    	if(this.matriz.esta(src, dst)){
    		return false;
    	}
    	this.matriz.add(src,dst, 1);
    	this.numArcos++;
    	return true;    	
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
    	if(!(this.contains(n.toString()))){
    		if(this.numVertices != 0){
    			this.matriz.addFila();    			
    			this.matriz.addColumna();
    		}
    		this.nodos.addFinal(n);
    		this.numVertices++;
    		return((Nodo) 
		    this.nodos.getArray()[this.nodos.getArray().length-1] == n); 
    	}
    	return false;
    }

    /**
     * Funcion: clear
     * Descripcion: Elimina los nodos y aristas del grafo.
     * Parametros: N/A
     * Precondicion: true
     * Postcondicion: se eliminan los nodos y arcos del grafo
     * devolviendolo a su estado original, como recien creado.
     */
    public void clear(){
    	for(int i=0;i<this.numVertices-1;i++){
    		this.nodos.remove(0);
    		this.matriz.removeFila(0);    		
    	}
    	this.nodos.remove(0);
    	for(int i=0;i<this.numVertices-1;i++){
    		this.matriz.removeColumna(0);
    	}
    	this.numVertices =0;
    	this.numArcos=0;
    	this.matriz.add(0, 0,0);
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
    public  boolean contains(String src, String dst){
    	int i=0;
    	try{
    		while(!((Nodo)this.nodos.getArray()[i]).equals(new Nodo(src))){
    			i++;
    		}
    	}catch(java.lang.ArrayIndexOutOfBoundsException bla){
    		return false;
    	}
    	int j=0;
    	try{
    		while(!((Nodo)this.nodos.getArray()[j]).equals(new Nodo(dst))){
    			j++;
    		}
    	}catch(java.lang.ArrayIndexOutOfBoundsException bla){
    		return false;
    	}
    	return this.matriz.esta(i, j);
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
    	int i=0;
    	try{
    		while(!((Nodo)this.nodos.getArray()[i]).equals(new Nodo(nod))){
    			i++;
    		}
    	}catch(java.lang.ArrayIndexOutOfBoundsException bla){
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
    public  Arco get(String src, String dst){
    	if(this.contains(src, dst)){
    		return new Arco(src,dst);
    	}else{
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
    public  Lista<Arco> getArcos(){
    	MiLista<Arco> lista = new MiLista<Arco>();
    	for(int i=0;i<this.numVertices;i++){
    		for(int j=0;j<this.numVertices;j++){
    			if(this.matriz.esta(i, j)){
    				lista.add(this.get(
					this.nodos.getArray()[i].toString(),
    					this.nodos.getArray()[j].toString()));
    			}
    		}
    	}
    	return lista;
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
    	if(this.contains(nod)){
    		return new Nodo(nod);
    	}
    	return null;
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
    	MiLista<Nodo> lista = new MiLista<Nodo>();
    	
    	for(int i=0;i<this.nodos.getArray().length;i++){
    		lista.add((Nodo)this.nodos.getArray()[i]);
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
    public  Lista<Arco> getInArcos(String nodo){
    	if(this.contains(nodo)){
    		MiLista<Arco> lista = new MiLista<Arco>();
    		for(int i=0;i<this.numVertices;i++){
    			if(null != this.get(this.nodos.getArray()[i].toString(), 
									nodo)){
    				lista.add(this.get(
				     this.nodos.getArray()[i].toString(),nodo));
    			}
    		}    		
    		return lista;
    	}
    	return null;
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
    public  Lista<Arco> getOutArcos(String nodo){
    	if(this.contains(nodo)){
    		MiLista<Arco> lista = new MiLista<Arco>();
    		for(int i=0;i<this.numVertices;i++){
    			if(null != this.get(nodo,
					this.nodos.getArray()[i].toString())){
    				lista.add(this.get(nodo,
					this.nodos.getArray()[i].toString()));
    			}
    		}    		
    		return lista;
    	}
    	return null;
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
    public  boolean remove(String src, String dst){
    	int i = 0;
    	Lista<Arco> antes = this.getArcos();
    	Nodo dummy = (Nodo) this.nodos.getArray()[i];

    	try{
    		while(!(src.equalsIgnoreCase(dummy.toString()))
    				){
    			i++;
    			dummy = (Nodo) this.nodos.getArray()[i];
    		}
    	}catch(java.lang.ArrayIndexOutOfBoundsException bla){
    		return false;
    	}
    	
    	int j = 0;
    	dummy = (Nodo) this.nodos.getArray()[j];
    	try{
    		while(!(dst.equalsIgnoreCase(dummy.toString()))
    				){
    			j++;
    			dummy = (Nodo) this.nodos.getArray()[j];
    			}
    	}catch(java.lang.ArrayIndexOutOfBoundsException bla){
    		return false;
    	}
    	
    	this.matriz.add(i, j,0);

    	if(antes.getSize() != this.getArcos().getSize()){
    		this.numArcos--;
    	}
    	return (antes.getSize() != this.getArcos().getSize());
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
    	int arcosEliminar = 0;
    	if(this.contains(nod)){    		
    		int i =0;
    		for(i=0;i<this.getArcos().toArray().length;i++){
    			Arco dummy = (Arco) this.getArcos().toArray()[i];
    		
    			if(dummy.getSrc().equalsIgnoreCase(nod) || 
					dummy.getDst().equalsIgnoreCase(nod)){
    				arcosEliminar += 1;
    			}    			
    		}
    		
    		i = 0;    		
			while(!(nod.equalsIgnoreCase(
					this.nodos.getArray()[i].toString()))){
				i++;
			}
			
			this.nodos.remove(i);
			this.matriz.removeColumna(i);
			this.matriz.removeFila(i);
    	}    		
    	this.numArcos -= arcosEliminar;
    	this.numVertices--;
    	return this.contains(nod);
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
    public Object clone(){
    	DigraphMatriz nuevo = new DigraphMatriz();
    	nuevo.matriz = this.matriz;
    	nuevo.nodos = this.nodos;
    	nuevo.numArcos = this.numArcos;
    	nuevo.numVertices = this.numVertices;
    	return nuevo;
    }

}

// End DigraphHash.
