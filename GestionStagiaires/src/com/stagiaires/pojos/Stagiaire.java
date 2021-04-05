package com.stagiaires.pojos;

public class Stagiaire {
	private int ID;
	private String prenom;
	private String nom;
	private int ville;
	private int id_groupe;
	
	
	
	public Stagiaire() {
		super();
	}

	public Stagiaire( String prenom, String nom, int ville, int id_groupe) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.ville = ville;
		this.id_groupe = id_groupe;

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

	public int getVille() {
		return ville;
	}

	public void setVille(int ville) {
		this.ville = ville;
	}

	public int getId_groupe() {
		return id_groupe;
	}

	public void setId_groupe(int id_groupe) {
		this.id_groupe = id_groupe;
	}

	public void eat() {
		System.out.println("i eat everyday");
	}
	
	@Override
	public String toString() {
		return "Stagiaire [ID=" + ID + ", prenom=" + prenom + ", nom=" + nom + ", ville=" + ville + ", id_groupe="
				+ id_groupe + "]";
	}
	

	
}
