import java.io.*;
import java.util.Random;

public class Main {

	static Digraph grafo = new DigraphLista(); 
	
	public static BufferedReader obtenerGrafo(BufferedReader inFile,
												int numLinea) {
		try {
			for(int i=0;i<numLinea;i++) {

				String[] primer = inFile.readLine().split(": \\(");
				Nodo nuevFunc = new Nodo(primer[0]);
				nuevFunc.setFuncion(true);
				grafo.add(nuevFunc);
				

				String[] segundo = primer[1].split("\\), \\(");
				String[] dominio = segundo[0].split(", ");
				for(int j=0;j<dominio.length;j++) {
					grafo.add(new Nodo(dominio[j]));
					grafo.add(new Arco(dominio[j],primer[0]));
				}
				String[] tercero = segundo[1].split("\\) ");
				String[] rango = tercero[0].split(", ");
				for(int k=0;k<rango.length;k++) {
					grafo.add(new Nodo(rango[k]));
					grafo.add(new Arco(primer[0],rango[k]));
				}
				grafo.get(primer[0]).setCosto(Integer.parseInt(tercero[1]));
				
			}
			return inFile;
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static class RandomStringGenerator {
		public final static short TYPE_MIXED_CASE = 0;
		public final static short TYPE_UPPER_ONLY = 1;
		public final static short TYPE_LOWER_ONLY = 2;
		public final static Random rnd = new Random();
		
		static final char[] alphas = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
				         			  'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		
		/**
		 * Generate a random string of characters at the specified length.  The first argument (type) is a constant 
		 * TYPE_MIXED_CASE, TYPE_UPPER_ONLY, TYPE_LOWER_ONLY.  This is followed by length.  That is followed by whether or not
		 * the first character should be capitalized (regardless of lower only).  It is of course silly to use initial caps
		 * with TYPE_UPPER_ONLY.  
		 * 
		 * @param type
		 * @param length
		 * @param initialCaps
		 * @return
		 */
		public static String generateRandomString(short type,int length, boolean initialCaps) {
			int min = type == TYPE_LOWER_ONLY ? 26 : 0;
			int max = type == TYPE_UPPER_ONLY ? 26 : alphas.length;
			String generated = "";
			for (int i = 0; i < length; i++) {			
				int random = rnd.nextInt(max - min) + min;
				generated += alphas[random];
			}
			generated = initialCaps ? (""+generated.charAt(0)).toUpperCase() + generated.substring(1) : generated;
			return generated;
			
			
		}
		/**
		 * Generate a random string of characters at the specified length without the option of initial caps.  See the other method in this class.
		 * @param type
		 * @param length
		 * @return
		 */
		public static String generateRandomString(short type,int length) {
			return generateRandomString(type, length, false);
		}
	}
		
	public static void prueba(){
		Random generador = new Random();
		
		String ant = RandomStringGenerator.generateRandomString(RandomStringGenerator.TYPE_MIXED_CASE, 5);
		
		Nodo prueba = new Nodo(ant); 
		grafo.add(prueba);
		String string = RandomStringGenerator.generateRandomString(RandomStringGenerator.TYPE_MIXED_CASE, 5);
		grafo.add(new Nodo(string));
		for(int i = 0; i<200;i++){
			String string1 = RandomStringGenerator.generateRandomString(RandomStringGenerator.TYPE_MIXED_CASE, 5);
			grafo.add(new Nodo(string1));
			ant = string1;
			
			
		}
		
		grafo.add(new Arco(string, ant));
		grafo.add(new Arco(ant, string));
		
		grafo.getNodos().imprimirLista();
		System.out.println("\n"+"\n");
		grafo.getArcos().imprimirLista();
		
		System.out.println("\n"+ant+"\n");
		
		grafo.getOutArcos(string).imprimirLista();
		grafo.getInArcos(ant).imprimirLista();

		System.out.println("\n"+"\n");
		grafo.remove(ant, string);
		grafo.getOutArcos(string).imprimirLista();
		grafo.getInArcos(ant).imprimirLista();
		
		System.out.println("\n"+"\n");
		grafo.getArcos().imprimirLista();
		System.out.println("\n"+"\n");
		
		grafo.remove(prueba.toString());
		
		grafo.getNodos().imprimirLista();
		
		System.out.println(grafo.contains(prueba.toString()));
	}
	
	@SuppressWarnings("unchecked")
	public static Dominio obtenerComposicion(String[] Entrada, String[] Salida) {
		
		int costoMio = 0;
		
		for(int g=0;g<grafo.getNodos().toArray().length;g++) {
			((Nodo) grafo.getNodos().toArray()[g]).setVisitado(false);
		}
		
		Lista<String> inic = new MiLista<String>();
		
		for(int i=0;i<Entrada.length;i++) {
			inic.add(Entrada[i]);
		}
		
		Lista<String> func = new MiLista<String>();
		System.out.println("\n------");
		inic.imprimirLista();
		System.out.println("\n------");
		func.imprimirLista();
		System.out.println("\n------");
		Dominio inicial = new Dominio(inic,func,0);
		BinaryHeap<Dominio> abiertos = new BinaryHeap<Dominio>();
		abiertos.agregar(inicial);
		
		while (!abiertos.esVacio()) {
			System.out.println(abiertos.toString());
			System.out.println("\n------");
			System.out.println("abiertos no es vaciooo");
			System.out.println("\n------");
			Dominio actual = (Dominio) abiertos.getMin();
			abiertos.removeMin();
			
			MiLista<String> listaDom = (MiLista<String>) actual.getCont();
			listaDom.imprimirLista();
			System.out.println("\n------");
			
			int k=0;
			boolean sali = false;
			
			while(k<Salida.length && !sali) {
				System.out.println(Salida[k]);
				System.out.println("\n------");
				if (!listaDom.contains(Salida[k])) {
					sali = true;
					System.out.println("No tengo a salida["+k+"]");
					System.out.println("\n------");
				}
				k++;
			}
			
			if (!sali) {
				System.out.println("no saliii");
				System.out.println("\n------");
				return actual;
			}
			
			for(k=0;k<listaDom.getSize();k++) {
				System.out.println("entre a un for lindo del dijkstra: " + k);
				System.out.println("\n---listaDom---");
				listaDom.imprimirLista();
				System.out.println("\n---listaDom[k]---");
				System.out.println(((String)listaDom.toArray()[k]));
				System.out.println("\n---Sucesores---");
				Lista<Nodo> sucesores = grafo.getSucs(((String)listaDom.toArray()[k]));
				sucesores.imprimirLista();
				System.out.println("\n------");
				for (int j=0;j<sucesores.getSize();j++) {
					System.out.println("y a otro fooor :D");
					System.out.println("\n---Sucesores["+j+"]---");
					System.out.println(((Nodo) sucesores.toArray()[j]).toString());
					System.out.println("\n------");
					if (!((Nodo) sucesores.toArray()[j]).getVisitado()) {
						System.out.println("entre al ifff de que no este visitado");
						System.out.println("\n------");
						Lista<Nodo> predecesores = 
							grafo.getPreds(sucesores.toArray()[j].toString());
						System.out.println("\n---Predecesores---");
						predecesores.imprimirLista();
						System.out.println("\n------");
						int i=0;
						boolean noIgual = false;
						while (i<predecesores.getSize() && !noIgual) {
							System.out.println("Entre al otro whileee");
							System.out.println("\n---predecesores["+i+"]---");
							System.out.println(predecesores.toArray()[i].toString());
							System.out.println("\n------");
							if (!listaDom.contains(predecesores.toArray()[i].toString())) {
								System.out.println("entre a ese if :D");
								System.out.println("no tengo a lo que sea");
								System.out.println("\n------");
								noIgual = true;
							}
							i++;
						}
						
						if (!noIgual) {
							System.out.println("ENtre a iguaaaal");
							/*
							 * Creo que aqui hay un problema con marcarlos 
							 * visitado antes...
							 */
							System.out.println("\n---Sucesores["+j+"]---");
							System.out.println(((Nodo)sucesores.toArray()[j]).toString());
							System.out.println("\n------");
							((Nodo)sucesores.toArray()[j]).setVisitado(true);

							Lista<Nodo> sucs2 = grafo.getSucs((
									(Nodo)sucesores.toArray()[j]).toString());
							System.out.println("Costo actual: "+actual.getCosto());
							System.out.println("nuevo costo func: "+
									((Nodo) sucesores.toArray()[j]).getCosto());

							int nuevCost = actual.getCosto() + 
									((Nodo) sucesores.toArray()[j]).getCosto();

							System.out.println("El nuevo costo es: "+nuevCost);
							System.out.println("\n------");
							
/*							System.out.println("\n---Original---");
							listaDom.imprimirLista();
							System.out.println("\n---Clone---");
							((MiLista<String>)listaDom.clone()).imprimirLista();
							System.out.println("\n------");
*/							
							
							/*
							 * Cuando aqui le paso las listas casteadas como 
							 * MiLista para que las clone
							 */
							
							Dominio nuevo = new Dominio((MiLista)listaDom,
									(MiLista)actual.getFunciones(),nuevCost);
							nuevo.agregarFuncion(((Nodo) 
									sucesores.toArray()[j]).toString());

							/*
							 * Cambie "k" por "l"
							 */
							for(int l=0;l<sucs2.getSize();l++) {
								nuevo.agregarCont(((Nodo) 
										sucs2.toArray()[l]).toString());
							}
							abiertos.agregar(nuevo);
							
						}
					}
				}
			}
		}
		return new Dominio(new MiLista<String>(),new MiLista<String>(),0);
		
	}
	
	public static void buscarComposiciones(int numComp, BufferedReader inFile) {
		for(int i=0;i<numComp;i++) {
			try {
				String[] primer = inFile.readLine().split("\\), \\(");
				String[] segundo = primer[0].split("\\(");
				String[] tercer = primer[1].split("\\)");
				String[] entrada = segundo[1].split(", ");
				String[] salida = tercer[0].split(", ");
				
				Dominio dom = obtenerComposicion(entrada,salida);
				dom.getFunciones().imprimirLista();
				System.out.println("\n\nEl costo de la vaina es: ");
				System.out.println(dom.toString()+"\n");
				
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {

//		prueba();
		
	String in = "file.in";
	String out = "file.out";
	
	if(args.length == 2){
		in = args[0];
		out = args[1];
	}
	
	String linea = "";
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
//		System.out.println(numLinea);
		
		if(numLinea == 0){
			System.exit(1);
		}
		
		inFile = obtenerGrafo(inFile, numLinea);
//		System.out.println(grafo.toString());
		
		int numComp = Integer.parseInt(inFile.readLine());
//		System.out.println(numComp);
		
		if(numComp == 0){
			System.exit(1);
		}
		
		buscarComposiciones(numComp,inFile);
		
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
