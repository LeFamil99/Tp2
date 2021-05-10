package ca.qc.bdeb.info202.tp2.tuiles;

import ca.qc.bdeb.info202.tp2.personnages.Adlez;

/**
 * Représentation du plancher. Il est possible de marcher sur du plancher,
 * mais il n'est pas possible d'intéragir avec le plancher.
 * @see ca.qc.bdeb.info202.tp2.tuiles.Tuile
 */
public class Plancher extends Tuile {
    public Plancher(int x, int y) {
        super(x, y, ' ', true, false);
    }

    /**
     * {@inheritDoc}
     * Ne fait rien.
     * @param adlez
     */
    @Override
    public void action(Adlez adlez) {}
}
