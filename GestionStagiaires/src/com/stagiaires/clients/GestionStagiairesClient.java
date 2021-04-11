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
	static int groupeID = 0;
	
	/////point d'entre du programme
	public static void main(String[] args) throws IOException {
		System.out.println("######################## Bonjour Admin ! ########################################### ");
		System.out.println();
		testerGroupeEnBDD();
	}
	
	
///////debut de methode 1
	public static void testerGroupeEnBDD() throws IOException {

		daoFactory = new DaoFactory(DatabaseFactory.createDatabase());
		groupeService = new GroupeService(daoFactory);

		ArrayList<Groupe> listeGroupesEnBDD = (ArrayList<Groupe>) groupeService.getAll();

		int nbGroupesEnBDD = listeGroupesEnBDD.size();

		if (nbGroupesEnBDD != 10 && nbGroupesEnBDD > 0) {
			for (int i = nbGroupesEnBDD; i < 10; i++) {
				groupeService.createGroupe(new Groupe("Groupe" + i));
			}

		} else if (nbGroupesEnBDD != 10 && nbGroupesEnBDD == 0) {
			for (int i = nbGroupesEnBDD; i < 10; i++) {
				groupeService.createGroupe(new Groupe("Groupe" + i));
			}
		}
		
		ArrayList<Groupe> listeGroupesAjour = (ArrayList<Groupe>) groupeService.getAll();
		System.out.println(" Voici les groupes de stagiaires actuellement enregistrés en base de données :");
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------");
		for (Groupe groupe : listeGroupesAjour) {
			System.out.println(groupe.getIdGroup() + " " + groupe.getNom() + " Créé le  " + groupe.getDateDebut());
             }
		
		System.out.println();
		System.out.println("########### ###################  #########");
		System.out.println(
				"A présent, sélectionnez un ID groupe parmi ceux qui s'affichent ci-dessus (ou tapez 0 pour sortir de cette application).");
		System.out.println("--------------------------------------------------------------------");

		groupeID = SelectionnerGroupe();
		if (groupeID == 0) {
			exit();
		} else {
			getStagiairesParGroupe(groupeID);
		}
	}
   ///fin
	
	
/// debut de Méthode pour recuperer l'ID du groupe choisi
	public static int SelectionnerGroupe() {
		int idGroupe = sc.nextInt();
		return idGroupe;
	}
///fin
	
	
//debut de  Méthode pour récupérer les listes de stagiaires pour chaque groupe choisi
	public static void getStagiairesParGroupe(int idGroupe) throws IOException {

		ArrayList<Stagiaire> listeStagiaires = new ArrayList<Stagiaire>();
		daoFactory = new DaoFactory(DatabaseFactory.createDatabase());

		groupeService = new GroupeService(daoFactory);

		switch (idGroupe) {
		case 0:
			exit();
			break;
		default:
			Groupe testerIDgroupe = groupeService.getGroupeById(idGroupe);
			if (testerIDgroupe != null) {
				listeStagiaires = (ArrayList<Stagiaire>) groupeService.getAllStagiairesParGroupe(idGroupe);
			} else {
				System.out.println(
						"------------------------------------------------------------------------------------------------------");
				System.out.println(
						"  Cet ID de groupe n'existe pas en base de données. Veuillez en saisir un autre parmi les groupes qui s'affichent !");
				testerGroupeEnBDD();
			}
		}

		afficherStagiaires(listeStagiaires);
	}
///fin 
	
/////debut de methode
	public static void afficherStagiaires(List<Stagiaire> listeStagiaires) throws IOException {
		
		daoFactory = new DaoFactory(DatabaseFactory.createDatabase());
		stagiaireService = new StagiaireService(daoFactory);
		villeService = new VilleService(daoFactory);
		
		// tester si la liste est vide sinon afficher les stagiaires du groupe
		if (listeStagiaires.size() != 0) {
			System.out.println(
					"##########################################################################################");
			// parcourir la liste et afficher les stagiaires du groupe
			System.out.println("Voici la liste des stagiaires du groupe sélectionné");
			System.out.println("-------------------------------------------------------------------------");

			for (Stagiaire stagiaire : listeStagiaires) {
              
				 Ville villeStagiaire = villeService.getVilleById(stagiaire.getVille());
				 Groupe groupeStagiaire = groupeService.getGroupeById(stagiaire.getId_groupe());
				System.out.println(
						"Id :  " + stagiaire.getID() + ", Nom: " + stagiaire.getNom() + ", Prenom: " + stagiaire.getPrenom()
								+ ",  Ville: " + villeStagiaire.getNom() + ", Groupe: " + groupeStagiaire.getNom());
			}
		} else {
			System.out.println();
			System.out.println("Désolé, le groupe que vous avez choisi est vide !!");
		}

		System.out.println();

		afficherOptions();
	}
///fin
	
////debut methode
	public static void GestionStagiaires(int option) throws IOException {
		daoFactory = new DaoFactory(DatabaseFactory.createDatabase());
		stagiaireService = new StagiaireService(daoFactory);
		villeService = new VilleService(daoFactory);
		switch (option) {

		case 0:
			exit();
			break;
		case 1:
			System.out.println("#############################################################################");
			System.out.println("Voici la liste de villes actuellement dans la base de données");
			List<Ville> listeVilles = villeService.getAll();

			if (listeVilles.size() != 0) {
				for (Ville ville : listeVilles) {
					System.out.println(ville.getIdVille() + "  " + ville.getNom());
				}
			} else {
				System.out.println("----------------------------------------------------------------------");
				System.out.println("La liste est vide");
			}
			System.out.println("#############################################################################");
			System.out.println(
					"Voulez-vous ajouter une nouvelle ville ?  Saisir 0 pour l'ajouter sinon 1 pour continuer.");
			System.out.println("----------------------------------------------------------------------");
			int idNouvelleVille = sc.nextInt();
			if (idNouvelleVille == 0) {
				System.out.println("Saisir à présent le nom de la ville à ajouter en base de données.");
				String nomVilleSaisi = sc.next();
				Ville nouvelleVille = new Ville(nomVilleSaisi);
				villeService.createVille(nouvelleVille);
				GestionStagiaires(1);
			}
			System.out.println("Pour ajouter un nouveau stagiaire veuillez saisir son nom.");
			String nom = sc.next();
			System.out.println("Veuillez saisir le prénom du stagiaire.");
			String prenom = sc.next();
			System.out.println("Veuillez saisir l'id de la ville de résidence du stagiaire.");
			int idVille = sc.nextInt();

			Ville testIDville = villeService.getVilleById(idVille);
			if (testIDville == null) {
				System.out.println(
						"------------------------------------------------------------------------------------------------------");
				System.out.println(
						" Ce ID du  ville  n'existe pas en base de données. Veuillez saisir l'autre parmi les ville qui s'affiche !");
				GestionStagiaires(1);
				
			}

			System.out.println("Veuillez saisir l'id du groupe auquel vous voulez affecter le stagiaire.");
			groupeID = sc.nextInt();
			Stagiaire newStagiaire = new Stagiaire(nom, prenom, idVille, groupeID);
			Stagiaire stagiaire = stagiaireService.createStagiaire(newStagiaire);
			System.out.println("Bravo !" + stagiaire.getNom() + "  " + stagiaire.getPrenom()+ " est bien ajouté dans le groupe " + stagiaire.getId_groupe());
			getStagiairesParGroupe(stagiaire.getId_groupe());
			break;
		case 2:
			System.out.println("Veuillez saisir l'ID du stagiaire à supprimer.");
			int idStagiaire = sc.nextInt();

			Stagiaire stagiaireAsupprimer = stagiaireService.getStagiaireById(idStagiaire);
			if (stagiaireAsupprimer.getId_groupe() == groupeID) {
				stagiaireService.deleteStagiaire(stagiaireAsupprimer);
				System.out.println("Bravo! " + stagiaireAsupprimer.getNom() + "  " + stagiaireAsupprimer.getPrenom()
						+ " est bien supprimé de la groupe " + stagiaireAsupprimer.getId_groupe());
				getStagiairesParGroupe(stagiaireAsupprimer.getId_groupe());
			} else {
				System.out.println("Vous ne pouvez pas supprimer le stagiaire ID: " + stagiaireAsupprimer.getID()+" car il n'est pas en  groupe "+ groupeID);
				afficherOptions();
			}
            break;
		case 3:

			GestionStagiairesClient.testerGroupeEnBDD();
			break;
		default:
			exit();
		}
	}
/////fin 
	
	
////debut de la method
	public static void afficherOptions() throws IOException {
		System.out.println("##########################################################################################");
		System.out.println("Quelle opération voulez-vous effectuer maintenant ? Choisissez 1, 2 , 3 ou 0 pour quitter parmi les choix suivants  : ");
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("1 : ajouter un stagiaire");
		System.out.println("2 : retirer un stagiaire du groupe");
		System.out.println("3 : retour à la liste des groupes");
		System.out.println("0 : quitter l'application");

		System.out.println("---------------------------------------------------------------------------");

		int choixOption = sc.nextInt();
		GestionStagiaires(choixOption);
	}
///fin
	
	
////debut de methode
	public static void exit() {
		System.out.println();
		System.out.println("############################# Merci. Au revoir! ################################");
	}
//fin
}
