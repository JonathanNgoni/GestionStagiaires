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
import com.stagiaires.services.GroupeService;

public class AdminClient {

	static Scanner sc = new Scanner(System.in);
	int pop = sc.nextInt();

	public static void main(String[] args) throws IOException {

		registerGroupes();
		System.out.println("########### Bonjour Admin  #########");
		System.out.println("Choisir un numero ci-dessus sinon taper zero pour sortir de programme");
		System.out.println("--------------------------------------------------------------------");
		
		//recuperer le numero id de groupe choisi
		int selectedGroup = selectGroupe();
		
		//recuperer les liste des etudiants pour chaque groupe chosi
		ArrayList<Stagiaire> listEtu = (ArrayList<Stagiaire>) afficheListStagiaire(selectedGroup);
		
		//tester si la liste est null
       if(listEtu.size()!=0) {
    	   
    	   //parcourir la liste et afficher
		 for (Stagiaire stag : listEtu) {
			System.out.println(stag.getID() + " " + stag.getNom() + " " + stag.getPrenom() + " " + stag.getVille() + " "
					+ stag.getId_groupe());
		 }
		 }else {
			System.out.println("Pardon, la groupe "+selectedGroup+" est vide !!" );	
		 }

	}

	public static void registerGroupes() throws IOException {

		DaoFactory daoFactory = new DaoFactory(DatabaseFactory.createDatabase());
		GroupeService groupeService = new GroupeService(daoFactory);

		ArrayList<Groupe> groupList = (ArrayList<Groupe>) groupeService.getAll();

		int size = groupList.size();
		if (size != 10 && size > 0) {
			for (int i = size; i < 10; i++) {
				groupeService.createGroupe(new Groupe("Groupe" + i));
			}
		} else if (size != 10 && size == 0) {
			for (int i = size; i < 10; i++) {
				groupeService.createGroupe(new Groupe("Groupe" + i + 1));
			}
		}
		ArrayList<Groupe> groupList2 = (ArrayList<Groupe>) groupeService.getAll();
		for (Groupe group : groupList2) {
			Date date = group.getDateDebut();

			System.out.println(group.getIdGroup() + " " + group.getNom() + " Créé le  " + date);
		}

	}
	
//la method pour recuperer le numero groupe chosi
	public static int selectGroupe() {
		int idGroupe = sc.nextInt();
		return idGroupe;
	}

	
	//la methode pour recuperer la liste des stagiaire  dans la groupe
	public static List<Stagiaire> afficheListStagiaire(int idGro) throws IOException {
		
		ArrayList<Stagiaire> listSt = new ArrayList<Stagiaire>();
		DaoFactory daoFactory = new DaoFactory(DatabaseFactory.createDatabase());
		
		GroupeService groupeService = new GroupeService(daoFactory);
		
		if (idGro == 0) {
			
			System.out.println("au revoir !!");
			
		} else {
			
			listSt = (ArrayList<Stagiaire>) groupeService.getAllSt(idGro);
		}

		return listSt;
	}

}
