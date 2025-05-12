package gestion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;

import classe.Vente;
import connection.Connexion;

public class GestionResultat extends Gestion 
{
	public GestionResultat(Connexion c) 
	{
		super(c);
		// TODO Auto-generated constructor stub
	}
	
	public static void gestionResultat(Gestion g, BufferedReader bf)
    {
    	try 
    	{
            String command = "";

            while (true) 
            {
            	System.out.println("\n----- Résultats ----------\n");
	            System.out.println("1- Afficher le résultat journalier");
	            System.out.println("2- Afficher le résultat hebdommadaire");
	            System.out.println("3- Afficher le résultat mensuel");
	            System.out.println("4- Afficher le résultat annuel");
	            System.out.println("\nEntrez (R) pour retourner au menu précédent");
	   
	            command = bf.readLine().trim().toUpperCase();
	
	            switch (command) {
	                case "1":
	                    displayResJour(g, bf);
	                    break;
	                    
	                case "2":
	                    displayResHebd(g, bf);
	                    break;
	                    
	                case "3":
	                    displayResMens(g, bf);
	                    break;
	                    
	                case "4":
	                    displayResAnn(g, bf);
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
	
	
		/*********** Résultat Journalier **********/
	
	   private static void displayResJour(Gestion gestion, BufferedReader reader) throws SQLException
	    {
	    	displayVentJour(gestion, reader);
	    	displayBenefJour(gestion, reader);
	    	displayClassJour(gestion, reader);
	    }
	   
	   private static void displayVentJour(Gestion gestion, BufferedReader reader) throws SQLException
	    {	    	
	    	String req = "SELECT v.date_vente,\n"
	    			+ "       v.id_vente,\n"
	    			+ "       v.id_lot,\n"
	    			+ "       v.prix_vente,\n"
	    			+ "       v.quantite_vendue,\n"
	    			+ "       (v.prix_vente - l.prix_achat) * v.quantite_vendue AS benefice\n"
	    			+ "FROM ams_vente v\n"
	    			+ "JOIN ams_lot l ON v.id_lot = l.id_lot\n"
	    			+ "WHERE v.date_vente = CURRENT_DATE;";
	    	
	    	gestion.displayReq(req);
	    }
	   
	   private static void displayBenefJour(Gestion gestion, BufferedReader reader) throws SQLException
	    {	    	
	    	String req = "SELECT date_vente, \n"
	    			+ "       SUM((prix_vente - l.prix_achat) * quantite_vendue) AS benefice_total\n"
	    			+ "FROM ams_vente v\n"
	    			+ "JOIN ams_lot l ON v.id_lot = l.id_lot\n"
	    			+ "WHERE date_vente = CURRENT_DATE\n"
	    			+ "GROUP BY date_vente;";
	    	
	    	gestion.displayReq(req);
	    }
	   
	   private static void displayClassJour(Gestion gestion, BufferedReader reader) throws SQLException
	    {	    	
	    	String req = "SELECT v.id_vente,\n"
	    			+ "       v.id_lot,\n"
	    			+ "       v.prix_vente,\n"
	    			+ "       v.quantite_vendue,\n"
	    			+ "       (v.prix_vente - l.prix_achat) * v.quantite_vendue AS benefice\n"
	    			+ "FROM ams_vente v\n"
	    			+ "JOIN ams_lot l ON v.id_lot = l.id_lot\n"
	    			+ "WHERE v.date_vente = CURRENT_DATE\n"
	    			+ "ORDER BY benefice DESC, v.quantite_vendue DESC\n"
	    			+ "LIMIT 10;";
	    	
	    	gestion.displayReq(req);
	    }
	   
	   /*********** Résultat Hebdomadaire **********/
	   
	   private static void displayResHebd(Gestion gestion, BufferedReader reader) throws SQLException
	    {
	    	displayVentHebd(gestion, reader);
	    	displayBenefHebd(gestion, reader);
	    	displayClassHebd(gestion, reader);
	    }
	   
	   private static void displayVentHebd(Gestion gestion, BufferedReader reader) throws SQLException
	    {	    	
	    	String req = "SELECT v.date_vente,\n"
	    			+ "       v.id_vente,\n"
	    			+ "       v.id_lot,\n"
	    			+ "       v.prix_vente,\n"
	    			+ "       v.quantite_vendue,\n"
	    			+ "       (v.prix_vente - l.prix_achat) * v.quantite_vendue AS benefice\n"
	    			+ "FROM ams_vente v\n"
	    			+ "JOIN ams_lot l ON v.id_lot = l.id_lot\n"
	    			+ "WHERE DATE_TRUNC('week', v.date_vente) = DATE_TRUNC('week', CURRENT_DATE)\n"
	    			+ "ORDER BY v.date_vente ASC;";
	    	
	    	gestion.displayReq(req);
	    }
	   
	   private static void displayBenefHebd(Gestion gestion, BufferedReader reader) throws SQLException
	    {	    	
	    	String req = "SELECT SUM((v.prix_vente - l.prix_achat) * v.quantite_vendue) AS benefice_total\n"
	    			+ "FROM ams_vente v\n"
	    			+ "JOIN ams_lot l ON v.id_lot = l.id_lot\n"
	    			+ "WHERE DATE_TRUNC('week', v.date_vente) = DATE_TRUNC('week', CURRENT_DATE);";
	    	
	    	gestion.displayReq(req);
	    }
	   
	   private static void displayClassHebd(Gestion gestion, BufferedReader reader) throws SQLException
	    {	    	
	    	String req = "SELECT v.id_vente,\n"
	    			+ "       v.id_lot,\n"
	    			+ "       v.prix_vente,\n"
	    			+ "       v.quantite_vendue,\n"
	    			+ "       (v.prix_vente - l.prix_achat) * v.quantite_vendue AS benefice\n"
	    			+ "FROM ams_vente v\n"
	    			+ "JOIN ams_lot l ON v.id_lot = l.id_lot\n"
	    			+ "WHERE DATE_TRUNC('week', v.date_vente) = DATE_TRUNC('week', CURRENT_DATE)\n"
	    			+ "ORDER BY benefice DESC, v.quantite_vendue DESC\n"
	    			+ "LIMIT 10;";
	    	
	    	gestion.displayReq(req);
	    }
	   
	   /*********** Résultat Mensuel **********/
	   
	   private static void displayResMens(Gestion gestion, BufferedReader reader) throws SQLException
	    {
	    	displayVentMens(gestion, reader);
	    	displayBenefMens(gestion, reader);
	    	displayClassMens(gestion, reader);
	    }
	   
	   private static void displayVentMens(Gestion gestion, BufferedReader reader) throws SQLException
	    {	    	
	    	String req = "SELECT v.date_vente,\n"
	    			+ "       v.id_vente,\n"
	    			+ "       v.id_lot,\n"
	    			+ "       v.prix_vente,\n"
	    			+ "       v.quantite_vendue,\n"
	    			+ "       (v.prix_vente - l.prix_achat) * v.quantite_vendue AS benefice\n"
	    			+ "FROM ams_vente v\n"
	    			+ "JOIN ams_lot l ON v.id_lot = l.id_lot\n"
	    			+ "WHERE DATE_TRUNC('month', v.date_vente) = DATE_TRUNC('month', CURRENT_DATE)\n"
	    			+ "ORDER BY v.date_vente ASC;";
	    	
	    	gestion.displayReq(req);
	    }
	   
	   private static void displayBenefMens(Gestion gestion, BufferedReader reader) throws SQLException
	    {	    	
	    	String req = "SELECT SUM((v.prix_vente - l.prix_achat) * v.quantite_vendue) AS benefice_total\n"
	    			+ "FROM ams_vente v\n"
	    			+ "JOIN ams_lot l ON v.id_lot = l.id_lot\n"
	    			+ "WHERE v.date_vente BETWEEN CURRENT_DATE - INTERVAL '1 month' AND CURRENT_DATE;";
	    	
	    	gestion.displayReq(req);
	    }
	   
	   private static void displayClassMens(Gestion gestion, BufferedReader reader) throws SQLException
	    {	    	
	    	String req = "SELECT v.id_vente,\n"
	    			+ "       v.id_lot,\n"
	    			+ "       v.prix_vente,\n"
	    			+ "       v.quantite_vendue,\n"
	    			+ "       (v.prix_vente - l.prix_achat) * v.quantite_vendue AS benefice\n"
	    			+ "FROM ams_vente v\n"
	    			+ "JOIN ams_lot l ON v.id_lot = l.id_lot\n"
	    			+ "WHERE v.date_vente BETWEEN CURRENT_DATE - INTERVAL '1 month' AND CURRENT_DATE\n"
	    			+ "ORDER BY benefice DESC, v.quantite_vendue DESC\n"
	    			+ "LIMIT 10;";
	    	
	    	gestion.displayReq(req);
	    }
	   
	   /*********** Résultat Annuel **********/
	   
	   private static void displayResAnn(Gestion gestion, BufferedReader reader) throws SQLException
	    {
	    	displayVentAnn(gestion, reader);
	    	displayBenefAnn(gestion, reader);
	    	displayClassAnn(gestion, reader);
	    }
	   
	   private static void displayVentAnn(Gestion gestion, BufferedReader reader) throws SQLException
	    {	    	
	    	String req = "SELECT v.date_vente,\n"
	    			+ "       v.id_vente,\n"
	    			+ "       v.id_lot,\n"
	    			+ "       v.prix_vente,\n"
	    			+ "       v.quantite_vendue,\n"
	    			+ "       (v.prix_vente - l.prix_achat) * v.quantite_vendue AS benefice\n"
	    			+ "FROM ams_vente v\n"
	    			+ "JOIN ams_lot l ON v.id_lot = l.id_lot\n"
	    			+ "WHERE DATE_TRUNC('year', v.date_vente) = DATE_TRUNC('year', CURRENT_DATE)\n"
	    			+ "ORDER BY v.date_vente ASC;";
	    	
	    	gestion.displayReq(req);
	    }
	   
	   private static void displayBenefAnn(Gestion gestion, BufferedReader reader) throws SQLException
	    {	    	
	    	String req = "SELECT SUM((v.prix_vente - l.prix_achat) * v.quantite_vendue) AS benefice_total\n"
	    			+ "FROM ams_vente v\n"
	    			+ "JOIN ams_lot l ON v.id_lot = l.id_lot\n"
	    			+ "WHERE DATE_TRUNC('year', v.date_vente) = DATE_TRUNC('year', CURRENT_DATE);";
	    	
	    	gestion.displayReq(req);
	    }
	   
	   private static void displayClassAnn(Gestion gestion, BufferedReader reader) throws SQLException
	    {	    	
	    	String req = "SELECT v.id_vente,\n"
	    			+ "       v.id_lot,\n"
	    			+ "       v.prix_vente,\n"
	    			+ "       v.quantite_vendue,\n"
	    			+ "       (v.prix_vente - l.prix_achat) * v.quantite_vendue AS benefice\n"
	    			+ "FROM ams_vente v\n"
	    			+ "JOIN ams_lot l ON v.id_lot = l.id_lot\n"
	    			+ "WHERE DATE_TRUNC('year', v.date_vente) = DATE_TRUNC('year', CURRENT_DATE)\n"
	    			+ "ORDER BY benefice DESC, v.quantite_vendue DESC\n"
	    			+ "LIMIT 10;";
	    	
	    	gestion.displayReq(req);
	    }
}