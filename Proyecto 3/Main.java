import java.util.Random;

public class Main {

	public static void main(String[] args) {
		BinaryHeap<Arco> heap = new BinaryHeap<Arco>();
		
		Random num = new Random(); 

		for(int i=0; i<9999;i++){
			heap.agregar(new Arco("B","B", num.nextInt(1000)));
		}
		
		System.out.println(heap.getMin());
		heap.agregar(new Arco("A","A", -1));
		System.out.println(heap.getMin());
		heap.removeMin();
		System.out.println(heap.getMin());
		
	for(int i=0; i<10000;i++){
			System.out.println(i + ": " + heap.getMin().toString());
			heap.removeMin();
		}
		
		
		
		
		
	}

}
