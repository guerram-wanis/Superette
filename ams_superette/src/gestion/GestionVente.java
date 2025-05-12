package gestion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;

import classe.Vente;
import connection.Connexion;

public class GestionVente extends Gestion 
{
	public GestionVente(Connexion c) 
	{
		super(c);
		// TODO Auto-generated constructor stub
	}
	
	public static void gestionVente(Gestion g, BufferedReader bf)
    {
    	try
    	{
            String command = "";
            
            while (true) 
            {
            	System.out.println("\n---------- Gestion des Ventes ----------");
	            System.out.println("1- Enregistrer une vente");
	            System.out.println("2- Afficher l'historique des ventes");
	            System.out.println("\nEntrez (R) pour retourner au menu précédent");
	            
	            System.out.println("\n\n----------------------------------------\n");
	   
	            command = bf.readLine().trim().toUpperCase();
	
	            switch (command) 
	            {
	                case "1":
	                    insertVente(g, bf);
	                    break;
	                    
	                case "2":
	                    displayVente(g, bf);
	                    break;
	                    
	                case "R":
	                    System.out.println("Retour à l'acceuil.\n");
	                    return;
	
	                default:
	                    System.out.println("Commande inconnue. Entrez 1, 2 ou R.");
                }
            }
        } 
    	catch (Exception e) 
    	{
            System.out.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        }
    }
	
	private static void insertVente(Gestion gestion, BufferedReader reader) 
	{
        try 
        {
        	System.out.println("\n--- Enregistrer une vente ---\n");
        	
            System.out.print("Entrez l'ID : ");
            int id = Integer.parseInt(reader.readLine().trim());

            System.out.print("Entrez la date de la vente : ");
            String date = reader.readLine().trim();
            
            System.out.print("Entrez le prix de vente : ");
            double prix = Double.parseDouble(reader.readLine().trim());
            
            System.out.print("Entrez l'ID du lot : ");
            int idl = Integer.parseInt(reader.readLine().trim());
            
            System.out.print("Entrez la quantité vendue : ");
            int qt = Integer.parseInt(reader.readLine().trim());

        	Vente v = new Vente(id,date,prix,idl,qt);
        	
        	gestion.insert(v, "ams_vente");

            System.out.println("Vente enregistré !");
        } 
        catch (Exception e) {System.out.println("Erreur lors de l'insertion de la vente : " + e.getMessage());}
    }
	
	//affiche l'historique des ventes
	private static void displayVente(Gestion gestion, BufferedReader reader) throws SQLException
    {
    	String req = "SELECT *\n"
    			+ "FROM ams_vente;";
    	
    	gestion.displayReq(req);
    }
}
