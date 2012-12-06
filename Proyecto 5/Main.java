import java.io.*;
import java.util.Scanner;

import java.util.Random;

public class Main {
	
	static Scanner scan = null;
	
	public static BinaryHeap<Arco> leerArchivo(int numEscenario) {
        
		BinaryHeap<Arco> lados = new BinaryHeap<Arco>();
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
				lados.agregar(aux);
			}
		}
		int yaConect = scan.nextInt();
		//System.out.println(yaConect);
		for(int j=0;j<yaConect;j++) {
			int c1 = scan.nextInt();
			int c2 = scan.nextInt();
			Arco aux2 = new Arco(c1,c2,0);
			lados.agregar(aux2);
		}
		BinaryHeap<Arco> lados2 = Kruskal(lados,numCiudad);
		return lados2;
	}
	
	public static BinaryHeap<Arco> Kruskal(BinaryHeap<Arco> lad, int nC) {
		DisjointSet E = new DisjointSet(nC);
		BinaryHeap<Arco> ladK = new BinaryHeap<Arco>();
		int i=0;
		while (E.getConexas() > 1) {
			//System.out.println("---Iteracion "+i+"---");
			Arco e = (Arco) lad.getMin();
			lad.removeMin();
			//System.out.println("---Revisamos el arco: "+e.toString()+"---");
			int[] rep = E.getRepres();
			//System.out.println("---origen:"+rep[e.getSrc()]+"---");
			//System.out.println("---destino:"+rep[e.getDst()]+"---");
			if (rep[e.getSrc()] != rep[e.getDst()]) {
				//System.out.println("---Entre al if---");
				E.union(e.getSrc(), e.getDst());
				ladK.agregar(e);
			}
			i++;
		}
		return ladK;
	}
	
	public static void imprimirArch(BinaryHeap<Arco> lK, BufferedWriter out) {
		try {
			int cost = 0;
			while(!lK.esVacio()) {
				Arco act = (Arco) lK.getMin();
				lK.removeMin();
				cost += act.getCost();
			}
			out.write(cost+"\n");
			System.out.println(cost);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void prueba(){
		DynamicArray dummy = new DynamicArray(2);
		Random random = new Random();
		
		for(int i=50000;i>0;i--){
			Arco arco = new Arco(random.nextInt(20),random.nextInt(20), random.nextInt(10000));
			dummy.addFinal(arco);
		}
		
		for(int i= 0;i < dummy.getPosicion();i++){
			System.out.println(((Arco)dummy.get(i)).getCost()+": "+dummy.get(i).toString());
		}
		
		System.out.println("\n----------------\n");
		dummy.heapSort();
		
		for(int i= 0;i < dummy.getPosicion();i++){
			System.out.println(((Arco)dummy.get(i)).getCost()+": "+ dummy.get(i).toString());
		}
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
	
	public static void main(String[] args) {
		
		prueba();
/*
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
			BinaryHeap<Arco> ladKrus = leerArchivo(i);
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
	*/

	}

}
