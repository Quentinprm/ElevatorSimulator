import java.util.LinkedList;

public class Echeancier extends Constantes {
    // La liste triée chronologiquement des événements du simulateur.
    
    private LinkedList<Evenement> listeEvenements; // Changement de type autorisé.
    // La liste triée des événements.
    
    public Echeancier() {
        listeEvenements = new LinkedList<Evenement>();
    }
    
    public boolean estVide() {
        return listeEvenements.isEmpty();
    }
    
    public void ajouter(Evenement e) {
        int pos = 0;
        while (pos < listeEvenements.size()) {
            if (listeEvenements.get(pos).date >= e.date) {
                listeEvenements.add(pos, e);
                return;
            } else {
                pos++;
            }
        }
        listeEvenements.add(pos, e);
    }
    
    public Evenement retourneEtEnlevePremier() {
        Evenement e = listeEvenements.getFirst();
        listeEvenements.removeFirst();
        return e;
    }
    
    public void afficheLaSituation(Immeuble ascenseur) {
        System.out.print("Echeancier = ");
        int index = 0;
        while (index < listeEvenements.size()) {
            listeEvenements.get(index).affiche(ascenseur);
            index++;
            if (index < listeEvenements.size()) {
                System.out.print(',');
            }
        }
        System.out.println("");
    }
	
}
