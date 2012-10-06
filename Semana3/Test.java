
public class Test {

    public static void main(String args[]) {

	if (args.length == 0) {
	    System.out.printf("Sintaxis: Main <numNodos> \n");

	    return ;
	}
				 
	Digraph d = new DigraphHash();

	Nodo ns[ ] = new Nodo[Integer.parseInt(args[0])];
	Arco as[ ] = new Arco[Integer.parseInt(args[0])];

	for (int i = 0; i < ns.length; i++) {
	    ns[i] = new Nodo("Nodo"+i);
	    d.add(ns[i]);
	}

	for (int i = 0; i < as.length-1; i++) {
	    as[i] = new Arco(ns[i].toString(), ns[i+1].toString());
	    d.add(as[i]);
	}



	System.out.println("Grafo es: \n" + d);

	for (int i = 0; i < ns.length; i++) {
	    ns[i] = new Nodo("Nodo"+i);
	    d.add(ns[i]);
	}

	for (int i = 0; i < as.length-1; i++) {
	    //	    as[i] = new Arco(ns[i].toString(), ns[i+1].toString());
	    d.add(as[i]);
	}

	System.out.println("Grafo es: \n" + d);

	for (int i = 0; i < ns.length; i++) {
	    String lab = ns[i].toString();
	    System.out.println("Esta: " + d.contains(lab) + " " + d.get(lab));
	}

	for (int i = 0; i < as.length-1; i++) {
	    String lab1 = as[i].getSrc();
	    String lab2 = as[i].getDst();

	    System.out.println("Esta: " + d.contains(lab1, lab2) + " " + d.get(lab1, lab2));
	}

	System.out.println("Nodos:");
	for (Object o : d.getNodos().toArray()) {
	    System.out.println(o);
	}

	System.out.println("Arcos:");
	for (Object o : d.getArcos().toArray()) {
	    System.out.println(o);
	}

	System.out.println("Nodos In:");
	for (Nodo n: ns) {
	    System.out.println("\tNodo:" + n.toString());
	    for (Object o : d.getInArcos(n.toString()).toArray()) {
		System.out.println("\t\t"+o);
	    }
	}
	System.out.println("Nodos Out:");
	for (Nodo n: ns) {
	    System.out.println("\tNodo:" + n.toString());
	    for (Object o : d.getOutArcos(n.toString()).toArray()) {
		System.out.println("\t\t"+o);
	    }
	}

	System.out.println("Grafo es: \n" + d);
	d.remove(ns[2].toString());
	System.out.println("Elimine "+ns[2].toString()+" Grafo es: \n" + d);

	System.out.println("Grafo es: \n" + d);

	d.remove(as[4].getSrc(), as[4].getDst());
	System.out.println("Elimine " + as[4] +" Grafo es: \n" + d);
    }
}
