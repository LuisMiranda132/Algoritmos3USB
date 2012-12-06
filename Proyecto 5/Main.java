import java.io.*;
import java.util.Scanner;

public class Main {
	
	static Scanner scan = null;
	
	public static int leerArchivo(int numEscenario) {
        
		DynamicArray lados = new DynamicArray(2);
		int numCiudad = scan.nextInt();
		//System.out.println(numCiudad);
		for(int i=0;i<numCiudad;i++) {
			String bleh = scan.nextLine();
			char[] arrChar = bleh.toCharArray();

	        while (arrChar.length == 0) {
	            bleh = scan.nextLine();
	            arrChar = bleh.toCharArray();
	        }
	        //System.out.println(bleh);
			int numVecinas = scan.nextInt();
			//System.out.println(numVecinas);
			for(int k=0;k<numVecinas;k++) {
				int vec = scan.nextInt();
				int costo = scan.nextInt();
				//System.out.println(vec+" "+costo);
				Arco aux = new Arco(i+1,vec,costo);
				lados.addFinal(aux);
			}
		}
		int yaConect = scan.nextInt();
		//System.out.println(yaConect);
		for(int j=0;j<yaConect;j++) {
			int c1 = scan.nextInt();
			int c2 = scan.nextInt();
			Arco aux2 = new Arco(c1,c2,0);
			lados.addFinal(aux2);
		}
		int lados2 = Kruskal(lados,numCiudad);
		return lados2;
	}
	
	public static int Kruskal(DynamicArray lad, int nC) {
		DisjointSet E = new DisjointSet(nC);
		DynamicArray ladK = new DynamicArray(2);
/*		
		for(int j=0;j<lad.getPosicion();j++){
			System.out.println(lad.get(j).toString());
		}
*/		
//		System.out.println("\n-----------\n");
		
		lad.heapSort();
/*		
		for(int j=0;j<lad.getPosicion();j++){
			System.out.println(lad.get(j).toString());
		}
*/		
		
		int i=0;
		int cost=0;
		while (E.getConexas() > 1) {
//			System.out.println("\n--- Iteracion "+i+"---\n");
			Arco e = (Arco) lad.get(i);
//			System.out.println("\n--- Revisamos el arco: "+e.toString()+"---\n ");
			int[] rep = E.getRepres();
//			System.out.println("\n--- origen: "+rep[e.getSrc()]+" ---\n");
//			System.out.println("\n--- destino: "+rep[e.getDst()]+" ---\n");
			if (E.find(e.getSrc()) != E.find(e.getDst())) {
//				System.out.println("\n---Entre al if---\n");
				E.union(e.getSrc(), e.getDst());
				//ladK.addFinal(e);
				cost += e.getCost();
			}
			i++;
		}
		return cost;
	}
	
	public static void imprimirArch(int lK, BufferedWriter out) {
		try {
			//int cost = 0;
			//for(int k=0;k<lK.getPosicion();k++) {
				//Arco act = (Arco) lK.get(k);
				//cost += act.getCost();
			//}
			out.write(lK+"\n");
			System.out.println(lK);
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
