import java.io.*;

public class Main {

	static Digraph grafo; 
	
	/**
	 * Funcion: obtenerGrafo
	 * Descripcion: lee el archivo y construye el grafo correspondiente.
	 * Parametros: inFile: archivo que leeremos, numLineas: numero de lineas
	 * del archivo que leeremos
	 */
	public static BufferedReader obtenerGrafo(BufferedReader inFile, 
											  int numLinea){
		boolean agregue;
		try{
			
			//leemos tantas lineas se nos indica que hay en el archivo
			for(int i = 0;i<numLinea;i++){
				
				/* obtenemos el nombre de la funcion en linea[0]
				 * luego linea[1] lo rompemos en tres cadenas mas que contendran
				 * nodo de inicio, nodo de fin y costo.
				 */
				
				String[] linea = inFile.readLine().split(": ");
				String[] info = linea[1].split(", ");
				Nodo nuev = new Nodo(info[0]);
				nuev.setSrc();
				//si el grafo no tiene el nodo lo agregamos. 
				if (!grafo.contains(info[0])) {
					agregue = grafo.add(nuev);
				}
				//si lo tiene nos aseguramos de marcarlo como un nodo de origen
				else
					grafo.get(info[0]).setSrc();
				
				Nodo nuev2 = new Nodo(info[1]);
				nuev2.setDst();
				
				//si el grafo no tiene el nodo lo agregamos
				if (!grafo.contains(info[1])){
					//System.out.println(nuev2.toString());
					agregue = grafo.add(nuev2);
				}
				//si lo tiene nos aseguramos de marcarlo como nodo de destino
				else 
					grafo.get(info[1]).setDst();
				
				// agregamos el nuevo arco al nodo.
				Arco nuevArc = new Arco(info[0],info[1],linea[0],Integer.parseInt(info[2]));
				grafo.add(nuevArc);
			}
			return inFile;
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Funcion: obtenerComposicion
	 * Descripcion: esta funcion dados el archivo de entrada y de salida 
	 * se encarga de buscar el camino de menor costo e imprimirlo en el archivo
	 * de salida para cada caso en el archivo de entrada.
	 */
	public static void obtenerComposicion(BufferedReader inFile,
			BufferedWriter outFile) {
		try{
			//leemos cuantos caminos tenemos que buscar dados por el archivo.
			int numLin = Integer.parseInt(inFile.readLine());
			
			//leemos tantas lineas como nos indique el numero anterior
			
			for(int i=0;i<numLin;i++) {
				String[] Nodos = inFile.readLine().split(", ");
				//llamamos a la funcion busCamino que nos devuelve el camino
				Lista<String> camino = busCamino(Nodos[0],Nodos[1]);
				
				//llamamos a la funcion imprimirEnArchivo para que imprima
				//la informacion de los caminos encontrados.
				imprimirEnArchivo(camino,Nodos[0],Nodos[1],outFile);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Funcion: busCamino
	 * Descripcion: se encarga de buscar el camino de menor costo entre el nodo
	 * de partida y el de llegada. devuelve una lista vacia si no existe tal
	 * camino.
	 */
	public static Lista<String> busCamino(String partida, String llegada) {
		
		Lista<String> cam = new MiLista<String>();
		Nodo nodoS = grafo.get(partida);
		Nodo nodoD = grafo.get(llegada);
		
		
		//si el nodo de partida no es un nodo fuente, devolvemos la lista vacia
		if (nodoS == null || !(nodoS.esSrc())){
			return cam;
		}
		
		//si el nodo de llegada no es un nodo destino, devolvemos la lista vacia
		if (nodoD == null || !(nodoD.esDst())) {
			return cam;
		}
		
		//buscamos mediante el algoritmo de Dijkstra el camino de menor costo
		cam = Dijkstra(grafo.get(partida),grafo.get(llegada));
		
		return cam;
	}
	
	
	/**
	 * Funcion: Dijkstra
	 * Descripcion: implementacion del algoritmo de Dijkstra para buscar el 
	 * camino de menor costo entre el nodo de partida n y el nodo de llegada d
	 * devuelve una lista vacia si no consigue tal camino.
	 */
	public static Lista<String> Dijkstra(Nodo n, Nodo d) {
		BinaryHeap<Nodo> abiertos = new BinaryHeap<Nodo>();
		int i = 0;
		Lista<String> camino = new MiLista<String>();
		
		/* inicializamos el campo visitado de todos los nodos como false y 
		 * para todos los nodos que no sean el nodo n (partida) inicializamos
		 * su costo en el valor mas grande que se puede representar de los 
		 * enteros 
		 */
		while (i < grafo.getNodos().toArray().length) {
			if (grafo.getNodos().toArray()[i] != n) {
				((Nodo) grafo.getNodos().toArray()[i]).setCost(Integer.MAX_VALUE);
			}
			((Nodo) grafo.getNodos().toArray()[i]).setVisitado(false);
			i++;
		}
		
		n.setCost(0);
		
		abiertos.agregar(n);
		
		//mientras la cola de prioridades no este vacia buscamos el camino
		while (!abiertos.esVacio()){
			
			Nodo actual = (Nodo) abiertos.getMin();
			
			abiertos.removeMin();
			actual.setVisitado(true);
			
			Lista<Arco> adyacentes = grafo.getOutArcos(actual.toString());
			i=(adyacentes.toArray().length - 1);
			
			//reviso todos los adyacentes de actual
			while (i >= 0 ) {
				Nodo ady = grafo.get(((Arco) adyacentes.toArray()[i]).getDst());
				//si el ady no esta visitado entonces puede que el camino este
				//pasando por el
				if (!ady.getVisitado()) {
					int costAux = actual.getCost() + 
							((Arco) adyacentes.toArray()[i]).getCosto();
					/* si el costo acumulado hasta ahora mas el costo de 
					 * movernos a ady es menor al costo acumulado de ady 
					 * entonces marcamos en el nodo ady a actual como su 
					 * ancestro e indicamos mediante cual funcion llegamos 
					 */
					if (costAux < ady.getCost()) {
						ady.setCost(costAux);
						ady.setFuncion((((Arco) adyacentes.toArray()[i]).getFuncion()));	
						ady.setAncestro(actual);
					}
					
					abiertos.agregar(ady);
					
				}
				
				i--;
			}
		}
		/* Aca construimos el camino como tal, partiendo del nodo final 
		 * revisamos su ancestro y agregamos al camino la funcion mediante la
		 * cual llegamos a el y asi vamos moviendonos hasta llegar al nodo de 
		 * origen. Esto nos devuelve el camino "al reves" ese problema es 
		 * resuelto al momento de imprimir.
		 */
		Nodo auxi = grafo.get(d.toString());
		if (auxi.getCost() == Integer.MAX_VALUE) {
			return camino;
		}
		camino.add(auxi.getFuncion());
		Nodo ancest = grafo.get(d.toString()).getAncestro();
		
		while (! ancest.equals(grafo.get(n.toString()))){
			auxi = ancest;
			camino.add(auxi.getFuncion());
			ancest = auxi.getAncestro();
		}
		
		
	
		return camino;
	}
	
	
	/**
	 * Funcion: imprimirEnArchivo
	 * Descripcion: esta funcion imprime en el archivo de salida el camino de
	 * menor costo para llegar del nodo fuente al nodo destino.
	 */
	public static void imprimirEnArchivo(Lista<String> camino,String n_Src, 
			String n_Dst,BufferedWriter outFile) {		
		
		Nodo Llegada = grafo.get(n_Dst);
		
		try {
			outFile.write("<");
			//en i estara la ultima posicion del arreglo
			int i=(camino.toArray().length-1);
			
			//si i es -1 es porque la secuencia era vacia (lista vacia)
			if (i==-1){
				outFile.write("> 0 \n");
			}
			//sino es porque hay un camino
			else{
				//vamos imprimiendo de la ultima posicion del arreglo a la
				//primera de este modo como teniamos el orden invertido 
				//estariamos imprimiendo en el orden correcto
				while(i>0) {
					outFile.write(camino.toArray()[i]+",");
					i--;
				}
				outFile.write(camino.toArray()[0]+"> ");
				outFile.write(Llegada.getCost()+"\n");
			}
		}
		catch (IOException e2) {
			e2.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		
	String in = "file.in";
	String out = "file.out";
	
	if(args.length == 2){
		in = args[0];
		out = args[1];
	}
	
	BufferedReader inFile = null;
	
	FileWriter sali = null;
	BufferedWriter outFile = null;
	
	try {
		sali = new FileWriter(new File(out));
	}
	catch (IOException e1){
		e1.printStackTrace();
	}
	
	outFile = new BufferedWriter(sali);
	
	try{
		inFile = new BufferedReader(new FileReader(in));
		
		grafo = new DigraphLista();
		
		int numLinea = Integer.parseInt(inFile.readLine());
		
		if(numLinea == 0){
			System.exit(1);
		}
		
		inFile = obtenerGrafo(inFile, numLinea);
		
		obtenerComposicion(inFile,outFile);
	
	}catch(IOException e){
		e.printStackTrace();
	}
	
	//cerramos el archivo
	try {
		outFile.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	}

}
