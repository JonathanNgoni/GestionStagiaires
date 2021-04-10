package com.stagiaires.clients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.stagiaires.dao.DaoFactory;
import com.stagiaires.dao.DatabaseFactory;
import com.stagiaires.pojos.Stagiaire;
import com.stagiaires.pojos.Ville;
import com.stagiaires.services.GroupeService;
import com.stagiaires.services.StagiaireService;
import com.stagiaires.services.VilleService;

public class Test {
	 private static DaoFactory daoFactory = null; 
	 private static GroupeService groupeService = null; 
	 private static StagiaireService stagiaireService = null; 
	 private static VilleService villeService = null;
	 static Scanner sc = new Scanner(System.in);
	int pop = sc.nextInt();
	public static void main(String[] args) throws IOException {
		System.out.println("chose between 1,2,3");

		int in =  sc.nextInt();
		GestionStagiaires(in);
	}
	public static  void GestionStagiaires(int opes) throws IOException {
		 daoFactory = new DaoFactory(DatabaseFactory.createDatabase());
		 stagiaireService  = new StagiaireService(daoFactory);
		 villeService =  new VilleService(daoFactory);
		 
	if(opes == 1) {
		System.out.println("voici les liste de Ville dans la base de données");
		List<Ville> listeVille = villeService.getAll();
		
		if(listeVille.size() != 0 ) {
			for(Ville vil : listeVille) {
				System.out.println(vil.getIdVille()+"  "+vil.getNom());
			}
		}else {
			System.out.println("liste est vide");
		}
		System.out.println("#############################################################################");
		System.out.println("Vous voulais ajouter une novelles ville?  Saisir 0 pour L'ajouter, Saisir 1 pour Continuer");
		int newville =sc.nextInt();
		if(newville==0) {
			System.out.println("Saisir le Nom Ville à ajouter dans la Database");
			String nomVil= sc.next();
			Ville newVille=  new Ville(nomVil);
			villeService.createVille(newVille);
			GestionStagiaires(1);
		}
		System.out.println("Pour Ajouter une Nouvelle Stagiaire ,Sinon Saisir Nom  stagiaire");
		String nom =  sc.next();
		System.out.println("Saisir PreNom  stagiaire");
		String prenom =  sc.next();
		System.out.println("Saisir idVille stagiaire");
		int idVille =  sc.nextInt();
		System.out.println("Saisir idGroupe stagiaire");
		int idGroupe =  sc.nextInt();
		Stagiaire newStagiaire = new Stagiaire(nom, prenom, idVille, idGroupe);
		Stagiaire stagiare= stagiaireService.createStagiaire(newStagiaire);
		System.out.println("Success!!! " +stagiare.getNom()+ "  "+stagiare.getPrenom()+" est bien ajouté dans la groupe "+stagiare.getId_groupe());
		
	}else 
		if(opes==2) {
			System.out.println("Saisir le numero de stagiaires à supprimer");
			int idStagiaire =  sc.nextInt();
			Stagiaire stagiaireAsupprimer =  stagiaireService.getStagiaireById(idStagiaire);
			stagiaireService.deleteStagiaire(stagiaireAsupprimer);
			System.out.println("Success!!! " +stagiaireAsupprimer.getNom()+ "  "+stagiaireAsupprimer.getPrenom()+" est bien supprimé de la groupe "+stagiaireAsupprimer.getId_groupe());
		
	}else {
		if(opes==3) {
			System.out.println("les Liste de groupe sont: ");
			GestionStagiairesClient.registerGroupes();
		}
	}
	}
}
