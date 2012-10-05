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
	/*
	 * Constructor
	 */

	public Matriz(){
		this.quad = new DynamicArray();
		DynamicArray dummy = new DynamicArray();
		dummy.addFinal(0);
		this.quad.addFinal(dummy);
	}
	
	/*
	 * Marca un "1" a la posicion (x,y) de la matriz
	 */
	public void add(int x, int y, int val){
		try{
			DynamicArray dummy = (DynamicArray)this.quad.getArray()[y];
			dummy.add(val,x);
		}catch(java.lang.ArrayIndexOutOfBoundsException e){
			System.out.println("Estas agregando fuera de la matriz");
		}
	}
	
	/*
	 * Agrega una columna a la matrix
	 */
	public void addColumna(){
		for(int i=0;i<this.quad.getArray().length;i++){
			DynamicArray dummy = (DynamicArray)this.quad.getArray()[i];
			dummy.addFinal(0);
		}
	}
	
	/*
	 * Remueve la columna numero "pos" de la matriz
	 */
	public void removeColumna(int pos){
		for(int i=0;i<this.quad.getArray().length;i++){
			DynamicArray dummy = (DynamicArray) this.quad.getArray()[i];
			dummy.remove(pos);
		}
	}
	
	/*
	 * Agrega una fila a la matriz
	 */
	public void addFila(){		
		this.quad.crecer(this.quad.getArray().length + 1);
		DynamicArray dummy2 = (DynamicArray) this.quad.getArray()[0];
		this.quad.getArray()[this.quad.getArray().length-1] = new DynamicArray();
		DynamicArray dummy = (DynamicArray) this.quad.getArray()[this.quad.getArray().length-1];
		for(int j=0;j<dummy2.getArray().length;j++){
			dummy.addFinal(0);
		}
	}
	/*
	 * Remueve la fila numero "pos" de la matriz
	 */
	public void removeFila(int pos){
		this.quad.remove(pos);
	}
	
	/*
	 * Imprime la matriz por pantalla
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
	
	public boolean esta(int x, int y){
		try{
			DynamicArray dummy = (DynamicArray)this.quad.getArray()[y];
			return dummy.getArray()[x].equals(1);
		}catch(java.lang.ArrayIndexOutOfBoundsException e){
			System.out.println("Estas revisando un campo que no esta en la matriz");
			return false;
		}
	}

}
