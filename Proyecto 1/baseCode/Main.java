
public class Main {

	public static void main(String[] args) {
		
		Arco prueba = new Arco("pedro","juan");

		Arco prueba1 = new Arco("pedro","pepe");
//		Arco prueba1 = (Arco)prueba.clone();

		System.out.println(prueba.getDst()+"\n"+prueba1.getSrc());
		
		System.out.println(prueba.equals(prueba1));
	}

}
