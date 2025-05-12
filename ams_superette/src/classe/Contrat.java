package classe;

import java.util.HashMap;

import data.*;

public class Contrat implements IData 
{
	private String values;
	private String infos;
	private HashMap<String, fieldType> hm;
	private int id_contrat;
	private double prix_contrat;
	private String date_d_contrat;
	private String date_f_contrat;
	private int id_produit;
	private int id_fournisseur;
	private boolean actif;
	
	public Contrat(int idc, double prix, String dateD, String dateF, int idP, int idF)
	{
		id_contrat = idc;
		prix_contrat = prix;
		date_d_contrat = dateD;
		date_f_contrat = dateF;
		id_produit = idP;
		id_fournisseur = idF;
		actif = true;
		
		getStruct();
	}
	
	public Contrat() 
	{
		getStruct();
	}

	@Override
	public void getStruct() 
	{
		if (hm == null) {hm = new HashMap<String, fieldType>();}
		
		hm.put("id_contrat", fieldType.INT4);
		hm.put("prix_contrat", fieldType.NUMERIC);
		hm.put("date_d_contrat", fieldType.DATE);
		hm.put("date_f_contrat", fieldType.DATE);
		hm.put("id_produit", fieldType.INT4);
		hm.put("id_fournisseur", fieldType.INT4);
		hm.put("actif", fieldType.BOOLEAN);
		
		infos = "id_contrat,prix_contrat,date_d_contrat,date_f_contrat,id_produit,id_fournisseur,actif";
		values = id_contrat +  ", " + prix_contrat + ", '" + date_d_contrat + "', '" + date_f_contrat + "', " + id_produit + ", " + id_fournisseur+ ", '" + actif + "'";
	}
	
	public String getIdName() {
		return "id_contrat";
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

	@Override
	public String toString() {
		return "Contrat [id_contrat=" + id_contrat + ", prix_contrat=" + prix_contrat + ", date_d_contrat="
				+ date_d_contrat + ", date_f_contrat=" + date_f_contrat + ", id_produit=" + id_produit
				+ ", id_fournisseur=" + id_fournisseur + ", actif=" + actif + "]";
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public String getInfos() {
		return infos;
	}

	public void setInfos(String infos) {
		this.infos = infos;
	}

	public HashMap<String, fieldType> getMap() {
		return hm;
	}

	public void setHm(HashMap<String, fieldType> hm) {
		this.hm = hm;
	}

	public int getId_contrat() {
		return id_contrat;
	}

	public void setId_contrat(int id_contrat) {
		this.id_contrat = id_contrat;
	}

	public double getPrix_contrat() {
		return prix_contrat;
	}

	public void setPrix_contrat(double prix_contrat) {
		this.prix_contrat = prix_contrat;
	}

	public String getDate_d_contrat() {
		return date_d_contrat;
	}

	public void setDate_d_contrat(String date_d_contrat) {
		this.date_d_contrat = date_d_contrat;
	}

	public String getDate_f_contrat() {
		return date_f_contrat;
	}

	public void setDate_f_contrat(String date_f_contrat) {
		this.date_f_contrat = date_f_contrat;
	}

	public int getId_produit() {
		return id_produit;
	}

	public void setId_produit(int id_produit) {
		this.id_produit = id_produit;
	}

	public int getId_fournisseur() {
		return id_fournisseur;
	}

	public void setId_fournisseur(int id_fournisseur) {
		this.id_fournisseur = id_fournisseur;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

}