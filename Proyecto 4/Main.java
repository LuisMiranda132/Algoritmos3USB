import java.io.*;
import java.util.Random;

public class Main {

	static Digraph grafo = new DigraphLista(); 
	
	public static BufferedReader obtenerGrafo(BufferedReader inFile,
												int numLinea) {
		try {
			for(int i=0;i<numLinea;i++) {

				String[] primer = inFile.readLine().split(": \\(");
				System.out.println(primer[0]);
				Nodo nuevFunc = new Nodo(primer[0]);
				System.out.println("\n"+nuevFunc+"\n");
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
	
	public static Dominio obtenerComposicion(String[] Entrada, String[] Salida) {
		
		Lista<String> inic = new MiLista<String>();
		
		for(int i=0;i<Entrada.length;i++) {
			inic.add(Entrada[i]);
		}
		
		Lista<String> func = new MiLista<String>();
		Dominio inicial = new Dominio(inic,func,Integer.MAX_VALUE);
		BinaryHeap<Dominio> abiertos = new BinaryHeap<Dominio>();
		abiertos.agregar(inicial);
		
		while (!abiertos.esVacio()) {
			Dominio actual = (Dominio) abiertos.getMin();
			
			Lista<String> listaDom = actual.getCont();
			
			/* aqui falta un condicional que revise si el rango está
			 * conenido en el dominio que no se hacerlo. 
			 */			
			
			for(int k=0;k<listaDom.getSize();k++) {
				Lista<Nodo> sucesores = grafo.getSucs(((String)listaDom.toArray()[k]));
				for (int j=0;j<sucesores.getSize();j++) {
					if (!((Nodo) sucesores.toArray()[j]).getVisitado()) {
						Lista<Nodo> predecesores = 
							grafo.getPreds(sucesores.toArray()[j].toString());
						int i=0;
						boolean noIgual = false;
						while (i<predecesores.getSize() && !noIgual) {
							if (!listaDom.contains(predecesores.toArray()[i].toString())) {
								noIgual = true;
							}
						}
						
						if (!noIgual) {
							((Nodo)sucesores.toArray()[j]).setVisitado(true);
							Lista<Nodo> sucs2 = grafo.getSucs((
									(Nodo)sucesores.toArray()[j]).toString());
							int nuevCost = actual.getCosto() + 
									((Nodo) sucesores.toArray()[j]).getCosto();
							Dominio nuevo = new Dominio(listaDom,
									actual.getFunciones(),nuevCost);
							nuevo.agregarFuncion(((Nodo) 
									sucesores.toArray()[j]).toString());
							for(k=0;k<sucs2.getSize();k++) {
								nuevo.agregarCont(((Nodo) 
										sucs2.toArray()[k]).toString());
							}
							abiertos.agregar(nuevo);
							return (Dominio) abiertos.getMin();
						}
						return (Dominio) abiertos.getMin();
					}
					return (Dominio) abiertos.getMin();
				}
			}
			return (Dominio) abiertos.getMin();
		}
		return (Dominio) abiertos.getMin();
		
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
		System.out.println(numLinea);
		
		if(numLinea == 0){
			System.exit(1);
		}
		
		inFile = obtenerGrafo(inFile, numLinea);
		System.out.println(grafo.toString());
	
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
