
/**
 * 
 * @author Gabriela Limonta, Luis Miranda
 * 
 * Modulo de prueba de la nueva funcion 
 * equals y los constructores de digraph
 */
public class Main {

	public static void main(String[] args) {
		Digraph grafL1 = new DigraphLista();
		Digraph grafM1 = new DigraphMatriz();
		
		System.out.println(grafL1.add(new Nodo("A")));
		System.out.println(grafL1.add(new Nodo("B")));
		System.out.println(grafL1.add(new Nodo("C")));
		System.out.println(grafL1.add(new Nodo("D")));
		System.out.println(grafL1.add(new Nodo("E")));
		
		System.out.println(grafM1.add(new Nodo("F")));
		System.out.println(grafM1.add(new Nodo("G")));
		System.out.println(grafM1.add(new Nodo("H")));
		System.out.println(grafM1.add(new Nodo("I")));
		System.out.println(grafM1.add(new Nodo("J")));
		
		System.out.println(grafL1.add(new Arco("A","B")));
		System.out.println(grafL1.add(new Arco("B","C")));
		System.out.println(grafL1.add(new Arco("C","D")));
		System.out.println(grafL1.add(new Arco("D","E")));
		System.out.println(grafL1.add(new Arco("E","A")));
		
		System.out.println(grafM1.add(new Arco("F","G")));
		System.out.println(grafM1.add(new Arco("G","H")));
		System.out.println(grafM1.add(new Arco("H","I")));
		System.out.println(grafM1.add(new Arco("I","J")));
		System.out.println(grafM1.add(new Arco("J","F")));
		
		System.out.println("Veremos si los grafos son iguales (no):");
		System.out.println(grafM1.equals(grafL1));
		
		Digraph grafL2 = new DigraphLista(grafL1);
		Digraph grafL3 = new DigraphLista(grafM1);
		Digraph grafM2 = new DigraphMatriz(grafM1);
		Digraph grafM3 = new DigraphMatriz(grafL1);
		
		System.out.println(grafL2.toString());
		grafL2.getNodos().imprimirLista();
		grafL2.getArcos().imprimirLista();
		
		System.out.println(grafL3.toString());
		grafL3.getNodos().imprimirLista();
		grafL3.getArcos().imprimirLista();
		
		System.out.println(grafM2.toString());
		grafM2.getNodos().imprimirLista();
		grafM2.getArcos().imprimirLista();
		
		System.out.println(grafM3.toString());
		grafM3.getNodos().imprimirLista();
		grafM3.getArcos().imprimirLista();
		

	}

}
