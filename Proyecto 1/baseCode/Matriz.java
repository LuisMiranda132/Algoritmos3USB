/**
 * 
 * @author Luis Miranda, Gabriela Limonta
 * 
 * Clase que implementa una matriz utilizando
 * DynamicArrays
 *
 */

public class Matriz {
	
	private DynamicArray quad;
	/**
	 * Constructor
	 */

	public Matriz(){
		this.quad = new DynamicArray();
		DynamicArray dummy = new DynamicArray();
		dummy.addFinal(0);
		this.quad.addFinal(dummy);
	}
	
	/**
	 * Funcion: add
	 * Descripcion: Agrega val a la posicion (x,y) de la matriz
	 * Parametros: x, coordenada x de la matriz; y, coordenada
	 * y de la matriz; val, valor que vamos a agregar a la matriz
	 * Precondicion: true
	 * Postcondicion: retorna true si en la posicion x,y de la 
	 * matriz se sustituye  por el valor val 
	 */
	public boolean add(int x, int y, int val){
		try{
		     DynamicArray dummy = (DynamicArray)this.quad.getArray()[y];
		     dummy.add(val,x);			
		}catch(java.lang.ArrayIndexOutOfBoundsException e){
			return false;
		}
		return true;
	}
	
	/**
	 * Funcion addColumna
	 * Descripcion: Agrega una columna a la matriz
	 * Parametros: N/A
	 * Precondicion: true
	 * Postcondicion: la matriz ahora posee una nueva columna
	 */
	public void addColumna(){
		for(int i=0;i<this.quad.getArray().length;i++){
		     DynamicArray dummy = (DynamicArray)this.quad.getArray()[i];
		     dummy.addFinal(0);
		}
	}
	
	/**
	 * Funcion: removeColumna
	 * Descripcion: Remueve la columna numero "pos" de la matriz
	 * Parametros: pos, posicion de la columna que vamos a eliminar
	 * Precondicion: pos < this.quad.getArray().length
	 * Postcondicion: Se elimina la columna pos de la matriz. 
	 */
	public void removeColumna(int pos){
		for(int i=0;i<this.quad.getArray().length;i++){
		     DynamicArray dummy = (DynamicArray) this.quad.getArray()[i];
		     dummy.remove(pos);
		}
	}
	
	/**
	 * Funcion: addFila
	 * Descripcion: Agrega una fila a la matriz
	 * Parametros: N/A
	 * Precondicion: true
	 * Postcondicion: la matriz ahora tiene una nueva fila.
	 */
	public void addFila(){		
		this.quad.crecer(this.quad.getArray().length + 1);
		DynamicArray dummy2 = (DynamicArray) this.quad.getArray()[0];
		this.quad.getArray()[this.quad.getArray().length-1] = 
							new DynamicArray();
		DynamicArray dummy = (DynamicArray) 
			    this.quad.getArray()[this.quad.getArray().length-1];
		for(int j=0;j<dummy2.getArray().length;j++){
			dummy.addFinal(0);
		}
	}
	/**
	 * Funcion: remove
	 * Descripcion: Remueve la fila numero "pos" de la matriz
	 * Parametros: pos, posicion de la fila que vamos a eliminar
	 * Precondicion: pos < this.quad.getArray().length
	 * Postcondicion: se ha eliminado la fila pos de la lista
	 */
	public void removeFila(int pos){
		this.quad.remove(pos);
	}
	
	/**
	 * Funcion: print
	 * Descripcion: Imprime la matriz por pantalla
	 * Parametros: N/A
	 * Precondicion: true
	 * Postcondicion: true
	 */
	public void print(){
		DynamicArray dummy;
		for(int i=0;i<this.quad.getArray().length;i++){
			dummy = (DynamicArray) this.quad.getArray()[i];			
			for(int j=0;j<dummy.getArray().length;j++){
				System.out.print(dummy.getArray()[j]+" ");
			}	
			System.out.println();
		}			
	}
	
	/*
	 * Chequea si en la posicion (x,y) esta el elemento 1
	 */
	public boolean esta(int x, int y){
		try{
		     DynamicArray dummy = (DynamicArray)this.quad.getArray()[y];
		     return dummy.getArray()[x].equals(1);
		}catch(java.lang.ArrayIndexOutOfBoundsException e){
			return false;
		}
	}

}
