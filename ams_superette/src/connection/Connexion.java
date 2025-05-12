package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Connexion 
{
	private Connection con;
	
	public Connexion()
	{
		con = null;
	}
	
	public void connect() 
	{
	    try {
	
	        Class.forName("org.postgresql.Driver");
	
	        String dataBase = "etd";
	        String url = "jdbc:postgresql://pedago.univ-avignon.fr:5432/" + dataBase;

	        Properties props = new Properties();
	        props.setProperty("user", "uapv2002989");
	        props.setProperty("password", "Z72pAA");
	        
	        con = DriverManager.getConnection(url, props);
	     
	    } 
	    catch (ClassNotFoundException e) 
	    {
	        System.out.println("Erreur : Driver PostgreSQL non trouvé.");
	        e.printStackTrace();
	    } 
	    catch (SQLException e) 
	    {
	        System.out.println("Erreur de connexion à la base de données.");
	        e.printStackTrace();
	    }
	}
	
	public Connection getCon() 
	{
		return con;
	}

	public void disconnect()
	{
		try 
		{
			if (con != null || !con.isClosed())
			{
				con.close();
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void executeCreate(String query)
	{
		try
		{
			Statement stmt = con.createStatement();
			
			System.out.println("Query : " + query + " execution...");
			
			stmt.executeUpdate(query);
			
			stmt.close();
		}
		catch(Exception e)
		{
			System.out.println("Erreur création!");
		}
	}

}