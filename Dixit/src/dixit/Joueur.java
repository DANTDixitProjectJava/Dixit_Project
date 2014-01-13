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
public class Joueur {
    private List<Carte> main;

    /**
     *
     */
    public Joueur() {
        main = new ArrayList<Carte>();
    }

    /**
     *
     * @return
     */
    public List<Carte> getMain() {
        return main;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("");
        for (int i = 0; i < main.size(); i++) {
            res.append(main.get(i)).append(" ");
        }
        return res.toString();
    }
    
    
    
}
