package gestion;

import connection.*;
import java.beans.Statement;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

import data.IData;
import data.fieldType;

public class Gestion 
{
	protected Connection con;
	
	public Gestion(Connexion c)
	{
		con = c.getCon(); 
	}
	
	public void gestionAcceuil()
	{
		try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) 
		{
            String command = "";

            while (true) 
            {
            	System.out.println("\n---------- Acceuil ----------\n");
				System.out.println("1- Gestion des fournisseurs");
				System.out.println("2- Gestion des stocks");
				System.out.println("3- Gestion des commandes");
				System.out.println("4- Gestion des ventes");
				System.out.println("5- Résultats");
				
				System.out.println("\nFermer l'application (EXIT)");
				
				System.out.println("\n----------------------------------------\n");
				
				command = bf.readLine().trim().toUpperCase();
				
				switch (command) 
				{
					case "1":
					    GestionFournisseur.gestionFournissseur(this, bf);
					    break;
					
					case "2":
						GestionStock.gestionStock(this, bf);
				        break;
					
					case "3":
					    GestionCommande.gestionCommande(this, bf);
					    break;
					
					case "4":
				        GestionVente.gestionVente(this, bf);
				        break;
				
					case "5":
					    GestionResultat.gestionResultat(this, bf);
					    break;
					
					case "EXIT":
						System.out.println("Fermeture de l'application.");
						return;
					
					default:
					    System.out.println("Commande inconnue. Entrez 1, 2, 3, 4 5, ou EXIT.");
                }
            }
        } 
		catch (Exception e) 
		{
            System.out.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        } 
	}
	
	protected HashMap<String, fieldType> structTable(String table, boolean display) throws SQLException
	{
		HashMap<String, fieldType> hm = new HashMap<String, fieldType>();
		
		String reqSql = "SELECT column_name, data_type FROM information_schema.columns WHERE table_name = '" + table + "'";
		
		java.sql.Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery(reqSql);
		
		while (rs.next())
		{			
			String nomCol = rs.getString("column_name");
			String typeCol = rs.getString("data_type");
			
			fieldType ft = null;
			
			if(typeCol.equals("integer")) {ft = fieldType.INT4;}
			else if(typeCol.equals("character varying")) {ft = fieldType.VARCHAR;}
			else if(typeCol.equals("numeric")) {ft = fieldType.NUMERIC;}
			else if(typeCol.equals("boolean")) {ft = fieldType.BOOLEAN;}
			else if(typeCol.equals("date")) {ft = fieldType.DATE;}
			
			if (ft != null) {hm.put(nomCol, ft);}
		}
		
		if (display) {System.out.println("Structure de la table \"" + table + "\" : " + hm);}
		
		return hm;	
	}
	
	protected void displayTablev1(String table) throws SQLException
	{
		String reqSql = "";
		if (table == "ams_fournisseur") {reqSql = "SELECT * FROM " + table + " WHERE actif = TRUE";}
		else {reqSql = "SELECT * FROM " + table;}
		
		PreparedStatement stmt = con.prepareStatement(reqSql);
		
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData rsM = rs.getMetaData();
		
		int nbCol = rsM.getColumnCount();
		
		for (int i = 1; i <= nbCol; i++)
		{
			System.out.print(rsM.getColumnName(i) + "\t");
		}
		System.out.println();
		
		while (rs.next())
		{
			for (int i = 1; i <= nbCol; i++)
			{
				System.out.print(rs.getObject(i) + "\t");
			}
			System.out.println();
		}
	}
	
	protected void displayTable(String table) throws SQLException 
	{
	    String reqSql = "";
	    if ("ams_fournisseur".equals(table)) {reqSql = "SELECT * FROM " + table + " WHERE actif = TRUE";} 
	    else {reqSql = "SELECT * FROM " + table;}
	    
	    PreparedStatement stmt = con.prepareStatement(reqSql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	    ResultSet rs = stmt.executeQuery();
	    ResultSetMetaData rsM = rs.getMetaData();

	    int nbCol = rsM.getColumnCount();

	    // Déterminer les longueurs maximales pour chaque colonne
	    int[] colWidths = new int[nbCol];
	    
	    for (int i = 1; i <= nbCol; i++) 
	    {
	        colWidths[i - 1] = rsM.getColumnName(i).length(); // Longueur du nom de la colonne
	    }

	    // Parcourir les résultats pour trouver les largeurs maximales
	    while (rs.next()) 
	    {
	        for (int i = 1; i <= nbCol; i++) 
	        {
	            int valueLength = rs.getObject(i) != null ? rs.getObject(i).toString().length() : 4; // 4 pour "NULL"
	            
	            if (valueLength > colWidths[i - 1]) {colWidths[i - 1] = valueLength;}
	        }
	    }

	    // Réinitialiser le ResultSet pour afficher les résultats
	    rs.beforeFirst();

	    // Afficher les noms des colonnes avec format
	    for (int i = 1; i <= nbCol; i++) 
	    {
	        System.out.printf("%-" + (colWidths[i - 1] + 2) + "s", rsM.getColumnName(i));
	    }
	    
	    System.out.println();
	    System.out.println("-".repeat(java.util.Arrays.stream(colWidths).sum() + 2 * nbCol)); // Ligne de séparation

	    // Afficher les lignes du tableau
	    while (rs.next()) 
	    {
	        for (int i = 1; i <= nbCol; i++) 
	        {
	            System.out.printf("%-" + (colWidths[i - 1] + 2) + "s", rs.getObject(i) != null ? rs.getObject(i) : "NULL");
	        }
	        System.out.println();
	    }
	    
	    System.out.println("\n");
	    
	    rs.close();
	    stmt.close();
	}
	
	protected void insert(IData data, String table) throws SQLException
	{
		
		HashMap<String, fieldType> tableStruct = structTable(table, false);
		
		if (data.check(tableStruct))
		{
			
			String reqVerif = "SELECT * FROM " + table + " WHERE " + data.getIdName() + " = ?";
			
			PreparedStatement verifStmt = con.prepareStatement(reqVerif);
			
			
			String[] dataVal = data.getValues().split(",");
			
			int id = Integer.valueOf(dataVal[0]);
			
			verifStmt.setInt(1,id);
			
			
			ResultSet rs = verifStmt.executeQuery();
			
			if (!rs.next()) 
			{
				String reqAjout = "INSERT INTO " + table +" VALUES ("+ data.getValues() +")";
				
				try (PreparedStatement ajoutStmt = con.prepareStatement(reqAjout)) 
				{
				    ajoutStmt.executeUpdate();
				    ajoutStmt.close();
				}			
			}
			
			rs.close();
			verifStmt.close();
		}
	}
	
	protected void update(IData data, String table) throws SQLException
	{
		
		HashMap<String, fieldType> tableStruct = structTable(table, false);

		
		if (data.check(tableStruct))
		{
			String reqVerif = "SELECT * FROM " + table + " WHERE " + data.getIdName() + " = ?";
			
			PreparedStatement verifStmt = con.prepareStatement(reqVerif);
			
			
			String[] dataVal = data.getValues().split(",");
			
			int id = Integer.valueOf(dataVal[0]);
			
			verifStmt.setInt(1,id);
			
			
			ResultSet rs = verifStmt.executeQuery();
			
			if (rs.next())
			{
				String set = " ";
				String[] col = data.getInfos().split(",");			
				
				for (int i = 0; i < col.length; i++)
				{
					if (i == col.length-1) {set += col[i] + " =" + dataVal[i+1] + " WHERE "+ data.getIdName() + " = " + dataVal[0];}
					
					else {set += col[i] + " =" + dataVal[i+1] + ",";} 
				}
				
				String reqModif = "UPDATE " + table + " SET" + set ;
				
				PreparedStatement modifStmt = con.prepareStatement(reqModif);
	            
	            modifStmt.executeUpdate();
	            
	            modifStmt.close();
			}
			
			else {System.out.println("Le fournissseur n'existe pas !");}
			
			rs.close();
			verifStmt.close();
		}
	}
	
	protected void archiver(IData data, String table, int id) throws SQLException
	{
		HashMap<String, fieldType> tableStruct = structTable(table, false);
		
		if (data.check(tableStruct))
		{
			String reqVerif = "SELECT * FROM " + table + " WHERE " + data.getIdName() + " = ?";
			
			PreparedStatement verifStmt = con.prepareStatement(reqVerif);
				
			verifStmt.setInt(1,id);
		
			ResultSet rs = verifStmt.executeQuery();
			
			if (rs.next())
			{	
				String set = " ";
				String[] col = data.getInfos().split(",");
				
				HashMap<String, fieldType> h = data.getMap();
				
				/*for (int i = 0; i < col.length; i++)
				{
					if (i == col.length-1) {set += col[i] + " = FALSE WHERE "+ data.getIdName() + " = " + id;}
				}*/
				
				set +=  "actif = FALSE WHERE "+ data.getIdName() + " = " + id;
				
				String reqModif = "UPDATE " + table + " SET" + set ;
				
				PreparedStatement modifStmt = con.prepareStatement(reqModif);
	            
	            modifStmt.executeUpdate();
	            
	            modifStmt.close();
			}
			
			else {System.out.println("Le fournissseur n'existe pas !");}
			
			rs.close();
			verifStmt.close();
		}
	}
	
	protected void exeReq(String req) throws SQLException
	{	
		PreparedStatement stmt = con.prepareStatement(req);
		
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData rsM = rs.getMetaData();
		
		int nbCol = rsM.getColumnCount();
		
		for (int i = 1; i <= nbCol; i++)
		{
			System.out.print(rsM.getColumnName(i) + "\t");
		}
		System.out.println();
		
		while (rs.next())
		{
			for (int i = 1; i <= nbCol; i++)
			{
				System.out.print(rs.getObject(i) + "\t");
			}
			System.out.println();
		}
	}
	
	protected void displayReq(String req) throws SQLException
	{
		PreparedStatement stmt = con.prepareStatement(req, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	    ResultSet rs = stmt.executeQuery();
	    ResultSetMetaData rsM = rs.getMetaData();

	    int nbCol = rsM.getColumnCount();

	    // Déterminer les longueurs maximales pour chaque colonne
	    int[] colWidths = new int[nbCol];
	    
	    for (int i = 1; i <= nbCol; i++) 
	    {
	        colWidths[i - 1] = rsM.getColumnName(i).length(); // Longueur du nom de la colonne
	    }

	    // Parcourir les résultats pour trouver les largeurs maximales
	    while (rs.next()) 
	    {
	        for (int i = 1; i <= nbCol; i++) 
	        {
	            int valueLength = rs.getObject(i) != null ? rs.getObject(i).toString().length() : 4; // 4 pour "NULL"
	            
	            if (valueLength > colWidths[i - 1]) {colWidths[i - 1] = valueLength;}
	        }
	    }

	    // Réinitialiser le ResultSet pour afficher les résultats
	    rs.beforeFirst();

	    // Afficher les noms des colonnes avec format
	    for (int i = 1; i <= nbCol; i++) {
	        System.out.printf("%-" + (colWidths[i - 1] + 2) + "s", rsM.getColumnName(i));
	    }
	    System.out.println();
	    System.out.println("-".repeat(java.util.Arrays.stream(colWidths).sum() + 2 * nbCol)); // Ligne de séparation

	    // Afficher les lignes du tableau
	    while (rs.next()) {
	        for (int i = 1; i <= nbCol; i++) {
	            System.out.printf("%-" + (colWidths[i - 1] + 2) + "s", rs.getObject(i) != null ? rs.getObject(i) : "NULL");
	        }
	        System.out.println();
	    }
	    System.out.println("\n");
	    rs.close();
	    stmt.close();
	}
}
