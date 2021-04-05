package com.stagiaires.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.stagiaires.pojos.Groupe;
import com.stagiaires.pojos.Ville;

public class GroupeDao extends Dao<Groupe> { 
		 public static final String TABLE_NAME = "groupe"; 
		 GroupeDao(Database db) 
		 { 
		 super(db, TABLE_NAME); 
		 } 
		 
		 @Override
		 public Groupe find(long id) { 
		 String requete = String.format("SELECT * FROM %s WHERE idGroupe = ?", TABLE_NAME); 
		 return getItemOnQuery(requete, id); 
		 } 
		 
		 @Override
		 public List<Groupe> list() { 
		 String requete = String.format("SELECT * FROM %s", TABLE_NAME); 
		 return getListOnQuery(requete); 
		 } 
		 @Override
		 public Groupe insert(Groupe obj) { 
		 String requete = String.format("INSERT INTO %s (nom)" + 
		 " VALUES(?)", TABLE_NAME); 
		 
		 obj.setIdGroup(insert(requete, obj.getNom())); 
		 return obj;

		 }  @Override
		 public void update(Groupe obj) { 
		 String requete = String.format("UPDATE %s SET " + 
		 " nom = ? " + 
		 " WHERE idGroupe = ?", TABLE_NAME); 
		 
		 update(requete
		 , obj.getNom() 
		 , obj.getIdGroup()); 
		 } 
		 @Override
		 public void delete(Groupe obj) { 
		 String requete = String.format("DELETE  FROM %s WHERE idGroupe = ?",  
		TABLE_NAME); 
		 delete(requete, obj.getIdGroup()); 
		 } 
		 @Override
		 public Groupe load(ResultSet resultSet) throws SQLException { 
		 Groupe groupe = new Groupe(); 
		 
		 groupe.setIdGroup(resultSet.getInt("idGroupe")); 
		 groupe.setNom(resultSet.getString("nom")); 
		 groupe.setDateDebut(resultSet.getDate("dateDebut"));
		 return groupe; 
		 } 
}
