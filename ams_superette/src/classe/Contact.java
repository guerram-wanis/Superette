package classe;

import java.util.HashMap;

import data.*;

public class Contact implements IData 
{
	private String values;
	private String infos;
	private HashMap<String, fieldType> hm;
	private int id_contact;
	private String prenom_contact;
	private String nom_contact;	
	private String fonction_contact;
	private String mail_contact;
	private String tel_contact;
	private boolean actif;
	
	
	public Contact(int idc, String pre, String nom, String fonct, String mail, String tel)
	{
		id_contact = idc;
		prenom_contact = pre;
		nom_contact = nom;
		fonction_contact = fonct;
		mail_contact = mail;
		tel_contact = tel;
		actif = true;
		
		getStruct();
	}
	
	public Contact() 
	{
		getStruct();
	}

	@Override
	public void getStruct() 
	{
		if (hm == null) {hm = new HashMap<String, fieldType>();}
		
		hm.put("id_contact", fieldType.INT4);
		hm.put("prenom_contact", fieldType.VARCHAR);
		hm.put("nom_contact", fieldType.VARCHAR);
		hm.put("fonction_contact", fieldType.VARCHAR);
		hm.put("mail_contact", fieldType.VARCHAR);
		hm.put("tel_contact", fieldType.VARCHAR);
		hm.put("actif", fieldType.BOOLEAN);
		
		infos = "prenom_contact,nom_contact,fonction_contact,mail_contact,tel_contact";
		values = id_contact +  ", '" + prenom_contact + "', '" + nom_contact + "', '" + fonction_contact + "', '" + mail_contact + "', '" + tel_contact + "', '" + actif + "'";
	}
	
	public String getIdName() {
		return "id_contact";
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
		return "Contact [id_contact=" + id_contact + ", prenom_contact=" + prenom_contact + ", nom_contact="
				+ nom_contact + ", fonction_contact=" + fonction_contact + ", mail_contact=" + mail_contact
				+ ", tel_contact=" + tel_contact + "]";
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

	public int getId_contact() {
		return id_contact;
	}

	public void setId_contact(int id_contact) {
		this.id_contact = id_contact;
	}

	public String getPrenom_contact() {
		return prenom_contact;
	}

	public void setPrenom_contact(String prenom_contact) {
		this.prenom_contact = prenom_contact;
	}

	public String getNom_contact() {
		return nom_contact;
	}

	public void setNom_contact(String nom_contact) {
		this.nom_contact = nom_contact;
	}

	public String getFonction_contact() {
		return fonction_contact;
	}

	public void setFonction_contact(String fonction_contact) {
		this.fonction_contact = fonction_contact;
	}

	public String getMail_contact() {
		return mail_contact;
	}

	public void setMail_contact(String mail_contact) {
		this.mail_contact = mail_contact;
	}

	public String getTel_contact() {
		return tel_contact;
	}

	public void setTel_contact(String tel_contact) {
		this.tel_contact = tel_contact;
	}
}
