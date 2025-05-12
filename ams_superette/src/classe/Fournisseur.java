package classe;

import java.util.HashMap;

import data.*;

public class Fournisseur implements IData 
{
	private String values;
	private String infos;
	private HashMap<String, fieldType> hm;
	private int id_fournisseur;
	private String nom_fournisseur;
	private String siret;
	private String adr_fournisseur;
	private String mail_fournisseur;
	private boolean actif;
	
	public Fournisseur(int idf, String nom, String sire, String adr, String mail)
	{
		id_fournisseur = idf;
		nom_fournisseur = nom;
		siret = sire;
		adr_fournisseur = adr;
		mail_fournisseur = mail;
		actif = true;
		
		getStruct();
	}
	
	public Fournisseur() 
	{
		// TODO Auto-generated constructor stub
		getStruct();
	}

	@Override
	public void getStruct() 
	{
		if (hm == null) {hm = new HashMap<String, fieldType>();}
		
		hm.put("id_fournisseur", fieldType.INT4);
		hm.put("nom_fournisseur", fieldType.VARCHAR);
		hm.put("siret", fieldType.VARCHAR);
		hm.put("adr_fournisseur", fieldType.VARCHAR);
		hm.put("mail_fournisseur", fieldType.VARCHAR);
		hm.put("actif", fieldType.BOOLEAN);
		
		infos = "nom_fournisseur,siret,adr_fournisseur,mail_fournisseur,actif";
		values = id_fournisseur +  ", '" + nom_fournisseur + "', '" + siret + "', '" + adr_fournisseur + "', '" + mail_fournisseur + "', '" + actif + "'";
	}

	public String getInfos() {
		return infos;
	}

	public void setInfos(String infos) {
		this.infos = infos;
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
		String ts = nom_fournisseur + " [ " + id_fournisseur + ", " + nom_fournisseur  + ", " + siret + ", " + adr_fournisseur + ", " + mail_fournisseur + " ]";
		return ts;
	}

	public HashMap<String, fieldType> getHm() {
		return hm;
	}

	public void setHm(HashMap<String, fieldType> hm) {
		this.hm = hm;
	}

	public int getId_fournisseur() {
		return id_fournisseur;
	}
	
	public String getIdName() {
		return "id_fournisseur";
	}

	public void setId_fournisseur(int id_fournisseur) {
		this.id_fournisseur = id_fournisseur;
	}

	public String getNom_fournisseur() {
		return nom_fournisseur;
	}

	public void setNom_fournisseur(String nom_fournisseur) {
		this.nom_fournisseur = nom_fournisseur;
	}

	public String getSiret() {
		return siret;
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	public String getAdr_fournisseur() {
		return adr_fournisseur;
	}

	public void setAdr_fournisseur(String adr_fournisseur) {
		this.adr_fournisseur = adr_fournisseur;
	}

	public String getMail_fournisseur() {
		return mail_fournisseur;
	}

	public void setMail_fournisseur(String mail_fournisseur) {
		this.mail_fournisseur = mail_fournisseur;
	}

	public void setValues(String values) {
		this.values = values;
	}

}
