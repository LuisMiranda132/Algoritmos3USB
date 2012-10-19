import java.io.*;

public class Main {

	static Lista<Digraph> lista = new MiLista<Digraph>();
	static Lista<Lista<String>> listaPer = new MiLista<Lista<String>>();
	
	public static void BFS(Digraph grafo){
		Cola cola = new Cola(grafo.numVertices);
		boolean[] visitado = new boolean[grafo.numVertices];
		
		if(grafo.contains("ErdosP")){
						
			grafo.get("ErdosP").setErdos(0);
						
			cola.encolar(grafo.get("ErdosP"));
			
			int i=0;
			while(!"ErdosP".equalsIgnoreCase((grafo.getNodos().toArray()[i]).toString())){
				i++;
			}
			visitado[i] = true;
			
			while(!cola.esVacia()){
				
				Nodo dummy = (Nodo) cola.primero();
				cola.desencolar();
				
				for(i=0;i<grafo.getOutDegree(dummy.toString());i++){
					
					Lista<Arco> arcos = grafo.getInArcos(dummy.toString());
					String src = ((Arco)arcos.toArray()[i]).getSrc();
					
					int j = 0;
					while(!src.equalsIgnoreCase((grafo.getNodos().toArray()[j]).toString())){
						j++;
					}
					
					if(!visitado[j]){
						visitado[j]=true;
						grafo.get(src).setErdos(Math.min(grafo.get(src).getErdos(),grafo.get(dummy.toString()).getErdos()+1));
						cola.encolar(grafo.get(src));
					}
				}
			}
		}
		for(int i=0;i<grafo.getNodos().toArray().length;i++){
			System.out.println(grafo.getNodos().toArray()[i].toString()+": "+((Nodo)grafo.getNodos().toArray()[i]).getErdos());
		}
	}
	
	public static void cargarDatos(String fileIn){
		String linea = "";
		BufferedReader in = null;
		Digraph grafo = new DigraphLista();
		Lista<String> listaNodo = new MiLista<String>();
		Lista<String> listaGente = new MiLista<String>();
		
		try {
			in = new BufferedReader(new FileReader(fileIn));
			
			int numCasos = Integer.parseInt(in.readLine());
		
			for(int num = numCasos;num>0;num--){
				linea = in.readLine();
				int numArt = linea.toCharArray()[0]-48;
				int numPer = linea.toCharArray()[2]-48;
			
				System.out.println(numArt +"-"+numPer);
			
				for(int i=0;i<numArt;i++){
					linea = in.readLine();
					System.out.println(linea);
					String[] palabras = linea.split(" ");
					
					for(int j=0;j<palabras.length;j++){
						if(palabras[j].toCharArray()[palabras[j].length()-1] == ',' ||
							
								palabras[j].toCharArray()[palabras[j].length()-1] == ':'){
							
							if(palabras[j].toCharArray()[palabras[j].length()-2] == '.'){
								
								listaNodo.add(palabras[j].split(",")[0].split(".:")[0]);
							}else{
								listaNodo.add(palabras[j].split(",")[0].split(":")[0]);
							}	
						}
					}
				
					for(int k = 0;k<listaNodo.toArray().length;k++){
						Nodo dummy = new Nodo((String) listaNodo.toArray()[k]);
						grafo.add(dummy);
					}
				
					for(int k = 0;k<listaNodo.toArray().length;k++){
						for(int l = 0;l<listaNodo.toArray().length;l++){
							if(l != k){
								grafo.add(new Arco((String) listaNodo.toArray()[k],(String) listaNodo.toArray()[l]));
							}
						}
					}
					
					System.out.println();
					listaNodo.clear();
				}

				for(int i=0;i<numPer;i++){
					listaGente.add(linea = in.readLine());
				}
			
				lista.add(grafo);
				listaPer.add(listaGente);
				listaGente = new MiLista<String>();
				grafo = new DigraphLista();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	/**
	 * Funcion: buscarCamino
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static void buscarCamino() {
		int i=0;
		Lista<String> listaPerAct;
		Digraph digraphAct;
		Nodo perAct;
		
		while (i<lista.getSize()) {
			listaPerAct = ((Lista<String>) listaPer.toArray()[i]);
			digraphAct = ((Digraph) lista.toArray()[i]);
			System.out.println("Escenario"+(i+1));
			int j=0;
			while (j<listaPerAct.getSize()) {
				perAct = digraphAct.get((String) listaPerAct.toArray()[j]);
				System.out.print(perAct.toString());
				System.out.print(": <");
				if (perAct.getErdos() < 2147483647) {
					System.out.print(perAct.toString());
					System.out.print(", ");
					caminoErdos(perAct,digraphAct);
					System.out.println();
				}
				else {
					System.out.println("> ");
				}
				j++;
			}
			i++;
		}
		
	}
	
	public static void caminoErdos(Nodo perA, Digraph graf) {
		Lista<Nodo> listAdy = graf.getSucs(perA.toString());
		int i=0;
		int min = 0;
		
		while (i<listAdy.getSize()) {
			if (((Nodo) listAdy.toArray()[i]).getErdos() < 
					((Nodo) listAdy.toArray()[min]).getErdos()) {
				min = i;
			}
			i++;
		}
		
		if (((Nodo) listAdy.toArray()[min]).getErdos() == 0) {
			System.out.print(listAdy.toArray()[min].toString());
			System.out.println(">");
		}
		else {
			System.out.print(listAdy.toArray()[min].toString());
			System.out.print(", ");
			Nodo nuevAct = graf.get(((Nodo) listAdy.toArray()[min]).toString());
			caminoErdos(nuevAct,graf);
		}

	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String in = "file.in";
		String out = "file.out";
		
		if(args.length == 2){
			in = args[0];
			out = args[1];
		}
		
		cargarDatos(in);
		
		for(int i=0;i<lista.getSize();i++){

			System.out.println("\n"+lista.toArray()[i].toString());
			((Lista<String>)listaPer.toArray()[i]).imprimirLista();
			System.out.println();
			BFS((Digraph)lista.toArray()[i]);
		}
		
		buscarCamino();
				
	}
}
