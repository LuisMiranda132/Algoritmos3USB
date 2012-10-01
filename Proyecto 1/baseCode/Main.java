
public class Main {

	public static void main(String[] args) {
		
		Arco prueba = new Arco("pedro","juan");

		Arco prueba1 = new Arco("pedro","pepe");

		Arco prueba2 = new Arco("lalala","lelele");
//		Arco prueba1 = (Arco)prueba.clone();

//		System.out.println(prueba.getDst()+"\n"+prueba1.getSrc());
		
//		System.out.println(prueba.equals(prueba1));
		
		Lista<Arco> listaPrueba = new MiLista<Arco>();
		Lista<Arco> listaPrueba2 = new MiLista<Arco>();
		boolean a=true;
		
		System.out.println("isEmpty");
		a = listaPrueba.isEmpty();
		System.out.println(a);
		
		a = listaPrueba.add(prueba);
//		System.out.println(a);
		
		System.out.println("getSize");
		int tam = listaPrueba.getSize();
		System.out.println(tam);

		a = listaPrueba.add(prueba1);
//		System.out.println(a);
		
		System.out.println("getSize");
		tam = listaPrueba.getSize();
		System.out.println(tam);
		
		a = listaPrueba.contains(prueba2);
//		System.out.println(a);
		
		System.out.println("isEmpty");
		a = listaPrueba.isEmpty();
		System.out.println(a);
		
		a = listaPrueba2.isEmpty();
//		System.out.println(a);
		
		a = listaPrueba.remove(prueba);
//		System.out.print("remove");
//		System.out.println(a);
		
		a = listaPrueba.contains(prueba1);
		System.out.println("contains");
		System.out.println(a);
		
		
		System.out.println("getSize");
		tam = listaPrueba.getSize();
		System.out.println(tam);
		
		listaPrueba.clear();
		
		tam = listaPrueba.getSize();
//		System.out.println(tam);
		
		a = listaPrueba.add(prueba);
//		System.out.println(a);
		a = listaPrueba.add(prueba1);
//		System.out.println(a);
		
		a = listaPrueba2.add(prueba);
//		System.out.println(a);
		a = listaPrueba2.add(prueba1);
//		System.out.println(a);
		
		System.out.println("Equals");
		System.out.println(listaPrueba.equals(listaPrueba2));
		System.out.println(listaPrueba2.equals(listaPrueba));
		
		listaPrueba.clear();
		listaPrueba2.clear();
		
		a = listaPrueba.add(prueba);
//		System.out.println(a);
		a = listaPrueba.add(prueba2);
//		System.out.println(a);
		a = listaPrueba.add(prueba);
//		System.out.println(a);
		
		a = listaPrueba2.add(prueba);
//		System.out.println(a);
		a = listaPrueba2.add(prueba2);
//		System.out.println(a);
		a = listaPrueba2.add(prueba1);
//		System.out.println(a);
		
		System.out.println("Equals");
		a = listaPrueba2.equals(listaPrueba);
		System.out.println(a);
		
		System.out.println("\nImprimir");
		Object[] array = (Object[]) listaPrueba2.toArray();
		for(int i=0; i<array.length;i++){
			System.out.println(array[i].toString());
		}

		Matriz matrix = new Matriz();
		
		matrix.print();
		matrix.addFila();
		matrix.addColumna();
		matrix.print();
		matrix.addFila();
		matrix.addColumna();
		matrix.addColumna();
		matrix.addColumna();
		matrix.addColumna();
		for(int i=0;i<3;i++){
			matrix.add(i, i);
		}
		matrix.add(2, 0);
		matrix.add(0, 2);		
		for(int i=3;i<6;i++){
			for(int j=0;j<3;j++){
				matrix.add(j,i);
			}
		}
		matrix.remove(0, 5);
		matrix.remove(2 ,5);
		matrix.remove(1, 4);
		matrix.print();
	}

}
