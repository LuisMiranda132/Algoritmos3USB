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
	
		/*
		 * Constructor
		 */
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
		
		/**
	     * Funcion: getPalab
	     * Descripcion: Retorna el vertice src de la arista.
	     * Parametros: N/A
	     * Precondicion: true
	     * Postcondicion: retorna this.palab
	     */
		public String getPalab() {
			return this.palab;
		}
		
		/**
	     * Funcion: getNumLet
	     * Descripcion: Retorna el num asignado a la palabra.
	     * Parametros: N/A
	     * Precondicion: true
	     * Postcondicion: retorna this.let
	     */
		public int getNumLet() {
			return this.numLetras;
		}
	
	}