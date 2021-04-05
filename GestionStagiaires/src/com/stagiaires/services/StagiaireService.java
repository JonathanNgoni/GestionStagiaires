package com.stagiaires.services;

import java.util.List;

import com.stagiaires.dao.DaoFactory;
import com.stagiaires.dao.StagiaireDao;
import com.stagiaires.pojos.Stagiaire;


public class StagiaireService {
	private DaoFactory daoFactory;

	public StagiaireService(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public Stagiaire createStagiaire(Stagiaire stage) {
		StagiaireDao stagiaireDao =daoFactory.getStagiaireDao();
		return stagiaireDao.insert(stage);
	}

	public Stagiaire getStagiaireById(int id) {
		StagiaireDao stagiaireDao = daoFactory.getStagiaireDao();
		return stagiaireDao.find(id);
	}

	public void updateStagiaire(Stagiaire stagiaire) {
		StagiaireDao stagiaireDao= daoFactory.getStagiaireDao();
		stagiaireDao.update(stagiaire);
	}

	public void deleteStagiaire(Stagiaire stagiaire) {
		StagiaireDao stagiaireDao = daoFactory.getStagiaireDao();
		stagiaireDao.delete(stagiaire);
	}

	public List<Stagiaire> getAll() {

		StagiaireDao stagiaireDao = daoFactory.getStagiaireDao();
		return stagiaireDao.list();
	}
}
