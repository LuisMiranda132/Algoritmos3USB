
public class BinaryHeap<T extends Comparable<T>>{

	private DynamicArray data;
	private int size;
	
	public BinaryHeap(int tam){
		this.data = new DynamicArray();
		this.data.crecer(1);
		size = 0;
	}
	
	public boolean esVacio(){
		return this.size == 0;
	}
	
	public Object getMin(){
		if(this.esVacio()){
			System.out.println("Heap esta vacio");
			return null;
		}else{
			return this.data.get(0);
//			return this.data.getArray()[0];
		}	
	}
	
	public int getPosPadre(int pos){
		return (pos - 1)/2;
	}
	
	public int getPosHijoDer(int pos){
		return (pos*2) + 2;
	}
	
	public int getPosHijoIzq(int pos){
		return (pos*2) + 1;
	}
	
	@SuppressWarnings("unchecked")
	public void agregar(Arco o){
		if (this.size == this.data.getArray().length){
			this.data.crecer(2*this.size);
		}
			this.data.add(o, this.size);
//			this.data.getArray()[this.size] = o;
			this.size++;
			
			int i = this.size-1;
			if(this.getPosPadre(i)<0){
				return;
			}
			
			while(((T)this.data.get(i)).compareTo((T) this.data.get(this.getPosPadre(i)))== -1){
//			while(((T)this.data.getArray()[i]).compareTo((T) this.data.getArray()[this.getPosPadre(i)])==-1){
				this.cambiar(i,this.getPosPadre(i));
				i = this.getPosPadre(i);
			}		
		}
	
	
	public void removeMin(){
		if(this.esVacio()){
			System.out.println("El heap esta vacio");
		}else{
			this.size--;
			
			this.data.add(this.data.get(this.size), 0);
//			this.data.getArray()[0] = this.data.getArray()[this.size];
			
			this.reordenar(0);
		}
			
	}
	
	@SuppressWarnings("unchecked")
	public void reordenar(int actual){
		int hijoDer = this.getPosHijoDer(actual);
		int hijoIzq = this.getPosHijoIzq(actual);
		int min;
		if(hijoDer >= this.size){
			if(hijoIzq >= this.size){
				return;
			}else{
				min = hijoIzq;
			}
		}else{
			if(((T)this.data.get(hijoIzq)).compareTo((T) this.data.get(hijoDer)) == 1){
//			if(((T)this.data.getArray()[hijoIzq]).compareTo((T) this.data.getArray()[hijoDer]) == 1){
				min = hijoDer;
			}else{
				min = hijoIzq;
			}
		}
		
		if(((T)this.data.get(actual)).compareTo((T)this.data.get(min)) == 1){
//		if(((T)this.data.getArray()[actual]).compareTo((T) this.data.getArray()[min]) == 1){
			this.cambiar(actual, min);
			this.reordenar(min);
		}
	}
	
	public void cambiar(int a, int b){
		Object dummy = this.data.get(a);
		this.data.add(this.data.get(b), a);
		this.data.add(dummy, b);
		/*
		Object dummy = this.data.getArray()[a];
		this.data.getArray()[a] = this.data.getArray()[b];
		this.data.getArray()[b] = dummy;
		*/
	}
	
	@Override
	public String toString(){
		String dummy = "";
		for(Object o:this.data.getArray()){
//		for(int i=0;i<this.size;i++){
			dummy = dummy + "\n" + o.toString();
		}
		return dummy;
	}

}
