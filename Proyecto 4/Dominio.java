
public class Dominio implements Comparable<Dominio> {

//	private Lista<String> contenido;
//	private Lista<String> funciones;
	private DynamicArray contenido;
	private Lista<String> contArev;
	private DynamicArray funcionesRecorridas;
	private int costo;
	
	public Dominio(){
		this.contenido = new DynamicArray();
		this.contArev = new MiLista<String>();
		this.funcionesRecorridas = new DynamicArray();
		
		this.costo = 0;
		this.contenido.crecer(2);
		this.funcionesRecorridas.crecer(2);

	}
	
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
	public Dominio(Lista<String> cont, Lista<String> func, int c) {
		this.contenido = cont;
		this.funcionesAbiertas = funcA;
		this.funcionesRecorridas = funcR;
		this.costo = c;
	}
	
	@SuppressWarnings("unchecked")
	public Dominio(MiLista<String> cont, MiLista<String> funcA,
			MiLista<String> funcR, int c) {
		this.contenido = (Lista<String>) cont.clone();
		this.funcionesAbiertas = (Lista<String>) funcA.clone();
		this.funcionesRecorridas = (Lista<String>) funcR.clone();
		this.costo = c;
	}
*/
	
/*<<<<<<< Updated upstream
	public Dominio(String cont, String funcA, String funcR, int c) {
		this.contenido.add(cont);
		this.funcionesAbiertas.add(funcA);
		this.funcionesRecorridas.add(funcR);
		this.costo = c;
	}
=======
*/
		
	public int compareTo(Dominio d) {
		if(this.costo > d.costo){
			return 1;
		}else if (this.costo < d.costo){
			return -1;
		}
		return 0;
	}
	
	public void agregarCont(String cont) {
		this.contenido.addOrd(cont);
	}
	
	public void agregarAcontArev(String nod) {
		this.contArev.add(nod);
	}
	
	public void eliminardeContArev(String nod) {
		this.contArev.remove(nod);
	}
	
	public void agregarFuncionRec(String funcR) {
		this.funcionesRecorridas.addOrd(funcR);

	}
	
	public DynamicArray getCont() {
		return  this.contenido;
	}
	
	public Lista<String> getContArev() {
		return this.contArev;
	}
	
	public DynamicArray getFuncionesRec() {
		return this.funcionesRecorridas;

	}
	
	public int getCosto() {
		return this.costo;
	}
	
	public void setCosto(int c) {
		this.costo = c;
	}
	
	public String toString() {
		return this.costo+" ";
	}
	
}