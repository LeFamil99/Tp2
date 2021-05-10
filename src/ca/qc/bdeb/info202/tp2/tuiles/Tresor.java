package ca.qc.bdeb.info202.tp2.tuiles;

import ca.qc.bdeb.info202.tp2.objets.Item;
import ca.qc.bdeb.info202.tp2.personnages.Adlez;

/**
 * Représentation d'un trésor. Un trésor contient un item pouvait être obtenu
 * en l'ouvrant.
 * @see ca.qc.bdeb.info202.tp2.tuiles.Tuile
 */
public class Tresor extends Tuile {

    private Item obj;
    private boolean estOuvert = false;

    public Tresor(int x, int y, Item obj) {
        super(x, y, '$', false, true);
        this.obj = obj;
    }

    /**
     * {@inheritDoc}
     * Permet Adlez d'utiliser l'item du trésor s'il est encore fermé.
     * Dans ce cas, il sera ensuite fermé.
     * @param adlez
     */
    @Override
    public void action(Adlez adlez) {
        if(!estOuvert) {
            estOuvert = true;
            adlez.utiliserItem(obj);
        }
    }

    /**
     * Retourne le symbole adapter. Si le trésor est ouvert,
     * son symbole deviens alors "_".
     * @return charctère à afficher pour le trésor
     */
    @Override
    public char getSymbole() {
        return estOuvert ? '_' : super.symbole;
    }
}
