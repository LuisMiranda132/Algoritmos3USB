/**
 * @author Gabriela Limonta, Luis Miranda
 * 
 * Clase que implementa el Main del programa.
 *
 */
import java.io.*;


public class Main {

	static Lista<Digraph> theLista = new MiLista<Digraph>(); 
	static Lista<Lista<Nodo>> theNodos = new MiLista<Lista<Nodo>>();
	static boolean[] siEstan;
	static int ady=0;

    public static void cargarDatos(String fin) {
	String linea = "";
        BufferedReader in = null;

        Digraph grafo = new DigraphHash();
        Lista<Nodo> lista = new MiLista<Nodo>();
        
        int fila, columna, maxFila = 0, maxColumna = 0;
        
        try {
            in = new BufferedReader(new FileReader(fin));
            
            fila = 0;
            columna = 0;
            int numCasos = Integer.parseInt(in.readLine());
            in.readLine();
            
            while ((linea = in.readLine()) != null) {
            	try{
            	while(!linea.equalsIgnoreCase("")){
            	  char p1 = '-',p2 = '-';
            	  
            	  columna = 0;
            	  for(char c : linea.toCharArray()){
                	  if(c == 'L'){
                	  }else if(c == 'W'){
                		  grafo.add(new Nodo(fila+","+columna));
                	  }else{
                		  if(c != ' '){
                			  if(p1 == '-'){
                				  p1 = c;
                			  }else{
                				  p2 = c;
                				  lista.add(new Nodo(p1+","+p2));
                			  }
                		  }
                	  fila--;
                	  }
                	  columna++;
                  }
                  fila++;
                  maxColumna = Math.max(maxColumna, columna);
                  maxFila = Math.max(maxFila, fila);
                  
                  linea = in.readLine();
                  
            	}
            	}catch(java.lang.NullPointerException e){
            	}
            
            	for(int i=0;i<grafo.getNodos().toArray().length;i++){
            	
            		int x = -1;
            		int y = -1;
            	
            		for(char c :grafo.getNodos().toArray()[i].toString().toCharArray()){
            			int casilla = c - 48;
            		
            			if(c != ','){
            				if(x == -1){
            					x = casilla;
            				}else{
            					y = casilla;
            				}
            			}	
            	
            			if(grafo.contains((x-1)+","+(y-1))){
            				grafo.add(new Arco((x+","+y),((x-1)+","+(y-1))));
            			}
            			if(grafo.contains((x-1)+","+(y))){
            				grafo.add(new Arco((x+","+y),((x-1)+","+(y))));
            			}
            			if(grafo.contains((x-1)+","+(y+1))){
            				grafo.add(new Arco((x+","+y),((x-1)+","+(y+1))));
            			}
            			
            			if(grafo.contains((x+1)+","+(y-1))){
            				grafo.add(new Arco((x+","+y),((x+1)+","+(y-1))));
            			}
            			if(grafo.contains((x+1)+","+(y+1))){
            				grafo.add(new Arco((x+","+y),((x+1)+","+(y+1))));
            			}    			
            			if(grafo.contains((x+1)+","+(y))){
            				grafo.add(new Arco((x+","+y),((x+1)+","+(y))));
            			}    			
            			if(grafo.contains((x)+","+(y-1))){
            				grafo.add(new Arco((x+","+y),((x)+","+(y-1))));
            			}
            			if(grafo.contains((x)+","+(y+1))){
            				grafo.add(new Arco((x+","+y),((x)+","+(y+1))));
            			}
            			
            		}
            	}
            	
            	
            	theLista.add(grafo);
            	fila = 0; 
            	columna = 0;
            	maxColumna = 0; 
            	maxFila = 0;
            	numCasos--;
            	grafo = new DigraphHash();
            	theNodos.add(lista);
            	lista = new MiLista<Nodo>();
            }
        } catch (Exception ioe) {
          System.out.println(ioe);
        }
        
    }
 
    public static void contarAdyacencias(Nodo nodito, int pos){
        int i = 0;
        Digraph grafo = (DigraphHash) theLista.toArray()[pos];
    	try{
    		while(!nodito.toString().equalsIgnoreCase((grafo.getNodos().toArray()[i]).toString())){
    			i++;
    		}
        
    		if(!(siEstan[i])){
    			siEstan[i] = true;
    			ady++;
       
    			for(int j =0;j<grafo.getOutDegree(nodito.toString());j++){
    				Arco dummy = (Arco) grafo.getOutArcos(nodito.toString()).toArray()[j];
    				Nodo nodo = new Nodo(dummy.getDst());
    				contarAdyacencias(nodo, pos);
    			}
        	}
    	}catch(java.lang.ArrayIndexOutOfBoundsException e){
    		System.out.println("El nodo "+nodito+" no pertenece al grafo");
    	}
    }


    @SuppressWarnings("unchecked")
	public static void main(String args[]) {
	String in  = "file.in";
	String out = "file.out";

	if (args.length == 2) {
		in  = args[0];
	    out = args[1];
	}
	
	FileWriter sali = null;
	
	try {
		sali = new FileWriter(out);
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	BufferedWriter salida = new BufferedWriter(sali);
	
	cargarDatos(in);
		
	for(int i=0;i<theLista.toArray().length;i++){
		
		Digraph dummy = (DigraphHash) theLista.toArray()[i];
		

		siEstan = new boolean[dummy.getNodos().toArray().length];
		
		Lista<Nodo> dummy2 = (Lista<Nodo>) theNodos.toArray()[i];
		
		for(int j=0;j<dummy2.toArray().length;j++){
				
			contarAdyacencias((Nodo)dummy2.toArray()[j],i);
			
			try {
				salida.write(ady+"\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			ady = 0;
		}
		try {
			salida.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	try {
		salida.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
		
    }	
}
