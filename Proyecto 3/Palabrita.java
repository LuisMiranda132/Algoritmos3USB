/**
 * 
 * @author Gabriela Limonta, Luis Miranda
 * 
 * Clase que almacena la informacion que va en los nodos del heap; es decir,
 * las palabras comunes entre dos idiomas.
 */

import java.lang.Comparable;

public class Palabrita implements Comparable<Palabrita>  {
		protected String palab;
		protected int numLetras;
	
	
		public Palabrita(String nPal) {
			this.palab = nPal;
			this.numLetras = nPal.length();
		}
		
		public int compareTo(Palabrita p) {
			if(this.numLetras > p.numLetras){
				return 1;
			}else if (this.numLetras < p.numLetras){
				return -1;
			}
			return 0;
		}
		
		public String toString() {
			return this.numLetras + " " + this.palab;
		}
	
	}