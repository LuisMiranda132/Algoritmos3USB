import java.io.*;

public class Main {

	static Lista<Digraph> lista = new MiLista<Digraph>();
	static Lista<Lista<String>> listaPer = new MiLista<Lista<String>>();
	
	/**
	 * Etiqueta los nodos de los grafos asignandoles
	 * su numero de Erdös mediante BFS.
	 */
	public static void BFS(Digraph grafo){
		Cola cola = new Cola(grafo.numVertices);
		boolean[] visitado = new boolean[grafo.numVertices];
		
		if(grafo.contains("ErdosP")){
						
			grafo.get("ErdosP").setErdos(0);
						
			cola.encolar(grafo.get("ErdosP"));
			
			int i=0;
			// buscamos el nodo ErdosP para marcarlo como visitado.
			while(!"ErdosP".equalsIgnoreCase((grafo.getNodos().toArray()[i]).
					toString())){
				i++;
			}
			visitado[i] = true;
			
			while(!cola.esVacia()){
				
				Nodo dummy = (Nodo) cola.primero();
				cola.desencolar();
				
				for(i=0;i<grafo.getOutDegree(dummy.toString());i++){
					
					Lista<Arco> arcos = grafo.getInArcos(dummy.toString());
					String src = ((Arco)arcos.toArray()[i]).getSrc();
					
					int j = 0;
					//buscamos el nodo el el grafo
					while(!src.equalsIgnoreCase((grafo.getNodos().toArray()[j]).
							toString())){
						j++;
					}
					
					//si no esta visitado lo marcamos como tal y le asignamos 
					//su numero de erdos.
					if(!visitado[j]){
						visitado[j]=true;
						grafo.get(src).setErdos(Math.min(grafo.get(src).getErdos(),
								grafo.get(dummy.toString()).getErdos()+1));
						cola.encolar(grafo.get(src));
					}
				}
			}
		}
	}
	
	/**
	 * Carga los datos del archivo fileIn a los grafos.
	 */
	public static void cargarDatos(String fileIn){
		String linea = "";
		BufferedReader in = null;
		Digraph grafo = new DigraphLista();
		Lista<String> listaNodo = new MiLista<String>();
		Lista<String> listaGente = new MiLista<String>();
		
		try {
			in = new BufferedReader(new FileReader(fileIn));
			
			int numCasos = Integer.parseInt(in.readLine());
		
			for(int num = numCasos;num>0;num--){
				linea = in.readLine();
				int numArt = linea.toCharArray()[0]-48;
				int numPer = linea.toCharArray()[2]-48;
			
				for(int i=0;i<numArt;i++){
					linea = in.readLine();
					String[] palabras = linea.split(" ");
					
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
				
					//agrego los nodos al grafo
					for(int k = 0;k<listaNodo.toArray().length;k++){
						Nodo dummy = new Nodo((String) listaNodo.toArray()[k]);
						grafo.add(dummy);
					}
					
					// agrego los arcos al grafo
					for(int k = 0;k<listaNodo.toArray().length;k++){
						for(int l = 0;l<listaNodo.toArray().length;l++){
							if(l != k){
								grafo.add(new Arco((String) listaNodo.
								 toArray()[k],(String) listaNodo.toArray()[l]));
							}
						}
					}
					
					listaNodo.clear();
				}
				
				//agrego los nombres de las personas a mi lista
				for(int i=0;i<numPer;i++){
					listaGente.add(linea = in.readLine());
				}
			
				//agrego a mis listas el grafo actual y la lista de gente actual
				lista.add(grafo);
				listaPer.add(listaGente);
				listaGente = new MiLista<String>();
				grafo = new DigraphLista();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	/**
	 * Busca el camino para llegar a Erdös de 
	 * cada nodo dentro del grafo y luego escribe
	 * sus resultados en el archivo nomArch
	 */
	@SuppressWarnings("unchecked")
	public static void buscarCamino(String nomArch) {
		int i=0;
		Lista<String> listaPerAct;
		Digraph digraphAct;
		Nodo perAct;
		
		FileWriter sali = null;
		
		try {
			sali = new FileWriter(new String(nomArch));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BufferedWriter salida = new BufferedWriter(sali);
		
		//vamos moviendonos por la lista de lista de personas y la lista
		//de grafos para recopilar la informacion que hay que imprimir
		while (i<lista.getSize()) {
			listaPerAct = ((Lista<String>) listaPer.toArray()[i]);
			digraphAct = ((Digraph) lista.toArray()[i]);
			
			//imprimo en el archivo
			try {
				salida.write("\nEscenario"+(i+1)+"\n\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			int j=0;
			//buscamos el camino hasta erdos de cada persona en la lista
			//de personas que revisamos actualmente
			while (j<listaPerAct.getSize()) {
				perAct = digraphAct.get((String) listaPerAct.toArray()[j]);
				
				try {
					salida.write(perAct.toString()+": <");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				//Si la persona tiene un número de Erdös menor a 2147483647
				//buscamos su camino hasta él
				if (perAct.getErdos() < 2147483647) {
					try {
						salida.write(perAct.toString());
						salida.write(", ");
					} catch (IOException e) {
						e.printStackTrace();
					}
					caminoErdos(perAct,digraphAct,salida);
				}
				//Si la persona tiene Erdos 2147483647 no hay camino hasta 
				//Erdös desde el asi que se imprimiría una secuencia vacia
				else {
					try {
						salida.write("> \n");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				j++;
			}
			i++;
		}
		try {
			salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void caminoErdos(Nodo perA, Digraph graf,BufferedWriter sal) {
		Lista<Nodo> listAdy = graf.getSucs(perA.toString());
		int i=0;
		int min = 0;
		
		//busco el nodo adyacente al actual con menor numero de Erdös
		while (i<listAdy.getSize()) {
			if (((Nodo) listAdy.toArray()[i]).getErdos() < 
					((Nodo) listAdy.toArray()[min]).getErdos()) {
				min = i;
			}
			i++;
		}
		
		//Si este nodo tiene erdos=0 es porque es el mismo Erdös y terminamos
		//de buscar el camino.
		if (((Nodo) listAdy.toArray()[min]).getErdos() == 0) {
			try {
				sal.write(listAdy.toArray()[min].toString()+"> \n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		//Si tiene un erdos>0 entonces cambiamos el nodo actual y llamamos
		//a la funcion recursivamente.
		else {
			try {
				sal.write(listAdy.toArray()[min].toString()+", ");
			} catch (IOException e) {
				e.printStackTrace();
			}
			Nodo nuevAct = graf.get(((Nodo) listAdy.toArray()[min]).toString());
			caminoErdos(nuevAct,graf,sal);
		}

	}
	
	public static void main(String[] args) {
		String in = "file.in";
		String out = "file.out";
		
		if(args.length == 2){
			in = args[0];
			out = args[1];
		}
		
		cargarDatos(in);
		
		for(int i=0;i<lista.getSize();i++){
			BFS((Digraph)lista.toArray()[i]);
		}
		
		buscarCamino(out);
				
	}
}
