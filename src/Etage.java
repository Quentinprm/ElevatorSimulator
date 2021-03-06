import java.util.ArrayList;
import java.util.Iterator;

public class Etage extends Constantes {

	private int numero; // de l'Etage pour l'usager

	public Etage plus_haut; // ou null sinon

	public Etage plus_bas; // ou null sinon

	private LoiDePoisson poissonFrequenceArrivee; // dans l'Etage

	private ArrayList<Passager> listePassagersEtage = new ArrayList<Passager>();

	public Etage(Etage pb, int n, int fa) {
		plus_bas = pb;
		numero = n;
		int germe = n << 2;
		if (germe <= 0) {
			germe = -germe + 1;
		}
		poissonFrequenceArrivee = new LoiDePoisson(germe, fa);
	}

	public void afficheLaSituation(Immeuble immeuble) {
		if (numero() >= 0) {
			System.out.print(' ');
		}
		System.out.print(numero());
		if (this == immeuble.cabine.etage) {
			System.out.print(" C ");
			if (immeuble.cabine.porteOuverte) {
				System.out.print("[  ]: ");
			} else {
				System.out.print(" [] : ");
			}
		} else {
			System.out.print("   ");
			System.out.print(" [] : ");
		}
		int i = 0;
		boolean stop = listePassagersEtage.size() == 0;
		while (!stop) {
			if (i >= listePassagersEtage.size()) {
				stop = true;
			} else if (i > 6) {
				stop = true;
				System.out.print("...(");
				System.out.print(listePassagersEtage.size());
				System.out.print(')');
			} else {
				listePassagersEtage.get(i).affiche();
				i++;
				if (i < listePassagersEtage.size()) {
					System.out.print(", ");
				}
			}
		}
		System.out.print('\n');
	}

	public int numero() {
		return this.numero;
	}

	public void ajouter(Passager passager) {
		assert passager != null;
		listePassagersEtage.add(passager);
	}

	public long arriveeSuivante() {
		return poissonFrequenceArrivee.suivant();
	}
	public boolean passagerAttende(){
		boolean res=false;
		if(this.listePassagersEtage.size()!=0){
			res=true;
		}
		return res;
	}
	public int nbPassagers(){
		return this.listePassagersEtage.size();
	}
	public Passager getPassager(int i){
		return this.listePassagersEtage.get(i);
	}
	public void delPassager(int i){
		this.listePassagersEtage.remove(i);
	}
	public boolean memeSens(Cabine c){
		for (int i=0;i<this.listePassagersEtage.size();i++){
			if(this.listePassagersEtage.get(i).sens()==c.status()){
				return true;
			}
		}
			return false;

	}
}
