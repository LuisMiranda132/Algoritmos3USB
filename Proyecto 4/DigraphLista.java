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
    	nodos.crecer(2);
    	arcos.crecer(2);
    }

    protected class leArco{
    	
    	private MiLista<Arco> out;
    	private MiLista<Arco> in;
    	
    	protected leArco(){
    		this.out = new MiLista<Arco>();
    		this.in = new MiLista<Arco>();
    	}
    	
    	protected void addIn(Arco a){
    		this.in.add(a);
    	}
    	
    	protected void addOut(Arco a){
    		this.out.add(a);
    	}
    	
    	protected MiLista<Arco> getOut(){
    		return this.out;
    	}
    	
    	protected MiLista<Arco> getIn(){
    		return this.in;
    	}
    	
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
        int i = 0, j = 0;
        
        i = this.nodos.binarySearchPos(noditoS);
        
        if(i==-1)return false;
        
        j = this.nodos.binarySearchPos(noditoD.toString());
        if (j==-1)return false;        
        
        if (this.contains(e.getSrc(), e.getDst())) {
        	return false;
        }
        else {
        	
        	((leArco) this.arcos.getArray()[i]).addOut(e);
        	((leArco) this.arcos.getArray()[j]).addIn(e);
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
    		int pos = this.nodos.addOrd(n);
    		this.arcos.addOrdMov(new leArco(),pos);
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
	public  boolean contains(String src, String dst){
        Nodo noditoS = new Nodo(src);
        int i=0;
        
        i = this.nodos.binarySearchPos(noditoS);
        
        if(i==-1)return false;
        
        if(!this.contains(dst))return false;
        
        Arco aux = new Arco(src,dst);
        MiLista<Arco> lista = ((leArco) this.arcos.getArray()[i]).getOut();
        if(lista.contains(aux))return true;
        return false;
    }

    /*
     * Chequea si el grafo contiene un nodo con id nod
     */
    public boolean contains(String nod) {
		Nodo aux = new Nodo(nod);
		return aux.equals(this.nodos.binarySearch(aux));

    }

    /*
     * Retorna la arista del grafo que conecta a los vertices
     * src y dst. Si no existe dicha arista, retorna null.
     */

	public  Arco get(String src, String dst){
        Nodo auxD = new Nodo(dst);
        
        if(!this.contains(src, dst))return null;
        
        int i = this.nodos.binarySearchPos(auxD);
        
        MiLista<Arco> lista = ((leArco) this.arcos.get(i)).getIn();
        return (Arco) lista.linearSearch(new Arco(src, dst));
        
	}
    

    /*
     *Retorna todas las aristas del grafo
     */

    public  Lista<Arco> getArcos(){
    	int i=0;
        
        MiLista<Arco> listaSal = new MiLista<Arco>();
        
        while (i < this.nodos.getPosicion()) {
        	Lista<Arco> listaTemp = new MiLista<Arco>();
        	listaTemp = ((leArco) this.arcos.getArray()[i]).getOut();
        	
        	for(Object a: listaTemp.toArray()){
        		if(a != null){
        			listaSal.add((Arco)a);
        		}
        	}
        	
/*        	listaTemp = (Lista<Arco>) this.arcos.getArray()[i];
        	Caja<Arco> cajaAux = listaTemp.obtenerPrimero();
        	while (cajaAux != null) {
        		listaSal.addOrdenado(cajaAux.obtenerCont());
        		cajaAux = cajaAux.obtenerSiguiente();
        	}
*/
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
    	
        return (Nodo) this.nodos.binarySearch(aux);
        
    }

    /* 
     * Retorna todos los nodos del grafo.
     */
    public  Lista<Nodo> getNodos(){
        MiLista<Nodo> lista = new MiLista<Nodo>();
        int i=0;
        
        while(i < this.nodos.getPosicion()) {
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
    	Nodo aux = new Nodo(nodo);
    	
    	int pos = this.nodos.binarySearchPos(aux);

    	return ((leArco)this.arcos.getArray()[pos]).getIn(); 	        
    }

    /*
     * Retorna la lista de lados que tienen al vertice dado como
     * origen. Si el vertice no existe, retorna null.
     */
    public  Lista<Arco> getOutArcos(String nodo){
    	Nodo aux = new Nodo(nodo);
    	
    	int pos = this.nodos.binarySearchPos(aux);

    	return ((leArco)this.arcos.getArray()[pos]).getOut(); 
    }

    /*
     * Remueve la arista del grafo que conecta a los vertices src y
     * dst. Si el grafo no cambia, retorna false. Si el grafo cambia,
     * retorna true.
     */
	public  boolean remove(String src, String dst){
        Nodo noditoS = new Nodo(src);
        Nodo noditoD = new Nodo(dst);
        int i = 0, j = 0;
        
        i = this.nodos.binarySearchPos(noditoS);
        
        if(i==-1)return false;
        
        j = this.nodos.binarySearchPos(noditoD.toString());
        if (i==-1)return false;        
        
        if (!this.contains(src, dst)) {
        	return false;
        }
        else {
        	
        	Arco e = new Arco(src, dst);
        	
        	((leArco) this.arcos.getArray()[i]).getOut().remove(e);
        	((leArco) this.arcos.getArray()[j]).getIn().remove(e);
            this.numArcos++;
        	return true;
        }
    }

    /*
     * Remueve el nodo del grafo el nodo nod y todas las aristas a las
     * que esta conectado. Si el grafo no cambia, retorna false. Si el
     * grafo cambia, retorna true.
     */
    public  boolean remove(String nod){
    	Nodo aux = new Nodo(nod);
    	
    	int i;
		if((i = this.nodos.binarySearchPos(aux)) == -1)return false;
    	
		this.nodos.removeOrdMov(i);
		this.arcos.removeOrdMov(i);
		
    	return true;
    	
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
