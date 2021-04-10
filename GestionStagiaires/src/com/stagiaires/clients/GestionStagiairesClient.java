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

public class GestionStagiairesClient {

	private static DaoFactory daoFactory = null;
	private static GroupeService groupeService = null;
	private static StagiaireService stagiaireService = null;
	private static VilleService villeService = null;
	static Scanner sc = new Scanner(System.in);
	int pop = sc.nextInt();
     static int groupeID = 0;
     
	public static void main(String[] args) throws IOException {

		registerGroupes();

		// recuperer le numero id de groupe choisi

		// recuperer les liste des etudiants pour chaque groupe chosi
		// ArrayList<Stagiaire> listeStagiairesParGroupe = null;

		// listeStagiairesParGroupe = (ArrayList<Stagiaire>)
		// getStagiairesParGroupe(groupChoisi);

		// tester la liste s'il et vide sinon afficher les stagiaires dans la groupe

		// int choixMenus = afficherStagiaires(listeStagiairesParGroupe);

		// GestionStagiaires(choixMenus);

	}

	
	
	public static void registerGroupes() throws IOException {

		daoFactory = new DaoFactory(DatabaseFactory.createDatabase());
		groupeService = new GroupeService(daoFactory);

		ArrayList<Groupe> listeGroupe = (ArrayList<Groupe>) groupeService.getAll();

		int nbGroupe = listeGroupe.size();
		if (nbGroupe != 10 && nbGroupe > 0) {
			for (int i = nbGroupe; i < 10; i++) {
				groupeService.createGroupe(new Groupe("Groupe" + i));
			}
		} else if (nbGroupe != 10 && nbGroupe == 0) {
			for (int i = nbGroupe; i < 10; i++) {
				groupeService.createGroupe(new Groupe("Groupe" + i + 1));
			}
		}
		ArrayList<Groupe> listeGroupeAjour = (ArrayList<Groupe>) groupeService.getAll();
		System.out.println("########### Bonjour Admin Voici les groupe  #########");
		for (Groupe group : listeGroupeAjour) {
			Date date = group.getDateDebut();

			System.out.println(group.getIdGroup() + " " + group.getNom() + " Créé le  " + date);
		}

		System.out.println("########### Bonjour Admin  #########");
		System.out.println("Choisir un numero ci-dessus sinon taper zero pour sortir de programme");
		System.out.println("--------------------------------------------------------------------");

		groupeID = selectGroupe();
		getStagiairesParGroupe(groupeID);
	}

	
	
	// la method pour recuperer le numero groupe
	public static int selectGroupe() {
		int idGroupe = sc.nextInt();
		return idGroupe;
	}

	
	
	// la methode pour recuperer la liste des stagiaire dans la groupe
	public static void getStagiairesParGroupe(int idGro) throws IOException {

		ArrayList<Stagiaire> listSt = new ArrayList<Stagiaire>();
		daoFactory = new DaoFactory(DatabaseFactory.createDatabase());

		groupeService = new GroupeService(daoFactory);
		switch (idGro) {
		case 0:
			System.out.println("au revoir !!");
			break;
		default:
			listSt = (ArrayList<Stagiaire>) groupeService.getAllStagiairesParGroupe(idGro);

		}
		afficherStagiaires(listSt);
	}

	
	
	public static void afficherStagiaires(List<Stagiaire> listE) throws IOException {
		// tester si la liste est null
		if (listE.size() != 0) {

			// parcourir la liste et afficher
			System.out.println("Voici la liste de stagiaires dans une groupe");
			for (Stagiaire stag : listE) {
				System.out.println(stag.getID() + " " + stag.getNom() + " " + stag.getPrenom() + " " + stag.getVille()
						+ " " + stag.getId_groupe());
			}
		} else {
			System.out.println("Pardon, la groupe vous avez choisi est vide !!");
		}
		afficherOptions();
	}

	
	
	public static void GestionStagiaires(int opes) throws IOException {
		daoFactory = new DaoFactory(DatabaseFactory.createDatabase());
		stagiaireService = new StagiaireService(daoFactory);
		villeService = new VilleService(daoFactory);
		switch (opes) {

		case 0:
			exit();
			break;
		case 1:
			System.out.println("#############################################################################");
			System.out.println("voici les liste de Ville dans la base de données");
			List<Ville> listeVille = villeService.getAll();

			if (listeVille.size() != 0) {
				for (Ville vil : listeVille) {
					System.out.println(vil.getIdVille() + "  " + vil.getNom());
				}
			} else {
				System.out.println("liste est vide");
			}
			System.out.println("#############################################################################");
			System.out.println(
					"Vous voulais ajouter une novelles ville?  Saisir 0 pour L'ajouter, Saisir 1 pour Continuer");
			int newville = sc.nextInt();
			if (newville == 0) {
				System.out.println("Saisir le Nom Ville à ajouter dans la Database");
				String nomVil = sc.next();
				Ville newVille = new Ville(nomVil);
				villeService.createVille(newVille);
				GestionStagiaires(1);
			}
			System.out.println("Pour Ajouter une Nouvelle Stagiaire ,Sinon Saisir Nom  stagiaire");
			String nom = sc.next();
			System.out.println("Saisir PreNom  stagiaire");
			String prenom = sc.next();
			System.out.println("Saisir idVille stagiaire");
			int idVille = sc.nextInt();
			System.out.println("Saisir idGroupe stagiaire");
			groupeID = sc.nextInt();
			Stagiaire newStagiaire = new Stagiaire(nom, prenom, idVille, groupeID);
			Stagiaire stagiare = stagiaireService.createStagiaire(newStagiaire);
			System.out.println("Success!!! " + stagiare.getNom() + "  " + stagiare.getPrenom()
					+ " est bien ajouté dans la groupe " + stagiare.getId_groupe());
			getStagiairesParGroupe(stagiare.getId_groupe());
			break;
		case 2:
			System.out.println("Saisir le numero de stagiaires à supprimer");
			int idStagiaire = sc.nextInt();

			Stagiaire stagiaireAsupprimer = stagiaireService.getStagiaireById(idStagiaire);
			if (stagiaireAsupprimer.getId_groupe() == groupeID) {
				stagiaireService.deleteStagiaire(stagiaireAsupprimer);
			System.out.println("Success!!! " + stagiaireAsupprimer.getNom() + "  " + stagiaireAsupprimer.getPrenom()
					+ " est bien supprimé de la groupe " + stagiaireAsupprimer.getId_groupe());
			getStagiairesParGroupe(stagiaireAsupprimer.getId_groupe());}
			else {
				System.out.println("vous pouvez pas supprimer le stagiaire car il n'es pas dans la meme groupe");
			}

		case 3:
			System.out.println("les Liste de groupe sont: ");
			GestionStagiairesClient.registerGroupes();
			break;
		default:
			exit();
		}

	}

	
	
	public static void afficherOptions() throws IOException {
		System.out.println("Quelle Operations vous voulais effectuer , choisir 1 ou 2 ou 3 parmi les suivant");

		System.out.println("1 : ajouter un stagiaire");
		System.out.println("2 : retirer un stagiaire du groupe");
		System.out.println("3 : retour à la liste des groupes");
		System.out.println("0 : Pour Quitter");

		int choice = sc.nextInt();
		GestionStagiaires(choice);
	}

	public static void exit() {
		System.out.println("Au revoir!!");
	}
}
