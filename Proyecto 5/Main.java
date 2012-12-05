import java.io.*;
import java.util.Scanner;

public class Main {
	
	static Scanner scan = null;
	
	public static DynamicArray leerArchivo(int numEscenario) {
        
		DynamicArray lados = new DynamicArray();
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
				lados.addOrd(aux);
			}
		}
		int yaConect = scan.nextInt();
		//System.out.println(yaConect);
		for(int j=0;j<yaConect;j++) {
			int c1 = scan.nextInt();
			int c2 = scan.nextInt();
			Arco aux2 = new Arco(c1,c2,0);
			lados.addOrd(aux2);
		}
		DynamicArray lados2 = Kruskal(lados,numCiudad);
		return lados2;
	}
	
	public static DynamicArray Kruskal(DynamicArray lad, int nC) {
		DisjointSet E = new DisjointSet(nC);
		DynamicArray ladK = new DynamicArray();
		int i=0;
		while (E.getConexas() > 1) {
			Arco e = (Arco) lad.get(i);
			int[] rep = E.getRepres();
			if (rep[e.getSrc()] != rep[e.getDst()]) {
				E.union(e.getSrc(), e.getDst());
				ladK.addOrd(e);
			}
			i++;
		}
		return ladK;
	}
	
	public static void imprimirArch(DynamicArray lK, BufferedWriter out) {
		try {
			int cost = 0;
			for(int i=0;i<lK.getPosicion();i++) {
				Arco act = (Arco) lK.get(i);
				cost += act.getCost();
			}
			out.write(cost+"\n");
			System.out.println(cost);
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
			DynamicArray ladKrus = leerArchivo(i);
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
