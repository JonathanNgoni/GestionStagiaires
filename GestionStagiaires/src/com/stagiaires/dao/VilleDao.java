package com.stagiaires.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.stagiaires.pojos.Ville;

public class VilleDao  extends Dao<Ville> { 
		 public static final String TABLE_NAME = "ville"; 
		 VilleDao(Database db) 
		 { 
		 super(db, TABLE_NAME); 
		 } 
		 
		 @Override
		 public Ville find(long id) { 
		 String requete = String.format("SELECT * FROM %s WHERE idVille = ?", TABLE_NAME); 
		 return getItemOnQuery(requete, id); 
		 } 
		 
		 @Override
		 public List<Ville> list() { 
		 String requete = String.format("SELECT * FROM %s", TABLE_NAME); 
		 return getListOnQuery(requete); 
		 } 
		 @Override
		 public Ville insert(Ville obj) { 
		 String requete = String.format("INSERT INTO %s (nom)" + 
		 " VALUES(?)", TABLE_NAME); 
		 
		 obj.setIdVille(insert(requete
		 , obj.getNom())); 
		 return obj; 
		 }  @Override
		 public void update(Ville obj) { 
		 String requete = String.format("UPDATE %s SET " + 
		 " nom = ? " + 
		 " WHERE idVille = ?", TABLE_NAME); 
		 
		 update(requete
		 , obj.getNom() 
		 , obj.getIdVille()); 
		 } 
		 @Override
		 public void delete(Ville obj) { 
		 String requete = String.format("DELETE FROM %s WHERE idVille = ?", 
		TABLE_NAME); 
		 delete(requete, obj.getIdVille()); 
		 } 
		 @Override
		 public Ville load(ResultSet resultSet) throws SQLException { 
		 Ville ville = new Ville(); 
		 
		 ville.setIdVille(resultSet.getInt("idVille")); 
		 ville.setNom(resultSet.getString("nom")); 
		 return ville; 
		 } 
}
