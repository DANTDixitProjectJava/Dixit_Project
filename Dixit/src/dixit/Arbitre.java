/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dixit;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author paulo
 */
public class Arbitre {

    static void distribution(List<Carte> listeCartes, Joueur[] tabJoueurs) {
        int idCarte;
        //Chaque Joueur
        for (int i = 0; i < tabJoueurs.length; i++) {
            //6 Cartes par Joueur
            for (int j = 0; j < 6; j++) {
                idCarte = (int) (Math.random()*listeCartes.size());
                tabJoueurs[i].getMain().add(listeCartes.get(idCarte));
                listeCartes.remove(idCarte);
            }
        }
    }

    static int selectionConteur(int nbJoueur) {
        int idConteur = (int) (Math.random()*nbJoueur);
        return idConteur;
    }

    static Carte choixConteur(Joueur joueur, Plateau plateau) {
        //Choix id Carte
        Scanner sc = new Scanner(System.in);
        System.out.println("Choix de la carte ");
        int res = Integer.parseInt(sc.nextLine());
        while(res > joueur.getMain().size()){
            System.out.println("Choix impossible");
            System.out.println("Choix de la carte ");
            res = Integer.parseInt(sc.nextLine());
        }
        //Phrase
        System.out.println("Phrase indice");
        System.out.println(sc.nextLine());

        return joueur.getMain().get(res-1);
    }

    static Carte choixJoueur(Joueur joueur, Plateau plateau) {
        //Choix id Carte
        Scanner sc = new Scanner(System.in);
        System.out.println("Choix de la carte ");
        int res = Integer.parseInt(sc.nextLine());
        while(res > joueur.getMain().size()){
            System.out.println("Choix impossible");
            System.out.println("Choix de la carte ");
            res = Integer.parseInt(sc.nextLine());
        }
        joueur.getMain().remove(res);
        return joueur.getMain().get(res-1);
    }

    static void afficheChoix(Carte[] cartesPlateau) {
        for (int i = 0; i < cartesPlateau.length; i++) {
            System.out.print(cartesPlateau[i]+" ");
        }
    }

    static Carte vote(Joueur joueur, Carte[] cartesPlateau, Carte choix) {
    //Choix id Carte
        Scanner sc = new Scanner(System.in);
        System.out.println("Trouver la carte du conteur");
        int res = Integer.parseInt(sc.nextLine());
        while(res > cartesPlateau.length || joueur.getMain().get(res).equals(choix)){
            System.out.println("Choix impossible");
            System.out.println("Trouver la carte du conteur");
            res = Integer.parseInt(sc.nextLine());
        }
        return cartesPlateau[res-1];        
    }

    static void comptePoints(Carte[] tabVote, Carte carte, int[] tabPoints, int idConteur) {
        if(Arbitre.toutBon(tabVote,carte, idConteur) || Arbitre.toutFaux(tabVote,carte, idConteur)){
            //0 pour le conteur
            //2 pour les joueurs
            for (int i = 0; i < tabPoints.length; i++) {
                if(idConteur != i)
                    tabPoints[i] = tabPoints[i]+2;
            }
        }
        else{
            //conteur
            tabPoints[idConteur] = tabPoints[idConteur]+3;
            //Joueur Bon + 3
            for (int i = 0; i < tabPoints.length; i++) {
                if(idConteur != i && tabVote[i].equals(carte))
                    tabPoints[i] = tabPoints[i] + 3;
            }
            //+1 par vote sur son image (sauf conteur)
            for (int i = 0; i < tabPoints.length; i++) {
                if(idConteur != i){
                    for (int j = 0; j < tabPoints.length; j++) {
                        if(tabVote[i].equals(tabVote[j]) && idConteur != j)
                            tabPoints[j] = tabPoints[j] + 1;
                    }
                }
            }            
        }
    }

    private static boolean toutBon(Carte[] tabVote, Carte carte, int idConteur) {
        for (int i = 0; i < tabVote.length; i++) {
            if(i != idConteur){
                if(!tabVote[i].equals(carte))
                    return false;
            }
        }
        return true;
    }

    private static boolean toutFaux(Carte[] tabVote, Carte carte, int idConteur) {
        for (int i = 0; i < tabVote.length; i++) {
            if(i != idConteur){
                if(tabVote[i].equals(carte))
                    return false;
            }
        }
        return true;
    }    

    static void afficheScore(int[] tabPoints) {
        for (int i = 0; i < tabPoints.length; i++) {
            System.out.println("Joueur "+ i + " : "+tabPoints[i]);
        }
    }

    static void repioche(Joueur joueur, List<Carte> pioche) {
        int indice = (int) (Math.random()*pioche.size());
        joueur.getMain().add(pioche.get(indice));
    }
    
}
