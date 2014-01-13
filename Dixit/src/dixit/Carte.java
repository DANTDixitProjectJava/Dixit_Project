/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dixit;

/**
 *
 * @author paulo
 */
public class Carte {
    private int id;
    
    Carte(int i) {
        this.id = i;
    }

    @Override
    public String toString() {
        String res = ""+id;
        return res;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Carte other = (Carte) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
 
    
    
}
