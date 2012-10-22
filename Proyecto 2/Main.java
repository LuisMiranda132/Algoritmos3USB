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
/*			Lista<Nodo> dummy1 = grafo.getNodos();
			while(!"ErdosP".equalsIgnoreCase((dummy1.toArray()[i]).
					toString())){
				i++;
			}
			visitado[i] = true;
*/
			grafo.get("ErdosP").setVisitado(true);
			
			while(!cola.esVacia()){
				
				Nodo dummy = (Nodo) cola.primero();
				cola.desencolar();
				
				int degree = grafo.getOutDegree(dummy.toString());
				for(i=0;i<degree;i++){
					
					Lista<Arco> arcos = grafo.getInArcos(dummy.toString());
					String src = ((Arco)arcos.toArray()[i]).getSrc();
					
/*					int j = 0;
					//buscamos el nodo el el grafo
					Lista<Nodo> dummy2 = grafo.getNodos();
					while(!src.equalsIgnoreCase(dummy2.toArray()[j].
							toString())){
						j++;
					}
					
					//si no esta visitado lo marcamos como tal y le asignamos 
					//su numero de erdos.
*/
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
	 * Carga los datos del archivo fileIn a los grafos.
	 */
	public static void cargarDatos(String fileIn, String fileOut){
		String linea = "";
		BufferedReader in = null;
		Digraph grafo = new DigraphLista();
		Lista<String> listaNodo = new MiLista<String>();
		Lista<String> listaGente = new MiLista<String>();
		
		FileWriter sali = null;
		
		try {
			sali = new FileWriter(new String(fileOut));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BufferedWriter salida = new BufferedWriter(sali);
		
		try {
			in = new BufferedReader(new FileReader(fileIn));
			
			int numCasos = Integer.parseInt(in.readLine());
		
			for(int num = numCasos;num>0;num--){
				linea = in.readLine();
				
				String[] numeros = linea.split(" ");
				
				int numArt = Integer.parseInt(numeros[0]);
				int numPer = Integer.parseInt(numeros[1]);
				
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
//							if(l != k){
								grafo.add(new Arco((String) listaNodo.
								 toArray()[k],(String) listaNodo.toArray()[l]));
//							}
						}
					}
					
					listaNodo.clear();
				}			

				BFS(grafo);

				
				
				
				try {
					salida.write
					("Escenario "+(numCasos - num +1)+"\n");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
								
				//agrego los nombres de las personas a mi lista
				for(int i=0;i<numPer;i++){
					linea= in.readLine();
										
					Nodo perAct = grafo.get(linea);
					
					//imprimo en el archivo

						try {
							salida.write
//							System.out.print
							(perAct.toString()+": <");
						} catch (IOException e) {
							e.printStackTrace();
						}
						if(perAct.getErdos()< 2147483647){
							while (perAct.getErdos() != 0){
						
								try {
									salida.write
//									System.out.print
									(perAct.toString());
									salida.write
//									System.out.print
									(", ");
								} catch (IOException e) {
									e.printStackTrace();
								}

								
								Lista<Nodo> dummyLista = grafo.getSucs(perAct.toString());
													
								int k =0;
								while(perAct.getErdos() <=
								((Nodo)dummyLista.toArray()[k]).getErdos()){
									k++;
								}
							
								perAct = (Nodo) dummyLista.toArray()[k];

							}
							
							try {
							salida.write
//							System.out.print
							(perAct.toString()+"> \n");
						} catch (IOException e) {
							e.printStackTrace();
						}
							
						}else{
							
							try {
								salida.write
//								System.out.print
								("> \n");
							} catch (IOException e) {
								e.printStackTrace();
							}
							
						}
//						j++;
//					}
//					i++;
//				}
				
					
					
				}
				
			
				//agrego a mis listas el grafo actual y la lista de gente actual
/*				lista.add(grafo);
				listaPer.add(listaGente);
				listaGente = new MiLista<String>();
*/				grafo = new DigraphLista();
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

	/**
	 * Busca el camino para llegar a Erdös de 
	 * cada nodo dentro del grafo y luego escribe
	 * sus resultados en el archivo nomArch
	 */
	@SuppressWarnings("unchecked")
	public static void buscarCamino(String nomArch, String nomNodo, 
						Digraph digraphAct, int i) {
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
//		while (i<lista.getSize()) {
//			listaPerAct = ((Lista<String>) listaPer.toArray()[i]);
//			digraphAct = ((Digraph) lista.toArray()[i]);
		
		try {
			salida.write
			("Escenario "+i+"\n");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		perAct = digraphAct.get(nomNodo);
		
			//imprimo en el archivo

				try {
					salida.write
//					System.out.print
					(perAct.toString()+": <");
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(perAct.getErdos()< 2147483647){
					while (perAct.getErdos() != 0){
				
						try {
							salida.write
//							System.out.print
							(perAct.toString());
							salida.write
//							System.out.print
							(", ");
						} catch (IOException e) {
							e.printStackTrace();
						}

						
						Lista<Nodo> dummyLista = digraphAct.getSucs(perAct.toString());
											
						int k =0;
						while(perAct.getErdos() <=
						((Nodo)dummyLista.toArray()[k]).getErdos()){
							k++;
						}
					
						perAct = (Nodo) dummyLista.toArray()[k];

					}
					
					try {
					salida.write
//					System.out.print
					(perAct.toString()+"> \n");
				} catch (IOException e) {
					e.printStackTrace();
				}
					
				}else{
					
					try {
						salida.write
//						System.out.print
						("> \n");
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
//				j++;
//			}
//			i++;
//		}
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
		
//		for(int i=0;i<lista.getSize();i++){
//			BFS((Digraph)lista.toArray()[i]);
//		}
		
				
	}
}
