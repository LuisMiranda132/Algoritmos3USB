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
	private int posicion = 0;
	
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

	public void removeOrdMov(int pos){
		
		this.add(null, pos);
		
//		for(int j = this.posicion; j >= pos ;j--){
		for(int j = pos+1; j<this.posicion ;j++){
			this.add(this.get(j), j-1);
		}
		
		this.posicion--;
	}
	
	public void addOrdMov(Object o,int pos){
		
		if(this.posicion+1 == this.getArray().length){
			this.crecer(2*this.getArray().length);
		}
		
		for(int j = this.posicion; j >= pos ;j--){
			this.add(this.get(j), j+1);
		}
		
		this.posicion++;
		this.add(o, pos);
	}
	
	public int addOrd(Object x){
		String cajita = x.toString();
		int i = 0;
		
		if(this.get(0)==null){
			this.add(x, 0);
			this.posicion++;
		}else{
		
			while(this.get(i)!=null&&i<this.getArray().length&&cajita.compareTo(this.get(i).toString())>0){
				i++;
			}
			
//			i = this.binarySearchPosUlt(x);
						
//			System.out.print(i+": ");
			
			Object dummy = this.get(i);
			
//			if(dummy!=null)System.out.println(this.get(i).toString());
						
			if(dummy!=null)if(dummy.toString().compareTo(cajita)==0)return -1;
			
			if(this.posicion+1 == this.getArray().length){
				this.crecer(2*this.getArray().length);
			}
			
			for(int j = this.posicion; j >= i ;j--){
				this.add(this.get(j), j+1);
			}
			
			this.add(x, i);
			this.posicion++;
		
		}
		return i;
		
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

	public Object clone(){
		DynamicArray dummy = new DynamicArray();
		for(Object o: this.array){
			dummy.addFinal(o);
		}
		return dummy;
	}
	
	public int binarySearchPos(Object element){
    	Object[] array = this.array;
    	int min = 0, max = this.posicion, mid = 0;
    	boolean encontre = false;
    	
    	while(!encontre && min <= max){
    		mid = (min + max)/2;
    		if(mid >= this.posicion)break;

    		if(array[mid].toString().compareTo(element.toString()) == 0){
    			encontre = true;
    		}else if(array[mid].toString().compareTo(element.toString()) > 0){
    			max = mid -1;
    		}else{
    			min = mid + 1;
    		}
    		
    	}
    	
    	if(encontre){
    		return mid;
    	}
    	return -1;
    }
	
	public int binarySearchPosUlt(Object element){
    	Object[] array = this.array;
    	int min = 0, max = this.posicion, mid = 0;
    	boolean encontre = false;
    	
    	while(!encontre && min <= max){
    		mid = (min + max)/2;
    		if(mid >= this.posicion)break;

    		if(array[mid].toString().compareTo(element.toString()) == 0){
    			encontre = true;
    		}else if(array[mid].toString().compareTo(element.toString()) > 0){
    			max = mid -1;
    		}else{
    			min = mid + 1;
    		}
    		
    	}
    	
    	if(encontre){
    		return mid;
    	}
    	return mid;
    }
	
	public Object binarySearch(Object element){
    	Object[] array = this.array;
    	int min = 0, max = this.posicion, mid = 0;
    	boolean encontre = false;
    	
    	while(!encontre && min <= max){
    		mid = (min + max)/2;
    		if(mid >= this.posicion)break;

    		if(array[mid].toString().compareTo(element.toString()) == 0){
    			encontre = true;
    		}else if(array[mid].toString().compareTo(element.toString()) > 0){
    			max = mid -1;
    		}else{
    			min = mid + 1;
    		}
    		
    	}
    	
    	if(encontre){
    		return array[mid];
    	}
    	return null;
    }
	
	public int getPosicion(){
		return this.posicion;
	}
}
