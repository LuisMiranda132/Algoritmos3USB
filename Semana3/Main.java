
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class Main {

    public Main() {
    }

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
            
            while ((linea = in.readLine()) != null) {
              if(!(linea.equalsIgnoreCase(""))){
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
              }
            }
            System.out.println("Filas: "+maxFila+"\nColumnas: "+maxColumna);
            System.out.println("Nodos: ");
            for(int i=0;i<grafo.getNodos().toArray().length;i++){
            	
            	int x = -1;
            	int y = 0;
            	
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
           
        } catch (Exception ioe) {
          System.out.println(ioe);
        }
        
        for(int i=0;i<lista.toArray().length;i++){
        	System.out.println(lista.toArray()[i].toString());
        }
        
    }


    public static void main(String args[]) {
	String in  = "file.in";
	String out = "file.out";

	if (args.length == 2) {
	    in  = args[0];
	    out = args[1];
	}
	
	cargarDatos(in);

	Digraph d = new DigraphHash();
	

	

    }
}
