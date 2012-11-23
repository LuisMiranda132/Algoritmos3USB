import java.io.*;
import java.util.Random;

public class Main {

	static Digraph grafo = new DigraphLista(); 
	
	public static BufferedReader obtenerGrafo(BufferedReader inFile,
												int numLinea) {
		try {
			/*
			 * leemos la cantidad de lineas en el archivo correspondientes 
			 * a funciones para construir el grafo
			 */
			for(int i=0;i<numLinea;i++) {
				
				String[] primer = inFile.readLine().split(": \\(");
				Nodo nuevFunc = new Nodo(primer[0]);
				nuevFunc.setFuncion(true);
				//tenemos en primer[0] la funcion, asi que la agregamos al grafo
				grafo.add(nuevFunc);
				

				String[] segundo = primer[1].split("\\), \\(");
				String[] dominio = segundo[0].split(", ");
				//agregamos todos los elementos del dominio de la funcion al grafo
				for(int j=0;j<dominio.length;j++) {
					grafo.add(new Nodo(dominio[j]));
					grafo.add(new Arco(dominio[j],primer[0]));
				}
				String[] tercero = segundo[1].split("\\) ");
				String[] rango = tercero[0].split(", ");
				//agregamos todos los elementos del rango de la funcion al grafo
				for(int k=0;k<rango.length;k++) {
					grafo.add(new Nodo(rango[k]));
					grafo.add(new Arco(primer[0],rango[k]));
				}
				//agregamos al grafo el costo de la funcion.
				grafo.get(primer[0]).setCosto(Integer.parseInt(tercero[1]));
				
			}
			return inFile;
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static Dominio obtenerComposicion(String[] Entrada, String[] Salida) {
		
		String[] funcR = new String[0];
		MiLista<String> contRevInic = new MiLista<String>();
		//agregamos los tipos de datos que debemos revisar, estos son al principio
		//el contenido de la entrada
		for(String s:Entrada){
			contRevInic.add(s);
		}
		Dominio inicial = new Dominio(Entrada,contRevInic,funcR,0);
		
		BinaryHeap<Dominio> abiertos = new BinaryHeap<Dominio>();
		abiertos.agregar(inicial);
		
		/*
		 * almacenamos en nuestra cola de prioridades conjuntos que representan
		 * subgrafos del grafo inicial con las composiciones posibles a realizar
		 * y su costo. Mientras tengamos elementos en la cola continuamos con 
		 * el algoritmo.
		 */
		while (!abiertos.esVacio()) {
			Dominio actual = (Dominio) abiertos.getMin();
			abiertos.removeMin();
			
			Lista<String> revisar = actual.getContArev();
			DynamicArray listaDom = actual.getCont();

			int k=0;
			boolean sali = false;
			/*
			 * revisamos si el contenido de la salida está en nuestro dominio
			 * actual
			 */
			while(k<Salida.length && !sali) {
				if(listaDom.binarySearchPos(Salida[k]) == -1){
					sali = true;
				}
				k++;
			}
			//si esta, entonces retornamos.
			if (!sali) {
				return actual;
			}
			
			DynamicArray listafR = actual.getFuncionesRec();
			
			/*
			 * tenemos en la lista revisar los nodos que vamos a intentar 
			 * expandir para evitar revisar y empilar de mas.
			 */
			for (k=0;k<revisar.getSize();k++) {
				String actString = (String) revisar.toArray()[k];
				DynamicArray sucesores = ((MiLista<Nodo>) grafo.getSucs(
						(String)revisar.toArray()[k])).toDynamicArray();
								
				// revisamos los sucesores del nodo actual
				for (int j=0;j<sucesores.getPosicion();j++) {
					/*
					 * si es una funcion que no hemos recorrido entonces
					 * buscamos a ver si tenemos todos los elementos necesarios
					 * para ejecutarla 
					 */
					if(listafR.binarySearchPos(sucesores.get(j)) == -1) {
						
						DynamicArray listaPred = ((MiLista<Nodo>) 
								grafo.getPreds(((Nodo)
										sucesores.get(j)).toString())).toDynamicArray();
												
						int i=0;
						boolean noIgual=false;
						
						//buscamos que todos lo elementos necesarios esten 
						//en el dominio
						while(i<listaPred.getPosicion() && !noIgual) {
							if (listaDom.binarySearchPos(listaPred.get(i))==-1){
								noIgual =true;
							}
							i++;
						}
						
						/*
						 * si estan todos los elementos entonces procedemos a 
						 * construir el nuevo "subgrafo" resultante de ejecutar
						 * esa funcion
						 */
						if (!noIgual) {
							DynamicArray sucs2 = ((MiLista<Nodo>) 
									grafo.getSucs(((Nodo) 
									sucesores.get(j)).toString())).toDynamicArray();
														
							int nuevCost = actual.getCosto() + ((Nodo)grafo.get(
									((Nodo)sucesores.get(j)).toString())).getCosto();
							
							Dominio nuevo = new Dominio(listaDom,
									(MiLista<String>) revisar.clone(), listafR, nuevCost);
							
							nuevo.agregarFuncionRec(((Nodo) 
									sucesores.get(j)).toString());
							nuevo.eliminardeContArev(actString);
							
							/* 
							 * agregamos el rango de la funcion que ejecutamos
							 * al dominio y a los elementos que podemos expandir
							 */
							for (int l=0;l<sucs2.getPosicion();l++) {
								nuevo.agregarCont(((Nodo) 
										sucs2.get(l)).toString());
								nuevo.agregarAcontArev(((Nodo) 
										sucs2.get(l)).toString());
							}
														
							//agregamos al heap el nuevo estado
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
		//leemos del archivo el numero de composiciones que debemos buscar
		for(int i=0;i<numComp;i++) {
			try {
				String[] primer = inFile.readLine().split("\\), \\(");
				String[] segundo = primer[0].split("\\(");
				String[] tercer = primer[1].split("\\)");
				String[] entrada = segundo[1].split(", ");
				String[] salida = tercer[0].split(", ");
				
				Dominio dom = obtenerComposicion(entrada,salida);
				
				outFile.write("(");
				/*
				 * si el costo es cero es porque no hay un camino asi que 
				 * imprimimos la secuencia vacia
				 */
				if (dom.getCosto() == 0) {
					outFile.write("), 0, 0\n");
				}
				/*
				 * sino entonces imprimimos la secuencia de funciones que 
				 * recorrimos y el costo.
				 */
				else {
					int k=0;
					while (k<dom.getFuncionesRec().getPosicion()-1) {
						outFile.write((String)dom.getFuncionesRec().get(k)+", ");
						k++;
					}
					outFile.write((String) dom.getFuncionesRec().get(k)+"), ");
					outFile.write(dom.getCosto()+", 0\n");
				
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {

	String in = "file.in";
	String out = "file.out";
	
	if(args.length == 2){
		in = args[0];
		out = args[1];
	}
	
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
		
		int numComp = Integer.parseInt(inFile.readLine());
		
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
