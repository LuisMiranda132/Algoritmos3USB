import java.util.Random;

public class Main {

	public static void main(String[] args) {
		BinaryHeap<Palabrita> heap = new BinaryHeap<Palabrita>();
		
		Random num = new Random(); 

		for(int i=0; i<9999;i++){
			heap.agregar(new Palabrita("nana",4));
		}
		
		System.out.println(heap.getMin());
		heap.agregar(new Palabrita("bu",2));
		System.out.println(heap.getMin());
		heap.removeMin();
		System.out.println(heap.getMin());
		
	for(int i=0; i<10000;i++){
			System.out.println(i + ": " + heap.getMin().toString());
			heap.removeMin();
		}
		
		
		
		
		
	}

}
