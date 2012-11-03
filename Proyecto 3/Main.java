import java.util.Random;
import java.io.*;

public class Main {

	static Digraph grafo; 
	
	public static BufferedReader obtenerGrafo(BufferedReader inFile, 
											  int numLinea){
		try{
			for(int i = 0;i<numLinea;i++){
				String[] linea = inFile.readLine().split(" ");
				if(!grafo.contains(linea[0])){
					grafo.add(new Nodo(linea[0]));
				}else if(!grafo.contains(linea[1])){
					grafo.add(new Nodo(linea[1]));
				}
				grafo.add(new Arco(linea[0],linea[1],new Palabrita(linea[2])));
			}
			return inFile;
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
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
		
		int numLinea = Integer.parseInt(inFile.readLine());
		
		if(numLinea == 0){
			System.exit(1);
		}
		
		linea = inFile.readLine();
		
		String[] iniFin = linea.split(" ");
		String partida = iniFin[0];
		String llegada = iniFin[1];
		
		System.out.println("partida: "+partida+"\n"+"llegada: "+llegada);
		
		inFile = obtenerGrafo(inFile, numLinea);
		
	
	}catch(IOException e){
		e.printStackTrace();
	}
	

	}

}
