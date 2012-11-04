import java.util.Random;
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
	
	public static void pruebaListaOrdenada() {
		MiLista<Nodo> lisNod = new MiLista<Nodo>();
		
		System.out.println("creando noditos");
		
		System.out.println(lisNod.addOrdenado(new Nodo("soy")));
		System.out.println(lisNod.addOrdenado(new Nodo("Hola")));
		System.out.println(lisNod.addOrdenado(new Nodo("un")));
		System.out.println(lisNod.addOrdenado(new Nodo("perol")));
		System.out.println(lisNod.addOrdenado(new Nodo("nodo")));
		
		lisNod.imprimirLista();
		
	}
	
	public static int Dijkstra(Digraph g, Nodo n, Nodo d) {
		BinaryHeap<Nodo> abiertos = new BinaryHeap<Nodo>();
		int i = 0;
		int letAcumul = 0;
		
		abiertos.agregar(n);
		
		while (!abiertos.esVacio()){
			Nodo actual = (Nodo) abiertos.getMin();
			abiertos.removeMin();
			actual.setVisitado(true);
			Lista<Arco> adyacentes = g.getOutArcos(actual.toString());
			while (i < adyacentes.toArray().length) {
				Nodo ady = g.get(((Arco) adyacentes.toArray()[i]).getDst());
				if (!ady.getVisitado()) {
					Palabrita nuevPal = (Palabrita) ((Arco) adyacentes.
							toArray()[i]).getPal().getMin();
					letAcumul += nuevPal.getNumLet();
					
					if (ady.equals(d)){
						return letAcumul;
					}
					
					abiertos.agregar(ady);
				}
				i++;
			}
		}
		
		
		return letAcumul;
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
		
		int numLinea;
		System.out.println( numLinea = Integer.parseInt(inFile.readLine()));
		
		if(numLinea == 0){
			System.exit(1);
		}
		
		linea = inFile.readLine();
		
		String[] iniFin = linea.split(" ");
		String partida = iniFin[0];
		String llegada = iniFin[1];
		
		System.out.println("partida: "+partida+"\n"+"llegada: "+llegada);
		
		inFile = obtenerGrafo(inFile, numLinea);
		
		Lista<Arco> prueba = grafo.getArcos();
		
		for(Object o : prueba.toArray()){
			System.out.println(o.toString());
		}
				
		grafo = new DigraphLista();
		linea = "";
		System.out.println();
		}		
	
	}catch(IOException e){
		e.printStackTrace();
	}
	

	}

}
