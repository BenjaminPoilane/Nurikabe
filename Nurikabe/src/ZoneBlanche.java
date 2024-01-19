
public class ZoneBlanche {
	private int taille;
	private int pointeur;
	private int[][] cases;
	
	public ZoneBlanche(int dim){
		pointeur=0;
		cases=new int[dim][2];
	}
	public void ajouterCase(int x,int y){
		cases[pointeur][0]=x;
		cases[pointeur][1]=y;
		pointeur ++;
	}
	public void definirTaille(int t){
		taille=t;
	}
	public int[] renvoitCase(int x){
	return(cases[x]);	
	}
	public int renvoitTaille(){
		return taille;
	}
	public int[] renvoitAleatoireCase(){
		int num = (int) ((taille)*Math.random());
		
		return(cases[num]);
	}
}
