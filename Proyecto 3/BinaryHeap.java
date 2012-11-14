/**
 * 
 * @author Gabriela Limonta, Luis Miranda
 * 
 * Implementacion de heap binario
 */
public class BinaryHeap<T extends Comparable<T>>{

	private DynamicArray data;
	private int size;
	
	/*
	 * constructor
	 */
	public BinaryHeap(){
		this.data = new DynamicArray();
		this.data.crecer(1);
		size = 0;
	}
	
	public boolean esVacio(){
		return this.size == 0;
	}
	
	public Object getMin(){
		if(this.esVacio()){
			return null;
		}else{
			return this.data.get(0);
		}	
	}
	
	private int getPosPadre(int pos){
		return (pos - 1)/2;
	}
	
	private int getPosHijoDer(int pos){
		return (pos*2) + 2;
	}
	
	private int getPosHijoIzq(int pos){
		return (pos*2) + 1;
	}
	
	@SuppressWarnings("unchecked")
	public void agregar(Palabrita p){
		if (this.size == this.data.getArray().length){
			this.data.crecer(2*this.size);
		}
			this.data.add(p, this.size);
			this.size++;
			
			int i = this.size-1;
			if(this.getPosPadre(i)<0){
				return;
			}
			
			while(((T)this.data.get(i)).compareTo((T) this.data.get(
													this.getPosPadre(i)))== -1){
				this.cambiar(i,this.getPosPadre(i));
				i = this.getPosPadre(i);
			}		
		}
	
	@SuppressWarnings("unchecked")
	public void agregar(Nodo p){
		if (this.size == this.data.getArray().length){
			this.data.crecer(2*this.size);
		}
			this.data.add(p, this.size);
			this.size++;
			
			int i = this.size-1;
			if(this.getPosPadre(i)<0){
				return;
			}
			
			while(((T)this.data.get(i)).compareTo((T) this.data.get(
													this.getPosPadre(i)))== -1){
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
			
			this.reordenar(0);
		}
			
	}
	
	@SuppressWarnings("unchecked")
	private void reordenar(int actual){
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
			if(((T)this.data.get(hijoIzq)).compareTo((T) this.data.get(
																hijoDer)) == 1){
				min = hijoDer;
			}else{
				min = hijoIzq;
			}
		}
		
		if(((T)this.data.get(actual)).compareTo((T)this.data.get(min)) == 1){
			this.cambiar(actual, min);
			this.reordenar(min);
		}
	}
	
	private void cambiar(int a, int b){
		Object dummy = this.data.get(a);
		this.data.add(this.data.get(b), a);
		this.data.add(dummy, b);
	}
	
	@Override
	public String toString(){
		String dummy = "";
		for(int i=0;i<this.size;i++){
			dummy = dummy + "\n" + this.data.getArray()[i].toString();
		}
		return dummy;
	}
	
	public Object clone(){
		BinaryHeap<T> dummy = new BinaryHeap<T>();
		dummy.data = (DynamicArray)this.data.clone();
		dummy.size = this.size;
		return dummy;
	}

}
