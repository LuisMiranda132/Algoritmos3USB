
public class DynamicArray{
	
	private Object[] array;
	
	public DynamicArray(){
		this.array = new Object[0];
	}
	
	public void addFinal(Object x){
		this.resizeArray(this.array, this.array.length+1);
		this.array[this.array.length -1] = x;
	}
	
	public void add(Object x, int pos){
		this.getArray()[pos] = x;
	}

	public void crecer(int x){
		this.resizeArray(this.array, x);
	}
	
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
	
	public void remove(int posicion){
		Object[] dummy = new Object[this.array.length-1];
		
		System.arraycopy(this.array, 0, dummy, 0, posicion);
		System.arraycopy(this.array, posicion+1, dummy, posicion, this.array.length-(posicion+1));
		
		this.array = dummy;
	}
	
	public Object[] getArray(){
		return this.array;
	}
	
	//private static Object
	private void resizeArray(Object oldArray, int newSize){

		int oldSize = java.lang.reflect.Array.getLength(oldArray);
		
		Class elementType = oldArray.getClass().getComponentType();
		Object newArray = java.lang.reflect.Array.newInstance(elementType, newSize);		
			
		int preserveLength = Math.min(oldSize, newSize);
		
		if(preserveLength > 0){
			System.arraycopy(oldArray, 0, newArray, 0, preserveLength);
		}

		this.array = (Object[]) newArray;
	}

	
}
