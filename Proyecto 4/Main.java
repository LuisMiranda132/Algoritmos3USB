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
		//Random generador = new Random();
		
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
		
//		grafo.getNodos().imprimirLista();
//		System.out.println("\n"+"\n");
//		grafo.getArcos().imprimirLista();
		
//		System.out.println("\n"+ant+"\n");
		
		grafo.getOutArcos(string).imprimirLista();
		grafo.getInArcos(ant).imprimirLista();

//		System.out.println("\n"+"\n");
		grafo.remove(ant, string);
		grafo.getOutArcos(string).imprimirLista();
		grafo.getInArcos(ant).imprimirLista();
		
//		System.out.println("\n"+"\n");
//		grafo.getArcos().imprimirLista();
//		System.out.println("\n"+"\n");
		
		grafo.remove(prueba.toString());
		
//		grafo.getNodos().imprimirLista();
		
//		System.out.println(grafo.contains(prueba.toString()));
	}
	
	@SuppressWarnings("unchecked")
	public static Dominio obtenerComposicion(String[] Entrada, String[] Salida) {
		
		String[] funcR = new String[0];
//		System.out.println("\n------");
		MiLista<String> contRevInic = new MiLista<String>();
		for(String s:Entrada){
//			System.out.println(s);
			contRevInic.add(s);
		}
//		System.out.println("\n------");
		Dominio inicial = new Dominio(Entrada,contRevInic,funcR,0);
		
		BinaryHeap<Dominio> abiertos = new BinaryHeap<Dominio>();
		abiertos.agregar(inicial);
		
		while (!abiertos.esVacio()) {
/*			System.out.println(abiertos.toString());
			System.out.println("\n------");
			System.out.println("abiertos no es vaciooo");
			System.out.println("\n------");
*/			Dominio actual = (Dominio) abiertos.getMin();
			abiertos.removeMin();
			
			Lista<String> revisar = actual.getContArev();
			DynamicArray listaDom = actual.getCont();

/*			for(int i=0;i<revisar.getSize();i++) {
				System.out.println(((String) revisar.toArray()[i]));
			}
			System.out.println("\n------");
*/			
			int k=0;
			boolean sali = false;
			
			while(k<Salida.length && !sali) {
//				System.out.println(Salida[k]);
//				System.out.println("\n------");
				if(listaDom.binarySearchPos(Salida[k]) == -1){
					sali = true;
//					System.out.println("No tengo a salida["+k+"]");
//					System.out.println("\n------");
				}
				k++;
			}
			
			if (!sali) {
//				System.out.println("no saliii");
//				System.out.println("\n------");
				return actual;
			}
			
			DynamicArray listafR = actual.getFuncionesRec();
//			System.out.println("\n------");
//			System.out.println("lista de funciones recorridas:");
			
/*			for(Object s:listafR.getArray()){
				if(s!=null)System.out.println(s.toString());
			}
			System.out.println("\n------");
*/			
			
			for (k=0;k<revisar.getSize();k++) {
//				System.out.println("\n---"+k+"---");
//				System.out.println("elemento del dominio actual:");
				String actString = (String) revisar.toArray()[k];
//				System.out.println(revisar.toArray()[k]);
//				System.out.println("\n------");
				DynamicArray sucesores = ((MiLista<Nodo>) grafo.getSucs(
						(String)revisar.toArray()[k])).toDynamicArray();
				
//				System.out.println("\n------");
//				System.out.println("mis sucesores son:");
/*				for(Object s:sucesores.getArray()){
					if(s!=null)System.out.println(s.toString());
				}

				System.out.println("\n------");
*/				
				for (int j=0;j<sucesores.getPosicion();j++) {
					if(listafR.binarySearchPos(sucesores.get(j)) == -1) {
						
						DynamicArray listaPred = ((MiLista<Nodo>) 
								grafo.getPreds(((Nodo)
										sucesores.get(j)).toString())).toDynamicArray();
						
//						System.out.println("\n------");
//						System.out.println("mis predecesores son:");
/*						for(Object s:listaPred.getArray()){
							if(s!=null)System.out.println(s.toString());
						}
						System.out.println("\n------");
*/						
						int i=0;
						boolean noIgual=false;
						
						while(i<listaPred.getPosicion() && !noIgual) {
							if (listaDom.binarySearchPos(listaPred.get(i))==-1){
/*								System.out.println("\n------");
								System.out.println("no tengo este predecesor :(");
								System.out.println("\n------");
*/								noIgual =true;
							}
							i++;
						}
						
						if (!noIgual) {
							/*
							 * Cambio de j por k
							 */
							DynamicArray sucs2 = ((MiLista<Nodo>) 
									grafo.getSucs(((Nodo) 
									sucesores.get(j)).toString())).toDynamicArray();
//									sucesores.get(k)).toString())).toDynamicArray();
							
							
//							System.out.println("\n------");
//							System.out.println("mis sucesores (rango) son:");
/*							for(Object s:sucs2.getArray()){
								if(s!=null)System.out.println(s.toString());
							}
							System.out.println("\n------");
*/							
							int nuevCost = actual.getCosto() + ((Nodo)grafo.get(
									((Nodo)sucesores.get(j)).toString())).getCosto();
//									((Nodo)sucesores.get(k)).toString())).getCosto();
							
/*							System.out.println("\n------");
							System.out.println("el nuevo costo es:"+nuevCost);
							System.out.println("\n------");
*/							
							Dominio nuevo = new Dominio(listaDom,
									(MiLista<String>) revisar.clone(), listafR, nuevCost);
							
							nuevo.agregarFuncionRec(((Nodo) 
									sucesores.get(j)).toString());
//									sucesores.get(k)).toString());
//							System.out.println("\n---revisar---");
//							revisar.imprimirLista();
//							System.out.println("\n------");
							nuevo.eliminardeContArev(actString);
//							nuevo.getContArev().imprimirLista();
							
							for (int l=0;l<sucs2.getPosicion();l++) {
								nuevo.agregarCont(((Nodo) 
										sucs2.get(l)).toString());
								nuevo.agregarAcontArev(((Nodo) 
										sucs2.get(l)).toString());
							}
							
//							System.out.println("\n---final---");
//							nuevo.getContArev().imprimirLista();
							
							abiertos.agregar(nuevo);
							
						}
					}

				}
			}
		}
			
		return new Dominio();
		
	}
	
	public static void buscarComposiciones(int numComp, BufferedReader inFile,
			BufferedWriter outFile) {
		for(int i=0;i<numComp;i++) {
			try {
				String[] primer = inFile.readLine().split("\\), \\(");
				String[] segundo = primer[0].split("\\(");
				String[] tercer = primer[1].split("\\)");
				String[] entrada = segundo[1].split(", ");
				String[] salida = tercer[0].split(", ");
				
				Dominio dom = obtenerComposicion(entrada,salida);

				for(Object s:dom.getFuncionesRec().getArray()){
					if(s!=null)System.out.println(s.toString());
				}
				System.out.println("\n\nEl costo de la vaina es: ");
				System.out.println(dom.toString()+"\n");
				
				outFile.write("(");
				if (dom.getCosto() == 0) {
					outFile.write("), 0\n");
				}
				else {
					int k=0;
					while (k<dom.getFuncionesRec().getPosicion()-1) {
						outFile.write((String)dom.getFuncionesRec().get(k)+", ");
						k++;
					}
					outFile.write((String) dom.getFuncionesRec().get(k)+"), ");
					outFile.write(dom.getCosto()+"\n");
				
				}
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
	
	//String linea = "";
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
		
		buscarComposiciones(numComp,inFile,outFile);
		
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
