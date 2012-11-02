import java.util.Random;

public class Main {

	public static void main(String[] args) {
		BinaryHeap heap = new BinaryHeap(200);
		
		Random num = new Random(); 

		for(int i=0; i<200;i++){
			heap.agregar(new Arco("B","B", num.nextInt(1000)));
		}
		
		for(int i=0; i<200;i++){
			System.out.println(heap.getMin().toString());
			heap.removeMin();
		}
		
		
		
		
		
	}

}
