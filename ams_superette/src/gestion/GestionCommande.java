package gestion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import classe.Fournisseur;
import classe.Lot;
import connection.Connexion;

public class GestionCommande extends Gestion
{

	public GestionCommande(Connexion c) 
	{
		super(c);
		// TODO Auto-generated constructor stub
	}
	
	//Menu qui permet d'acceder aux différentes fonctionnalités qui permettent de gérer les commandes
	public static void gestionCommande(Gestion g, BufferedReader bf)
    {
    	try 
    	{
            String command = "";

            while (true) 
            {
            	System.out.println("----- Gestion des Commandes -----\n");
	            System.out.println("1- Pré-commande");
	            System.out.println("2- Modifier une pré-commande");
	            System.out.println("3- Valider une pré-commande");
	            System.out.println("4- Afficher les pré-commandes");
	            System.out.println("5- Afficher les commandes vaidées/livrées");
	            System.out.println("\nEntrez (R) pour retourner au menu précédent");
	   
	            command = bf.readLine().trim().toUpperCase();
	
	            switch (command) 
	            {
	                case "1":
	                    insertLot(g, bf);
	                    break;
	
	                case "2":
	                    updateLot(g, bf);
	                    break;
	                    
	                case "3":
	                    valideLot(g, bf);
	                    break;
	                    
	                case "4":
	                    displayPrecommande(g, bf);
	                    break;
	                    
	                case "5":
	                    displayCommandeValide(g, bf);
	                    break;
	                    
	                case "R":
	                    System.out.println("Retour à l'acceuil.\n");
	                    return;
	
	                default:
	                    System.out.println("Commande inconnue. Entrez 1, 2, 3, 4, 5 ou R.");
                }
            }
        } 
    	catch (Exception e) 
    	{
            System.out.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        }
    }
	
    private static void insertLot(Gestion gestion, BufferedReader reader) 
    {
        try 
        {
        	System.out.println("\n--- Ajouter un lot ---\n");
        	
            System.out.print("Entrez l'ID : ");
            int id = Integer.parseInt(reader.readLine().trim());
            
            System.out.print("Entrez le prix d'achat : ");
            double prixA = Double.parseDouble(reader.readLine().trim());
            
            System.out.print("Entrez la quatité comandée : ");
            int qt = Integer.parseInt(reader.readLine().trim());

            System.out.print("Entrez la date d'achat : ");
            String dateA = reader.readLine().trim();

            System.out.print("Entrez la date de péremption : ");
            String dateP = reader.readLine().trim();

            System.out.print("Entrez l'ID de contrat : ");
            int idc = Integer.parseInt(reader.readLine().trim());
            
            Lot l = new Lot(id,prixA,qt,dateA,dateP,idc);
            
        	//Lot l = new Lot(3, 6.67, 40, "12-12-2024", "30-12-2024",1);
        	
        	gestion.insert(l, "ams_lot");

            System.out.println("Lot ajouté !");
        } 
        catch (Exception e) {System.out.println("Erreur lors de l'ajout du lot : " + e.getMessage());}
    }
    
    private static void updateLot(Gestion gestion, BufferedReader reader) 
    {
        try 
        {
        	System.out.println("\n--- Modifier un lot ---\n");
        	
            System.out.print("Entrez l'ID du lot à modifier : ");
            int id = Integer.parseInt(reader.readLine().trim());
            
            System.out.print("Entrez le prix d'achat : ");
            double prixA = Double.parseDouble(reader.readLine().trim());
            
            System.out.print("Entrez la quatité comandée : ");
            int qt = Integer.parseInt(reader.readLine().trim());

            System.out.print("Entrez la date d'achat : ");
            String dateA = reader.readLine().trim();

            System.out.print("Entrez la date de péremption : ");
            String dateP = reader.readLine().trim();

            System.out.print("Entrez l'ID de contrat : ");
            int idc = Integer.parseInt(reader.readLine().trim());
            
            Lot l = new Lot(id,prixA,qt,dateA,dateP,idc);
            
        	//Lot l = new Lot(3, 6.67, 70, "12-12-2024", "30-12-2024",1);
        	
            gestion.update(l, "ams_lot");

            System.out.println("Lot modifié !");
        } 
        catch (Exception e) {System.out.println("Erreur lors de la modification du lot : " + e.getMessage());}
    }
    
    private static void valideLot(Gestion gestion, BufferedReader reader) throws SQLException 
    {
    	try 
        {
        	System.out.println("\n--- Valider un lot ---\n");
        	
            System.out.print("Entrez l'ID du lot à valider : ");
            int id = Integer.parseInt(reader.readLine().trim());
            
            String query = "UPDATE ams_lot SET etat = 'Confirmé' WHERE id_lot = ?";
            
            try (PreparedStatement stmt = gestion.con.prepareStatement(query)) 
            {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            } 
            catch (SQLException e) 
            {
                System.out.println("Erreur lors de l'execution de la validation : " + e.getMessage());
                throw e;
            }

            System.out.println("Lot validé !");
        } 
    	catch (Exception e) {System.out.println("Erreur lors de la validation du lot : " + e.getMessage());}
    }
    
    private static void displayPrecommande(Gestion gestion, BufferedReader reader) throws SQLException
    { 	
    	String req = "SELECT *\n"
    			+ "FROM ams_lot\n"
    			+ "WHERE etat = 'Non confirmé';";
    	
    	gestion.displayReq(req);
    }
    
    private static void displayCommandeValide(Gestion gestion, BufferedReader reader) throws SQLException
    {
    	String req = "SELECT *\n"
    			+ "FROM ams_lot\n"
    			+ "WHERE etat = 'Confirmé';";
    	
    	gestion.displayReq(req);
    }
}
