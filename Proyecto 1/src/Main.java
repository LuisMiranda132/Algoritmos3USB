
public class Main {

	public static void main(String[] args) {
		
		Arco prueba = new Arco("pedro","juan");

		Arco prueba1 = new Arco("pedro","pepe");
		Arco prueba2 = new Arco("nipedro","nipepe");
//		Arco prueba1 = (Arco)prueba.clone();

		System.out.println(prueba.getDst()+"\n"+prueba1.getSrc());
		
		System.out.println(prueba.equals(prueba1));
		
		Lista<Arco> listaPrueba = new MiLista<Arco>();
		Lista<Arco> listaPrueba2 = new MiLista<Arco>();
		boolean a=true;
		
		a = listaPrueba.add(prueba);
		System.out.println(a);
		a = listaPrueba.add(prueba1);
		System.out.println(a);
		
		a = listaPrueba.contains(prueba2);
		System.out.println(a);
		
		a = listaPrueba.isEmpty();
		System.out.println(a);
		
		a = listaPrueba2.isEmpty();
		System.out.println(a);
		
		a = listaPrueba.remove(prueba);
		System.out.println(a);
		
		a = listaPrueba.contains(prueba);
		System.out.println(a);
		
		int tam;
		
		tam = listaPrueba.getSize();
		System.out.println(tam);
		
		listaPrueba.clear();
		
		tam = listaPrueba.getSize();
		System.out.println(tam);
		
		a = listaPrueba.add(prueba);
		System.out.println(a);
		a = listaPrueba.add(prueba1);
		System.out.println(a);
		
		a = listaPrueba2.add(prueba);
		System.out.println(a);
		a = listaPrueba2.add(prueba1);
		System.out.println(a);
		
		a = listaPrueba.equals(listaPrueba2);
		System.out.println(a);
		
		listaPrueba.clear();
		listaPrueba2.clear();
		
		a = listaPrueba.add(prueba);
		System.out.println(a);
		a = listaPrueba.add(prueba2);
		System.out.println(a);
		
		a = listaPrueba2.add(prueba);
		System.out.println(a);
		a = listaPrueba2.add(prueba1);
		System.out.println(a);
		
		a = listaPrueba2.equals(listaPrueba);
		System.out.println(a);
		
	}

}