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

     /*
     * @see Constructor para Digraph.
     */
    public DigraphMatriz() {
	super();
	this.matriz = new Matriz();
	this.nodos = new DynamicArray();	
    }

    /*
     * Agrega la arista dada al grafo. Si los vertices de la arista
     * no existen o el grafo tiene una arista entre dichos vertices,
     * retorna false. Si se agrega la nueva arista, retorna true.
     */
    public  boolean add(Arco e){
    	int src = 0;
    	try{
    		while(!(((Nodo)this.nodos.getArray()[src]).equals(new Nodo(e.getSrc())))){
//    		while(!(e.getSrc().equalsIgnoreCase(dummy.toString()))){
    			src++;
    		}
    	}catch(java.lang.ArrayIndexOutOfBoundsException bla){
    		System.out.println("El origen del arco no existe");
    		return false;
    	}
    	
    	int dst = 0;
    	try{
    		while(!(((Nodo)this.nodos.getArray()[dst]).equals(new Nodo(e.getDst())))){
//    		while(!(e.getDst().equalsIgnoreCase(dummy.toString()))){
    			dst++;
    			}
    	}catch(java.lang.ArrayIndexOutOfBoundsException bla){
    		System.out.println("El destino del arco no existe");
    		return false;
    	}
    	if(this.matriz.esta(src, dst)){
    		return false;
    	}
    	this.matriz.add(src,dst, 1);
    	this.numArcos++;
    	return true;    	
    }

    /*
     * Agrega el nodo n. Si el vertice ya existe, retorna false. Si
     * se agrega el nodo, retorna true.
     */
    public  boolean add(Nodo n){
    	if(!(this.contains(n.toString()))){
    		if(this.numVertices != 0){
    			this.matriz.addFila();    			
    			this.matriz.addColumna();
    		}
    		this.nodos.addFinal(n);
    		this.numVertices++;
    		return((Nodo) this.nodos.getArray()[this.nodos.getArray().length-1] == n); 
    	}
    	return false;
    }

    /*
     * Elimina los nodos y aristas del grafo.
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

    /*
     * Chequea si el grafo contiene una arista del nodo src a dst
     */
    public  boolean contains(String src, String dst){
    	int i=0;
    	try{
    		while(!((Nodo)this.nodos.getArray()[i]).equals(new Nodo(src))){
//    		while(!(src.equalsIgnoreCase(this.nodos.getArray()[i].toString()))){
    			i++;
    		}
    	}catch(java.lang.ArrayIndexOutOfBoundsException bla){
    		System.out.println("El nodo de origen no existe");
    		return false;
    	}
    	int j=0;
    	try{
    		while(!((Nodo)this.nodos.getArray()[j]).equals(new Nodo(dst))){
//    		while(!(dst.equalsIgnoreCase(this.nodos.getArray()[j].toString()))){
    			j++;
    		}
    	}catch(java.lang.ArrayIndexOutOfBoundsException bla){
    		System.out.println("El nodo de llegada no existe");
    		return false;
    	}
    	return this.matriz.esta(i, j);
    }

    /*
     * Chequea si el grafo contiene un nodo con id nod
     */
    public boolean contains(String nod) {
    	int i=0;
    	try{
    		while(!((Nodo)this.nodos.getArray()[i]).equals(new Nodo(nod))){
//    		while(!(nod.equalsIgnoreCase(this.nodos.getArray()[i].toString()))){
    			i++;
    		}
    	}catch(java.lang.ArrayIndexOutOfBoundsException bla){
    		return false;
    	}
    	return true;
    }

    /*
     * Retorna la arista del grafo que conecta a los vertices
     * src y dst. Si no existe dicha arista, retorna null.
     */
    public  Arco get(String src, String dst){
    	if(this.contains(src, dst)){
    		return new Arco(src,dst);
    	}else{
    		return null;
    	}
    }

    /*
     *Retorna todas las aristas del grafo
     */
    public  Lista<Arco> getArcos(){
    	MiLista<Arco> lista = new MiLista<Arco>();
    	for(int i=0;i<this.numVertices;i++){
    		for(int j=0;j<this.numVertices;j++){
    			if(this.matriz.esta(i, j)){
    				lista.add(this.get(this.nodos.getArray()[i].toString(),
    						this.nodos.getArray()[j].toString()));
    			}
    		}
    	}
    	return lista;
    }

    /*
     * Retorna el nodo con id nod. Si no existe dicho nodo, 
     * retorna null.
     */
    public Nodo get(String nod){
    	if(this.contains(nod)){
    		return new Nodo(nod);
    	}
    	return null;
    }

    /* 
     * Retorna todos los nodos del grafo.
     */
    public  Lista<Nodo> getNodos(){
    	MiLista<Nodo> lista = new MiLista<Nodo>();
    	
    	for(int i=0;i<this.nodos.getArray().length;i++){
    		lista.add((Nodo)this.nodos.getArray()[i]);
    	}
    	return lista;
    }

    /*
     * Retorna la lista de lados que tienen al vertice dado como
     * destino. Si el vertice no existe, retorna null.
     */
    public  Lista<Arco> getInArcos(String nodo){
    	if(this.contains(nodo)){
    		MiLista<Arco> lista = new MiLista<Arco>();
    		for(int i=0;i<this.numVertices;i++){
    			if(null != this.get(this.nodos.getArray()[i].toString(), nodo)){
    				lista.add(this.get(this.nodos.getArray()[i].toString(),nodo));
    			}
    		}    		
    		return lista;
    	}
    	return null;
    }

    /*
     * Retorna la lista de lados que tienen al vertice dado como
     * origen. Si el vertice no existe, retorna null.
     */
    public  Lista<Arco> getOutArcos(String nodo){
    	if(this.contains(nodo)){
    		MiLista<Arco> lista = new MiLista<Arco>();
    		for(int i=0;i<this.numVertices;i++){
    			if(null != this.get(nodo,this.nodos.getArray()[i].toString())){
    				lista.add(this.get(nodo,this.nodos.getArray()[i].toString()));
    			}
    		}    		
    		return lista;
    	}
    	return null;
    }

    /*
     * Remueve la arista del grafo que conecta a los vertices src y
     * dst. Si el grafo no cambia, retorna false. Si el grafo cambia,
     * retorna true.
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
    		System.out.println("El origen del arco no existe");
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
    		System.out.println("El origen del arco no existe");
    		return false;
    	}
    	
    	this.matriz.add(i, j,0);

    	if(antes.getSize() != this.getArcos().getSize()){
    		this.numArcos--;
    	}
    	return (antes.getSize() != this.getArcos().getSize());
    }

    /*
     * Remueve el nodo del grafo el nodo nod y todas las aristas a las
     * que esta conectado. Si el grafo no cambia, retorna false. Si el
     * grafo cambia, retorna true.
     */
    public  boolean remove(String nod){
    	int arcosEliminar = 0;
    	if(this.contains(nod)){    		
    		int i =0;
    		for(i=0;i<this.getArcos().toArray().length;i++){
    			Arco dummy = (Arco) this.getArcos().toArray()[i];
    		
    			if(dummy.getSrc().equalsIgnoreCase(nod) || dummy.getDst().equalsIgnoreCase(nod)){
    				arcosEliminar += 1;
    			}    			
    		}
    		
    		i = 0;    		
			while(!(nod.equalsIgnoreCase(this.nodos.getArray()[i].toString()))){
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
    
    /*
     * Retorna un nuevo grafo que es una copia del grafo actual.
     * 
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
