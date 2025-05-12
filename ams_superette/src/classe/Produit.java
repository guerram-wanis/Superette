package classe;

import java.util.HashMap;

import data.IData;
import data.fieldType;

public class Produit implements IData
{
	private String values;
	private String infos;
	
	public String getInfos() {
		return infos;
	}

	public void setInfos(String infos) {
		this.infos = infos;
	}

	public void setValues(String values) {
		this.values = values;
	}

	private HashMap<String, fieldType> hm;
	private int id_produit;
	private int id_lot;
	private String nom_produit;
	private String description_produit;
	private String categorie_produit;
	private double prix_produit;
	
	public Produit(int idp, int idl, String n, String d, String c, double p)
	{
		id_produit = idp;
		id_lot = idl;
		nom_produit = n;
		description_produit = d;
		categorie_produit = c;
		prix_produit = p;
		
		getStruct();
	}
	
	@Override
	public void getStruct() 
	{
		if (hm == null) {hm = new HashMap<String, fieldType>();}
		
		hm.put("id_produit", fieldType.INT4);
		hm.put("id_lot", fieldType.INT4);
		hm.put("nom_produit", fieldType.VARCHAR);
		hm.put("description_produit", fieldType.VARCHAR);
		hm.put("categorie_produit", fieldType.VARCHAR);
		hm.put("prix_produit", fieldType.NUMERIC);
		
		values = id_produit + ", " + id_lot + ", '" + nom_produit + "', '" + description_produit + "', '" + categorie_produit + "', " + prix_produit;
	}

	@Override
	public String getValues() 
	{
		return values;
	}

	@Override
	public HashMap<String, fieldType> getMap() 
	{
		return hm;
	}

	@Override
	public boolean check(HashMap<String, fieldType> tableStruct) 
	{
		for(String n : hm.keySet()) 
		{
			if(!tableStruct.containsKey(n))
				return false;
		
			fieldType f = hm.get(n);
			
			if (! f.equals(tableStruct.get(n)))
				return false;
		}
		
		return true;
	}
	
	public String toString()
	{
		String ts = nom_produit + " [ " + id_produit + ", " + id_lot  + ", " + description_produit + ", " + categorie_produit + ", " + prix_produit + " ]";
		return ts;
	}

	public int getId_produit() {
		return id_produit;
	}

	public void setId_produit(int id_produit) {
		this.id_produit = id_produit;
		getStruct();
	}
	
	public String getIdName() {
		return "id_produit";
	}

	public int getId_lot() {
		return id_lot;
	}

	public void setId_lot(int id_lot) {
		this.id_lot = id_lot;
		getStruct();
	}

	public String getNom_produit() {
		return nom_produit;
	}

	public void setNom_produit(String nom_produit) {
		this.nom_produit = nom_produit;
		getStruct();
	}

	public String getDescription_produit() {
		return description_produit;
	}

	public void setDescription_produit(String description_produit) {
		this.description_produit = description_produit;
		getStruct();
	}

	public String getCategorie_produit() {
		return categorie_produit;
	}

	public void setCategorie_produit(String categorie_produit) {
		this.categorie_produit = categorie_produit;
		getStruct();
	}

	public double getPrix_produit() {
		return prix_produit;
	}

	public void setPrix_produit(double prix_produit) {
		this.prix_produit = prix_produit;
		getStruct();
	}
}