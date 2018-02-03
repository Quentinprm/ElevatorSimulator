public class EvenementFermeturePorteCabine extends Evenement {
    
    public EvenementFermeturePorteCabine(long d) {
        super(d);
    }
    
    public void afficheDetails(Immeuble immeuble) {
        System.out.print("FPC");
    }
    
    public void traiter(Immeuble immeuble, Echeancier echeancier) {
	Cabine cabine = immeuble.cabine;
        assert cabine.porteOuverte;
        cabine.porteOuverte=false;
        assert ! cabine.porteOuverte;
        Etage e=cabine.prochainEtage();
        assert e!= null;
        if(e.numero()>cabine.etage.numero()){
        	cabine.changerStatus('^');
        	echeancier.ajouter(new EvenementPassageCabinePalier(this.date+Constantes.tempsPourBougerLaCabineDUnEtage,cabine.etage.plus_haut));
        }
        else{
        	cabine.changerStatus('v');
        	echeancier.ajouter(new EvenementPassageCabinePalier(this.date+Constantes.tempsPourBougerLaCabineDUnEtage,cabine.etage.plus_bas));
        }
        
    }
	
}
