
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public Main() {
    }

    public static void cargarDatos(String fin) {
	String linea = "";
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(fin));
            
            int numCasos = Integer.parseInt(in.readLine());
            
            while ((linea = in.readLine()) != null) {
              if(!(linea.equalsIgnoreCase(""))){
//            	  System.out.println(linea);
                  for(char c : linea.toCharArray()){
                	  if(c == 'L'){
                		  System.out.print(" ");
                	  }
                	  else{
                		  System.out.print(c);
                	  }
                  }
                  System.out.println();
              }
            }
            

        } catch (Exception ioe) {
          System.out.println(ioe);
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
