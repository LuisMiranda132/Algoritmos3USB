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
	
	/**
	 * Funcion: addFinal
	 * Descripcion: Agrega un elemento al 
	 * final del arreglo. El arreglo final es
	 * es mayor que el de origen.
	 * Precondicion: true
	 * Postcondicion: el elemento es agregado al
	 * arreglo y el tamaño de este crece en uno
	 */
	public void addFinal(Object x){
		this.resizeArray(this.array, this.array.length+1);
		this.array[this.array.length -1] = x;
	}
	
	/**
	 * Funcion: add
	 * Descripcion: Agrega un elemento en 
	 * la posicion "pos" del arreglo.
	 * Precondicion: pos < this.array.length
	 * Postcondicion: this.getArray()[pos] = x
	 */
	public void add(Object x, int pos){
		this.getArray()[pos] = x;
	}

	/**
	 * Funcion: crecer
	 * Descripcion: Aumenta el arreglo de
	 * tamaño.
	 * Precondicion: true
	 * Postcondicion: le agrega x casillas 
	 * al arreglo
	 */
	public void crecer(int x){
		this.resizeArray(this.array, x);
	}
	
	/**
	 * Funcion: existe
	 * Descripcion: Chequea si el elemento 
	 * "x" esta en el arreglo
	 * Precondicion: true
	 * Postcondicion: retorna true si el 
	 * elemento existe y false en caso 
	 * contrario.
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
	
	/**
	 * Funcion: remove
	 * Descripcion: Elimina un elemento
	 * que este en cierta posicion del 
	 * arreglo.
	 * Precondicion: posicion < this.array().length
	 * Postcondicion: el elemento de la posicion
	 * es eliminado del arreglo.
	 */
	public void remove(int posicion){
		Object[] dummy = new Object[this.array.length-1];
		
		System.arraycopy(this.array, 0, dummy, 0, posicion);
		System.arraycopy(this.array, posicion+1, dummy, posicion, 
						this.array.length-(posicion+1));
		
		this.array = dummy;
	}
	
	/**
	 * Funcion: getArray
	 * Descripcion: Retorna el arreglo
	 * Precondicion: true
	 * Postcondicion: retorna this.array
	 */
	public Object[] getArray(){
		return this.array;
	}
	
	/**
	 * Funcion: resizeArray
	 * Descripcion: Se encarga de 
	 * cambiarle el tamano al arreglo.
	 * Precondicion: true
	 * Postcondicion: copia el arreglo viejo 
	 * en uno nuevo con nuevas casillas libres
	 * al final (mayor tamaño).
	 */
	private void resizeArray(Object oldAr, int newSi){

		int oldSi = java.lang.reflect.Array.getLength(oldAr);
		
		Class elementType = oldAr.getClass().getComponentType();
		Object newArray = java.lang.reflect.Array.newInstance(elementType, 
									newSi);		
			
		int preserveLength = Math.min(oldSi, newSi);
		
		if(preserveLength > 0){
			System.arraycopy(oldAr, 0, newArray, 0, preserveLength);
		}

		this.array = (Object[]) newArray;
	}

	
}
