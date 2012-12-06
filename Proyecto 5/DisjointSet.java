
public class DisjointSet {
	
	private int[] repres;
	private int[] rango;
	private int conexas;
	
	public DisjointSet(int tam){
		this.repres = new int [tam+1];
		this.rango = new int [tam+1];

		for(int i=0;i<tam+1;i++){
			this.repres[i] = i;
		}
		
		this.conexas = tam;
	}
	
	public int[] getRepres(){
		return this.repres;
	}
	
	public int[] getRango(){
		return this.rango;
	}
	
	public int getConexas(){
		return this.conexas;
	}
	
	public int find(int x){
		if(x==this.repres[x]){
			return x;
		}else{
			this.repres[x] = find(this.repres[x]);
			return this.repres[x];
		}
	}

	public void union(int x, int y){
		int px = find(x);
		int py = find(y);
		
		if(px == py){
			return;
		}else{		
			this.conexas--; 
			if(rango[px]>rango[py]){
				repres[py] = px;
			}else if(rango[py]>rango[px]){
				repres[px] = py;
			}else{
				repres[py] = px;
				rango[px]++;
			}
		}
	}
}
