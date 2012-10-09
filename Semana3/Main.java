
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class Main {

	static Lista<Digraph> theLista = new MiLista<Digraph>(); 
	static Lista<Lista<Nodo>> theNodos = new MiLista<Lista<Nodo>>();

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
                		  System.out.print(" ");
                	  }else if(c == 'W'){
                		  System.out.print(new Nodo(fila +","+columna).toString());
                		  grafo.add(new Nodo(fila+","+columna));
                		  System.out.print(" ");
                	  }else{
                		  System.out.print(c);
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
                  System.out.println();
                  maxColumna = Math.max(maxColumna, columna);
                  maxFila = Math.max(maxFila, fila);
                  
                  linea = in.readLine();
                  
            	}
            	}catch(java.lang.NullPointerException e){
            	}
            
            	System.out.println("Filas: "+maxFila+"\nColumnas: "+maxColumna);
            	System.out.println("Nodos: ");
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
            	
            	for(int i=0;i<grafo.getOutArcos("3,2").toArray().length;i++){
            	
System.out.println(grafo.getOutArcos("3,2").toArray()[i]);
            	}
            	
            	for(int i=0;i<lista.toArray().length;i++){
            		System.out.println(lista.toArray()[i]);
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
    
    public static Lista<Integer> verificarAdyacencias(Digraph grafito,
                                               Lista<Nodo> listita) {
        Object [] arregloLista = grafito.getNodos().toArray();
        Lista<Integer> listaAdy = new MiLista<Integer>();
        int i=0;
        System.out.println("verifi");
        while (i < listita.toArray().length) {
              int adyacencias = 0;
              boolean [] arregloVerif = new
                            boolean[grafito.getNodos().toArray().length];
                           
System.out.println(((Nodo)arregloLista[i]).toString());
              adyacencias = contarAdyacencias(((Nodo)arregloLista[i]),
                                               i,arregloVerif,grafito);
              listaAdy.add(adyacencias);
              i++;
        }
       
        return listaAdy;
    }
    
    public static int contarAdyacencias(Nodo nodito,int pos, 
                                boolean[] arregloNod, Digraph grafi) {
        int ady=0;
        
        if (arregloNod[pos] == true) {
            ady = 0;
            return ady;
        }
        else if (arregloNod[pos] == false) {
            arregloNod[pos] = true;
            for (int i=0;
              i < grafi.getOutArcos(nodito.toString()).toArray().length;i++){
                String temp =
            ((Arco)grafi.getOutArcos(nodito.toString()).toArray()[i]).getDst();
                Nodo nTemp = new Nodo(temp);
                int j=0;
                while (!(((Nodo)
                        grafi.getNodos().toArray()[j]).equals(nTemp))) {
                        j++;
                }
                ady += contarAdyacencias(nTemp,j,arregloNod,grafi);
            }
            ady++;
            return ady;
        }
        return 0;
    }


    @SuppressWarnings("unchecked")
	public static void main(String args[]) {
	String in  = "file.in";
	String out = "file.out";

	if (args.length == 2) {
	    in  = args[0];
	    out = args[1];
	}
	
	cargarDatos(in);
	
	for(int i=0;i<theLista.toArray().length;i++){
		Digraph dummy = (DigraphHash) theLista.toArray()[i];
		System.out.println(i);
		System.out.println(dummy.toString());
		
	}
	for(int i=0;i<theNodos.toArray().length;i++){
		Lista<Nodo> dummy = (Lista<Nodo>) theNodos.toArray()[i];
		System.out.println(i);
		for(int j=0;j<dummy.toArray().length;j++){
			System.out.println(dummy.toArray()[j].toString());
		}
	}
	
	Lista<Integer> listitaAdy = new MiLista<Integer>();
	
	listitaAdy = verificarAdyacencias(((Digraph) theLista.toArray()[0]),
            ((Lista<Nodo>)theNodos.toArray()[0]));
        
        System.out.println("Estoy imprimiendo la lista de cantidad de aguas:");
        listitaAdy.imprimirLista();
	
    }
}
