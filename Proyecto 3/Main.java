/**
 * 
 * @author Gabriela Limonta, Luis Miranda
 * 
 */

import java.io.*;

public class Main {

	static Digraph grafo; 
	
	/*
	 * obtenerGrafo se encarga de cargar el archivo en el grafo
	 */
	public static BufferedReader obtenerGrafo(BufferedReader inFile,
											  int numLinea){
		try{
			for(int i = 0;i<numLinea;i++){

				String dummy = inFile.readLine();
				
				String[] linea = dummy.split(" ");

				MiLista<Arco> lista = (MiLista<Arco>) grafo.getArcos();
				Arco arco = null;
				
				/* 
				 * Se busca a ver si el arco ya esta en el grafo, si ya esta
				 * se procede a agregar el el valor de la palabra como en 
				 * formato "Palabrita" dentro de un heap que esta en el arco
				 * 	
				 */
			
				if(!lista.isEmpty())
					arco = (Arco) lista.binarySearch(
												new Arco(linea[0],linea[1]));
				
				if(arco != null){
						
						arco.getPal().agregar(new Palabrita(linea[2]));
						arco = (Arco) lista.binarySearch(
												new Arco(linea[1],linea[0]));
						arco.getPal().agregar(new Palabrita(linea[2]));
					
				}else{
					/*
					 * Si no se consigue se procede a revisars si ya estan 
					 * creados los nodos, si no estan se crean, y se agrega un 
					 * arco desde 0
					 */
					if(!grafo.contains(linea[0])){
						grafo.add(new Nodo(linea[0]));
					}
					if(!grafo.contains(linea[1])){
						grafo.add(new Nodo(linea[1]));
					}
					grafo.add(new Arco(linea[0],linea[1],new Palabrita(linea[2])));
					grafo.add(new Arco(linea[1],linea[0],new Palabrita(linea[2])));
				}
			}
			return inFile;
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Dijkstra es una modificacion del algoritmo de Dijkstra que se adapta a 
	 * solucionar el problema
	 */
	@SuppressWarnings("unchecked")
	public static String Dijkstra(String n, Nodo d) {
		
		BinaryHeap<Nodo> abiertos = new BinaryHeap<Nodo>();
		
		Nodo nod = grafo.get(n);
		
		// Si el idioma de partida es igual al de llegada, imprimo 0
		if(nod.equals(d))return "0\n";
		
		nod.setCosto(0);
		abiertos.agregar(nod);
				
		while (!abiertos.esVacio()){
//			System.out.println(abiertos.toString());
			
			Nodo actual = (Nodo) abiertos.getMin();
			abiertos.removeMin();

			// Se revisa si ya se llego al idioma que estas buscando
			if(actual.equals(d))
				return Integer.toString(actual.getCosto())+"\n";
			
//			System.out.println("Actual: "+actual.toString()+"("+actual.getAnterior()+") "+actual.getCosto()+" : " + actual.getVisitado());
			
			actual.setVisitado(true);
			int costoActual = actual.getCosto();
			
			Lista<Arco> adyacentes = grafo.getOutArcos(actual.toString());
			
			for(Object o:adyacentes.toArray()){
				Nodo ady = grafo.get(((Arco)o).getDst());
				
				boolean visitado = false;
				Nodo dummy = actual.getAntNodo();
				
				/*
				 * Se busca entre los nodos que ya recorri si se encuentra el
				 * nodo el ady, para no empilar de mas
				 */
				while(!visitado&&dummy!=null){
					visitado = dummy.equals(ady);
					dummy = dummy.getAntNodo();
				}
				
//				System.out.println("\n"+ady.toString()+"("+ady.getAnterior()+")"+ ": " + visitado);
				
				if(!visitado){
					/*
					 * Se usa un clon del heap que esta en el arco para no da~ar
					 * el grafo
					 */
					BinaryHeap<Palabrita> heapCosto = 
						(BinaryHeap<Palabrita>) ((Arco)o).getPal().clone();
					int i=0;
					
					/*
					 * Aqui se itera entre todas las posibilidades de caminos 
					 * se pueden tomar
					 */
					while(!heapCosto.esVacio()){
						Palabrita minPal = (Palabrita) heapCosto.getMin();

//						System.out.println("\t"+minPal.toString());
						
						i++;
						
						/*
						 * Solo se empilan las que cumplen con la condicion de 
						 * la primera letra
						 */
						
						if(minPal.getPalab().charAt(0)!=actual.getAnterior()){

							int costoMin;	

							/*
							 * Se usa un clon para no da~ar el grafo
							 */
							Nodo clone = (Nodo) ady.clone();
							costoMin = Math.min(clone.getCosto(), 
								costoActual+minPal.getNumLet());

							clone.setAnterior(minPal.getPalab().charAt(0));
							clone.setCosto(costoMin);
						
							clone.setAntNodo(actual);
							clone.setArco((Arco)o);
							clone.setVisitado(true);
							
//							System.out.println("\n"+clone.toString()+"("+clone.getAnterior()+")"+ ": " + clone.getCosto());
													
							abiertos.agregar(clone);
							
						}

						heapCosto.removeMin();
					}

				}
				
			}
		}
		/*
		 * Si llega a este punto significa que no se puede llegar al idioma
		 * deseado
		 */
		return "impossivel\n";
	}
	
	public static void main(String[] args) {

		
	String in = "file.in";
	String out = "file.out";
	
	if(args.length == 2){
		in = args[0];
		out = args[1];
	}
	
	String linea = "";
	BufferedReader inFile = null;
	
	try{
		inFile = new BufferedReader(new FileReader(in));
		
		FileWriter outFi = new FileWriter(out);
		BufferedWriter outFile = new BufferedWriter(outFi);
		
		grafo = new DigraphLista();
		
		/*
		 * Se tiene un ciclo que termina una vez que consigue un 0 en el archivo
		 * de entrada
		 */
		while(true){
		
		int numLinea = Integer.parseInt(inFile.readLine());
		
		if(numLinea == 0){
			outFile.close();
			System.exit(1);
		}
		
		linea = inFile.readLine();
		
		String[] iniFin = linea.split(" ");
		String partida = iniFin[0];
		String llegada = iniFin[1];
		
		inFile = obtenerGrafo(inFile, numLinea);		
		
		outFile.write(Dijkstra(partida, new Nodo(llegada)));
				
		grafo = new DigraphLista();
		linea = "";
		}		
		
	}catch(IOException e){
		e.printStackTrace();
	}
	

	}

}
