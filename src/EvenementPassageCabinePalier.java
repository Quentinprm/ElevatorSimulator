public class EvenementPassageCabinePalier extends Evenement {

	private Etage etage;

	public EvenementPassageCabinePalier(long d, Etage e) {
		super(d);
		assert e != null;
		etage = e;
	}

	public void afficheDetails(Immeuble immeuble) {
		System.out.print("PCP ");
		System.out.print(etage.numero());
	}

	public void traiter(Immeuble immeuble, Echeancier echeancier) {
		Cabine cabine = immeuble.cabine;
		assert !cabine.etage.equals(this.etage);
		cabine.etage=this.etage;
		assert ! cabine.porteOuverte;
		if(cabine.etage==immeuble.etageLePlusHaut()){
			cabine.changerStatus('v');
		}
		if(cabine.etage==immeuble.etageLePlusBas()){
			cabine.changerStatus('^');
		}
		if(cabine.etage.passagerAttende()&&cabine.nbPassagers()==0){
			cabine.changerStatus(cabine.etage.getPassager(0).sens());
		}
		if((cabine.etage.passagerAttende()&& this.etage.memeSens(cabine))|| cabine.vouloirSortir()){
			echeancier.ajouter(new EvenementOuverturePorteCabine(this.date+Constantes.tempsPourOuvrirLesPortes));

		}
		else{
			if(cabine.status()=='v'){
				if(cabine.etage==immeuble.etageLePlusBas()){
					cabine.changerStatus('^');
					echeancier.ajouter(new EvenementPassageCabinePalier(this.date+Constantes.tempsPourBougerLaCabineDUnEtage,cabine.etage.plus_haut));

				}else{
					echeancier.ajouter(new EvenementPassageCabinePalier(this.date+Constantes.tempsPourBougerLaCabineDUnEtage,cabine.etage.plus_bas));
				}
			}else{
					assert cabine.status()=='^';
					if(cabine.etage==immeuble.etageLePlusHaut()){
						cabine.changerStatus('v');
						echeancier.ajouter(new EvenementPassageCabinePalier(this.date+Constantes.tempsPourBougerLaCabineDUnEtage,cabine.etage.plus_bas));
					}else{
						echeancier.ajouter(new EvenementPassageCabinePalier(this.date+Constantes.tempsPourBougerLaCabineDUnEtage,cabine.etage.plus_haut));
					}

				}
			}
		}
	}
