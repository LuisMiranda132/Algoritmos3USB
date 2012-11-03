/**
 * 
 * Clase que implementa la estructura de datos Cola
 * 
 * @author Gabriela Limonta, Luis Miranda
 *
 */
public class Cola {
	
	protected int MAX;
	protected int ini;
	protected int fini;
	protected boolean vacia;
	protected Object[] elementos;
	
	/**
	 * Constructor
	 * Descripcion: crea una nueva cola vacia de tamaño m 
	 * Parametros: m, tamaño maximo de la cola
	 * Precondicion: m>0
	 * Postcondicion: this.MAX = m /\ this.ini = this.fini = 0
	 * /\ this.vacia = true
	 */
	public Cola(int m) {
		
		this.MAX = m;
		this.ini = 0;
		this.fini = 0;
		this.vacia = true;
		this.elementos = new Object[this.MAX];
		
	}
	
	/**
	 * Funcion: encolar
	 * Descripcion: agrega un nuevo elemento a nuestra cola, 
	 * retorna true si lo encola y false en caso contrario.
	 * Parametros: elem, elemento a agregar.
	 * Precondicion: this.vacio \/ this.ini != this.fini
	 * Postcondicion: this.fini = (this.fini + 1) mod this.MAX /\
	 * this.elementos = this.elementos(this.fini:elem) /\ 
	 * this.vacia = false /\ this.ini = this.ini /\ 
	 * ((this.ini == this.fini) => !encole)
	 */
	public boolean encolar(Object elem) {
		boolean encole = false;
		if ((this.ini == this.fini) && !(this.vacia))  {
			return encole;
		}
		this.elementos[this.fini] = elem;
		this.fini = (this.fini + 1) % this.MAX;
		this.vacia = false;
		encole = true;
		return encole;
	}
	
	/**
	 * Funcion: desencolar
	 * Descripcion: desencola el primer elemento de la cola
	 * retorna true si lo hace correctamente y false en caso 
	 * contrario.
	 * Parametros: N/A
	 * Precondicion: this.vacia = false
	 * Postcondicion: this.elementos = this.elementos /\ 
	 * this.ini = (this.ini + 1) mod this.MAX /\ 
	 * this.fini = this.fini /\ ((this.ini = this.fini) => this.vacio)
	 * /\ (this.vacia => !desencole)
	 */
	public boolean desencolar() {
		boolean desencole = false;
		if (this.vacia) {
			return desencole;
		}
		this.ini = (this.ini + 1) % this.MAX;
		if (this.ini == this.fini) {
			this.vacia = true;
		}
		desencole = true;
		return desencole;
	}
	
	/**
	 * Funcion: primero
	 * Descripcion: retorna el primer elemento de la cola
	 * en caso de ser vacia retorna null.
	 * Parametros: N/A
	 * Precondicion: !this.vacia
	 * Postcondicion: r = this.elementos[this.ini]
	 */
	public Object primero() {
		if (this.vacia) {
			return null;
		}
		Object r = this.elementos[this.ini];
		return r;
	}
	
	/**
	 * Funcion: esVacia
	 * Descripcion: retorna true si la cola es vacia y 
	 * false en caso contrario
	 * Parametros: N/A
	 * Precondicion: true
	 * Postcondicion: vac == this.vacia
	 * @return
	 */
	public boolean esVacia() {
		boolean vac = this.vacia;
		return vac;
	}

}
