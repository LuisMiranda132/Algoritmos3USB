/**
 * 
 * @author Gabriela Limonta, Luis Miranda
 * 
 * Clase que contiene el main del programa.
 */

import java.io.*;
import java.util.Scanner;

public class Main {
	
	static Scanner scan = null;
	
	/*
	 * leerArchivo: dado el numero de escenarios se encarga de leer el archivo
	 * para buscar las ciudades y las conexiones entre ellas que deben 
	 * establecerse para formar nuestro grafo.
	 */
	public static int leerArchivo(int numEscenario) {
        
		DynamicArray lados = new DynamicArray(2);
		//leemos el numero de ciudades del escenario
		int numCiudad = scan.nextInt();
		//en este ciclo agregaremos los lados correspondientes a cada ciudad
		for(int i=0;i<numCiudad;i++) {
			// este trozo de codigo es para ignorar el string que leemos ya que
			// no nos importa
			String bleh = scan.nextLine();
			char[] arrChar = bleh.toCharArray();

	        while (arrChar.length == 0) {
	            bleh = scan.nextLine();
	            arrChar = bleh.toCharArray();
	        }
			int numVecinas = scan.nextInt();
			/* numVecinas es el numero de ciudades con las que se conecta y
			 * para todas las vecinas leemos quien es y el costo y creamos 
			 * un nuevo arco que agregamos a la lista de arcos.
			 */
			for(int k=0;k<numVecinas;k++) {
				int vec = scan.nextInt();
				int costo = scan.nextInt();
				Arco aux = new Arco(i+1,vec,costo);
				lados.addFinal(aux);
			}
		}
		/* luego vienen las ciudades que ya estan conectadas,
		 * aca leemos que ciudad se conecta con quien (c1 y c2) y las
		 * agregamos a la lista de lados con costo cero ya que al estar
		 * conectadas ya el problema de su costo fue resuelto.
		 */
		int yaConect = scan.nextInt();
		for(int j=0;j<yaConect;j++) {
			int c1 = scan.nextInt();
			int c2 = scan.nextInt();
			Arco aux2 = new Arco(c1,c2,0);
			lados.addFinal(aux2);
		}
		/* ahora aplicamos kruskal a los lados que leimos del archivo y
		 * kruskal nos devuelve el costo del minimo arbol cobertor.  
		 */
		int cost = Kruskal(lados,numCiudad);
		//devolvemos eso
		return cost;
	}
	
	/*
	 * Kruskal: es la implementacion del algoritmo de Kruskal con la unica 
	 * diferencia de que devuelve el costo del arbol cobertor de costo minimo.
	 * toma una lista de lados y un numero de ciudades.
	 */
	public static int Kruskal(DynamicArray lad, int nC) {
		DisjointSet E = new DisjointSet(nC);
		DynamicArray ladK = new DynamicArray(2);

		//ordenamos los lados de menor a mayor
		lad.heapSort();
		
		int i=0;
		int cost=0;
		/* mientras aun haya mas de 1 componente conexa es porque no 
		 * hemos logrado conectar todos los nodos mediante arcos 
		 */
		while (E.getConexas() > 1) {
			Arco e = (Arco) lad.get(i);
			//si pertenecen a componentes conexas dif, las uno y sumo el costo
			//de pasar por este lado
			if (E.find(e.getSrc()) != E.find(e.getDst())) {
				E.union(e.getSrc(), e.getDst());
				cost += e.getCost();
			}
			i++;
		}
		//devuelvo el costo
		return cost;
	}
	
	/*
	 * imprimirArch: a esta funcion se le pasa un entero (lo que vamos a
	 * imprimir en el archivo) y un BufferedWriter y lo que hace es imprimir
	 * el entero en el archivo de salida.
	 */
	public static void imprimirArch(int lK, BufferedWriter out) {
		try {
			out.write(lK+"\n");
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
	
	File arch = new File(in);
	FileWriter sali = null;
	
	//creo lo necesario para escribir en el archivo
	try {
		sali = new FileWriter(new File(out));
	}
	catch (IOException e1){
		e1.printStackTrace();
	}
	
	BufferedWriter outFile = new BufferedWriter(sali);

	try{
		scan = new Scanner(arch);
		int numEsc = scan.nextInt();
		//para cada escenario se hace leerArchivo e imprimirArchivo
		for(int i=0;i<numEsc;i++) {
			int ladKrus = leerArchivo(i);
			imprimirArch(ladKrus,outFile);
		}
				
	}catch(FileNotFoundException e){
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
