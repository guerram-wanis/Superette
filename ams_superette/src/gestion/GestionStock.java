package gestion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;

import classe.Fournisseur;
import classe.Produit;
import connection.Connexion;

public class GestionStock extends Gestion
{

	public GestionStock(Connexion c) 
	{
		super(c);
		// TODO Auto-generated constructor stub
	}
	
	//Menu qui permet d'acceder aux différentes fonctionnalités qui permettent de gérer les stocks
	public static void gestionStock(Gestion g, BufferedReader bf)
    {
    	try 
    	{
            String command = "";

            while (true) 
            {
            	System.out.println("\n---------- Gestion des Stocks ----------\n");
            	System.out.println("1- Ajouter un produit");
	            System.out.println("2- Afficher les produits disponibles en stock");
	            System.out.println("3- Afficher la liste des lots arrivant à échéance");
	            System.out.println("\nEntrez (R) pour retourner au menu précédent");
	            
	            System.out.println("\n\n----------------------------------------\n");
	            
	            //Saisie de la fonctiannalité à untiliser par l'utiliateur 
	            command = bf.readLine().trim().toUpperCase();
	
	            switch (command) 
	            {
	            	case "1":
	            		insertProduct(g, bf);
	            		break;
                    
	                case "2":
	                    displayProduitsDisponibles(g, bf);
	                    break;
	
	                case "3":
	                    displayProduitsArrEcheance(g, bf);
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
	
	//Affiche les produits en stock
	private static void displayProduitsDisponibles(Gestion gestion, BufferedReader reader) throws SQLException
    {
    	String req = "SELECT p.id_produit, \n"
    			+ "       p.nom_produit, \n"
    			+ "       p.description_produit, \n"
    			+ "       p.categorie_produit, \n"
    			+ "       p.prix_vente_produit, \n"
    			+ "       l.quantite_lot - COALESCE(SUM(v.quantite_vendue), 0) AS quantite_disponible,\n"
    			+ "       l.date_achat, \n"
    			+ "       l.date_peremption, \n"
    			+ "       l.etat\n"
    			+ "\n"
    			+ "FROM ams_produit p\n"
    			+ "\n"
    			+ "JOIN ams_contrat c ON p.id_produit = c.id_produit\n"
    			+ "\n"
    			+ "JOIN ams_lot l ON c.id_contrat = l.id_contrat\n"
    			+ "\n"
    			+ "LEFT JOIN (\n"
    			+ "    SELECT id_lot, SUM(quantite_vendue) AS quantite_vendue\n"
    			+ "    FROM ams_vente\n"
    			+ "    GROUP BY id_lot\n"
    			+ ") v ON l.id_lot = v.id_lot\n"
    			+ "\n"
    			+ "GROUP BY p.id_produit, p.nom_produit, p.description_produit, p.categorie_produit,\n"
    			+ "        p.prix_vente_produit, l.quantite_lot, l.date_achat, l.date_peremption, l.etat\n"
    			+ "\n"
    			+ "HAVING l.quantite_lot - COALESCE(SUM(v.quantite_vendue), 0) > 0\n"
    			+ "   AND (l.date_peremption IS NULL OR l.date_peremption > CURRENT_DATE);";
    	
    	gestion.displayReq(req);
    }
	
	//Affiche les produits qui arrivent bientôt à échéance  
	private static void displayProduitsArrEcheance(Gestion gestion, BufferedReader reader) throws SQLException
    {
    	String req = "SELECT p.id_produit, \n"
    			+ "       p.nom_produit, \n"
    			+ "       p.description_produit, \n"
    			+ "       p.categorie_produit, \n"
    			+ "       p.prix_vente_produit, \n"
    			+ "       l.quantite_lot,\n"
    			+ "       l.date_achat, \n"
    			+ "       l.date_peremption, \n"
    			+ "       l.etat\n"
    			+ "FROM ams_produit p\n"
    			+ "JOIN ams_contrat c ON p.id_produit = c.id_produit\n"
    			+ "JOIN ams_lot l ON c.id_contrat = l.id_contrat\n"
    			+ "WHERE l.date_peremption IS NOT NULL \n"
    			+ "  AND l.date_peremption > CURRENT_DATE \n"
    			+ "  AND l.date_peremption <= CURRENT_DATE + INTERVAL '30 days'\n"
    			+ "ORDER BY l.date_peremption ASC;";
    	
    	gestion.displayReq(req);
    }
	
	//Ajoute un produit
	private static void insertProduct(Gestion gestion, BufferedReader reader) {
        try 
        {
        	System.out.println("\n--- Ajouter un produit ---\n");
        	
            System.out.print("Entrez l'ID : ");
            int id = Integer.parseInt(reader.readLine().trim());
            
            System.out.print("Entrez l'ID de lot : ");
            int idl = Integer.parseInt(reader.readLine().trim());

            System.out.print("Entrez le nom du produit : ");
            String nom = reader.readLine().trim();

            System.out.print("Entrez la description du produit : ");
            String desc = reader.readLine().trim();

            System.out.print("Entrez la categorie du produit : ");
            String cate = reader.readLine().trim();

            System.out.print("Entrez le prix de vente : ");
            double prix = Double.parseDouble(reader.readLine().trim());
        	
            Produit produit = new Produit(id,idl,nom,desc,cate,prix);
            
        	//Produit produit = new Produit(11, 9, "ok", "many", "je", 0.01);
            
        	gestion.insert(produit, "ams_produit");

            System.out.println("Produit ajouté !");
        } 
        catch (Exception e) {System.out.println("Erreur lors de l'ajout du produit : " + e.getMessage());}
    }
	
}
