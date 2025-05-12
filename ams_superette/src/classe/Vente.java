package classe;

import java.util.HashMap;

import data.*;

public class Vente implements IData 
{
	private String values;
	private String infos;
	private HashMap<String, fieldType> hm;
	private int id_vente;
	private String date_vente;
	private double prix_vente;
	private int id_lot;
	private int cleClient;
	private int quantite_vendue;
	
	
	public Vente(int idv, String dateV, double prix,int idl, int qt)
	{
		id_lot = idl;
		quantite_vendue = qt;
		prix_vente = prix;
		date_vente = dateV;
		id_lot = idl;
		
		getStruct();
	}
	
	public Vente() 
	{
		getStruct();
	}

	@Override
	public void getStruct() 
	{
		if (hm == null) {hm = new HashMap<String, fieldType>();}
		
		hm.put("id_vente", fieldType.INT4);
		hm.put("id_lot", fieldType.INT4);
		hm.put("prix_vente", fieldType.NUMERIC);
		hm.put("quantite_vendue", fieldType.INT4);
		hm.put("date_vente", fieldType.DATE);
		hm.put("cleClient", fieldType.INT4);
		
		infos = "date_vente,prix_vente,id_lot,cleClient,quantite_vendue";
		values = id_vente +  ", '" + date_vente + "', " + prix_vente + ", " + id_lot + ", " + cleClient + ", " + quantite_vendue;
	}
	
	public String getIdName() {
		return "id_vente";
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
		return "Vente [id_vente=" + id_vente + ", date_vente=" + date_vente + ", prix_vente=" + prix_vente + ", id_lot="
				+ id_lot + ", cleClient=" + cleClient + ", quantite_vendue=" + quantite_vendue + "]";
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

	public int getId_vente() {
		return id_vente;
	}

	public void setId_vente(int id_vente) {
		this.id_vente = id_vente;
	}

	public String getDate_vente() {
		return date_vente;
	}

	public void setDate_vente(String date_vente) {
		this.date_vente = date_vente;
	}

	public double getPrix_vente() {
		return prix_vente;
	}

	public void setPrix_vente(double prix_vente) {
		this.prix_vente = prix_vente;
	}

	public int getId_lot() {
		return id_lot;
	}

	public void setId_lot(int id_lot) {
		this.id_lot = id_lot;
	}

	public int getCleClient() {
		return cleClient;
	}

	public void setCleClient(int cleClient) {
		this.cleClient = cleClient;
	}

	public int getQuantite_vendue() {
		return quantite_vendue;
	}

	public void setQuantite_vendue(int quantite_vendue) {
		this.quantite_vendue = quantite_vendue;
	}
	
	
}