package Gestion;

import java.util.Scanner;

public class Projet {

    private static Scanner sc = new Scanner(System.in);
    private static Compte compte = null;

    public static void main(String[] args) {

        String choix;

        do {
            menu();
            choix = sc.nextLine();

            switch (choix) {

                case "1":
                    creerCompte();
                    break;

                case "2":
                    afficherCompte();
                    break;

                case "3":
                    faireDepot();
                    break;

                case "4":
                    faireRetrait();
                    break;

                case "5":
                    afficherHistorique();
                    break;

                case "6":
                    System.out.println("Au revoir.");
                    break;

                default:
                    System.out.println("Choix invalide.");
            }

        } while (!choix.equals("6"));
    }

    private static void menu() {
        System.out.println("\n--- MENU BANCAIRE ---");
        System.out.println("1 - Créer un compte");
        System.out.println("2 - Afficher le compte");
        System.out.println("3 - Dépôt");
        System.out.println("4 - Retrait");
        System.out.println("5 - Historique");
        System.out.println("6 - Quitter");
        System.out.print("Choix : ");
    }
    
    
    private static void creerCompte() {

        if (compte != null) {
            System.out.println("Un compte existe déjà.");
            return;
        }

        System.out.print("Type (courant/joint/epargne) : ");
        String type = sc.nextLine();

        System.out.print("Numéro de compte : ");
        String numero = sc.nextLine();

        System.out.print("Nom : ");
        String nom = sc.nextLine();

        System.out.print("Prénom : ");
        String prenom = sc.nextLine();

        System.out.print("Dépôt initial : ");
        double depot = Double.parseDouble(sc.nextLine());

        System.out.print("Taux (%) : ");
        double taux = Double.parseDouble(sc.nextLine());

        compte = new Compte(type, numero, nom, prenom, depot, taux);

        System.out.println("Compte créé avec succès.");
    }
    
    private static void afficherCompte() {
        if (compte == null) {
            System.out.println("Aucun compte.");
            return;
        }
        compte.afficherCompte();
    }

    private static void faireDepot() {
        if (compte == null) {
            System.out.println("Créez d'abord un compte.");
            return;
        }

        System.out.print("Montant à déposer : ");
        double montant = Double.parseDouble(sc.nextLine());

        try {
            compte.depot(montant);
            System.out.println("Dépôt effectué.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void faireRetrait() {
        if (compte == null) {
            System.out.println("Créez d'abord un compte.");
            return;
        }

        System.out.print("Montant à retirer : ");
        double montant = Double.parseDouble(sc.nextLine());

        try {
            compte.retrait(montant);
            System.out.println("Retrait effectué.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void afficherHistorique() {
        if (compte == null) {
            System.out.println("Aucun compte.");
            return;
        }
        compte.afficherHistorique();
    }
}
