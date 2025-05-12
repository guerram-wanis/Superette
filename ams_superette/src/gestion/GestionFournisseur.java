package gestion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import classe.Contact;
import classe.Contrat;
import classe.Fournisseur;
import connection.Connexion;

public class GestionFournisseur extends Gestion 
{
	public GestionFournisseur(Connexion c) 
	{
		super(c);
		// TODO Auto-generated constructor stub
	}
	
	//Menu qui permet d'acceder aux différentes fonctionnalités qui permettent de gérer les fournisseurs, contacts et contrats
	public static void gestionFournissseur(Gestion g, BufferedReader bf)
    {
    	try 
    	{
            String command = "";

            while (true) 
            {
            	System.out.println("\n---------- Gestion des fournisseurs ----------\n");
            	
            	displayFournisseur(g);
            	
            	//Saisie de la fonctiannalité à untiliser par l'utiliateur 
	            System.out.println("1- Ajouter un fournisseur");
	            System.out.println("2- Modifier un fournisseur");
	            System.out.println("3- Archiver un fournisseur");
	            System.out.println("4- Ajouter un contact");
	            System.out.println("5- Modifier un contact");
	            System.out.println("6- Archiver un contact");
	            System.out.println("7- Afficher les contacts");
	            System.out.println("8- Ajouter un contrat");
	            System.out.println("9- Archiver un contrat");
	            System.out.println("10- Afficher les produits disponible pour un fournisseur");
	            System.out.println("\nEntrez (R) pour retourner au menu précédent");
	            
	            System.out.println("\n----------------------------------------\n");
	            
	            command = bf.readLine().trim().toUpperCase();
	
	            switch (command) {
	                case "1":
	                    insertFournisseur(g, bf);
	                    break;
	
	                case "2":
	                    updateFournisseur(g, bf);
	                    break;
	
	                case "3":
	                    archiverFournisseur(g, bf);
	                    break;
	                    
	                case "4":
	                    insertContact(g, bf);
	                    break;
	                    
	                case "5":
	                    updateContact(g, bf);
	                    break;
	                    
	                case "6":
	                    archiverContact(g, bf);
	                    break;
	                
	                case "7":
	                    displayContacts(g, bf);
	                    break;
	                    
	                case "8":
	                    insertContrat(g, bf);
	                    break;
	                    
	                case "9":
	                    archiverContrat(g, bf);
	                    break;
	                    
	                case "10":
	                    displayContratWFournisseur(g, bf);
	                    break;
	
	                case "R":
	                    System.out.println("Retour à l'acceuil.\n");
	                    return;
	
	                default:
	                    System.out.println("Commande inconnue. Entrez 1, 2, 3, 4, 5, 6 ou R.");
                }
            }
        } 
    	catch (Exception e) 
    	{
            System.out.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        }
    }
    
	//Création d'un fournisseur
    private static void insertFournisseur(Gestion gestion, BufferedReader reader) 
    {
        try 
        {
        	System.out.println("\n--- Ajouter un fournisseur ---\n");
        	
            System.out.print("Entrez l'ID : ");
            int id = Integer.parseInt(reader.readLine().trim());

            System.out.print("Entrez le nom de la société : ");
            String nom = reader.readLine().trim();

            System.out.print("Entrez le numéro de SIRET : ");
            String siret = reader.readLine().trim();

            System.out.print("Entrez l'adresse : ");
            String adresse = reader.readLine().trim();

            System.out.print("Entrez l'adresse e-mail : ");
            String email = reader.readLine().trim();
            
            Fournisseur f = new Fournisseur(id, nom, siret, adresse, email);
            
        	//Fournisseur f = new Fournisseur(6, "Casino", "56789012345678", "50 Place du Commerce, Bordeaux", "contact@casino.com");
        	
            gestion.insert(f, "ams_fournisseur");

            System.out.println("\nFournisseur ajouté !\n");
        } 
        catch (Exception e) {System.out.println("Erreur lors de l'insertion du fournisseur : " + e.getMessage());}
    }
    
    //affichage des fournisseurs (non archivés)
    private static void displayFournisseur(Gestion g)
    {
    	try {g.displayTable("ams_fournisseur");}
    	catch (SQLException e) {System.out.println("Erreur lors de l'affichage des fournisseurs : " + e.getMessage());}
    }
    
    //Modification d'un fournisseur 
    private static void updateFournisseur(Gestion gestion, BufferedReader reader) 
    {
        try 
        {
        	//Trouver une solution pour modifier uniquement les informations souhaitées
        	System.out.println("\n--- Modifier un fournisseur ---\n");
        	
        	System.out.print("Entrez l'ID du fournisseur à modifier : ");
            int id = Integer.parseInt(reader.readLine().trim());

            System.out.print("Entrez le nom de la société : ");
            String nom = reader.readLine().trim();

            System.out.print("Entrez le numéro de SIRET : ");
            String siret = reader.readLine().trim();

            System.out.print("Entrez l'adresse : ");
            String adresse = reader.readLine().trim();

            System.out.print("Entrez l'adresse e-mail : ");
            String email = reader.readLine().trim();
 
            Fournisseur f = new Fournisseur(id, nom, siret, adresse, email);
            
        	//Fournisseur f = new Fournisseur(1, "Auchan", "442443", "91011 Boulevard Sakakini", "auchan@auchan.com");
            
        	gestion.update(f, "ams_fournisseur");

            System.out.println("Fournisseur modifié !");
        } 
        catch (Exception e) {System.out.println("Erreur lors de la modification du fournisseur : " + e.getMessage());}
    }
   
    //Archiver un fournisseur
    private static void archiverFournisseur(Gestion gestion, BufferedReader reader) 
    {
        try 
        {
        	System.out.print("Entrez l'ID du fournisseur à archiver : ");
            int id = Integer.parseInt(reader.readLine().trim());
            
        	Fournisseur f = new Fournisseur();
        	gestion.archiver(f, "ams_fournisseur",id);

            System.out.println("Fournisseur archivé !");
        } 
        catch (Exception e) {System.out.println("Erreur lors de l'archivage du fournisseur : " + e.getMessage());}
    }
    
/******************** GESTION DES CONTRATS ********************/
    
    //Création d'un contrat
    private static void insertContrat(Gestion gestion, BufferedReader reader) 
    {
        try 
        {
        	System.out.println("\n--- Ajouter un contrat ---\n");
        	
        	System.out.print("Entrez l'ID du contrat : ");
            int id = Integer.parseInt(reader.readLine().trim());

            System.out.print("Entrez le prix : ");
            double prix = Double.parseDouble(reader.readLine().trim());

            System.out.print("Entrez la date de début : ");
            String dateD = reader.readLine().trim();

            System.out.print("Entrez la date de fin : ");
            String dateF = reader.readLine().trim();
            
            System.out.print("Entrez l'ID du produit concerné : ");
            int idProd = Integer.parseInt(reader.readLine().trim());
            
            System.out.print("Entrez l'ID du fournisseur concerné : ");
            int idFour = Integer.parseInt(reader.readLine().trim());
            
            Contrat c = new Contrat(id,prix,dateD, dateF,idProd,idFour);
            
        	//Contrat c = new Contrat(2,9.99,"10-09-2024", "01-01-2025",1,100);
            
        	gestion.insert(c, "ams_contrat");

            System.out.println("Contrat ajouté !");
        } 
        catch (Exception e) {System.out.println("Erreur lors de l'ajout du contrat : " + e.getMessage());}
    }
    
    private static void archiverContrat(Gestion gestion, BufferedReader reader) 
    {
        try 
        {
        	//Trouver une solution pour modifier uniquement les informations souhaitées
        	System.out.print("Entrez l'ID du contrat à archiver : ");
            int id = Integer.parseInt(reader.readLine().trim());

            Contrat c = new Contrat();
            
        	//Contrat c = new Contrat(1,9.99,"10-09-2024", "01-01-2025",1,1);
            
        	gestion.archiver(c, "ams_contrat",id);

            System.out.println("Contrat archivé !");
        } 
        catch (Exception e) {System.out.println("Erreur lors de l'archivage du contrat : " + e.getMessage());}
    }
    
    //Affiche les produits disponible pour un fournisseur 
    private static void displayContratWFournisseur(Gestion gestion, BufferedReader reader) throws SQLException
    {   	
    	try 
    	{
    		System.out.print("Entrez l'ID du fournisseur pour lequel vous voulez voir les produits disponibles : ");
            int id = Integer.parseInt(reader.readLine().trim());
        	
        	String req = "SELECT p.id_produit, p.nom_produit, p.description_produit, p.categorie_produit, p.prix_vente_produit FROM ams_produit p JOIN ams_contrat c ON p.id_produit = c.id_produit WHERE c.id_fournisseur = " + id +" AND c.actif = TRUE;";
        	
        	gestion.displayReq(req);
        } 
        catch (Exception e) {System.out.println("Erreur lors de l'affichage des contrats : " + e.getMessage());}
    }
    
    /******************** GESTION DES CONTACTS ********************/
    
    //Ajouter un contact
    private static void insertContact(Gestion gestion, BufferedReader reader) 
    {
        try 
        {
        	System.out.println("\n--- Ajouter un contact ---\n");
        	
            System.out.print("Entrez l'ID : ");
            int id = Integer.parseInt(reader.readLine().trim());

            System.out.print("Entrez le prénom : ");
            String prenom = reader.readLine().trim();

            System.out.print("Entrez le nom : ");
            String nom = reader.readLine().trim();

            System.out.print("Entrez la fonction : ");
            String fonction = reader.readLine().trim();

            System.out.print("Entrez l'adresse e-mail : ");
            String email = reader.readLine().trim();
            
            System.out.print("Entrez le numéro de téléphone : ");
            String tel = reader.readLine().trim();
        	
            Contact c = new Contact(id,prenom,nom,fonction,email,tel);
            
        	//Contact c = new Contact(3,"MOO","PAA","Monstre","MOOPAAmonstre@neeeeeh.com","0645578909");
            
        	gestion.insert(c, "ams_contact");

            System.out.println("Contact ajouté !");
        } 
        catch (Exception e) {System.out.println("Erreur lors de la création du contact : " + e.getMessage());}
    }
    
    //Modifier un contact 
    private static void updateContact(Gestion gestion, BufferedReader reader) 
    {
        try 
        {
        	//Trouver une solution pour modifier uniquement les informations souhaitées
        	System.out.println("\n--- Modifier un contact ---\n");
        	
            System.out.print("Entrez l'ID du contact à modifier : ");
            int id = Integer.parseInt(reader.readLine().trim());

            System.out.print("Entrez le prénom : ");
            String prenom = reader.readLine().trim();

            System.out.print("Entrez le nom : ");
            String nom = reader.readLine().trim();

            System.out.print("Entrez la fonction : ");
            String fonction = reader.readLine().trim();

            System.out.print("Entrez l'adresse e-mail : ");
            String email = reader.readLine().trim();
            
            System.out.print("Entrez le numéro de téléphone : ");
            String tel = reader.readLine().trim();
        	
            Contact c = new Contact(id,prenom,nom,fonction,email,tel);
            
        	//Contact c = new Contact(3,"MOO","PAA","Comme MORTA","MOOPAAmonstre@neeeeeh.com","0645578909");
            
        	gestion.update(c, "ams_contact");

            System.out.println("Contact modifié !");
        } 
        catch (Exception e) {System.out.println("Erreur lors de la modification du contact : " + e.getMessage());}
    }
    
    //Archiver un contact
    private static void archiverContact(Gestion gestion, BufferedReader reader) 
    {
        try 
        {
        	System.out.print("Entrez l'ID du contrat à archiver : ");
            int id = Integer.parseInt(reader.readLine().trim());
            
        	Contact c = new Contact();
        	gestion.archiver(c, "ams_contact",id);

            System.out.println("Contact archivé !");
        } 
        catch (Exception e) {System.out.println("Erreur lors de l'archivage du contact : " + e.getMessage());}
    }
    
    private static void displayContacts(Gestion gestion, BufferedReader reader) throws SQLException
    {   	
    	try 
    	{
    		String req = "SELECT \n"
    				+ "    f.nom_fournisseur AS fournisseur,\n"
    				+ "    c.nom_contact AS nom_contact,\n"
    				+ "    c.prenom_contact AS prenom_contact,\n"
    				+ "    c.fonction_contact AS fonction_contact,\n"
    				+ "    c.mail_contact AS email_contact,\n"
    				+ "    c.tel_contact AS telephone_contact\n"
    				+ "FROM \n"
    				+ "    ams_fournisseur_contact fc\n"
    				+ "JOIN \n"
    				+ "    ams_fournisseur f ON fc.id_fournisseur = f.id_fournisseur\n"
    				+ "JOIN \n"
    				+ "    ams_contact c ON fc.id_contact = c.id_contact\n"
    				+ "ORDER BY \n"
    				+ "    f.nom_fournisseur, c.nom_contact;";
        	
        	gestion.displayReq(req);
        } 
        catch (Exception e) {System.out.println("Erreur lors de l'affichage des contrats : " + e.getMessage());}
    }
    
    //Ajouter la méthode permettant l'ajout d'une relation fournisseur/contact
}
