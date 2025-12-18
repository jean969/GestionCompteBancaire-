# 📌 Projet Gestion de Comptes Bancaires (Java)

Application Java de gestion de comptes bancaires basée sur une architecture orientée objet, avec interface console et interface graphique (GUI).

---

## 🧾 Description

Ce projet est une application Java permettant de gérer des comptes bancaires et leurs opérations associées.
Il met en œuvre les principes fondamentaux de la **programmation orientée objet (OOP)** et sert de base évolutive vers des architectures plus avancées (persistance, tests, GUI, etc.).

### Fonctionnalités principales :

* Création de comptes bancaires
* Dépôt, retrait et virement entre comptes
* Historique détaillé des opérations
* Calcul automatique du solde
* Interface console
* Interface graphique (Swing / JavaFX)

---

## 🏗️ Architecture du projet

```
ProjetGestionCompteBancaire/
├── src/
│   ├── module-info.java
│   └── Gestion/
│       ├── Compte.java                # Logique métier du compte bancaire
│       ├── LigneComptable.java        # Représente une opération bancaire
│       ├── TypeOperation.java         # Enum des types d'opérations
│       ├── Projet.java                # Point d'entrée (interface console)
│       └── CompteBancaireGUI.java     # Interface graphique
├── bin/                               # Classes compilées (IDE)
├── out/                               # Classes compilées (manuel)
└── README.md
```

### 🧠 Principes respectés

* Séparation des responsabilités
* Encapsulation des données
* Utilisation d’énumérations (`enum`)
* Gestion des erreurs via exceptions
* Historique des transactions

---

## ⚙️ Prérequis

* **Java JDK** : version 11 ou supérieure
* Java accessible depuis le terminal (`PATH`)
* **Eclipse IDE** (optionnel mais recommandé)

### Vérification de Java :

```bash
java -version
javac -version
```

---

## ▶️ Installation et exécution

### 🔹 Méthode 1 : Compilation manuelle (PowerShell)

1. Ouvrir PowerShell à la racine du projet

2. Créer le dossier de sortie :

```powershell
if (-not (Test-Path out)) { New-Item -ItemType Directory -Path out }
```

3. Compiler le projet :

```powershell
javac -d out src\module-info.java src\Gestion\*.java
```

4. Exécuter l’application :

```powershell
java -p out -m ProjetGestionCompteBancaire/Gestion.Projet
```

---

### 🔹 Méthode 2 : Eclipse IDE

1. **Importer le projet**

   * File → Import → Existing Projects into Workspace
   * Sélectionner `ProjetGestionCompteBancaire`

2. **Configurer le JDK**

   * Project → Properties → Java Build Path
   * Vérifier Java 11+

3. **Exécuter**

   * Clic droit sur `Projet.java` → Run As → Java Application

---

## 🧩 Description des classes principales

### `Compte.java`

* Représente un compte bancaire
* Contient le solde, le titulaire et l’historique
* Méthodes métier : dépôt, retrait, virement

### `LigneComptable.java`

* Modélise une opération bancaire
* Contient : type, montant, date, description

### `TypeOperation.java`

* Enumération des types d’opérations :

  * DEPOT
  * RETRAIT
  * VIREMENT_ENTRANT
  * VIREMENT_SORTANT

### `Projet.java`

* Point d’entrée de l’application
* Gère l’interface console et les interactions utilisateur

### `CompteBancaireGUI.java`

* Interface graphique
* Permet la gestion visuelle des comptes et opérations

---

## 🛠️ Dépannage courant

### ❌ Erreur : *Could not find or load main class*

➡️ Vérifier le module et le package :

```powershell
java -p out -m ProjetGestionCompteBancaire/Gestion.Projet
```

### ❌ Problème de module

Vérifier `module-info.java` :

```java
module ProjetGestionCompteBancaire {
    requires java.desktop;
    exports Gestion;
}
```

### ❌ Erreurs Eclipse

* Project → Clean
* Vérifier la version du compilateur
* Supprimer `bin/` si nécessaire

---

## 📌 Bonnes pratiques Git

### `.gitignore` recommandé

```gitignore
*.class
bin/
out/

.project
.classpath
.settings/

.idea/
*.iml

*.log
.DS_Store
Thumbs.db
```

### Initialisation Git

```bash
git init
git add .
git commit -m "Initial commit - Projet Gestion de Comptes Bancaires"
```

---

## 🚀 Améliorations prévues

* [ ] Gestion de plusieurs comptes
* [ ] Sauvegarde des données (fichier / base de données)
* [ ] Export CSV / Excel
* [ ] Génération de relevés PDF
* [ ] Authentification utilisateur
* [ ] Recherche et filtres dans l’historique
* [ ] Tests unitaires (JUnit)
* [ ] Documentation Javadoc

---

## 👤 Auteur
Yavo Jean-Pierre



