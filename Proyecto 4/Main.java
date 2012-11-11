
import java.io.*;

public class Main {

	static Digraph grafo; 
	
	public static BufferedReader obtenerGrafo(BufferedReader inFile,
												int numLinea) {
		try {
			for(int i=0;i<numLinea;i++) {
				String[] primer = inFile.readLine().split(": ");
				System.out.println(primer[0]);
				Nodo nuevFunc = new Nodo(primer[0]);
				nuevFunc.setFuncion(true);
				grafo.add(nuevFunc);
				
				
				String[] segundo = primer[1].split(", ");
				System.out.println(segundo[0]);
				System.out.println(segundo[1]);
				/*String dom = segundo[0].replace("(", "");
				dom = dom.replace(")", "");
				String[] dominio = dom.split(", ");
				for(int j=0;j<dominio.length;j++) {
					grafo.add(new Nodo(dominio[j]));
					grafo.add(new Arco(dominio[j],primer[0]));
				}
				System.out.println(segundo[1]);
				String[] tercero = segundo[1].split(" ");
				System.out.println(tercero[0]);
				System.out.println(tercero[1]);
				String rang = tercero[0].replace("(", "");
				rang = rang.replace(")", "");
				String[] rango = rang.split(", ");
				for(int k=0;k<dominio.length;k++) {
					grafo.add(new Nodo(dominio[k]));
					grafo.add(new Arco(primer[0],dominio[k]));
				}
				
				Nodo nuevito = grafo.get(primer[0]);
				System.out.println(nuevito.toString());
				nuevito.setCosto(Integer.parseInt(tercero[1]));
				*/
				/*
				String[] segundo = primer[1].split("), (");
				String[] dominio = segundo[0].split(", ");
				for(int j=0;j<dominio.length;j++) {
					grafo.add(new Nodo(dominio[j]));
					grafo.add(new Arco(dominio[j],primer[0]));
				}
				String[] tercero = segundo[1].split(") ");
				String[] rango = tercero[0].split(", ");
				for(int k=0;k<rango.length;k++) {
					grafo.add(new Nodo(rango[k]));
					grafo.add(new Arco(primer[0],rango[k]));
				}
				grafo.get(primer[0]).setCosto(Integer.parseInt(tercero[1]));
				*/
			}
			return inFile;
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	
	public static void main(String[] args) {

		
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
