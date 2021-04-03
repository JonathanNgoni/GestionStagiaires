package com.stagiaires.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.stagiaires.pojos.Stagiaire;
import com.stagiaires.pojos.Ville;

public class StagiaireDao extends Dao<Stagiaire> { 
	 public static final String TABLE_NAME = "stagiaire"; 
	 StagiaireDao(Database db) 
	 { 
	 super(db, TABLE_NAME); 
	 } 
	 
	 @Override
	 public Stagiaire find(long id) { 
	 String requete = String.format("SELECT * FROM %s WHERE idGroupe = ?", TABLE_NAME); 
	 return getItemOnQuery(requete, id); 
	 } 
	 
	 @Override
	 public List<Stagiaire> list() { 
	 String requete = String.format("SELECT * FROM %s", TABLE_NAME); 
	 return getListOnQuery(requete); 
	 } 
	 @Override
	 public Stagiaire insert(Stagiaire obj) { 
	 String requete = String.format("INSERT INTO %s (nom)" + 
	 " VALUES(?)", TABLE_NAME); 
	 
	 obj.setID(insert(requete
	 , obj.getNom())); 
	 return obj; 
	 
	 }  @Override
	 
	 public void update(Stagiaire obj) { 
	 String requete = String.format("UPDATE %s SET " + 
	 " nom = ? " + 
	 " WHERE idGroupe = ?", TABLE_NAME); 
	 
	 update(requete
	 , obj.getNom() 
	 , obj.getID()); 
	 } 
	 @Override
	 public void delete(Stagiaire obj) { 
	 String requete = String.format("DELETE FROM %s WHERE idGroupe = ?", 
	TABLE_NAME); 
	 delete(requete, obj.getID()); 
	 } 
	 @Override
	 public Stagiaire load(ResultSet resultSet) throws SQLException { 
		 Stagiaire stagiaire = new Stagiaire(); 
	 
		 stagiaire.setID(resultSet.getInt("idGroupe")); 
		 stagiaire.setNom(resultSet.getString("nom")); 
	 return stagiaire; 
	 }  }


