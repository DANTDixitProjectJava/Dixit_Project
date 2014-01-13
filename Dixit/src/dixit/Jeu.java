/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dixit;

/**
 *
 * @author paulo
 */
public class Jeu {
    private Plateau plateau;

    /**
     *
     */
    public Jeu() {
        this.plateau = new Plateau(4);
        plateau.jouer();
    }
    
    
}
