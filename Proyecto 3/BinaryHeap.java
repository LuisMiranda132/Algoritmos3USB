
public class BinaryHeap<T extends Comparable<T>>{

	private Object[] data;
	private int size;
	
	public BinaryHeap(int tam){
		this.data = new Object[tam];
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
			return this.data[0];
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
	
	public void agregar(Arco o){
		if (this.size == this.data.length){
			System.out.println("El heap esta full");
		}else{
			this.data[this.size] = o;
			this.size++;
			
			int i = this.size-1;
			if(this.getPosPadre(i)<0){
				return;
			}
			while(((T)this.data[i]).compareTo((T) this.data[this.getPosPadre(i)])==-1){
				this.cambiar(i,this.getPosPadre(i));
				i = this.getPosPadre(i);
			}		
		}
	}
	
	public void removeMin(){
		if(this.esVacio()){
			System.out.println("El heap esta vacio");
		}else{
			this.size--;
			this.data[0] = this.data[this.size];
			
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
			if(((T)this.data[hijoIzq]).compareTo((T) this.data[hijoDer]) == 1){
				min = hijoDer;
			}else{
				min = hijoIzq;
			}
		}
		
		if(((T)this.data[actual]).compareTo((T) this.data[min]) == 1){
			this.cambiar(actual, min);
			this.reordenar(min);
		}
	}
	
	public void cambiar(int a, int b){
		Object dummy = this.data[a];
		this.data[a] = this.data[b];
		this.data[b] = dummy;
	}
	
	@Override
	public String toString(){
		String dummy = "";
		for(int i=0;i<this.size;i++){
			dummy = dummy + "\n" + this.data[i].toString();
		}
		return dummy;
	}

}
