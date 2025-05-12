-- Supprimer les tables dans l'ordre des dépendances
DROP TABLE IF EXISTS AMS_Vente CASCADE;
DROP TABLE IF EXISTS AMS_Lot CASCADE;
DROP TABLE IF EXISTS AMS_Contrat CASCADE;
DROP TABLE IF EXISTS AMS_Fournisseur_Contact CASCADE;
DROP TABLE IF EXISTS AMS_Produit CASCADE;
DROP TABLE IF EXISTS AMS_Fournisseur CASCADE;
DROP TABLE IF EXISTS AMS_Contact CASCADE;

-- Table Contact
CREATE TABLE AMS_Contact (
    id_contact INT PRIMARY KEY,
    nom_contact VARCHAR(255) NOT NULL,
    prenom_contact VARCHAR(255) NOT NULL,
    fonction_contact VARCHAR(255),
    mail_contact VARCHAR(255) UNIQUE NOT NULL,
    tel_contact VARCHAR(20),
    actif BOOLEAN DEFAULT TRUE NOT NULL
);

-- Table Fournisseur
CREATE TABLE AMS_Fournisseur (
    id_fournisseur INT PRIMARY KEY,
    nom_fournisseur VARCHAR(255) NOT NULL,
    siret VARCHAR(14) UNIQUE NOT NULL,
    adr_fournisseur VARCHAR(255) NOT NULL,
    mail_fournisseur VARCHAR(255) UNIQUE NOT NULL,
    actif BOOLEAN DEFAULT TRUE NOT NULL
);

-- Table Produit
CREATE TABLE AMS_Produit (
    id_produit INT PRIMARY KEY,
    nom_produit VARCHAR(255) NOT NULL,
    description_produit VARCHAR(255),
    categorie_produit VARCHAR(255),
    prix_vente_produit DECIMAL(10, 2) NOT NULL
);

-- Table Fournisseur_Contact
CREATE TABLE AMS_Fournisseur_Contact (
    id_fournisseur INT REFERENCES AMS_Fournisseur(id_fournisseur) ON DELETE CASCADE,
    id_contact INT REFERENCES AMS_Contact(id_contact) ON DELETE CASCADE,
    PRIMARY KEY (id_fournisseur, id_contact)
);

-- Table Contrat
CREATE TABLE AMS_Contrat (
    id_contrat INT PRIMARY KEY,
    prix_contrat DECIMAL(10, 2) NOT NULL,
    date_d_contrat DATE NOT NULL,
    date_f_contrat DATE NOT NULL,
    id_produit INT REFERENCES AMS_Produit(id_produit) ON DELETE CASCADE,
    id_fournisseur INT REFERENCES AMS_Fournisseur(id_fournisseur) ON DELETE CASCADE,
    actif BOOLEAN DEFAULT TRUE NOT NULL
);

-- Table Lot
CREATE TABLE AMS_Lot (
    id_lot INT PRIMARY KEY,
    prix_achat DECIMAL(10, 2) NOT NULL,
    quantite_lot INT NOT NULL,
    date_achat DATE NOT NULL,
    date_peremption DATE,
    etat VARCHAR(255) DEFAULT 'Non confirmé' NOT NULL,
    id_contrat INT REFERENCES AMS_Contrat(id_contrat) ON DELETE CASCADE
);

-- Table Vente
CREATE TABLE AMS_Vente (
    id_vente INT PRIMARY KEY,
    date_vente DATE NOT NULL,
    prix_vente DECIMAL(10, 2) NOT NULL,
    id_lot INT REFERENCES AMS_Lot(id_lot) ON DELETE CASCADE,
    cle_client INT NOT NULL,
    quantite_vendue INT NOT NULL
);