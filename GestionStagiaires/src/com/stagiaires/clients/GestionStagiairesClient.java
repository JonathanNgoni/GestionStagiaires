package com.stagiaires.clients;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.stagiaires.dao.DaoFactory;
import com.stagiaires.dao.DatabaseFactory;
import com.stagiaires.pojos.Groupe;
import com.stagiaires.pojos.Stagiaire;
import com.stagiaires.pojos.Ville;
import com.stagiaires.services.GroupeService;
import com.stagiaires.services.StagiaireService;
import com.stagiaires.services.VilleService;

public class GestionStagiairesClient  {

	 private static DaoFactory daoFactory = null; 
	 private static GroupeService groupeService = null; 
	 private static StagiaireService stagiaireService = null; 
	 private static VilleService villeService = null;
	 static Scanner sc = new Scanner(System.in);
	int pop = sc.nextInt();

	public static void main(String[] args)  {

		try {
			registerGroupes();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		System.out.println("########### Bonjour Admin  #########");
		System.out.println("Choisir un numero ci-dessus sinon taper zero pour sortir de programme");
		System.out.println("--------------------------------------------------------------------");
		
		//recuperer le numero id de groupe choisi
		int groupChoisi = selectGroupe();
		
		//recuperer les liste des etudiants pour chaque groupe chosi
		ArrayList<Stagiaire> listeStagiairesParGroupe = null;
		try {
			listeStagiairesParGroupe = (ArrayList<Stagiaire>) getStagiairesParGroupe(groupChoisi);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		//tester la liste s'il et vide sinon afficher les stagiaires dans la groupe
		try {
			afficherStagiaires(listeStagiairesParGroupe);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("Quelle Operations vous voulais effectuer , choisir 1 ou 2 ou 3 parmi les suivant");
		
		System.out.println("1 : ajouter un stagiaire"); 
		System.out.println("2 : retirer un stagiaire du groupe");
		System.out.println("3 : retour à la liste des groupes");
		
		int choixMenus=  sc.nextInt();
		
	}

	public static void registerGroupes() throws IOException {

		daoFactory = new DaoFactory(DatabaseFactory.createDatabase());
		groupeService = new GroupeService(daoFactory);

		ArrayList<Groupe> listeGroupe = (ArrayList<Groupe>) groupeService.getAll();

		int nbGroupe = listeGroupe.size();
		if (nbGroupe  != 10 && nbGroupe  > 0) {
			for (int i = nbGroupe ; i < 10; i++) {
				groupeService.createGroupe(new Groupe("Groupe" + i));
			}
		} 
		else if (nbGroupe  != 10 && nbGroupe  == 0) {
			for (int i = nbGroupe ; i < 10; i++) {
				groupeService.createGroupe(new Groupe("Groupe" + i + 1));
			}
		}
		ArrayList<Groupe> listeGroupeAjour = (ArrayList<Groupe>) groupeService.getAll();
		for (Groupe group :  listeGroupeAjour ) {
			Date date = group.getDateDebut();

			System.out.println(group.getIdGroup() + " " + group.getNom() + " Créé le  " + date);
		}

	}
	
    //la method pour recuperer le numero groupe 
	public static int selectGroupe() {
		int idGroupe = sc.nextInt();
		return idGroupe;
	}

	
	//la methode pour recuperer la liste des stagiaire  dans la groupe
	public static List<Stagiaire> getStagiairesParGroupe(int idGro) throws IOException {
		
		ArrayList<Stagiaire> listSt = new ArrayList<Stagiaire>();
		daoFactory = new DaoFactory(DatabaseFactory.createDatabase());
		
		groupeService = new GroupeService(daoFactory);
		
		if (idGro == 0) {
			
			System.out.println("au revoir !!");
			
			
		} else {
			
			listSt = (ArrayList<Stagiaire>) groupeService.getAllStagiairesParGroupe(idGro);
		}

		return listSt;
	}
	public static void afficherStagiaires(List<Stagiaire> listE )throws IOException{
		//tester si la liste est null
	       if(listE.size()!=0) {
	    	   
	    	   //parcourir la liste et afficher
			 for (Stagiaire stag : listE) {
				System.out.println(stag.getID() + " " + stag.getNom() + " " + stag.getPrenom() + " " + stag.getVille() + " "
						+ stag.getId_groupe());
			 }
			 }else {
				System.out.println("Pardon, la groupe vous avez choisi est vide !!" );	
			 }

	}
	
	public static  void GestionStagiaires(int opes) throws IOException {
		 daoFactory = new DaoFactory(DatabaseFactory.createDatabase());
		 stagiaireService  = new StagiaireService(daoFactory);
	if(opes == 1) {
		System.out.println("voici les liste de Ville dans la base de données");
		ArrayList<Ville> listeVille =  (ArrayList<Ville>) villeService.getAll();
		
		System.out.println("Saisir Nom  stagiaire");
		String nom =  sc.nextLine();
		System.out.println("Saisir PreNom  stagiaire");
		String prenom =  sc.nextLine();
		System.out.println("Saisir idVille stagiaire");
		int idVille =  sc.nextInt();
		System.out.println("Saisir idGroupe stagiaire");
		int idGroupe =  sc.nextInt();
		Stagiaire newStagiaire = new Stagiaire(nom, prenom, idVille, idGroupe);
		
	}else 
		if(opes==2) {
			System.out.println("Saisir le numero de stagiaires à supprimer");
			int idStagiaire =  sc.nextInt();
			Stagiaire stagiaireAsupprimer =  stagiaireService.getStagiaireById(idStagiaire);
			stagiaireService.deleteStagiaire(stagiaireAsupprimer);
		
	}else {
		if(opes==3) {
			System.out.println("les Liste de groupe sont: ");
			registerGroupes();
		}
	}
	}
}
