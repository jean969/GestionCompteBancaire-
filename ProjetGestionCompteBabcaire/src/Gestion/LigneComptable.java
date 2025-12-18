package Gestion;

import java.time.LocalDateTime;

public class LigneComptable {

    private TypeOperation type;
    private double montant;
    private LocalDateTime date;
    private String description;

    public LigneComptable(TypeOperation type, double montant, String description) {
        this.type = type;
        this.montant = montant;
        this.date = LocalDateTime.now();
        this.description = description;
    }

    @Override
    public String toString() {
        return date + " | " + type + " | " + montant + " FCFA | " + description;
    }
}
