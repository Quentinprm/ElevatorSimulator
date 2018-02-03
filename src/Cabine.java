public class Cabine extends Constantes {
	public Etage etage; // actuel

	public boolean porteOuverte;

	private char status; // '-' ou 'v' ou '^'

	private Passager[] tableauPassager;

	public Cabine(Etage e) {
		assert e != null;
		etage = e;
		tableauPassager = new Passager[nombreDePlacesDansLaCabine];
		porteOuverte = false;
		status = 'v';
	}

	public void afficheLaSituation() {
		System.out.print("Contenu de la cabine: ");
		for (int i = tableauPassager.length - 1; i >= 0 ; i--) {
			Passager p = tableauPassager[i];
			if (p != null) {
				p.affiche();
				System.out.print(' ');
			}
		}
		assert (status == 'v') || (status == '^') || (status == '-');
		System.out.println("\nStatus de la cabine: " + status);
	}

	public char status() {
		assert (status == 'v') || (status == '^') || (status == '-');
		return status;
	}

	public void changerStatus(char s){
		assert (s == 'v') || (s == '^') || (s == '-');
		status = s;
	}

	public int nbPassagers(){
		int res=0;
		for(int i=0;i<this.tableauPassager.length;i++){
			if(this.tableauPassager[i]!=null){
				res++;
			}
		}
		return res;
	}
	public void entreePassagers(Passager p){
		boolean rentrer=false;
		int i=0;
		while(i<this.tableauPassager.length&&!rentrer){
			if(this.tableauPassager[i]==null){
				this.tableauPassager[i]=p;
				rentrer=true;
			}
			i++;
		}
	}
	public boolean vouloirSortir(){
		boolean res=false;
		int i=0;
		while(i<tableauPassager.length && !res){
			Passager p=tableauPassager[i];
			if(p !=null){
				if(p.etageDestination().numero()==etage.numero()){
					res=true;
				}
			}
			i++;
		}
		return res;
	}
	public int sortiePassagers(){
		assert this.porteOuverte;
		int res=0;
		for(int i=0; i<tableauPassager.length;i++){
			Passager p=tableauPassager[i];
			if(p !=null){
				if(p.etageDestination().numero()==etage.numero()){
					tableauPassager[i]=null;
					res++;
				}
			}
		}
		return res;

	}
	public Etage prochainEtage(){
		Etage res=null;
		for( int i=0;i<this.tableauPassager.length;i++){
			if(this.tableauPassager[i]!=null){
				res= this.tableauPassager[i].etageDestination();

			}
		}
			if(res==null){
				if(this.status=='^'){
					res=this.etage.plus_haut;
				}
				else{
					assert this.status=='v';
					res= this.etage.plus_bas;
				}
			}
		return res;	
		
	}

}
