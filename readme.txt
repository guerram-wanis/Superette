README : Présentation et Fonctionnement du Projet

Ce projet propose un outil complet pour la gestion d’une supérette, couvrant les aspects suivants :
	•	Gestion des fournisseurs, contacts et contrats.
	•	Gestion des stocks.
	•	Gestion des commandes et des ventes.
	•	Affichage des résultats de ventes (journalier, hebdomadaire, mensuel et annuel).

Points à améliorer ou à ajouter :

	1.	Gestion des prix de vente : La logique de calcul et la validation des prix de 			vente pourraient être optimisées.

	2.	Relation entre les contacts et les entreprises : Bien que les contacts soient 			associés aux entreprises, leur gestion reste basique et pourrait être enrichie.

	3.	Ajout de produits : Une fonctionnalité pour gérer directement les produits 			manque.


Par ailleurs, le script de création des tables a été modifié par rapport au premier rendu, notamment avec l’ajout de colonnes de type BOOLEAN dans certaines tables. Ces colonnes permettent d’archiver certaines données en les marquant comme inactives, sans pour autant les supprimer définitivement de la base. 


Fonctionnement des Interfaces

Menu Principal

Le menu principal permet de naviguer entre les différentes sections :

	•	1- Gestion des fournisseurs
	•	2- Gestion des stocks
	•	3- Gestion des commandes
	•	4- Gestion des ventes
	•	5- Résultats

L’utilisateur peut taper le numéro correspondant pour accéder à l’interface souhaitée ou entrer EXIT pour quitter l’application.

1. Gestion des Fournisseurs

Dans cette interface, l’utilisateur peut :

	•	Ajouter un fournisseur : Permet de créer un nouveau fournisseur en entrant ses 			informations.

	•	Modifier un fournisseur : Permet de mettre à jour les informations d’un 			fournisseur existant.

	•	Archiver un fournisseur : Permet de désactiver un fournisseur sans le supprimer 		de la base.

	•	Ajouter, modifier ou archiver un contact : Gérer les contacts associés aux 			fournisseurs.

	•	Afficher les contacts associés : Affiche les contacts liés à un fournisseur 			spécifique.

	•	Ajouter ou archiver un contrat : Permet de gérer les contrats entre la supérette 		et les fournisseurs.

	•	Afficher les produits disponibles pour un fournisseur actif.

2. Gestion des Stocks

Cette section permet de :
	•	Voir l’état des stocks actuels : Affichage des lots disponibles, avec leurs 			quantités restantes et leurs dates de péremption.

	•	Ajouter des produits ou des lots : Gérer les approvisionnements et vérifier leur 		conformité avec les contrats existants.

	•	Mettre à jour les lots : Modifier les quantités ou l’état des lots si 				nécessaire.

3. Gestion des Commandes

Les commandes incluent :
	•	Pré-commande : Créer une pré-commande en spécifiant le fournisseur et les 			produits souhaités.

	•	Modifier une pré-commande : Modifier les informations d’une commande non 			validée.

	•	Valider une pré-commande : Finaliser une commande en confirmant sa livraison.
	•	Afficher les pré-commandes : Voir les commandes en cours ou non validées.
	•	Afficher les commandes validées/livrées : Suivre les commandes finalisées.

4. Gestion des Ventes

Cette section concerne la gestion des ventes :
	•	Enregistrer une vente : Ajouter une nouvelle vente avec le lot, la quantité 			vendue et la clé client.

	•	Afficher l’historique des ventes : Consulter toutes les ventes effectuées.

5. Résultats

L’interface des résultats propose plusieurs affichages analytiques :
	•	Résultat journalier : Montre les ventes du jour avec les bénéfices générés.
	•	Résultat hebdomadaire : Résume les ventes et les bénéfices de la semaine en 			cours.
	•	Résultat mensuel : Affiche les performances sur le mois courant.
	•	Résultat annuel : Donne une vue d’ensemble des ventes et des bénéfices sur l’année en cours.


Le projet est fonctionnel et couvre les principales fonctionnalités attendues. Les menus permettent une navigation fluide et les différentes sections offrent une bonne gestion des données de la supérette. Toutefois, des améliorations, notamment dans la gestion des relations entre les entités et l’ajout de fonctionnalités supplémentaires, sont envisageables pour rendre l’application encore plus complète.
				

Yanis Laftimi
Wanis Guerram 
L2 informatique - groupe 4