import java.lang.reflect.Array;

/**
 * 
 * @author Gabriela Limonta, Luis Miranda
 * 
 * Implementacion de arreglos dinamicos
 * 
 */

public class DynamicArray{
	
	private Object[] array;
	
	/*
	 * Constructor
	 */
	public DynamicArray(){
		this.array = new Object[0];
	}
	
	/*
	 * Agrega un elemento al final del arreglo. El arreglo final es
	 * es mayor que el de origen.
	 */
	public void addFinal(Object x){
		this.resizeArray(this.array, this.array.length+1);
		this.array[this.array.length -1] = x;
	}
	
	/*
	 * Agrega un elemento en la posicion "pos" del arreglo.
	 */
	public void add(Object x, int pos){
		this.getArray()[pos] = x;
	}

	/*
	 * Aumenta el arreglo
	 */
	public void crecer(int x){
		this.resizeArray(this.array, x);
	}
	
	/*
	 * Chequea si el elemento "x" esta en el arreglo
	 */
	public boolean existe(Object x) {
		int i = 0;
		boolean e = false;
		if (this.getArray().length == 0) 
				return false;
		else {
		while((this.getArray()[i] != x) && (i < this.getArray().length)) {
			i++;
		}
		
		if (this.getArray()[i] == x)
			e = true;
		
		return e;
		}
	}
	
	/*
	 * Elimina un elemento que este en cierta posicion
	 */
	public void remove(int posicion){
		Object[] dummy = new Object[this.array.length-1];
		
		System.arraycopy(this.array, 0, dummy, 0, posicion);
		System.arraycopy(this.array, posicion+1, dummy, posicion, this.array.length-(posicion+1));
		
		this.array = dummy;
	}
	
	public Object get(int pos){
		return this.getArray()[pos];
	}
	
	/*
	 * Retorna el arreglo
	 */
	public Object[] getArray(){
		return this.array;
	}
	
	/*
	 * Se encarga de cambiarle el tamano al arreglo
	 */
	private void resizeArray(Object oldAr, int newSi){

		int oldSi = Array.getLength(oldAr);
		
		Class elementType = oldAr.getClass().getComponentType();
		Object newArray = Array.newInstance(elementType, newSi);		
			
		int preserveLength = Math.min(oldSi, newSi);
		
		if(preserveLength > 0){
			System.arraycopy(oldAr, 0, newArray, 0, preserveLength);
		}

		this.array = (Object[]) newArray;
	}

	
}
