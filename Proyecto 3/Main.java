
import java.io.*;

public class Main {

	static Digraph grafo; 
	
	public static BufferedReader obtenerGrafo(BufferedReader inFile, 
											  int numLinea){
		try{
			for(int i = 0;i<numLinea;i++){
				String[] linea = inFile.readLine().split(" ");
//				if(grafo.contains(linea[0]) && grafo.contains(linea[1])){
				MiLista<Arco> lista = (MiLista<Arco>) grafo.getArcos();
				Arco arco = null;
				if(!lista.isEmpty())
					arco = (Arco) lista.binarySearch(new Arco(linea[0],linea[1]));
				if(arco != null){
						
						arco.getPal().agregar(new Palabrita(linea[2]));
						arco = (Arco) lista.binarySearch(new Arco(linea[1],linea[0]));
						arco.getPal().agregar(new Palabrita(linea[2]));
					
					
/*					Manera indigena, NO BORRAR POR QUE FUNCIONA
					for(Object o: grafo.getArcos().toArray()){
						if(((Arco)o).getSrc().equalsIgnoreCase(linea[0])&&((Arco)o).getDst().equalsIgnoreCase(linea[1])||
							(((Arco)o).getSrc().equalsIgnoreCase(linea[1])&&(((Arco)o).getDst().equalsIgnoreCase(linea[0])))){
							((Arco)o).getPal().agregar(new Palabrita(linea[2]));
							j++;
						}
						if(j==2)break;

					}
*/
				}else{
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
	
	@SuppressWarnings("unchecked")
	public static String Dijkstra(String n, Nodo d) {
		BinaryHeap<Nodo> abiertos = new BinaryHeap<Nodo>();
		
		Nodo nod = grafo.get(n);
		
		if(nod.equals(d))return "0";
		
		nod.setCosto(0);
		abiertos.agregar(nod);
				
		while (!abiertos.esVacio()){
//			System.out.println(abiertos.toString());
			
			Nodo actual = (Nodo) abiertos.getMin();
			abiertos.removeMin();
			
			if(actual.equals(d))
				return Integer.toString(actual.getCosto());
			
//			System.out.println("Actual: "+actual.toString()+"("+actual.getAnterior()+") "+actual.getCosto()+" : " + actual.getVisitado());
			
			actual.setVisitado(true);
			int costoActual = actual.getCosto();
			
			Lista<Arco> adyacentes = grafo.getOutArcos(actual.toString());
			
			for(Object o:adyacentes.toArray()){
				Nodo ady = grafo.get(((Arco)o).getDst());
				
				boolean visitado = false;
				Nodo dummy = actual.getAntNodo();
				
				while(!visitado&&dummy!=null){
					visitado = dummy.equals(ady);
					dummy = dummy.getAntNodo();
				}
				
//				System.out.println("\n"+ady.toString()+"("+ady.getAnterior()+")"+ ": " + visitado);
				
//				if(!ady.getVisitado()){
				if(!visitado){
					BinaryHeap<Palabrita> heapCosto = 
						(BinaryHeap<Palabrita>) ((Arco)o).getPal().clone();
					int i=0;
					
					while(!heapCosto.esVacio()){
						Palabrita minPal = (Palabrita) heapCosto.getMin();

//						System.out.println("\t"+minPal.toString());
						
						i++;
						
						if(minPal.getPalab().charAt(0)!=actual.getAnterior()){

							int costoMin;	
//							if(ady.getCosto()==Integer.MIN_VALUE){
//								costoMin = actual.getCosto()+minPal.getNumLet();
//							}else{
							Nodo clone = (Nodo) ady.clone();
							costoMin = Math.min(clone.getCosto(), 
								costoActual+minPal.getNumLet());
//							}

							clone.setAnterior(minPal.getPalab().charAt(0));
							clone.setCosto(costoMin);
						
							clone.setAntNodo(actual);
							clone.setArco((Arco)o);
							clone.setVisitado(true);
							
//							System.out.println("\n"+clone.toString()+"("+clone.getAnterior()+")"+ ": " + clone.getCosto());
							
//							if(i==1&&clone.equals(d))
//								return Integer.toString(clone.getCosto());
						
							abiertos.agregar(clone);
							
						}

						heapCosto.removeMin();
					}

				}
				
			}
		}

		return "impossivel";
	}
	public static Arco arcoSearch(MiLista<Arco> lista, String arco){
		Object[] array = lista.toArray();
		int min = 0, max = array.length, mid = 0;
    	boolean encontre = false;
    	
    	while(!encontre && min <= max){
    		mid = (min + max)/2;
    		if(mid >= array.length)break;
    		
    		if(((Arco)array[mid]).getSrc().compareTo(arco) == 0){
    			encontre = true;
    		}else if(((Arco)array[mid]).getSrc().compareTo(arco) < 0){
    			max = mid -1;
    		}else{
    			min = mid + 1;
    		}
    		
    	}
    	
    	if(encontre){
    		return (Arco)array[mid];
    	}
    	return null;
	}

	@SuppressWarnings("unchecked")
	public static void Bijkstra(String src, String dst){
		MiLista<Arco> lista =  (MiLista<Arco>) grafo.getOutArcos(src);
		System.out.println("\nBijkstra");
		lista.imprimirLista();
		
		for(Object o:lista.toArray()){
			Arco arco = (Arco) o;
			BinaryHeap<Palabrita> heap = (BinaryHeap<Palabrita>) arco.getPal().clone();
			heap.getMin();
			heap.removeMin();
			System.out.println("\n"+arco.toString()+"\n"+ heap.toString());
		}
	}
	
	public static void main(String[] args) {

		
	String in = "file.in";
	String out = "file.out";
	
	if(args.length == 2){
		in = args[0];
		out = args[0];
	}
	
	String linea = "";
	BufferedReader inFile = null;

	try{
		inFile = new BufferedReader(new FileReader(in));
		
		grafo = new DigraphLista();
		
		while(true){
		
		int numLinea = Integer.parseInt(inFile.readLine());
//		System.out.println( numLinea );
		
		if(numLinea == 0){
			System.exit(1);
		}
		
		linea = inFile.readLine();
		
		String[] iniFin = linea.split(" ");
		String partida = iniFin[0];
		String llegada = iniFin[1];
		
//		System.out.println("partida: "+partida+"\n"+"llegada: "+llegada);
		
		inFile = obtenerGrafo(inFile, numLinea);		
		
		
		Lista<Arco> prueba = grafo.getArcos();
		
/*		for(Object o : prueba.toArray()){
			System.out.println(o.toString());
		}
*/		
		System.out.println(Dijkstra(partida, new Nodo(llegada)));
				
		grafo = new DigraphLista();
		linea = "";
//		System.out.println();
		}		
	
	}catch(IOException e){
		e.printStackTrace();
	}
	

	}

}
