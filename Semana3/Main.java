import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public Main() {
    }

    public static MiLista<Digraph> cargarDatos(String fin) {
    	String linea = "";
    	char[] arreglin;
        BufferedReader in = null;
        int i;
        int j;
        int k;
        int casos = 0;
        MiLista<Digraph> listaGrafos = new MiLista<Digraph>();

        try {
            in = new BufferedReader(new FileReader(fin));
            linea = in.readLine();
            casos = Integer.getInteger(linea);
            for (k=0;k<casos;k++){
            	// Creamos el nuevo grafo para este mapa
            	Digraph d = new DigraphHash();
            	//Leemos las lineas
            	i =0;
            	while ((linea = in.readLine()) != null) {
            		System.out.println(linea);
            		arreglin = linea.toCharArray();
            		j=0;
            		while (j < arreglin.length){
            			String ii;
        				String jj;
            			if (arreglin[j] == 'W') {
            				ii = Integer.toString(i);
            				jj = Integer.toString(j);
            				Nodo nuevoNodo = new Nodo(ii,jj);
            				d.add(nuevoNodo);
            			}
            			else if ((arreglin[j] == '0') || (arreglin[j] == '1') ||
            					(arreglin[j] == '2') || (arreglin[j] == '3') || 
            					(arreglin[j] == '4') || (arreglin[j] == '5') ||
            					(arreglin[j] == '6') || (arreglin[j] == '7') ||
            					(arreglin[j] == '8') || (arreglin[j] == '9')) {
            				
            				if (arreglin[j+1] == ' ') {
            					ii = String.valueOf(arreglin[j]);
            					jj = String.valueOf(arreglin[j+2]+arreglin[j+3]);
            				}
            				else {
            					ii = String.valueOf(arreglin[j]+arreglin[j+1]);
            					jj = String.valueOf(arreglin[j+3]+arreglin[j+4]);
            				}
            				Arco nuevoArco = new Arco(ii,jj);
            				d.add(nuevoArco);
            				j = 6;
            			}
            			
            			j++;
            		
            		}
            		System.out.println(arreglin);
            		i++;
            	}
            	listaGrafos.add(d);
            }
            return listaGrafos;
        } 
        catch (Exception ioe) {
        	return listaGrafos;
        }

    }


    public static void main(String args[]) {
	String in  = "file.in";
	String out = "file.out";
	MiLista<Digraph> lista = new MiLista<Digraph>();

	if (args.length == 2) {
	    in  = args[0];
	    out = args[1];
	}
	
	lista = cargarDatos(in);
	

    }
}
