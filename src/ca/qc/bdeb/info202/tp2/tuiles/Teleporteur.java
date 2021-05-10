package ca.qc.bdeb.info202.tp2.tuiles;

import ca.qc.bdeb.info202.tp2.personnages.Adlez;

/**
 * Représentation d'un téléporteur. Un téléporteur permet de se déplacer vers
 * la case de coordonnée (destinationX; destinationX).
 * @see ca.qc.bdeb.info202.tp2.tuiles.Tuile
 */
public class Teleporteur extends Tuile {
    private int destinationY;
    private int destinationX;

    public Teleporteur(int x, int y, int destinationX, int destinationY) {
        super(x, y, '*', true, true);
        this.destinationX = destinationX;
        this.destinationY = destinationY;
    }

    /**
     * {@inheritDoc}
     * Déplace Adlez à la position (destinationX; destinationX).
     * @param adlez
     */
    @Override
    public void action(Adlez adlez) {
        adlez.repositionner(destinationX, destinationY);
    }
}
