
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
            
            while ((linea = in.readLine()) != null) {
              System.out.println(linea);
            }
            

        } catch (Exception ioe) {
          System.out.println();
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
