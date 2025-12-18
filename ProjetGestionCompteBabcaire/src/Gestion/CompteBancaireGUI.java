package Gestion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CompteBancaireGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    
    // Composants GUI
    private JTextField txtNom, txtPrenom, txtNumero, txtDepot, txtTaux, txtMontant;
    private JComboBox<String> comboType;
    private JTextArea zoneAffichage;
    private JButton btnCreer, btnAfficher, btnDeposer, btnRetirer, btnCalculerInterets;
    
    // Données du compte
    private CompteBancaire compte;
    
    public CompteBancaireGUI() {
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("Gestion de Compte Bancaire");
        setSize(700, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Panel principal avec marges
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        // ----- SECTION CRÉATION DE COMPTE -----
        mainPanel.add(creerPanelFormulaire(), BorderLayout.NORTH);
        
        // ----- SECTION OPÉRATIONS -----
        mainPanel.add(creerPanelOperations(), BorderLayout.CENTER);
        
        // ----- ZONE AFFICHAGE -----
        mainPanel.add(creerPanelAffichage(), BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private JPanel creerPanelFormulaire() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Création de compte"));
        
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        
        formPanel.add(new JLabel("Type de compte :"));
        comboType = new JComboBox<>(new String[]{"Courant", "Joint", "Épargne"});
        formPanel.add(comboType);
        
        formPanel.add(new JLabel("Numéro :"));
        txtNumero = new JTextField();
        formPanel.add(txtNumero);
        
        formPanel.add(new JLabel("Nom :"));
        txtNom = new JTextField();
        formPanel.add(txtNom);
        
        formPanel.add(new JLabel("Prénom :"));
        txtPrenom = new JTextField();
        formPanel.add(txtPrenom);
        
        formPanel.add(new JLabel("Dépôt initial (FCFA) :"));
        txtDepot = new JTextField("0");
        formPanel.add(txtDepot);
        
        formPanel.add(new JLabel("Taux d'intérêt (%) :"));
        txtTaux = new JTextField("0");
        formPanel.add(txtTaux);
        
        panel.add(formPanel, BorderLayout.CENTER);
        
        btnCreer = new JButton("Créer Compte");
        btnCreer.setBackground(new Color(46, 204, 113));
        btnCreer.setForeground(Color.WHITE);
        btnCreer.setFont(new Font("Arial", Font.BOLD, 12));
        btnCreer.addActionListener(e -> creerCompte());
        
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnCreer);
        panel.add(btnPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel creerPanelOperations() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Opérations"));
        
        JPanel operPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        
        operPanel.add(new JLabel("Montant (FCFA) :"));
        txtMontant = new JTextField();
        operPanel.add(txtMontant);
        
        panel.add(operPanel, BorderLayout.NORTH);
        
        JPanel btnPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        
        btnDeposer = new JButton("Déposer");
        btnDeposer.setEnabled(false);
        btnDeposer.addActionListener(e -> deposer());
        btnPanel.add(btnDeposer);
        
        btnRetirer = new JButton("Retirer");
        btnRetirer.setEnabled(false);
        btnRetirer.addActionListener(e -> retirer());
        btnPanel.add(btnRetirer);
        
        btnCalculerInterets = new JButton("Calculer Intérêts");
        btnCalculerInterets.setEnabled(false);
        btnCalculerInterets.addActionListener(e -> calculerInterets());
        btnPanel.add(btnCalculerInterets);
        
        btnAfficher = new JButton("Afficher Détails");
        btnAfficher.setEnabled(false);
        btnAfficher.addActionListener(e -> afficherCompte());
        btnPanel.add(btnAfficher);
        
        panel.add(btnPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel creerPanelAffichage() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Informations du compte"));
        
        zoneAffichage = new JTextArea(10, 40);
        zoneAffichage.setEditable(false);
        zoneAffichage.setFont(new Font("Monospaced", Font.PLAIN, 12));
        zoneAffichage.setText("Aucun compte créé.");
        
        JScrollPane scrollPane = new JScrollPane(zoneAffichage);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void creerCompte() {
        try {
            // Validation des champs
            String type = comboType.getSelectedItem().toString();
            String numero = txtNumero.getText().trim();
            String nomValue = txtNom.getText().trim();
            String prenomValue = txtPrenom.getText().trim();
            
            if (numero.isEmpty() || nomValue.isEmpty() || prenomValue.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Veuillez remplir tous les champs obligatoires.", 
                    "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            double depotInitial = Double.parseDouble(txtDepot.getText().trim());
            double tauxValue = Double.parseDouble(txtTaux.getText().trim());
            
            if (depotInitial < 0) {
                JOptionPane.showMessageDialog(this, 
                    "Le dépôt initial ne peut pas être négatif.", 
                    "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (tauxValue < 0 || tauxValue > 100) {
                JOptionPane.showMessageDialog(this, 
                    "Le taux doit être entre 0 et 100%.", 
                    "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Création du compte
            compte = new CompteBancaire(type, numero, nomValue, prenomValue, depotInitial, tauxValue);
            
            // Activation des boutons
            btnDeposer.setEnabled(true);
            btnRetirer.setEnabled(true);
            btnCalculerInterets.setEnabled(true);
            btnAfficher.setEnabled(true);
            
            JOptionPane.showMessageDialog(this, 
                "Compte créé avec succès !\nTitulaire : " + nomValue + " " + prenomValue, 
                "Succès", JOptionPane.INFORMATION_MESSAGE);
            
            afficherCompte();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Veuillez entrer des valeurs numériques valides pour le dépôt et le taux.", 
                "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deposer() {
        if (compte == null) return;
        
        try {
            double montant = Double.parseDouble(txtMontant.getText().trim());
            
            if (montant <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "Le montant doit être positif.", 
                    "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            compte.deposer(montant);
            JOptionPane.showMessageDialog(this, 
                "Dépôt de " + montant + " FCFA effectué avec succès.", 
                "Succès", JOptionPane.INFORMATION_MESSAGE);
            
            afficherCompte();
            txtMontant.setText("");
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Veuillez entrer un montant valide.", 
                "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void retirer() {
        if (compte == null) return;
        
        try {
            double montant = Double.parseDouble(txtMontant.getText().trim());
            
            if (montant <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "Le montant doit être positif.", 
                    "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (montant > compte.getSolde()) {
                JOptionPane.showMessageDialog(this, 
                    "Solde insuffisant. Solde actuel : " + compte.getSolde() + " FCFA", 
                    "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            compte.retirer(montant);
            JOptionPane.showMessageDialog(this, 
                "Retrait de " + montant + " FCFA effectué avec succès.", 
                "Succès", JOptionPane.INFORMATION_MESSAGE);
            
            afficherCompte();
            txtMontant.setText("");
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Veuillez entrer un montant valide.", 
                "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void calculerInterets() {
        if (compte == null) return;
        
        double interets = compte.calculerInterets();
        JOptionPane.showMessageDialog(this, 
            String.format("Intérêts calculés : %.2f FCFA\nNouveau solde : %.2f FCFA", 
                interets, compte.getSolde()), 
            "Calcul des intérêts", JOptionPane.INFORMATION_MESSAGE);
        
        afficherCompte();
    }
    
    private void afficherCompte() {
        if (compte == null) {
            zoneAffichage.setText("Aucun compte créé.");
            return;
        }
        
        zoneAffichage.setText(compte.toString());
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new CompteBancaireGUI().setVisible(true);
        });
    }
}

// ----- CLASSE MÉTIER -----
class CompteBancaire {
    private String typeCompte;
    private String numeroCompte;
    private String nom;
    private String prenom;
    private double solde;
    private double taux;
    private LocalDate dateCreation;
    
    public CompteBancaire(String type, String numero, String nom, String prenom, double soldeInitial, double taux) {
        this.typeCompte = type;
        this.numeroCompte = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.solde = soldeInitial;
        this.taux = taux;
        this.dateCreation = LocalDate.now();
    }
    
    public void deposer(double montant) {
        if (montant > 0) {
            solde += montant;
        }
    }
    
    public void retirer(double montant) {
        if (montant > 0 && montant <= solde) {
            solde -= montant;
        }
    }
    
    public double calculerInterets() {
        double interets = solde * (taux / 100);
        solde += interets;
        return interets;
    }
    
    public double getSolde() {
        return solde;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format(
            "═══════════════════════════════════════\n" +
            "         DÉTAILS DU COMPTE\n" +
            "═══════════════════════════════════════\n" +
            "Titulaire    : %s %s\n" +
            "Type         : %s\n" +
            "Numéro       : %s\n" +
            "Solde        : %.2f FCFA\n" +
            "Taux         : %.2f %%\n" +
            "Créé le      : %s\n" +
            "═══════════════════════════════════════",
            nom, prenom, typeCompte, numeroCompte, solde, taux, dateCreation.format(formatter)
        );
    }
} 