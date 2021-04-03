package com.stagiaires.clients;

import java.io.IOException;

import com.stagiaires.dao.DaoFactory;
import com.stagiaires.dao.DatabaseFactory;
import com.stagiaires.dao.VilleDao;
import com.stagiaires.pojos.Ville;
import com.stagiaires.services.VilleService;

public class test {
	
 
public static void main(String[] args) throws IOException {
	
	DaoFactory daoFactory = new DaoFactory(DatabaseFactory.createDatabase());
	
	VilleService service1  = new VilleService(daoFactory);
	//Ville ville =  new Ville("Grenoble");
	//service1.createVille(ville);
	
	Ville villeP = service1.getVilleById(8);
	service1.deleteVille(villeP);
	//System.out.println(ville.getNom()+" "+ville.getIdVille());
}
}

