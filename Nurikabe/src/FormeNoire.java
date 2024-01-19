 

	
	
	public class FormeNoire {
		public int dim;
		public int[][] grille;
		public int[][] grilleInitiale;
		private int taille;
		private ZoneBlanche[] listeZoneBlanche;
		
		
		public int calculerTaille(int[][] grill){
			int dime=grill.length;
			int somme=0;
			for(int i = 0; i<dime;i++){
				for(int j = 0; j<dime;j++){
					somme=somme+grill[i][j];}
			}
			return(somme);
		}

		
		
		public FormeNoire(int n,boolean plusGrandQue5){
			
			this.dim=n;
			if (plusGrandQue5==false){
			grilleInitiale=grilleConstante(dim,-1);
			int k=0;
			
			while(grilleInitiale[0][0]==-1){
				k++;
				
				this.genererFormeNoire();
				grilleInitiale=this.genereGrilleInitialeASolutionUnique(grille);
			}
			
			this.taille=calculerTaille(this.grille);
			this.getListZoneBlanche();
			}
			else{
				this.genererFormeNoire();
				this.taille=calculerTaille(this.grille);
				this.getListZoneBlanche();
				this.genererGrilleInitiale();
				
				
				
			}
		}
		
		public FormeNoire(int[][] tab){
			this.dim=tab.length;
			this.grille=tab;
			this.taille=calculerTaille(this.grille);
			this.getListZoneBlanche();
			this.genererGrilleInitiale();
		}
		
		public int[][] renvoitGrille(){return(this.grille);}
		public int[][] renvoitGrilleInitiale(){return(this.grilleInitiale);}
		public ZoneBlanche[] renvoitListeZoneBlanche(){return (this.listeZoneBlanche);}
		public int renvoitTaille(){return this.taille;}
		public int renvoitDim(){return dim;}
		
		
		public boolean estCorrecte(){
			
			if (dim==1){return(true);}
			else{
				for (int i=0;i<dim-1;i++){
					for (int j=0;j<dim-1;j++){
						if(this.grille[i][j]+this.grille[i+1][j]+this.grille[i][j+1]+this.grille[i+1][j+1]>=4){return(false);}
					}	
				}
			return(this.estContinue(this.grille));
			}
			
		
		}
		
		public boolean estCorrecte1(int[][] grille){
			
			if (dim==1){return(true);}
			else{
				for (int i=0;i<dim-1;i++){
					for (int j=0;j<dim-1;j++){
						if(grille[i][j]+grille[i+1][j]+grille[i][j+1]+grille[i+1][j+1]>=4){return(false);}
					}	
				}
			return(this.estContinue(grille));
			}
			
		
		}
	
		public int NombreZoneBlanche(){
			return (listeZoneBlanche.length);
			
		}
		
		
		public static void afficher(int[][] grill){
			System.out.println("  ");
			int dime=grill.length;
			String chaine=" ";
			for (int i=0;i<dime;i++){
				chaine = " ";
				for (int j=0;j<dime;j++){
					chaine = chaine + grill[i][j] +" ";
					
					
				}
				System.out.println( chaine);
				
			}
		}
		
		public static int[][] grilleEntouree(int[][] grille0){ //renvoit la mme grille entouree de 0
			int dime=grille0.length;
			int[][] nouvelleGrille= new int[dime+2][dime+2];
			
			for (int n=0 ; n<dime+2 ; n++){
				nouvelleGrille[0][n]=0;
				nouvelleGrille[n][0]=0;
				nouvelleGrille[dime+1][n]=0;
				nouvelleGrille[n][ dime+1 ]=0;
				
			}
			for (int k=1 ; k<dime+1 ; k++){
				for (int l=1 ; l<dime+1 ; l++){
					nouvelleGrille[k][l]=grille0[k-1][l-1];}}
			return(nouvelleGrille);
		}
		
		public static int[][] calculerGrilleVoisins(int[][] grill){ //renvoit la grille (mme taille que grille) ou les cases noires  
			int dime=grill.length;
			int[][] grille1 = new int[dime][dime];			 //sont remplacees par le nombre de leurs voisins noirs
			int[][] nouvelleGrille=grilleEntouree(grill);
			
			for (int n=1 ; n<dime+1 ; n++){
				for (int m=1 ; m<dime+1 ; m++){
					grille1[n-1][m-1]=nouvelleGrille[n][m]*(nouvelleGrille[n][m-1]+nouvelleGrille[n-1][m]+nouvelleGrille[n][m+1]+nouvelleGrille[n+1][m]);
					
				}	
				
			}		
			return (grille1);
		}
		
		public static int[] direction(int angle){  //renvoit la direction correspondant a l'angle angle*Pi/2 et avec en prenant en 
												   // compte que les lignes sont numerotees a l'envers
			int[] dir=new int[2];
			while (angle<0){
				angle=angle+4;
			}
			if (angle%4==0){
				dir[0]=0;
				dir[1]=1;
			}

			if (angle%4==1){
				dir[0]=-1;
				dir[1]=0;
			}

			if (angle%4==2){
				dir[0]=0;
				dir[1]=-1;
			}
			if (angle%4==3){
				dir[0]=1;
				dir[1]=0;
			}
			return(dir);
		}
		
		public static int directionInverse(int[] dir){ // rend l'angle d'une direction (inverse de direction)
			int alpha=0;
			
			if (dir[0]==-1 && dir[1]==0){
				alpha=1;}
			if (dir[0]==0 && dir[1]==-1){
				alpha=2;}
			if (dir[0]==1 && dir[1]==0){
				alpha=3;}
			
			return alpha;
		}
		
		public int[][] grilleConstante(int taille,int k){// renvoit une matrice carrae constante de taille taille et de valeure k 
			int[][] grilleZero=new int[taille][taille];
			for (int i=0;i<taille;i++){
				for (int j=0;j<taille;j++){
					grilleZero[i][j]=k;
				}
			}
		
			return grilleZero ;
		}
		
		public static int[] plus(int[] tab1, int[] tab2, int eps1, int eps2){ // renvoit la somme termes a termes de eps1*tab1 et eps2*tab2, de tailles 2
			int[] tab=new int[2];
			tab[0]=eps1*tab1[0]+eps2*tab2[0];
			tab[1]=eps1*tab1[1]+eps2*tab2[1];
			return(tab);
		}
		
		public boolean sontRelies (int[][] grill, int i1, int j1,int i2,int j2 ){ // renvoit true si (i1,j1)
																						
			//et (i2,j2) sont reliŽs par un chemin de 1 dans grille
			int dime=grill.length;
			int[] curseur = {i1+1,j1+1};
			int[] curseur1 = {0,0};
			int[][] grilleCompt0=grilleEntouree(calculerGrilleVoisins(grill));
			int[][] grilleCompt1=grilleConstante(dime+2,0);
			int angle= 1;
			int angleMin=10;
			int min=0;
			
			while(min!=10) {
				
				grilleCompt1[curseur[0]][curseur[1]]++;
				grilleCompt0[curseur[0]][curseur[1]]--;
				
				min=10;
				
				
				if (grilleCompt0[plus(curseur,direction(angle+3),1,1)[0]][plus(curseur,direction(angle+3),1,1)[1]]!=0) {
					min = grilleCompt1[curseur[0]][curseur[1]+1];
					angleMin=0;
				}
				if (grilleCompt0[plus(curseur,direction(angle),1,1)[0]][plus(curseur,direction(angle),1,1)[1]]!=0 && grilleCompt1[plus(curseur,direction(angle),1,1)[0]][plus(curseur,direction(angle),1,1)[1]]<min) {
					min=grilleCompt1[curseur[0]][curseur[1]+1];
					angleMin=1;
				}
				if (grilleCompt0[plus(curseur,direction(angle+1),1,1)[0]][plus(curseur,direction(angle+1),1,1)[1]]!=0 && grilleCompt1[plus(curseur,direction(angle+1),1,1)[0]][plus(curseur,direction(angle+1),1,1)[1]]<min) {
					min=grilleCompt1[curseur[0]][curseur[1]+1];
					angleMin=2;
				}
				if (grilleCompt0[plus(curseur,direction(angle+2),1,1)[0]][plus(curseur,direction(angle+2),1,1)[1]]!=0 && grilleCompt1[plus(curseur,direction(angle+2),1,1)[0]][plus(curseur,direction(angle+2),1,1)[1]]<min) {
					min=grilleCompt1[curseur[0]][curseur[1]+1];
					angleMin=3;
				}
				angle=angle+angleMin;
				
				if(curseur[0]==i2+1 && curseur[1]==j2+1){
					return(true);
				}
				
				curseur1 = plus(curseur,direction(angle+3),1,1);
				angle=directionInverse(plus(curseur1,curseur,1,-1));
				curseur=curseur1;
				
			}
			
			                            
			
		return(false);	
		}

		public boolean estContinue (int[][] g){
			int dim=g.length;
			int compteur=0;
			int c[]= {0,0};
			int k=0;
			int l=0;
			int e=0;
			int angle=0;
			int indicateur=0;
			int pos[]=new int[2];
			int gCompt[][]=grilleConstante(dim,0);
			int gVois[][]= calculerGrilleVoisins(g);
			int min=5;
			int angleMin=0;
			int t= calculerTaille(g);
			if (t<2){return true;}
			while(k+l<2*dim-1 && g[k][l]!=1){
				e=0;
				if (l<dim-1){l++;e=1;}
				if (l==dim-1 && e==0){k++;l=0;}
			}
			if(k+l== 2*dim-1){return (true);}
			c[0]=k;
			c[1]=l;
			
			while (true){
				if (gCompt[c[0]][c[1]]==0){compteur ++; if (compteur==t){return true;}}
				gCompt[c[0]][c[1]]++;
				min=5;
				
				for (int i=-1;i<3;i++){
					pos[0]=c[0] + direction(angle+i)[0];
					pos[1]=c[1] + direction(angle+i)[1];
					
					
					if(pos[0]>=0 && pos[1]>=0 && pos[0]<dim && pos[1]<dim && g[pos[0]][pos[1]]==1 && gCompt[pos[0]][pos[1]]<gVois[pos[0]][pos[1]]){
						
						if (gCompt[pos[0]][pos[1]] < min){min=gCompt[pos[0]][pos[1]]; angleMin= angle+i;}	}
					
				}
				
					if (min==5){return false;}
					angle= angle + angleMin;
					c[0]=c[0] + direction(angleMin)[0];
					c[1]=c[1] + direction(angleMin)[1];
				
			}
				
		}
		
		public static int tirageAleatoire(int borneInf,int borneSup){	
			return (int) ((borneSup-borneInf + 1)*Math.random()) + borneInf;
			}
			
		public void genererFormeNoire(){			//return(formeAleatoire);
			int interupteur=0;
			while (interupteur==0){
			int taille=this.dim;
			int[][] formeAleatoire = grilleConstante(taille,1);
			
			for(int j=0;j<taille; j++ ){						//on traite a part la premire ligne (pas de tests)
				formeAleatoire[0][j]=tirageAleatoire(0,1);
			}
			for (int i=1;i<taille-1;i++){
				formeAleatoire[i][0]=tirageAleatoire(0,1);
				if (formeAleatoire[i][0]==0 && formeAleatoire[i-1][0]==1){
					//System.out.println("test relies"+i+"  "+j+ " "+ "case d'au dessus="+ " "+ formeAleatoire[i-1][0]);
					//afficher(formeAleatoire);
					//System.out.println(sontRelies(formeAleatoire,i-1,0,i+1,0));
					
					if(sontRelies(formeAleatoire,i-1,0,i+1,0)==false){
						//System.out.println("non relies"+ i + "  "+ 0);
						formeAleatoire[i][0]=1;}
				}
				for(int j=1;j<taille; j++ ){
					formeAleatoire[i][j]=tirageAleatoire(0,1);
					if (formeAleatoire[i][j]==0 && formeAleatoire[i-1][j]==1){
						//System.out.println("test relies"+i+"  "+j+ " "+ "case d'au dessus="+ " "+ formeAleatoire[i-1][j]);
						//afficher(formeAleatoire);
						//System.out.println(sontRelies(formeAleatoire,i-1,j,i+1,j));
						
						if(sontRelies(formeAleatoire,i-1,j,i+1,j)==false){
							//System.out.println("non relies"+ i + "  "+ j);
							formeAleatoire[i][j]=1;}
					}	
					if (formeAleatoire[i][j]==1){
						if (formeAleatoire[i][j-1]+formeAleatoire[i-1][j]+formeAleatoire[i-1][j-1]>2){formeAleatoire[i][j]=0;}
					}	
						
						
				}			
			}
			int i=taille-1;
			
			for(int t=0;t<taille;t++){
				formeAleatoire[i][t]=0;
				
			}
			
			boolean indicateur=estContinue(formeAleatoire);
			
					//on remplit la dernière ligne de 0
			
			for(int f=0;f<taille;f++){
				formeAleatoire[taille-1][f]=0;}
			
			formeAleatoire[i][0]=tirageAleatoire(0,1);
			
			if (formeAleatoire[i][0]==0 && formeAleatoire[i-1][0]==1){
				
				
				if(sontRelies(formeAleatoire,i-1,0,i+1,0)==false){
					formeAleatoire[i][0]=1;}
			}
			if (formeAleatoire[i-1][1]+formeAleatoire[i-1][0]+formeAleatoire[i][1]>2){formeAleatoire[i-1][0]=0;}
			for(int k=1;k<taille-1;k++){
				
				if(indicateur){
					
					formeAleatoire[i][k]=tirageAleatoire(0,1);
					if (formeAleatoire[i][k]==1){
						if (formeAleatoire[i][k-1]+formeAleatoire[i-1][k]+formeAleatoire[i-1][k-1]>2){formeAleatoire[i][k]=0;}
						if(estContinue(formeAleatoire)==false){formeAleatoire[i][k]=0;}
					}
				}
	
				if(indicateur==false){
					formeAleatoire[i][k]=tirageAleatoire(0,1);
					if (formeAleatoire[i][k]==1){
						if (formeAleatoire[i][k-1]+formeAleatoire[i-1][k]+formeAleatoire[i-1][k-1]>2){formeAleatoire[i][k]=0;}
					}
						if (formeAleatoire[i][k]==0){
							if(formeAleatoire[i][k-1]==1){
								if(sontRelies(formeAleatoire,i,k-1,i,k+1)==false){formeAleatoire[i][k]=1;}
							}
							if(formeAleatoire[i-1][k]==1){
								if(sontRelies(formeAleatoire,i-1,k,i,k+1)==false){formeAleatoire[i][k]=1;}
								}
							}
						}
					}
				formeAleatoire[i][taille-1]=tirageAleatoire(0,1);
					if (formeAleatoire[i][taille-1]==1){
						if (estContinue(formeAleatoire)==false){formeAleatoire[i][taille-1]=0;}
					}
					if (formeAleatoire[i][taille-1]==0){
						if(estContinue(formeAleatoire)==false){formeAleatoire[i][taille-1]=1;}
					}
					
					for (int q=0;q<taille-1; q++){
						if (formeAleatoire[taille-2][q]+formeAleatoire[taille-2][q+1]+formeAleatoire[taille-1][q]+formeAleatoire[taille-1][q+1]>3){
							formeAleatoire[taille-2][q]=0;
							if (estContinue(formeAleatoire)==false){
								formeAleatoire[taille-2][q]=1;
								formeAleatoire[taille-2][q+1]=0;
							}
						}
					
						}
				
					
					this.grille=formeAleatoire;
					if (this.estCorrecte()){
						interupteur=1;
					}}
			}
			
		public void getListZoneBlanche (){
			this.listeZoneBlanche=this.getListZoneBlanche1(this.grille);
			
		} 
		
		public ZoneBlanche[] getListZoneBlanche1 (int[][] grille){
			int dim=grille.length;
			ZoneBlanche[] z= new ZoneBlanche[dim*dim];
			ZoneBlanche[] z1;
			int[] c1 = {0,0};
			int[] c2 = {0,0};
			int [][] g = new int[dim][dim];
			
			for(int i=0;i<dim;i++){						// On inverse grille
				for(int j=0;j<dim;j++){
					if (grille[i][j]==1){g[i][j]=0;}
					if (grille[i][j]==0){g[i][j]=1;}
				}
			}
			int in=0;
			int[][] gVois= calculerGrilleVoisins(g);
			int[][] gCompt = grilleConstante(dim,0);
			int angle=0; int indic=0; int e=0; int angleMin=0; int min; int[] pos={0,0}; int t=0;int k=-1;int indic2=0;
			
			while( c1[1]<dim && c1[0]<dim){
				c1[1]=-1; c1[0]=0;
				in=0;
				while(c1[1]<dim && c1[0]<dim && in==0){
					e=0;
					
					if (c1[1]<dim-1){
						c1[1]++; e ++;
					}
					if (c1[1]>=dim-1 && e==0){
						c1[1]=0;c1[0]++;
					}
					if(c1[1]<dim && c1[0]<dim){
						
					if(g[c1[0]][c1[1]]==1){in=1;}}
				}
		
				
				
				c2[0]=c1[0];
				c2[1]=c1[1];
				
				
				
				t=0;
				angle=0;
				indic=1;
				indic2=0;
				if(c1[0]<dim && c1[1]<dim){
				if (g[c1[0]][c1[1]]==1){indic=0;indic2=1;k++;z[k]=new ZoneBlanche(dim*dim);}
				}
			
				while(indic==0){
					if (gCompt[c2[0]][c2[1]]==0){
						z[k].ajouterCase(c2[0], c2[1]);t++;
						
					}
					gCompt[c2[0]][c2[1]]++;
					
					
					min=5;
					
					
					 for(int i=-1;i<3;i++){
						 
						 pos[0]=c2[0] + direction(angle + i)[0];
						 pos[1]=c2[1] + direction(angle + i)[1];
						 
						 if (pos[1]>=0 && pos[0]>=0 && pos[1]<dim && pos[0]<dim && g[pos[0]][pos[1]]==1 && gCompt[pos[0]][pos[1]]<gVois[pos[0]][pos[1]] && gCompt[pos[0]][pos[1]]<min){
							 min=gCompt[pos[0]][pos[1]];
							 angleMin=angle + i;
							 
						 }
					 }
					if (min==5) {indic=1;}
					angle = angleMin;
					c2[0]=c2[0]+direction(angle)[0];
					c2[1]=c2[1]+direction(angle)[1];
				}
				if (indic*indic2==1){
					z[k].definirTaille(t);
					
					for (int i=0;i<t;i++){
						g[z[k].renvoitCase(i)[0]][z[k].renvoitCase(i)[1]]=0;
					}
				}
				
				}
				z1 = new ZoneBlanche[k+1];
				for (int i = 0;i<k+1;i++){
					z1[i]=new ZoneBlanche(z[i].renvoitTaille());
					z1[i].definirTaille(z[i].renvoitTaille());
					for(int j=0;j<z[i].renvoitTaille();j++){
						z1[i].ajouterCase(z[i].renvoitCase(j)[0], z[i].renvoitCase(j)[1]);
					}
			}
			
			return(z1);
			
		}
		
		public void genererGrilleInitiale(){
			
			int[][] grilleInitial=grilleConstante(grille.length,0);
			
			int[] pos = {0,0};
			for(int i=0;i<listeZoneBlanche.length;i++){
				
				listeZoneBlanche[i].renvoitCase(listeZoneBlanche[i].renvoitTaille()-1);
				pos=listeZoneBlanche[i].renvoitAleatoireCase();
				grilleInitial[pos[0]][pos[1]]=listeZoneBlanche[i].renvoitTaille();
			}
		
			this.grilleInitiale=grilleInitial;
			
			
		}			
			
		public int[][] calcAppInv0(){
			int tab[][]=new int[dim*dim][2];
			int tab0[][]=calcApp0();
			for (int i=0;i<dim;i++){
				for (int j=0;j<dim;j++){
					tab[tab0[i][j]][0]=i;
					tab[tab0[i][j]][1]=j;
				}
			}
			return(tab);
		}
		
		public int[][] calcApp0(){
			int tab[][]=new int[dim][dim];
			
			for (int i=0;i<dim;i++){
				for (int j=0;j<dim;j++){
					if (i+j<dim){tab[i][j]=(((i+j+1)*(i+j))/2) + i;}
					else{tab[i][j]=dim*dim+(2*dim-1)*(i+j-dim)+dim-1-j-((i+j)*((i+j)-1))/2 ;}
					
				}
			}
			return(tab);
		}
		
		public int[] calcDist0() {
			int tab[]= new int[dim*dim];
			
			for (int k=0;k<(dim*(dim-1))/2;k++){
				tab[k]=calcAppInv0()[k][0] + calcAppInv0()[k][1] + 2;
				
			}
			for (int k=(dim*(dim-1))/2;k<dim*dim;k++){
				tab[k]= 2*dim - 1 - (calcAppInv0()[k][0] + calcAppInv0()[k][1]);
				
			}
			
			return tab;
		}
		
		public int [][] calcAppInv(int[][] g){

			int s=0;
			for (int i=0;i<dim;i++){  
				for (int j=0;j<dim;j++){
					if (g[i][j]>0){s++;}
				}
			}
			
			
			 int invf[][]=calcAppInv0();
			for (int i=0;i<dim*dim;i++){  
				
					if (g[invf[i][0]][invf[i][1]]>0){
						for (int k =i;k<dim*dim-1;k++){
							invf[k]=invf[k+1];
						}
						if (i<dim*dim-s && g[invf[i][0]][invf[i][1]]>0){i--;}
					}
				}
			
					
		int invf0[][]=new int[dim*dim-s][2];		
		for (int i=0;i<dim*dim-s;i++){
			invf0[i]=invf[i];
		}
		return invf0;
		}
	
		public boolean compare(ZoneBlanche[] z1, ZoneBlanche[] z2){
			
			if (z1.length==z2.length){
				int max=0;
				for(int i=0;i<z1.length;i++){
					if(z1[i].renvoitTaille()>max){max=z1[i].renvoitTaille();}
				}
				int tab1[]= new int[max+1];
				for(int i=0;i<max+1;i++){tab1[i]=0;}
				for(int i=0;i<z1.length;i++){tab1[z1[i].renvoitTaille()]++;}
				
				max=0;
				for(int i=0;i<z1.length;i++){
					if(z2[i].renvoitTaille()>max){max=z2[i].renvoitTaille();}
				}
				if (max+1!=tab1.length){return false;}
				
				int tab2[]=new int [max+1];
				for(int i=0;i<max+1;i++){tab2[i]=0;}
				for(int i=0;i<z2.length;i++){tab2[z2[i].renvoitTaille()]++;}
				for(int i=0;i<max+1;i++){
					if (tab1[i]!=tab2[i]) {return (false);}
				}
				return (true);
			}
			return (false);
		}
		
		public boolean aPlusDe2Solutions(int[][] gr,int[][] grillePsIn){
			int compteur=0;
			int t=calculerTaille(gr);
			int s=0;
			for (int i=0; i<dim;i++){
				for(int j=0;j<dim;j++){
					if(grillePsIn[i][j]>0){s++;}
				}
			}
			int invf[][]= calcAppInv(grillePsIn);
			int g[][] = grilleConstante(dim,0);
			int g1[]=new int[t];
			for(int i=0;i<t;i++){
				g1[i]=i;
				g[invf[g1[i]][0]][invf[g1[i]][1]]=1;
			}
			
			ZoneBlanche zb[]=getListZoneBlanche1(gr);			
			ZoneBlanche zb1[];
			int d[]=calcDist0();
			int e; int k;int indic;
			
			while(true){
				if (compteur>1){return true;}
				indic=0;
				e=0;
				k=t-1;
				
				String str ="";
				for(int i=0;i<t;i++){
					str=str+" "+ g1[i];	
				}//;
				
				if(estCorrecte1(g)){
					
					zb1=getListZoneBlanche1(g);
					if (compare(zb1,zb)){
						int e1=0;
						for (int i=0;i<zb1.length; i++ ){
							int n=-1;
							for (int j=0;j<zb1[i].renvoitTaille();j++){
								if (grillePsIn[zb1[i].renvoitCase(j)[0]][zb1[i].renvoitCase(j)[1]]>0){
									if(grillePsIn[zb1[i].renvoitCase(j)[0]][zb1[i].renvoitCase(j)[1]]!=zb1[i].renvoitTaille()){e1=1;}
									if(n!=appartientZoneBlanche(zb1[i].renvoitCase(j),zb) && n>=0){ e1=1;}
									n=appartientZoneBlanche(zb1[i].renvoitCase(j),zb);
								}
							}
						}
						if(e1==0){
						
						compteur++;}}
				}
				if(g1[k]<dim*dim-s-1 && g1[k]-g1[k-1]<d[g1[k-1]]+1 ){
					e=1;
					g[invf[g1[k]][0]][invf[g1[k]][1]]=0;
					g1[k]++;
					g[invf[g1[k]][0]][invf[g1[k]][1]]=1;
				}
				
				if((g1[k]<dim*dim-s-1-(t-k-1) && g1[k]-g1[k-1]<d[g1[k-1]]+1)==false && e==0){
					
					while(indic==0 &&( g1[k]<dim*dim-s-1-(t-k-1) && g1[k]-g1[k-1]<d[g1[k-1]])==false ){
						
							k--;
							if(k==0){indic=1;}
						}
					if(k==0){
						if(g1[k]>=dim*dim-s-1-(t-k-1)){
							if (compteur>1){return true;}
							else{return false;}
							}
					}
					
					g[invf[g1[k]][0]][invf[g1[k]][1]]=0;
					g1[k]++;
					g[invf[g1[k]][0]][invf[g1[k]][1]]=1;
					for(int i=1;i<t-k;i++){
						g[invf[g1[k+i]][0]][invf[g1[k+i]][1]]=0;
						g1[k+i]=g1[k]+i;
						g[invf[g1[k+i]][0]][invf[g1[k+i]][1]]=1;
					}	
				}
				}			
		}
		
		public ZoneBlanche[] tri(ZoneBlanche[] zb){
			ZoneBlanche z[]=zb;
			int indicateur=1;
			ZoneBlanche zone;
			while(indicateur==1){
				indicateur=0;
				for (int i=0;i<z.length;i++){
					if (z[i].renvoitTaille()>z[i+1].renvoitTaille()){
						zone=z[i+1];
						z[i+1]=z[i];
						z[i]=zone;
						indicateur=1;
					}
				}
			}
			return z;
		}
		
		
		public int nombreDeSolution(int[][] gr,int[][] grillePsIn){
			int compteur=0;
			int t=calculerTaille(gr);
			int s=0;
			for (int i=0; i<dim;i++){
				for(int j=0;j<dim;j++){
					if(grillePsIn[i][j]>0){s++;}
				}
			}
			int invf[][]= calcAppInv(grillePsIn);
			int g[][] = grilleConstante(dim,0);
			int g1[]=new int[t];
			for(int i=0;i<t;i++){
				g1[i]=i;
				g[invf[g1[i]][0]][invf[g1[i]][1]]=1;
			}
			
			ZoneBlanche zb[]=getListZoneBlanche1(gr);			
			ZoneBlanche zb1[];
			int d[]=calcDist0();
			int e; int k;int indic;
			
			while(true){
				
				indic=0;
				e=0;
				k=t-1;
				
				String str ="";
				for(int i=0;i<t;i++){
					str=str+" "+ g1[i];	
				}
				
				if(estCorrecte1(g)){
					
					zb1=getListZoneBlanche1(g);
					if (compare(zb1,zb)){
						int e1=0;
						for (int i=0;i<zb1.length; i++ ){
							int n=-1;
							for (int j=0;j<zb1[i].renvoitTaille();j++){
								if (grillePsIn[zb1[i].renvoitCase(j)[0]][zb1[i].renvoitCase(j)[1]]>0){
									if(grillePsIn[zb1[i].renvoitCase(j)[0]][zb1[i].renvoitCase(j)[1]]!=zb1[i].renvoitTaille()){e1=1;}
									if(n!=appartientZoneBlanche(zb1[i].renvoitCase(j),zb) && n>=0){ e1=1;}
									n=appartientZoneBlanche(zb1[i].renvoitCase(j),zb);
								}
							}
						}
						if(e1==0){
							
						compteur++;}}
				}
				if(g1[k]<dim*dim-s-1 && g1[k]-g1[k-1]<d[g1[k-1]]+1 ){
					e=1;
					g[invf[g1[k]][0]][invf[g1[k]][1]]=0;
					g1[k]++;
					g[invf[g1[k]][0]][invf[g1[k]][1]]=1;
				}
				
				if((g1[k]<dim*dim-s-1-(t-k-1) && g1[k]-g1[k-1]<d[g1[k-1]]+1)==false && e==0){
					
					while(indic==0 &&( g1[k]<dim*dim-s-1-(t-k-1) && g1[k]-g1[k-1]<d[g1[k-1]])==false ){
						
							k--;
							if(k==0){indic=1;}
						}
					if(k==0){
						if(g1[k]>=dim*dim-s-1-(t-k-1)){return compteur; }
					}
					
					g[invf[g1[k]][0]][invf[g1[k]][1]]=0;
					g1[k]++;
					g[invf[g1[k]][0]][invf[g1[k]][1]]=1;
					for(int i=1;i<t-k;i++){
						g[invf[g1[k+i]][0]][invf[g1[k+i]][1]]=0;
						g1[k+i]=g1[k]+i;
						g[invf[g1[k+i]][0]][invf[g1[k+i]][1]]=1;
					}	
				}
				}			
		}
		
		public int[][] genereGrilleInitialeASolutionUnique(int[][] formeNoire ){
			int [][] echec=grilleConstante(dim,-1);
			if(calculerTaille(formeNoire)<2){return echec;}
			int g[][]=grilleConstante(dim,0);
			ZoneBlanche zb[] = getListZoneBlanche1(formeNoire);
			int nbZone=zb.length;int e=0;
			for (int i=0;i<nbZone;i++){
				for (int j=0;j<zb[i].renvoitTaille();j++){
					g[zb[i].renvoitCase(j)[0]][zb[i].renvoitCase(j)[1]]=zb[i].renvoitTaille();
				}
			}
			int c[]=new int[nbZone]; c[0]=0;
			for (int i=1;i<nbZone;i++){c[i]=-1;}
			int niveau=0;
			int cMax[]=new int[nbZone];
			for (int i=0;i<nbZone;i++){cMax[i]=zb[i].renvoitTaille()-1;}
			while(true){
				e=0;
				
				for (int i=0;i<zb[niveau].renvoitTaille();i++){
					g[zb[niveau].renvoitCase(i)[0]][zb[niveau].renvoitCase(i)[1]]=0;}
				g[zb[niveau].renvoitCase(c[niveau])[0]][zb[niveau].renvoitCase(c[niveau])[1]]=zb[niveau].renvoitTaille();
				
				if(aPlusDe2Solutions(formeNoire,g)){
				e=1;
				while(c[niveau]>=cMax[niveau]){
					if(niveau==0){return echec;}
					for (int i=0;i<zb[niveau].renvoitTaille();i++){
						g[zb[niveau].renvoitCase(i)[0]][zb[niveau].renvoitCase(i)[1]]=zb[niveau].renvoitTaille();}
					c[niveau]=0;
					niveau --;
				}
				c[niveau]++;
			}
			if(aPlusDe2Solutions(formeNoire,g)==false && e==0){
				if(niveau==nbZone-1){return g;}
				niveau ++; c[niveau]=0;
			}
			
			}
			
			
			
		}
 
		
		
		public int appartientZoneBlanche(int[] c, ZoneBlanche[] zb){ // renvoit i tel que c appartiennent à zb[i]
			
			for (int i=0;i<zb.length;i++){
				for (int j=0;j<zb[i].renvoitTaille();j++){
					if (c==zb[i].renvoitCase(j)){return i;}
				}
			}
			return -1;
		}
		
		
		
		/*public static void main(String[] args) {
			
			FormeNoire forme = new FormeNoire(4);
			
			/*int[][] g = {{ 0,0, 1, 0, 1},{ 0, 1,1, 1, 1},{ 1, 1,0,0, 1},{0,1,0, 1, 0,},{1,1,1,1,0}};
			int[][]gI={{3,0,0,1,0},{0,0, 0, 0, 0},{0,0,3,0,0},{1, 0, 0, 0, 2},{0, 0,0, 0,0}};
			
			int[][] h={{ 0,0, 1, 0, 1},{ 0, 1,1, 1, 1},{ 1, 1,0,1, 0},{0,1,0, 1, 0,},{1,1,0,1,1}};
			afficher(h);
			ZoneBlanche z[]=forme.getListZoneBlanche1(h);
			System.out.println(z.length);
			System.out.println(forme.estContinue(h));
			forme.compare(z, forme.getListZoneBlanche1(g));
			System.out.println(forme.compare(z, forme.getListZoneBlanche1(g)));
			//System.out.println(forme.compare(forme.getListZoneBlanche1(g), forme.getListZoneBlanche1(g)));
			
			afficher(g);
			System.out.println(forme.estContinue(g));
			afficher(gI);
			//System.out.println(forme.nombreDeSolution(g,gI));
			
			
			//afficher(forme.genereGrilleInitialeASolutionUnique(g));
			//System.out.println("yuyuyu");
			//forme.nombreDeSolution(g,forme.genereGrilleInitialeASolutionUnique(g));
			/*System.out.println(estContinue(grilleConstante(7,1)));
			 * 
			 */
			//int[][] g ={{1, 1, 0, 1, 1, 1, 1},{1, 0, 1, 0, 0, 0, 1},{1, 0, 1, 1, 0, 1, 1},{1, 0, 1, 0, 1, 0, 1},{1, 1, 1, 1, 1, 1, 1},{1, 0,0, 1, 0, 0, 1},{1, 1, 1, 1, 1, 1, 1}};
			//afficher(forme.grille);
			//System.out.println(forme.estContinue(forme.grille));
			//System.out.println(forme.getListZoneBlanche1(forme.grille).length);*/
			
		//int g[][] ={{1, 1, 1, 1, 1, 1},{0, 1, 0, 1, 0, 1},{1, 1, 1, 0, 0, 1},{1, 0, 0, 1, 1, 0},{1, 0, 1, 0, 1, 0},{1, 1,1, 1, 1, 1}};
		/*int[][] g={{1,0,0,1},{1,1,1,1},{0,1,0,1},{0,1,1,1}};
		//afficher(g);	
		System.out.println(forme.estContinue(g));
		System.out.println(forme.getListZoneBlanche1(g).length);
		}*/
		
		}
	
	
	


