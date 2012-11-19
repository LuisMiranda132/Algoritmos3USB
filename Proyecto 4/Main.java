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
		
/*		Lista<String> inic = new MiLista<String>();
		Lista<String> funcA = new MiLista<String>();
		
		for(int i=0;i<Entrada.length;i++) {
			inic.add(Entrada[i]);
			Lista<Nodo> sucesoritos = grafo.getSucs(Entrada[i]);
			for(int f=0;f<sucesoritos.getSize();f++) {
				System.out.println(((Nodo) sucesoritos.toArray()[f]).toString());
				funcA.add(((Nodo) sucesoritos.toArray()[f]).toString());
			}
		}
		
		funcA.imprimirLista();
		
		
		Lista<String> funcR = new MiLista<String>();
		System.out.println("\n------");
		inic.imprimirLista();
		System.out.println("\n------");
		funcR.imprimirLista();
		System.out.println("\n------");
		Dominio inicial = new Dominio(inic,funcA,funcR,0);

*/		
		
		String[] funcV = new String[0];
		String[] funcR = new String[0];
		System.out.println("\n------");
		for(String s:Entrada){
			System.out.println(s);
		}
		System.out.println("\n------");
		Dominio inicial = new Dominio(Entrada,funcV,funcR,0);
		
		/*for(int i=0;i<Entrada.length;i++) {
			Lista<Nodo> sucesoritos = grafo.getSucs(Entrada[i]);
			Object[] succ = sucesoritos.toArray();
			for(Object f: succ) {
				System.out.println(f.toString());
				inicial.agregarFuncionAb(f.toString());
			}
		}*/
		
		BinaryHeap<Dominio> abiertos = new BinaryHeap<Dominio>();
		abiertos.agregar(inicial);
		
		while (!abiertos.esVacio()) {
			System.out.println(abiertos.toString());
			System.out.println("\n------");
			System.out.println("abiertos no es vaciooo");
			System.out.println("\n------");
			Dominio actual = (Dominio) abiertos.getMin();
			abiertos.removeMin();
			
			DynamicArray listaDom = actual.getCont();

			for(Object s:listaDom.getArray()){
				if(s!=null)System.out.println(s.toString());
			}
			System.out.println("\n------");
			
			int k=0;
			boolean sali = false;
			
			while(k<Salida.length && !sali) {
				System.out.println(Salida[k]);
				System.out.println("\n------");
//				if (!listaDom.contains(Salida[k])) {
				if(listaDom.binarySearchPos(Salida[k]) == -1){
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
			
//<<<<<<< Updated upstream
			DynamicArray listafV = actual.getFuncionesVis();
			System.out.println("\n------");
			System.out.println("lista de funciones visitadas:");

			for(Object s:listafV.getArray()){
				if(s!=null)System.out.println(s.toString());
			}
			System.out.println("\n------");
			
			DynamicArray listafR = actual.getFuncionesRec();
			System.out.println("\n------");
			System.out.println("lista de funciones recorridas:");
			
			for(Object s:listafR.getArray()){
				if(s!=null)System.out.println(s.toString());
			}
			System.out.println("\n------");
			
			
			for (k=0;k<listaDom.getPosicion();k++) {
				System.out.println("\n------");
				System.out.println("elemento del dominio actual:");
				System.out.println(listaDom.get(k));
				System.out.println("\n------");
				DynamicArray sucesores = ((MiLista<Nodo>) grafo.getSucs(
						(String)listaDom.get(k))).toDynamicArray();
				
				System.out.println("\n------");
				System.out.println("mis sucesores son:");
				for(Object s:sucesores.getArray()){
					if(s!=null)System.out.println(s.toString());
				}
				System.out.println("\n------");
				
				for (int j=0;j<sucesores.getPosicion();j++) {
					if(listafV.binarySearchPos(sucesores.get(j)) == -1) {
						listafV.addOrd(sucesores.get(j));
						
						DynamicArray listaPred = ((MiLista<Nodo>) 
								grafo.getPreds(((Nodo)
										sucesores.get(j)).toString())).toDynamicArray();
						
						System.out.println("\n------");
						System.out.println("mis predecesores son:");
						for(Object s:listaPred.getArray()){
							if(s!=null)System.out.println(s.toString());
						}
						System.out.println("\n------");
						
						int i=0;
						boolean noIgual=false;
						
						while(i<listaPred.getPosicion() && !noIgual) {
							if (listaDom.binarySearchPos(listaPred.get(i))==-1){
								System.out.println("\n------");
								System.out.println("no tengo este predecesor :(");
								System.out.println("\n------");
								noIgual =true;
							}
							i++;
						}
						
						if (!noIgual) {
							DynamicArray sucs2 =((MiLista<Nodo>) 
									grafo.getSucs(((Nodo) 
									sucesores.get(k)).toString())).toDynamicArray();
							
							System.out.println("\n------");
							System.out.println("mis sucesores (rango) son:");
							for(Object s:sucs2.getArray()){
								if(s!=null)System.out.println(s.toString());
							}
							System.out.println("\n------");
							
							int nuevCost = actual.getCosto() + ((Nodo)grafo.get(
									((Nodo)sucesores.get(k)).toString())).getCosto();
							
							System.out.println("\n------");
							System.out.println("el nuevo costo es:"+nuevCost);
							System.out.println("\n------");
							
							Dominio nuevo = new Dominio(listaDom,
									listafV, listafR, nuevCost);
							
							nuevo.agregarFuncionRec(((Nodo) 
									sucesores.get(k)).toString());
							
							for (int l=0;l<sucs2.getPosicion();l++) {
								nuevo.agregarCont(((Nodo) 
										sucs2.get(l)).toString());
							}
							
							abiertos.agregar(nuevo);
							
						}
					}
				}/*
				if (listafR.binarySearchPos(listafA.get(k)) == -1){
					System.out.println("\n------");
					System.out.println("wiii no la tengo en mis funciones recorridas!");
					System.out.println("\n------");

					DynamicArray listaPred = 
						((MiLista<Nodo>) grafo.getPreds((String)listafA.get(k)))
								.toDynamicArray();
					
					System.out.println("\n------");
					System.out.println("mis predecesores son:");
					for(Object s:listaPred.getArray()){
						if(s!=null)System.out.println(s.toString());
					}
					System.out.println("\n------");
					
					int i=0;
					boolean noIgual=false;
					
					while(i<listaPred.getPosicion() && !noIgual) {
						if (listaDom.binarySearchPos(listaPred.get(i))==-1){
							System.out.println("\n------");
							System.out.println("no tengo este predecesor :(");
							System.out.println("\n------");
							noIgual =true;
						}
						i++;
					}
					
					if (!noIgual) {
						System.out.println("\n------");
						System.out.println("tengo a todos los predecesoreees :D");
						System.out.println("\n------");
					
						DynamicArray sucs2 =((MiLista<Nodo>) grafo.getSucs((String) 
								listafA.get(k))).toDynamicArray();

						
						
						int nuevCost = actual.getCosto() + ((Nodo)grafo.get(
								(String)listafA.get(k))).getCosto();
						
						Dominio nuevo = new Dominio(listaDom,
								listafA, listafR, nuevCost);
						
						nuevo.agregarFuncionRec((String) listafA.get(k));
						
						for (int l=0;l<sucs2.getPosicion();l++) {
							nuevo.agregarCont(((Nodo) 
									sucs2.get(l)).toString());

							DynamicArray sucs3 = ((MiLista<Nodo>) grafo.getSucs(
									sucs2.get(l).toString())).toDynamicArray();

							for (int r=0;r<sucs3.getPosicion();r++) {
								nuevo.agregarFuncionAb(sucs3.get(r).toString());
							}
						}
						
						abiertos.agregar(nuevo);
					}*/
				}
			}
			
/*
//			for(k=0;k<listaDom.getSize();k++) {
			for(k=0;k<listaDom.getPosicion();k++){
				System.out.println("entre a un for lindo del dijkstra: " + k);
				System.out.println("\n---listaDom---");
				for(Object s:listaDom.getArray()){
					if(s!=null)System.out.println(s.toString());
				}
				System.out.println("\n---listaDom["+k+"]---");
				System.out.println(listaDom.get(k));
				System.out.println("\n---Sucesores---");
				MiLista<Nodo> sucesores = (MiLista<Nodo>) grafo.getSucs((String)listaDom.get(k));
				sucesores.imprimirLista();
				System.out.println("\n------");
				
				DynamicArray suces = sucesores.toDynamicArray();

				for (int j=0;j<suces.getPosicion();j++) {
					System.out.println("y a otro fooor :D");
					System.out.println("\n---Sucesores["+j+"]---");
					System.out.println(((Nodo) suces.get(j)).toString());
					System.out.println("\n------");

					if (!((Nodo) suces.get(j)).getVisitado()) {
						System.out.println("entre al ifff de que no este visitado");
						System.out.println("\n------");
//						Lista<Nodo> predecesores = grafo.getPreds(sucesores.toArray()[j].toString());
						
						
						//Arreglo de Nodos
						
						DynamicArray predecesores = 
							((MiLista<Nodo>) grafo.getPreds((
									(Nodo)suces.get(j)).toString())).toDynamicArray();
						System.out.println("\n---Predecesores---");
						for(Object s:predecesores.getArray()){
							if(s!=null)System.out.println(s.toString());
						}
						System.out.println("\n------");
						
						int i=0;
						boolean noIgual = false;
						while (i<predecesores.getPosicion() && !noIgual) {
							System.out.println("Entre al otro whileee");
							System.out.println("\n---predecesores["+i+"]---");
							System.out.println(predecesores.get(i).toString());
							System.out.println("\n------");
//							if (!listaDom.contains(predecesores.toArray()[i].toString())) {
							if(listaDom.binarySearchPos(predecesores.get(i).toString())==-1){
								System.out.println("entre a ese if :D");
								System.out.println("no tengo a lo que sea");
								System.out.println("\n------");
								noIgual = true;
							}
							i++;
						}
						
						if (!noIgual) {
							System.out.println("ENtre a iguaaaal");*/
							/*
							 * Creo que aqui hay un problema con marcarlos 
							 * visitado antes...
							 */
/*							System.out.println("\n---Sucesores["+j+"]---");
//							System.out.println(((Nodo)sucesores.toArray()[j]).toString());
							System.out.println(suces.get(j).toString());

//							Lista<Nodo> sucs2 = grafo.getSucs(((Nodo)sucesores.toArray()[j]).toString());
							
							
							// Arreglo de Nodos
							
							DynamicArray sucs2 = 
								((MiLista<Nodo>)grafo.getSucs(
										((Nodo)suces.get(j))
											.toString())).toDynamicArray();
							
							System.out.println("Costo actual: "+actual.getCosto());
							System.out.println("nuevo costo func: "+
//									((Nodo) sucesores.toArray()[j]).getCosto());
									((Nodo)suces.get(j)).getCosto());

							int nuevCost = actual.getCosto() + 
//									((Nodo) sucesores.toArray()[j]).getCosto();
									((Nodo)suces.get(j)).getCosto();

							System.out.println("El nuevo costo es: "+nuevCost);
							System.out.println("\n------");
		*/					
							
/*							Dominio nuevo = new Dominio(listaDom.getArray(),
									actual.getFunciones().getArray(),nuevCost);
							nuevo.agregarFuncion(((Nodo)suces.get(j)).toString());
//									((Nodo) sucesores.toArray()[j]).toString());

							/*
							 * Cambie "k" por "l"
							 */
/*							for(int l=0;l<sucs2.getPosicion();l++) {
								nuevo.agregarCont(((Nodo) 
										sucs2.get(l)).toString());
							}
							abiertos.agregar(nuevo);
							
						}
					}
				}
			}*/

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
