/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dixit;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author paulo
 */
public class Plateau {
    private Joueur[] tabJoueurs;
    private int[] tabPoints;
    private List<Carte> pioche;
    
    private int idConteur;
    
    private Carte[] cartesPlateau;
    private Carte[] tabVote;

    /**
     * 
     * @param nbJoueur
     */
    public Plateau(int nbJoueur) {
        //Joueurs
        tabJoueurs = new Joueur[nbJoueur];
        tabPoints = new int[nbJoueur];
        for (int i = 0; i < nbJoueur; i++) {
            tabJoueurs[i] = new Joueur();
        }
        
        Carte carte;
        //Pioche
        pioche = new ArrayList<Carte>();
        
        for (int i = 0; i < 84; i++) {
            carte = new Carte(i);
            pioche.add(carte);
        }
        
        //Distribution des cartes
        Arbitre.distribution(pioche, tabJoueurs);
        
        //Determination du conteur
        this.idConteur = Arbitre.selectionConteur(nbJoueur);
        
        //cartePLateau
        cartesPlateau = new Carte[nbJoueur];
        
        //Tableau de vote
        tabVote = new Carte[nbJoueur];
    }

    /**
     *
     */
    public void jouer() {
        //Debut de la game
        int i;
        while(pioche.size() > 0){
            //Conteur
            i = idConteur;
            System.out.println("Conteur "+(idConteur+1));
            //Choix de la carte
            while(i < (idConteur+tabJoueurs.length)){
                //Main
                System.out.println("Main du Joueur "+((i%tabJoueurs.length)+1));
                System.out.println(this.tabJoueurs[(i%tabJoueurs.length)].toString());

                //Si conteur
                if(idConteur == (i%tabJoueurs.length)){
                    cartesPlateau[idConteur] = Arbitre.choixConteur(this.tabJoueurs[(i%tabJoueurs.length)], this);
                }
                //Si Joueur
                else{
                    cartesPlateau[(i%tabJoueurs.length)] = Arbitre.choixJoueur(this.tabJoueurs[(i%tabJoueurs.length)], this);
                }
                i++;
            }

            //Affichage des cartes
            Arbitre.afficheChoix(cartesPlateau);

            //Vote
            for (int j = 0; j < tabJoueurs.length; j++) {
                if(j != idConteur)
                    tabVote[j] = Arbitre.vote(this.tabJoueurs[j], cartesPlateau, cartesPlateau[j]);
            }

            //Decompte des points
            Arbitre.comptePoints(tabVote, cartesPlateau[idConteur], tabPoints, idConteur);
            Arbitre.afficheScore(tabPoints);
            
            idConteur = (idConteur+1)%tabJoueurs.length;
            //On repioche
            for (int j = 0; j < tabJoueurs.length; j++) {
                Arbitre.repioche(tabJoueurs[j], pioche);
            }
        }  
    }
}
