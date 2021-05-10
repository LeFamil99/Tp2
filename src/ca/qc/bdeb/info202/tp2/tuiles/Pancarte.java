package ca.qc.bdeb.info202.tp2.tuiles;

import ca.qc.bdeb.info202.tp2.personnages.Adlez;

/**
 * Représentation d'une pancarte. Une pancarte affiche un message.
 * @see ca.qc.bdeb.info202.tp2.tuiles.Tuile
 */
public class Pancarte extends Tuile{
    private String texte;
    public Pancarte(int x, int y, String texte) {
        super(x, y, '!', true, true);
        this.texte = texte;
    }

    /**
     * {@inheritDoc}
     * Afficher se qui se trouve sur la pancarte.
     * @param adlez
     */
    @Override
    public void action(Adlez adlez) {
        System.out.println(texte);
    }
}
