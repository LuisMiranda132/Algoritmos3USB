
public class Dominio implements Comparable<Dominio> {

	private DynamicArray contenido; //dominio
	private Lista<String> contArev; //nodos a expandir
	private DynamicArray funcionesRecorridas; //funciones ya recorridas
	private int costo; //costo actual
	
	
	/*
	 * constructor de Dominio
	 */
	public Dominio(){
		this.contenido = new DynamicArray();
		this.contArev = new MiLista<String>();
		this.funcionesRecorridas = new DynamicArray();
		
		this.costo = 0;
		this.contenido.crecer(2);
		this.funcionesRecorridas.crecer(2);

	}
	
	/*
	 * constructor de Dominio
	 */
	@SuppressWarnings("unchecked")
	public Dominio(DynamicArray cont, MiLista<String> domR, DynamicArray funcR, int c){
		this.contenido = new DynamicArray();
		this.contArev = (Lista<String>) domR.clone();
		this.funcionesRecorridas = new DynamicArray();
		
		this.contenido.crecer(2);
		this.funcionesRecorridas.crecer(2);

		for(int i = 0;i<cont.getPosicion();i++){
			this.contenido.addOrd(cont.get(i));
		}
		for(int i = 0;i<funcR.getPosicion();i++){
			this.funcionesRecorridas.addOrd(funcR.get(i));
		}
		
		this.costo = c;

	}
	
	/*
	 * constructor de dominio
	 */
	@SuppressWarnings("unchecked")
	public Dominio(Object[] cont, MiLista<String> contRev, Object[] funcR, int c){
		this.contenido = new DynamicArray();
		this.funcionesRecorridas = new DynamicArray();
		
		this.contenido.crecer(2);
		this.contArev = (Lista<String>) contRev.clone();
		this.funcionesRecorridas.crecer(2);
		
		for(Object o: cont){
			if(o!=null)this.contenido.addOrd(o);
		}
		for(Object o: funcR){
			if(o!=null)this.funcionesRecorridas.addOrd(o);
		}
		
		this.costo = c;

	}
	
	/*
	 * compara dos elementos del tipo dominio y si el costo del primero es mayor
	 * devuelve 1, cero si son iguales y -1 si es menor
	 */
	public int compareTo(Dominio d) {
		if(this.costo > d.costo){
			return 1;
		}else if (this.costo < d.costo){
			return -1;
		}
		return 0;
	}
	
	/*
	 * agrega un elemento al dominio
	 */
	public void agregarCont(String cont) {
		this.contenido.addOrd(cont);
	}
	
	/*
	 * agrega un elemento al dominio a expandir
	 */
	public void agregarAcontArev(String nod) {
		this.contArev.add(nod);
	}
	
	/*
	 * elimina un elemento del dominio que expandimos
	 */
	public void eliminardeContArev(String nod) {
		this.contArev.remove(nod);
	}
	
	/*
	 * agrega una funcion a las funciones recorridas
	 */
	public void agregarFuncionRec(String funcR) {
		this.funcionesRecorridas.addOrd(funcR);

	}
	
	/*
	 * devuelve el dominio
	 */
	public DynamicArray getCont() {
		return  this.contenido;
	}
	
	/*
	 * devuelve el dominio a expandir
	 */
	public Lista<String> getContArev() {
		return this.contArev;
	}
	
	/*
	 * devuelve las funciones recorridas
	 */
	public DynamicArray getFuncionesRec() {
		return this.funcionesRecorridas;

	}
	
	/*
	 * devuelve el costo del dominio
	 */
	public int getCosto() {
		return this.costo;
	}
	
	/*
	 * asigna el dominio al costo
	 */
	public void setCosto(int c) {
		this.costo = c;
	}
	
	
	/*
	 * convierte a string
	 */
	public String toString() {
		return this.costo+" ";
	}
	
}