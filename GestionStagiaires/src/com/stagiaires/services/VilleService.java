package com.stagiaires.services;

import java.util.List;

import com.stagiaires.dao.DaoFactory;
import com.stagiaires.dao.VilleDao;
import com.stagiaires.pojos.Ville;

public class VilleService {

	private DaoFactory daoFactory;

	public VilleService(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public Ville createVille(Ville ville) {
		VilleDao villeDao =daoFactory.getVilleDao();
		return villeDao.insert(ville);
	}

	public Ville getVilleById(int id) {
		VilleDao villeDao = daoFactory.getVilleDao();
		return villeDao.find(id);
	}

	public void updateVille(Ville ville) {
		VilleDao villeDao = daoFactory.getVilleDao();
		villeDao.update(ville);
	}

	public void deleteVille(Ville ville) {
		VilleDao villeDao = daoFactory.getVilleDao();
		villeDao.delete(ville);
	}

	public List<Ville> getAll() {

		VilleDao villeDao = daoFactory.getVilleDao();
		return villeDao.list();
	}
}