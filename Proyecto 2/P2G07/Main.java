import java.io.*;

public class Main {

	static Lista<Digraph> lista = new MiLista<Digraph>();
	static Lista<Lista<String>> listaPer = new MiLista<Lista<String>>();
	
	
	/**
	 * Funcion: BFS
	 * Descripcion: Etiqueta cada nodo del grafo asignandole su numero de Erdös
	 * Parametros: grafo: Grafo al que aplicaremos BFS
	 * Precondicion: el grafo contiene a Erdös
	 * Postcondicion: se ha etiquetado a cada nodo asignandole su número de 
	 * Erdös correspondiente.
	 */
	public static void BFS(Digraph grafo){
		Cola cola = new Cola(grafo.numVertices);
		
		//Solo etiquetaremos el grafo si este contiene a Erdos.
		if(grafo.contains("ErdosP")){
						
			grafo.get("ErdosP").setErdos(0);  //asignamos erdos 0 a Erdös
						
			cola.encolar(grafo.get("ErdosP"));
			
			int i=0;
			
			//Marcamos a Erdös como visitado.
			grafo.get("ErdosP").setVisitado(true);
			
			while(!cola.esVacia()){
				
				Nodo dummy = (Nodo) cola.primero();
				cola.desencolar();
				
				int degree = grafo.getOutDegree(dummy.toString());
				
				//Encolamos todos los nodos que tienen al nodo actual como nodo
				//de llegada.
				for(i=0;i<degree;i++){
					
					Lista<Arco> arcos = grafo.getInArcos(dummy.toString());
					String src = ((Arco)arcos.toArray()[i]).getSrc();
	
					//si no esta visitado lo marcamos como tal y le asignamos 
					//su numero de erdos.

					if(!grafo.get(src).getVisitado()){
						grafo.get(src).setVisitado(true);
						grafo.get(src).setErdos(Math.min(grafo.get(src).getErdos(),
								grafo.get(dummy.toString()).getErdos()+1));
						cola.encolar(grafo.get(src));
					}
				}
			}
		}
	}
	
	/**
	 * Funcion: cargarDatos
	 * Descripcion: carga los datos del archivo para construir un grafo
	 * con el cual buscaremos el camino hasta llegar a Erdös y se imprime esa
	 * secuencia en el archivo de salida.
	 * Parametros: fileIn: nombre del archivo de entrada, fileOut: nombre del
	 * archivo de salida.
	 * Precondicion: el archivo de entrada debe tener un formato valido
	 * Postcondicion: se imprime en el archivo de salida la secuencia seguida 
	 * para llegar a Erdös desde cada persona especificada en el archivo de 
	 * entrada
	 */
	public static void cargarDatos(String fileIn, String fileOut){
		String linea = "";
		BufferedReader in = null;
		Digraph grafo = new DigraphLista();
		Lista<String> listaNodo = new MiLista<String>();
		FileWriter sali = null;
		
		try {
			sali = new FileWriter(new String(fileOut));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BufferedWriter salida = new BufferedWriter(sali);
		
		try {
			in = new BufferedReader(new FileReader(fileIn));
			
			//leemos en el archivo el numero de casos que tendremos.
			int numCasos = Integer.parseInt(in.readLine());
			
			for(int num = numCasos;num>0;num--){
				linea = in.readLine();
				
				//leemos el numero de publicaciones y el numero de personas.
				String[] numeros = linea.split(" ");
				
				int numArt = Integer.parseInt(numeros[0]);
				int numPer = Integer.parseInt(numeros[1]);
				
				//leemos la informacion de los articulos.
				for(int i=0;i<numArt;i++){
					linea = in.readLine();
					
					//Almacenamos la linea actual que leemos
					String[] palabras = linea.split(" ");
					
					//veo la informacion de la linea leida, si son autores se 
					//guardan en la lista de nodos.
					for(int j=0;j<palabras.length;j++){
						if(palabras[j].toCharArray()[palabras[j].length()-1] 
								== ',' || palabras[j].toCharArray()[palabras[j].
								length()-1] == ':') {
							
							if(palabras[j].toCharArray()[palabras[j].length()-2]
									== '.'){
								listaNodo.add(palabras[j].split(",")[0].
										split(".:")[0]);
							}else{
								listaNodo.add(palabras[j].split(",")[0].
										split(":")[0]);
							}	
						}
					}
				
					//agrego los nodos buscados anteriormente al grafo
					for(int k = 0;k<listaNodo.toArray().length;k++){
						Nodo dummy = new Nodo((String) listaNodo.toArray()[k]);
						grafo.add(dummy);
					}
					
					// agrego los arcos al grafo
					for(int k = 0;k<listaNodo.toArray().length;k++){
						for(int l = 0;l<listaNodo.toArray().length;l++){
							grafo.add(new Arco((String) listaNodo.
							 toArray()[k],(String) listaNodo.toArray()[l]));
						}
					}
					
					//limpio la informacion de la lista de nodos para la 
					//proxima iteracion
					listaNodo.clear();
				}			

				//Etiqueto el grafo creado con BFS para asignar Erdös
				BFS(grafo);
				
				/* Aqui procederemos a buscar el camino del grafo actual desde
				 * cada una de las personas que se piden en el archivo hasta 
				 * llegar a Erdös y se imprimiran en el archivo.
				 */
				
				//Imprimo el escenario en el que estamos
				try {
					salida.write("Escenario "+(numCasos - num +1)+"\n");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
								
				//Leo el archivo y voy revisando para cada persona su camino 
				//hasta Erdös
				for(int i=0;i<numPer;i++){
					linea= in.readLine();
										
					Nodo perAct = grafo.get(linea);
					
					//imprimo en el archivo la persona actual de la que busco
					//el camino

					try {
						salida.write(perAct.toString()+": <");
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					//si la persona tiene un numero de erdos valido buscamos 
					//su camino.
					if(perAct.getErdos()< 2147483647){
						while (perAct.getErdos() != 0){
						
							//imprimimos el nodo actual en el archivo.
							try {
								salida.write(perAct.toString()+", ");
							} catch (IOException e) {
								e.printStackTrace();
							}

							//creamos una lista con los sucesores del nodo.
							Lista<Nodo> dummyLista = grafo.getSucs(perAct.toString());
													
							int k =0;
							
							//buscamos un sucesor con erdos menor al de la 
							//persona actual
							while(perAct.getErdos() <=
							((Nodo)dummyLista.toArray()[k]).getErdos()){
								k++;
							}
							
							// este nodo sera nuestro nuevo actual.
							perAct = (Nodo) dummyLista.toArray()[k];

						}
							
						/* Cuando finalizamos es porque llegamos a Erdös por 
						 * lo que imprimimos su nombre en el archivo y termina
						 * la busqueda del camino
						 */
						try {
							salida.write
							(perAct.toString()+">\n");
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						/* Si no tiene un numero valido es porque esta persona
						 * no tiene un camino hasta Erdös. Por lo que imprimimos
						 * una secuencia vacia en el archivo.
						 */
						}else{
							
							try {
								salida.write(">\n");
							} catch (IOException e) {
								e.printStackTrace();
							}
							
						}					
				}
				
				//asignamos un grafo vacio a grafo para la proxima iteracion 
				grafo = new DigraphLista();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
		
	public static void main(String[] args) {
		String in = "file.in";
		String out = "file.out";
		
		if(args.length == 2){
			in = args[0];
			out = args[1];
		}
		
		cargarDatos(in, out);
		
				
	}
}
