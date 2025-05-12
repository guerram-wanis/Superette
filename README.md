# Projet : Ma Supérette sur le Net

## Présentation

Ce projet est une application Java de gestion d’une supérette en ligne. Elle permet la gestion des produits, fournisseurs, ventes, lots, contrats, résultats, stocks, commandes et utilisateurs via une base de données PostgreSQL.

Le projet repose sur une structure orientée objet où chaque entité métier (Produit, Lot, Fournisseur, Vente, etc.) est représentée par une classe implémentant une interface `IData`. Des types personnalisés sont utilisés via l’énumération `fieldType`.

## Technologies utilisées

- Java (JDK 17+ recommandé)
- PostgreSQL
- JDBC
- Interface `IData` + énumération `fieldType`
- Requêtes SQL (script `superette.sql`)
- Compilation avec `javac`
- Exécution via `java` (`Test.class`)

## Structure du projet

### Classes métier :
- `Produit.class`
- `Lot.class`
- `Fournisseur.class`
- `Contact.class`
- `Contrat.class`
- `Vente.class`

### Gestion :
- `Gestion.class` : gestion centralisée de l’application
- `GestionStock.class` : gestion des stocks
- `GestionVente.class` : gestion des ventes
- `GestionCommande.class` : gestion des commandes
- `GestionFournisseur.class` : gestion des fournisseurs
- `GestionResultat.class` : affichage de résultats/statistiques

### Autres :
- `IData.class` : interface commune à toutes les entités
- `fieldType.class` : types de données utilisés pour les colonnes SQL
- `Connexion.class` : gestion de la connexion à la base de données
- `Test.class` : classe principale de test/validation

### SQL :
- `superette.sql` : script de création des tables de la base de données

## Utilisation

### 1. Préparation de la base de données
Lancer le script SQL pour créer les tables nécessaires :

```sql
-- Exemple
psql -U postgres -f superette.sql
```

### 2. Compilation

```bash
javac *.java
```

### 3. Exécution

```bash
java Test
```

## Auteurs

Projet réalisé par Yanis Laftimi (L2 Informatique - Groupe 4).