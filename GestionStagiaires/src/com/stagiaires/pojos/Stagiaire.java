package com.stagiaires.pojos;

public class Stagiaire {
	private int ID;
	private String prenom;
	private String nom;
	private Ville ville;
	private int id_groupe;
	
	public Stagiaire() {
		super();
	}

	public Stagiaire( String prenom, String nom, Ville ville) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.ville = ville;

	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

	public int getId_groupe() {
		return id_groupe;
	}

	public void setId_groupe(int id_groupe) {
		this.id_groupe = id_groupe;
	}

	
	@Override
	public String toString() {
		return "Stagiaire [ID=" + ID + ", prenom=" + prenom + ", nom=" + nom + ", ville=" + ville + ", id_groupe="
				+ id_groupe + "]";
	}
	

	
}
