/**
 * Clase que almacena la informacion de un nodo del grafo.
 */

public class Nodo {

    private String x = null;
    private String y = null;

    public Nodo(String i,String j){
	this.x = new String(i);
	this.y = new String(j);
    }

    /**
     * Retorna una nuevo nodo que es copia de this.
     */
    @Override
    protected Object clone() {
	return new Nodo(x,y);
    }

    /**
     * Indica si la nodo de entrada es igual a this.
     */
    public boolean equals(Object o) {
        return (o instanceof Nodo) && (x.equalsIgnoreCase(((Nodo) o).x)) 
        		&& (y.equalsIgnoreCase(((Nodo) o).y));
    }

    /**
     * Retorna la representacion en String de la nodo.
     */
    @Override
    public String toString() {
    	String retorno;
    	retorno = x + y;
	return new String(retorno);
    }

}