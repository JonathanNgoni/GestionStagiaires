package com.stagiaires.pojos;

import java.util.Date;

public class Groupe {
	private int idGroup;
	private String nom;
	private Date DateDebut;
	private Date DateDebutStage;
	private Date DateFin;

	public Groupe() {
		super();
	}

	public Groupe(String nom) {
		super();
		this.nom = nom;
		
	}

	public int getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(int idGroup) {
		this.idGroup = idGroup;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateDebut() {
		return DateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		DateDebut = dateDebut;
	}

	public Date getDateDebutStage() {
		return DateDebutStage;
	}

	public void setDateDebutStage(Date dateDebutStage) {
		DateDebutStage = dateDebutStage;
	}

	public Date getDateFin() {
		return DateFin;
	}

	public void setDateFin(Date dateFin) {
		DateFin = dateFin;
	}

	@Override
	public String toString() {
		return "Groupe [idGroup=" + idGroup + ", nom=" + nom + ", DateDebut=" + DateDebut + ", DateDebutStage="
				+ DateDebutStage + ", DateFin=" + DateFin + "]";
	}

}
