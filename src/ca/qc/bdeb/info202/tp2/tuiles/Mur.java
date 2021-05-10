package ca.qc.bdeb.info202.tp2.tuiles;

import ca.qc.bdeb.info202.tp2.personnages.Adlez;

/**
 * Représentation d'un mur. Il est impossible de marcher et
 * d'interragir avec un mur.
 * @see ca.qc.bdeb.info202.tp2.tuiles.Tuile
 */
public class Mur extends Tuile{

    public Mur(int x, int y) {
        super(x, y, '#', false, false);
    }

    /**
     * {@inheritDoc}
     * Ne fait rien.
     * @param adlez
     */
    @Override
    public void action(Adlez adlez) {

    }
}
