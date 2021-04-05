package com.stagiaires.dao;

public class DaoFactory {
	
	private Database db;
	
	
	public DaoFactory(Database db)
	{
	this.db = db;
	}
	public VilleDao getVilleDao() {
	return new VilleDao(db);
	}
	// TODO : Ajouter les méthodes retournant les DAO pour Groupe et Stagiaire
	
	public StagiaireDao getStagiaireDao() {
		return new StagiaireDao(db);
		}
	
	public GroupeDao getGroupeDao() {
		return new GroupeDao(db);
		}
	}

