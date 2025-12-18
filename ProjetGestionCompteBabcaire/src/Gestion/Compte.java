package Gestion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Compte {

    private String type;
    private String numero;
    private String nom;
    private String prenom;
    private double solde;
    private double taux;
    private LocalDate dateCreation;

    private List<LigneComptable> historique;

    public Compte(String type, String numero, String nom, String prenom,
                  double depotInitial, double taux) {

        if (depotInitial < 0) {
            throw new IllegalArgumentException("Dépôt initial invalide");
        }

        this.type = type;
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.solde = depotInitial;
        this.setTaux(taux);
        this.dateCreation = LocalDate.now();
        this.historique = new ArrayList<>();

        historique.add(new LigneComptable(
                TypeOperation.DEPOT,
                depotInitial,
                "Dépôt initial"
        ));
    }

    // ---------- MÉTIERS ----------

    public void depot(double montant) {
        verifierMontant(montant);

        solde += montant;
        historique.add(new LigneComptable(
                TypeOperation.DEPOT,
                montant,
                "Dépôt"
        ));
    }

    public void retrait(double montant) {
        verifierMontant(montant);

        if (montant > solde) {
            throw new IllegalStateException("Solde insuffisant");
        }

        solde -= montant;
        historique.add(new LigneComptable(
                TypeOperation.RETRAIT,
                montant,
                "Retrait"
        ));
    }

    public void virement(Compte destinataire, double montant) {

        if (destinataire == null) {
            throw new IllegalArgumentException("Compte destinataire invalide");
        }

        verifierMontant(montant);

        if (montant > solde) {
            throw new IllegalStateException("Solde insuffisant");
        }

        // débit
        this.retrait(montant);
        historique.add(new LigneComptable(
                TypeOperation.VIREMENT_SORTANT,
                montant,
                "Vers compte " + destinataire.numero
        ));

        // crédit
        destinataire.depot(montant);
        destinataire.historique.add(new LigneComptable(
                TypeOperation.VIREMENT_ENTRANT,
                montant,
                "Depuis compte " + this.numero
        ));
    }

    private void verifierMontant(double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Montant invalide");
        }
    }

    // ---------- AFFICHAGE ----------

    public void afficherCompte() {
        System.out.println("\n--- COMPTE BANCAIRE ---");
        System.out.println("Titulaire : " + nom + " " + prenom);
        System.out.println("Numéro    : " + numero);
        System.out.println("Type      : " + type);
        System.out.println("Solde     : " + solde + " FCFA");
        System.out.println("Créé le   : " + dateCreation);
    }

    public void afficherHistorique() {
        if (historique.isEmpty()) {
            System.out.println("Aucune opération.");
            return;
        }
        historique.forEach(System.out::println);
    }

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}
}
