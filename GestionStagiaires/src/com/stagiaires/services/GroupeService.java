package com.stagiaires.services;

import java.util.List;

import com.stagiaires.dao.DaoFactory;
import com.stagiaires.dao.GroupeDao;
import com.stagiaires.dao.StagiaireDao;
import com.stagiaires.dao.VilleDao;
import com.stagiaires.pojos.Groupe;
import com.stagiaires.pojos.Stagiaire;
import com.stagiaires.pojos.Ville;

public class GroupeService {
	private DaoFactory daoFactory;

	public GroupeService(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public Groupe createGroupe(Groupe groupe) {
		GroupeDao groupeDao =daoFactory.getGroupeDao();
		return groupeDao.insert(groupe);
	}

	public Groupe getGroupeById(int id) {
		GroupeDao groupeDao= daoFactory.getGroupeDao();
		return groupeDao.find(id);
	}

	public void updateGroupe(Groupe groupe) {
		GroupeDao groupeDao= daoFactory.getGroupeDao();
		groupeDao.update(groupe);
	}

	public void deleteVGroupe(Groupe groupe) {
		GroupeDao groupeDao= daoFactory.getGroupeDao();
		groupeDao.delete(groupe);
	}

	public List<Groupe> getAll() {
		GroupeDao groupeDao= daoFactory.getGroupeDao();
		return groupeDao.list();
	}
	
	public List<Stagiaire> getAllStagiairesParGroupe(int id) {
		StagiaireDao stagiaireDao= daoFactory.getStagiaireDao();
		return stagiaireDao.listStagiaire(id);
	}
}
