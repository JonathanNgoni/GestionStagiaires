package com.stagiaires.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Dao<T> {

	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	protected Database db;
	protected String tableName;

	Dao(Database db, String tableName) {

		this.db = db;
		this.tableName = tableName;
	}

	// Permet de r�cup�rer un objet via son ID
	public abstract T find(long id);

	// Permet de r�cup�rer une liste d'objet

	public abstract List<T> list();

	// Permet de cr�er une entr�e dans la base de donn�es par rapport � un objet
	public abstract T insert(T obj);

	// Permet de mettre � jour les donn�es d'une entr�e dans la base
	public abstract void update(T obj);

	// Permet la suppression d'une entr�e de la base
	public abstract void delete(T obj);

	// Permet la cr�ation d'un objet � partir d'un resultSet
	public abstract T load(ResultSet resultSet) throws SQLException;

	protected T getItemOnQuery(String requete, Object... objets) {

		T businessObject = null;
		try {
			connection = db.getConnection();
			preparedStatement = db.initialisationRequetePreparee(connection, requete, false, objets);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				businessObject = load(resultSet);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			db.close(resultSet, preparedStatement, connection);
		}
		return businessObject;
	}

	protected List<T> getListOnQuery(String requete, Object... objets) {
		List<T> businessObjectList = new ArrayList<T>();
		try {
			connection = db.getConnection();
			preparedStatement = db.initialisationRequetePreparee(connection, requete, false, objets);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				T businessObject = load(resultSet);
				businessObjectList.add(businessObject);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			db.close(resultSet, preparedStatement, connection);
		}
		return businessObjectList;
	}

	protected int insert(String requete, Object... objets) 
	 { 
	 try { 
	 connection = db.getConnection(); 
	 preparedStatement = db.initialisationRequetePreparee( 
	connection, requete, true, objets ); 
	 int statut = preparedStatement.executeUpdate(); 
	 if ( statut == 0 ) { 
	 throw new DaoException(String.format("�chec de la cr�ation dans la table %s", tableName)); 
	 } 
	 resultSet = preparedStatement.getGeneratedKeys(); 
	 if ( resultSet.next() ) { 
	 return resultSet.getInt(1); 
	 } else { 
	 throw new DaoException(String.format("�chec de la cr�ation dans la table %s, aucun ID auto-g�n�r� retourn�", tableName)); 
	 } 
	 } catch ( SQLException e ) { 
	 throw new DaoException( e ); 
	 } finally { 
	 db.close(resultSet, preparedStatement, connection); 
	 } 
	 }

	protected void update(String requete, Object... objets) {
		try {
			connection = db.getConnection();
			preparedStatement = db.initialisationRequetePreparee(connection, requete, true, objets);
			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new DaoException(String.format("�chec de la mise � jour dans la table %s", tableName));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			db.close(resultSet, preparedStatement, connection);
		}
	}

	protected void delete(String requete, Object... objets) 
	 { 
	 try { 
	 connection = db.getConnection(); 
	 preparedStatement = db.initialisationRequetePreparee( 
	connection, requete, true, objets );  int statut = preparedStatement.executeUpdate(); 
	 if ( statut == 0 ) { 
	 throw new DaoException(String.format("�chec de la mise � jour dans la table %s", tableName)); 
	 } 
	 } catch ( SQLException e ) { 
	 throw new DaoException( e ); 
	 } 
	 finally { 
	 db.close(resultSet, preparedStatement, connection); 
	 } 
	 }

}
