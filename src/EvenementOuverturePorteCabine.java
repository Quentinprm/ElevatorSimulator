import java.util.ArrayList;

public class EvenementOuverturePorteCabine extends Evenement {
    
    public EvenementOuverturePorteCabine(long d) {
        super(d);
    }
    
    public void afficheDetails(Immeuble immeuble) {
        System.out.print("OPC");
    }
    
    public void traiter(Immeuble immeuble, Echeancier echeancier) {
        Cabine cabine = immeuble.cabine;
        Etage etage = cabine.etage;
	assert ! cabine.porteOuverte;
		cabine.porteOuverte=true;
	assert cabine.porteOuverte;
	int nbsortie=cabine.sortiePassagers();
	int nbmontee=0;
	int i=0;
		while(etage.nbPassagers()!=0 && i<etage.nbPassagers()){
			if(etage.getPassager(i).sens()==cabine.status()){
			cabine.entreePassagers(etage.getPassager(i));
			etage.delPassager(i);
			nbmontee++;
			}else{
				i++;
			}
		}
		Evenement e=new EvenementFermeturePorteCabine(this.date+Constantes.tempsPourFermerLesPortes+Constantes.tempsPourEntrerDansLaCabine*nbmontee+Constantes.tempsPourSortirDeLaCabine*nbsortie);
		echeancier.ajouter(e);
   }
}
