import java.util.Random;
import java.io.*;

public class Main {

	static Digraph grafo; 
	
	public static BufferedReader obtenerGrafo(BufferedReader inFile, 
											  int numLinea){
		try{
			for(int i = 0;i<numLinea;i++){
				String[] linea = inFile.readLine().split(":");
				String[] info = linea[1].split(",");
				if (!grafo.contains(info[0]))
					grafo.add(new Nodo(info[0]));
				if (!grafo.contains(info[1]))
					grafo.add(new Nodo(info[1]));
				Arco nuevArc = new Arco(info[0],info[1],linea[0],Integer.parseInt(info[2]));
				grafo.add(nuevArc);
			}
			return inFile;
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static void pruebaListaOrdenada() {
		Lista<Nodo> lisNod = new MiLista<Nodo>();
		
		System.out.println("creando noditos");
		/*
		System.out.println(lisNod.addOrdenado(new Nodo("Hola")));
		System.out.println(lisNod.addOrdenado(new Nodo("soy")));
		System.out.println(lisNod.addOrdenado(new Nodo("un")));
		System.out.println(lisNod.addOrdenado(new Nodo("nuevo")));
		System.out.println(lisNod.addOrdenado(new Nodo("nodo")));
		*/
	}
	
	/*
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
	*/
	
	
	public static void main(String[] args) {

		Arco prueba1 = new Arco("Arke2","B");
		Arco prueba2 = new Arco("Arke1","B");
		System.out.println(prueba1.compareTo(prueba2));
		
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
		
		int numLinea = Integer.parseInt(inFile.readLine());
		
		if(numLinea == 0){
			System.exit(1);
		}
		
		linea = inFile.readLine();
		
		//String[] iniFin = linea.split(" ");
		//String partida = iniFin[0];
		//String llegada = iniFin[1];
		
		//System.out.println("partida: "+partida+"\n"+"llegada: "+llegada);
		
		inFile = obtenerGrafo(inFile, numLinea);
		
		System.out.println(grafo.toString());
	
	}catch(IOException e){
		e.printStackTrace();
	}
	

	}

}
