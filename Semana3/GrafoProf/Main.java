
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public Main() {
    }

    public void cargarDatos(String fin) {
	String linea = "";
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(fin));


        } catch (Exception ioe) {
        }
    }


    public static void main(String args[]) {
	String in  = "file.in";
	String out = "file.out";

	if (args.length == 2) {
	    in  = args[0];
	    out = args[1];
	}

	Digraph d = new DigraphHash();

	

    }
}
