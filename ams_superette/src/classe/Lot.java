package classe;

import java.util.HashMap;

import data.*;

public class Lot implements IData 
{
	private String values;
	private String infos;
	private HashMap<String, fieldType> hm;
	private int id_lot;
	private double prix_achat;
	private int quantite_lot;
	private String date_achat;
	private String date_peremption;
	private boolean etat;
	private int id_contrat;
	
	public Lot(int idl, double prix,int qt, String dateA, String dateP, int idc)
	{
		id_lot = idl;
		quantite_lot = qt;
		prix_achat = prix;
		date_achat = dateA;
		date_peremption = dateP;
		etat = false;
		id_contrat = idc;
		
		getStruct();
	}
	
	public Lot() 
	{
		getStruct();
	}

	@Override
	public void getStruct() 
	{
		if (hm == null) {hm = new HashMap<String, fieldType>();}
		
		hm.put("id_lot", fieldType.INT4);
		hm.put("prix_achat", fieldType.NUMERIC);
		hm.put("quantite_lot", fieldType.INT4);
		hm.put("date_achat", fieldType.DATE);
		hm.put("date_peremption", fieldType.DATE);
		hm.put("etat", fieldType.VARCHAR);
		hm.put("id_contrat", fieldType.INT4);
		
		infos = "id_lot,prix_achat,quantite_lot,date_achat,date_peremption,id_contrat";
		values = id_lot +  ", " + prix_achat + ", " + quantite_lot + ", '" + date_achat + "', '" + date_peremption + "', 'Non confirmé', " + id_contrat;
	}
	
	public String getIdName() {
		return "id_lot";
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
		return "Lot [id_lot=" + id_lot + ", prix_achat=" + prix_achat + ", quantite_lot=" + quantite_lot
				+ ", date_achat=" + date_achat + ", date_peremption=" + date_peremption + ", etat=" + etat
				+ ", id_contrat=" + id_contrat + "]";
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

	public int getId_lot() {
		return id_lot;
	}

	public void setId_lot(int id_lot) {
		this.id_lot = id_lot;
	}

	public double getPrix_achat() {
		return prix_achat;
	}

	public void setPrix_achat(double prix_achat) {
		this.prix_achat = prix_achat;
	}

	public int getquantite_lot() {
		return quantite_lot;
	}

	public void setquantite_lot(int quantite_lot) {
		this.quantite_lot = quantite_lot;
	}

	public String getDate_achat() {
		return date_achat;
	}

	public void setDate_achat(String date_achat) {
		this.date_achat = date_achat;
	}

	public String getDate_peremption() {
		return date_peremption;
	}

	public void setDate_peremption(String date_peremption) {
		this.date_peremption = date_peremption;
	}

	public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	public int getId_contrat() {
		return id_contrat;
	}

	public void setId_contrat(int id_contrat) {
		this.id_contrat = id_contrat;
	}
}